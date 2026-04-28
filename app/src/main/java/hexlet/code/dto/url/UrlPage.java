package hexlet.code.dto.url;

import hexlet.code.dto.BasePage;
import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 *  This is a DTO class for passing data to a website page.
 *
 * <p>
 *     This class extends BasePage and passes data about a specific URL to a website page.
 * </p>
 * @author Ryabinin Alexander
 * @version 1.0
 */
@Getter
@AllArgsConstructor
public class UrlPage extends BasePage {
    private Url url;
    private List<UrlCheck> check;
}
