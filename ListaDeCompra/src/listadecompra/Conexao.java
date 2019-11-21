/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listadecompra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 *
 * @author jefersonfs
 */
public class Conexao {
    
    private static Connection connection;
    
    private static String serverName = "localhost";
    private static String mydatabase = "lista_de_compra";
    private static String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
    private static String username = "root";
    private static String password = "";
    
    private static String status = "Desconectado";

    public static Connection getConexao(){
        connection = null;
        
        if (connection == null){
            try {
                String driverName = "com.mysql.jdbc.Driver";
                Class.forName(driverName);
                connection = DriverManager.getConnection(url, username, password);
                if (connection != null) {
                    status = "Conectado!";                 
                } else {
                    status = "Desconectado!";
                }
            } catch (ClassNotFoundException e) {
                System.out.println("O driver expecificado nao foi encontrado.");
            } catch (SQLException e) {
                System.out.println(e);
                System.out.println("Nao foi possivel conectar ao Banco de Dados.");
            }
        }
        return connection;
    }
    

    public String statusConection() {
        return status;
    }

    public static boolean encerraConexao() {
        try{
            connection.close();
//            Conexao.getConexao().close();
            return true;
        } catch (SQLException e) {
            return false;
        }
//        try {
//
//            Conexao.getConexaoMySQL().close();
//
//            return true;
//
//        } catch (SQLException e) {
//
//            return false;
//
//        }
    }

    public Connection ReiniciarConexao() {
        encerraConexao();
        return getConexao();
//        FecharConexao();
//
//        return Conexao.getConexaoMySQL();
    }

}

