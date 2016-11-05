
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
public class Relatorios {
     
    public void preenchedados(List<CarCapContas> lancamentos) {
        
         InputStream fonte = relatorios.relatorioFluxoDeCaixa.Relatorios.class.getResourceAsStream("RelatorioLancamento.jrxml");
       
            JasperReport report = null;
        try {
            report = JasperCompileManager.compileReport(fonte);
        } catch (JRException ex) {
            Logger.getLogger(Relatorios.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        List<CarCapContas>contaRelatorio = new ArrayList<>();
        FlxcxOperacoesDAO op = new FlxcxOperacoesDAO();
        FlxcxOperacoes nova = new FlxcxOperacoes();
        
 
        CarPessoaDAO pessoaDAO = new CarPessoaDAO();
        CarPessoa pessoa = new CarPessoa();
                
        for(CarCapContas i: lancamentos){
            
            nova = op.Buscar(i.getFlxcxOperacoes().getOpCodigo());
            i.setTipoOperacaoDescricao(nova.getOpDescricao());
            
            i.setContaStatusDescricao(i.getContaStatus().toString());
            
            i.setContaTipoDescricao(i.getContaTipo().toString());
            
            pessoa = pessoaDAO.ListarId(i.getCarPessoa().getPessoaId());
            
            i.setPessoaNome(pessoa.getPessoaNome());
            
            contaRelatorio.add(i);
            
        }
        
        JasperPrint print = null;
        try {
            print = JasperFillManager.fillReport(report,null, new JRBeanCollectionDataSource(contaRelatorio,false));
        } catch (JRException ex) {
            Logger.getLogger(Relatorios.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        JasperViewer.viewReport(print,false);
        
        
    }
}
