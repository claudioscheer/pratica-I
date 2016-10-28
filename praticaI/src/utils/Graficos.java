/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Color;
import java.util.List;
import model.CarCapContas;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Alisson Allebrandt
 */
public class Graficos {

    public enum TiposGrafico {

        linha,
        pizza,
        barras,
        cruzamento

    }

    public JFreeChart SelecionarGrafico(TiposGrafico tipo,List<CarCapContas> pagar, List<CarCapContas> receber, String tituloGrafico) {

        switch (tipo) {

            case linha:

                return GraficoLinhas(pagar, receber,tituloGrafico);
                
            case barras:
                
                return GraficoBarras(pagar, receber, tituloGrafico);
                
                
        }

        return null;
    }

    public static JFreeChart GraficoLinhas(List<CarCapContas> pagar, List<CarCapContas> receber ,String titulo) {

        XYSeries series1 = new XYSeries("Pagar");

//        CarCapContas contasPagar = new CarCapContas();
        for (CarCapContas contasPagar : pagar) {

            series1.add(contasPagar.getContaValorTotal(), contasPagar.getContaDataEmissao().getMonth());

        }
        
        XYSeries series2 = new XYSeries("Receber");
        
        for(CarCapContas contasReceber : receber){
            
            series2.add(contasReceber.getContaValorTotal(), contasReceber.getContaDataEmissao().getMonth());
            
        }
            

        XYSeriesCollection dataset = new XYSeriesCollection();

        dataset.addSeries(series1);
        dataset.addSeries(series2);

        JFreeChart chart = ChartFactory.createXYLineChart(
                titulo, // chart title
                "X", // x axis label
                "Y", // y axis label
                dataset, // data
                PlotOrientation.VERTICAL,
                true, // include legend
                true, // tooltips
                false // urls
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);

        // plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesShapesVisible(1, false);
        plot.setRenderer(renderer);

        // change the auto tick unit selection to integer units only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // OPTIONAL CUSTOMISATION COMPLETED.
        return chart;

    }
    
    public JFreeChart GraficoBarras(List<CarCapContas> pagar, List<CarCapContas> receber, String titulo){
        
        String [] meses = {"Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
        
         DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        int indice = 0; 
         
        for(CarCapContas i : pagar){
            
            dataset.addValue(i.getContaValorTotal(), "Contas a pagar" , meses[indice]);
            
            indice++;
        }
        
        indice = 0;
        
        for(CarCapContas i : receber){
            
            dataset.addValue(i.getContaValorTotal(), "Contas a receber", meses[indice]);
            
            indice++;
        }
        
         JFreeChart barChart = ChartFactory.createBarChart(
         titulo, 
         "Mês", "Valor total", 
         dataset,PlotOrientation.VERTICAL, 
         true, true, false);
         
         
         return barChart;
   }
        
        

}
