package main;

import com.google.gson.Gson;
import entidades.Sensor;
import freemarker.template.Configuration;
import services.JsonTransformer;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class RutasSpark {
    public void iniciarSpark() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
        cfg.setClassForTemplateLoading(Main.class, "/templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(cfg);

        List<Sensor> humedadList = new ArrayList<>();

        get("/", (request, response) -> {
            response.redirect("/graficos");
            return "";
        });

        get("/graficos", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            return new ModelAndView(attributes, "index.html");
        }, freeMarkerEngine);


        get("/json/temperatura", (request, response) -> {
            return Main.temperaturaQueue;
        }, JsonTransformer.json());

        get("/json/humedad", (request, response) -> {
            return Main.humedadQueue;
        }, JsonTransformer.json());

    }
}
