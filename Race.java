package model;

public class Race implements Identifiable<Integer>{
    private int ID;
    private String name;
    private String racetype;
    private String date;
    private int distance;


    public Race(int Id, String name, String type, String minAge, int maxAge)
    {
        this.ID = Id;
        this.name = name;
        this.racetype = type;
        this.date = minAge;
        this.distance = maxAge;

    }
    public Race(){
        this.ID= 0;
        this.name = "";
        this.racetype = "";
        this.date = "";
        this.distance = 0;

    }

    @Override
    public String toString(){ return "Race: "+ this.ID+ ", name: "+ this.name + " ,type: " + this.racetype + ", set on: " + this.date + ", distance: " + this.distance + "km."; }
    @Override
    public Integer getID(){return ID;}
    @Override
    public void setID(Integer id) {this.ID = id;}
    public String getName(){return this.name;}
    public void setName(String name){this.name = name;}
    public String getRacetype(){return this.racetype;}
    public void setRacetype(String racetype){this.racetype = racetype;}
    public String  getDate(){return this.date;}
    public void setDate(String date){this.date = date;}
    public Integer getDistance(){return this.distance;}
    public void setDistance(Integer distance){this.distance = distance;}

}
