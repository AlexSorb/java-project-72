package hexlet.code.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * <p>
 * A test class to check the correct operation of the <code>Url</code> model.
 * </p>
 */

public class UrlTest {

    public static final String TEST_URL = "https://www.example.com/";

    /**
     * <p>
     * Check that a single-parameter Url constructor does not initialize other fields.
     * </p>
     */
    @Test
    public void initializationUrlOnlyName() {
        var cut = new Url(TEST_URL);

        assertEquals(TEST_URL, cut.getName(), "");
        assertNull(cut.getId(), "The id parameter must not be initialized.");
        assertNull(cut.getCreatedAt(), "The createdAt parameter must not be initialized.");
    }
}
