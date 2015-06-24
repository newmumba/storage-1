package logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import util.CreatingConnection;
import util.IRepository;

public class PackingList implements IRepository<PackingList>{
    
    ArrayList<Order> orders;
    ArrayList<Car> cars;
    Customer _customer;
    PackingList _packingList;
    
    CreatingConnection con = CreatingConnection.getInstance();
    SimpleDateFormat formDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String PackingListState1 = "Открыта";
    String PackingListState2 = "В очереди";
    String PackingListState3 = "Закрыта";
    
    Car _car;
    District _district;
        
    private int id;
    private double size;
    private District district;
    private String state;
    private Date firsDate;
    private Car car;
    
    public PackingList(){ }
    
    public PackingList(double size, District district, String state, Date firsDate, Car car){
        this.size = size;
        this.district = district;
        this.state = state;
        this.firsDate = firsDate;
        this.car = car;
    }
    
    public PackingList(int id, double size, District district, String state, Date firsDate, Car car){
        this.id = id;
        this.size = size;
        this.district = district;
        this.state = state;
        this.firsDate = firsDate;
        this.car = car;
    }
    
    public void setId(int id)                   {this.id = id;}
    public void setSize(double size)            {this.size = size;}
    public void setDistrict(District district)  {this.district = district;}
    public void setState(String state)          {this.state = state;}
    public void setFirsDate(Date firsDate)      {this.firsDate = firsDate;}
    public void setCar(Car car)                 {this.car = car;}
    
    public int getId()              {return id;}
    public double getSize()         {return size;}
    public District getDistrict()   {return district;}
    public String getState()        {return state;}
    public Date getFirsDate()       {return firsDate;}
    public Car getCar()             {return car;}

    public void put(double _size) {
        this.size = this.size + _size;
        save();
    }
    
    @Override
    public int add() {
        String insertSQL;
        insertSQL = "INSERT INTO PackingLists(size,id_district,state,firsDate,id_car) "
                + "VALUES({size}, {district}, '{state}', '{firsDate}', {car})";
        insertSQL = insertSQL.replace("{size}", ""+this.size)
            .replace("{district}", ""+this.district.getId())
            .replace("{state}", this.state)
            .replace("{firsDate}", ""+formDate.format(this.firsDate));
        if(car == null){
            insertSQL = insertSQL.replace("{car}", "null");
        }else{
            insertSQL = insertSQL.replace("{car}", ""+this.car.getId());
        }
        return con.setRecord("PackingLists", insertSQL);
    }

    @Override
    public ArrayList<PackingList> getAll() {
        _car = new Car();
        _district = new District();
        ArrayList<PackingList> packingLists = new ArrayList<>();
        String selectSQL;
        ResultSet rs;
        selectSQL = "SELECT * FROM PackingLists";
        try {
            rs = con.getRecord(selectSQL);
            while (rs.next()) {
                packingLists.add(new PackingList(rs.getInt(1),rs.getDouble(2),
                        _district.getById(rs.getInt(3)),rs.getString(4), rs.getTimestamp(5),_car.getById(rs.getInt(6))));      
            }
        } catch (SQLException ex) {
            return null;
        }
        return packingLists;
    }

    @Override
    public PackingList getById(int id) {
        _car = new Car();
        _district = new District();
        String selectSQL;
        ResultSet rs;
        selectSQL = "SELECT * FROM PackingLists WHERE ID="+id;
        try {
            rs = con.getRecord(selectSQL);
            if (rs != null){
                rs.next();
                return new PackingList(rs.getInt(1),rs.getDouble(2),
                        _district.getById(rs.getInt(3)),rs.getString(4), rs.getTimestamp(5),_car.getById(rs.getInt(6)));
            }
        } catch (SQLException ex) {}
        return null;
    }

    @Override
    public void save() {
        String updateSQL;
        updateSQL = "UPDATE PackingLists SET size={size}, id_district={district}, "
                + "state='{state}', firsDate='{firsDate}', id_car={car} WHERE id={id}";
        updateSQL = updateSQL.replace("{size}", ""+this.size)
            .replace("{district}", ""+this.district.getId())
            .replace("{state}", this.state)
            .replace("{firsDate}", ""+formDate.format(this.firsDate))
            .replace("{id}", ""+this.id);
        
        if(car == null){
            updateSQL = updateSQL.replace("{car}", "null");
        }else{
            updateSQL = updateSQL.replace("{car}", ""+this.car.getId());
        }
            
        con.setRecord(updateSQL);
    }
    
    public ArrayList<PackingList> getByState(String state) {
        _car = new Car();
        _district = new District();
        ArrayList<PackingList> packingLists = new ArrayList<>();
        String selectSQL;
        ResultSet rs;
        selectSQL = "SELECT * FROM PackingLists WHERE state='" + state + "'";
        try {
            rs = con.getRecord(selectSQL);
            while (rs.next()) {
                packingLists.add(new PackingList(rs.getInt(1),rs.getDouble(2),
                        _district.getById(rs.getInt(3)),rs.getString(4), rs.getTimestamp(5),_car.getById(rs.getInt(6))));      
            }
        } catch (SQLException ex) {
            return null;
        }
        return packingLists;
    }
    
    //Получить все заявки из накладной
    public ArrayList<Order> getOrdersIn(){
        _customer = new Customer();
        _district = new District();
        orders = new ArrayList<>();
        String selectSQL;
        ResultSet rs;
        selectSQL = "SELECT * FROM Orders WHERE id_packingList="+this.id;
        try {
            rs = con.getRecord(selectSQL);
            while (rs.next()) {
                orders.add(new Order(rs.getInt(1),_customer.getById(rs.getInt(2)),rs.getTimestamp(3),
                        rs.getString(4),rs.getDouble(5),rs.getDouble(6),_district.getById(rs.getInt(7)),
                        this));      
            }
        } catch (SQLException ex) {
            return null;
        }
        return orders;
    }
    
    //получить открытую накладную для указанного района
    public PackingList getOpen(District _district){
        String selectSQL;
        ResultSet rs;
        selectSQL = "SELECT * FROM PackingLists WHERE state = '{state}' and id_district = {district}";
        selectSQL = selectSQL.replace("{state}", PackingListState1)
            .replace("{district}", ""+_district.getId());
        try {
            rs = con.getRecord(selectSQL);
            if (rs != null){
                rs.next();
                _car = new Car();
                PackingList packingList = new PackingList(rs.getInt(1),rs.getDouble(2),
                        _district.getById(rs.getInt(3)),rs.getString(4), rs.getTimestamp(5), _car.getById(rs.getInt(6)));
                 return packingList;
            }
        } catch (SQLException ex) {}
        return null; 
    }
    
    public void NextState(){
        Date date = new Date();
        if (this.state.equals(PackingListState1)){
            this.state = PackingListState2;
            this.firsDate = date;
        }else if (this.state.equals(PackingListState2)){
            this.state = PackingListState3;
            this.firsDate = date;
        }
        save();
    }
    
    //поставить в очередь
    public void putInLine(){
        orders = new ArrayList<>();
        if(this.state.equals(PackingListState1)){
            this.NextState();
            orders =  this.getOrdersIn();
            for (Order order : orders) {
                order.NextState();
            }
        }
    }
    
    //закрыть накладную, ищем свободную машину
    public void Close(){
        _car = new Car();
        if(this.state.equals(PackingListState2)){
            cars = _car.getFree();
            for (Car car : cars) {
                if(car.getSize()>=this.size){
                    car.Approve();
                    this.NextState();
                    this.car = car;
                    save();
                    break;
                }
            }
        }
    }
    
    public ArrayList<PackingList> getLine() {
        _car = new Car();
        _district = new District();
        ArrayList<PackingList> packingLists = new ArrayList<>();
        String selectSQL;
        ResultSet rs;
        selectSQL = "SELECT * FROM PackingLists WHERE STATE = {state} ORDER BY firsDate";
        selectSQL = selectSQL.replace("{state}", PackingListState2);
        try {
            rs = con.getRecord(selectSQL);
            while (rs.next()) {
                packingLists.add(new PackingList(rs.getInt(1),rs.getDouble(2),
                        _district.getById(rs.getInt(3)),rs.getString(4), rs.getTimestamp(5),_car.getById(rs.getInt(6))));      
            }
        } catch (SQLException ex) {
            return null;
        }
        return packingLists;
    }
}
