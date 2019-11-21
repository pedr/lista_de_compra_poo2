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
public enum Mes {    
    JAN(1), FEV(2), MAR(3), ABR(4), MAI(5), JUN(6), JUL(7), AGO(8), SET(9), OUT(10), NOV(11), DEZ(12);

    public int valor;
    Mes(int valor) {
        this.valor = valor;
    }
    
    public static Mes valueOf(int valueMes) {
        for (Mes mes : Mes.values()) {
            if (mes.valor == valueMes) {
                return mes;
            }
        }
        return null;
    }
}
