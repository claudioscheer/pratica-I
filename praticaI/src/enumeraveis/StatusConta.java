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
    PendenteEmser, PendenteVencida, Quitada,;
    
    public static StatusConta getPendenteEmser() {  
        return PendenteEmser;
    }

    public static StatusConta getPendenteVencida() {
        return PendenteVencida;
    }

    public static StatusConta getQuitada() {
        return Quitada;
    }
}
