package hexlet.code.util;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

/**
 * It is a utility class for running the application.
 * It contains functions for normalizing URLs and retrieving data from a website page.
 *
 * @author Alexander Ryabinin
 * @version 1.0
 */
public class Utils {
    private static final String TEG_DESCRIPTION_REGEX = "(?<=<meta name=\"description\" content=\").+?(?=\">)";

    /**
     * Returns a normalized URL.
     * @param url Entered URL from website as <code>String</code>
     * @return Normalized URL as <code>String</code>
     * @throws URISyntaxException Incorrect URL entered
     * @throws MalformedURLException Incorrect URL entered
     */
    public static String getNormalizeUrl(String url) throws URISyntaxException, MalformedURLException {

        if (url == null || url.isEmpty()) {
            throw new NullPointerException("The url cannot be null");
        }

        String lowerCaseUrl = url.trim().toLowerCase();
        var objectUrl = new URI(lowerCaseUrl).toURL();
        String port = (objectUrl.getPort() != -1) ? ":" + objectUrl.getPort() : "";

        return String.format("%s://%s%s", objectUrl.getProtocol(), objectUrl.getHost(), port);
    }

    public static String getDescription(String body) {
        if (body == null || body.isEmpty()) {
            return "";
        }

        var pattern = Pattern.compile(TEG_DESCRIPTION_REGEX);
        var matcher = pattern.matcher(body);

        return matcher.find() ? matcher.group() : "";
    }

    public static String getDataFromHtmlTeg(String body, String htmlTeg) {

        if (body == null || body.isEmpty()) {
            return "";
        }

        String regex = "<" + htmlTeg + ".*?>.*?<\\/" + htmlTeg + ">";
        var pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(body);

        return matcher.find() ? matcher.group().replaceAll(("<.*?>"), "") : "";
    }
}
