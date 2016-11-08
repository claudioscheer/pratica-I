/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import dao.FlxcxEspecificacoesDAO;
import java.util.List;
import model.FlxcxEspecificacoes;
import model.FlxcxOperacoes;

/**
 *
 * @author Diego
 */
public class InicializacaoDadosFluxoCaixa {
    
    FlxcxEspecificacoesDAO especificacaoDAO = new FlxcxEspecificacoesDAO();
    
    public void execute(){
    
        
        
        
        
    }
    
    public void InserirEspecificacao(int espCodigo, String espDescricao, List<FlxcxOperacoes> operacoes){
        
        FlxcxEspecificacoes especificacao = new FlxcxEspecificacoes();
        
        especificacao.setEspCodigo(espCodigo);
        especificacao.setEspDescricao(espDescricao);
        
        especificacao.setOperacao(operacoes);
        
        especificacaoDAO.Inserir(especificacao);
        
    }
    
}
