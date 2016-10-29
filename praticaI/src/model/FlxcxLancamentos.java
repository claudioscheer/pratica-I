/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author Diego
 */
public class FlxcxLancamentos {
    
    private List<CarCapContas> carCapContas;
    private List<FlxcxMovimentoBancario> movimentosBancarios;

    public List<CarCapContas> getCarCapContas() {
        return carCapContas;
    }

    public void setCarCapContas(List<CarCapContas> carCapContas) {
        this.carCapContas = carCapContas;
    }

    public List<FlxcxMovimentoBancario> getMovimentosBancarios() {
        return movimentosBancarios;
    }

    public void setMovimentosBancarios(List<FlxcxMovimentoBancario> movimentosBancarios) {
        this.movimentosBancarios = movimentosBancarios;
    }
       
}
