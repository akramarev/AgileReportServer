package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.ReportFormatModel;
import org.bson.types.ObjectId;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class ReportFormatsService extends Controller
{
    public static Result Get()
    {
        return ok(Json.toJson(ReportFormatModel.GetMany(new ObjectId("53e8e7f7aeded062518b8848"))));
    }
}
