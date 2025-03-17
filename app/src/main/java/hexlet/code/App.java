package hexlet.code;

import io.javalin.Javalin;

public class App {

    public static final String PORT_NAME = "PORT";
    public static final String DEFAULT_PORT = "7070";

    public static void main(String[] args) {

        var app = getApp();

        app.get("/", handler -> handler.result("Hello World"));

        app.start(getPort(PORT_NAME));
    }

    public static Javalin getApp() {
        return Javalin.create(javalinConfig -> {
            javalinConfig.bundledPlugins.enableDevLogging();
        });
    }

    public static int getPort(String portName) {
        String port = System.getenv().getOrDefault(portName, DEFAULT_PORT);
        return Integer.parseInt(port);
    }
}
