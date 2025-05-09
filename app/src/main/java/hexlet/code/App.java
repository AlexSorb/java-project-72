package hexlet.code;

import hexlet.code.util.NamedRoutes;
import hexlet.code.controller.UrlController;
import hexlet.code.repository.BaseRepository;
import io.javalin.Javalin;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import io.javalin.rendering.template.JavalinJte;
import gg.jte.resolve.ResourceCodeResolver;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.stream.Collectors;

@Slf4j
public class App {

    public static final String PORT_NAME = "PORT";
    public static final String DEFAULT_PORT = "7071";


    public static void main(String[] args) throws IOException, SQLException {
        var app = getApp();

        app.start(getPort(PORT_NAME));
    }

    public static Javalin getApp() throws IOException, SQLException {
        var sql = readResourceFile("schema.sql");

        log.info(sql);
        try (var connection = BaseRepository.getConnection();
            var statement = connection.createStatement()) {
            statement.execute(sql);
        }

        var app = Javalin.create(javalinConfig -> {
            javalinConfig.bundledPlugins.enableDevLogging();
            javalinConfig.fileRenderer(new JavalinJte(createTemplateEngine()));
        });

        app.before(handler -> {
            handler.contentType("text/html; charset=utf-8");
        });

        // Обработчик событий
        app.get("/", handler -> {
            handler.render("index.jte");
        });

        app.post(NamedRoutes.urlsPath(), UrlController::create);
        app.get(NamedRoutes.urlsPath(), UrlController::index);
        app.get("/urls/{id}", UrlController::show);
        app.post("/urls/{id}/checks", UrlController::check);


        return app;
    }

    public static int getPort(String portName) {
        String port = System.getenv().getOrDefault(portName, DEFAULT_PORT);
        return Integer.parseInt(port);
    }

    private static TemplateEngine createTemplateEngine() {
        ClassLoader classLoader = App.class.getClassLoader();
        ResourceCodeResolver codeResolver = new ResourceCodeResolver("templates", classLoader);
        TemplateEngine templateEngine = TemplateEngine.create(codeResolver, ContentType.Html);
        return templateEngine;
    }

    private static String readResourceFile(String fileName) throws IOException {

        var inputStream = App.class.getClassLoader().getResourceAsStream(fileName);

        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        }
    }

}
