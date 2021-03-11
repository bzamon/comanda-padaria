package padaria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    static Connection con;
    public static Connection getConnection() throws ClassNotFoundException{
        
        con = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/padaria?useTimezone=true&serverTimezone=UTC", "root", "root");
            
        } catch (SQLException e) {
            
            throw new RuntimeException(e);
        }
        return con;
    }
}
