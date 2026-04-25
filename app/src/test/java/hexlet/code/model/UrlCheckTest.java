package hexlet.code.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The class contains tests of the UrlCheck class.
 */

public class UrlCheckTest {

    /**
     * Check that the UrlCheck constructor does not initialize other fields.
     */
    @Test
    public void initializationUrlCheck() {
        int testStatusCode = 200;
        String testTitle = "Test line of the title block";
        String testH1 = "Test line of the h1 block";
        String testDescription = "Test description";
        long testId = 1L;

        var cut = new UrlCheck(testStatusCode, testTitle, testH1, testDescription, testId);

        assertEquals(testStatusCode, cut.getStatusCode());
        assertEquals(testTitle, cut.getTitle());
        assertEquals(testH1, cut.getH1());
        assertEquals(testDescription, cut.getDescription());
        assertEquals(testId, cut.getUrlId());

        assertNull(cut.getId());
        assertNull(cut.getCreatedAt());
    }
}
