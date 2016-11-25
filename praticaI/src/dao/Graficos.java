/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static com.alee.managers.notification.NotificationOption.no;
import enumeraveis.FiltroData;
import enumeraveis.TipoConta;
import enumeraveis.TipoGrafico;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import javax.swing.JPanel;
import model.CarCapContas;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.editor.ChartEditorFactory;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.Rotation;
import utils.Utils;

/**
 *
 * @author Alisson Allebrandt
 */
public final class Graficos {

    JFreeChart grafico;
    XYSeriesCollection dados = new XYSeriesCollection();

    String titulo;
    String xy = "entradas";
    String xx = "saídas";

    public Graficos(TipoGrafico tipo, String titulo) {

        this.titulo = titulo;
        this.tipoGrafico(tipo);

    }

    public Graficos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void tipoGrafico(TipoGrafico tipo) {

        switch (tipo) {

            case linear:

                grafico = ChartFactory.createXYLineChart(titulo, xx, xy, dados, PlotOrientation.VERTICAL, true, true, true);

                break;

            case polar:

                grafico = ChartFactory.createPolarChart(titulo, dados, true, true, true);

                break;

//            case barras:
//
//      
//
//                break;
//            case pizza:
//
//                grafico = ChartFactory.createXYStepChart(titulo, xx, xy, dados);
//
//                break;
            case area:

                grafico = ChartFactory.createXYStepAreaChart(titulo, xx, xy, dados);

                break;

        }

    }

    public ChartPanel informarDadosGrafico(String nome, List<CarCapContas> contas) {

        XYSeries s = new XYSeries(xx);
        XYSeries s2 = new XYSeries(xy);

        for (CarCapContas i : contas) {

            if (i.getContaTipo().equals(TipoConta.Saida)) {

//                    System.out.println("entrou no iff");
                s.add(i.getContaValorTotal(), i.getContaValorPago());

            } else if (i.getContaTipo().equals(TipoConta.Entrada)) {

                s2.add(i.getContaValorTotal(), i.getContaValorPago());

            }
        }

        dados.addSeries(s);

        dados.addSeries(s2);

        return new ChartPanel(grafico);

    }

    public JFreeChart GraficoBarras(List<CarCapContas> contas, String titulo, FiltroData filtro, String estilo) {

        ArrayList<String> tipoFiltragem = new ArrayList();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        int tipo = 0;
        // 1 - diario // 2 - mensal// 3 - anual

        switch (filtro) {

            case Diario:

                if (estilo.equals("full")) {

                    tipoFiltragem.add("Domingo");
                    tipoFiltragem.add("Segunda");
                    tipoFiltragem.add("Terça");
                    tipoFiltragem.add("Quarta");
                    tipoFiltragem.add("Quinta");
                    tipoFiltragem.add("Sexta");
                    tipoFiltragem.add("Sábado");

                } else if (estilo.equals("small")) {

                    tipoFiltragem.add("Dom");
                    tipoFiltragem.add("Seg");
                    tipoFiltragem.add("Ter");
                    tipoFiltragem.add("Qua");
                    tipoFiltragem.add("Qui");
                    tipoFiltragem.add("Sex");
                    tipoFiltragem.add("Sáb");

                }

                tipo = 1;

                Date primeiroDiaSemana = dia_inicial_final_semana(true);
                Date ultimoDiaSemana = dia_inicial_final_semana(false);
                
                contas = new CarCapContasDAO().ListarTodos(primeiroDiaSemana, ultimoDiaSemana);
                
                dataset = montarDataSet(tipo, tipoFiltragem, contas, primeiroDiaSemana, ultimoDiaSemana);

                break;

            case Mensal:

                if (estilo.equals("full")) {

                    tipoFiltragem.add("Janeiro");
                    tipoFiltragem.add("Fevereiro");
                    tipoFiltragem.add("Março");
                    tipoFiltragem.add("Abril");
                    tipoFiltragem.add("Maio");
                    tipoFiltragem.add("Junho");
                    tipoFiltragem.add("Julho");
                    tipoFiltragem.add("Agosto");
                    tipoFiltragem.add("Setembro");
                    tipoFiltragem.add("Outubro");
                    tipoFiltragem.add("Novembro");
                    tipoFiltragem.add("Dezembro");

                } else if (estilo.equals("small")) {

                    tipoFiltragem.add("Jan");
                    tipoFiltragem.add("Fev");
                    tipoFiltragem.add("Mar");
                    tipoFiltragem.add("Abr");
                    tipoFiltragem.add("Mai");
                    tipoFiltragem.add("Jun");
                    tipoFiltragem.add("Jul");
                    tipoFiltragem.add("Ago");
                    tipoFiltragem.add("Set");
                    tipoFiltragem.add("Out");
                    tipoFiltragem.add("Nov");
                    tipoFiltragem.add("Dez");

                }

                tipo = 2;
                
            

                dataset = montarDataSet(tipo, tipoFiltragem, contas, null, null);

                break;

            case Anual:

                Calendar c = Calendar.getInstance();

                //seta ano atual
                tipoFiltragem.add(String.valueOf(c.get(Calendar.YEAR)));

                // seta próximos 5 anos
                for (int i = 1; i < 6; i++) {

                    c.add(Calendar.YEAR, 1);

                    tipoFiltragem.add(String.valueOf(c.get(Calendar.YEAR)));

                }

                tipo = 3;
                break;
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                titulo,
                "Mês", "Valor total",
                dataset, PlotOrientation.VERTICAL,
                true, true, false);

        return barChart;

    }

    public JFreeChart GraficoPizza(String titulo, List<CarCapContas> contas, FiltroData filtro) {

        DefaultPieDataset data = new DefaultPieDataset();

        for (CarCapContas i : contas) {

            if (i.getContaTipo().equals(TipoConta.Entrada)) {

                data.setValue("" + TipoConta.Entrada.toString(),
                        i.getContaValorPago());

            } else if (i.getContaTipo().equals(TipoConta.Saida)) {

                data.setValue("" + TipoConta.Saida.toString(),
                        i.getContaValorPago());

            }
        }

        JFreeChart chart = ChartFactory.createPieChart3D(titulo,
                data, true, true, true);

        return chart;
    }

    public DefaultCategoryDataset montarDataSet(int tipo, ArrayList<String> tipoFiltragem, List<CarCapContas> contas, Date dataInicial, Date dataFinal) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        int indice = 0;

        switch (tipo) {

            case 1:
//
//            String dataInicialSetada = Utils.formatData(dataInicial);
//            String dataFinalsetada = Utils.formatData(dataFinal);
//
//            int intervaloDatas = new utils.Utils().diasEntreDatas(dataInicialSetada, dataFinalsetada);

                double valorEntrada = 0;
                double valorSaida = 0;

                int controle = 0;
                Date dataAtualdoLoop;
                Calendar c = Calendar.getInstance();

                c.setTime(dataInicial);

                for (int j = 0; j < tipoFiltragem.size(); j++) {

                    dataset.addValue(0, "Contas a pagar", tipoFiltragem.get(j));
                    dataset.addValue(0, "Contas a receber", tipoFiltragem.get(j));

                    dataAtualdoLoop = c.getTime();

                    for (CarCapContas i : contas) {

                        System.out.println("data 1:" + Utils.formatData(dataAtualdoLoop) + "data2: " + Utils.formatData(i.getContaDataEmissao()));

                        if (Utils.formatData(dataAtualdoLoop).equals(Utils.formatData(i.getContaDataEmissao()))) {

                            if (i.getContaTipo().equals(TipoConta.Entrada)) {

                                valorEntrada += i.getContaValorPago();

                            } else {

                                valorSaida += i.getContaValorPago();

                            }

                        }else{
                            
                            controle +=1;
                            
                        }
                   
                    }

                    dataset.addValue(valorEntrada, "Contas a receber", tipoFiltragem.get(j));
                    dataset.addValue(valorSaida, "Contas a pagar", tipoFiltragem.get(j));
                    c.add(Calendar.DATE, +1);

                    valorEntrada = 0;
                    valorSaida = 0;

                }

                break;

            case 2:

                for (int j = 0; j < tipoFiltragem.size(); j++) {

                    dataset.addValue(0, "Contas a pagar", tipoFiltragem.get(j));
                    dataset.addValue(0, "Contas a receber", tipoFiltragem.get(j));

                    for (CarCapContas i : contas) {

                        if (i.getContaDataEmissao().getMonth() == j) {

                            if (i.getContaTipo().equals(TipoConta.Saida)) {

                                dataset.addValue(i.getContaValorPago(), "Contas a pagar", tipoFiltragem.get(j));

                            } else if (i.getContaTipo().equals(TipoConta.Entrada)) {

                                dataset.addValue(i.getContaValorTotal(), "Contas a receber", tipoFiltragem.get(j));
                            }

                        }
                    }

                }

                break;

            case 3:

                break;

        }

        return dataset;
    }
    
     public static Date dia_inicial_final_semana(boolean isPrimeiro) {
        //Seta a data atual.
        Date dataAtual= new Date();
        GregorianCalendar calendar = new GregorianCalendar();
        //Define que a semana começa no domingo.
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        //Define a data atual.
        calendar.setTime(dataAtual);
        if (isPrimeiro) {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        } else {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        }
        return calendar.getTime();
    }

}
