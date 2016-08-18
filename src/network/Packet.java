package network;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import game.Game;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 *
 */
public class Packet {
    // Function should be 'public static Object fromJson(JsonElement json) {}'
    private static Map<String, Function<JsonElement, Object>> fromJson;
    // Function should be 'public JsonElement toJson() {}'
    private static Map<Class, Function<Object, JsonElement>> toJson;

    static {
        toJson = new HashMap<>();
        toJson.put(Game.class, game -> ((Game) game).toJson());
        toJson.put(AcctCreateRequest.class, req -> ((AcctCreateRequest) req).toJson());
        toJson.put(Acknowledgement.class, req -> ((Acknowledgement) req).toJson());
        toJson.put(LoginRequest.class, req -> ((LoginRequest) req).toJson());

        fromJson = new HashMap<>();
        fromJson.put(Game.class.getSimpleName(), Game::fromJson);
        fromJson.put(AcctCreateRequest.class.getSimpleName(), AcctCreateRequest::fromJson);
        fromJson.put(Acknowledgement.class.getSimpleName(), Acknowledgement::fromJson);
        fromJson.put(LoginRequest.class.getSimpleName(), LoginRequest::fromJson);
    }

    private String token;
    private Object data;

    public Packet(String token, Object data) {
        this.token = token;
        this.data = data;
    }

    public static Packet perror(String msg) {
        return new Packet("", new Acknowledgement(msg, false));
    }

    public static String error(String msg) {
        return perror(msg).toJson();
    }

    public static Packet fromJson(String json) {
        try {
            JsonObject root = new JsonParser().parse(json).getAsJsonObject();
            String token = root.get("token").getAsString();
            String type = root.get("type").getAsString();
            Function<JsonElement, Object> func = fromJson.get(type);
            if (func == null) {
                return null;
            }
            Object data = func.apply(root.get("data"));
            return new Packet(token, data);
        } catch (IllegalStateException | UnsupportedOperationException e) {
            return null;
        }
    }

    public String toJson() {
        JsonObject root = new JsonObject();
        root.addProperty("token", token);
        Function<Object, JsonElement> func = toJson.get(data.getClass());
        if (func == null) {
            return null;
        }
        root.add("data", toJson.get(data.getClass()).apply(data));
        root.addProperty("type", data.getClass().getSimpleName());
        return new Gson().toJson(root);
    }

    public Object getData() {
        return this.data;
    }

    public Class getType() {
        return this.data.getClass();
    }

    public String getToken() {
        return token;
    }
}
