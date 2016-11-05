/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import enumeraveis.FiltroData;
import java.util.Date;

/**
 *
 * @author Diego
 */
public class ExportacaoExcel {
    
    private Date dataInicial;
    private Date dataFinal;
    private FiltroData filtroData;
    private FlxcxOperacoes operacoes;
    private CarCapContas contas;

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public FiltroData getFiltroData() {
        return filtroData;
    }

    public void setFiltroData(FiltroData filtroData) {
        this.filtroData = filtroData;
    }

    public FlxcxOperacoes getOperacoes() {
        return operacoes;
    }

    public void setOperacoes(FlxcxOperacoes operacoes) {
        this.operacoes = operacoes;
    }

    public CarCapContas getContas() {
        return contas;
    }

    public void setContas(CarCapContas contas) {
        this.contas = contas;
    }
       
    
}
