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
    
    private int Idconta;
    
    private Date dataemissao;
    
    private String statusdaConta;
    
    private String tipocontaNome;
    
    private double valorpago;
    
    private double valortotal;
    
    private String contaDescricao;
    
    
    
//    private Date dataInicial;
//    
//    private Date dataFinal;
//    
//    private double totalEntradas;
//    
//    private double totalSaidas;
//    

    public int getIdconta() {
        return Idconta;
    }



//    public Date getDataInicial() {
//        return dataInicial;
//    }
//
//    public void setDataInicial(Date dataInicial) {
//        this.dataInicial = dataInicial;
//    }
//
//    public Date getDataFinal() {
//        return dataFinal;
//    }
//
//    public void setDataFinal(Date dataFinal) {
//        this.dataFinal = dataFinal;
//    }

    public String getContaDescricao() {
        return contaDescricao;
    }

    public void setContaDescricao(String contaDescricao) {
        this.contaDescricao = contaDescricao;
    }

//    public double getTotalEntradas() {
//        return totalEntradas;
//    }
//
//    public void setTotalEntradas(double totalEntradas) {
//        this.totalEntradas = totalEntradas;
//    }
//
//    public double getTotalSaidas() {
//        return totalSaidas;
//    }
//
//    public void setTotalSaidas(double totalSaidas) {
//        this.totalSaidas = totalSaidas;
//    }

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
    
    
    
    public void RelatorioFluxoCaixa(List<CarCapContas> conta, Date dataIni, Date dataFim) throws JRException{
        
       InputStream fonte = Relatorios.class.getResourceAsStream("RelatorioFluxo.jrxml");
       JasperReport report = JasperCompileManager.compileReport(fonte);
       
       List<CarCapContas>contaRelatorio = new ArrayList<>();
       
//       Map record = null;
       Relatorios contas = null;
       TipoConta tipoConta;
       
        for(CarCapContas i : conta){
           
//            record = new HashMap(); 
            
//            record.put("contaDescricao", "Descricao teste");
//            record.put("dataemissao", i.getContaDataEmissao()); 
//            record.put("statusdaConta", i.getCapContaStatus().toString());
//            record.put("tipocontaNome", i.getContaTipo().toString());
//            record.put("valorpago", i.getContaValorPago());
//            record.put("valortotal", i.getContaValorTotal());
            
         
            i.setContaStatusDescricao(i.getCapContaStatus().toString());
            i.setContaTipoDescricao(i.getContaTipo().toString());
           
             
//            contar("dataemissao", i.getContaDataEmissao()); 
//            record.put("statusdaConta", i.getCapContaStatus().toString());
//            record.put("tipocontaNome", i.getContaTipo().toString());
//            record.put("valorpago", i.getContaValorPago());
//            record.put("valortotal", i.getContaValorTotal());
            
            contaRelatorio.add(i); 
            
            
            
//            //contas.setIdconta(i.getContaId());
//            contas.setStatusdaConta(i.getCapContaStatus().toString());
//            contas.setDataemissao(i.getContaDataEmissao());
//         
//            
////            tipoConta = i.getContaTipo();
////            valorPago = i.getContaValorPago();
//            contas.setTipocontaNome(i.getContaTipo().toString());
//            contas.setValorpago(i.getContaValorPago());
//            contas.setValortotal(i.getContaValorTotal());
//            contas.setContaDescricao("Descrição Teste");
//            
////            if(tipoConta.equals(TipoConta.Entrada)){
////               
////                totEntradas += valorPago; 
////                
////            }else if(tipoConta.equals(TipoConta.Saida)){
////                
////                totSaidas += valorPago;
////                
////            }           
            
        }
        
      
        JasperPrint print = JasperFillManager.fillReport(report,null, new JRBeanCollectionDataSource(contaRelatorio));
       
        JasperViewer.viewReport(print,false);
        
    }
    
}
