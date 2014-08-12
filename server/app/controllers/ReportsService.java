package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.ReportModel;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

public class ReportsService extends Controller
{
    public static Result Get()
    {
        return ok(Json.toJson(ReportModel.GetAll()));
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

        report.Save();

        return ok(Json.toJson(report));
    }
}
