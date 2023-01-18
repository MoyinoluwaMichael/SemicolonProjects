package christmassSnacks.checkOutApp;

import java.time.LocalDate;
import java.time.LocalTime;

public class Time {
    private final int hour = LocalTime.now().getHour();
    private final int minute = LocalTime.now().getMinute();
    private final int second = LocalTime.now().getSecond();
    private final int year = LocalDate.now().getYear();
    private final int month = LocalDate.now().lengthOfMonth();
    private final int day = LocalDate.now().getDayOfMonth();


    public String getTime(){
        return String.format("%s:%s:%s", hour, minute, second);
    }
    public String getDate(){
        return String.format("%s/%s/%s", day, month, year);
    }
    @Override
    public String toString(){
        return String.format("%s %s", getDate(), getTime());
    }
}
