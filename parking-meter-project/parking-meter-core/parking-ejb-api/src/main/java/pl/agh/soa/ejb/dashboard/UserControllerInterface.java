package pl.agh.soa.ejb.dashboard;

import javax.ejb.Remote;

@Remote
public interface UserControllerInterface {
    String getUserLogin();

    void changePassword(String decodedPassword);
}
