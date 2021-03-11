package padaria.Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import padaria.ConnectionFactory;

public class ConsumidoDAO {

    private static Connection con;
    private static PreparedStatement ps;

    public static boolean setConsumo(ConsumidoBean bean) throws ClassNotFoundException{
        boolean status = false;
        
        con = ConnectionFactory.getConnection();

        String sql;
        try {
            
            sql = new String("insert into consumido values(?,?,now(6),?);");
            try {
                ps = con.prepareStatement(sql);
                ps.setInt(1,bean.getIdComanda());
                ps.setInt(2,bean.getIdProduto());
                ps.setInt(3,bean.getQuantidade());
                ps.executeUpdate();
                ps.close();
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return status;
    }
    
    public static float getTotalDia(String dia, String mes) throws ClassNotFoundException, SQLException {
        boolean status = false;
        float totalDia = 0, auxValor = 0;
        int auxQtd = 0;
        String inicio = "2021-"+mes+"-"+dia+" 00:00:00";
        String fim = "2021-"+mes+"-"+dia+" 23:59:59";
        
        con = ConnectionFactory.getConnection();
        String sql = new String("select P.valor, C.quantidade from consumido C inner join produto P on P.idProduto = C.idProduto WHERE criadoem BETWEEN ? AND ?;");

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, inicio);
            ps.setString(2, fim);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                auxValor = rs.getFloat("valor");
                auxQtd = rs.getInt("quantidade");
                totalDia = totalDia + auxValor * auxQtd;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return totalDia;
    }
    
    public static float getTicketDia(String dia, String mes) throws ClassNotFoundException, SQLException {
        boolean status = false;
        float totalDia = ConsumidoDAO.getTotalDia(dia, mes);
        float ticketDia=0;
        int vendas;
        String inicio = "2021-"+mes+"-"+dia+" 00:00:00";
        String fim = "2021-"+mes+"-"+dia+" 23:59:59";
        
        con = ConnectionFactory.getConnection();
        String sql = new String("select SUM(C.quantidade) from consumido C WHERE criadoem BETWEEN ? AND ?;");

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, inicio);
            ps.setString(2, fim);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                vendas = rs.getInt(1);
                ticketDia = totalDia/vendas;
                System.out.printf("Quantidade de Vendas: %d",vendas);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ticketDia;
    }
    
    public static boolean getMenosVendido() throws ClassNotFoundException, SQLException {
        boolean status = false;
        int[] total = new int[3];
        String[] nome = new String[3];
        con = ConnectionFactory.getConnection();
        String sql = new String("select sum(quantidade), P.nome from consumido C inner join produto P on P.idProduto = C.idProduto group by C.idProduto order by sum(quantidade) asc;");
        int i=0;
        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()&&i<3){
                total[i]=rs.getInt(1);
                nome[i]=rs.getString(2);
                System.out.printf("%s %d\n",nome[i],total[i]);
                i++;
            }
            System.out.println();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return status;
    }
    
    public static boolean getMaisVendido() throws ClassNotFoundException, SQLException {
        boolean status = false;
        int[] total = new int[3];
        String[] nome = new String[3];
        con = ConnectionFactory.getConnection();
        String sql = new String("select sum(quantidade), P.nome from consumido C inner join produto P on P.idProduto = C.idProduto group by C.idProduto order by sum(quantidade) desc;");
        int i=0;
        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()&&i<3){
                total[i]=rs.getInt(1);
                nome[i]=rs.getString(2);
                System.out.printf("%s %d\n",nome[i],total[i]);
                i++;
            }
            System.out.println();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return status;
    }
    
}
