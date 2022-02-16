package helper;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class DateHelper {

    public static Integer getWeekOfYearFromLocalDateTime(final LocalDateTime dateTime) {
        final WeekFields weekFields = WeekFields.of(Locale.getDefault());
        return dateTime.get(weekFields.weekOfWeekBasedYear());
    }
}
