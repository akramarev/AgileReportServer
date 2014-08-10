package models;

import assets.ObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Entity;
import controllers.MorphiaObject;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

@Entity("users")
public class UserModel
{
    @Id
    @JsonSerialize(using = ObjectIdSerializer.class)
    public ObjectId id;

    public String email;

    @JsonSerialize(using = ObjectIdSerializer.class)
    public ObjectId companyId;

    @play.data.format.Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date dateCreatedUtc;

    public static List<UserModel> GetAll()
    {
        return MorphiaObject.datastore.find(UserModel.class).asList();
    }

    public static UserModel Get(ObjectId id)
    {
        return MorphiaObject.datastore.find(UserModel.class).filter("_id", id).get();
    }
}
