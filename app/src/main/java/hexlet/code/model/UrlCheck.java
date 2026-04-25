package hexlet.code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Represents a unit of validation for a URL entered on a website.
 *
 *<p>
 *     This class is used to encapsulate the validation of a URL sent through a website interface.
 *     The encapsulated URL validation can then be stored in the database for later use.
 *</p>
 *
 * @author Alexander Ryabinin
 * @version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UrlCheck {
    private Long id;
    private int statusCode;
    private String title;
    private String h1;
    private String description;
    private Long urlId;
    private LocalDateTime createdAt;

    /**
     *
     * @param statusCode
     * @param title
     * @param h1
     * @param description
     * @param urlId
     */
    public UrlCheck(int statusCode, String title, String h1, String description, Long urlId) {
        this.statusCode = statusCode;
        this.title = title;
        this.h1 = h1;
        this.description = description;
        this.urlId = urlId;
    }
}
