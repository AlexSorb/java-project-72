package hexlet.code.dto;

import lombok.Getter;
import lombok.Setter;
/**
 * This is a DTO class for passing data to a web page.
 *
 * <p>
 This class is the base class for all DTO classes representing page strobes for passing
 to website pages.
 * </p>
 *
 * @author Ryabinin Alexander
 * @version 1.0
 */
@Getter
@Setter
public class BasePage {
    private String flash;
}
