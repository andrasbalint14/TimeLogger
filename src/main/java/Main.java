import helper.FileReaderHelper;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        final String jsonTextLogs = FileReaderHelper.readFileFromResources(args[0]);
        final String jsonTextHolidays = FileReaderHelper.readFileFromResources(args[1]);
        System.out.println(jsonTextLogs);
        System.out.println(jsonTextHolidays);
    }

}
