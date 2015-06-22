package logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.CreatingConnection;
import util.IRepository;

public class District implements IRepository<District>{
    
    CreatingConnection con = CreatingConnection.getInstance();
    
    private int id;
    private String district;
    
    public District(){ } 
    
    public District(String district){
        this.district = district;
    }
    
    public District(int id, String district){
        this.id = id;
        this.district = district;
    }
    
    public void setDistrict(String district) {this.district = district;}
    
    public int getId()          {return id;}
    public String getDistrict() {return district;}

    @Override
    public int add() {
        String insertSQL;
        insertSQL = "INSERT INTO Districts(district) VALUES('{district}')";
        insertSQL = insertSQL.replace("{district}", this.district);
        return con.setRecord(insertSQL);
    }

    @Override
    public ArrayList<District> getAll() {
        ArrayList<District> districts = new ArrayList<>();
        String selectSQL;
        ResultSet rs;
        selectSQL = "SELECT * FROM districts";
        try {
            rs = con.getRecord(selectSQL);
            while (rs.next()) {
                
                districts.add(new District(rs.getInt(1),rs.getString(2)));      
            }
        } catch (SQLException ex) {
            return null;
        }
        return districts;
    }

    @Override
    public District getById(int id) {
        String selectSQL;
        ResultSet rs;
        selectSQL = "SELECT * FROM districts WHERE ID="+id;
        try {
            rs = con.getRecord(selectSQL);
            if (rs != null){
                rs.next();
                return new District(rs.getInt(1),rs.getString(2));  
            }
        } catch (SQLException ex) {}
        return null;
    }

    @Override
    public void save() {
        String updateSQL;

        updateSQL = "UPDATE districts SET district='{district}' WHERE id={id}";
        updateSQL = updateSQL.replace("{district}", this.district)
            .replace("{id}", ""+this.id);
        
        con.setRecord(updateSQL);
    }
}
