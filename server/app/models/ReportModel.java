package models;

import assets.ObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Entity;
import controllers.MorphiaObject;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

@Entity("reports")
public class ReportModel
{
    @Id
    @JsonSerialize(using = ObjectIdSerializer.class)
    public ObjectId id;

    @JsonSerialize(using = ObjectIdSerializer.class)
    public ObjectId companyId;

    @JsonSerialize(using = ObjectIdSerializer.class)
    public ObjectId userId;

    @play.data.format.Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date dateCreatedUtc;

    public static List<ReportModel> GetAll()
    {
        return MorphiaObject.datastore.find(ReportModel.class).asList();
    }

    public static ReportModel Get(ObjectId id)
    {
        return MorphiaObject.datastore.find(ReportModel.class).filter("_id", id).get();
    }
}
