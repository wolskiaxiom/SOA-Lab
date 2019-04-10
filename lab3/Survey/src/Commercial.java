import javax.ejb.Startup;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean(name = "commercial")
@ApplicationScoped
public class Commercial implements Serializable {

    private int counter;

    public Commercial(){
        this.counter = 0;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void addClick(){
        counter++;
    }

}
