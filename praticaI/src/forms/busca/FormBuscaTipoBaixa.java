package forms.busca;

import com.alee.laf.optionpane.WebOptionPane;
import components.JFrameBusca;
import components.TextFieldFK;
import components.panelsCads.PanelCadTipoBaixa;
import dao.PatNotaFiscalDAO;
import dao.PatTipoBaixaDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import model.PatTipoBaixa;
import utils.Utils;

public class FormBuscaTipoBaixa extends JFrameBusca {

    private PanelCadTipoBaixa panelCadastro;
    public int indexEditando;

    private int paginaBuscar;

    private List<PatTipoBaixa> tiposBaixa;

    public FormBuscaTipoBaixa() {
        initComponents();

        this.tiposBaixa = new ArrayList<>();

        this.tabelaTipoBaixa.setSortable(true);
        this.tabelaTipoBaixa.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Código", "Nome"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        this.tabelaTipoBaixa.setLoadMore(x -> new LoadTipoBaixa().execute());
        this.paginaBuscar = 0;

        new LoadTipoBaixa().execute();

        this.txtBuscar.setEventBuscar((e) -> {
            this.paginaBuscar = 0;
            this.tiposBaixa.clear();
            new LoadTipoBaixa().execute();
        });

        this.txtBuscar.addOpcoesBuscar(new String[]{
            "Código",
            "Descrição"
        });

        this.verificaPlaceholderText();
        this.txtBuscar.setEventChangeComboBox(al -> {
            this.verificaPlaceholderText();
        });
        this.setLocationRelativeTo(null);

    }

    private void verificaPlaceholderText() {
        switch (this.txtBuscar.getFiltroSelecionado()) {
            case 0:
                this.txtBuscar.setPlaceholderText("Digite o código");
                break;

            case 1:
                this.txtBuscar.setPlaceholderText("Digite a descrição");
                break;
        }
    }

    public class LoadTipoBaixa extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() throws Exception {
            DefaultTableModel model = (DefaultTableModel) tabelaTipoBaixa.getModel();

            tiposBaixa.addAll(new PatTipoBaixaDAO().getAll(paginaBuscar++, txtBuscar.getFiltroSelecionado(), txtBuscar.getText()));
            Utils.clearTableModel((DefaultTableModel) tabelaTipoBaixa.getModel());
            tiposBaixa.forEach((tipoBaixa) -> {
                model.addRow(impostoToArray(tipoBaixa));
            });
            tabelaTipoBaixa.setModel(model);
            return null;
        }

        @Override
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
        btnExcluir = new com.alee.laf.button.WebButton();
        txtBuscar = new components.TextFieldBuscar();
        tabelaTipoBaixa = new components.JTableLoadScroll();

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

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOpcoesLayout = new javax.swing.GroupLayout(panelOpcoes);
        panelOpcoes.setLayout(panelOpcoesLayout);
        panelOpcoesLayout.setHorizontalGroup(
            panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 620, Short.MAX_VALUE)
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
                    .addComponent(buttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout panelConsultaLayout = new javax.swing.GroupLayout(panelConsulta);
        panelConsulta.setLayout(panelConsultaLayout);
        panelConsultaLayout.setHorizontalGroup(
            panelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelOpcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tabelaTipoBaixa, javax.swing.GroupLayout.DEFAULT_SIZE, 922, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelConsultaLayout.setVerticalGroup(
            panelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConsultaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabelaTipoBaixa, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panelPanelsLayout = new javax.swing.GroupLayout(panelPanels);
        panelPanels.setLayout(panelPanelsLayout);
        panelPanelsLayout.setHorizontalGroup(
            panelPanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 859, Short.MAX_VALUE)
            .addGroup(panelPanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPanelsLayout.setVerticalGroup(
            panelPanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 462, Short.MAX_VALUE)
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
        int linhaselecionada = this.tabelaTipoBaixa.getSelectedRow();
        if (linhaselecionada < 0) {
            Utils.notificacao("Selecione um tipo de baixa!", Utils.TipoNotificacao.erro, 0);
            return;
        }

        PatTipoBaixa tipoBaixa = this.tiposBaixa.get(linhaselecionada);
        if (this.getFrameBuscaTipo() == JFrameBuscaTipo.textFieldFK) {
            TextFieldFK text = this.getTextFieldFK();
            text.setText(tipoBaixa.getTipoBaixaCodigo() + " - " + tipoBaixa.getTipoBaixaDescricao());
            text.setValue(tipoBaixa);
        } else {
            Consumer<Object> function = this.getFunction();
            function.accept(tipoBaixa);
        }

        this.getFrameBloquear().setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_buttonSelecionarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        this.initFormCadastro();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void buttonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditarActionPerformed
        int linhaselecionada = this.tabelaTipoBaixa.getSelectedRow();
        if (linhaselecionada < 0) {
            Utils.notificacao("Selecione um tipo de baixa!", Utils.TipoNotificacao.erro, 0);
            return;
        }

        PatTipoBaixa tipoBaixa = this.tiposBaixa.get(linhaselecionada);

        this.indexEditando = linhaselecionada;

        this.initFormCadastro();
        this.fecharAbrirPanelCadastro(false);

        this.panelCadastro.setDadosEditar(tipoBaixa);
    }//GEN-LAST:event_buttonEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int linhaselecionada = this.tabelaTipoBaixa.getSelectedRow();
        if (linhaselecionada < 0) {
            Utils.notificacao("Selecione um tipo de baixa!", Utils.TipoNotificacao.erro, 0);
            return;
        }

        if (WebOptionPane.showConfirmDialog(this, "Deseja deletar o tipo de baixa?", "Excluir",
                WebOptionPane.YES_NO_OPTION,
                WebOptionPane.QUESTION_MESSAGE) == WebOptionPane.OK_OPTION) {

            PatTipoBaixa tipoBaixa = this.tiposBaixa.get(linhaselecionada);
            new PatTipoBaixaDAO().delete(tipoBaixa);
            this.removeImposto(linhaselecionada);
            Utils.notificacao("Tipo baixa removida!", Utils.TipoNotificacao.ok, 0);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void initFormCadastro() {
        this.panelCadastro = new PanelCadTipoBaixa();
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

        PatTipoBaixa imposto = this.panelCadastro.getTipoBaixa();
        if (!this.panelCadastro.editando) {
            new PatTipoBaixaDAO().insert(imposto);
        } else {
            new PatTipoBaixaDAO().update(imposto);
            this.panelCadastro.editando = false;
            this.removeImposto(this.indexEditando);
            this.indexEditando = -1;
        }

        this.addTipoBaixa(imposto);

        Utils.notificacao("Tipo de baixa salvo!", Utils.TipoNotificacao.ok, 0);
        this.fecharAbrirPanelCadastro(true);
    }

    public void addTipoBaixa(PatTipoBaixa imposto) {
        this.tiposBaixa.add(0, imposto);
        DefaultTableModel model = (DefaultTableModel) this.tabelaTipoBaixa.getModel();
        model.insertRow(0, impostoToArray(imposto));
        this.tabelaTipoBaixa.setModel(model);
    }

    private Object[] impostoToArray(PatTipoBaixa imposto) {
        Object[] o = new Object[2];
        o[0] = imposto.getTipoBaixaCodigo();
        o[1] = imposto.getTipoBaixaDescricao();
        return o;
    }

    public void removeImposto(int index) {
        this.tiposBaixa.remove(index);
        DefaultTableModel model = (DefaultTableModel) this.tabelaTipoBaixa.getModel();
        model.removeRow(index);
        this.tabelaTipoBaixa.setModel(model);
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
    private com.alee.laf.button.WebButton btnExcluir;
    private com.alee.laf.button.WebButton btnNovo;
    private com.alee.laf.button.WebButton buttonEditar;
    private com.alee.laf.button.WebButton buttonSelecionar;
    private javax.swing.JPanel panelConsulta;
    private javax.swing.JPanel panelOpcoes;
    private javax.swing.JPanel panelPanels;
    private components.JTableLoadScroll tabelaTipoBaixa;
    private components.TextFieldBuscar txtBuscar;
    // End of variables declaration//GEN-END:variables
}
