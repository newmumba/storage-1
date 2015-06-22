package logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.CreatingConnection;
import util.IRepository;

public class Customer implements IRepository<Customer>{
    
    CreatingConnection con = CreatingConnection.getInstance();
    
    private int id;
    private String name;
    private String address;
    private String phone;
    private String login;
    private String password;
    
    public Customer(){ }
    
    public Customer(String name, String address, String phone, String login, String password){
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.login = login;
        this.password = password;
    }
    
    public Customer(int id, String name, String address, String phone, String login, String password){
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.login = login;
        this.password = password;
    }
    
    public void setName(String name)        {this.name = name;}
    public void setAddress(String address)  {this.address = address;}
    public void setPhone(String phone)      {this.phone = phone;}
    public void setLogin(String login)      {this.login = login;}
    public void setPassword(String password){this.password = password;}
    
    public int getId()          {return id;}
    public String getName()     {return name;}
    public String getAddress()  {return address;}
    public String getPhone()    {return phone;}
    public String getLogin()    {return login;}
    public String getPassword() {return password;}

    @Override
    public int add() {
       String insertSQL;
        insertSQL = "INSERT INTO Customers(name,address,phone,login,password) "
            + "VALUES('{name}', '{address}', '{phone}', '{login}', '{password}')";
        insertSQL = insertSQL.replace("{name}", this.name)
            .replace("{address}", this.address)
            .replace("{phone}", this.phone)
            .replace("{login}", this.login)
            .replace("{password}", this.password);
        return con.setRecord(insertSQL); 
    }

    @Override
    public ArrayList<Customer> getAll() {
        ArrayList<Customer> customers = new ArrayList<>();
        String selectSQL;
        ResultSet rs;
        selectSQL = "SELECT * FROM customers";
        try {
            rs = con.getRecord(selectSQL);
            while (rs.next()) {
                
                customers.add(new Customer(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6)));      
            }
        } catch (SQLException ex) {
            return null;
        }
        return customers;
    }

    @Override
    public Customer getById(int id) {
        String selectSQL;
        ResultSet rs;
        selectSQL = "SELECT * FROM customers WHERE ID="+id;
        try {
            rs = con.getRecord(selectSQL);
            if (rs != null){
                rs.next();
                return new Customer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));  
            }
        } catch (SQLException ex) {}
        return null;
    }

    @Override
    public void save() {
        String updateSQL;

        updateSQL = "UPDATE customers SET name='{name}', address='{address}', phone='{phone}', "
            + "login='{login}', password='{password}' WHERE id={id}";
        updateSQL = updateSQL.replace("{name}", this.name)
            .replace("{address}", this.address)
            .replace("{phone}", this.phone)
            .replace("{login}", this.login)
            .replace("{password}", this.password)
            .replace("{id}", ""+this.id);
        
        con.setRecord(updateSQL);
    }
    
    public Customer getByLoginPass(String _login, String _password) {
        String selectSQL;
        ResultSet rs;
        selectSQL = "SELECT * FROM customers WHERE login='{login}' and password = '{password}'";
        selectSQL = selectSQL.replace("{login}", _login)
                .replace("{password}", _password);
        try {
            rs = con.getRecord(selectSQL);
            if (rs != null){
                rs.next();
                return new Customer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));  
            }
        } catch (SQLException ex) {}
        return null;
    }
}