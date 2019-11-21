/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listadecompra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author pedr
 */
public class HistoricoCompraDAO {
    
    public static HistoricoCompra create(HistoricoCompra historicoCompra) {
        try {
            String generatedColumns[] = { "id" };
            Connection conn = Conexao.getConexao();
            PreparedStatement st = conn.prepareStatement("INSERT INTO historico (id_produto, id_supermercado, valor_pago, mes) values (?, ?, ?, ?)", generatedColumns);
            st.setInt(1, historicoCompra.getProduto().getId());
            st.setInt(2, historicoCompra.getSupermecado().getId());
            st.setDouble(3, historicoCompra.getValorPago());
            st.setInt(4, historicoCompra.getMes().valor);
            st.executeUpdate();
            
            ResultSet generetedKeys = st.getGeneratedKeys();
            generetedKeys.next();
            int id = generetedKeys.getInt(1);
            historicoCompra.setId(id);
            return historicoCompra;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public static HistoricoCompra get(int id) {
        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM historico WHERE id = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            int idProduto = rs.getInt("id_produto");
            Produto p = ProdutoDAO.get(idProduto);
            int idSupermercado = rs.getInt("id_supermercado");
            Supermercado s = SupermercadoDAO.get(idSupermercado);
            double valorPago = rs.getDouble("valor_pago");
            int valueMes = rs.getInt("mes");
            
            HistoricoCompra historicoCompra = new HistoricoCompra(Mes.valueOf(valueMes), p, valorPago, s);
            historicoCompra.setId(id);
            return historicoCompra;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public static ArrayList<HistoricoCompra> getAll() {
        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM historico");
            ResultSet rs = st.executeQuery();
            
            ArrayList<HistoricoCompra> historicoCompras = new ArrayList<>();
            
            while (rs.next()) {
                int idProduto = rs.getInt("id_produto");
                Produto p = ProdutoDAO.get(idProduto);
                int idSupermercado = rs.getInt("id_supermercado");
                Supermercado s = SupermercadoDAO.get(idSupermercado);
                double valorPago = rs.getDouble("valor_pago");
                int valueMes = rs.getInt("mes");

                HistoricoCompra historicoCompra = new HistoricoCompra(Mes.valueOf(valueMes), p, valorPago, s);
                historicoCompra.setId(rs.getInt("id"));
                historicoCompras.add(historicoCompra);
            }
            
            return historicoCompras;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public static double getMaxValue(Produto p) {
         try {
            Connection conn = Conexao.getConexao();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM historico WHERE id_produto = ?");
            st.setInt(1, p.getId());
            ResultSet rs = st.executeQuery();
            
            double max = 0.0;
            while (rs.next()) {
                double valorPago = rs.getInt("valor_pago");
                if (valorPago > max) {
                    max = valorPago;
                }
            }
            return max;
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
}
