package repository;

import exceptions.RepositoryException;
import model.Race;
import model.Registration;
import java.io.*;
import java.util.Properties;

public class FileRegistrationRepository extends AbstractRepository<Registration, Integer>{
    private String basicFileName;
    public FileRegistrationRepository(String BFileName)
    {
        this.basicFileName = BFileName;
        try {
            this.ReadFromFile();
        }catch (RepositoryException n)
        {
            System.err.println("Error "+ n.getMessage());
            System.out.println();
        }
    }
    public FileRegistrationRepository(){
        SetFilenameFromProperties();
        ReadFromFile();
    };
    private void SetFilenameFromProperties(){
        try {
            Properties pr = new Properties();
            pr.load(new FileInputStream("src/Race.properties"));
            this.basicFileName = pr.getProperty("RegistrationFile");
            if(this.basicFileName == null){
                this.basicFileName = "src/registration";
                System.err.println("Games not fount. Using default" + this.basicFileName);
            }
        }catch (IOException ex){
            System.err.println("Error reading the configuration file" +ex);
        }
    }

    public void ReadFromFile(){
        try(BufferedReader reader = new BufferedReader(new FileReader(this.basicFileName))){
            String line;
            while ((line = reader.readLine())!=null){
                String[] elem = line.split(";");
                if(elem.length != 6)
                {
                    System.err.println("Not a valid number of attributes "+ line);
                    continue;
                }
                FileRaceRepository r = new FileRaceRepository();
                Integer id = Integer.parseInt(elem[0]);
                String name = elem[1];
                String phoneNr = elem[2];
                String address = elem[3];
                Integer age = Integer.parseInt(elem[4]);
                Race race = r.findById(Integer.parseInt(elem[5]));
                Registration w = new Registration(id,name,phoneNr,address,age,race);
                this.add(w);
            }
        }catch (IOException ex) {
            throw new RepositoryException("Error reading" + ex.getMessage());
        }
    }

    private void writeToTxtFile(){
        try(PrintWriter pw = new PrintWriter(this.basicFileName)){
            for(Registration w: findAll())
            {
                String line= w.getID() + ";"+ w.getPersonName() + ";" + w.getPhoneNr()+ ";" + w.getAddress() + ";" + w.getAge() + ";" + w.getRace().getID();
                pw.println(line);
            }
        }
        catch (IOException ex)
        {
            throw new RepositoryException("Error writing "+ ex.getMessage());
        }
    }

    @Override
    public void add(Registration obj) throws  RepositoryException {
        try{
            if(obj.getRace().getRacetype().equals("kids"))
            {
                if (obj.getAge()>=6 && obj.getAge()<=11)
                {
                    super.add(obj);
                    writeToTxtFile();
                }
            }
            if(obj.getRace().getRacetype().equals("teens"))
            {
                if (obj.getAge()>=12 && obj.getAge()<=17)
                {
                    super.add(obj);
                    writeToTxtFile();
                }
            }
            if(obj.getRace().getRacetype().equals("adults"))
            {
                if (obj.getAge()>=18 && obj.getAge()<=60)
                {
                    super.add(obj);
                    writeToTxtFile();
                }
            }
            if(obj.getRace().getRacetype().equals("seniors"))
            {
                if (obj.getAge()>=61)
                {
                    super.add(obj);
                    writeToTxtFile();
                }
            }
//            super.add(obj);
//            writeToTxtFile();
        }
        catch(RuntimeException e)
        {
            throw new RepositoryException("Object wasn't added" + e + " " + obj);
        }
    }

    public void update(Registration obj, Integer id) throws RepositoryException{
        try{
            super.update(obj, id);
            writeToTxtFile();
        }
        catch (RuntimeException e){
            throw new RepositoryException("Object wasn't updated "+ e + " " + obj);
        }
    }

    public void deleteById(Integer id) throws RepositoryException{
        try{
            super.deleteById(id);
            writeToTxtFile();
        }
        catch (RuntimeException ex)
        {
            throw new RuntimeException("Obbject od id: " + id +" wasan't deleted" + ex);
        }
    }
}
