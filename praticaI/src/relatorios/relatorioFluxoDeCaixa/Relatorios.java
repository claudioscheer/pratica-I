/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios.relatorioFluxoDeCaixa;

import enumeraveis.StatusConta;
import enumeraveis.TipoConta;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import model.CarCapContas;
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
    
    public void RelatorioFluxoCaixa(List<CarCapContas> conta, Date dataIni, Date dataFim) throws JRException{
        
       InputStream fonte = Relatorios.class.getResourceAsStream("RelatorioFluxo.jrxml");
       JasperReport report = JasperCompileManager.compileReport(fonte);
       
       List<CarCapContas>contaRelatorio = new ArrayList<>();
      
       
        for(CarCapContas i : conta){
           
            i.setContaStatusDescricao(i.getCapContaStatus().toString());
            i.setContaTipoDescricao(i.getContaTipo().toString());
           
      
            contaRelatorio.add(i); 
            
        }
        
        JasperPrint print = JasperFillManager.fillReport(report,null, new JRBeanCollectionDataSource(contaRelatorio,false));
       
        JasperViewer.viewReport(print,false);
        
        }
    
}
