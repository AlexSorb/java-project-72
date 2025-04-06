package hexlet.code;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import hexlet.code.repository.BaseRepository;
import io.javalin.Javalin;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import io.javalin.rendering.template.JavalinJte;
import gg.jte.resolve.ResourceCodeResolver;

public class App {

    public static final String PORT_NAME = "PORT";
    public static final String DEFAULT_PORT = "7070";
    public static final String JDBC_URL_DEFAULT = "jdbc:h2:mem:project";
    public static final String JDBC_URL_NAME = "JDBC_DATABASE_URL";


    public static void main(String[] args) {

        var app = getApp();

        app.start(getPort(PORT_NAME));
    }

    public static Javalin getApp() {

        var hikariConfig = new HikariConfig();

        String dbName = System.getenv().getOrDefault(JDBC_URL_NAME, JDBC_URL_DEFAULT);
        hikariConfig.setJdbcUrl(dbName);


        BaseRepository.dataSource = new HikariDataSource(hikariConfig);

        var app = Javalin.create(javalinConfig -> {
            javalinConfig.bundledPlugins.enableDevLogging();
            javalinConfig.fileRenderer(new JavalinJte(createTemplateEngine()));
        });


        app.get("/", handler -> handler.result("Hello World"));
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
}
