package httpclient;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import data.Dragon;
import okhttp3.*;

import java.io.IOException;

public class HttpClient {
    private int gameId;
    private OkHttpClient client = new OkHttpClient();

    public Dragon getDragon() throws IOException {
        JsonParser parser = new JsonParser();
        JsonObject game;
        JsonObject knightStats;

        Request request = new Request.Builder()
                .url("http://www.dragonsofmugloar.com/api/game")
                .get()
                .addHeader("Cache-Control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();
        game = parser.parse(response.body().string()).getAsJsonObject();
        knightStats = game.getAsJsonObject("knight");
        Dragon dragon = new Dragon(
                knightStats.get("attack").getAsInt(),
                knightStats.get("armor").getAsInt(),
                knightStats.get("agility").getAsInt(),
                knightStats.get("endurance").getAsInt());
        this.gameId = game.get("gameId").getAsInt();
        return dragon;
    }

    public void putSolution(Dragon dragon) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, dragon.toString());
        Request request = new Request.Builder()
                .url("http://www.dragonsofmugloar.com/api/game/"+gameId+"/solution")
                .put(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Cache-Control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

}
