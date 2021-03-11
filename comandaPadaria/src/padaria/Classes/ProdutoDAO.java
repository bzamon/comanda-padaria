package padaria.Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import padaria.ConnectionFactory;

public class ProdutoDAO {

    private static Connection con;
    private static PreparedStatement ps;
    
    public static void getLista() throws ClassNotFoundException, SQLException {
        String nome;
        String idProduto;
        con = ConnectionFactory.getConnection();
        String sql = new String("SELECT nome, idProduto FROM PRODUTO;");
        int i=0;
        System.out.println("\nNOME || ID ");
        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                nome=rs.getString(1);
                idProduto=rs.getString(2);
                System.out.printf("%s || %s\n",nome,idProduto);
            }
            System.out.println();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
