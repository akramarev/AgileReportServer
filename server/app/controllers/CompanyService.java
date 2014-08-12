package controllers;

import models.CompanyModel;
import org.bson.types.ObjectId;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class CompanyService extends Controller
{
    public static Result Get(String id)
    {
        CompanyModel c = CompanyModel.Get(new ObjectId(id));
        if (c != null)
        {
            return ok(Json.toJson(c));
        }
        else
        {
            return notFound();
        }
    }
}
