package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.login;

public class LoginPage extends Controller {

    public static Result index() {
        return ok(login.render());
    }

}