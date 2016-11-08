/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CarCapContas;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import utils.Utils;
import model.ExportacaoExcel;
import model.ExportacaoExcelItens;
import model.FlxcxEspecificacoes;
import model.FlxcxOperacoes;

/**
 *
 * @author Diego
 */
public class ExportacaoParaExcel {

    private HSSFWorkbook workbook = new HSSFWorkbook();
    private HSSFSheet firstSheet = workbook.createSheet("FluxoCaixa");
    private final FlxcxOperacoesDAO operacoesDao = new FlxcxOperacoesDAO();
    private final FlxcxEspecificacoesDAO especificacaoDAO = new FlxcxEspecificacoesDAO();
    private final CarCapContasDAO contasDAO = new CarCapContasDAO();

    public void Exportar(List<ExportacaoExcel> exportacao, String nomeArquivo, Date dataInicial, Date dataFinal) {

        try {
            FileOutputStream arquivo = new FileOutputStream(new File(nomeArquivo));

            HSSFRow row = firstSheet.createRow(0);

            row.createCell(0).setCellValue("Data Inicial: ");
            row.createCell(1).setCellValue(dataInicial);
            
            row.createCell(3).setCellValue("Data Final: ");
            row.createCell(4).setCellValue(dataFinal);

            row = firstSheet.createRow(1);

            List<FlxcxEspecificacoes> especificacoes = BuscarEspecificoes();

            for (FlxcxEspecificacoes especificacao : especificacoes) {

                for (FlxcxOperacoes operacao : BuscarOperacoes(especificacao.getEspCodigo()) ) {
                    
                    for (CarCapContas conta : BuscaContas(operacao.getOpCodigo())){
                    
                        
                    
                    }
                    
                    
                }
            }

            this.workbook.write(arquivo);

        } catch (Exception ex) {
            utils.Utils.notificacao("Não foi possível gerar o arquivo de exportação", Utils.TipoNotificacao.erro, 0);
        }

    }

    public List<FlxcxEspecificacoes> BuscarEspecificoes() {

        return this.especificacaoDAO.ListarTodas();

    }

    public List<FlxcxOperacoes> BuscarOperacoes(int codigoEspecificacao) {

        return this.operacoesDao.BuscaOperacoes(codigoEspecificacao);

    }
    
    public List<CarCapContas> BuscaContas(int codigoOperacao){
    
        return this.contasDAO.BuscarContasOperacao(codigoOperacao);
        
    
    }

}
