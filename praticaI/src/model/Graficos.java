/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static com.alee.managers.notification.NotificationOption.no;
import enumeraveis.TipoConta;
import enumeraveis.TipoGrafico;
import java.util.List;
import java.util.Locale;
import javax.swing.JPanel;
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

    public JFreeChart GraficoBarras(List<CarCapContas> contas, String titulo) {

        String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        int indice = 0;

        for (int j = 0; j < meses.length; j++) {

            int mes = j + 1;

            dataset.addValue(0, "Contas a pagar", meses[j]);
            dataset.addValue(0, "Contas a receber", meses[j]);

            for (CarCapContas i : contas) {

                if (i.getContaDataEmissao().getMonth() == j) {

                    if (i.getContaTipo().equals(TipoConta.Saida)) {

                        dataset.addValue(i.getContaValorPago(), "Contas a pagar", meses[j]);

                    } else if (i.getContaTipo().equals(TipoConta.Entrada)) {

                        dataset.addValue(i.getContaValorTotal(), "Contas a receber", meses[j]);
                    }

                } 

            }
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                titulo,
                "Mês", "Valor total",
                dataset, PlotOrientation.VERTICAL,
                true, true, false);

        return barChart;
    }

    public JFreeChart GraficoPizza(String titulo, List<CarCapContas> contas) {

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
    
}
