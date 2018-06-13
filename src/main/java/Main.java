import data.Battle;
import httpclient.HttpClient;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    public Main() throws ParserConfigurationException {
    }

    public static void main(String[] args) throws IOException {
        HttpClient httpClient = new HttpClient();

        httpClient.putSolution(Battle.normalWeather(httpClient));
    }

}
