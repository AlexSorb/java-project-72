package hexlet.code;

import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {

        var app = getApp();

        app.get("/", handler -> handler.result("Hello World"));

        app.start(7070);
    }

    public static Javalin getApp() {
        return Javalin.create(javalinConfig -> {
            javalinConfig.bundledPlugins.enableDevLogging();
        });
    }
}
