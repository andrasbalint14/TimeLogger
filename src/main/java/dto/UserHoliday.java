package dto;

import java.util.List;
import java.time.LocalDate;

public class UserHoliday {

    private String author;
    private List<LocalDate> holidays;

    public String getAuthor() {
        return author;
    }

    public List<LocalDate> getHolidays() {
        return holidays;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setHolidays(List<LocalDate> holidays) {
        this.holidays = holidays;
    }

    @Override
    public String toString() {
        return "UserHoliday{" +
                "author='" + author + '\'' +
                ", holidays=" + holidays +
                '}';
    }
}
