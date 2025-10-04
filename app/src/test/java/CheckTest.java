import hexlet.code.App;
import io.javalin.Javalin;
import kong.unirest.Unirest;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.sql.SQLException;

public class CheckTest {
    private static final String TEST_H1_DATA = "this is h1";
    private static final String TEST_TITLE_DATA = "this is title";
    private static final String TEST_DESCRIPTION_DATA = "this is description";

    private String currentUrl;
    private MockWebServer testServer;
    private String testingUrl;
    private Javalin app;

    @BeforeAll
    void initialisation() throws IOException, SQLException {
        app = App.getApp();
        currentUrl = String.format("http://localhost:%d", App.getPort());

        // Создание тестируемого сервера
        testServer = new MockWebServer();
        testingUrl = testServer.url("/").toString();
        var response = new MockResponse();

        response.setBody(String.format("<h1>%s</h1>", TEST_H1_DATA));
        response.setBody(String.format("<title>%s</title>", TEST_TITLE_DATA));
        response.setBody(String.format("<meta name=\"description\" content=\"%s\">", TEST_DESCRIPTION_DATA));


        testServer.enqueue(response);
        testServer.start();

    }

    @Test
    void simpleTest() {
        var request = Unirest.get(currentUrl).asString();
        assertEquals(200, request.getStatus());
        assertNotNull(request.getBody());
    }

    @Test
    void addNewUrlForChecking() {
        var responseAddUrl = Unirest.post(currentUrl).body("url=" + testingUrl).asString();
        var requestAddedUrl = Unirest.get(currentUrl + "/urls").asString();

        assertEquals(201, responseAddUrl.getStatus());
        assertTrue(requestAddedUrl.getBody().contains(testingUrl));

        var responseCheckUrl = Unirest.post(currentUrl +"/1/" + "/checks").body("id=" + 1).asString();
        var requestCheckUrl = Unirest.get(currentUrl + "/1/").asString();

        assertEquals(201, responseCheckUrl.getStatus());
        assertTrue(requestCheckUrl.getBody().contains(TEST_H1_DATA));
        assertTrue(requestCheckUrl.getBody().contains(TEST_TITLE_DATA));
        assertTrue(requestCheckUrl.getBody().contains(TEST_DESCRIPTION_DATA));

    }

    @AfterAll
    void close() throws IOException {
        testServer.close();
        app.stop();
    }

}
