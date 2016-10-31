/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enumeraveis;

/**
 *
 * @author Diego
 */
public enum StatusConta {
    Pendente, Fechada, Vencida,PendenteParcial;
    
    public static StatusConta getPendente() {    
        return Pendente;
    }

    public static StatusConta getFechada() {
        return Fechada;
    }

    public static StatusConta getVencida() {
        return Vencida;
    }

    public static StatusConta getPendenteParcial() {
        return PendenteParcial;
    }
}
