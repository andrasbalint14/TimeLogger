package dto;

import java.util.List;

public class UserWeekLog {

    private String userId;
    private List<WeekLog> weekLogs;

    public UserWeekLog(String userId, List<WeekLog> weekLogs) {
        this.userId = userId;
        this.weekLogs = weekLogs;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<WeekLog> getWeekLogs() {
        return weekLogs;
    }

    public void setWeekLogs(List<WeekLog> weekLogs) {
        this.weekLogs = weekLogs;
    }
}
