package hexlet.code.repository;

import com.zaxxer.hikari.HikariDataSource;
import hexlet.code.util.DatabaseConfig;

import java.sql.Connection;
import java.sql.SQLException;

public class BaseRepository {
    public static HikariDataSource dataSource =
            new HikariDataSource(DatabaseConfig.getConfig());

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
