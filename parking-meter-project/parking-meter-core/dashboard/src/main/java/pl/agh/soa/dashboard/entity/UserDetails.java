package pl.agh.soa.dashboard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_details")
public class UserDetails {

    @Id
    @Column(name = "login")
    String login;

    @Column(name = "area")
    int areaId;

    public UserDetails() {
    }

    public UserDetails(String login, int areaId) {
        this.login = login;
        this.areaId = areaId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }
}
