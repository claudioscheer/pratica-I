/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.alee.laf.WebLookAndFeel;
import com.alee.laf.desktoppane.WebInternalFrame;
import com.alee.laf.optionpane.WebOptionPane;
import com.alee.laf.table.WebTable;
import com.alee.managers.language.LanguageManager;
import com.alee.managers.notification.NotificationManager;
import com.alee.managers.notification.WebNotification;
import components.panelsListagem.PanelConsultaAtivoImobilizado;
import dao.CarCapContasDAO;
import dao.ExportacaoParaExcel;
import enumeraveis.TipoConta;
import enumeraveis.TipoGrafico;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import model.CarCapContas;
import dao.Graficos;
import dao.PatAtivoImobilizadoDAO;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.SwingWorker;
import model.PatAtivoImobilizado;
import relatorios.relatorioFluxoDeCaixa.Relatorios;
import net.sf.jasperreports.engine.JRException;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import utils.Utils;

/**
 *
 * @author Alisson Ap
 */
public class FormFluxodeCaixa1 extends JFrame {

    /**
     * Creates new form FormFluxodeCaixa
     */
    TipoGrafico tipo;
    String titulo;
    Date dataInicial;
    Date dataFinal;
    int paginaBuscar = 0;

    private final CarCapContasDAO buscarconta = new CarCapContasDAO();
    private List<CarCapContas> contas;

    public FormFluxodeCaixa1() {

        // estancia o array de contas
        this.contas = new ArrayList<>();

        this.initComponents();

        //--ajustes data inicial --\\
        Calendar c = Calendar.getInstance();
        dataFinal = c.getTime();

        c.add(Calendar.DATE, -1);
        dataInicial = c.getTime();
 
        tipo = TipoGrafico.barras;
        titulo = "Grafico de Barras";
        // - Final ajuste data inicial -\\

//        verificaTipoGrafico(TipoConta.ambos, 1);
//        grapBarras.setSelected(true);
//        grapBarras.setIcon(Utils.getImage(Utils.Image.barraMarcado));
//
//        // Marca as CheckBox \\
//        checkbox_Grafico.setSelected(true);
//        checkbox_Lista.setSelected(true);
//        checkboxEntrada.setSelected(true);
//        checkboxSaida.setSelected(true);
        
         DefaultTableModel modelTabela = new DefaultTableModel(new Object[]{"Id", "Status", "Data", "Tipo", "Valor Total", "Valor Pago"}, 0);
        
        this.webPanel_Tabela.setModel(modelTabela);
        this.webPanel_Tabela.setSortable(true);
        this.webPanel_Tabela.setLoadMore(x -> new FormFluxodeCaixa1.CarregarContas().execute());

        new CarregarContas().execute();

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        webSplitPane1 = new com.alee.laf.splitpane.WebSplitPane();
        webPanel_Tabela = new components.JTableLoadScroll();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        webSplitPane1.setRightComponent(webPanel_Tabela);

        getContentPane().add(webSplitPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 730, 430));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public class CarregarContas extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() throws Exception {

            //contas = new ArrayList<CarCapContas>();
            
            CarCapContasDAO buscar = new CarCapContasDAO();

            contas.addAll(buscar.ListarTodosPaginacao(paginaBuscar++, dataInicial, dataFinal));
      
           // CarregarGraficoJTable(null, null, 3, TipoConta.ambos, dataInicial, dataFinal);
            
            atualizarTabela();
            
            
            return null;
        }

        @Override
        protected void done() {
            super.done(); //To change body of generated methods, choose Tools | Templates.
        }

    }
    
        private void atualizarTabela() {
        DefaultTableModel model = (DefaultTableModel) this.webPanel_Tabela.getModel();
        Utils.clearTableModel(model);

        this.contas.forEach(x -> {
            model.addRow(ativoToArray(x));
        });

        this.webPanel_Tabela.setModel(model);
    }

    private Object[] ativoToArray(CarCapContas conta) {
      
        Object[] o = new Object[6];
        o[0] = conta.getContaId();
        o[1] = conta.getContaStatus();
        o[2] = conta.getContaDataEmissao();
        o[3] = conta.getContaTipo();
        o[4] = conta.getContaValorTotal();
        o[5] = conta.getContaValorPago();
        
        return o;
    }
    

    public List<CarCapContas> CarregarGraficoJTable(String nome, TipoGrafico tipografico, int posicao, TipoConta tipoconta, Date DataInicial, Date DataFinal) {

        //Calendar c2 = Calendar.getInstance();
        // posicao = 1 (esquerda), posicao = 2 (direita), posicao 3 = JTable direita
        if (posicao == 1) {

            if (tipografico.equals(TipoGrafico.barras) || tipografico.equals(TipoGrafico.pizza)) {

                Graficos grap = new Graficos(tipo, this.titulo);
                JFreeChart j = null;

                if (tipografico.equals(TipoGrafico.barras)) {

                    j = grap.GraficoBarras(contas, "Entradas X Saidas");

                } else if (tipografico.equals(TipoGrafico.pizza)) {

                    j = grap.GraficoPizza("Gráfico de Pizza", contas);

                }

                ChartPanel chart = new ChartPanel(j);

//                WebPanelGrafico.removeAll();
//
//                webPanel_Split.validate();
//
//                int largura = WebPanelGrafico.getWidth();
//                int altura = WebPanelGrafico.getHeight();
//
//                WebPanelGrafico.setBounds(0, 0, largura, altura);
//
//                chart.setBounds(0, 0, largura, altura);
//                chart.addPropertyChangeListener(null);
//
//                WebPanelGrafico.add(chart);
//
//                WebPanelGrafico.revalidate();
//                WebPanelGrafico.repaint();

            } else {

                Graficos grap = new Graficos(tipo, this.titulo);

                ChartPanel c = grap.informarDadosGrafico("grafico", contas);
//
//                WebPanelGrafico.removeAll();
//
//                webPanel_Split.validate();
//
//                int largura = WebPanelGrafico.getWidth();
//                int altura = WebPanelGrafico.getHeight();
//
//                WebPanelGrafico.setBounds(0, 0, largura, altura);
//
//                c.setBounds(0, 0, largura, altura);
//                c.addPropertyChangeListener(null);
//
//                WebPanelGrafico.add(c);
//
//                WebPanelGrafico.revalidate();
//                WebPanelGrafico.repaint();

            }

        } else if (posicao == 2) {

            if (tipografico.equals(TipoGrafico.barras) || tipografico.equals(TipoGrafico.pizza)) {

                Graficos grap = new Graficos(tipo, this.titulo);
                JFreeChart j = null;

                if (tipografico.equals(TipoGrafico.barras)) {

                    j = grap.GraficoBarras(contas, "Entradas X Saidas");

                } else if (tipografico.equals(TipoGrafico.pizza)) {

                    j = grap.GraficoPizza("Gráfico de Pizza", contas);

                }

//                ChartPanel chart = new ChartPanel(j);
//
//                webPanel_Tabela.removeAll();
//
//                webPanel_Split.validate();
//
//                int largura = webPanel_Tabela.getWidth();
//                int altura = webPanel_Tabela.getHeight();
//
//                webPanel_Tabela.setBounds(0, 0, largura, altura);
//
//                chart.setBounds(0, 0, largura, altura);
//
//                webPanel_Tabela.add(chart);
//
//                webPanel_Tabela.revalidate();
//                webPanel_Tabela.repaint();

            } else {

//                Graficos grap = new Graficos(tipo, this.titulo);
//
//                ChartPanel c = grap.informarDadosGrafico("grafico", contas);
//
//                webPanel_Tabela.removeAll();
//
//                webPanel_Split.validate();
//
//                int largura = webPanel_Tabela.getWidth();
//                int altura = webPanel_Tabela.getHeight();
//
//                webPanel_Tabela.setBounds(0, 0, largura, altura);
//
//                c.setBounds(0, 0, largura, altura);
//
//                webPanel_Tabela.add(c);
//
//                webPanel_Tabela.revalidate();
//                webPanel_Tabela.repaint();

            }

        } else if (posicao == 3) {

            double totalEntradas = 0;
            double totalSaidas = 0;
            double totalDisponivel = 0;

            WebTable tablenovo = new WebTable();

            DefaultTableModel modelTabela = new DefaultTableModel(new Object[]{"Id", "Status", "Data", "Tipo", "Valor Total", "Valor Pago"}, 0);

            for (CarCapContas i : contas) {

                Object[] data = {i.getContaId(), i.getCapContaStatus(), i.getContaDataEmissao(), i.getContaTipo(), i.getContaValorTotal(), i.getContaValorPago()};

                modelTabela.addRow(data);

                if (i.getContaTipo().equals(TipoConta.Entrada)) {

                    totalEntradas += i.getContaValorPago();

                } else if (i.getContaTipo().equals(TipoConta.Saida)) {

                    totalSaidas += i.getContaValorPago();

                }
            }

//            txtTotalEntradas.setText(String.valueOf(Utils.format(totalEntradas)));
//            txtTotalSaidas.setText(String.valueOf(Utils.format(totalSaidas)));
//
//            tablenovo.setModel(modelTabela);
//
//            JScrollPane tableContainer = new JScrollPane(tablenovo);
//
//            webPanel_Tabela.removeAll();
//
//            webPanel_Split.validate();
//
//            int largura = webPanel_Tabela.getWidth();
//            int altura = webPanel_Tabela.getHeight();
//
//            webPanel_Tabela.setBounds(0, 0, largura, altura);
//
//            tableContainer.setBounds(0, 0, largura, altura);
//
//            webPanel_Tabela.add(tableContainer);
//
//            webPanel_Tabela.revalidate();
//            webPanel_Tabela.repaint();
//            
       

        } else if (posicao == 4) {

            return contas;

        }

        return null;
    }


    public void verificaSpliPanel() {

//        if (checkbox_Grafico.isSelected()) {
//
//            if (checkbox_Lista.isSelected()) {
//
//                webPanel_Split.setDividerLocation(.5f);
//
//            } else {
//
//                if (!grapPizza.isSelected() && !grapBarras.isSelected() && !GrapLinhas.isSelected()) {
//                    webPanel_Split.setDividerLocation(1210);
//                } else {
//
//                    // if(grapPizza.isSelected() || grapBarras.isSelected() || GrapLinhas.isSelected())
//                    checkbox_Lista.setSelected(true);
//                    webPanel_Split.setDividerLocation(.5f);
//                }
//
//            }
//
//        } else {
//
//            if (checkbox_Lista.isSelected()) {
//
//                webPanel_Split.setDividerLocation(1);
//
//            } else {
////                if(!checkbox_Lista.getActionCommand().equals("Lista")) {
//
//                webPanel_Split.setDividerLocation(1);
//                checkbox_Lista.setSelected(true);
//
//                CarregarNotificacao("Filtro por Lista aplicado automaticamente!");
//
//            }
//
//        }
//
//    }
//
//    public void CarregarNotificacao(String msg) {
//
//        WebNotification notificationPopup = new WebNotification();
//        notificationPopup.setDisplayTime(5000);
//
//        notificationPopup.setContent(msg);
//
//        NotificationManager.showNotification(notificationPopup);
//
//    }
//
//    public boolean MensagensConfirmacao(String msg, String tituloPainel) {
//
//        int resposta = WebOptionPane.showConfirmDialog(null, msg, tituloPainel, WebOptionPane.YES_NO_OPTION,
//                WebOptionPane.QUESTION_MESSAGE);
//
//        if (resposta == WebOptionPane.YES_OPTION) {
//
//            return true;
//
//        } else {
//
//            return false;
//
//        }
//
//    }
//
//    public int verificaMarcados() {
//
//        int conta = 0;
//
//        if (GrapLinhas.isSelected()) {
//
//            conta++;
//
//        }
//
//        if (grapBarras.isSelected()) {
//
//            conta++;
//
//        }
//
//        if (grapPizza.isSelected()) {
//
//            conta++;
//        }
//
//        return conta;
//
//    }
//
//    public TipoConta verificaTipodeConta() {
//
//        if (checkboxEntrada.isSelected() && checkboxSaida.isSelected()) {
//
//            verificaTipoGrafico(TipoConta.ambos, 0);
//
//            return TipoConta.ambos;
//
//        } else if (!checkboxEntrada.isSelected() && checkboxSaida.isSelected()) {
//
//            verificaTipoGrafico(TipoConta.Saida, 0);
//
//            return TipoConta.Saida;
//
//        } else if (checkboxEntrada.isSelected() && !checkboxSaida.isSelected()) {
//
//            verificaTipoGrafico(TipoConta.Entrada, 0);
//
//            return TipoConta.Entrada;
//
//        }
//
//        return null;
//    }
    }
   

    public void Excel() {

        JFileChooser fileChooser = new JFileChooser();

        if (fileChooser.showSaveDialog(fileChooser.getComponent(0)) == JFileChooser.APPROVE_OPTION) { //Exibe janela onde salvar o arquivo HTML

            File file = fileChooser.getSelectedFile(); //Pega o caminho completo

            String caminho;
            if (file.getPath().contains(".xls")) {

                caminho = file.getPath();

            } else {
                caminho = file.getPath() + ".xls";

            }

          //  new ExportacaoParaExcel().Exportar(caminho, dataInicial, dataFinal, comboFiltroData.getSelectedIndex());

        }

    }



    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {

            WebLookAndFeel.setDecorateAllWindows(true);
            WebLookAndFeel.setDecorateDialogs(true);
            WebLookAndFeel.setDecorateFrames(true);

            LanguageManager.setDefaultLanguage(LanguageManager.PORTUGUESE);

            WebLookAndFeel.install();

            FormFluxodeCaixa1 fluxo = new FormFluxodeCaixa1();
            fluxo.setVisible(true);

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.JTableLoadScroll webPanel_Tabela;
    private com.alee.laf.splitpane.WebSplitPane webSplitPane1;
    // End of variables declaration//GEN-END:variables

    private FormAddSaldoInicial formAddSaldo;

}
