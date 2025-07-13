package hexlet.code.util;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

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
            result = substr.replaceAll(("<.*?>"),"");
        }

        return result;
    }
}
