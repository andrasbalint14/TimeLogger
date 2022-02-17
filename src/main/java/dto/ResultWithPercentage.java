package dto;

import java.util.ArrayList;
import java.util.List;

public class ResultWithPercentage {

    private String userId;
    private List<WeekPercentage> weekPercentages = new ArrayList<>();

    public ResultWithPercentage(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<WeekPercentage> getWeekPercentages() {
        return weekPercentages;
    }

    public void setWeekPercentages(List<WeekPercentage> weekPercentages) {
        this.weekPercentages = weekPercentages;
    }

    public void addWeekPercentages(WeekPercentage weekPercentage) {
        this.weekPercentages.add(weekPercentage);
    }

    @Override
    public String toString() {
        return "ResultWithPercentage{" +
                "userId='" + userId + '\'' +
                ", weekPercentages=" + weekPercentages +
                '}';
    }
}
