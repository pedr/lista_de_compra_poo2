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
public class ListaCompraDAO {
    
    public static void createIfDoesntExist() {
        // cria uma entrada no banco para cada mes se não existir
        try {
            Connection conn = Conexao.getConexao();
            
            ArrayList<ListaCompra> listaCompras = ListaCompraDAO.getAll();
            
            if (listaCompras.size() > 0) {
                return;
            }
            
            for (int i = 1; i < 13; i++) {
                PreparedStatement st = conn.prepareStatement("INSERT INTO lista_compra (mes) values (?)");
                st.setInt(1, i);
                st.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static ListaCompra get(Mes mes) {
        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM lista_compra WHERE id = ?");
            st.setInt(1, mes.valor);
            ResultSet rs = st.executeQuery();
            rs.next();
            int mesLC = rs.getInt("mes");
            int idListaCompra = rs.getInt("id");
            
            ArrayList<ItemCompra> itensCompra = ItemCompraDAO.getAllItemCompraFromLista(idListaCompra);
            
            ListaCompra listaCompra = new ListaCompra(Mes.valueOf(mesLC), itensCompra);
            listaCompra.setId(idListaCompra);
            return listaCompra;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public static ArrayList<ListaCompra> getAll() {
        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM lista_compra");
            ResultSet rs = st.executeQuery();
            
            ArrayList<ListaCompra> listaCompras = new ArrayList<>();
            
            while (rs.next()) {
                int mes = rs.getInt("mes");
                int idListaCompra = rs.getInt("id");

                ArrayList<ItemCompra> itensCompra = ItemCompraDAO.getAllItemCompraFromLista(idListaCompra);
                ListaCompra lc = new ListaCompra(Mes.valueOf(mes), itensCompra);
                lc.setId(rs.getInt("id"));
                listaCompras.add(lc);
            }
            
            return listaCompras;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public static void addOneItemToLista(ListaCompra listaCompra, ItemCompra itemcompra) {
        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement st = conn.prepareStatement("INSERT INTO lista_item (id_lista, id_item) values (?, ?)");
            st.setInt(1, listaCompra.getId());
            st.setDouble(2, itemcompra.getId());
            st.executeUpdate();   
            return;
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
