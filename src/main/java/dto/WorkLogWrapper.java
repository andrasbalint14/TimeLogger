package dto;

import java.util.List;
import java.time.LocalDateTime;

public class WorkLogWrapper {

    private List<WorkLog> worklog;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public List<WorkLog> getWorklog() {
        return worklog;
    }

    public void setWorklog(List<WorkLog> worklog) {
        this.worklog = worklog;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "WorkLogWrapper{" +
                "worklog=" + worklog +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
