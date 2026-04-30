package hexlet.code.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NamedRoutesTest {

    private static final String INDEX_PATH = "/";
    private static final String URLS_PATH = "/urls";
    private static final String URL_ID_PATH = "/urls/" + Long.MAX_VALUE;
    private static final String URL_ID_PATH_TEMPLATE = "/urls/{id}";
    private static final String URL_ID_CHECK_PATH_TEMPLATE = "/urls/{id}/checks";

    @Test
    public void indexPathTest() {
        var indexPathResult = NamedRoutes.indexPath();
        assertEquals(INDEX_PATH, indexPathResult);
    }

    @Test
    public void urlsPathTest() {
        var urlsPathResult = NamedRoutes.urlsPath();
        assertEquals(URLS_PATH, urlsPathResult);
    }

    @Test
    public void urlsIdPathTest() {
        var urlsIdPathResult = NamedRoutes.urlsIdPath(Long.MAX_VALUE);
        assertEquals(URL_ID_PATH, urlsIdPathResult);
    }

    @Test
    public void urlsIdPathTem() {
        var cut = NamedRoutes.urlsIdPath();
        assertEquals(URL_ID_PATH_TEMPLATE, cut);
    }

    @Test
    public void urlCheckPathTest() {
        var cut = NamedRoutes.urlIdChecksPath();
        assertEquals(URL_ID_CHECK_PATH_TEMPLATE, cut);
    }
}
