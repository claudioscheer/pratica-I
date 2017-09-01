/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego
 */
public class Operacao {

    private FlxcxOperacoes operacoes;
    private List<Contas> contas = new ArrayList<Contas>();

    public FlxcxOperacoes getOperacoes() {
        return operacoes;
    }

    public void setOperacoes(FlxcxOperacoes operacoes) {
        this.operacoes = operacoes;
    }

    public List<Contas> getContas() {
        return contas;
    }

    public void setContas(List<Contas> contas) {
        this.contas = contas;
    }

}
