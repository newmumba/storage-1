package logic;

import gui.frmAddOrder;
import gui.frmDirectorStorage;
import gui.frmMain;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import util.CreatingConnection;

public class Storage {
    
    private static ArrayList<Car> cars;
    private static ArrayList<District> districts;
    private static ArrayList<Goods> goods;
    private static ArrayList<Customer> customers;
    private static ArrayList<GoodsPosition> goodsPositions;
    private static ArrayList<Order> orders;
    
    private static void initCar(){
        Car firstCar = new Car();
        Date date = new Date();
        firstCar.setName("Газел1ь");
        firstCar.setSize(18);
        firstCar.setState(true);
        firstCar.setDate(date);
        cars.add(firstCar);
        firstCar.add();
        
        Car secondCar = new Car();
        secondCar.setName("Камаз1");
        secondCar.setSize(30);
        secondCar.setState(true);
        secondCar.setDate(date);
        cars.add(secondCar);
        secondCar.add(); 
    }
    
    private static void initCustomer(){ 
        Customer firstCustomer = new Customer();
        firstCustomer.setName("Абрамов Антон Михайлович");
        firstCustomer.setAddress("г.Санкт-Петербург, ул. Московская, д.5, кв.8");
        firstCustomer.setPhone("8-921-786-96-56");
        firstCustomer.setLogin("admin");
        firstCustomer.setPassword("admin");
        customers.add(firstCustomer);
        firstCustomer.add();
        
        Customer secondCustomer = new Customer();
        secondCustomer.setName("Иванов Сергей Петрович");
        secondCustomer.setAddress("г.Екатеринбург, ул. Ленина, д.55, кв.9");
        secondCustomer.setPhone("310 10 10");
        secondCustomer.setLogin("123");
        secondCustomer.setPassword("123123");
        customers.add(secondCustomer);
        secondCustomer.add();
    }
    
    private static void initDistrict(){ 
        District firstDistrict = new District();
        firstDistrict.setDistrict("Невский");
        districts.add(firstDistrict);
        firstDistrict.add();
        
        District secondDistrict = new District();
        secondDistrict.setDistrict("Выборгский");
        districts.add(secondDistrict);
        secondDistrict.add();

        District thirdDistrict = new District();
        thirdDistrict.setDistrict("Центральный");
        districts.add(thirdDistrict);
        thirdDistrict.add();
    }
    
    private static void initGoods(){
        Goods firstGoods = new Goods();
        firstGoods.setName("Телевизор");
        firstGoods.setPrice(17000);
        firstGoods.setCount(5);
        firstGoods.setSize(0.8);
        goods.add(firstGoods);
        firstGoods.add();
        
        Goods secondGoods = new Goods();
        secondGoods.setName("Холодильник");
        secondGoods.setPrice(13500);
        secondGoods.setCount(3);
        secondGoods.setSize(1.3);
        goods.add(secondGoods);
        secondGoods.add();
    }

    
    private static void initOrder() {
        Date date = new Date();
        District _district = new District(); 
        Customer _customer = new Customer(); 
        Order firstOrder = new Order();
        firstOrder.setCustomer(_customer.getById(1));
        firstOrder.setDate(date);
        firstOrder.setState("В ожидании");
        firstOrder.setAmount(5000);
        firstOrder.setSize(6.4);
        firstOrder.setDistrict(_district.getById(1));
        firstOrder.add();
        
        Order secondOrder = new Order();
        secondOrder.setCustomer(_customer.getById(2));
        secondOrder.setDate(date);
        secondOrder.setState("Принята");
        secondOrder.setAmount(7000);
        secondOrder.setSize(10.6);
        secondOrder.setDistrict(_district.getById(2));
        secondOrder.add();
    }
    
    private static void initGoodsPosition(){
        Order _order = new Order();
        Goods _goods = new Goods();
        
        GoodsPosition firstGoodsPosition = new GoodsPosition();
        firstGoodsPosition.setOrder(_order.getById(3));
        firstGoodsPosition.setGoods(_goods.getById(3));
        firstGoodsPosition.setCount(2);
        goodsPositions.add(firstGoodsPosition);
        firstGoodsPosition.add();
        
        GoodsPosition secondGoodsPosition = new GoodsPosition();
        secondGoodsPosition.setOrder(_order.getById(4));
        secondGoodsPosition.setGoods(_goods.getById(4));
        secondGoodsPosition.setCount(3);
        goodsPositions.add(secondGoodsPosition); 
        secondGoodsPosition.add();
    }
    
    private static void initPackingList() throws ParseException{
        Date date = new Date();
        Car _car = new Car();
        District _district = new District();
        Date dateTest1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-10-10 10:10:10");
        Date dateTest2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-03-28 19:00:07");
        
        PackingList firstPackingList = new PackingList();
        firstPackingList.setCar(_car.getById(1));
        firstPackingList.setDistrict(_district.getById(1));
        firstPackingList.setFirsDate(dateTest1);
        firstPackingList.setSize(18);
        firstPackingList.setState("Открыта");
        firstPackingList.add();
        
        PackingList secondPackingList = new PackingList();
        secondPackingList.setCar(_car.getById(2));
        secondPackingList.setDistrict(_district.getById(2));
        secondPackingList.setFirsDate(date);
        secondPackingList.setSize(16);
        secondPackingList.setState("Загрузка");
        secondPackingList.add();
    }
    
    public static void main(String[] args) throws ParseException {
        //устанавливаем соединение с БД
        CreatingConnection con = CreatingConnection.getInstance();
        SimpleDateFormat formDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //проверяем, есть ли соединение с БД
        if (con.Connection()){
            cars = new ArrayList<>();
            districts = new ArrayList<>();
            goods = new ArrayList<>();
            customers = new ArrayList<>();
            goodsPositions = new ArrayList<>();
            orders = new ArrayList<>();
            //initCar();
            /*Car car = new Car();
            car = car.getById(1);
            if (car != null){
                car.setName("Машинка");
                car.setState(false);
                car.setSize(19.7);
                car.save();
                System.out.println(car.getState());
            }*/
            
            //initCustomer(); 
            /*Customer customer = new Customer(); 
            customer = customer.getById(27);
            if(customer != null){
                customer.setName("Петя Пупкин");
                customer.setPhone("8800 2000 500");
                customer.setAddress("пос. Теплая Гора");
                customer.setLogin("root");
                customer.setPassword("pass");
                customer.save();
                System.out.println(customer.getLogin());
            }*/
            
            //initDistrict(); 
            /*District district = new District();
            district = district.getById(4);
            if(district != null){ 
                district.setDistrict("Мой район");
                district.save();
                System.out.println(district.getDistrict());
            }*/
            
            //initGoods();
            /*Goods goods = new Goods();
            goods = goods.getById(3);
            if(goods != null){
                goods.setCount(10);
                goods.setName("Пылесос");
                goods.setSize(0.5);
                goods.setPrice(5000);
                goods.save();
                System.out.println(goods.getPrice());
            }*/
            
            //initOrder();
            /*Customer _customer = new Customer(); 
            District _district = new District();
            Order oneOrder = new Order();
            Date dateTest = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-10-10 10:10:10");
            oneOrder = oneOrder.getById(3);
            if(oneOrder != null){
                oneOrder.setAmount(15000);
                oneOrder.setCustomer(_customer.getById(27));
                oneOrder.setDate(dateTest);
                oneOrder.setDistrict(_district.getById(6));
                oneOrder.setSize(15);
                oneOrder.setState("Доставка");
                oneOrder.setPackingList(null);
                oneOrder.save();
                System.out.println(oneOrder.getAmount());
            }*/
            
            //initGoodsPosition();
            /*Order _order = new Order();
            Goods _goods = new Goods();
            GoodsPosition goodsPosition = new GoodsPosition();
            goodsPosition = goodsPosition.getById(6);
            if(goodsPosition != null){
                goodsPosition.setGoods(_goods.getById(1));
                goodsPosition.setOrder(_order.getById(1));
                goodsPosition.setCount(6);
                goodsPosition.save();
                System.out.println(goodsPosition.getGoods().getCount());
            }*/
            
            //initPackingList();
            /*Car _car = new Car();
            District _district = new District();
            Date dateTest = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2013-02-01 11:11:11");
            
            PackingList packingList = new PackingList();
            packingList = packingList.getById(1);
            if(packingList != null){
                packingList.setCar(_car.getById(2));
                packingList.setDistrict(_district.getById(2));
                packingList.setFirsDate(dateTest);
                packingList.setSize(25);
                packingList.setState("Доставка");
                packingList.save();
                System.out.println(packingList.getFirsDate());
            }*/
            
            //Добавление заявки и товаров к ней
            
            /*Goods goods = new Goods(); 
            Order order = new Order();
            Customer customer = new Customer();
            District district = new District();
            GoodsPosition firstGoodsPosition = new GoodsPosition();
            firstGoodsPosition.setGoods(goods.getById(1));
            firstGoodsPosition.setCount(2);
            goodsPositions.add(firstGoodsPosition);

            GoodsPosition secondGoodsPosition = new GoodsPosition();
            secondGoodsPosition.setGoods(goods.getById(2));
            secondGoodsPosition.setCount(3);
            goodsPositions.add(secondGoodsPosition);
            
            order.addWithGoods(customer.getById(1), district.getById(1), goodsPositions);
            */
            /*
            Order order = new Order();
            order = order.getById(1);
            order.OrderInAccepted();
            System.out.println("ОК");
            */
            /*PackingList packingList = new PackingList();
            packingList = packingList.getById(4);
            packingList.putInLine();*/
            
            /*PackingList packingList = new PackingList();
            packingList = packingList.getById(4);
            packingList.Close();*/
            //Customer customer = new Customer(); 
            //customer = customer.getByLoginPass("admin","admin");
            //Закрываем соединение с БД
            
            
            //frmAddOrder frmStart = new frmAddOrder();
            frmMain frmStart = new frmMain();
            //frmDirectorStorage frmStart = new frmDirectorStorage();
            frmStart.main(args);
            //con.CloseConnection();
        }else{
            System.out.println("Не ОК");
        } 
    }
}