package hexlet.code.util;

import com.zaxxer.hikari.HikariConfig;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DatabaseConfig {

    @Getter
    private static HikariConfig config = new HikariConfig();

    static {
        try {
            Class.forName(getDriver());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        log.info(getDataBaseUrl());
        config.setJdbcUrl(getDataBaseUrl());

    }

    private static String getDriver() {
        if (System.getenv().get("JDBC_DATABASE_URL") == null) {
            return "org.h2.Driver";
        } else {
            return "org.postgresql.Driver";
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

}
