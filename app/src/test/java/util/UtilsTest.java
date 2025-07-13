package util;

import hexlet.code.util.Utils;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class UtilsTest {
    private final String testString= "Test text";

    @Test
    public void getNormalizeUrlTest() {
        // TO DO
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
}
