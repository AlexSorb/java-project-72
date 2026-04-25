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
     * Creates a <code>UrlCheck</code> instance containing only attribute data from the entered website.
     *
     * <p>
     *     This constructor is intended for use when creating a <code>UrlCheck</code> object based on user-entered
     *     data received from a website. The remaining fields are retrieved from the database.
     * </p>
     * @param statusCode The HTTP status code returned by the entered website in response to the get request.
     * @param title The body of the title block from the entered website
     * @param h1 The body of the first h1 lock from the entered website
     * @param description Contains the content of the block marked with the description tag
     * @param urlId Contains the ID of the URL being checked
     */
    public UrlCheck(int statusCode, String title, String h1, String description, Long urlId) {
        this.statusCode = statusCode;
        this.title = title;
        this.h1 = h1;
        this.description = description;
        this.urlId = urlId;
    }
}
