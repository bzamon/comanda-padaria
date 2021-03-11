package padaria.Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoBean {

    String sql;
    static Connection con;
    static PreparedStatement ps;
    private String id, valor;
    
    
    
    public String getId() {
        return id;
    }

    public void setId(String aux) {
        this.id = aux;
    }
     
    public String getValor() {
        return valor;
    }

    public void setValor(String aux) {
        this.valor = aux;
    }
}
