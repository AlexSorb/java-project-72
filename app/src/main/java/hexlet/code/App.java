package hexlet.code;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import hexlet.code.Util.Date;
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
import java.util.stream.Collectors;

@Slf4j
public class App {

    public static final String PORT_NAME = "PORT";
    public static final String DEFAULT_PORT = "7070";
    public static final String JDBC_URL_DEFAULT = "jdbc:h2:mem:project";
    public static final String JDBC_URL_NAME = "JDBC_DATABASE_URL";

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        config.setJdbcUrl(Date.getBDName());
        config.setUsername(Date.getUserName());
        config.setPassword( Date.getPassword() );
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        ds = new HikariDataSource( config );
    }



    public static void main(String[] args) throws IOException {
        var app = getApp();

        app.start(getPort(PORT_NAME));
    }


    public static Javalin getApp() throws IOException {

        BaseRepository.dataSource = new HikariDataSource(config);
        var sql = readResourceFile("schema.sql");

        log.info(sql);


        var app = Javalin.create(javalinConfig -> {
            javalinConfig.bundledPlugins.enableDevLogging();
            javalinConfig.fileRenderer(new JavalinJte(createTemplateEngine()));
        });



        // Обработчик событий
        app.get("/", handler -> {
            handler.render("index.jte");
        });
        return app;
    }

    public static int getPort(String portName) {
        String port = System.getenv().getOrDefault(portName, DEFAULT_PORT);
        return Integer.parseInt(port);
    }

    // Создание шаблона jte
    private static TemplateEngine createTemplateEngine() {
        ClassLoader classLoader = App.class.getClassLoader();
        ResourceCodeResolver codeResolver = new ResourceCodeResolver("templates", classLoader);
        TemplateEngine templateEngine = TemplateEngine.create(codeResolver, ContentType.Html);
        return templateEngine;
    }


    // Загрузка данных из ресурсного файла
    private static String readResourceFile(String fileName) throws IOException {

        var inputStream = App.class.getClassLoader().getResourceAsStream(fileName);

        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        }
    }
}
