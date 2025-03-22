package hexlet.code;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import hexlet.code.repository.BaseRepository;
import io.javalin.Javalin;

public class App {

    public static final String PORT_NAME = "PORT";
    public static final String DEFAULT_PORT = "7070";
    public static final String JDBC_URL = "jdbc:h2:mem:project";

    public static void main(String[] args) {

        var app = getApp();

        app.get("/", handler -> handler.result("Hello World"));

        app.start(getPort(PORT_NAME));
    }

    public static Javalin getApp() {

        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(JDBC_URL);
        BaseRepository.dataSource = new HikariDataSource(hikariConfig);;


        return Javalin.create(javalinConfig -> {
            javalinConfig.bundledPlugins.enableDevLogging();
        });
    }

    public static int getPort(String portName) {
        String port = System.getenv().getOrDefault(portName, DEFAULT_PORT);
        return Integer.parseInt(port);
    }
}
