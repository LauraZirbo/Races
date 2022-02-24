package repository;

import model.*;
import java.util.*;
public abstract class AbstractRepository <T extends Identifiable<ID>,ID> implements Repository<T,ID> {
    protected ArrayList<T> elem;

    public AbstractRepository() {elem = new ArrayList<>();}
    public void add(T el){
        if(elem.contains(el))
            throw new RuntimeException("Element already exists!!!");
        else
            elem.add(el);
    }


    public T findById(ID id) {
        for(int i = 0; i<elem.size();i++)
            if(elem.get(i).getID() == id)
                return elem.get(i);
        return null;
    }

    public void update(T e, ID id){
        for(int i = 0;i<elem.size();i++)
            if(elem.get(i).getID() == id)
                elem.set(i,e);
    }

    public void deleteById(ID id){
        for(int i =0;i<elem.size();i++)
            if(elem.get(i).getID() == id)
                elem.remove(i);
    }

    public Iterable<T> findAll(){return elem;}

}
