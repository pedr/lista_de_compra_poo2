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
public class ProdutoDAO {
    
    public static Produto create(Produto produto) {
        try {
            String generatedColumns[] = { "id" };
            Connection conn = Conexao.getConexao();
            PreparedStatement st = conn.prepareStatement("INSERT INTO produtos (nome, descricao, unidade_compra, qnt_prevista_mes) values (?, ?, ?, ?)", generatedColumns);
            st.setString(1, produto.getNome());
            st.setString(2, produto.getDescricaoProduto());
            st.setString(3, produto.getUnidadeCompra().toString());
            st.setDouble(4, produto.getQntPrevistoMes());
            st.executeUpdate();
            
            ResultSet generetedKeys = st.getGeneratedKeys();
            generetedKeys.next();
            int id = generetedKeys.getInt(1);
            produto.setId(id);
            return produto;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public static Produto get(int id) {
        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM produtos WHERE id = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            String nome = rs.getString("nome");
            String descricao = rs.getString("descricao");
            String unidadeCompra = rs.getString("unidade_compra");
            Double qntPrevista = rs.getDouble("qnt_prevista_mes");
            
            Produto p = new Produto(nome, descricao, UnidadeCompra.valueOf(unidadeCompra), qntPrevista);
            p.setId(id);
            
            double maxPrice = HistoricoCompraDAO.getMaxValue(p);
            p.setPrecoMaximoAnterior(maxPrice);
            
            return p;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public static ArrayList<Produto> getAll() {
        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM produtos");
            ResultSet rs = st.executeQuery();
            
            ArrayList<Produto> produtos = new ArrayList<>();
            
            while (rs.next()) {
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                String unidadeCompra = rs.getString("unidade_compra");
                Double qntPrevista = rs.getDouble("qnt_prevista_mes");

                Produto p = new Produto(nome, descricao, UnidadeCompra.valueOf(unidadeCompra), qntPrevista);
                p.setId(rs.getInt("id"));
                
                
                double maxPrice = HistoricoCompraDAO.getMaxValue(p);
                p.setPrecoMaximoAnterior(maxPrice);

                produtos.add(p);
            }
            
            return produtos;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
