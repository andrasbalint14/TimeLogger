package dto;

import java.util.List;

public class WorkLog {

    private String key;
    private String summary;
    private List<WorkLogEntry> entries;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<WorkLogEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<WorkLogEntry> entries) {
        this.entries = entries;
    }

    @Override
    public String toString() {
        return "WorkLog{" +
                "key='" + key + '\'' +
                ", summary='" + summary + '\'' +
                ", entries=" + entries +
                '}';
    }
}
