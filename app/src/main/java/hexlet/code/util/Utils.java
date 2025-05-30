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
        String result = "";
        var indexH1 = body.indexOf("h1");
        var indexH1Close = body.indexOf("/h1");

        if (indexH1 != -1 && indexH1Close != -1) {
            var str = body.substring(indexH1, indexH1Close);

            var indexScobka = str.indexOf('>');
            var indexOpenScobka = str.indexOf('<');
            if (indexScobka != -1 && indexOpenScobka != -1) {
                result = str.substring(indexScobka + 1, indexOpenScobka);
            }

        }
        return result;
    }

    public static String getTitle(String body) {
        String result = "";
        var indexH1 = body.indexOf("title");
        var indexH1Close = body.indexOf("/title");

        if (indexH1 != -1 && indexH1Close != -1) {
            var str = body.substring(indexH1, indexH1Close);

            var indexScobka = str.indexOf('>');
            var indexOpenScobka = str.indexOf('<');
            if (indexScobka != -1 && indexOpenScobka != -1) {
                result = str.substring(indexScobka + 1, indexOpenScobka);
            }

        }
        return result;
    }

    public static String getDescription(String body) {
        String result = "";
        var indexDescription = body.indexOf("description");

        if ( indexDescription != -1) {
            var substring = body.substring(indexDescription);

            var indexScobka = substring.indexOf('>');
            var indexOpenScobka = substring.indexOf('<');

            if (indexScobka != -1 && indexOpenScobka != -1) {
                result = substring.substring(indexScobka, indexOpenScobka);
            }
        }
        return result;
    }
}
