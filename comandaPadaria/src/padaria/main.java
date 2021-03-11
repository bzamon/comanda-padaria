/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padaria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Scanner;
import java.util.TimeZone;
import padaria.Classes.ComandaBean;
import padaria.Classes.ComandaDAO;
import padaria.Classes.ConsumidoBean;
import padaria.Classes.ConsumidoDAO;
import padaria.Classes.ProdutoBean;
import padaria.Classes.ProdutoDAO;

/**
 *
 * @author Breno
 */
public class main {

    private static PreparedStatement ps;
    private String sql;
    private static Connection con;
    private static Scanner scan = new Scanner(System.in);

    static void getComanda() throws ClassNotFoundException {
        con = ConnectionFactory.getConnection();
        boolean status = false;
        try {
            String sql = new String("select * from comanda");
            ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            status = rs.next();

            if (status == true) {
                System.out.printf("%d", rs.getInt("idComanda"));
            }

            ps.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void esperarEntrada() {
        String line = scan.nextLine();
        line = scan.nextLine();
    }

    public static void tabelaProdutos() throws ClassNotFoundException, SQLException {
       ProdutoDAO.getLista();
        
    }

    public static void telaComanda() throws ClassNotFoundException, SQLException {

        int entrada = 0;
        ConsumidoBean consumido = new ConsumidoBean();

        ComandaBean comanda = ComandaDAO.criaComanda();

        consumido.setIdComanda(comanda.getId());

        while (entrada != 2) {
            System.out.println("1. ADICIONAR CONSUMO");
            System.out.println("2. FINALIZAR COMANDA");
            entrada = scan.nextInt();
            if (entrada == 1) {
                tabelaProdutos();
                System.out.println("ENTRE COM O ID DO PRODUTO E A QUANTIDADE (EX: 1 3): ");
                consumido.setIdProduto(scan.nextInt());
                consumido.setQuantidade(scan.nextInt());
                ConsumidoDAO.setConsumo(consumido);
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ComandaBean comanda = new ComandaBean();
        int entrada = 0;

        String dia, mes;
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        dia = String.valueOf(calendar.get(Calendar.DATE));
        mes = String.valueOf(calendar.get(Calendar.MONTH) + 1);

        float totalDia = 0, ticketDia = 0;

        while (entrada != 6) {
            System.out.println("1. CRIAR COMANDA");
            System.out.println("2. VISUALIZAR TOTAL DO DIA");
            System.out.println("3. VISUALIZAR TICKET MÉDIO DO DIA");
            System.out.println("4. VISUALIZAR PRODUTOS MENOS VENDIDOS");
            System.out.println("5. VISUALIZAR PRODUTOS MAIS VENDIDOS");
            System.out.println("6. ENCERRAR\n");

            entrada = scan.nextInt();

            switch (entrada) {
                case 1:
                    telaComanda();
                    break;
                case 2:
                    totalDia = ConsumidoDAO.getTotalDia(dia, mes);
                    System.out.printf("\nTotal do Dia: %.2f\n\n",totalDia);
                    break;
                case 3:
                    ticketDia = ConsumidoDAO.getTicketDia(dia, mes);
                    System.out.printf("\nTicket Médio do Dia: %.2f\n\n",ticketDia);
                    break;
                case 4:
                    ConsumidoDAO.getMenosVendido();
                    break;
                case 5:
                    ConsumidoDAO.getMaisVendido();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Entrada inválida!\n\n");
            }

        }
    }
}
