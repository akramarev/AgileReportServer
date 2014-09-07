package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.BasicAuthModel;
import models.TokenModel;
import models.UserModel;
import org.apache.commons.lang3.StringUtils;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

public class TokensService extends Controller
{
    @BodyParser.Of(BodyParser.Json.class)
    public static Result Post()
    {
        JsonNode json = request().body().asJson();
        if(json == null)
        {
            return badRequest();
        }

        BasicAuthModel auth = Json.fromJson(json, BasicAuthModel.class);

        if (StringUtils.isEmpty(auth.email))
        {
            return badRequest("Email Required");
        }

        if (StringUtils.isEmpty(auth.email))
        {
            return badRequest("Password Required");
        }

        UserModel u = UserModel.Get(auth.email);
        if (u == null)
        {
            return unauthorized();
        }

        if (!u.IsPasswordValid(auth.password))
        {
            return unauthorized();
        }

        TokenModel t = TokenModel.Generate(u);
        t.Save();

        return ok(Json.toJson(t));
    }
}
