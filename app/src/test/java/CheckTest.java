import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;

public class CheckTest {

    private static MockWebServer testServer;
    private static HttpUrl baseUrl;


    private String body = "<meta name=\"description\" content=\"краткое описание страницы\">\n" +
            "<title>Example Domain<title>\n<h1>Заголово</h1>";

    @BeforeAll
    public void init() throws IOException {
        testServer = new MockWebServer();
        var response = new MockResponse()
                .addHeader("Content-Type", "text/html; charset=utf-8").setResponseCode(200).setBody(body);

        testServer.start();
        baseUrl = testServer.url("/");
    }


    @AfterAll
    public void shutdown() throws IOException {
        testServer.shutdown();
    }
}
