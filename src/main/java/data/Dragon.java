package data;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Dragon {
    private int gameId;
    private Map<String, Integer> sortedStats = new HashMap<String, Integer>();

    public Dragon(int scaleThickness, int clawSharpness, int wingStrength, int fireBreath, int gameId) {
        this.gameId = gameId;
        Map<String, Integer> stats = new HashMap<String, Integer>();
        stats.put("scaleThickness", scaleThickness);
        stats.put("clawSharpness", clawSharpness);
        stats.put("wingStrength", wingStrength);
        stats.put("fireBreath", fireBreath);
        this.sortedStats = stats.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
}
