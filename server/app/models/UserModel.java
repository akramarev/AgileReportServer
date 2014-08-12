package models;

import assets.MorphiaObject;
import assets.ObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity("users")
public class UserModel
{
    @Id
    @JsonSerialize(using = ObjectIdSerializer.class)
    public ObjectId id;

    public String email;
    public String firstName;
    public String lastName;

    @play.data.format.Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date dateCreatedUtc;

    public CompanyModel company;

    public List<UserRole> roles = new ArrayList<>();

    public static List<UserModel> GetAll()
    {
        return MorphiaObject.datastore.find(UserModel.class).asList();
    }

    public static UserModel Get(ObjectId id)
    {
        return MorphiaObject.datastore.find(UserModel.class).filter("_id", id).get();
    }

    public void Save()
    {
        MorphiaObject.datastore.save(this);
    }
}
