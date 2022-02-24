package controller;

public interface ControllerInterface <T,Tid> {
    void add(T elem);
    T findById (Tid id);
    void deleteById(Tid id);
    void update (T elem, Tid id);
    Iterable<T> findAll();

}
