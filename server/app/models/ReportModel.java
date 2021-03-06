package models;

import assets.MorphiaObject;
import assets.ObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
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

    @Indexed
    public ReportStatus status;

    @play.data.format.Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date dateCreatedUtc;

    @play.data.format.Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date dateUpdatedUtc;

    @play.data.format.Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date dateCompletedUtc;

    public List<ReportBodyElement> body = new ArrayList<>();

    public static List<ReportModel> GetMany(ObjectId userId)
    {
        return MorphiaObject.datastore.find(ReportModel.class)
                .filter("user._id", userId)
                .filter("status !=", ReportStatus.Archived.toString())
                .order("-dateUpdatedUtc")
                .asList();
    }

    public static ReportModel Get(ObjectId id)
    {
        return MorphiaObject.datastore.find(ReportModel.class).filter("_id", id).get();
    }

    public void Save()
    {
        MorphiaObject.datastore.save(this);
    }

    public void Delete()
    {
        MorphiaObject.datastore.delete(this);
    }
}
