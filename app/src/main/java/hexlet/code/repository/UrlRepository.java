package hexlet.code.repository;

import hexlet.code.model.Url;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class is used to interact with URL entities and the database.
 * @author Ryabinin Alexander
 * @version 1.0
 */
public class UrlRepository extends BaseRepository {
    private static final String SAVE_SQU_QUERY = "INSERT INTO urls (name, created_at) VALUES (?, ?)";
    private static final String FIND_BY_ID_SQL_QUERY = "SELECT * FROM urls WHERE id = ?";
    private static final String GET_ENTITIES_SQL_QUERY = "SELECT * FROM urls";
    private static final String FIND_BY_NAME_SQL_QUERY = "SELECT * FROM urls WHERE name = ?";

    /**
     * Saves the URL to the database.
     * @param url Saved URL
     * @throws SQLException If the database did not return the id of the stored URL
     */
    public static void save(Url url) throws SQLException {
        try (var connection = UrlRepository.getConnection();
            var prepareStatement = connection.prepareStatement(SAVE_SQU_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setString(1, url.getName());

            var createAt = LocalDateTime.now();
            prepareStatement.setTimestamp(2, Timestamp.valueOf(createAt));

            prepareStatement.executeUpdate();
            var generatedKeys = prepareStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                url.setId(generatedKeys.getLong("id"));
                url.setCreatedAt(createAt);
            } else {
                throw new SQLException("Database has not returned an id after saving an entity");
            }
        }
    }

    /**
     * Finds a URL in the database by the entered ID
     * @param id ID of the searched URL
     * @return <code>Optional</code> with the found URL or an empty <code>Optional</code>
     * @throws SQLException If the request did not work
     */
    public static Optional<Url> findById(Long id) throws SQLException {
        try (var connection = UrlRepository.getConnection();
            var prepareStatement = connection.prepareStatement(FIND_BY_ID_SQL_QUERY)) {
            prepareStatement.setLong(1, id);

            var resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                var name = resultSet.getString("name");
                var createAt = resultSet.getTimestamp("created_at").toLocalDateTime();

                var url = new Url(name);
                url.setCreatedAt(createAt);
                url.setId(id);

                return Optional.of(url);
            } else {
                return Optional.empty();
            }
        }
    }

    /**
     * Returns all URLs from the database.
     * @return <code>List</code> of all URLs.
     * @throws SQLException If the request did not work
     */
    public static List<Url> getEntities() throws SQLException {
        var listUrls = new ArrayList<Url>();

        try (var connection = UrlRepository.getConnection(); var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery(GET_ENTITIES_SQL_QUERY);
            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var name = resultSet.getString("name");
                var createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();

                listUrls.add(new Url(id, name, createdAt));
            }
        }
        return listUrls;
    }

    /**
     * Finds URL by name.
     * @param url URL name.
     * @return <code>Optional</code> with the found URL or an empty <code>Optional</code>
     * @throws SQLException If the request did not work
     */
    public static Optional<Url> findByName(String url) throws SQLException {
        try (var connection = UrlRepository.getConnection();
             var prepareStatement = connection.prepareStatement(FIND_BY_NAME_SQL_QUERY)) {
            prepareStatement.setString(1, url);
            var resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {
                var id = resultSet.getLong("id");
                var name = resultSet.getString("name");
                var cheatedAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                return Optional.of(new Url(id, name, cheatedAt));
            }
        }
        return Optional.empty();
    }
}
