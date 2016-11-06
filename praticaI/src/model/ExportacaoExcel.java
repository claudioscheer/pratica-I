/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import enumeraveis.FiltroData;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Diego
 */
public class ExportacaoExcel {
    
    private Date dataInicial;
    private Date dataFinal;
    private FiltroData filtroData;
    private FlxcxEspecificacoes especificacao;
    private List<ExportacaoExcelItens> itens;

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

    public FlxcxEspecificacoes getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(FlxcxEspecificacoes especificacao) {
        this.especificacao = especificacao;
    }

    public List<ExportacaoExcelItens> getItens() {
        return itens;
    }

    public void setItens(List<ExportacaoExcelItens> itens) {
        this.itens = itens;
    }

}
