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

    @Test
    public void indexPathTest() {
        var indexPathResult = NamedRoutes.indexPath();
        assertEquals(indexPathResult, "/");
    }

    @Test
    public void urlsIdPathTest() {
        var urlsIdPathResult = NamedRoutes.urlsIdPath(Long.MAX_VALUE);
        assertEquals(urlsIdPathResult, "/urls/" + Long.MAX_VALUE);
    }
}
