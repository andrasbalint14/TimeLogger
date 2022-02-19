package business;

import dto.*;
import helper.DateHelper;
import helper.FileHelper;
import helper.JsonHelper;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class LogProcessor {

    private static final Float HOURS_32_IN_SECONDS = 115200F;
    private static final LocalTime RECORDING_DEADLINE = LocalTime.of(10, 0);

    public void processWorkLogs(final WorkLogWrapper workLog, final HolidayWrapper holidays) {
        final List<UserLog> userLogs = getUserLogs(workLog);
        final List<UserWeekLog> userWeekWeekLogs = userLogs.stream()
                .map(userLog -> this.getWeekLogs(userLog))
                .collect(Collectors.toList());
        final List<ResultWithPercentage> result1 = userWeekWeekLogs.stream()
                .map(userWeekLog -> calculateComparedPercentageOf32WorkingHours(userWeekLog)).collect(Collectors.toList());
        final List<ResultRecordingTime> result2 = userWeekWeekLogs.stream()
                .map(userWeekLog -> calculateRecordedHoursUntilMorning(userWeekLog,
                        holidays.getHoliday().stream()
                                .filter(userHoliday -> userHoliday.getAuthor().equals(userWeekLog.getUserId()))
                                .findFirst())).collect(Collectors.toList());

        FileHelper.writeFile("user_week_logs", JsonHelper.jsonObjectToText(userWeekWeekLogs));
        FileHelper.writeFile("result_1", JsonHelper.jsonObjectToText(result1));
        FileHelper.writeFile("result_2", JsonHelper.jsonObjectToText(result2));
    }

    private UserWeekLog getWeekLogs(final UserLog userLog) {
        // collecting all weeks where entries were made
        final Set<String> weekNumbers = userLog.getEntries().stream()
                .map(entry -> DateHelper.getWeekOfYearFromLocalDateTime(entry.getStartDate())).collect(Collectors.toSet());

        // map all log entries to weeks and users
        final List<WeekLog> weekLogs = weekNumbers.stream().map(week ->
                new WeekLog(week, userLog.getEntries().stream()
                        .filter(workLogEntry -> DateHelper.getWeekOfYearFromLocalDateTime(workLogEntry.getStartDate()).equals(week)).collect(Collectors.toList()))
        ).collect(Collectors.toList());

        return new UserWeekLog(userLog.getUserId(), weekLogs);
    }

    private List<UserLog> getUserLogs(final WorkLogWrapper workLog) {
        // collecting all unique authors
        final Set<String> authors = workLog.getWorklog().stream()
                .map(WorkLog::getEntries)
                .flatMap(List::stream)
                .map(WorkLogEntry::getAuthor).collect(Collectors.toSet());

        // map all log entries to authors
        return authors.stream().map(author ->
            new UserLog(author, workLog.getWorklog().stream()
                    .map(WorkLog::getEntries)
                    .flatMap(List::stream)
                    .filter(workLogEntry -> workLogEntry.getAuthor().equals(author)).collect(Collectors.toList()))
        ).collect(Collectors.toList());
    }

    private ResultWithPercentage calculateComparedPercentageOf32WorkingHours(final UserWeekLog userWeekLog) {

        final ResultWithPercentage result = new ResultWithPercentage(userWeekLog.getUserId());
        userWeekLog.getWeekLogs().forEach(weekLog -> {
            final Integer timeSpentOnWeekInSeconds = weekLog.getEntries().stream().map(WorkLogEntry::getTimeSpent)
                    .reduce(0, (a, b) -> a + b);
            final Float timeSpentPercentage = timeSpentOnWeekInSeconds / HOURS_32_IN_SECONDS * 100;
            result.addWeekPercentages(new WeekPercentage(weekLog.getWeekOfYear(), timeSpentPercentage));
        });
        return result;
    }

    public ResultRecordingTime calculateRecordedHoursUntilMorning(final UserWeekLog userWeekLog, final Optional<UserHoliday> userHolidays) {
        final List<WorkLogEntry> workLogEntries = userWeekLog.getWeekLogs().stream().map(WeekLog::getEntries).flatMap(List::stream).collect(Collectors.toList());
        final List<WorkLogEntry> entriesRecordedInTime = workLogEntries.stream().filter(workLogEntry -> wasRecordedInTime(workLogEntry, userHolidays)).collect(Collectors.toList());
        final Float percentage = workLogEntries.isEmpty() ? 0F : Integer.valueOf(entriesRecordedInTime.size()).floatValue() / Integer.valueOf(workLogEntries.size()).floatValue() * 100;
        return new ResultRecordingTime(userWeekLog.getUserId(), percentage);
    }

    public boolean wasRecordedInTime(final WorkLogEntry entry, final Optional<UserHoliday> userHolidays) {
        final Long daysToAdd = calculateDaysToAdd(entry, userHolidays);
        final LocalDateTime recordDeadline = entry.getStartDate().plusDays(daysToAdd).with(RECORDING_DEADLINE);
        return entry.getCreated().isBefore(recordDeadline);
    }

    public Long calculateDaysToAdd(final WorkLogEntry entry, final Optional<UserHoliday> userHolidays) {

        // no holidays recorded then just check if it is Friday
        if (!userHolidays.isPresent()) {
            return entry.getStartDate().getDayOfWeek() == DayOfWeek.FRIDAY ? 3L : 1L;
        }
        final UserHoliday userHoliday = userHolidays.get();
        LocalDate nextWorkingDay = null;
        long daysToAdd = entry.getStartDate().getDayOfWeek() == DayOfWeek.FRIDAY ? 3L : 1L;
        // find next working day which is not holiday and not weekend
        while (nextWorkingDay == null) {
            LocalDate date = entry.getStartDate().plusDays(daysToAdd).toLocalDate();
            if (!userHoliday.getHolidays().contains(date)
                    && date.getDayOfWeek() != DayOfWeek.SATURDAY
                    && date.getDayOfWeek() != DayOfWeek.SUNDAY) {
                nextWorkingDay = date;
            } else {
                daysToAdd++;
            }
        }
        return daysToAdd;
    }
}
