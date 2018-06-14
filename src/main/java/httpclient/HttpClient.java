package httpclient;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import data.Dragon;
import org.xml.sax.InputSource;
import okhttp3.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;

public class HttpClient {
    private int gameId;
    private double weatherRating;
    private OkHttpClient client = new OkHttpClient();
    private JsonParser parser = new JsonParser();

    public Dragon getDragon() throws IOException, ParserConfigurationException, SAXException {
        JsonObject game;
        JsonObject knightStats;
        Request request = new Request.Builder()
                .url("http://www.dragonsofmugloar.com/api/game")
                .get()
                .addHeader("Cache-Control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();
        game = this.parser.parse(response.body().string()).getAsJsonObject();
        knightStats = game.getAsJsonObject("knight");
        Dragon dragon = new Dragon(
                knightStats.get("attack").getAsInt(),
                knightStats.get("armor").getAsInt(),
                knightStats.get("agility").getAsInt(),
                knightStats.get("endurance").getAsInt());
        this.gameId = game.get("gameId").getAsInt();
        this.weatherRating = getWeather();
        return dragon;
    }

    public JsonObject putSolution(Dragon dragon) throws IOException {
        JsonObject result;
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body;
        if (dragon != null) {
            body = RequestBody.create(mediaType, dragon.toString());
        } else {
            body = RequestBody.create(mediaType, "");
        }
        Request request = new Request.Builder()
                .url("http://www.dragonsofmugloar.com/api/game/" + gameId + "/solution")
                .put(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Cache-Control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();
        result = this.parser.parse(response.body().string()).getAsJsonObject();
        return result;
    }

    private double getWeather() throws IOException, ParserConfigurationException, SAXException {
        URL xmlContent = new URL("http://www.dragonsofmugloar.com/weather/api/report/" + gameId);
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = db.parse(new InputSource(xmlContent.openStream()));
        this.weatherRating = Double.parseDouble(doc.getFirstChild().getLastChild().getTextContent());
        return weatherRating;
    }

    public double getWeatherRating() {
        return weatherRating;
    }
}
