package hexlet.code.controller;

import hexlet.code.dto.url.UrlsPage;
import hexlet.code.util.NamedRoutes;
import hexlet.code.util.Utils;
import hexlet.code.model.Url;
import hexlet.code.repository.UrlRepository;
import io.javalin.http.Context;
import static io.javalin.rendering.template.TemplateUtil.model;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Objects;

public class UrlController {

    public static void index(Context context) throws SQLException {
        var listUrls = UrlRepository.getEntities();
        var page = new UrlsPage(listUrls);
        page.setFlash(context.consumeSessionAttribute("flash"));
        context.render("urls/urls.jte", model("page", page));
    }

    public static void create(Context handler) throws URISyntaxException, MalformedURLException, SQLException {
        String urlString = Objects.requireNonNull(handler.formParam("url"));
        var normalizeUrl = Utils.getNormalizeUrl(urlString);

        if (UrlRepository.findByName(normalizeUrl).isEmpty()) {
            UrlRepository.save(new Url(normalizeUrl));
            handler.sessionAttribute("flash", "АПРА");
        } else {
            handler.sessionAttribute("flash", "EXIST");
        }

        handler.redirect(NamedRoutes.urlsPath());
    }


}
