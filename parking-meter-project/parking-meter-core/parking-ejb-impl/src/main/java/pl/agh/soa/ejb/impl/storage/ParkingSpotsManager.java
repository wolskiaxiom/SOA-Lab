package pl.agh.soa.ejb.impl.storage;


import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
@DependsOn({"ParkingSpotsStorage"})
public class ParkingSpotsManager {



}
