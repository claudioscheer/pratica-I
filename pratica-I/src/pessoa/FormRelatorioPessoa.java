package pessoa;

import components.JFrameBusca;
import components.Validador;
import dao.PessoaDAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import model.CarPessoa;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import utils.Utils;

public class FormRelatorioPessoa extends JFrameBusca {

    private LoadCategorias loadCategorias;
    private List<CarPessoa> pessoas;
    public Validador valid;

    public FormRelatorioPessoa() {
        initComponents();
        this.buscarCategorias();
        this.setLocationRelativeTo(null);
    }

    public void init() {
        this.valid = new Validador(Validador.TipoValidator.ICONE);
        this.valid.addObrigatorioValidator(fieldNome);
        this.valid.addObrigatorioValidator(fieldCpfCnpj);
    }

    public void buscarCategorias() {
        this.loadCategorias = new LoadCategorias();
        this.loadCategorias.execute();
    }

    public class LoadCategorias extends SwingWorker<Void, Void> {

        protected Void doInBackground() throws Exception {
            DefaultTableModel model = (DefaultTableModel) tablePessoa.getModel();
            pessoas = new PessoaDAO().getAll(0, "");
            for (CarPessoa pessoas : pessoas) {
                Object[] o = new Object[4];
                o[0] = pessoas.getPessoaId();
                o[1] = pessoas.getPessoaNome();
                o[2] = pessoas.getPessoaFone();
                o[3] = pessoas.getPessoaTipo();
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
        radioId = new com.alee.laf.radiobutton.WebRadioButton();
        radioNome = new com.alee.laf.radiobutton.WebRadioButton();
        radioCpfCnpj = new com.alee.laf.radiobutton.WebRadioButton();
        fieldId = new javax.swing.JTextField();
        fieldNome = new javax.swing.JTextField();
        fieldCpfCnpj = new javax.swing.JTextField();
        webButton1 = new com.alee.laf.button.WebButton();
        txtInfo = new javax.swing.JLabel();

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
                "ID", "Nome", "Fone", "Tipo"
            }
        ));
        jScrollPane2.setViewportView(tablePessoa);

        labelTipo.setText("Tipo de busca:");

        buttonGroup1.add(radioId);
        radioId.setText("ID");
        radioId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioIdActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioNome);
        radioNome.setText("Nome");
        radioNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNomeActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioCpfCnpj);
        radioCpfCnpj.setText("CPF / CNPJ");
        radioCpfCnpj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioCpfCnpjActionPerformed(evt);
            }
        });

        fieldId.setEnabled(false);

        fieldNome.setEnabled(false);

        fieldCpfCnpj.setEnabled(false);

        webButton1.setText("Filtrar");
        webButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webButton1ActionPerformed(evt);
            }
        });

        txtInfo.setText("Filtre os resultados para gerar um relatório personalizado, ou selecione \"Gerar Relatórios\" para um relatório de todas as pessoas.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelOpcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelTipo)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radioId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(radioNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(radioCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fieldCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldId, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(webButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68))))
                    .addComponent(txtInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTipo)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(radioId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fieldId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(webButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(txtInfo)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing

    private void buttonRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRelatorioActionPerformed
        // List<CarPessoa> listPessoas = new PessoaDAO().getAll(0, "");
        JRBeanCollectionDataSource jrs = new JRBeanCollectionDataSource(pessoas);

        Map parametros = new HashMap();
        try {
            JasperPrint jpr = JasperFillManager
                    .fillReport("src/relatorios/pessoa/listapessoas.jasper",
                            parametros,
                            jrs);
            JasperViewer.viewReport(jpr, false);
        } catch (JRException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_buttonRelatorioActionPerformed

    private void radioIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioIdActionPerformed
        ajustaCampos();
    }//GEN-LAST:event_radioIdActionPerformed

    private void radioNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioNomeActionPerformed
        ajustaCampos();
    }//GEN-LAST:event_radioNomeActionPerformed

    private void radioCpfCnpjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioCpfCnpjActionPerformed
        ajustaCampos();
    }//GEN-LAST:event_radioCpfCnpjActionPerformed

    private void webButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webButton1ActionPerformed
        this.busca();
        // TODO add your handling code here:
    }//GEN-LAST:event_webButton1ActionPerformed

    private void busca() {
        String text = "";
        int indexfiltro = -1;
        if (radioCpfCnpj.isSelected()) {
            indexfiltro = 2;
            text = fieldCpfCnpj.getText();
        } else if (radioNome.isSelected()) {
            indexfiltro = 1;
            text = fieldNome.getText();
        } else {
            indexfiltro = 0;
            text = fieldId.getText();
        }
        this.pessoas.clear();
        Utils.clearTableModel((DefaultTableModel) tablePessoa.getModel());
        pessoas = new PessoaDAO().getAll(indexfiltro, text);

        DefaultTableModel model = (DefaultTableModel) tablePessoa.getModel();
        for (CarPessoa pessoas : pessoas) {
            Object[] o = new Object[4];
            o[0] = pessoas.getPessoaId();
            o[1] = pessoas.getPessoaNome();
            o[2] = pessoas.getPessoaFone();
            o[3] = pessoas.getPessoaTipo();
            model.addRow(o);
        }
        tablePessoa.setModel(model);
    }

    private void ajustaCampos() {
        if (radioCpfCnpj.isSelected()) {
            fieldCpfCnpj.setEnabled(true);
            fieldCpfCnpj.requestFocus();
            fieldId.setEnabled(false);
            fieldNome.setEnabled(false);
        } else if (radioNome.isSelected()) {
            fieldCpfCnpj.setEnabled(false);
            fieldId.setEnabled(false);
            fieldNome.requestFocus();
            fieldNome.setEnabled(true);
        } else {
            fieldCpfCnpj.setEnabled(false);
            fieldId.setEnabled(true);
            fieldId.requestFocus();
            fieldNome.setEnabled(false);
        }
        fieldId.setText("");
        fieldNome.setText("");
        fieldCpfCnpj.setText("");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private com.alee.laf.button.WebButton buttonRelatorio;
    private javax.swing.JTextField fieldCpfCnpj;
    private javax.swing.JTextField fieldId;
    private javax.swing.JTextField fieldNome;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelTipo;
    private javax.swing.JPanel panelOpcoes;
    private com.alee.laf.radiobutton.WebRadioButton radioCpfCnpj;
    private com.alee.laf.radiobutton.WebRadioButton radioId;
    private com.alee.laf.radiobutton.WebRadioButton radioNome;
    private javax.swing.JTable tablePessoa;
    private javax.swing.JLabel txtInfo;
    private com.alee.laf.button.WebButton webButton1;
    // End of variables declaration//GEN-END:variables
}
