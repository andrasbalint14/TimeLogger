package helper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class DateHelperTest {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    @Test
    void getWeekOfYearFromLocalDateTime() {

        Assertions.assertEquals("2022-1", DateHelper.getWeekOfYearFromLocalDateTime(LocalDateTime.parse("2022-01-07 13:43:27.000", formatter)));
        Assertions.assertEquals("2022-7", DateHelper.getWeekOfYearFromLocalDateTime(LocalDateTime.parse("2022-02-18 13:43:27.000", formatter)));
        Assertions.assertEquals("2022-10", DateHelper.getWeekOfYearFromLocalDateTime(LocalDateTime.parse("2022-03-11 13:43:27.000", formatter)));
    }
}