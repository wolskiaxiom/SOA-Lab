package pl.agh.soa.ejb.impl.dashboard;

import pl.agh.soa.datasource.database.UserDetailsManager;
import pl.agh.soa.datasource.database.UserManager;
import pl.agh.soa.datasource.entities.User;
import pl.agh.soa.ejb.dashboard.UserControllerInterface;

import javax.annotation.Resource;
import javax.ejb.DependsOn;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import java.security.Principal;

@Stateless
public class UserController implements UserControllerInterface {

    @Resource
    SessionContext ctx;

    @Override
    public String getUserLogin(){
        Principal principal=ctx.getCallerPrincipal();
        return principal.getName();
    }

    @Override
    public void changePassword(String decodedPassword){
        User user = new User();
        user.setLogin(getUserLogin());
        user.setPassword(decodedPassword);
        UserManager.updateUser(user);
    }

}
