package UI;

import java.net.URL;
//import java.time.LocalDate;
import java.util.ResourceBundle;

import controller.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
//import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import repository.*;

public class ui implements Initializable{

    private final RaceController raceController;
    private final ObservableList<Race> raceList;

    private final RegistrationController regController;
    private final ObservableList<Registration> regList;

    private final FileRaceRepository w = new FileRaceRepository();
    private final FileRegistrationRepository e = new FileRegistrationRepository();

    // the fields for add Race
    @FXML private TextField id_tf;
    @FXML private TextField name_tf;
    @FXML private TextField Racetype_tf;
    @FXML private TextField date_tf;
    @FXML private TextField distance_tf;
    @FXML private Text ErrorRace_tf;

    //add Registration
    @FXML private TextField Rid_tf;
    @FXML private TextField Rname_tf;
    @FXML private TextField RphNr_tf;
    @FXML private TextField Raddress_tf;
    @FXML private TextField Rage_tf;
    @FXML private TextField Rrace_tf;
    @FXML private Text ErrorRegistration_tf;


    @FXML private TableView<Race> RaceTableView;
    @FXML private TableColumn<Race, Integer> IdCol;
    @FXML private TableColumn<Race, String> NameCol;
    @FXML private TableColumn<Race, String> racetypeCol;
    @FXML private TableColumn<Race, String> dateCol;
    @FXML private TableColumn<Race, Integer> distanceCol;



    @FXML private TableView<Registration> RTableView;
    @FXML private TableColumn<Registration, Integer> RIdCol;
    @FXML private TableColumn<Registration, String > RegNameCol;
    @FXML private TableColumn<Registration, String> phoneNrCol;
    @FXML private TableColumn<Registration, String> addressCol;
    @FXML private TableColumn<Registration, Integer> ageCol;
    @FXML private TableColumn<Registration, Integer> raceCol;

    public ui(){
        this.raceController = new RaceController(w);
        this.raceList = FXCollections.observableArrayList();

        this.regController = new RegistrationController(e);
        this.regList = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        IdCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        racetypeCol.setCellValueFactory(new PropertyValueFactory<>("racetype"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        distanceCol.setCellValueFactory(new PropertyValueFactory<>("distance"));

        for(Race elem : raceController.findAll()){
            raceList.add(elem);
        }

        RaceTableView.setItems(raceList);
        System.out.println(RaceTableView);


        RIdCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        RegNameCol.setCellValueFactory(new PropertyValueFactory<>("PersonName"));
        phoneNrCol.setCellValueFactory(new PropertyValueFactory<>("phoneNr"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        raceCol.setCellValueFactory(new PropertyValueFactory<>("race"));

        for(Registration elem : regController.findAll()){
            regList.add(elem);
        }
        RTableView.setItems(regList);
        System.out.println(RTableView);

    }
    @FXML public void resetRace(ActionEvent event){
        id_tf.setText("");
        name_tf.setText("");
        Racetype_tf.setText("");
        date_tf.setText("");
        distance_tf.setText("");

    }

    @FXML public void resetRegistration(ActionEvent event){
        Rid_tf.setText("");
        Rname_tf.setText("");
        RphNr_tf.setText("");
        Raddress_tf.setText("");
        Rage_tf.setText("");
        Rrace_tf.setText("");

    }


    @FXML public void addRace(ActionEvent event){
        ErrorRace_tf.setText("");
        String id = id_tf.getText();
        if(id.equals("")){
            id_tf.setStyle("-fx-border-color:red;");
            return;
        }
        String name = name_tf.getText();
        if(name.equals("")){
            name_tf.setStyle("-fx-border-color:red;");
            return;
        }
        String type = Racetype_tf.getText();
        if(type.equals("")){
            Racetype_tf.setStyle("-fx-border-color:red;");
            return;
        }
        String date = date_tf.getText();
        if(date.equals("")){
            date_tf.setStyle("-fx-border-color:red;");
            return;
        }

        String distance = distance_tf.getText();
        if(distance.equals("")){
            distance_tf.setStyle("-fx-border-color:red;");
            return;
        }


        try{
            Race race = new Race(Integer.parseInt(id),name,type,date,Integer.parseInt(distance));
            this.raceController.add(race);
            this.raceList.add(race);
        }catch (RuntimeException r)
        {
            ErrorRace_tf.setText("Something went wrong!");
            System.err.println("Error "+ r.getMessage());
            System.out.println();
        }
        System.out.println(this.raceController.findAll().toString());
        this.resetRace(event);
    }

    @FXML public void updateRace(ActionEvent event)  {
        ErrorRace_tf.setText("");
        String id = id_tf.getText();
        if(id.equals("")){
            id_tf.setStyle("-fx-border-color:red;");
            return;
        }
        String name = name_tf.getText();
        if(name.equals("")){
            name_tf.setStyle("-fx-border-color:red;");
            return;
        }
        String type = Racetype_tf.getText();
        if(type.equals("")){
            Racetype_tf.setStyle("-fx-border-color:red;");
            return;
        }
        String date = date_tf.getText();
        if(date.equals("")){
            date_tf.setStyle("-fx-border-color:red;");
            return;
        }

        String distance = distance_tf.getText();
        if(distance.equals("")){
            distance_tf.setStyle("-fx-border-color:red;");
            return;
        }
        try {
            this.raceController.update(new Race(Integer.parseInt(id),name, type, date, Integer.parseInt(distance)), Integer.parseInt(id)-1);
            this.raceList.set(Integer.parseInt(id)-1, new Race(Integer.parseInt(id),name, type, date, Integer.parseInt(distance)));
        }catch (RuntimeException r)
        {
            ErrorRace_tf.setText("Something went wrong!");
            System.err.println("Error "+ r.getMessage());
            System.out.println();
        }
        System.out.println(this.raceController.findAll().toString());
        this.resetRace(event);
    }

    @FXML public void deleteRace(ActionEvent event)  {
        ErrorRace_tf.setText("");
        String id = id_tf.getText();
        if(id.equals("")){
            id_tf.setStyle("-fx-border-color:red;");
            return;
        }

        try {
            for(Registration r : this.regController.findAll()) {
                if (r.getRace().getID().equals(Integer.parseInt(id))) {
                    ErrorRace_tf.setText("You cannot delete a race that has a registration in it");
                    return;
                }
            }
            this.raceController.deleteById((Integer.parseInt(id)));
            this.raceList.remove(Integer.parseInt(id)-1);
            for(int i = Integer.parseInt(id)-1;i<this.raceList.size();i++)
            {
                Race r = this.raceController.findById(i+2);
                Race w = r;
                r.setID(r.getID()-1);
                this.raceController.update(r,i+1);
                for(Registration reg: this.regController.findAll()){
                    if(reg.getRace().getID().equals(i+2))
                    {
                        reg.setRace(r);
                        this.regController.update(reg,reg.getID());
                        this.resetRegistration(event);
                    }

                }
                //this.raceList.set(i,r);
            }
        }catch (RuntimeException r)
        {
            ErrorRace_tf.setText("Something went wrong!");
            System.err.println("Error "+ r.getMessage());
            System.out.println();
        }
        System.out.println(this.raceController.findAll().toString());
        this.resetRace(event);
    }


    @FXML public void addRegistration(ActionEvent event){
        ErrorRegistration_tf.setText("");
        String id = Rid_tf.getText();
        if(id.equals("")){
            Rid_tf.setStyle("-fx-border-color:red;");
            return;
        }
        String name = Rname_tf.getText();
        if(name.equals("")){
            Rname_tf.setStyle("-fx-border-color:red;");
            return;
        }
        String type = RphNr_tf.getText();
        if(type.equals("")){
            RphNr_tf.setStyle("-fx-border-color:red;");
            return;
        }
        String minAge = Raddress_tf.getText();
        if(minAge.equals("")){
            Raddress_tf.setStyle("-fx-border-color:red;");
            return;
        }
        String maxAge = Rage_tf.getText();
        if(maxAge.equals("")){
            Rage_tf.setStyle("-fx-border-color:red;");
            return;
        }
        String minAget = Rrace_tf.getText();
        if(minAget.equals("")){
            Rrace_tf.setStyle("-fx-border-color:red;");
            return;
        }

        try{
            Registration e = new Registration(Integer.parseInt(id),name,type,minAge,Integer.parseInt(maxAge),this.raceController.findById(Integer.parseInt(minAget)));
            this.regController.add(e);
            this.regList.add(e);
        }catch (RuntimeException r)
        {
            ErrorRegistration_tf.setText("Something went wrong!");
            System.err.println("Error "+ r.getMessage());
            System.out.println();
        }
        System.out.println(this.regController.findAll().toString());
        this.resetRegistration(event);
    }

    @FXML public void updateRegistration(ActionEvent event){

        ErrorRegistration_tf.setText("");
        String id = Rid_tf.getText();
        if(id.equals("")){
            Rid_tf.setStyle("-fx-border-color:red;");
            return;
        }
        String name = Rname_tf.getText();
        if(name.equals("")){
            Rname_tf.setStyle("-fx-border-color:red;");
            return;
        }
        String type = RphNr_tf.getText();
        if(type.equals("")){
            RphNr_tf.setStyle("-fx-border-color:red;");
            return;
        }
        String minAge = Raddress_tf.getText();
        if(minAge.equals("")){
            Raddress_tf.setStyle("-fx-border-color:red;");
            return;
        }
        String maxAge = Rage_tf.getText();
        if(maxAge.equals("")){
            Rage_tf.setStyle("-fx-border-color:red;");
            return;
        }
        String minAget = Rrace_tf.getText();
        if(minAget.equals("")){
            Rrace_tf.setStyle("-fx-border-color:red;");
            return;
        }

        try{
            Registration e = new Registration(Integer.parseInt(id),name,type,minAge,Integer.parseInt(maxAge),this.raceController.findById(Integer.parseInt(minAget)));
            this.regController.update(e,Integer.parseInt(id)-1);
            this.regList.set(Integer.parseInt(id)-1,e);
        }catch (RuntimeException r)
        {
            ErrorRegistration_tf.setText("Something went wrong!");
            System.err.println("Error "+ r.getMessage());
            System.out.println();
        }
        System.out.println(this.regController.findAll().toString());
        this.resetRegistration(event);

    }

    @FXML public void deleteRegistration(ActionEvent event){
        ErrorRegistration_tf.setText("");
        String id = Rid_tf.getText();
        if(id.equals("")){
            Rid_tf.setStyle("-fx-border-color:red;");
            return;
        }

        try{
            this.regController.deleteById(Integer.parseInt(id)-1);
            this.regList.remove(Integer.parseInt(id)-1);
            for(int i = Integer.parseInt(id);i<this.regList.size();i++)
            {
                Registration r = this.regController.findById(Integer.parseInt(id)-1);
                r.setID(r.getID()-2);
                this.regController.update(r,Integer.parseInt(id)-1);
            }
                ErrorRace_tf.setText("You cannot delete a race that has a registration in it");
        }catch (RuntimeException r)
        {
            ErrorRegistration_tf.setText("Something went wrong!");
            System.err.println("Error "+ r.getMessage());
            System.out.println();
        }
        System.out.println(this.regController.findAll().toString());
        this.resetRegistration(event);

    }


}
