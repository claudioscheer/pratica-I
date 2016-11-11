package relatorios.patrimonio;

import dao.PatAtivoImobilizadoDAO;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PatAtivoImobilizado;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import relatorios.contareceber.RelatoriosContasReceber;

public class RelatoriosPatrimonio {

    public static class Relatorios {

        public static String RELATORIO_INVENTARIO = "src/relatorios/patrimonio/relatorio_inventario.jasper";
    }

    public static void relatorioInventario() throws JRException {

        List<PatAtivoImobilizado> ativos = new PatAtivoImobilizadoDAO().getParaRelatorio();
        JRBeanCollectionDataSource jrs = new JRBeanCollectionDataSource(ativos);

        Map parametros = new HashMap();
        parametros.put("teste", "asfdmgsdfkjghksjdhg");
        JasperPrint jpr = JasperFillManager.fillReport(Relatorios.RELATORIO_INVENTARIO, parametros, jrs);
        JasperViewer.viewReport(jpr, false);
    }

    public static void main(String[] args) {
        try {
            RelatoriosPatrimonio.relatorioInventario();
        } catch (JRException ex) {
            Logger.getLogger(RelatoriosPatrimonio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}