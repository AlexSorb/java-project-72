package hexlet.code.util;

/**
 * This is a utility class that contains functions that return the URL paths.
 *
 * @author Ryebinin Alexander
 * @version 1.0
 */
public class NamedRoutes {
    /**
     * Used to get a link to the home page of a website.
     * @return The root path of the website as <code>String</code>.
     */
    public static String indexPath() {
        return "/";
    }

    /**
     * Used to get a link to the main page with a list of added URLs.
     * @return The path to the page with the added URL as <code>String</code>
     */
    public static String urlsPath() {
        return "/urls";
    }

    /**
     * Used to get a link to a page with a specific URL by the specified ID.
     * @param id ID of the required URL in the database
     * @return The path to the page with the added URL as <code>String</code>.
     */
    public static String urlsIdPath(Long id) {
        return "/urls/" + id;
    }

    /**
     * Used to get a path with a prepared template for adding id at runtime.
     * @return The path with the id addition pattern as <code>String</code>.
     */
    public static String urlsIdPath() {
        return "/urls/{id}";
    }

    /**
     * Used to get the path to check a specific URL.
     * @return The path to the check as <code>String</code>.
     */
    public static String urlIdChecksPath() {
        return "/urls/{id}/checks";
    }
}
