package business;

import dto.*;
import helper.DateHelper;
import helper.FileHelper;
import helper.JsonHelper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LogProcessor {

    public void processWorkLogs(final WorkLogWrapper workLog) {
        final List<UserLog> userLogs = getUserLogs(workLog);
        final List<WeekLog> weekLogs = getWeekLogs(workLog);
        FileHelper.writeFile("user_logs", JsonHelper.jsonObjectToText(userLogs));
        FileHelper.writeFile("week_logs", JsonHelper.jsonObjectToText(weekLogs));
        System.out.println(userLogs);
        System.out.println(weekLogs);
    }

    private List<WeekLog> getWeekLogs(final WorkLogWrapper workLog) {
        // collecting all weeks where entries were made
        final Set<Integer> weekNumbers = workLog.getWorklog().stream()
                .map(WorkLog::getEntries)
                .flatMap(List::stream)
                .map(entry -> DateHelper.getWeekOfYearFromLocalDateTime(entry.getStartDate())).collect(Collectors.toSet());

        // map all log entries to weeks
        return weekNumbers.stream().map(week ->
                new WeekLog(week, workLog.getWorklog().stream()
                        .map(WorkLog::getEntries)
                        .flatMap(List::stream)
                        .filter(workLogEntry -> DateHelper.getWeekOfYearFromLocalDateTime(workLogEntry.getStartDate()).equals(week)).collect(Collectors.toList()))
        ).collect(Collectors.toList());
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
}
