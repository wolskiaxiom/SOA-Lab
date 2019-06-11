package pl.agh.soa.dashboard.beans;

import pl.agh.soa.ejb.dashboard.UserControllerInterface;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named
public class UserBean implements Serializable {

    @EJB(lookup = "java:global/parking-ejb-impl-1.0/UserController")
    UserControllerInterface userController;

    private boolean isChanging = false;
    private String userLogin = "";
    private String newPassword="";

    public UserBean() {
    }

    public String getUserLogin() {
        return userLogin=userController.getUserLogin();
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public void changePassword(){
        userController.changePassword(newPassword);
    }

    public boolean isChanging() {
        return isChanging;
    }

    public void setChanging(boolean changing) {
        isChanging = changing;
    }

    public void showForm(){
        if(!isChanging)isChanging = true;
        else{isChanging = false;}
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
