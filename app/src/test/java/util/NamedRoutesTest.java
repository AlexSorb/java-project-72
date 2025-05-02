package util;

import hexlet.code.util.NamedRoutes;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NamedRoutesTest {

    @Test
    public void urlsPathTest() {

        var urlsPathResult = NamedRoutes.urlsPath();
        assertEquals(urlsPathResult, "/urls");
    }
}
