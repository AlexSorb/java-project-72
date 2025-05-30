package hexlet.code.util;

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
