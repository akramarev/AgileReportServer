package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.ReportModel;
import models.UserModel;
import org.bson.types.ObjectId;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Date;

public class ReportsService extends Controller
{
    public static ObjectId LoggedInUserId = new ObjectId("53e8e868aeded062518b8849");

    public static Result Get()
    {
        return ok(Json.toJson(ReportModel.GetMany(LoggedInUserId)));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result Post()
    {
        JsonNode json = request().body().asJson();
        if(json == null)
        {
            return badRequest();
        }

        ReportModel report = Json.fromJson(json, ReportModel.class);

        if(report.id != null)
        {
            return badRequest("Use Put Method");
        }

        // assign report to logged in user
        report.user = UserModel.Get(LoggedInUserId);
        report.dateCreatedUtc = report.dateUpdatedUtc = report.dateCompletedUtc = new Date();

        report.Save();

        return ok(Json.toJson(report));
    }
}
