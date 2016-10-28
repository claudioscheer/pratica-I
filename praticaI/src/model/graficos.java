/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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



public final class graficos {
    
   
    
    JFreeChart grafico;
    XYSeriesCollection dados = new XYSeriesCollection();
    
    public final static int linear = 1;
    public final static int barras = 2;
    public final static int polar = 3;
    public final static int pizza = 4;
    public final static int area = 5;
    String titulo;
    String xx = "entradas";
    String xy = "saídas";
    
    public graficos(int tipo, String titulo) {
       
    this.titulo = titulo;    
    this.tipoGrafico(tipo);
    
    }
    
   
    
    public void tipoGrafico(int tipo){
        
        switch(tipo){
            
            case linear:       
                
                grafico = ChartFactory.createXYLineChart(titulo, xx, xy, dados, PlotOrientation.VERTICAL, true, true, true);
                
                break;
               
            case polar:
                
                grafico = ChartFactory.createPolarChart(titulo, dados, true, true, true);
                
                break;
                
            case barras:
                
                grafico = ChartFactory.createScatterPlot(titulo, xx, xy, dados, PlotOrientation.VERTICAL, true, true , true);
                
                break;
                
            case pizza:
                
               grafico = ChartFactory.createXYStepChart(titulo, xx, xy, dados);
                
                break;
                
            case area:
                
                grafico = ChartFactory.createXYStepAreaChart(titulo, xx, xy, dados);
                
                
                break; 
            
        }
        
    }
    
      public ChartPanel informarDadosGrafico(String nome, List<CarCapContas> pagar, List<CarCapContas> receber){
            
            
          XYSeries s = new XYSeries(xx);
          XYSeries s2 = new XYSeries(xy);
          int n  = pagar.size();
          
          for(CarCapContas i : pagar){
              
              s.add(i.getContaValorTotal(),i.getContaValorPago());
              
          }
           dados.addSeries(s);
           
           for(CarCapContas i : receber){
              
               s2.add(i.getContaValorTotal(),i.getContaValorPago());
               
          }
           
           dados.addSeries(s2);
           
          return new ChartPanel(grafico);
          
      }
           
           
        }
    
        
     
 
