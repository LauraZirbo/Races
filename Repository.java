package repository;
import model.*;
import java.util.Arrays;
public interface Repository <T,Tid> {
    void add(T elem);
    T findById (Tid id);
    void update(T elem, Tid id);
    void deleteById(Tid id);
    Iterable<T> findAll();
}