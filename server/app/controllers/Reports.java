package controllers;

import models.*;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class Reports extends Controller
{
    public static Result Get()
    {
        return ok(Json.toJson(ReportModel.GetAll()));
    }
}
