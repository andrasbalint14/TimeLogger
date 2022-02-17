package dto;

public class WeekPercentage {

    private Integer week;
    private Float percentage;

    public WeekPercentage(Integer week, Float percentage) {
        this.week = week;
        this.percentage = percentage;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
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
