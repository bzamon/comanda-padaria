package padaria.Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import padaria.ConnectionFactory;

public class ComandaDAO {

    private static Connection con;
    private static PreparedStatement ps;

    public static ComandaBean criaComanda() throws ClassNotFoundException, SQLException {
        boolean status = false;
        ComandaBean bean = new ComandaBean();
        int proxId = 0;
        con = ConnectionFactory.getConnection();

        String sql = new String("SELECT * FROM comanda WHERE idComanda=(SELECT max(idComanda) FROM comanda);");

        try {
            ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            rs.next();
            proxId = rs.getInt(1);
            proxId++;
            bean.setId(proxId);
            sql = new String("insert into comanda values(?);");
            try {
                ps = con.prepareStatement(sql);
                ps.setInt(1, proxId);
                ps.executeUpdate();
                ps.close();
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return bean;
    }

    public static float getTotalComanda(ComandaBean bean) throws ClassNotFoundException, SQLException {
        boolean status = false;
        float semitotal = 0, auxValor = 0;
        int auxQtd = 0;
        con = ConnectionFactory.getConnection();

        String sql = new String("select P.valor, C.quantidade from produto P inner join consumido C on P.idProduto = C.idProduto where C.idComanda = ?;");

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, bean.getId());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                auxValor = rs.getFloat("valor");
                auxQtd = rs.getInt("quantidade");
                semitotal = semitotal + auxValor * auxQtd;
                bean.setTotal(semitotal);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return bean.getTotal();
    }
    
    
    
}
