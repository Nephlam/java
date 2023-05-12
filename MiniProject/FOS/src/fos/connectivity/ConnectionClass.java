package fos.connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
    public Connection connection;
    
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        
        String dbName = "test";
        String username = "root";
        String password = "shazmeen";
        
        
        Class.forName("com.mysql.jdbc.Driver");
        
        connection = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,username, password);
        
       
        
        return connection;
    }
    
}
