import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "Counter")
@SessionScoped
public class Counter {
    int counter=0;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Counter(){}

    public Counter(int counter) {
        this.counter = counter;
    }

    public int increment(){
        counter++;
        return  counter;
    }
}
