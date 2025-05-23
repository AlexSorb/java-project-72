package hexlet.code.dto.url;

import hexlet.code.dto.BasePage;
import hexlet.code.model.Url;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UrlPage extends BasePage {
    private Url url;
}
