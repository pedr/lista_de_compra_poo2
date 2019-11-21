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
public class HistoricoCompra implements IsTable {
    
    private Mes mes;
    private Produto produto;
    private Double valorPago;
    private Supermercado supermecado;
    private int id;

    public HistoricoCompra(Mes mes, Produto produto, Double valorPago, Supermercado supermecado) {
        this.mes = mes;
        this.produto = produto;
        this.valorPago = valorPago;
        this.supermecado = supermecado;
        if (valorPago > this.produto.getPrecoMaximoAnterior()) {
            this.produto.setPrecoMaximoAnterior(valorPago);
        }
    }

    public Mes getMes() {
        return mes;
    }

    public void setMes(Mes mes) {
        this.mes = mes;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public Supermercado getSupermecado() {
        return supermecado;
    }

    public void setSupermecado(Supermercado supermecado) {
        this.supermecado = supermecado;
    }

    @Override
    public String toString() {
        return "" + this.produto.getNome() + " - " + this.getMes() + " -\t " + 
                this.getValorPago() + " -\t\t " + this.getSupermecado().getNome();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
