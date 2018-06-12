package httpclient;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import data.Dragon;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClient {

    public static Dragon getDragon() {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject game = new JsonObject();
        JsonObject knightStats = new JsonObject();

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://www.dragonsofmugloar.com/api/game");
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            String entity = EntityUtils.toString(response.getEntity());
            game = parser.parse(entity).getAsJsonObject();
            knightStats = game.getAsJsonObject("knight");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Dragon dragon = new Dragon(
                knightStats.get("attack").getAsInt(),
                knightStats.get("armor").getAsInt(),
                knightStats.get("agility").getAsInt(),
                knightStats.get("endurance").getAsInt(),
                game.get("gameId").getAsInt());
        String json = gson.toJson(dragon);
        String response = "{\"dragon\": "+ json +"}";
        System.out.println(response);
        return dragon;
    }
}
