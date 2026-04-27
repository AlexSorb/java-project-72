package hexlet.code.repository;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Represents the base class of repositories.
 *
 * <p>
 * This class is a simple base class from which all repositories inherit. It stores a static
 * field with a database connection.
 * </p>
 */
public class BaseRepository {
    public static HikariDataSource dataSource;

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
