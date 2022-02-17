import business.LogProcessor;
import dto.HolidayWrapper;
import dto.WorkLogWrapper;
import helper.FileHelper;
import helper.JsonHelper;

public class Main {


    public static void main(String[] args) {
        final String jsonTextLogs = FileHelper.readFileFromResources(args[0]);
        final String jsonTextHolidays = FileHelper.readFileFromResources(args[1]);
        final WorkLogWrapper workLogs  = (WorkLogWrapper) JsonHelper.jsonTextToObject(jsonTextLogs, WorkLogWrapper.class);
        final HolidayWrapper holidays = (HolidayWrapper) JsonHelper.jsonTextToObject(jsonTextHolidays, HolidayWrapper.class);
        final LogProcessor processor = new LogProcessor();
        processor.processWorkLogs(workLogs);
    }

}
