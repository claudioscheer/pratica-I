
package relatorios.contareceber;

import dao.CarPessoaDAO;
import dao.FlxcxOperacoesDAO;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
import static org.slf4j.helpers.Util.report;

/**
 *
 * @author Juliano
 */
public class RelatoriosContasReceber {
     
    public void preenchedados(List<CarCapContas> lancamentos) throws JRException {
        
            InputStream fonte = RelatoriosContasReceber.class.getResourceAsStream("RelatorioLancamento.jrxml");
          
            JasperReport report = JasperCompileManager.compileReport(fonte);
        
  
        List<CarCapContas>contaRelatorio = new ArrayList<>();
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
            
            contaRelatorio.add(i);
            
        }
        
        JasperPrint print = JasperFillManager.fillReport(report,null, new JRBeanCollectionDataSource(contaRelatorio,false));
 
     
        JasperViewer.viewReport(print,false);
        
        
    }
}
