package models;

import assets.MorphiaObject;
import assets.ObjectIdSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity("users")
public class UserModel
{
    @Id
    @JsonSerialize(using = ObjectIdSerializer.class)
    public ObjectId id;

    @Indexed
    public String email;

    public String firstName;
    public String lastName;

    @JsonIgnore
    public PasswordModel password;

    @play.data.format.Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date dateCreatedUtc;

    public CompanyModel company;

    public List<UserRole> roles = new ArrayList<>();

    public static UserModel Get(ObjectId id)
    {
        return MorphiaObject.datastore.find(UserModel.class).filter("_id", id).get();
    }

    public static UserModel Get(String email)
    {
        return MorphiaObject.datastore.find(UserModel.class).filter("email", email).get();
    }

    public void SetPassword(String password)
    {
        PasswordModel p = new PasswordModel();
        p.salt = BCrypt.gensalt();
        p.hash = BCrypt.hashpw(password, p.salt);

        this.password = p;
    }

    public boolean IsPasswordValid(String password)
    {
        return BCrypt.checkpw(password, this.password.hash);
    }

    public void Save()
    {
        MorphiaObject.datastore.save(this);
    }
}
