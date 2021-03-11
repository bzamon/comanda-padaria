package padaria.Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsumidoBean {
    private int idComanda, idProduto, quantidade;
    
    
    
    public int getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(int aux) {
        this.idComanda = aux;
    }
     
    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int aux) {
        this.idProduto = aux;
    }
    
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int aux) {
        this.quantidade = aux;
    }
}
