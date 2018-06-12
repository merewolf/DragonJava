package data;

import httpclient.HttpClient;
import java.util.Map;

public class Battle {

    public static void normalWeather() {
        Dragon dragon = HttpClient.getDragon();
        Map<String, Integer> stats = dragon.getSortedStats();
        String bestStat = (String) stats.keySet().toArray()[0];
        String secondBestStat = (String) stats.keySet().toArray()[1];
        int bestStatValue = stats.get(bestStat);
        int secondBestStatValue = stats.get(secondBestStat);

        stats.replace(bestStat,bestStatValue+2);
        stats.replace(secondBestStat,secondBestStatValue-2);
        dragon.setSortedStats(stats);
        System.out.println(dragon.getSortedStats());

    }
}
