package hexlet.code.repository;

import hexlet.code.model.UrlCheck;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UrlChecksRepository extends BaseRepository {

    public static void save(UrlCheck urlCheck) throws SQLException {
        String sql = "INSERT INTO url_checks (url_id, status_code, h1, title, description, created_at) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (var connection = UrlChecksRepository.getConnection();
             var prepareStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            prepareStatement.setLong(1, urlCheck.getUrlId());
            prepareStatement.setInt(2, urlCheck.getStatusCode());
            prepareStatement.setString(3, urlCheck.getH1());
            prepareStatement.setString(4, urlCheck.getTitle());
            prepareStatement.setString(5, urlCheck.getDescription());

            var createAt = LocalDateTime.now();
            prepareStatement.setTimestamp(6, Timestamp.valueOf(createAt));
            prepareStatement.executeUpdate();

            var generatedKeys = prepareStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                urlCheck.setId(generatedKeys.getLong("id"));
                urlCheck.setCreatedAt(createAt);
            } else {
                throw new SQLException("Database has not returned an id after saving an entity");
            }
        }
    }

    public static List<UrlCheck> findById(Long urlId) throws SQLException {
        String sql = "SELECT * FROM url_checks WHERE url_id = ? ORDER BY created_at DESC";
        var result = new ArrayList<UrlCheck>();

        try (var connection = UrlChecksRepository.getConnection();
        var prepareStatement = connection.prepareStatement(sql)) {
            prepareStatement.setLong(1, urlId);
            var resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {

                var title = resultSet.getString("title");
                var h1 = resultSet.getString("h1");
                var description = resultSet.getString("description");
                var id = resultSet.getLong("id");
                var statusCode = resultSet.getInt("status_code");
                var createAt = resultSet.getTimestamp("created_at").toLocalDateTime();


                var urlCheck = new UrlCheck(statusCode, title, h1,description,urlId);
                urlCheck.setCreatedAt(createAt);
                urlCheck.setId(id);

                result.add(urlCheck);
            }
        }
        return result;
    }

    public static void removeAll() throws SQLException {
        String sql = "TRUNCATE TABLE url_checks RESTART IDENTITY";
        try (var connection = UrlRepository.getConnection();
             var statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }
}
