package kelley.josh.Model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by joshuakelley on 10/25/16.
 */
@Entity
@Table(name = "TimeCards")
public class TimeCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long timeCardId;
    private String start;
    private String end;
    private int employeeID;
    private boolean overtime;
    //double hours

    TimeCard(){
    }

    public TimeCard(String start, String end, int employeeID, boolean overtime) {
        this.start = start;this.end = end;this.employeeID = employeeID;this.overtime = overtime;
    }

//    public TimeCard(){

    public void setStart(String start) {
        this.start = changeTime(start);
    }

    public void setEnd(String end) {
        this.end = changeTime(end);
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setOvertime(boolean overtime) {
        this.overtime = overtime;
    }
//        //this.start = changeTime(start);this.end = changeTime(end);this.employeeID = employeeID;this.overtime = overtime;
//    }

    public String getStart() {
        return start;
    }

    public long getTimeCardId() {
        return timeCardId;
    }

    public void setTimeCardId(long timeCardId) {
        this.timeCardId = timeCardId;
    }

    public String getEnd() {
        return end;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public boolean isOvertime() {
        return overtime;
    }

    public enum Months{
        Jan("01"), Feb("02"), Mar("03"), Apr("04"), May("05"), Jun("06"), Jul("07"), Aug("08"), Sep("09"), Oct("10"), Nov("11"), Dec("12");

        Months(String a){

        }

        static final Months[] months = Months.values();
    }

    private String changeTime(String s) {
        StringBuilder sb = new StringBuilder();
        int hourDigit = Integer.parseInt(s.substring(11,13));
        sb.append(Months.months[Integer.parseInt(s.substring(5,7))-1])
//        "2016-01-01T06:00:00.000Z"
                .append(" ")
//                .append(s.substring(8,10))
                .append(checkDay(hourDigit,s.substring(8,10)))
                .append(", ")
                .append(s.substring(0,4))
                .append(" ")
                .append(changeHours(hourDigit))
//                .append(s.substring(11,19));
                .append(s.substring(13,19));
        return sb.toString();
    }

    private String changeHours(int hour){
        int newNum = (hour+19)%24;
        String switchedNumber = addZero(newNum);
        return switchedNumber;
    }

    private String checkDay(int hour, String s){
        if(hour>5)return s;
        int oldNum = Integer.parseInt(s);
        String newDate = addZero(oldNum-1);
        return newDate;
    }

    private String addZero(int num){
        if(num<10)return "0"+new Integer(num).toString();
        return new Integer(num).toString();
    }

    @Override
    public String toString(){
        String o =(overtime)?"":"not";
        return String.format("Employee #%d began their shift at %s and ended at %s. The shift was "+o+"considered overtime.",employeeID,start,end);
    }
}
