package padaria.Classes;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ComandaBean {

    String sql;
    static Connection con;
    static PreparedStatement ps;
    private int id;
    private float total;
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int aux) {
        this.id = aux;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float aux) {
        this.total = aux;
    }

}
