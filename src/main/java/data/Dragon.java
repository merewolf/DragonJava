package data;

import java.util.*;
import java.util.stream.Collectors;

public class Dragon {
    private int gameId;
    private Map<String, Integer> sortedStats;

    public Dragon(int scaleThickness, int clawSharpness, int wingStrength, int fireBreath, int gameId) {
        this.gameId = gameId;
        Map<String, Integer> stats = new HashMap<>();
        stats.put("scaleThickness", scaleThickness);
        stats.put("clawSharpness", clawSharpness);
        stats.put("wingStrength", wingStrength);
        stats.put("fireBreath", fireBreath);
        this.sortedStats = stats.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public Map<String, Integer> getSortedStats() {
        return sortedStats;
    }

    public void setSortedStats(Map<String, Integer> sortedStats) {
        this.sortedStats = sortedStats;
    }

}
