package components.panelsListagem;

import com.alee.laf.panel.WebPanel;
import components.JFrameBusca;
import components.Validador;
import dao.CarCapContasDAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import model.CarCapContas;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import utils.Utils;

public class FormRelatorioContasPagar extends JFrameBusca {

    private LoadCategorias loadCategorias;
    private List<CarCapContas> contas;
    public Validador valid;

    public FormRelatorioContasPagar() {
        initComponents();
        this.buscarCategorias();
     
    }

    public void init() {
        this.valid = new Validador(Validador.TipoValidator.ICONE);
        this.valid.addObrigatorioValidator(fieldCpfCnpj);
    }

    public void buscarCategorias() {
        this.loadCategorias = new LoadCategorias();
        this.loadCategorias.execute();
    }

    public class LoadCategorias extends SwingWorker<Void, Void> {

        protected Void doInBackground() throws Exception {
            DefaultTableModel model = (DefaultTableModel) tablePessoa.getModel();
            contas = new CarCapContasDAO().getAll();
            for (CarCapContas contas : contas) {
                Object[] o = new Object[3];
                o[0] = contas.getDataVencimento();
                o[1] = contas.getValorPendente();
                o[2] = contas.getCarPessoa();
                model.addRow(o);
            }
            tablePessoa.setModel(model);
            return null;
        }

        public void done() {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelOpcoes = new javax.swing.JPanel();
        buttonRelatorio = new com.alee.laf.button.WebButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablePessoa = new javax.swing.JTable();
        labelTipo = new javax.swing.JLabel();
        radioCpfCnpj = new com.alee.laf.radiobutton.WebRadioButton();
        fieldCpfCnpj = new javax.swing.JTextField();
        webButton1 = new com.alee.laf.button.WebButton();
        fieldDataInicial = new com.alee.extended.date.WebDateField();
        txtInfo = new javax.swing.JLabel();
        radioData = new com.alee.laf.radiobutton.WebRadioButton();
        fieldDataFinal = new com.alee.extended.date.WebDateField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        panelOpcoes.setBackground(new java.awt.Color(255, 255, 255));

        buttonRelatorio.setText("Gerar Relatório");
        buttonRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRelatorioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOpcoesLayout = new javax.swing.GroupLayout(panelOpcoes);
        panelOpcoes.setLayout(panelOpcoesLayout);
        panelOpcoesLayout.setHorizontalGroup(
            panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOpcoesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelOpcoesLayout.setVerticalGroup(
            panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonRelatorio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        tablePessoa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vencimento", "Valor", "Fornecedor"
            }
        ));
        jScrollPane2.setViewportView(tablePessoa);

        labelTipo.setText("Tipo de busca:");

        buttonGroup1.add(radioCpfCnpj);
        radioCpfCnpj.setText("CPF / CNPJ");
        radioCpfCnpj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioCpfCnpjActionPerformed(evt);
            }
        });

        fieldCpfCnpj.setEnabled(false);

        webButton1.setText("Filtrar");
        webButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webButton1ActionPerformed(evt);
            }
        });

        fieldDataInicial.setToolTipText("Informe a data de pagamento da primeira parcela");

        txtInfo.setText("Filtre os resultados para gerar um relatório personalizado, ou selecione \"Gerar Relatórios\" para um relatório de todas as pessoas.");

        buttonGroup1.add(radioData);
        radioData.setText("Período");
        radioData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioDataActionPerformed(evt);
            }
        });

        fieldDataFinal.setToolTipText("Informe a data de pagamento da primeira parcela");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelOpcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
                    .addComponent(txtInfo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(labelTipo)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(radioCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fieldCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(radioData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fieldDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)
                                .addComponent(fieldDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(webButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTipo)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(radioCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fieldCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(radioData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fieldDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fieldDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(webButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(txtInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(panelOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing

    private void buttonRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRelatorioActionPerformed
        // List<CarPessoa> listPessoas = new PessoaDAO().getAll(0, "");
        JRBeanCollectionDataSource jrs = new JRBeanCollectionDataSource(contas);

        Map parametros = new HashMap();
        try {
            JasperPrint jpr = JasperFillManager
                    .fillReport("src/relatorios/listacontas.jasper",
                            parametros,
                            jrs);
            JasperViewer.viewReport(jpr, false);
        } catch (JRException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_buttonRelatorioActionPerformed

    private void radioCpfCnpjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioCpfCnpjActionPerformed
        ajustaCampos();
    }//GEN-LAST:event_radioCpfCnpjActionPerformed

    private void webButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webButton1ActionPerformed
        this.busca();
        // TODO add your handling code here:
    }//GEN-LAST:event_webButton1ActionPerformed

    private void radioDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioDataActionPerformed

    private void busca() {
        String text = "";
        int indexfiltro = -1;
        this.contas.clear();
        if (radioCpfCnpj.isSelected()) {
            indexfiltro = 2;
            text = fieldCpfCnpj.getText();
            contas = new CarCapContasDAO().getAll(indexfiltro, text);
        } else if (radioData.isSelected()) {
            contas = new CarCapContasDAO().ListarLancamentosFuturos(fieldDataInicial.getDate(), fieldDataFinal.getDate());
        } 
        Utils.clearTableModel((DefaultTableModel) tablePessoa.getModel());
        

        DefaultTableModel model = (DefaultTableModel) tablePessoa.getModel();
        for (CarCapContas contas : contas) {
                Object[] o = new Object[3];
                o[0] = contas.getDataVencimento();
                o[1] = contas.getValorPendente();
                o[2] = contas.getCarPessoa();
                model.addRow(o);
            }
        tablePessoa.setModel(model);
    }

    private void ajustaCampos() {
        if (radioCpfCnpj.isSelected()) {
            fieldCpfCnpj.setEnabled(true);
            fieldCpfCnpj.requestFocus();
            fieldDataFinal.setEnabled(false);
            fieldDataInicial.setEnabled(false);
        } else if (radioData.isSelected()) {
            fieldCpfCnpj.setEnabled(false);  
            fieldDataInicial.requestFocus();
        } 
        fieldCpfCnpj.setText("");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private com.alee.laf.button.WebButton buttonRelatorio;
    private javax.swing.JTextField fieldCpfCnpj;
    private com.alee.extended.date.WebDateField fieldDataFinal;
    private com.alee.extended.date.WebDateField fieldDataInicial;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelTipo;
    private javax.swing.JPanel panelOpcoes;
    private com.alee.laf.radiobutton.WebRadioButton radioCpfCnpj;
    private com.alee.laf.radiobutton.WebRadioButton radioData;
    private javax.swing.JTable tablePessoa;
    private javax.swing.JLabel txtInfo;
    private com.alee.laf.button.WebButton webButton1;
    // End of variables declaration//GEN-END:variables
}
