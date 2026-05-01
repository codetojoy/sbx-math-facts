package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.MathFacts;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.MathFactsService;

import javax.inject.Inject;

public class HomeController extends Controller {

    private final MathFactsService mathFactsService;

    @Inject
    public HomeController(MathFactsService mathFactsService) {
        this.mathFactsService = mathFactsService;
    }

    public Result index() {
        return ok(views.html.index.render());
    }

    public Result check(Http.Request request) {
        JsonNode body = request.body().asJson();
        if (body == null || !body.has("n") || !body.get("n").canConvertToLong()) {
            return badRequest(Json.newObject().put("error", "expected JSON body with integer field 'n'"));
        }
        long n = body.get("n").asLong();
        MathFacts facts = mathFactsService.factsFor(n);
        return ok(Json.toJson(facts));
    }
}
