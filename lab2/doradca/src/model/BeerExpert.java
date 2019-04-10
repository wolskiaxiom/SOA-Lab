package model;

public class BeerExpert {
    String color;
    String type;

    public BeerExpert(String color){
        this.color = color;
        if(color.equals("zloty")){
            this.type = "Lager";
        }else if(color.equals("czarny")){
            this.type = "Stout";
        }else if(color.equals("zielony")){
            this.type = "Raciborskie zielone";
        }else {
            this.type = "Nie mogę pomóc";
        }
    }

    @Override
    public String toString() {
        return this.type;
    }
}
