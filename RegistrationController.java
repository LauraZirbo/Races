package controller;

import model.Registration;
import repository.FileRegistrationRepository;

public class RegistrationController implements ControllerInterface<Registration, Integer>{
    private final FileRegistrationRepository repo;
    public RegistrationController(FileRegistrationRepository repo){this.repo = repo;}
    public void add(Registration elem){repo.add(elem);}
    public Registration findById(Integer id) {return repo.findById(id);}
    public void deleteById(Integer id){repo.deleteById(id);}
    public void update(Registration elem, Integer id){repo.update(elem,id);}
    public Iterable<Registration> findAll(){return repo.findAll();}

}
