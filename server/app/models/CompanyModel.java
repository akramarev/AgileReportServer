package models;

import assets.ObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Entity;
import assets.MorphiaObject;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

@Entity("companies")
public class CompanyModel
{
    @Id
    @JsonSerialize(using = ObjectIdSerializer.class)
    public ObjectId id;

    public String name;

    @play.data.format.Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date dateCreatedUtc;

    public static List<CompanyModel> GetAll()
    {
        return MorphiaObject.datastore.find(CompanyModel.class).asList();
    }

    public static CompanyModel Get(ObjectId id)
    {
        return MorphiaObject.datastore.find(CompanyModel.class).filter("_id", id).get();
    }
}
