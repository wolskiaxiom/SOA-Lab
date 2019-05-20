package service;

import java.net.MalformedURLException;
import java.net.URL;

public class HolidayServiceClient {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/holiday_service_war_exploded/HolidayService?wsdl");

        HolidayServiceService holidayService_Service
                = new HolidayServiceService(url);
        HolidayService holidayService = holidayService_Service.getHolidayServicePort();

        long numberOfDays = holidayService.getNumberOfDays();

        System.out.println(numberOfDays);

    }
}
