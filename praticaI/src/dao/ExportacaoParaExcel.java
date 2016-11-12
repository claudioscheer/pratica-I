/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import enumeraveis.TipoConta;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import model.CarCapContas;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import utils.Utils;
import model.FlxcxEspecificacoes;
import model.FlxcxOperacoes;

/**
 *
 * @author Diego
 */
public class ExportacaoParaExcel {

    private final HSSFWorkbook workbook = new HSSFWorkbook();
    private final HSSFSheet firstSheet = workbook.createSheet("FluxoCaixa");
    private final FlxcxOperacoesDAO operacoesDao = new FlxcxOperacoesDAO();
    private final FlxcxEspecificacoesDAO especificacaoDAO = new FlxcxEspecificacoesDAO();
    private final CarCapContasDAO contasDAO = new CarCapContasDAO();

    public void Exportar(String nomeArquivo, Date dataInicial, Date dataFinal, int filtroData) {

        try {
            FileOutputStream arquivo = new FileOutputStream(new File(nomeArquivo));

            //controla linha posicionada
            int linha = 0;

            double totalEntradas = 0;
            double totalSaidas = 0;

            HSSFRow row = firstSheet.createRow(linha);

            row.createCell(0).setCellValue("Data Inicial: ");
            row.createCell(1).setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(dataInicial));

            row.createCell(3).setCellValue("Data Final: ");
            row.createCell(4).setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(dataFinal));

            linha += 1;
            row = firstSheet.createRow(linha);

            int coluna = 3;
            for (String item : RetornaColunas(filtroData, dataInicial, dataFinal)) {

                row.createCell(coluna).setCellValue(item);

                coluna += 1;
            }

            List<FlxcxEspecificacoes> especificacoes = BuscarEspecificoes();

            for (FlxcxEspecificacoes especificacao : especificacoes) {

                //Linha alimenta uma nova especificacao
                linha += 1;
                row = firstSheet.createRow(linha);

                //Coluna com a descricao da especificacao
                row.createCell(0).setCellValue(especificacao.getEspDescricao());

                int sequenciaOperacao = 0;

                for (FlxcxOperacoes operacao : BuscarOperacoes(especificacao.getEspCodigo())) {

                    //Linha alimenta uma nova operacao
                    linha += 1;
                    row = firstSheet.createRow(linha);

                    //codigo sequencial
                    sequenciaOperacao += 1;
                    row.createCell(0).setCellValue(sequenciaOperacao);

                    //Descricao da operacao
                    row.createCell(1).setCellValue(operacao.getOpDescricao());

                    totalEntradas = 0;
                    totalSaidas = 0;

                    SimpleDateFormat sdf = new SimpleDateFormat("MM");
                    int mesAnterior = Integer.valueOf(sdf.format(dataInicial));

                    int colunaConta = 3; //Deve comecar no tres pq as outra colunas possue outros valores ja

                    for (CarCapContas conta : BuscaContas(operacao.getOpCodigo(), dataInicial, dataFinal)) {

                        switch (filtroData) {

                            case 0: //Diaria
                                break;

                            case 1: //Mensal

                                sdf = new SimpleDateFormat("MM");
                                int mes = Integer.valueOf(sdf.format(conta.getContaDataEmissao()));

                                if (mesAnterior == mes) { //Controla se ta no proximo mes

                                    //Realiza soma
                                    if (conta.getContaTipo() == TipoConta.Entrada) {
                                        totalEntradas += conta.getContaValorPago();
                                    } else {
                                        totalSaidas += conta.getContaValorPago();
                                    }

                                } else {

                                    mesAnterior = mes; //Controla se ta no proximo mes

                                    //Linha alimenta novo valor
                                    row.createCell(colunaConta).setCellValue(totalEntradas - totalSaidas);

                                    colunaConta += 1;

                                }

                                break;

                            case 2: //Anual
                                break;

                        }

                    }

                }
            }

            this.workbook.write(arquivo);
            arquivo.close();

            utils.Utils.notificacao("Exportação concluída com sucesso", Utils.TipoNotificacao.ok, 0);

        } catch (Exception ex) {
            utils.Utils.notificacao("Não foi possível gerar o arquivo de exportação", Utils.TipoNotificacao.erro, 0);
            System.err.println(ex.getMessage());
        }

    }

    public List<FlxcxEspecificacoes> BuscarEspecificoes() {

        return this.especificacaoDAO.ListarTodas();

    }

    public List<FlxcxOperacoes> BuscarOperacoes(int codigoEspecificacao) {

        return this.operacoesDao.BuscaOperacoes(codigoEspecificacao);

    }

    public List<CarCapContas> BuscaContas(int codigoOperacao, Date dataInicial, Date dataFinal) {

        return this.contasDAO.BuscarContasOperacao(codigoOperacao, dataInicial, dataFinal);

    }

    public ArrayList<String> RetornaColunas(int posicao, Date dataInicial, Date dataFinal) {

        ArrayList<String> colunas = new ArrayList<String>();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf;

        switch (posicao) {

            case 0:

                sdf = new SimpleDateFormat("yyyy");
                long data = (dataFinal.getTime() - dataInicial.getTime()) + 3600000;

                long dias = (data / 86400000L);

                for (int i = 0; i <= dias; i++) { //adiciona coluna de dias.
//                    colunas.add(dataInicial + i);
                            
                }

                break;

            case 1: //Mensal

                colunas.add("Janeiro");
                colunas.add("Fevereiro");
                colunas.add("Março");
                colunas.add("Abril");
                colunas.add("Maio");
                colunas.add("Junho");
                colunas.add("Julho");
                colunas.add("Agosto");
                colunas.add("Setembro");
                colunas.add("Outubro");
                colunas.add("Novembro");
                colunas.add("Dezembro");

                break;

            case 2: //Anual

                sdf = new SimpleDateFormat("yyyy");
                int anoInicio = Integer.valueOf(sdf.format(dataInicial));
                int anoFim = Integer.valueOf(sdf.format(BuscaMaiorData(dataInicial, dataFinal)));

                int anoDataFinal = Integer.valueOf(sdf.format(dataFinal));

                if (anoDataFinal > anoFim) {

                    anoFim = anoDataFinal;

                }

                while (anoInicio <= anoFim) {

                    colunas.add(String.valueOf(anoInicio));

                    anoInicio += 1;

                }

                break;

        }

        return colunas;
    }

    public Date BuscaMaiorData(Date dataInicial, Date dataFinal) {

        return this.contasDAO.BuscaUltimaData(TipoConta.ambos, dataInicial, dataFinal);

    }

}
