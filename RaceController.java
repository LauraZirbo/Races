package controller;

import model.Race;
import repository.FileRaceRepository;

public class RaceController implements ControllerInterface<Race, Integer>{
    private final FileRaceRepository repo;
    public RaceController(FileRaceRepository repo){this.repo = repo;}
    public void add(Race elem){repo.add(elem);}
    public Race findById(Integer id) {return repo.findById(id);}
    public void deleteById(Integer id){repo.deleteById(id);}
    public void update(Race elem, Integer id){repo.update(elem,id);}
    public Iterable<Race> findAll(){return repo.findAll();}

}
