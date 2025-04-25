package hexlet.code.repository;

import hexlet.code.model.Url;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UrlRepository extends BaseRepository {

    public static void save(Url url) throws SQLException {
        String sql = "INSERT INTO urls (name, created_at) VALUES (?, ?)";
        try(var connection = dataSource.getConnection();
            var prepareStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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

    public static Optional<Url> findById(Long id) throws SQLException {
        String sql = "SELECT * FORM urls WHERE id = ?";
        try(var connection = dataSource.getConnection();
            var prepareStatement = connection.prepareStatement(sql)) {
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

    public static List<Url> getEntities() throws SQLException {
        String sql = "SELECT * FROM urls";
        var listUrls = new ArrayList<Url>();

        try(var connection = dataSource.getConnection(); var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var name = resultSet.getString("name");
                var createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();

                listUrls.add(new Url(id, name, createdAt));
            }
        }
        return listUrls;
    }

    public static Optional<Url> findByName(String url) throws SQLException {
        String sql = "SELECT * FROM urls WHERE name = ?";
        try (var connection = dataSource.getConnection();
             var prepareStatement = connection.prepareStatement(sql)) {
            prepareStatement.setString(1, url);
            var resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {
                var id = resultSet.getLong("id");
                var name = resultSet.getString("name");
                var cheatedAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                return Optional.of(new Url(id,name,cheatedAt));
            }
        }
        return Optional.empty();
    }
}
