package juborajsarker.mytourmate.data;

import java.util.ArrayList;

/**
 * Created by dell on 1/25/2017.
 */

public class ForcasetDetails {
    ArrayList<ForcasetDetails> forcasetDetails;
    private int code;
    private String date;
    private String day;
    private String text;
    private String high;
    private String low;

    public ForcasetDetails(int code, String date, String day, String text, String high, String low) {
        this.code = code;
        this.date = date;
        this.day = day;
        this.text = text;
        this.high = high;
        this.low = low;

    }

    public ForcasetDetails(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public ArrayList<ForcasetDetails> getForcasetDetails() {
        return forcasetDetails;
    }

    public int getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

    public String getText() {
        return text;
    }

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }
}
