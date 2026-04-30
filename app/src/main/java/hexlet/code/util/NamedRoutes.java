package hexlet.code.util;

/**
 * This is a utility class that contains functions that return the URL paths.
 *
 * @author Ryebinin Alexander
 * @version 1.0
 */
public class NamedRoutes {

    public static String indexPath() {
        return "/";
    }
    public static String urlsPath() {
        return "/urls";
    }
    public static String urlsIdPath(Long id) {
        return "/urls/" + id;
    }

    public static String urlsIdPath() {
        return "/urls/{id}";
    }

    public static String urlIdChecksPath() {
        return "/urls/{id}/checks";
    }
}
