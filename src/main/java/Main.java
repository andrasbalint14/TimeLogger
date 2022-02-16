import business.LogProcessor;
import com.google.gson.*;
import dto.HolidayWrapper;
import dto.WorkLogWrapper;
import helper.FileReaderHelper;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class Main {
    private static Gson gson;

    static {
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) -> LocalDate.parse(json.getAsJsonPrimitive().getAsString()))
                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> LocalDateTime.ofInstant(Instant.ofEpochMilli(json.getAsJsonPrimitive().getAsLong()),
                        TimeZone.getDefault().toZoneId()))
                .create();
    }

    public static void main(String[] args) throws IOException {
        final String jsonTextLogs = FileReaderHelper.readFileFromResources(args[0]);
        final String jsonTextHolidays = FileReaderHelper.readFileFromResources(args[1]);
        final WorkLogWrapper workLogs  = gson.fromJson(jsonTextLogs, WorkLogWrapper.class);
        final HolidayWrapper holidays = gson.fromJson(jsonTextHolidays, HolidayWrapper.class);
        final LogProcessor processor = new LogProcessor();

        processor.processWorkLogs(workLogs);
    }

}
