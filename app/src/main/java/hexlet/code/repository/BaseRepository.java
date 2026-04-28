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
 * @author Ryabinin Alexander
 * @version 1.0
 */
public class BaseRepository {
    public static HikariDataSource dataSource;

    /**
     * Returns the database connection for further use.
     *
     * @return Returns the connection to the database.
     * @throws SQLException - if you were unable to connect to the database
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
