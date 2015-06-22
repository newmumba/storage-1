package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreatingConnection {
    
    private static CreatingConnection instance;
    Statement st;
    ResultSet rs;
    
    private CreatingConnection() {
        
    };
     
    public static synchronized CreatingConnection getInstance() {
        if (instance == null) {
            instance = new CreatingConnection();
        }
        return instance;
    }
    
    Connection conn = null;
    
    public boolean Connection(){
        boolean result;
        //подключение драйвера
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            //параметры подключения
            String url = "jdbc:derby://localhost:1527/storage";
            String name = "anton";
            String password = "anton";

            //установка соединения с БД
            try {
                conn = DriverManager.getConnection(url, name, password);
                System.out.println("есть подключение к БД");
                result = true;
            } catch (java.sql.SQLException e) {
                System.err.println("ошибка подключения к БД");
                result = false;
            }

            //проверка на наличие данных в БД
            if (conn == null) {
             System.err.println("нет данных БД");
            } else {
             System.out.println("есть данные!");
            }
        } catch (ClassNotFoundException ex) {
            System.err.println("Драйвер не подключен");
            result = false;
        }
        return result;
    }
    
    public void CloseConnection(){
        try{
            conn.close();
            System.out.println("Соединение c БД закрыто");
        } catch (SQLException ex) {
            System.err.println("Соединение c БД не установлено");
        }
    }
    
    //добавляем, изменяем запись в БД, возвращем 0 - успех, -1 - ошибка
    public int setRecord(String insertSQL){
        try{
            st = conn.createStatement();
            st.executeUpdate(insertSQL);
            System.out.println("Запись добавлена в БД");
            return 0;
        } catch (SQLException ex) {
            System.err.println("Запись не добавлена в БД");
            return -1;
        }
    }
    
    //добавляем, изменяем запись в БД, id добалвенной записи, -1 - ошибка
    public int setRecord(String table, String insertSQL){
        try{
            String selectSQL;
            st = conn.createStatement();
            
            selectSQL = "SELECT MAX(ID) FROM " + table;
            st.executeUpdate(insertSQL);
            rs = st.executeQuery(selectSQL);
            System.out.println("Запись добавлена в БД");
            rs.next();
            return (int)rs.getDouble(1);
        } catch (SQLException ex) {
            System.err.println("Запись не добавлена в БД");
            return -1;
        }
    }
    
    public ResultSet getRecord(String selectSQL){
        try{
            st = conn.createStatement();
            rs = st.executeQuery(selectSQL);
            System.out.println("Запись получена из БД");
            return rs;
        } catch (SQLException ex) {
            System.err.println("Запись не получена из БД");
            return null;
        }
    }
}