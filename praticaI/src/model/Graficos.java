/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import enumeraveis.TipoConta;
import enumeraveis.TipoGrafico;
import java.util.List;
import java.util.Locale;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.editor.ChartEditorFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Alisson Allebrandt
 */
public final class Graficos {

    JFreeChart grafico;
    XYSeriesCollection dados = new XYSeriesCollection();

    String titulo;
    String xx = "entradas";
    String xy = "saídas";

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

            case barras:

                grafico = ChartFactory.createScatterPlot(titulo, xx, xy, dados, PlotOrientation.VERTICAL, true, true, true);

                break;

            case pizza:

                grafico = ChartFactory.createXYStepChart(titulo, xx, xy, dados);

                break;

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

                    System.out.println("entrou no iff");
                    
                    s.add(i.getContaValorTotal(), i.getContaValorPago());

                } else if (i.getContaTipo().equals(TipoConta.Entrada)) {

                    s2.add(i.getContaValorTotal(), i.getContaValorPago());

                }
            }
            
            dados.addSeries(s);

            dados.addSeries(s2);

            return new ChartPanel(grafico);


    }
}
