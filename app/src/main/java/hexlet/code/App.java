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
import java.util.Properties;
import java.util.stream.Collectors;

@Slf4j
public class App {

    public static void main(String[] args) throws IOException, SQLException {
        var app = getApp();
        var port = getPort();

        app.start(port);
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

        app.get(NamedRoutes.indexPath(), handler -> {
            handler.render("index.jte");
        });

        app.post(NamedRoutes.urlsPath(), UrlController::create);
        app.get(NamedRoutes.urlsPath(), UrlController::index);
        app.get(NamedRoutes.urlsIdPath(), UrlController::show);
        app.post(NamedRoutes.urlIdChecksPath(), UrlController::check);

        return app;
    }

    public static int getPort() throws IOException {
        var inputLoader = App.class.getClassLoader().getResourceAsStream("config.properties");
        var properties = new Properties();
        properties.load(inputLoader);

        var portName = properties.getProperty("port_name");
        var defaultPort = properties.getProperty("default_port");

        var port = System.getenv().getOrDefault(portName, defaultPort);
        return Integer.parseInt(port);
    }

    private static TemplateEngine createTemplateEngine() {
        ClassLoader classLoader = App.class.getClassLoader();
        ResourceCodeResolver codeResolver = new ResourceCodeResolver("templates", classLoader);
        return  TemplateEngine.create(codeResolver, ContentType.Html);
    }

    private static String readResourceFile(String fileName) throws IOException {

        var inputStream = App.class.getClassLoader().getResourceAsStream(fileName);

        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        }
    }

}
