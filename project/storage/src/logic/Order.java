package logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import util.CreatingConnection;
import util.IRepository;

public class Order implements IRepository<Order>{
    
    CreatingConnection con = CreatingConnection.getInstance();
    SimpleDateFormat formDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ArrayList<Order> orders;
    Customer _customer;
    District _district;
    PackingList _packingList;
    GoodsPosition _goodsPosition;
    Goods _goods;
    String OrderState1 = "На рассмотрении";
    String OrderState2 = "Принята";
    String OrderState3 = "Доставка";
    String PackingListState1 = "Открыта";
    double minSize = 12;
    
    private int id;
    private Customer customer; 
    private Date date;
    private String state;
    private double amount;
    private double size;
    private District district;
    private PackingList packingList;
    
    public Order(){ }
    
    public Order(Customer customer, Date date, String state, double amount, double size, District district, PackingList packingList){
        this.customer = customer;
        this.date = date;
        this.state = state;
        this.amount = amount;
        this.size = size;
        this.district = district;
        this.packingList = packingList;
    }
    
    public Order(int id, Customer customer, Date date, String state, double amount, double size, District district, PackingList packingList){
        this.id = id;
        this.customer = customer;
        this.date = date;
        this.state = state;
        this.amount = amount;
        this.size = size;
        this.district = district;
        this.packingList = packingList;
    }
    
    public void setId(int id)  {this.id = id;}
    public void setCustomer(Customer customer)          {this.customer = customer;}
    public void setDate(Date date)                      {this.date = date;}
    public void setState(String state)                  {this.state = state;}
    public void setAmount(double amount)                {this.amount = amount;}
    public void setSize(double size)                    {this.size = size;}
    public void setDistrict(District district)          {this.district = district;}
    public void setPackingList(PackingList packingList) {this.packingList = packingList;}
    
    public int getId()                      {return id;}
    public Customer getCustomer()           {return customer;}
    public Date getDate()                   {return date;}
    public String getState()                {return state;}
    public double getAmount()               {return amount;}
    public double getSize()                 {return size;}
    public District getDistrict()           {return district;}
    public PackingList getPackingList()     {return packingList;}

    @Override
    public int add() {
        String insertSQL;
        insertSQL = "INSERT INTO Orders(id_customer,date,state, amount, size, id_district) "
                + "VALUES({customer}, '{date}', '{state}', {amount}, {size}, {district})";
        insertSQL = insertSQL.replace("{customer}", ""+this.customer.getId())
            .replace("{date}", ""+formDate.format(this.date))
            .replace("{state}", this.state)
            .replace("{amount}", ""+this.amount)
            .replace("{size}", ""+this.size)
            .replace("{district}", ""+this.district.getId());
        
        return con.setRecord("Orders", insertSQL);

    }

    @Override
    public ArrayList<Order> getAll() {
        _customer = new Customer();
        _district = new District();
        _packingList = new PackingList();
        orders = new ArrayList<>();
        String selectSQL;
        ResultSet rs;
        selectSQL = "SELECT * FROM Orders";
        try {
            rs = con.getRecord(selectSQL);
            while (rs.next()) {
                orders.add(new Order(rs.getInt(1),_customer.getById(rs.getInt(2)),rs.getTimestamp(3),
                        rs.getString(4),rs.getDouble(5),rs.getDouble(6),_district.getById(rs.getInt(7)),
                        _packingList.getById(rs.getInt(8))));      
            }
        } catch (SQLException ex) {
            return null;
        }
        return orders;
    }

    @Override
    public Order getById(int id) {
        _customer = new Customer();
        _district = new District();
        _packingList = new PackingList();
        String selectSQL;
        ResultSet rs;
        selectSQL = "SELECT * FROM Orders WHERE ID="+id;
        try {
            rs = con.getRecord(selectSQL);
            if (rs != null){
                rs.next();
                return new Order(rs.getInt(1),_customer.getById(rs.getInt(2)),rs.getTimestamp(3),
                        rs.getString(4),rs.getDouble(5),rs.getDouble(6),_district.getById(rs.getInt(7)),
                        _packingList.getById(rs.getInt(8)));
            }
        } catch (SQLException ex) {}
        return null;
    }

    @Override
    public void save() {
        String updateSQL;
        updateSQL = "UPDATE Orders SET id_customer={customer}, date='{date}', state='{state}',"
                + " amount={amount},size={size},id_district={district}, id_packingList={packingList} WHERE id={id}";
        updateSQL = updateSQL.replace("{customer}", ""+this.customer.getId())
            .replace("{date}", ""+formDate.format(this.date))
            .replace("{state}", this.state)
            .replace("{amount}", ""+this.amount)
            .replace("{size}", ""+this.size)
            .replace("{district}", ""+this.district.getId())
            
            .replace("{id}", ""+this.id);
        
        if(this.packingList != null){
            updateSQL = updateSQL.replace("{packingList}", ""+this.packingList.getId());
        } else{
            updateSQL = updateSQL.replace("{packingList}", "null");
        }
        con.setRecord(updateSQL);
    }
    
    public ArrayList<Order> getByCustomer(int id) {
        _customer = new Customer();
        _district = new District();
        _packingList = new PackingList();
        orders = new ArrayList<>();
        String selectSQL;
        ResultSet rs;
        selectSQL = "SELECT * FROM Orders WHERE id_customer="+id;
        try {
            rs = con.getRecord(selectSQL);
            while (rs.next()) {
                orders.add(new Order(rs.getInt(1),_customer.getById(rs.getInt(2)),rs.getTimestamp(3),
                        rs.getString(4),rs.getDouble(5),rs.getDouble(6),_district.getById(rs.getInt(7)),
                        _packingList.getById(rs.getInt(8))));      
            }
        } catch (SQLException ex) {
            return null;
        }
        return orders;
    }
    
    public ArrayList<Order> getByState(String state) {
        _customer = new Customer();
        _district = new District();
        _packingList = new PackingList();
        orders = new ArrayList<>();
        String selectSQL;
        ResultSet rs;
        selectSQL = "SELECT * FROM Orders WHERE state='"+state+"'";
        try {
            rs = con.getRecord(selectSQL);
            while (rs.next()) {
                orders.add(new Order(rs.getInt(1),_customer.getById(rs.getInt(2)),rs.getTimestamp(3),
                        rs.getString(4),rs.getDouble(5),rs.getDouble(6),_district.getById(rs.getInt(7)),
                        _packingList.getById(rs.getInt(8))));      
            }
        } catch (SQLException ex) {
            return null;
        }
        return orders;
    }
    
    //Добавить заявку.
    public void addWithGoods(Customer customer, District district, ArrayList<GoodsPosition> goodsPositions){
        date = new Date();
        double _size = 0;
        int _amount = 0;
        int resAddGoods;
        int id_order;
        _goodsPosition = new GoodsPosition();
        _goods = new Goods();
        
        //считаем объем и сумму заказа
        for(int i = 0; goodsPositions.size()>i; i++){
            _amount += goodsPositions.get(i).getGoods().getPrice();
            _size += goodsPositions.get(i).getGoods().getSize();
        }
        this.customer = customer;
        this.date = date;
        this.state = OrderState1;
        this.amount = _amount;
        this.size = _size;
        this.district = district;
        this.packingList = null;
        
        //Добавляем заявку в БД
        id_order = this.add();
        this.setId(id_order);
        
        if (id_order>0){//если в ответ пришел id добавленной записи заявки, то отправляем товары в этом заказе на добавление
            //добавляем товары в БД
            for(int i = 0; goodsPositions.size()>i; i++){
                
                _goodsPosition = goodsPositions.get(i);
                _goodsPosition.setOrder(this);
                resAddGoods = _goodsPosition.add();
                if(resAddGoods == 0){
                    _goods = _goods.getById(_goodsPosition.getGoods().getId());
                    _goods.setCount(_goods.getCount()-_goodsPosition.getCount());
                    _goods.save();
                }
            }
        }
    }
    
    public void NextState(){
        if (this.state.equals(OrderState1)){
            this.state = OrderState2;
        }else if (this.state.equals(OrderState2)){
            this.state = OrderState3;
        }
        save();
    }
    
    //выставить заявку в состояние Принята.
    public void InAccepted(){
        //Если заявка на рассмотрении, мы можем перевести ее в статус Принята.
        if(this.getState().equals(OrderState1)){
            int id_packingList;
            //Проверяем, есть ли открытая траспортная накладная по данному району
            _packingList = new PackingList();
            _packingList = _packingList.getOpen(this.district);
            if(_packingList == null){
                _packingList = new PackingList();
                _packingList.setDistrict(this.district);
                _packingList.setFirsDate(new Date());
                _packingList.setSize(this.size);
                _packingList.setState(PackingListState1);
                id_packingList = _packingList.add();
                _packingList.setId(id_packingList);
            }else{
                _packingList.put(this.size);
            }
            //переводим заявку в состояние принята и даем ей id транспортной накладной
            this.setPackingList(_packingList);
            save();
            this.NextState();
            
            //проверка на готовность постановки в очередь заявку.
            if(minSize <= _packingList.getSize()){
                _packingList.putInLine();
            }
        }
    }
}

