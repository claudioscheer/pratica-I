/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Alisson Allebrandt
 */
public class Relatorios {
    
    
    public void RelatorioFluxoCaixa(List<CarCapContas> conta) throws JRException{
        InputStream fonte = Relatorios.class.getResourceAsStream("teste2.jrxml");
        JasperReport report = JasperCompileManager.compileReport(fonte);
        

        for(CarCapContas i : conta){
            
            System.out.println("status"+i.getCapContaStatus());
            
        }
        
        
         JasperPrint print = JasperFillManager.fillReport(report,null, new JRBeanCollectionDataSource(conta));
       
        JasperViewer.viewReport(print,false);
        
    }
    
}
