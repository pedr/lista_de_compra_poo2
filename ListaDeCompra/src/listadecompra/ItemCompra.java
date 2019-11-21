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
public class ItemCompra {
    
    private Produto produto;
    private Double qntEfetivaCompra;
    private Double precoMaximoMes;
    private int id;

    public ItemCompra(Produto produto, Double qntEfetivaCompra) {
        this.produto = produto;
        this.qntEfetivaCompra = qntEfetivaCompra;
        this.setPrecoMaximoMes();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Double getQntEfetivaCompra() {
        return qntEfetivaCompra;
    }

    public void setQntEfetivaCompra(Double qntEfetivaCompra) {
        this.qntEfetivaCompra = qntEfetivaCompra;
    }

    public Double getPrecoMaximoMes() {
        return precoMaximoMes;
    }

    private void setPrecoMaximoMes() {
        if (this.produto.getPrecoMaximoAnterior() > 0) {
            this.precoMaximoMes = this.produto.getPrecoMaximoAnterior() * 1.05;
            return;
        }
        this.precoMaximoMes = 0.0;
    }

    @Override
    public String toString() {
        return "Produto: " + this.produto.getNome() + " - Qnt comprada: " + this.getQntEfetivaCompra()
                + " - Valor máximo do mês: " + this.getPrecoMaximoMes();
    }
    
    public String toStringToListaCompra() {
        return "" + this.produto.getNome() + " - " + this.getQntEfetivaCompra();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    
    
}
