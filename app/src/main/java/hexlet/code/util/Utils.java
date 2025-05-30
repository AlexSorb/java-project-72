package hexlet.code.util;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class Utils {
    public static String getNormalizeUrl(String url) throws URISyntaxException, MalformedURLException {
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

    public static String getH1Teg(String body) {
        return "";
    }

    public static String getTitle(String body) {
        return "";
    }

    public static String getDescription(String body) {
        return "";
    }
}
