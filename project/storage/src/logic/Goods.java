package logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.CreatingConnection;
import util.IRepository;

public class Goods implements IRepository<Goods>{
    
    CreatingConnection con = CreatingConnection.getInstance();
    
    private int id;
    private String name;
    private double size;
    private double price;
    private int count;
    
    public Goods(){ }
    
    public Goods(String name, double size, double price, int count){
        this.name = name;
        this.size = size;
        this.price = price;
        this.count = count;
    }
    
    public Goods(int id, String name, double size, double price, int count){
        this.id = id;
        this.name = name;
        this.size = size;
        this.price = price;
        this.count = count;
    }
    
    public void setName(String name)    {this.name = name;}
    public void setSize(double size)    {this.size = size;}  
    public void setPrice(double price)  {this.price = price;}
    public void setCount(int count)     {this.count = count;}
    
    public int getId()      {return id;}
    public String getName() {return name;}
    public double getSize() {return size;}
    public double getPrice(){return price;}
    public int getCount()   {return count;}

    @Override
    public int add() {
        String insertSQL;
        insertSQL = "INSERT INTO goods(name,size,price,count) VALUES('{name}', {size}, {price}, {count})";
        insertSQL = insertSQL.replace("{name}", this.name)
            .replace("{size}", ""+this.size)
            .replace("{price}", ""+this.price)
            .replace("{count}", ""+this.count);
        return con.setRecord(insertSQL);
    }

    @Override
    public ArrayList<Goods> getAll() {
        ArrayList<Goods> goods = new ArrayList<Goods>();
        String selectSQL;
        ResultSet rs;
        selectSQL = "SELECT * FROM goods";
        try {
            rs = con.getRecord(selectSQL);
            while (rs.next()) {
                goods.add(new Goods(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getDouble(4),rs.getInt(5)));      
            }
        } catch (SQLException ex) {
            return null;
        }
        return goods;
    }

    @Override
    public Goods getById(int id) {
        String selectSQL;
        ResultSet rs;
        selectSQL = "SELECT * FROM goods WHERE ID="+id;
        try {
            rs = con.getRecord(selectSQL);
            if (rs != null){
                rs.next();
                return new Goods(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getDouble(4),rs.getInt(5));
            }
        } catch (SQLException ex) {}
        return null;
    }

    @Override
    public void save() {
        String updateSQL;
        updateSQL = "UPDATE goods SET name='{name}', size={size}, price={price}, count={count} WHERE id={id}";
        updateSQL = updateSQL.replace("{name}", this.name)
            .replace("{size}", ""+this.size)
            .replace("{price}", ""+this.price)
            .replace("{count}", ""+this.count)
            .replace("{id}", ""+this.id);
        
        con.setRecord(updateSQL);
    }
}
