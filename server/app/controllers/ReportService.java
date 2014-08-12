package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.ReportModel;
import org.bson.types.ObjectId;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

public class ReportService extends Controller
{
    public static Result Get(String id)
    {
        ReportModel report = ReportModel.Get(new ObjectId(id));
        if (report != null)
        {
            return ok(Json.toJson(report));
        }
        else
        {
            return notFound();
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result Put(String id)
    {
        ReportModel originalReport = ReportModel.Get(new ObjectId(id));
        if(originalReport == null)
        {
            return notFound();
        }

        JsonNode json = request().body().asJson();
        if(json == null)
        {
            return badRequest();
        }

        ReportModel report = Json.fromJson(json, ReportModel.class);
        report.id = new ObjectId(id);
        report.Save();

        return ok(Json.toJson(report));
    }
}
