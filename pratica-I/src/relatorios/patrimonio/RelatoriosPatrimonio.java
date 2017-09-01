package relatorios.patrimonio;

import dao.PatAtivoImobilizadoDAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PatAtivoImobilizado;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import utils.Utils;

public class RelatoriosPatrimonio {

    public static class Relatorios {

        public static String RELATORIO_INVENTARIO = "src/relatorios/patrimonio/relatorio_inventario.jasper";
        public static String RELATORIO_BAIXAS = "src/relatorios/patrimonio/relatorio_inventario.jasper";
    }

    public static void relatorioInventario(String titulo) throws JRException {
        List<PatAtivoImobilizado> ativos = new PatAtivoImobilizadoDAO().buscarAtivosAtivosRelatorio();
        if (ativos.size() < 1) {
            utils.Utils.notificacao("Nenhum ativo encontrado!", Utils.TipoNotificacao.erro, 10000);
            return;
        }
        
        JRBeanCollectionDataSource jrs = new JRBeanCollectionDataSource(ativos);

        Map parametros = new HashMap();
        parametros.put("titulo", titulo);
        JasperPrint jpr = JasperFillManager.fillReport(Relatorios.RELATORIO_INVENTARIO, parametros, jrs);
        JasperViewer.viewReport(jpr, false);
    }

    public static void relatorioAtivoEmBaixa(String titulo) throws JRException {
        List<PatAtivoImobilizado> ativos = new PatAtivoImobilizadoDAO().buscarAtivoEmBaixaRelatorio();
        if (ativos.size() < 1) {
            utils.Utils.notificacao("Nenhum ativo em baixa encontrado!", Utils.TipoNotificacao.erro, 10000);
            return;
        }

        JRBeanCollectionDataSource jrs = new JRBeanCollectionDataSource(ativos);

        Map parametros = new HashMap();
        parametros.put("titulo", titulo);
        JasperPrint jpr = JasperFillManager.fillReport(Relatorios.RELATORIO_BAIXAS, parametros, jrs);
        JasperViewer.viewReport(jpr, false);
    }
}
