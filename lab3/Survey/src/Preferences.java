import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;

@ApplicationScoped
@ManagedBean
public class Preferences {
    String moneyOnShopping;
    String howOften;
    String preferedColor;
    String [] preferedType;
    public String getMoneyOnShopping() {
        return moneyOnShopping;
    }

    public void setMoneyOnShopping(String moneyOnShopping) {
        this.moneyOnShopping = moneyOnShopping;
    }

    public String getHowOften() {
        return howOften;
    }

    public void setHowOften(String howOften) {
        this.howOften = howOften;
    }

    public String getPreferedColor() {
        return preferedColor;
    }

    public void setPreferedColor(String preferedColor) {
        this.preferedColor = preferedColor;
    }

    public String[] getPreferedType() {
        return preferedType;
    }

    public void setPreferedType(String[] preferedType) {
        this.preferedType = preferedType;
    }


}
