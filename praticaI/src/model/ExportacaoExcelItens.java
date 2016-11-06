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
public class ExportacaoExcelItens {
    private FlxcxOperacoes operacao;
    private CarCapContas contas;

    public FlxcxOperacoes getOperacao() {
        return operacao;
    }

    public void setOperacao(FlxcxOperacoes operacao) {
        this.operacao = operacao;
    }

    public List<CarCapContas> getContas() {
        return contas;
    }

    public void setContas(List<CarCapContas> contas) {
        this.contas = contas;
    }
    
    
    
}
