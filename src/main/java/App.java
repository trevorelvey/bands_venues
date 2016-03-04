import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("bands", Band.all());
      model.put("venues", Venue.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/new-band", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Band band = new Band(request.queryParams("name-band"));
      band.save();
      model.put("band", band);
      response.redirect("/");
      return null;
    });

    post("/delete-band", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Band band = Band.find(Integer.parseInt(request.queryParams("id-band")));
      band.delete();
      model.put("band", band);
      response.redirect("/");
      return null;
    });

    post("/update-band", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String newName = request.queryParams("newName-band");
      Band band = Band.find(Integer.parseInt(request.queryParams("id-band")));
      band.update(newName);
      model.put("band", band);
      response.redirect("/");
      return null;
    });

    get("/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Band thisBand = Band.find(Integer.parseInt(request.params("id")));
      model.put("band", thisBand);
      model.put("bands", Band.all());
      model.put("template", "templates/band.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/:id/new-venue", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Band thisBand = Band.find(Integer.parseInt(request.params("id")));
      Band band = Band.find(Integer.parseInt(request.queryParams("id-band")));
      Venue venue = new Venue(request.queryParams("name-venue"));
      venue.save();
      venue.assignBand(band.getId());
      response.redirect("/" + thisBand.getId());
      return null;
    });

    post("/:id/delete-venue", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Band thisBand = Band.find(Integer.parseInt(request.params("id")));
      Venue venue = Venue.find(Integer.parseInt(request.queryParams("id-venue")));
      venue.delete();
      response.redirect("/" + thisBand.getId());
      return null;
    });

    post("/:id/update-venue", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Band thisBand = Band.find(Integer.parseInt(request.params("id")));
      String newName = request.queryParams("newName-venue");
      Venue venue = Venue.find(Integer.parseInt(request.queryParams("id-venue")));
      venue.update(newName);
      response.redirect("/" + thisBand.getId());
      return null;
    });
  }
}
