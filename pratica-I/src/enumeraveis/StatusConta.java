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
    Pendente, PendenteVencida, Quitada,;
    
    public static StatusConta getPendente() {  
        return Pendente;
    }

    public static StatusConta getPendenteVencida() {
        return PendenteVencida;
    }

    public static StatusConta getQuitada() {
        return Quitada;
    }
}
