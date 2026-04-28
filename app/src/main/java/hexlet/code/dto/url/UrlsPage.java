package hexlet.code.dto.url;

import hexlet.code.dto.BasePage;
import hexlet.code.model.Url;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
/**
 * This is a DTO class for passing data to a web page.
 *
 * <p>
 * This class extends BasePage and passes data about all URLs to the web page.
 * </p>
 */
@Getter
@AllArgsConstructor
public class UrlsPage extends BasePage {
    private List<Url> urlList;
}
