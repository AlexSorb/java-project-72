package hexlet.code.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UtilsTest {
    private static final String TEST_STRING = "Test text";

    private static final String FULL_URL_WITHOUT_PORT = "https://example.com/api/v1/users?status=active&sort=desc";
    private static final String SHORT_URL_WITHOUT_PORT = "https://example.com/";

    private static final String FULL_URL_WITH_PORT = "http://localhost:3000/auth/register";
    private static final String SHORT_URL_WITH_PORT = "http://localhost:3000";

    private static final String RIGHT_URL_WITHOUT_PORT = "https://example.com";
    private static final String RIGHT_URL_WITH_PORT = "http://localhost:3000";


    @Test
    public void inputNullAsUrlTest() {
        var exception = assertThrows(NullPointerException.class, () -> Utils.getNormalizeUrl(null));
        assertEquals("The url cannot be null", exception.getMessage());
    }

    @Test
    public void inputNotUrlTest() throws MalformedURLException, URISyntaxException {
        String ramdomString = "This is random string";
        assertThrows(URISyntaxException.class, () -> Utils.getNormalizeUrl(ramdomString));
    }

    @Test
    public void inputCorrectUrlWithoutPortTest() throws MalformedURLException, URISyntaxException {
        var resultFullUrlWithoutPort = Utils.getNormalizeUrl(FULL_URL_WITHOUT_PORT);
        assertEquals(RIGHT_URL_WITHOUT_PORT, resultFullUrlWithoutPort);

        var resultShortUrlWithoutPort = Utils.getNormalizeUrl(SHORT_URL_WITHOUT_PORT);
        assertEquals(RIGHT_URL_WITHOUT_PORT, resultShortUrlWithoutPort);
    }

    @Test
    public void inputCorrectUrlWithPortTest() throws MalformedURLException, URISyntaxException {
        var resultFullUrlWithPort = Utils.getNormalizeUrl(FULL_URL_WITH_PORT);
        assertEquals(RIGHT_URL_WITH_PORT, resultFullUrlWithPort);

        var resultShortUrlWithPort = Utils.getNormalizeUrl(SHORT_URL_WITH_PORT);
        assertEquals(RIGHT_URL_WITH_PORT, resultShortUrlWithPort);
    }

    @Test
    public void getDescriptionTest() {
        String body = "<meta name=\"description\" content=\"" + TEST_STRING + "\">";
        var getTeg = Utils.getDescription(body);
        assertEquals(TEST_STRING, getTeg);

        String wrongBody = "<meta name=\"anotherName\" content=\"should not match\">";
        var resultAnotherName = Utils.getDescription(wrongBody);
        assertTrue(resultAnotherName.isEmpty());

        String emptyBody = "";
        var resultEmptyBody = Utils.getDescription(emptyBody);
        assertTrue(resultEmptyBody.isEmpty());

        var resultNullString = Utils.getDescription(null);
        assertTrue(resultNullString.isEmpty());
    }

}
