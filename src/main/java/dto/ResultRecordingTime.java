package dto;

public class ResultRecordingTime {

    private String userId;
    private Float percentage;

    public ResultRecordingTime(String userId, Float percentage) {
        this.userId = userId;
        this.percentage = percentage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Float getPercentage() {
        return percentage;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }
}
