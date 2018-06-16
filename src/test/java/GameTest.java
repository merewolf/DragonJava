import data.Battle;
import data.Dragon;
import httpclient.HttpClient;
import org.junit.Test;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class GameTest {

    private HttpClient httpClient = new HttpClient();
    private Battle battle = new Battle();

    @Test
    public void getDragonTest() throws ParserConfigurationException, SAXException, IOException {
        assertThat(httpClient.getDragon(), instanceOf(Dragon.class));
    }

    @Test
    public void getWeatherRatingTest(){
        assertThat(httpClient.getWeatherRating(), instanceOf(Double.class));
    }

    @Test
    public void getResultsTest() throws ParserConfigurationException, SAXException, IOException {
        battle.getResults(httpClient);
        assertEquals("Victory",battle.getStatus());
    }
}
