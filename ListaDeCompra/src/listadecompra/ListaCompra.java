/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listadecompra;

import java.util.ArrayList;

/**
 *
 * @author pedr
 */
public class ListaCompra implements IsTable {
    
    private Mes mes;
    private ArrayList<ItemCompra> itensCompra;
    private Double totalEstimado;
    private int id;

    public ListaCompra(Mes mes, ArrayList<ItemCompra> itensCompra) {
        this.mes = mes;
        this.itensCompra = itensCompra;
        this.totalEstimado = this.getTotalEstimado();
    }

    public Mes getMes() {
        return mes;
    }

    public void setMes(Mes mes) {
        this.mes = mes;
    }

    public ArrayList<ItemCompra> getItensCompra() {
        return this.itensCompra;
    }

    public void setItensCompra(ArrayList<ItemCompra> itensCompra) {
        this.itensCompra = itensCompra;
    }

    public Double getTotalEstimado() {
        this.totalEstimado = this.setTotalEstimado();
        return this.totalEstimado;
    }

    private Double setTotalEstimado() {
        Double total = 0.0;
        for (ItemCompra itemCompra : this.itensCompra) {
            Produto p = itemCompra.getProduto();
            total += p.getPrecoMaximoAnterior() * itemCompra.getQntEfetivaCompra();
        }
        return total;
    }

    public void addOneItemCompra(ItemCompra ic) {
        this.itensCompra.add(ic);
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
        String str = this.mes.toString();
        for (ItemCompra ic : this.itensCompra) {
            str += "\n\t\t" + ic.toStringToListaCompra();
        }
        str += "\n\tTotal Estimado: " + this.totalEstimado + "\n";
        return str;
    
    }
    
    
    
    
    
}
