/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Diego
 */
public class ColunasExcel {
    private FlxcxEspecificacoes especificacao;
    private FlxcxOperacoes operacao;
    private Date dataColuna;
    private double valorTotal;
    private int linha;
    private int coluna;

    public FlxcxEspecificacoes getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(FlxcxEspecificacoes especificacao) {
        this.especificacao = especificacao;
    }

    public FlxcxOperacoes getOperacao() {
        return operacao;
    }

    public void setOperacao(FlxcxOperacoes operacao) {
        this.operacao = operacao;
    }

    public Date getDataColuna() {
        return dataColuna;
    }

    public void setDataColuna(Date dataColuna) {
        this.dataColuna = dataColuna;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }
    
    
    
    
    
}
