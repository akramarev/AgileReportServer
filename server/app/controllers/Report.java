package controllers;

import models.ReportModel;
import org.bson.types.ObjectId;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class Report extends Controller
{
    public static Result Get(String id)
    {
        ReportModel r = ReportModel.Get(new ObjectId(id));
        if (r != null)
        {
            return ok(Json.toJson(r));
        }
        else
        {
            return notFound();
        }
    }
}
