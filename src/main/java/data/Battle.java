package data;

import httpclient.HttpClient;

import java.io.IOException;
import java.util.Map;

public class Battle {

    public static Dragon normalWeather(HttpClient httpClient) throws IOException {
        Dragon dragon = httpClient.getDragon();
        Map<String, Integer> stats = dragon.getSortedStats();
        String bestStat = (String) stats.keySet().toArray()[0];
        String secondBestStat = (String) stats.keySet().toArray()[1];
        int bestStatValue = stats.get(bestStat);
        int secondBestStatValue = stats.get(secondBestStat);

        stats.replace(bestStat,bestStatValue+2);
        stats.replace(secondBestStat,secondBestStatValue-2);
        dragon.setSortedStats(stats);
        return dragon;
    }
}
