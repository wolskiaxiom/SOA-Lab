package service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;

@WebService
public class HolidayService {

    @WebMethod
    public long getNumberOfDays(){
        LocalDate today = LocalDate.now();
        //29-July-2017, change this to your desired End Date
        LocalDate firstDay = LocalDate.of(2019, Month.JULY, 29);
        long noOfDaysBetween = ChronoUnit.DAYS.between(today, firstDay);
        System.out.println(noOfDaysBetween);

        return noOfDaysBetween;
    }
}
