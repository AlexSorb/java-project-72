package hexlet.code.util;

import hexlet.code.util.Utils;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UtilsTest {
    private final String testString= "Test text";

    private final String fullUrlWithoutPort = "https://example.com/api/v1/users?status=active&sort=desc";
    private final String shortUrlWithoutPort = "https://example.com/";


    private final String fullUrlWithPort = "http://localhost:3000/auth/register";
    private final String shortUrlWithPort = "http://localhost:3000";

    private final String rightUrlWithoutPort = "https://example.com";
    private final String rightUrlWithPort = "http://localhost:3000";


    @Test
    public void getNormalizeUrlTest() {
        // TO DO
    }

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
        var resultFullUrlWithoutPort = Utils.getNormalizeUrl(fullUrlWithoutPort);
        assertEquals(rightUrlWithoutPort, resultFullUrlWithoutPort);

        var resultShortUrlWithoutPort = Utils.getNormalizeUrl(shortUrlWithoutPort);
        assertEquals(rightUrlWithoutPort, resultShortUrlWithoutPort);
    }

    @Test
    public void inputCorrectUrlWithPortTest() throws MalformedURLException, URISyntaxException {
        var resultFullUrlWithPort = Utils.getNormalizeUrl(fullUrlWithPort);
        assertEquals(rightUrlWithPort, resultFullUrlWithPort);

        var resultShortUrlWithPort = Utils.getNormalizeUrl(shortUrlWithPort);
        assertEquals(rightUrlWithPort, resultShortUrlWithPort);
    }

    @Test
    public void getDescriptionTest() {
        String body = "<meta name=\"description\" content=\"" + testString + "\">";
        var getTeg = Utils.getDescription(body);
        assertEquals(getTeg, testString);

        String wrongBody = "<meta name=\"anotherName\" content=\"should not match\">";
        var resultAnotherName = Utils.getDescription(wrongBody);
        assertEquals(resultAnotherName, "");

        String emptyBody = "";
        var resultEmptyBody = Utils.getDescription(emptyBody);
        assertEquals(resultEmptyBody, "");

        var resultNullString = Utils.getDescription(null);
        assertEquals(resultNullString, "");
    }

    @Test
    public void getDescriptionTest2() {

    }
}
