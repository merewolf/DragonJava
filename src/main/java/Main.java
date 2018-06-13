import data.Battle;
import httpclient.HttpClient;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        HttpClient httpClient = new HttpClient();

        httpClient.putSolution(Battle.normalWeather(httpClient));
    }
}
