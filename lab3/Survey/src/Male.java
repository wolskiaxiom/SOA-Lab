import javax.faces.bean.ManagedBean;

@ManagedBean(name = "maleModel")
public class Male {
    private int chest;
    private int pas;

    public Male() {
    }

    private int manLegLength;
    public int getChest() {
        return chest;
    }

    public void setChest(int chest) {
        this.chest = chest;
    }

    public int getPas() {
        return pas;
    }

    public void setPas(int pas) {
        this.pas = pas;
    }

    public int getManLegLength() {
        return manLegLength;
    }

    public void setManLegLength(int manLegLength) {
        this.manLegLength = manLegLength;
    }

    public Male(int chest, int pas, int manLegLength) {
        this.chest = chest;
        this.pas = pas;
        this.manLegLength = manLegLength;
    }


}