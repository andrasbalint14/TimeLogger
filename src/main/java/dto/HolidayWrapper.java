package dto;

import java.util.List;

public class HolidayWrapper {

    private List<UserHoliday> holiday;

    public List<UserHoliday> getHoliday() {
        return holiday;
    }

    public void setHoliday(List<UserHoliday> holiday) {
        this.holiday = holiday;
    }

    @Override
    public String toString() {
        return "HolidayWrapper{" +
                "holiday=" + holiday +
                '}';
    }
}
