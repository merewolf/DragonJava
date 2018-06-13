package data;

import java.util.*;
import java.util.stream.Collectors;

public class Dragon {
    private Map<String, Integer> sortedStats;

    public Dragon(int scaleThickness, int clawSharpness, int wingStrength, int fireBreath) {
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

    public Map<String, Integer> getSortedStats() {
        return sortedStats;
    }

    public void setSortedStats(Map<String, Integer> sortedStats) {
        this.sortedStats = sortedStats;
    }

    @Override
    public String toString() {
        return "{\"dragon\":{" +
                "\"scaleThickness\":" + sortedStats.get("scaleThickness") +
                ",\"clawSharpness\":" + sortedStats.get("clawSharpness") +
                ",\"wingStrength\":" + sortedStats.get("wingStrength") +
                ",\"fireBreath\":" + sortedStats.get("fireBreath") + "}" +
                '}';
    }
}
