/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
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
    
    private int Idconta;
    
    private Date dataemissao;
    
    private String statusdaConta;
    
    private String tipocontaNome;
    
    private double valorpago;
    
    private double valortotal;

    public int getIdconta() {
        return Idconta;
    }

    public void setIdconta(int Idconta) {
        this.Idconta = Idconta;
    }

    public Date getDataemissao() {
        return dataemissao;
    }

    public void setDataemissao(Date dataemissao) {
        this.dataemissao = dataemissao;
    }

    public String getStatusdaConta() {
        return statusdaConta;
    }

    public void setStatusdaConta(String statusdaConta) {
        this.statusdaConta = statusdaConta;
    }

    public String getTipocontaNome() {
        return tipocontaNome;
    }

    public void setTipocontaNome(String tipocontaNome) {
        this.tipocontaNome = tipocontaNome;
    }

    public double getValorpago() {
        return valorpago;
    }

    public void setValorpago(double valorpago) {
        this.valorpago = valorpago;
    }

    public double getValortotal() {
        return valortotal;
    }

    public void setValortotal(double valortotal) {
        this.valortotal = valortotal;
    }
    
    
    
    public void RelatorioFluxoCaixa(List<CarCapContas> conta) throws JRException{
        
       InputStream fonte = Relatorios.class.getResourceAsStream("teste2.jrxml");
       JasperReport report = JasperCompileManager.compileReport(fonte);
        LinkedList<Relatorios> contaRelatorio = new LinkedList<>();
       
      // ArrayList<Relatorios> contaRelatorio = new ArrayList();
        
       
       Relatorios contas;

       
        for(CarCapContas i : conta){

             contas = new Relatorios();
            
            contas.setIdconta(i.getContaId());
            contas.setStatusdaConta(i.getCapContaStatus().toString());
            contas.setDataemissao(i.getContaDataEmissao());
            contas.setTipocontaNome(i.getContaTipo().toString());
            contas.setValorpago(i.getContaValorPago());
            contas.setValortotal(i.getContaValorTotal());
            
            contaRelatorio.addFirst(contas);
          
        }
        
        
         JasperPrint print = JasperFillManager.fillReport(report,null, new JRBeanCollectionDataSource(contaRelatorio));
       
         JasperViewer.viewReport(print,false);
        
    }
    
}
