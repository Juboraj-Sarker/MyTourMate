package juborajsarker.mytourmate.weatheractivity;

/**
 * Created by dell on 1/23/2017.
 */

public class GetCurrentDateTime {
    public String hour;
    public String min;
    public String ampm;
    public String year;
    public String day;
    public String month;

    public GetCurrentDateTime(String hour, String min, String ampm, String year, String day, String month) {
        this.hour = hour;
        this.min = min;
        this.ampm = ampm;
        this.year = year;
        this.day = day;
        this.month = month;
    }

    public GetCurrentDateTime(String hour, String min) {
        this.hour = hour;
        this.min = min;
    }

    public GetCurrentDateTime(String year, String day, String month) {
        this.year = year;
        this.day = day;
        this.month = month;
    }

    public String getHour() {
        return hour;
    }

    public String getMin() {
        return min;
    }

    public String getAmpm() {
        return ampm;
    }

    public String getYear() {
        return year;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }
}
