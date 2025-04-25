package hexlet.code.controller;

import hexlet.code.util.NamedRoutes;
import hexlet.code.util.Utils;
import hexlet.code.model.Url;
import hexlet.code.repository.UrlRepository;
import io.javalin.http.Context;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Objects;

public class UrlController {

    public static void index(Context context) {
        context.render("urls/urls.jte");
    }

    public static void create(Context handler) throws URISyntaxException, MalformedURLException, SQLException {
        String urlString = Objects.requireNonNull(handler.formParam("url"));
        var normalizeUrl = Utils.getNormalizeUrl(urlString);

        UrlRepository.save(new Url(normalizeUrl));
        handler.redirect(NamedRoutes.urlsPath());
    }


}
