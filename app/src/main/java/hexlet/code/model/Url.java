package hexlet.code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 *  Represents a URL entered on a website.
 *  <p>
 *  This class is used to encapsulate a URL string submitted via the website's interface.
 *  The encapsulated URL can then be persisted to a database for further use.
 *  </p>
 *
 * @author Ryabinin Alexander
 * @version 1.0
 */

@Getter
@Setter
@AllArgsConstructor
public class Url {
    private Long id;
    private String name;
    private LocalDateTime createdAt;

    /**
     *  Constructs a <code>Url</code> instance with only the raw URL string.
     *  <p>
     *  This constructor is intended for use when creating a <code>Url</code> from
     *  user input on the website. The remaining fields are retrieved from the database.
     *  </p>
     *
     * @param name the URL string used to initialize the <code>Url</code> object
     */
    public Url(String name) {
        this.name = name;
    }
}
