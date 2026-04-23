package hexlet.code;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
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
    public   static HikariConfig hikariConfig = new HikariConfig();

    static {
        var preferences = System.getenv();
        //hikariConfig.setJdbcUrl(preferences.getOrDefault("JDBC_DATABASE_URL", "jdbc:h2:mem:project"));
        hikariConfig.setJdbcUrl(getDataBaseUrl());
        try {
            Class.forName(getDriver());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        BaseRepository.dataSource = new HikariDataSource(hikariConfig);
    }

    public static void main(String[] args) throws IOException, SQLException {
        var app = getApp();
        var port = getPort();

        app.start(port);
        log.info("Starts on port {}", app.port());
    }

    public static Javalin getApp() throws IOException, SQLException {

        var sql = readResourceFile("schema.sql");
        log.info("INITIAL DATABASE STRUCTURE SETUP:\n{}", sql);

        try (var connection = BaseRepository.getConnection();
            var statement = connection.createStatement()) {
            statement.execute(sql);
        }

        var app = Javalin.create(javalinConfig -> {
            javalinConfig.bundledPlugins.enableDevLogging();
            javalinConfig.fileRenderer(new JavalinJte(createTemplateEngine()));

            javalinConfig.routes.before(handler ->
                    handler.contentType("text/html; charset=utf-8"));

            javalinConfig.routes.get(NamedRoutes.indexPath(),
                    handler -> handler.render("index.jte"));

            javalinConfig.routes.post(NamedRoutes.urlsPath(), UrlController::create);
            javalinConfig.routes.get(NamedRoutes.urlsPath(), UrlController::index);
            javalinConfig.routes.get(NamedRoutes.urlsIdPath(), UrlController::show);
            javalinConfig.routes.post(NamedRoutes.urlIdChecksPath(), UrlController::check);
        });

        return app;
    }

    public static int getPort() {
        String port = System.getenv().getOrDefault("PORT", "7070");
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
    private static String getDataBaseUrl() {
        if (System.getenv().get("JDBC_DATABASE_URL") == null) {
            return "jdbc:h2:mem:project";
        }

        var stringBilderUrl = new StringBuilder("jdbc:postgresql://");
        stringBilderUrl.append(System.getenv("HOST"));
        stringBilderUrl.append(":");
        stringBilderUrl.append(System.getenv("DB_PORT"));
        stringBilderUrl.append("/");
        stringBilderUrl.append(System.getenv("DATABASE"));
        stringBilderUrl.append("?password=");
        stringBilderUrl.append(System.getenv("PASSWORD"));
        stringBilderUrl.append("&user=");
        stringBilderUrl.append(System.getenv("USERNAME"));


        var port = System.getenv("HOST");
        var dbPort = System.getenv("DB_PORT");
        var dataBase = System.getenv("DATABASE");
        var password = System.getenv("PASSWORD");
        var userName = System.getenv("USERNAME");

        String url = String.format("jdbc:postgresql://%s:%s/%s?password=%s&user=%s",
                port, dbPort, dataBase, password, userName);

        return url;

        //return stringBilderUrl.toString();
    }

    private static String getDriver() {
        if (System.getenv().get("JDBC_DATABASE_URL") == null) {
            return "org.h2.Driver";
        } else {
            return "org.postgresql.Driver";
        }
    }
}
