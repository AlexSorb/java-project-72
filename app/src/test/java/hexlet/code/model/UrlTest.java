package hexlet.code.model;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UrlTest {

    @Test
    public void initializationUrlOnlyName() {
        String testUrl = "https://www.example.com/";

        var cut = new Url(testUrl);

        assertEquals(testUrl, cut.getName(), "");
        assertNull(cut.getId(), "The id parameter must not be initialized.");
        assertNull(cut.getCreatedAt(), "The createdAt parameter must not be initialized.");
    }
}
