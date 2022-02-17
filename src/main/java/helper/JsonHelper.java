package helper;

import com.google.gson.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

public class JsonHelper {

    private static Gson gson;
    private static ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();

    static {
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) -> LocalDate.parse(json.getAsJsonPrimitive().getAsString()))
                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> LocalDateTime.ofInstant(Instant.ofEpochMilli(json.getAsJsonPrimitive().getAsLong()),
                        TimeZone.getDefault().toZoneId()))
                .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (json, type, jsonDeserializationContext) -> new JsonPrimitive(json.atStartOfDay(DEFAULT_ZONE_ID).toEpochSecond()))
                .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> new JsonPrimitive(json.atZone(DEFAULT_ZONE_ID).toEpochSecond()))
                .setPrettyPrinting()
                .create();
    }

    public static Object jsonTextToObject(final String jsonText, final Class className) {
        return gson.fromJson(jsonText, className);
    }

    public static String jsonObjectToText(final Object jsonObject) {

        final String uglyJsonText = gson.toJson(jsonObject);
        final JsonElement jsonElement = JsonParser.parseString(uglyJsonText);
        return gson.toJson(jsonElement);
    }

}
