import data.Battle;
import httpclient.HttpClient;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        HttpClient httpClient = new HttpClient();
        Battle battle = new Battle();
        for (int i=0;i<20;i++) {
            battle.getResults(httpClient);
        }
    }
}
