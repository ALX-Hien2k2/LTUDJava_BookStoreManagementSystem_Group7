package dataaccess;

import com.mysql.cj.jdbc.Driver;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyConnection {
    public static Connection create(){
        Connection connection = null;
        try {
            Driver myDriver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(myDriver);
            String DB_URL = "jdbc:mysql://localhost/bookstore";
            String USER = "root";
            String PASS = "T!nInvisible123";
            
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            return connection;
        } catch (SQLException ex) {
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
}
