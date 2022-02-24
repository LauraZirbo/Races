package model;

public class Registration implements Identifiable<Integer>{
    private int ID;
    private String PersonName;
    private String phoneNr;
    private String address;
    private int age;
    private Race race;

    public Registration(int Id, String name, String type, String minAge, int maxAge, Race date)
    {
        this.ID = Id;
        this.PersonName = name;
        this.phoneNr = type;
        this.address = minAge;
        this.age = maxAge;
        this.race = date;
    }
    public Registration(){
        this.ID= 0;
        this.PersonName = "";
        this.phoneNr = "";
        this.address = "";
        this.age = 0;
        this.race = null;
    }

    @Override
    public String toString(){ return "Applicant: "+ this.ID+ ", firstname: "+ this.PersonName + " ,lastname: " + this.phoneNr + ", age: " + this.address + ", gender: " + this.age + ", job: " + this.race; }
    @Override
    public Integer getID(){return ID;}
    @Override
    public void setID(Integer id) {this.ID = id;}
    public String getPersonName(){return this.PersonName;}
    public void setPersonName(String personName){this.PersonName = personName;}
    public String getPhoneNr(){return this.phoneNr;}
    public void setPhoneNr(String phoneNr){this.phoneNr = phoneNr;}
    public String getAddress(){return this.address;}
    public void setAddress(String address){this.address = address;}
    public Integer getAge(){return this.age;}
    public void setAge(Integer age){this.age = age;}
    public Race getRace(){return this.race;}
    public void setRace(Race race){this.race = race;}

}
