package forms.busca;

import components.JFrameBusca;
import components.TextFieldFK;
import components.panelsCads.PanelCadImposto;
import dao.ImpostoDAO;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import modelAntigo.Imposto;
import utils.Utils;

public class FormBuscaImposto extends JFrameBusca {

    private PanelCadImposto panelCadastro;
    public int indexEditando;

    private LoadImpostos loadImpostos;
    private List<Imposto> impostos;

    public FormBuscaImposto() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.txtBuscar.showComboOpcoes(false);
        this.buscarImpostos();
    }

    public void buscarImpostos() {
        this.loadImpostos = new LoadImpostos();
        this.loadImpostos.execute();
    }

    public class LoadImpostos extends SwingWorker<Void, Void> {

        protected Void doInBackground() throws Exception {

            DefaultTableModel model = (DefaultTableModel) tabelaImpostos.getModel();

            impostos = new ImpostoDAO().getAll();
            for (Imposto imposto : impostos) {
                Object[] o = new Object[3];
                o[0] = imposto.getImposto();
                o[1] = imposto.getNome();
                o[2] = imposto.getAliquota();
                model.addRow(o);
            }
            tabelaImpostos.setModel(model);
            return null;
        }

        public void done() {

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPanels = new javax.swing.JPanel();
        panelConsulta = new javax.swing.JPanel();
        panelOpcoes = new javax.swing.JPanel();
        buttonSelecionar = new com.alee.laf.button.WebButton();
        btnNovo = new com.alee.laf.button.WebButton();
        buttonEditar = new com.alee.laf.button.WebButton();
        txtBuscar = new components.TextFieldBuscar();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaImpostos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        panelOpcoes.setBackground(new java.awt.Color(255, 255, 255));

        buttonSelecionar.setText("Selecionar");
        buttonSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSelecionarActionPerformed(evt);
            }
        });

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        buttonEditar.setText("Editar");
        buttonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOpcoesLayout = new javax.swing.GroupLayout(panelOpcoes);
        panelOpcoes.setLayout(panelOpcoesLayout);
        panelOpcoesLayout.setHorizontalGroup(
            panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 706, Short.MAX_VALUE)
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonSelecionar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelOpcoesLayout.setVerticalGroup(
            panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOpcoesLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSelecionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        tabelaImpostos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Alíquota"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabelaImpostos);

        javax.swing.GroupLayout panelConsultaLayout = new javax.swing.GroupLayout(panelConsulta);
        panelConsulta.setLayout(panelConsultaLayout);
        panelConsultaLayout.setHorizontalGroup(
            panelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelOpcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelConsultaLayout.setVerticalGroup(
            panelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConsultaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panelPanelsLayout = new javax.swing.GroupLayout(panelPanels);
        panelPanels.setLayout(panelPanelsLayout);
        panelPanelsLayout.setHorizontalGroup(
            panelPanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 942, Short.MAX_VALUE)
            .addGroup(panelPanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPanelsLayout.setVerticalGroup(
            panelPanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 555, Short.MAX_VALUE)
            .addGroup(panelPanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPanels, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPanels, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.getFrameBloquear().setEnabled(true);
    }//GEN-LAST:event_formWindowClosing

    private void buttonSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSelecionarActionPerformed
        int linhaselecionada = this.tabelaImpostos.getSelectedRow();
        if (linhaselecionada < 0) {
            Utils.notificacao("Selecione um imposto!", Utils.TipoNotificacao.erro, 0);
            return;
        }

        Imposto imposto = this.impostos.get(linhaselecionada);
        if (this.getFrameBuscaTipo() == JFrameBuscaTipo.textFieldFK) {
            TextFieldFK text = this.getTextFieldFK();
            text.setText(imposto.getImposto() + " - " + imposto.getNome());
            text.setValue(imposto);
        } else {
            Consumer<Object> function = this.getFunction();
            function.accept(imposto);
        }

        this.getFrameBloquear().setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_buttonSelecionarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        this.initFormCadastro();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void buttonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditarActionPerformed
        int linhaselecionada = this.tabelaImpostos.getSelectedRow();
        if (linhaselecionada < 0) {
            Utils.notificacao("Selecione um imposto!", Utils.TipoNotificacao.erro, 0);
            return;
        }

        Imposto imposto = this.impostos.get(linhaselecionada);

        this.indexEditando = linhaselecionada;

        this.initFormCadastro();
        this.fecharAbrirPanelCadastro(false);

        this.panelCadastro.setDadosEditar(imposto);
    }//GEN-LAST:event_buttonEditarActionPerformed

    private void initFormCadastro() {
        this.panelCadastro = new PanelCadImposto();
        this.panelCadastro.init();
        this.panelCadastro.setEvents((e) -> {
            this.salvarImposto();
        }, (e) -> {
            this.fecharAbrirPanelCadastro(true);
        });
        this.panelCadastro.setBounds(0, 0, this.panelPanels.getWidth(), this.panelPanels.getHeight());
        this.fecharAbrirPanelCadastro(false);
    }

    private void salvarImposto() {

        if (!this.panelCadastro.validador.isValid()) {
            return;
        }

        Imposto imposto = this.panelCadastro.getAtivoImobilizado();
        if (!this.panelCadastro.editando) {
            new ImpostoDAO().insert(imposto);
        } else {
            new ImpostoDAO().update(imposto);
            this.panelCadastro.editando = false;
            this.removeImposto(this.indexEditando);
            this.indexEditando = -1;
        }

        this.addImposto(imposto);

        Utils.notificacao("Imposto salvo!", Utils.TipoNotificacao.ok, 0);
        this.fecharAbrirPanelCadastro(true);
    }

    public void addImposto(Imposto imposto) {
        this.impostos.add(0, imposto);
        DefaultTableModel model = (DefaultTableModel) this.tabelaImpostos.getModel();
        model.insertRow(0, impostoToArray(imposto));
        this.tabelaImpostos.setModel(model);
    }

    private Object[] impostoToArray(Imposto imposto) {
        Object[] o = new Object[5];
        o[0] = imposto.getImposto();
        o[1] = imposto.getNome();
        o[2] = imposto.getAliquota();
        return o;
    }

    public void removeImposto(int index) {
        this.impostos.remove(index);
        DefaultTableModel model = (DefaultTableModel) this.tabelaImpostos.getModel();
        model.removeRow(index);
        this.tabelaImpostos.setModel(model);
    }

    private void fecharAbrirPanelCadastro(boolean fechar) {
        if (fechar) {
            this.panelPanels.remove(this.panelCadastro);
            this.panelPanels.add(this.panelConsulta);
        } else {
            this.panelPanels.remove(this.panelConsulta);
            this.panelPanels.add(this.panelCadastro);
        }

        this.panelPanels.revalidate();
        this.panelPanels.repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.button.WebButton btnNovo;
    private com.alee.laf.button.WebButton buttonEditar;
    private com.alee.laf.button.WebButton buttonSelecionar;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelConsulta;
    private javax.swing.JPanel panelOpcoes;
    private javax.swing.JPanel panelPanels;
    private javax.swing.JTable tabelaImpostos;
    private components.TextFieldBuscar txtBuscar;
    // End of variables declaration//GEN-END:variables
}
