/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import enumeraveis.FiltroData;
import enumeraveis.LogTipo;
import enumeraveis.TipoConta;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import model.CarCapContas;
import model.ColunasExcel;
import model.Contas;
import model.Excel;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import utils.Utils;
import model.FlxcxEspecificacoes;
import model.FlxcxOperacoes;
import model.Operacao;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.ComparatorChain;

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
    private int linhaCabecalho = 0;
    private Excel valor = new Excel();
    private List<Excel> colunas = new ArrayList<>();

    public void Exportar(String nomeArquivo, Date dataInicial, Date dataFinal, FiltroData filtroData) {

        try {
            FileOutputStream arquivo = new FileOutputStream(new File(nomeArquivo));

            //controla linha posicionada
            int linha = 0;

            HSSFRow row = firstSheet.createRow(linha);

            this.AdicionaLinha(row, 0, "Data Inicial: ");
            this.AdicionaLinha(row, 1, new SimpleDateFormat("dd-MM-yyyy").format(dataInicial));

            this.AdicionaLinha(row, 3, "Data Final: ");
            this.AdicionaLinha(row, 4, new SimpleDateFormat("dd-MM-yyyy").format(dataFinal));

            linha += 1;
            row = firstSheet.createRow(linha);

            int coluna = 3;

            this.linhaCabecalho = linha;

            //futuramente pode ser continuada a implementacao das diferentes formas de visualização
//            switch (filtroData) {
//
//                case Diario:
//                    this.ExportacaoDiariaNova(dataInicial, dataFinal, row, linha);
//                    break;
//
//                case Mensal:

                    ArrayList<String> colunasExcel = RetornaColunas(filtroData, dataInicial, dataFinal);

                    for (String item : colunasExcel) {

                        this.AdicionaLinha(row, coluna, item);

                        coluna += 1;
                    }

                    this.ExportacaoMensal(dataInicial, dataFinal, row, linha);
                    
//                    break;
//
//            }

            this.workbook.write(arquivo);
            arquivo.close();

            utils.Utils.notificacao("Exportação concluída com sucesso", Utils.TipoNotificacao.ok, 0);

        } catch (Exception ex) {
            utils.Utils.notificacao("Não foi possível gerar o arquivo de exportação", Utils.TipoNotificacao.erro, 0);
            Utils.log("Falha ao gerar relatório Excel", ex.toString(), LogTipo.ERRO);

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

    public ArrayList<String> RetornaColunas(FiltroData filtroData, Date dataInicial, Date dataFinal) {

        ArrayList<String> colunas = new ArrayList<String>();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf;

        switch (filtroData) {

            case Diario:

//                sdf = new SimpleDateFormat("yyyy");
//
//                long data = (dataFinal.getTime() - dataInicial.getTime()) + 3600000;
//
//                long dias = (data / 86400000L);
//
//                Calendar calendar = Calendar.getInstance();
//                calendar.setTime(dataInicial);
//
//                for (int i = 0; i <= dias; i++) { //adiciona coluna de dias.
//
//                    if (i == 0) {
//                        sdf = new SimpleDateFormat("dd/MM/yyyy");
//                        colunas.add(sdf.format(calendar.getTime()));
//                    } else {
//                        calendar.add(Calendar.DAY_OF_MONTH, 1);
//                        sdf = new SimpleDateFormat("dd/MM/yyyy");
//                        colunas.add(sdf.format(calendar.getTime()));
//                    }
//
//                }
//
//                break;
            case Mensal:

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

            case Anual:

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

    private void ExportacaoMensal(Date dataInicial, Date dataFinal, HSSFRow row, int linha) {

        double totalEntradas = 0;
        double totalSaidas = 0;
        int mes = 0;

        List<FlxcxEspecificacoes> especificacoes = BuscarEspecificoes();

        int mesAnterior = 0;

        for (FlxcxEspecificacoes especificacao : especificacoes) {

            List<FlxcxOperacoes> operacoes = BuscarOperacoes(especificacao.getEspCodigo());

            for (FlxcxOperacoes operacao : operacoes) {

                totalEntradas = 0;
                totalSaidas = 0;
                mesAnterior = 0;

                boolean mostrar = true;

                List<CarCapContas> contas = BuscaContas(operacao.getOpCodigo(), dataInicial, dataFinal);

                for (CarCapContas conta : contas) {

                    SimpleDateFormat sdf = new SimpleDateFormat("MM");
                    mes = Integer.valueOf(sdf.format(conta.getContaDataEmissao()));

                    if (mostrar) {

                        //Linha alimenta uma nova especificacao
                        linha += 1;
                        row = firstSheet.createRow(linha);

                        //Coluna com a descricao da especificacao
                        this.AdicionaLinha(row, 0, especificacao.getEspDescricao());

                        int sequenciaOperacao = 0;

                        //Linha alimenta uma nova operacao
                        linha += 1;
                        row = firstSheet.createRow(linha);

                        //codigo sequencial
                        sequenciaOperacao += 1;
                        this.AdicionaLinha(row, 0, sequenciaOperacao);

                        //Descricao da operacao
                        firstSheet.autoSizeColumn(1);
                        row.createCell(1).setCellValue(operacao.getOpDescricao());

                        mostrar = false;
                    } else {

                    }

                    if (mes != mesAnterior) {

                        totalEntradas = 0;
                        totalSaidas = 0;

                        mesAnterior = mes;

                        //Realiza soma
                        if (conta.getContaTipo() == TipoConta.Entrada) {
                            totalEntradas += conta.getContaValorPago();
                        } else {
                            totalSaidas += conta.getContaValorPago();
                        }

                    } else {

                        //Realiza soma
                        if (conta.getContaTipo() == TipoConta.Entrada) {
                            totalEntradas += conta.getContaValorPago();
                        } else {
                            totalSaidas += conta.getContaValorPago();
                        }

                    }

                    switch (mes) {

                        case 1:
                            this.AdicionaLinha(row, 3, (totalEntradas - totalSaidas));
                            break;
                        case 2:
                            this.AdicionaLinha(row, 4, (totalEntradas - totalSaidas));
                            break;
                        case 3:
                            this.AdicionaLinha(row, 5, (totalEntradas - totalSaidas));
                            break;
                        case 4:
                            this.AdicionaLinha(row, 6, (totalEntradas - totalSaidas));
                            break;
                        case 5:
                            this.AdicionaLinha(row, 7, (totalEntradas - totalSaidas));
                            break;
                        case 6:
                            this.AdicionaLinha(row, 8, (totalEntradas - totalSaidas));
                            break;
                        case 7:
                            this.AdicionaLinha(row, 9, (totalEntradas - totalSaidas));
                            break;
                        case 8:
                            this.AdicionaLinha(row, 10, (totalEntradas - totalSaidas));
                            break;
                        case 9:
                            this.AdicionaLinha(row, 11, (totalEntradas - totalSaidas));
                            break;
                        case 10:
                            this.AdicionaLinha(row, 12, (totalEntradas - totalSaidas));
                            break;
                        case 11:
                            this.AdicionaLinha(row, 13, (totalEntradas - totalSaidas));
                            break;
                        case 12:
                            this.AdicionaLinha(row, 14, (totalEntradas - totalSaidas));
                            break;

                    }

                }

                if (totalEntradas != 0 || totalSaidas != 0) {
                    switch (mes) {

                        case 1:
                            this.AdicionaLinha(row, 3, (totalEntradas - totalSaidas));
                            break;
                        case 2:
                            this.AdicionaLinha(row, 4, (totalEntradas - totalSaidas));
                            break;
                        case 3:
                            this.AdicionaLinha(row, 5, (totalEntradas - totalSaidas));
                            break;
                        case 4:
                            this.AdicionaLinha(row, 6, (totalEntradas - totalSaidas));
                            break;
                        case 5:
                            this.AdicionaLinha(row, 7, (totalEntradas - totalSaidas));
                            break;
                        case 6:
                            this.AdicionaLinha(row, 8, (totalEntradas - totalSaidas));
                            break;
                        case 7:
                            this.AdicionaLinha(row, 9, (totalEntradas - totalSaidas));
                            break;
                        case 8:
                            this.AdicionaLinha(row, 10, (totalEntradas - totalSaidas));
                            break;
                        case 9:
                            this.AdicionaLinha(row, 11, (totalEntradas - totalSaidas));
                            break;
                        case 10:
                            this.AdicionaLinha(row, 12, (totalEntradas - totalSaidas));
                            break;
                        case 11:
                            this.AdicionaLinha(row, 13, (totalEntradas - totalSaidas));
                            break;
                        case 12:
                            this.AdicionaLinha(row, 14, (totalEntradas - totalSaidas));
                            break;

                    }

                }

            }

        }

    }

    private void ExportacaoDiaria(Date dataInicial, Date dataFinal, HSSFRow row, int linha) {

        double totalEntradas;
        double totalSaidas;
        double entrada;
        double saida;
        int coluna;
        Date dataMostrar;
        Date dataConta;
        Calendar c = Calendar.getInstance();
        c.set(1, 1, 1800);
        Date dataAnterior = c.getTime();

        List<FlxcxEspecificacoes> especificacoes = BuscarEspecificoes();

        for (FlxcxEspecificacoes especificacao : especificacoes) {

            List<FlxcxOperacoes> operacoes = BuscarOperacoes(especificacao.getEspCodigo());

            for (FlxcxOperacoes operacao : operacoes) {

                totalEntradas = 0;
                totalSaidas = 0;
                entrada = 0;
                saida = 0;
                coluna = 3;
                dataConta = c.getTime();
                dataAnterior = c.getTime();
                dataMostrar = c.getTime();

                boolean mostrar = true;
                boolean primeiroRegistro = true;

                List<CarCapContas> contas = BuscaContas(operacao.getOpCodigo(), dataInicial, dataFinal);

                for (CarCapContas conta : contas) {

                    dataConta = conta.getContaDataEmissao();

                    if (mostrar) {

                        //Linha alimenta uma nova especificacao
                        linha += 1;
                        row = firstSheet.createRow(linha);

                        //Coluna com a descricao da especificacao
                        this.AdicionaLinha(row, 0, especificacao.getEspDescricao());

                        int sequenciaOperacao = 0;

                        //Linha alimenta uma nova operacao
                        linha += 1;
                        row = firstSheet.createRow(linha);

                        //codigo sequencial
                        sequenciaOperacao += 1;
                        this.AdicionaLinha(row, 0, sequenciaOperacao);
                        this.AdicionaLinha(row, 1, operacao.getOpDescricao());
                        mostrar = false;

                    }

                    boolean novacoluna = false;
                    if (!conta.getContaDataEmissao().equals(dataAnterior)) {

                        novacoluna = true;

                        dataMostrar = dataAnterior;
                        dataAnterior = dataConta;

                        entrada = totalEntradas;
                        saida = totalSaidas;

                        totalEntradas = 0;
                        totalSaidas = 0;

                        //Isso tem que ser repetido, pois quando vier a nova data deve contar junto depois que adicionou a antiga
                        //Realiza soma
                        if (conta.getContaTipo() == TipoConta.Entrada) {
                            totalEntradas += conta.getContaValorPago();
                        } else {
                            totalSaidas += conta.getContaValorPago();
                        }

                    } else {

                        //Realiza soma
                        if (conta.getContaTipo() == TipoConta.Entrada) {
                            totalEntradas += conta.getContaValorPago();
                        } else {
                            totalSaidas += conta.getContaValorPago();
                        }

                    }

                    this.AdicionaColunaCabecalho(coluna, dataMostrar);
                    this.AdicionaLinha(row, coluna, (entrada - saida));

                    if (novacoluna && !primeiroRegistro) {

                        coluna += 1;

                    }

                    primeiroRegistro = false;

                }

                if (totalEntradas != 0 || totalSaidas != 0) {

                    dataMostrar = dataAnterior;
                    entrada = totalEntradas;
                    saida = totalSaidas;

                    this.AdicionaColunaCabecalho(coluna, dataMostrar);
                    this.AdicionaLinha(row, coluna, (entrada - saida));

                }
            }

        }
    }

    private void ExportacaoDiariaNova(Date dataInicial, Date dataFinal, HSSFRow row, int linha) {

        BuscaContas(dataInicial, dataFinal);

        List<ColunasExcel> arquivo = new ArrayList<ColunasExcel>();

        int sequenciaOperacao = 0;
        for (Excel excel : colunas) {

            for (Operacao op : excel.getOperacao()) {
                boolean mostrar = true;
                if (op.getContas().size() > 0) {

                    for (Contas conta : op.getContas()) {

                        ColunasExcel colunasExcel = new ColunasExcel();

                        colunasExcel.setEspecificacao(excel.getEspecificacao());
                        colunasExcel.setOperacao(op.getOperacoes());

//                        this.AdicionaColunaCabecalho(3, conta.getDataColuna());
                        double entrada = this.Soma(conta.getValoresEntrada());

                        double saida = this.Soma(conta.getValoresSaida());

                        colunasExcel.setDataColuna(conta.getDataColuna());
                        colunasExcel.setValorTotal(entrada - saida);

                        arquivo.add(colunasExcel);

                    }

                }
            }
        }

        int coluna = 3;

        BeanComparator fieldComparator = new BeanComparator("dataColuna");

        Collections.sort(arquivo, fieldComparator);

        Calendar c = Calendar.getInstance();
        c.set(1, 1, 1800);
        Date dataAnterior = c.getTime();

        List<ColunasExcel> mostrar = new ArrayList<ColunasExcel>();

        for (ColunasExcel linhaExc : arquivo) {

            boolean acrescentarColuna = true;
            boolean acrescentarLinha = true;

            for (ColunasExcel colunaExc : arquivo) {

                if (linhaExc.getEspecificacao().getEspCodigo() == colunaExc.getEspecificacao().getEspCodigo()) {
                

                }

                if (linhaExc.getDataColuna().equals(colunaExc.getDataColuna())) {
                   coluna -= 1;           

                }

            }

            

            linhaExc.setColuna(coluna);
            linhaExc.setLinha(linha);

            mostrar.add(linhaExc);

            coluna += 1;
            linha += 1;
            
        }

        int espCodigoAnterior = 0, opCodigoAnterior = 0;

        for (ColunasExcel excel : mostrar) {

            this.AdicionaLinha(row, excel.getColuna(), excel.getDataColuna());

            linha = excel.getLinha();            
            row = firstSheet.createRow(linha);

            //Coluna com a descricao da especificacao
            this.AdicionaLinha(row, 0, excel.getEspecificacao().getEspDescricao());

            //Linha alimenta uma nova operacao
            linha += 1;
            row = firstSheet.createRow(linha);

            //codigo sequencial
            sequenciaOperacao += 1;
            this.AdicionaLinha(row, 0, sequenciaOperacao);
            this.AdicionaLinha(row, 1, excel.getOperacao().getOpDescricao());

            this.AdicionaLinha(row, excel.getColuna(), excel.getValorTotal());

            linha += 1;
            row = firstSheet.createRow(linha);

        }

        /*
         if (espCodigoAnterior != 0 && espCodigoAnterior != e.getEspecificacao().getEspCodigo()) {
         //Linha alimenta uma nova especificacao
         linha += 1;
         row = firstSheet.createRow(linha);

         //Coluna com a descricao da especificacao
         this.AdicionaLinha(row, 0, e.getEspecificacao().getEspDescricao());

         }

         if (opCodigoAnterior != 0 && opCodigoAnterior != e.getOperacao().getOpCodigo()) {
         //Linha alimenta uma nova operacao
         linha += 1;
         row = firstSheet.createRow(linha);

         //codigo sequencial
         sequenciaOperacao += 1;
         this.AdicionaLinha(row, 0, sequenciaOperacao);
         this.AdicionaLinha(row, 1, e.getOperacao().getOpDescricao());

         }

         if (!dataAnterior.equals(c.getTime()) && e.getDataColuna().before(dataAnterior)) {
         coluna += 1;
         } else {

         this.AdicionaColunaCabecalho(coluna, e.getDataColuna());

         }
            
         this.AdicionaLinha(row, coluna, e.getValorTotal());
            
         coluna += 1;
            
         dataAnterior = e.getDataColuna();
         espCodigoAnterior = e.getEspecificacao().getEspCodigo();
         opCodigoAnterior = e.getOperacao().getOpCodigo();
         */
    }

    private void BuscaContas(Date dataInicial, Date dataFinal) {

        List<FlxcxEspecificacoes> especificacoes = BuscarEspecificoes();

        boolean carregar = false;

        for (FlxcxEspecificacoes especificacao : especificacoes) {

            this.valor = new Excel();
            this.valor.setEspecificacao(especificacao);

            List<FlxcxOperacoes> operacoes = BuscarOperacoes(especificacao.getEspCodigo());

            for (FlxcxOperacoes operacao : operacoes) {

                Operacao op = new Operacao();
                op.setOperacoes(operacao);

                boolean mostrar = true;
                boolean primeiroRegistro = true;

                List<CarCapContas> contas = BuscaContas(operacao.getOpCodigo(), dataInicial, dataFinal);

                Contas cont = new Contas();

                Calendar c = Calendar.getInstance();
                c.set(1, 1, 1800);
                Date dataAnterior = c.getTime();

                for (CarCapContas conta : contas) {

                    if (!conta.getContaDataEmissao().equals(dataAnterior)) {

                        if (!dataAnterior.equals(c.getTime())) {
                            cont.setDataColuna(dataAnterior);
                            op.getContas().add(cont);
                            cont = new Contas();
                        }

                        dataAnterior = conta.getContaDataEmissao();

                    }

                    if (conta.getContaTipo() == TipoConta.Entrada) {

                        cont.getValoresEntrada().add(conta);
                    } else {

                        cont.getValoresSaida().add(conta);

                    }

                }

                if (!dataAnterior.equals(c.getTime())) {
                    cont.setDataColuna(dataAnterior);
                    op.getContas().add(cont);
                    cont = new Contas();
                    this.valor.getOperacao().add(op);
                    carregar = true;
                }

            }

            if (carregar) {
                this.colunas.add(this.valor);
            }
        }

    }

    private double Soma(List<CarCapContas> valores) {

        double total = 0;
        for (CarCapContas valor : valores) {

            total += valor.getContaValorPago();

        }

        return total;

    }

    private void AdicionaLinha(HSSFRow row, int coluna, double valor) {

        firstSheet.autoSizeColumn(coluna);
        
        //Linha alimenta novo valor
        row.createCell(coluna).setCellValue(valor);

    }

    private void AdicionaLinha(HSSFRow row, int coluna, String valor) {

        firstSheet.autoSizeColumn(coluna);
        
        //Linha alimenta novo valor
        row.createCell(coluna).setCellValue(valor);

    }

    private void AdicionaLinha(HSSFRow row, int coluna, Date valor) {
        
        firstSheet.autoSizeColumn(coluna);

        //Linha alimenta novo valor
        row.createCell(coluna).setCellValue(valor);

    }

    private void AdicionaLinha(HSSFRow row, int coluna, int valor) {
        
        firstSheet.autoSizeColumn(coluna);
        
        //Linha alimenta novo valor
        row.createCell(coluna).setCellValue(valor);

    }

    private void AdicionaColunaCabecalho(int coluna, Date valor) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(valor);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String data = sdf.format(calendar.getTime());

        //Linha alimenta novo valor
        HSSFRow rowcabec = firstSheet.getRow(linhaCabecalho);

        rowcabec.createCell(coluna).setCellValue(data);

    }

}
