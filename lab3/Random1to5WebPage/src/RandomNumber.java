import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "RandomNumber")
@RequestScoped
public class RandomNumber {
    public int randomInt = (int)(Math.random()*5+1);

    public  int getRandomInt() {
        return randomInt;
    }

    public static void setRandomInt(int randomInt) {
        randomInt = randomInt;
    }

    public RandomNumber() {
    }

    public String isNumberEqual(int chosenNumber){
        if (chosenNumber == this.randomInt){
            return "YES";
        }else{
            return "NO";
        }
    }
}
