package hexlet.code.controller;

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

import static io.javalin.rendering.template.TemplateUtil.model;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class UrlController {
    public static void index(Context context) throws SQLException {
        var listUrls = UrlRepository.getEntities();
        var page = new UrlsPage(listUrls);
        page.setFlash(context.consumeSessionAttribute("flash"));
        context.render("urls/urls.jte", model("page", page));
    }

    public static void create(Context handler) throws URISyntaxException, MalformedURLException, SQLException {
        try {
            String newUrlAsString = handler.formParamAsClass("url", String.class)
                    .check(value -> Pattern.matches(
                            "(https?):((//)|(\\\\\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*", value),
                            "Некорректный URL")
                    .get();

            var normalizeUrl = Utils.getNormalizeUrl(newUrlAsString);

            if (UrlRepository.findByName(normalizeUrl).isEmpty()) {
                UrlRepository.save(new Url(normalizeUrl));
                handler.sessionAttribute("flash", "Страница успешно добавлена");
            } else {
                handler.sessionAttribute("flash", "Страница уже существует");
            }

            handler.redirect(NamedRoutes.urlsPath());
        } catch (ValidationException exception) {
            // TO DO
            handler.sessionAttribute("flash", "Некорректный URL");
            handler.redirect(NamedRoutes.urlsPath());
        }
    }

    public static void show(Context handler) throws SQLException {
        var id = handler.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.findById(id).orElseThrow(() ->
                new NotFoundResponse("Entity with id = " + id + " not found"));

        var urlCheck = UrlChecksRepository.findById(id);
        var page = new UrlPage(url, urlCheck);
        handler.render("urls/url.jte", model("page", page));
    }

    public static void check(Context handler) throws SQLException {
        var id = handler.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.findById(id).get();

        var response = Unirest.get(url.getName()).asString();
        var responseStatus = response.getStatus();
        var body = response.getBody();
        var h1 = Utils.getH1Teg(body);
        var title = Utils.getTitle(body);
        var description = Utils.getDescription(body);



        var check = new UrlCheck();
        check.setH1(h1);
        check.setTitle(title);
        check.setDescription(description);
        check.setStatusCode(responseStatus);
        check.setUrlId(id);

        UrlChecksRepository.save(check);

        handler.redirect("/urls/" + id);
    }
}
