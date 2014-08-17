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

@Entity("report_formats")
public class ReportFormatModel
{
    @Id
    @JsonSerialize(using = ObjectIdSerializer.class)
    public ObjectId id;

    public CompanyModel company;

    @play.data.format.Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date dateCreatedUtc;

    @play.data.format.Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date dateUpdatedUtc;

    public List<ReportBodyElement> body = new ArrayList<>();

    public static List<ReportFormatModel> GetMany(ObjectId companyId)
    {
        return MorphiaObject.datastore.find(ReportFormatModel.class).filter("company._id", companyId).asList();
    }

    public void Save()
    {
        MorphiaObject.datastore.save(this);
    }
}
