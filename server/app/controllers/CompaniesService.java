package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.CompanyModel;
import models.ReportModel;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

public class CompaniesService extends Controller
{
    @BodyParser.Of(BodyParser.Json.class)
    public static Result Post()
    {
        JsonNode json = request().body().asJson();
        if(json == null)
        {
            return badRequest();
        }

        CompanyModel company = Json.fromJson(json, CompanyModel.class);

        if(company.id != null)
        {
            return badRequest("Use Put Method");
        }

        company.Save();
        return ok(Json.toJson(company));
    }
}