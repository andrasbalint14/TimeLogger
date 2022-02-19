package dto;

import java.util.List;

public class WeekLog {

    private String weekOfYear;
    private List<WorkLogEntry> entries;

    public WeekLog(String weekOfYear, List<WorkLogEntry> entries) {
        this.weekOfYear = weekOfYear;
        this.entries = entries;
    }

    public String getWeekOfYear() {
        return weekOfYear;
    }

    public void setWeekOfYear(String weekOfYear) {
        this.weekOfYear = weekOfYear;
    }

    public List<WorkLogEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<WorkLogEntry> entries) {
        this.entries = entries;
    }
}
