import hexlet.code.App;
import kong.unirest.Unirest;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

public class CheckTest {

    private String currentUrl;

    @BeforeAll
    void initialisation()throws IOException {
        currentUrl = "http://localhost:" + App.getPort();
    }

    @Test
    void simpleTest() {
        var request = Unirest.get(currentUrl).asString();
        assertEquals(200, request.getStatus());
        assertNotNull(request.getBody());
    }


}
