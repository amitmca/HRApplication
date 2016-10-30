package kelley.josh.Model;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by joshuakelley on 10/27/16.
 */
public class DateManager {

    public static double calculateWorkHours(Date start, Date end){

        return (double)(end.getTime()-start.getTime())/3600000;
    }

    public static double calculatePayForPeriod(List<TimeCard> timeCardList,Employee employee){
        double hours = 0;
        double shiftHours;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM d, yy HH:mm:ss");
        for(TimeCard timeCard: timeCardList){
            shiftHours = calculateWorkHours(simpleDateFormat.parse(timeCard.getStart(),new ParsePosition(0)),simpleDateFormat.parse(timeCard.getEnd(),new ParsePosition(0)));
            if(timeCard.isOvertime())shiftHours*=1.5;
            hours += shiftHours;
        }
        return hours*employee.getHourlyPayRate();
    }
}
