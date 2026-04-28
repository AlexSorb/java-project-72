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

    /**
     * Returns a normalized URL
     * @param url Entered URL from website as <code>String</code>
     * @return Normalized URL as <code>String</code>
     * @throws URISyntaxException Incorrect URL entered
     * @throws MalformedURLException Incorrect URL entered
     */
    public static String getNormalizeUrl(String url) throws URISyntaxException, MalformedURLException {

        if (url == null) {
            throw new NullPointerException("The url cannot be null");
        }

        if (url.isEmpty()) {
            return "url";
        }

        String lowerCaseUrl = url.trim().toLowerCase();

        var objectUrl = new URI(lowerCaseUrl).toURL();

        StringBuilder normalizeUrl = new StringBuilder();
        normalizeUrl.append(objectUrl.getProtocol());
        normalizeUrl.append("://");
        normalizeUrl.append(objectUrl.getHost());

        String port = (objectUrl.getPort() != -1) ? ":" + objectUrl.getPort() : "";
        normalizeUrl.append(port);

        return normalizeUrl.toString();
    }

    public static String getDescription(String body) {
        if (body == null) {
            return "";
        }

        String result = "";
        String regex = "(?<=<meta name=\"description\" content=\").+?(?=\">)";
        var pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(body);
        if (matcher.find()) {
            result = matcher.group();
        }

        return result;
    }

    public static String getDataFromHtmlTeg(String body, String htmlTeg) {
        String result = "";
        String regex = "<" + htmlTeg + ".*?>.*?<\\/" + htmlTeg + ">";
        var pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(body);

        if (matcher.find()) {
            var substr = matcher.group();
            result = substr.replaceAll(("<.*?>"), "");
        }

        return result;
    }
}
