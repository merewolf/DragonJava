package data;

import com.google.gson.JsonObject;
import httpclient.HttpClient;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

public class Battle {
    private int won = 0;
    private int lost = 0;
    private int timesPlayed = 0;

    public void getResults(HttpClient httpClient) throws IOException, SAXException, ParserConfigurationException {
        JsonObject result = httpClient.putSolution(doBattle(httpClient));
        String status;
        String message;
        status = result.getAsJsonPrimitive("status").getAsString();
        message = result.getAsJsonPrimitive("message").getAsString();
        if (status.equals("Victory")) {
            this.won++;
        } else {
            this.lost++;
        }
        System.out.println(message);
        this.timesPlayed++;
        System.out.println("Wins: " + won + " Losses: " + lost + ". Total games played: " + timesPlayed +
                ". Win percentage: " + (double) won / (double) timesPlayed * 100);
        System.out.println("------------------------------------");
    }

    private Dragon doBattle(HttpClient httpClient) throws ParserConfigurationException, SAXException, IOException {
        Dragon dragon = httpClient.getDragon();
        double weatherRating = httpClient.getWeatherRating();
        if (weatherRating < 2) return dragon;
        else if (weatherRating < 4) {
            dragon = null;
        } else if (weatherRating < 7) {
            dryWeather(dragon);
        } else if (weatherRating < 10) {
            normalWeather(dragon);
        } else {
            rainWeather(dragon);
        }
        return dragon;
    }

    private void normalWeather(Dragon dragon) {
        Map<String, Integer> stats = dragon.getSortedStats();
        String bestStat = (String) stats.keySet().toArray()[0];
        String secondBestStat = (String) stats.keySet().toArray()[1];
        String thirdBestStat = (String) stats.keySet().toArray()[2];
        int bestStatValue = stats.get(bestStat);
        int secondBestStatValue = stats.get(secondBestStat);
        int thirdBestStatValue = stats.get(thirdBestStat);

        stats.replace(bestStat, bestStatValue + 2);
        stats.replace(secondBestStat, secondBestStatValue - 1);
        stats.replace(thirdBestStat, thirdBestStatValue - 1);
        dragon.setSortedStats(stats);
    }

    private void dryWeather(Dragon dragon) {
        Map<String, Integer> stats = dragon.getSortedStats();
        stats.replace("scaleThickness", 5);
        stats.replace("clawSharpness", 5);
        stats.replace("wingStrength", 5);
        stats.replace("fireBreath", 5);
        dragon.setSortedStats(stats);
    }

    private void rainWeather(Dragon dragon) {
        Map<String, Integer> stats = dragon.getSortedStats();
        stats.replace("scaleThickness", 5);
        stats.replace("clawSharpness", 10);
        stats.replace("wingStrength", 5);
        stats.replace("fireBreath", 0);
        dragon.setSortedStats(stats);
    }
}
