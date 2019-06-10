package pl.agh.soa.app;

import pl.agh.soa.soap.client.SensorService;
import pl.agh.soa.soap.client.SensorServiceService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class SensorSenderApp {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/parking_connector/SensorService?wsdl");

        SensorServiceService sensorService
                = new SensorServiceService(url);
        SensorService service = sensorService.getSensorServicePort();

        Scanner sc = new Scanner(System.in);
        int areaId, sensorId;
        while(true){
            System.out.println("Wprowadź 0 aby zakończyć\n" +
                    "Wprowadź 1 aby zgłosić przyjazd samochodu\n" +
                    "Wprowadź 2 aby zgłosić odjazd samochodu\n");
            int i = sc.nextInt();
            if(i==0)break;
            else if(i==1){
                System.out.println("Wprowadź numer obszaru");
                areaId = sc.nextInt();
                System.out.println("Wprowadź numer czujnika");
                sensorId = sc.nextInt();

                service.occupyPlace(areaId,sensorId,System.currentTimeMillis());
            }
            else if(i==2){
                System.out.println("Wprowadź numer obszaru");
                areaId = sc.nextInt();
                System.out.println("Wprowadź numer czujnika");
                sensorId = sc.nextInt();

                service.releasePlace(areaId,sensorId, System.currentTimeMillis());
            }else{
                System.out.println("Coś poszło nie tak!");
            }
        }
    }
}
