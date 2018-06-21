package data;

import com.google.gson.JsonObject;
import httpclient.HttpClient;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

public class Battle {
    private int gameId;
    private double weatherRating;
    private int won = 0;
    private int lost = 0;
    private int timesPlayed = 0;

    public void getResults(HttpClient httpClient) throws IOException, SAXException, ParserConfigurationException {
        JsonObject result = httpClient.putSolution(doBattle(setUpBattle(httpClient)), this.gameId);
        String status = result.getAsJsonPrimitive("status").getAsString();
        String message = result.getAsJsonPrimitive("message").getAsString();
        if (status.equals("Victory")) {
            this.won++;
        } else {
            this.lost++;
        }
        System.out.println("------------------------------------");
        System.out.println(message);
        this.timesPlayed++;
        System.out.println("Wins: " + won + " Losses: " + lost + ". Total games played: " + timesPlayed +
                ". Win percentage: " + (double) won / (double) timesPlayed * 100);
        System.out.println("------------------------------------");
    }

    private JsonObject setUpBattle(HttpClient httpClient) throws ParserConfigurationException, SAXException, IOException {
        JsonObject game = httpClient.getNewGame();
        this.gameId = game.get("gameId").getAsInt();
        this.weatherRating = httpClient.getWeather(gameId);
        return game.getAsJsonObject("knight");
    }

    private Dragon doBattle(JsonObject knightStats) {
        Dragon dragon = new Dragon(
                knightStats.get("attack").getAsInt(),
                knightStats.get("armor").getAsInt(),
                knightStats.get("agility").getAsInt(),
                knightStats.get("endurance").getAsInt());
        if (this.weatherRating < 2) return dragon;
        else if (this.weatherRating < 4) {
            dragon = null;
        } else if (this.weatherRating < 7) {
            dryWeather(dragon);
        } else if (this.weatherRating < 10) {
            normalWeather(dragon);
        } else {
            rainWeather(dragon);
        }
        return dragon;
    }

    void normalWeather(Dragon dragon) {
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

    void dryWeather(Dragon dragon) {
        Map<String, Integer> stats = dragon.getSortedStats();
        stats.replace("scaleThickness", 5);
        stats.replace("clawSharpness", 5);
        stats.replace("wingStrength", 5);
        stats.replace("fireBreath", 5);
        dragon.setSortedStats(stats);
    }

    void rainWeather(Dragon dragon) {
        Map<String, Integer> stats = dragon.getSortedStats();
        stats.replace("scaleThickness", 5);
        stats.replace("clawSharpness", 10);
        stats.replace("wingStrength", 5);
        stats.replace("fireBreath", 0);
        dragon.setSortedStats(stats);
    }
}
