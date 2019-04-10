import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
@ApplicationScoped
@ManagedBean(name = "femaleModel", eager = true)
public class Female {
    private int bust;
    private String cupSize;
    private int waist;
    private int hips;
    private int legLength;

    public Female(int bust, String cupSize, int waist, int hips, int legLength) {
        this.bust = bust;
        this.cupSize = cupSize;
        this.waist = waist;
        this.hips = hips;
        this.legLength = legLength;
    }

    public Female() {
    }

    public int getBust() {
        return bust;
    }

    public void setBust(int bust) {
        this.bust = bust;
    }

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public int getWaist() {
        return waist;
    }

    public void setWaist(int waist) {
        this.waist = waist;
    }

    public int getHips() {
        return hips;
    }

    public void setHips(int hips) {
        this.hips = hips;
    }

    public int getLegLength() {
        return legLength;
    }

    public void setLegLength(int legLength) {
        this.legLength = legLength;
    }


}