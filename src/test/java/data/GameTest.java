package data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.hamcrest.core.IsEqual.equalTo;

class GameTest {

    private Battle battle = new Battle();
    private Dragon dragon;

    @BeforeEach
    void setUp() {
        this.dragon = new Dragon(8, 6, 5, 1);
    }

    @Test
    void normalWeatherTest() {
        battle.normalWeather(dragon);
        Map<String, Integer> stats = dragon.getSortedStats();
        assertThat(stats, hasEntry(equalTo("scaleThickness"), equalTo(10)));
        assertThat(stats, hasEntry(equalTo("clawSharpness"), equalTo(5)));
        assertThat(stats, hasEntry(equalTo("wingStrength"), equalTo(4)));
        assertThat(stats, hasEntry(equalTo("fireBreath"), equalTo(1)));
    }

    @Test
    void dryWeatherTest() {
        battle.dryWeather(dragon);
        Map<String, Integer> stats = dragon.getSortedStats();
        assertThat(stats, hasEntry(equalTo("scaleThickness"), equalTo(5)));
        assertThat(stats, hasEntry(equalTo("clawSharpness"), equalTo(5)));
        assertThat(stats, hasEntry(equalTo("wingStrength"), equalTo(5)));
        assertThat(stats, hasEntry(equalTo("fireBreath"), equalTo(5)));
    }

    @Test
    void rainWeatherTest() {
        battle.rainWeather(dragon);
        Map<String, Integer> stats = dragon.getSortedStats();
        assertThat(stats, hasEntry(equalTo("scaleThickness"), equalTo(5)));
        assertThat(stats, hasEntry(equalTo("clawSharpness"), equalTo(10)));
        assertThat(stats, hasEntry(equalTo("wingStrength"), equalTo(5)));
        assertThat(stats, hasEntry(equalTo("fireBreath"), equalTo(0)));
    }
}
