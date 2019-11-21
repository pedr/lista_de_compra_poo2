/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listadecompra;

import java.util.Objects;

/**
 *
 * @author pedr
 */
public class Produto implements IsTable {
    
    private String nome;
    private String descricaoProduto;
    private UnidadeCompra unidadeCompra;
    private Double qntPrevistoMes; // qnt ela acha que gasta por mês do produto
    private Double precoMaximoAnterior; // começa com 0, mas 
    private int id;
    
    Produto(String nome, String descricaoProduto, UnidadeCompra unidadeCompra, Double qntPrevistoMes) {
        this.nome = nome;
        this.descricaoProduto = descricaoProduto;
        this.unidadeCompra = unidadeCompra;
        this.qntPrevistoMes = qntPrevistoMes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public UnidadeCompra getUnidadeCompra() {
        return unidadeCompra;
    }

    public void setUnidadeCompra(UnidadeCompra unidadeCompra) {
        this.unidadeCompra = unidadeCompra;
    }

    public Double getQntPrevistoMes() {
        return qntPrevistoMes;
    }

    public void setQntPrevistoMes(Double qntPrevistoMes) {
        this.qntPrevistoMes = qntPrevistoMes;
    }

    public Double getPrecoMaximoAnterior() {
        return this.precoMaximoAnterior;
    }

    public void setPrecoMaximoAnterior(Double precoMaximoAnterior) {
        this.precoMaximoAnterior = precoMaximoAnterior;
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
        return "Nome: " + nome + " - Unidade de compra: " + unidadeCompra +
                " - Qnt prevista no mês: " + qntPrevistoMes + " - Descricao: " +  descricaoProduto
                + " - Preço máximo: " + this.getPrecoMaximoAnterior();
    
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.nome);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
