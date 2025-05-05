package hexlet.code.util;

public class NamedRoutes {

    public static String index() {
        return "/";
    }
    public static String urlsPath() {
        return "/urls";
    }
    public static String urlsIdPath(Long id) {
        return "/urls/" + id;
    }
}
