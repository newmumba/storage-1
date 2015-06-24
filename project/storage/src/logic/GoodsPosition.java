package logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.CreatingConnection;
import util.IRepository;

public class GoodsPosition implements IRepository<GoodsPosition>{
    
    CreatingConnection con = CreatingConnection.getInstance();
    Goods _goods;
    Order _order;
    
    private int id;
    private Goods goods;
    private Order order;
    private int count;
     
    public GoodsPosition(){ }
    
    public GoodsPosition(Goods goods, Order order, int count){
        this.goods = goods;
        this.order = order;
        this.count = count;
    }
    
    public GoodsPosition(int id, Goods goods, Order order, int count){
        this.id = id;
        this.goods = goods;
        this.order = order;
        this.count = count;
    }
    
    public void setGoods(Goods goods)   {this.goods = goods;}
    public void setOrder(Order order)   {this.order = order;} 
    public void setCount(int count)     {this.count = count;}
    
    public int getId()      {return id;}
    public Goods getGoods() {return goods;}
    public Order getOrder() {return order;}
    public int getCount()   {return count;}

    @Override
    public int add() {
        String insertSQL;
        insertSQL = "INSERT INTO GoodsPositions(id_goods,id_order,count) VALUES({goods},{order},{count})";
        insertSQL = insertSQL.replace("{goods}", ""+this.goods.getId())
                .replace("{order}", ""+this.order.getId())
                .replace("{count}", ""+this.count);
        return con.setRecord(insertSQL);
    }

    @Override
    public ArrayList<GoodsPosition> getAll() {
        _goods =  new Goods();
        _order = new Order();
        ArrayList<GoodsPosition> goodsPositions = new ArrayList<>();
        String selectSQL;
        ResultSet rs;
        selectSQL = "SELECT * FROM GoodsPositions";
        try {
            rs = con.getRecord(selectSQL);
            while (rs.next()) {
                goodsPositions.add(new GoodsPosition(rs.getInt(1),_goods.getById(rs.getInt(2)),
                        _order.getById(rs.getInt(3)), rs.getInt(4)));      
            }
        } catch (SQLException ex) {
            return null;
        }
        return goodsPositions;
    }

    @Override
    public GoodsPosition getById(int id) {
        _goods = new Goods();
        _order = new Order();
        String selectSQL;
        ResultSet rs;
        selectSQL = "SELECT * FROM GoodsPositions WHERE ID="+id;
        try {
            rs = con.getRecord(selectSQL);
            if (rs != null){
                rs.next();
                return new GoodsPosition(rs.getInt(1),_goods.getById(rs.getInt(2)),
                    _order.getById(rs.getInt(3)), rs.getInt(4));
            }
        } catch (SQLException ex) {}
        return null;
    }

    @Override
    public void save() {
        String updateSQL;
        updateSQL = "UPDATE GoodsPositions SET id_goods={goods}, id_order={order}, "
                + "count={count} WHERE id={id}";
        updateSQL = updateSQL.replace("{goods}", ""+this.goods.getId())
            .replace("{order}", ""+this.order.getId())
            .replace("{count}", ""+this.count)
            .replace("{id}", ""+this.id);
        
        con.setRecord(updateSQL);
    }
    
    public ArrayList<GoodsPosition> getByOrder(Order order) {
        _goods =  new Goods();
        _order = new Order();
        ArrayList<GoodsPosition> goodsPositions = new ArrayList<>();
        String selectSQL;
        ResultSet rs;
        selectSQL = "SELECT * FROM GoodsPositions WHERE id_order=" + order.getId();
        try {
            rs = con.getRecord(selectSQL);
            while (rs.next()) {
                goodsPositions.add(new GoodsPosition(rs.getInt(1),_goods.getById(rs.getInt(2)),
                        _order.getById(rs.getInt(3)), rs.getInt(4)));      
            }
        } catch (SQLException ex) {
            return null;
        }
        return goodsPositions;
    }
    
}
