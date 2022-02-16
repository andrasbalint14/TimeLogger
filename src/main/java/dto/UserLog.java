package dto;

import java.util.List;

public class UserLog {

    private String userId;
    private List<WorkLogEntry> entries;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<WorkLogEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<WorkLogEntry> entries) {
        this.entries = entries;
    }

    public UserLog(String userId, List<WorkLogEntry> entries) {
        this.userId = userId;
        this.entries = entries;
    }

    @Override
    public String toString() {
        return "UserLog{" +
                "userId='" + userId + '\'' +
                ", entries=" + entries +
                '}';
    }
}
