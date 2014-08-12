package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.report;

public class ReportPage extends Controller {

    public static Result index() {
        return ok(report.render());
    }

}