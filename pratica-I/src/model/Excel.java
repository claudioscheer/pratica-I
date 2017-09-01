/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Diego
 */
public class Excel {

    private FlxcxEspecificacoes especificacao;
    private List<Operacao> operacao = new ArrayList<>();

    public FlxcxEspecificacoes getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(FlxcxEspecificacoes especificacao) {
        this.especificacao = especificacao;
    }

    public List<Operacao> getOperacao() {
        return operacao;
    }

    public void setOperacao(List<Operacao> operacao) {
        this.operacao = operacao;
    }

}
