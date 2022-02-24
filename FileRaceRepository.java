package repository;
import model.*;
import exceptions.RepositoryException;
import java.io.*;
import java.util.Properties;

public class FileRaceRepository extends AbstractRepository<Race, Integer>{
    private String basicFileName;
    public FileRaceRepository(String BFileName)
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
    public FileRaceRepository(){
        SetFilenameFromProperties();
        ReadFromFile();
    };
    private void SetFilenameFromProperties(){
        try {
            Properties pr = new Properties();
            pr.load(new FileInputStream("src/Race.properties"));
            this.basicFileName = pr.getProperty("RaceFile");
            if(this.basicFileName == null){
                this.basicFileName = "src/race";
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
                if(elem.length != 5)
                {
                    System.err.println("Not a valid number of attributes "+ line);
                    continue;
                }
                Integer id = Integer.parseInt(elem[0]);
                String name = elem[1];
                String rtype = elem[2];
                String date = elem[3];
                Integer distance = Integer.parseInt(elem[4]);
                Race w = new Race(id,name,rtype,date,distance);
                super.add(w);
            }
        }catch (IOException ex) {
            throw new RepositoryException("Error reading" + ex.getMessage());
        }
    }

    private void writeToTxtFile(){
        try(PrintWriter pw = new PrintWriter(this.basicFileName)){
            for(Race w: findAll())
            {
                String line= w.getID() + ";"+ w.getName() + ";" + w.getRacetype()+ ";" + w.getDate() + ";" + w.getDistance();
                pw.println(line);
            }
        }
        catch (IOException ex)
        {
            throw new RepositoryException("Error writing "+ ex.getMessage());
        }
    }

    @Override
    public void add(Race obj) throws  RepositoryException {
        try{
            super.add(obj);
            writeToTxtFile();

        }
        catch(RuntimeException e)
        {
            throw new RepositoryException("Object wasn't added" + e + " " + obj);
        }
    }

    public void update(Race obj, Integer id) throws RepositoryException{
        try{
            super.update(obj, id);
            writeToTxtFile();
        }
        catch (RuntimeException e){
            throw new RepositoryException("Object wasn't updated "+ e + " " + obj);
        }
    }

    public void deleteById(Integer id) throws  RepositoryException{
        try{
            super.deleteById(id);

            writeToTxtFile();
        }
        catch (RuntimeException e){
            throw new RepositoryException("Object wasn't deleted "+ e);
        }
    }

}
