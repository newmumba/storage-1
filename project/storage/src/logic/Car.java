package logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import util.CreatingConnection;
import util.IRepository;

public class Car implements IRepository<Car>{
    
    CreatingConnection con = CreatingConnection.getInstance();
    SimpleDateFormat formDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ArrayList<PackingList> packingLists;
    PackingList _packingList;
    
    private int id;
    private String name;
    private double size;
    private boolean state;
    private Date date;
    
    public Car() { }
    
    public Car(String name, double size, boolean state, Date date){
        this.name = name;
        this.size = size;
        this.state = state;
        this.date = date;
    }
    
    public Car(int id, String name, double size, boolean state, Date date){
        this.id = id;
        this.name = name;
        this.size = size;
        this.state = state;
        this.date = date;
    }
    
    public void setName(String name)    {this.name = name;}
    public void setSize(double size)    {this.size = size;}
    public void setState(boolean state) {this.state = state;}
    public void setDate(Date date)      {this.date = date;}
    
    public int getId()          {return id;}
    public String getName()     {return name;}
    public double getSize()     {return size;}
    public boolean getState()   {return state;}
    public Date getDate()       {return date;}
    
    @Override
    public int add() {
        String insertSQL;
        insertSQL = "INSERT INTO CARS(NAME,SIZE,STATE,DATE) VALUES('{name}', {size}, {state}, '{date}')";
        insertSQL = insertSQL.replace("{name}", this.name)
            .replace("{size}", ""+this.size)
            .replace("{state}", ""+this.state)
            .replace("{date}", ""+formDate.format(this.date));
        return con.setRecord(insertSQL);
    }

    @Override
    public ArrayList<Car> getAll() {
        ArrayList<Car> cars = new ArrayList<>();
        String selectSQL;
        ResultSet rs;
        selectSQL = "SELECT * FROM CARS";
        try {
            rs = con.getRecord(selectSQL);
            while (rs.next()) {
                cars.add(new Car(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getBoolean(4),rs.getTimestamp(5)));      
            }
        } catch (SQLException ex) {
            return null;
        }
        return cars;
    }

    @Override
    public Car getById(int id) {
        String selectSQL;
        ResultSet rs;
        selectSQL = "SELECT * FROM CARS WHERE ID="+id;
        try {
            rs = con.getRecord(selectSQL);
            if (rs != null){
                rs.next();
                return new Car(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getBoolean(4),rs.getTimestamp(5));
            }
        } catch (SQLException ex) {}
        return null;
    }

    @Override
    public void save() {
        String updateSQL;
        updateSQL = "UPDATE CARS SET name='{name}', size={size}, state={state}, date='{date}' WHERE id={id}";
        updateSQL = updateSQL.replace("{name}", this.name)
            .replace("{size}", ""+this.size)
            .replace("{state}", ""+this.state)
            .replace("{date}", ""+formDate.format(this.date))
            .replace("{id}", ""+this.id);
        con.setRecord(updateSQL);
    }
    
    public ArrayList<Car> getFree() {
        ArrayList<Car> cars = new ArrayList<>();
        String selectSQL;
        ResultSet rs;
        selectSQL = "select * from CARS WHERE STATE = TRUE ORDER BY DATE;";
        try {
            rs = con.getRecord(selectSQL);
            while (rs.next()) {
                cars.add(new Car(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getBoolean(4),rs.getTimestamp(5)));      
            }
        } catch (SQLException ex) {
            return null;
        }
        return cars;
    }
    
    //Назначить машину
    public void Approve(){
        this.state = false;
        save();
    }
    
    //Вернуть машину в гараж
    public void InFree(){
        this.state = true;
        save();
    }
    
    public PackingList getPackingList(){
        if(this.state == false) this.InFree();
        _packingList = new PackingList();
        packingLists = new ArrayList<>();
        packingLists = _packingList.getLine();
        for (PackingList packingList : packingLists) {
            if(packingList.getSize()<=this.size){
                Approve();
                packingList.NextState();
                packingList.setCar(this);
                packingList.save();
                break;
            }
        }
        return null;
    }
}