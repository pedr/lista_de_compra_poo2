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
public enum UnidadeCompra {
    kg(1), litro(2), unidade(3);
    
    public int valor;

    UnidadeCompra(int valorUnidade) {
        this.valor = valorUnidade;
    }
    
    public static UnidadeCompra valueOf(int valueUC) {
        for (UnidadeCompra uc : UnidadeCompra.values()) {
            if (uc.valor == valueUC) {
                return uc;
            }
        }
        return null;
    }
}
