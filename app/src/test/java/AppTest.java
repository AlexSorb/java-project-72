import hexlet.code.App;
import hexlet.code.model.Url;
import hexlet.code.repository.UrlRepository;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    private static Javalin app;


    @BeforeEach
    public final void generate() throws SQLException, IOException {
        app = App.getApp();
        UrlRepository.removeAll();
    }

    @Test
    public void mainPageTest() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("Бесплатно проверяйте сайты на SEO пригодность");
        });
    }

    @Test
    public void urlsPageTest() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls");
            assertThat(response.code()).isEqualTo(200);

        });
    }

    @Test
    public void urlPageTest() {
        JavalinTest.test(app, (server, client) -> {
            var url = new Url("https://www.example.com/");
            UrlRepository.save(url);
            var response = client.get("/urls/" + url.getId());
            assertThat(response.code()).isEqualTo(200);
        });
    }

    @Test
    public void urlNotFoundTest() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls/" + Integer.MAX_VALUE);
            assertThat(response.code()).isEqualTo(404);
        });
    }

    @Test
    public void createUrlTest() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=https://www.example.com/";
            var response = client.post("/urls", requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("https://www.example.com");
        });
    }

}
