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
public class SupermercadoDAO {
    
    
    public static Supermercado create(Supermercado supermercado) {
        try {
            String generatedColumns[] = { "id" };
            Connection conn = Conexao.getConexao();
            String nome = supermercado.getNome();
            PreparedStatement st = conn.prepareStatement("INSERT INTO supermercados (nome) values (?)", generatedColumns);
            st.setString(1, nome);
            st.executeUpdate();
            
            ResultSet generetedKeys = st.getGeneratedKeys();
            generetedKeys.next();
            int id = generetedKeys.getInt(1);
            supermercado.setId(id);
            return supermercado;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public static Supermercado get(int id) {
        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM supermercados WHERE id = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            String nome = rs.getString("nome");
            int idSupermercado = rs.getInt("id");
            
            Supermercado sm = new Supermercado(nome);
            sm.setId(idSupermercado);
            return sm;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
     public static ArrayList<Supermercado> getAll() {
        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM supermercados");
            ResultSet rs = st.executeQuery();
            
            ArrayList<Supermercado> supermercados = new ArrayList<>();
            
            while (rs.next()) {
                String nome = rs.getString("nome");

                Supermercado s = new Supermercado(nome);
                s.setId(rs.getInt("id"));
                supermercados.add(s);
            }
            
            return supermercados;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
