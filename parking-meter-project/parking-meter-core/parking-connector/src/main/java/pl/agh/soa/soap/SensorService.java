package pl.agh.soa.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class SensorService {

    @WebMethod
    public void occupyPlace(int areaId, int sensorId){
        System.out.println("asdfasdf");
    }
    @WebMethod
    public void releasePlace(int areaId, int sensorId){
        System.out.println("asdfasdf");
    }



}
