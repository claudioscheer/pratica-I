
package relatorios.contareceber;

import dao.CarPessoaDAO;
import dao.FlxcxOperacoesDAO;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CarCapContas;
import model.CarPessoa;
import model.FlxcxOperacoes;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import static org.slf4j.helpers.Util.report;

/**
 *
 * @author Juliano
 */
public class RelatoriosContasReceber {
     
    public void geraRelatorioLancamentos(List<CarCapContas> lancamentos, Date dataIni, Date dataFim) throws JRException {
        
            InputStream fonte = RelatoriosContasReceber.class.getResourceAsStream("RelatorioLancamento.jrxml");
          
            JasperReport report = JasperCompileManager.compileReport(fonte);
        
  
        FlxcxOperacoesDAO op = new FlxcxOperacoesDAO();
        FlxcxOperacoes nova ; 
 
        CarPessoaDAO pessoaDAO = new CarPessoaDAO();
        CarPessoa pessoa;
             
        for(CarCapContas i: lancamentos){
            
            nova = new FlxcxOperacoes();
            
            nova = op.Buscar(i.getFlxcxOperacoes().getOpCodigo());
          
            i.setTipoOperacaoDescricao(nova.getOpDescricao());
            
            i.setContaStatusDescricao(i.getContaStatus().toString());
            
            i.setContaTipoDescricao(i.getContaTipo().toString());
            
            pessoa = new CarPessoa();
            
            pessoa = pessoaDAO.ListarId(i.getCarPessoa().getPessoaId());
            
            i.setPessoaNome(pessoa.getPessoaNome());
        }
        
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("dataIni", dataIni);
        parametros.put("dataFim", dataFim);
        JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(lancamentos,false));
 
     
        JasperViewer.viewReport(print,false);
        
        
    }
    
}
