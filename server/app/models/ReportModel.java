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

@Entity("reports")
public class ReportModel
{
    @Id
    @JsonSerialize(using = ObjectIdSerializer.class)
    public ObjectId id;

    public UserModel user;

    public ReportStatus status;

    @play.data.format.Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date dateCreatedUtc;

    @play.data.format.Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date dateUpdatedUtc;

    @play.data.format.Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date dateCompletedUtc;

    public List<ReportBodyElement> body = new ArrayList<>();

    public static List<ReportModel> GetAll()
    {
        return MorphiaObject.datastore.find(ReportModel.class).asList();
    }

    public static ReportModel Get(ObjectId id)
    {
        return MorphiaObject.datastore.find(ReportModel.class).filter("_id", id).get();
    }

    public void Save()
    {
        MorphiaObject.datastore.save(this);
    }
}
