package business;

import dto.UserHoliday;
import dto.WorkLogEntry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;

class LogProcessorTest {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private LogProcessor logProcessor;

    @BeforeEach
    public void setUp() {
        logProcessor = new LogProcessor();
    }

    @org.junit.jupiter.api.Test
    void calculateDaysToAdd() {

        WorkLogEntry entry = new WorkLogEntry();

        entry.setStartDate(LocalDateTime.parse("2022-02-18 13:43:27.000", formatter));
        Assertions.assertEquals(3, logProcessor.calculateDaysToAdd(entry, Optional.empty()));

        entry.setStartDate(LocalDateTime.parse("2022-02-18 13:43:27.000", formatter).minusDays(1));
        Assertions.assertEquals(1, logProcessor.calculateDaysToAdd(entry, Optional.empty()));

        UserHoliday userHoliday = new UserHoliday();
        entry.setStartDate(LocalDateTime.parse("2022-02-15 13:43:27.000", formatter));
        userHoliday.setHolidays(Arrays.asList(LocalDate.parse("2022-02-16"), LocalDate.parse("2022-02-17")));
        Assertions.assertEquals(3, logProcessor.calculateDaysToAdd(entry, Optional.of(userHoliday)));

        userHoliday.setHolidays(Arrays.asList(LocalDate.parse("2022-02-16"), LocalDate.parse("2022-02-17"),
                LocalDate.parse("2022-02-18")));
        Assertions.assertEquals(6, logProcessor.calculateDaysToAdd(entry, Optional.of(userHoliday)));

        userHoliday.setHolidays(Arrays.asList(LocalDate.parse("2022-02-16"), LocalDate.parse("2022-02-17"),
                LocalDate.parse("2022-02-18"), LocalDate.parse("2022-02-21")));
        Assertions.assertEquals(7, logProcessor.calculateDaysToAdd(entry, Optional.of(userHoliday)));

        userHoliday.setHolidays(Arrays.asList(LocalDate.parse("2022-02-16"), LocalDate.parse("2022-02-17"),
                LocalDate.parse("2022-02-18"), LocalDate.parse("2022-02-21"), LocalDate.parse("2022-02-22"),
                LocalDate.parse("2022-02-23"), LocalDate.parse("2022-02-24"), LocalDate.parse("2022-02-25")));
        Assertions.assertEquals(13, logProcessor.calculateDaysToAdd(entry, Optional.of(userHoliday)));
    }

    @org.junit.jupiter.api.Test
    void wasRecordedInTime() {

        WorkLogEntry entry = new WorkLogEntry();

        // was recorded after 10:00 am
        entry.setStartDate(LocalDateTime.parse("2022-02-17 13:43:27.000", formatter));
        entry.setCreated(entry.getStartDate().plusDays(1));
        Assertions.assertFalse(logProcessor.wasRecordedInTime(entry, Optional.empty()));

        // was recorded before 10:00 am
        entry.setStartDate(LocalDateTime.parse("2022-02-17 13:43:27.000", formatter));
        entry.setCreated(entry.getStartDate().plusDays(1).with(LocalTime.of(8, 0)));
        Assertions.assertTrue(logProcessor.wasRecordedInTime(entry, Optional.empty()));

        // working hours were on Friday, next working day should be Monday
        entry.setStartDate(LocalDateTime.parse("2022-02-18 13:43:27.000", formatter));
        entry.setCreated(entry.getStartDate().plusDays(3).with(LocalTime.of(8, 0)));
        Assertions.assertTrue(logProcessor.wasRecordedInTime(entry, Optional.empty()));

        // working hours were on Friday, next working day should be Monday, but it is Tuesday instead
        entry.setStartDate(LocalDateTime.parse("2022-02-18 13:43:27.000", formatter));
        entry.setCreated(entry.getStartDate().plusDays(4).with(LocalTime.of(8, 0)));
        Assertions.assertFalse(logProcessor.wasRecordedInTime(entry, Optional.empty()));

        // working hours were on Friday, Monday is holiday, so Tuesday is fine
        UserHoliday userHoliday = new UserHoliday();
        userHoliday.setHolidays(Arrays.asList(LocalDate.parse("2022-02-21")));
        entry.setStartDate(LocalDateTime.parse("2022-02-18 13:43:27.000", formatter));
        entry.setCreated(entry.getStartDate().plusDays(4).with(LocalTime.of(8, 0)));
        Assertions.assertTrue(logProcessor.wasRecordedInTime(entry, Optional.of(userHoliday)));
    }
}