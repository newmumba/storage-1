package util;

import java.util.ArrayList;

public interface IRepository<T> {
    
    public int add();
    public ArrayList<T> getAll();
    public T getById(int id); 
    public void save();
}
