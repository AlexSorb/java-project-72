package hexlet.code.controller;

import hexlet.code.dto.BasePage;
import hexlet.code.dto.url.UrlPage;
import hexlet.code.dto.url.UrlsPage;
import hexlet.code.model.UrlCheck;
import hexlet.code.repository.UrlChecksRepository;
import hexlet.code.util.NamedRoutes;
import hexlet.code.util.Utils;
import hexlet.code.model.Url;
import hexlet.code.repository.UrlRepository;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;
import kong.unirest.Unirest;
import org.eclipse.jetty.http.HttpStatus;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.regex.Pattern;
import java.util.Map;

/**
 * The class contains functions for processing requests.
 * @author Ryabinin Alexander
 * @version 1.0
 */
public class UrlController {
    /**
     * Generates a page with all entered URLs.
     * @param context The entered query
     * @throws SQLException If the page is not rendered
     */
    public static void index(Context context) throws SQLException {
        var listUrls = UrlRepository.getEntities();
        var page = new UrlsPage(listUrls);

        page.setFlash(context.consumeSessionAttribute("flash"));
        context.render("urls/urls.jte", Map.of("page", page));
    }

    /**
     * Processes URL input on the page.
     * @param handler The entered query
     * @throws URISyntaxException If an error occurred while normalizing the URL
     * @throws MalformedURLException If an error occurred while normalizing the URL
     * @throws SQLException If you were unable to save the URL
     */
    public static void create(Context handler) throws URISyntaxException, MalformedURLException, SQLException {
        try {
//            var pattern = Pattern.compile("(https?):((//)|(\\\\\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*");
//            String urlAsString = handler.formParamAsClass("url", String.class)
//                    .check(value -> {
//                        var matcher = pattern.matcher(value);
//                        return matcher.matches();
//                    }, "Некорректный URL")
//                    .get();
//            var normalizeUrl = Utils.getNormalizeUrl(urlAsString);
//            var url = new Url(normalizeUrl);
//            var urlFind = UrlRepository.findByName(normalizeUrl);
//
//            if (urlFind.isEmpty()) {
//                UrlRepository.save(url);
//                handler.sessionAttribute("flash", "Страница успешно добавлена");
//                handler.redirect(NamedRoutes.urlsIdPath(url.getId()));
//            } else {
//                handler.sessionAttribute("flash", "Страница уже существует");
//                handler.redirect(NamedRoutes.urlsIdPath(urlFind.get().getId()));
//            }


            var enteredUrl = handler.formParamAsClass("url", String.class).getOrNull();
            var normalizeUrl = Utils.getNormalizeUrl(enteredUrl);
            var url = new Url(normalizeUrl);

            if (UrlRepository.findByName(url.getName()).isEmpty()) {
                UrlRepository.save(url);
                handler.sessionAttribute("flash", "Страница успешно добавлена");
            } else {
                handler.sessionAttribute("flash", "Страница уже существует");
                var savedUrl = UrlRepository.findByName(url.getName());
                url.setId(savedUrl.get().getId());
                url.setCreatedAt(savedUrl.get().getCreatedAt());
            }

            handler.redirect(NamedRoutes.urlsIdPath(url.getId()));
        } catch (Exception exception) {
            handler.sessionAttribute("flash", "Некорректный URL");
            handler.status(HttpStatus.UNPROCESSABLE_ENTITY_422);
            var page = new BasePage();
            page.setFlash("Некорректный URL");
            handler.render("index.jte", Map.of("page", page));
        }

    }

    /**
     * Displays all entered URLs.
     * @param handler The entered query
     * @throws SQLException If the database query failed
     */
    public static void show(Context handler) throws SQLException {
        var id = handler.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.findById(id).orElseThrow(() ->
                new NotFoundResponse("Entity with id = " + id + " not found"));

        var urlCheck = UrlChecksRepository.findById(id);
        var page = new UrlPage(url, urlCheck);
        page.setFlash(handler.consumeSessionAttribute("flash"));
        handler.render("urls/url.jte", Map.of("page", page));
    }

    /**
     * Performs URL checking.
     * @param handler The entered query
     * @throws SQLException If the database query failed
     */

    public static void check(Context handler) throws SQLException {
        var id = handler.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.findById(id).get();

        var response = Unirest.get(url.getName()).asString();

        var responseStatus = response.getStatus();
        var body = response.getBody();
        var h1 = Utils.getDataFromHtmlTeg(body, "h1");
        var title = Utils.getDataFromHtmlTeg(body, "title");
        var description = Utils.getDescription(body);

        var check = new UrlCheck(responseStatus, title, h1, description, id);
        UrlChecksRepository.save(check);
        handler.redirect(NamedRoutes.urlsIdPath(id));
    }
}
