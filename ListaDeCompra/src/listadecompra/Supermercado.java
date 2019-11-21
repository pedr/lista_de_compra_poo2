/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listadecompra;

/**
 *
 * @author pedr
 */
public class Supermercado implements IsTable{
    
    private String nome;
    private int id;

    public Supermercado(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "Nome: " + this.getNome(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
