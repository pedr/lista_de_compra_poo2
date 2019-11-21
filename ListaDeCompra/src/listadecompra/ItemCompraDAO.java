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
public class ItemCompraDAO {
    
    public static ItemCompra create(ItemCompra itemCompra) {
        try {
            String generatedColumns[] = { "id" };
            Connection conn = Conexao.getConexao();
            PreparedStatement st = conn.prepareStatement("INSERT INTO item_compra (id_produto, qnt_efetiva_compra) values (?, ?)", generatedColumns);
            st.setInt(1, itemCompra.getProduto().getId());
            st.setDouble(2, itemCompra.getQntEfetivaCompra());
            st.executeUpdate();
            
            ResultSet generetedKeys = st.getGeneratedKeys();
            generetedKeys.next();
            int id = generetedKeys.getInt(1);
            itemCompra.setId(id);
            return itemCompra;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public static ItemCompra get(int id) {
        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM item_compra WHERE id = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            int idProduto = rs.getInt("id_produto");
            Produto p = ProdutoDAO.get(idProduto);
            double qntEfetivaCompra = rs.getDouble("qnt_efetiva_compra");
            
            ItemCompra itemCompra = new ItemCompra(p, qntEfetivaCompra);
            itemCompra.setId(id);
            return itemCompra;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public static ArrayList<ItemCompra> getAll() {
        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM item_compra");
            ResultSet rs = st.executeQuery();
            
            ArrayList<ItemCompra> itensCompras = new ArrayList<>();
            
            while (rs.next()) {
                int idProduto = rs.getInt("id_produto");
                Produto p = ProdutoDAO.get(idProduto);
                double qntEfetivaCompra = rs.getDouble("qnt_efetiva_compra");
                
                ItemCompra itemCompra = new ItemCompra(p, qntEfetivaCompra);
                itemCompra.setId(rs.getInt("id"));
                itensCompras.add(itemCompra);
            }
            
            return itensCompras;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    
    public static ArrayList<ItemCompra> getAllItemCompraFromLista(int idLista) {
        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM lista_item WHERE id_lista = ?");
            st.setInt(1, idLista);
   
            ResultSet rs = st.executeQuery();
            
            ArrayList<ItemCompra> itensCompras = new ArrayList<>();
            
            while (rs.next()) {
                int idItemCompra = rs.getInt("id_item");
                ItemCompra itemCompra = ItemCompraDAO.get(idItemCompra);
                itensCompras.add(itemCompra);
            }
            
            return itensCompras;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
