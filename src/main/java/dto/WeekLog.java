package dto;

import java.util.List;

public class WeekLog {

    private Integer weekOfYear;
    private List<WorkLogEntry> entries;

    public WeekLog(Integer weekOfYear, List<WorkLogEntry> entries) {
        this.weekOfYear = weekOfYear;
        this.entries = entries;
    }

    public Integer getWeekOfYear() {
        return weekOfYear;
    }

    public void setWeekOfYear(Integer weekOfYear) {
        this.weekOfYear = weekOfYear;
    }

    public List<WorkLogEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<WorkLogEntry> entries) {
        this.entries = entries;
    }

    @Override
    public String toString() {
        return "WeekLog{" +
                "weekOfYear=" + weekOfYear +
                ", entries=" + entries +
                '}';
    }
}
