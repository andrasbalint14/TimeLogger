package dto;

public class WeekPercentage {

    private String week;
    private Float percentage;

    public WeekPercentage(String week, Float percentage) {
        this.week = week;
        this.percentage = percentage;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public Float getPercentage() {
        return percentage;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "WeekPercentage{" +
                "week=" + week +
                ", percentage=" + percentage +
                '}';
    }
}
