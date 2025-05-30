package hexlet.code.repository;

import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UrlChecksRepository extends BaseRepository {

    public static void save(UrlCheck urlCheck) throws SQLException {
        String sql = "INSERT INTO url_checks (url_id, status_code, h1, title, description, created_at) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (var connection = UrlRepository.getConnection();
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

}
