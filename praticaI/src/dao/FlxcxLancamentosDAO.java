/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.FlxcxLancamentos;

/**
 *
 * @author Diego
 */
public class FlxcxLancamentosDAO {

    public FlxcxLancamentos ListarLancamentos() {

        FlxcxLancamentos lancamentos = new FlxcxLancamentos();

        lancamentos.setCarCapContas(new CarCapContasDAO().ListarTodos());
        lancamentos.setMovimentosBancarios(new FlxcxMovimentoBancarioDAO().ListarTodas());

        return lancamentos;

    }

}
