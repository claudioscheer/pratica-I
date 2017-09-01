/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import dao.EstTipoOperacaoDAO;
import model.CarEstTipoOperacao;

/**
 *
 * @author Anderson
 */
public class IniciarDadosEstoque {

    private final EstTipoOperacaoDAO tpOpDao = new EstTipoOperacaoDAO();
    private CarEstTipoOperacao operacao;
    
    public void execute() {
        new Thread(dados1).start();        
    }
    private Runnable dados1 = new Runnable() {

        @Override
        public void run() {
            operacao = InserirEspecificacao(1, "Entrada", "Para Entradas");
            operacao = InserirEspecificacao(2, "Saída", "Para Saídas");
        }
    };

    public CarEstTipoOperacao InserirEspecificacao(int codigo, String nome, String descricao) {

        CarEstTipoOperacao tpop = new CarEstTipoOperacao();
        tpop.setTpOpId(codigo);
        tpop.setTpOpNome(nome);
        tpop.setTpOpDescricao(descricao);
        this.tpOpDao.Inserir(tpop);
        return tpop;
    }
}
