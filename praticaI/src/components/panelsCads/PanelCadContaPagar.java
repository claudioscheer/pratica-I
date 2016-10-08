package components.panelsCads;

import com.alee.laf.optionpane.WebOptionPane;
import com.alee.laf.panel.WebPanel;
import components.Validador;
import forms.FormPrincipal;
import forms.busca.FormBuscaImposto;
import forms.busca.FormBuscaNotaFiscal;
import forms.busca.FormBuscaProdutoImposto;
import forms.busca.FormBuscaFornecedor;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Imposto;
import model.ImpostoItemNota;
import model.ImpostoNotaFiscal;
import model.ItemNota;
import model.NotaFiscal;
import utils.Utils;

public class PanelCadContaPagar extends WebPanel {

    public Validador validador;

    public PanelCadContaPagar() {
    }

    public void init() {
        this.initComponents();

        FormBuscaFornecedor formFornecedor = new FormBuscaFornecedor();
        formFornecedor.setFrameBloquear(FormPrincipal.getInstance());
        this.txtFornecedor.setFrame(formFornecedor);

        FormBuscaNotaFiscal formNotaFiscal = new FormBuscaNotaFiscal();
        formNotaFiscal.setFrameBloquear(FormPrincipal.getInstance());
        this.txtNotaFiscal.setFrame(formNotaFiscal);

        scrollCadastro.getVerticalScrollBar().setUnitIncrement(20);
    }

    public void setEvents(ActionListener salvar, ActionListener cancelar) {
        this.btnSalvar.addActionListener(salvar);
        this.btnCancelar.addActionListener(cancelar);
    }

    public NotaFiscal getNotaFiscal() {
        NotaFiscal nota = new NotaFiscal();

        return nota;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollCadastro = new javax.swing.JScrollPane();
        panelItens = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        txtFornecedor = new components.TextFieldFK();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtDataEmissao = new com.alee.extended.date.WebDateField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaParcelar = new javax.swing.JTable();
        btnAdicionarProduto = new javax.swing.JButton();
        btnRemoverProduto = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtNotaFiscal = new components.TextFieldFK();
        txtNumeroParcelas = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        checkParcelarAutomaticamente = new com.alee.laf.checkbox.WebCheckBox();
        jLabel7 = new javax.swing.JLabel();
        txtValor1 = new javax.swing.JTextField();
        ComboBoxContas = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        RadioBtnEstoque = new javax.swing.JRadioButton();
        RadioBtnPatrimonio = new javax.swing.JRadioButton();
        panelOpcoes = new javax.swing.JPanel();
        btnSalvar = new com.alee.laf.button.WebButton();
        btnCancelar = new com.alee.laf.button.WebButton();

        setMinimumSize(null);

        scrollCadastro.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollCadastro.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollCadastro.setMaximumSize(new java.awt.Dimension(0, 0));

        panelItens.setBackground(new java.awt.Color(255, 255, 255));
        panelItens.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelItens.setPreferredSize(new java.awt.Dimension(0, 570));

        jLabel2.setText("Valor");

        txtValor.setText("0");

        jLabel3.setText("Fornecedor");

        jLabel4.setText("Data emissão");

        jLabel6.setText("Parcelas");

        tabelaParcelar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Dt. Venc.", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabelaParcelar);

        btnAdicionarProduto.setText("Adicionar");
        btnAdicionarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarProdutoActionPerformed(evt);
            }
        });

        btnRemoverProduto.setText("Remover");
        btnRemoverProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverProdutoActionPerformed(evt);
            }
        });

        jLabel8.setText("Nota Fiscal");

        jLabel5.setText("Número parcelas");

        checkParcelarAutomaticamente.setText("Parcelar automaticamente");
        checkParcelarAutomaticamente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkParcelarAutomaticamenteItemStateChanged(evt);
            }
        });

        jLabel7.setText("Valor entrada");

        txtValor1.setText("0");

        ComboBoxContas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setText("Conta de débito:");

        jLabel10.setText("Destino:");

        RadioBtnEstoque.setText("Estoque");

        RadioBtnPatrimonio.setText("Patrimônio");
        RadioBtnPatrimonio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioBtnPatrimonioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelItensLayout = new javax.swing.GroupLayout(panelItens);
        panelItens.setLayout(panelItensLayout);
        panelItensLayout.setHorizontalGroup(
            panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelItensLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelItensLayout.createSequentialGroup()
                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtValor1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelItensLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10))
                            .addGroup(panelItensLayout.createSequentialGroup()
                                .addComponent(ComboBoxContas, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(RadioBtnPatrimonio)
                                    .addComponent(RadioBtnEstoque)))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 755, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8)
                    .addComponent(txtNotaFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addGroup(panelItensLayout.createSequentialGroup()
                        .addComponent(txtNumeroParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(checkParcelarAutomaticamente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addGroup(panelItensLayout.createSequentialGroup()
                        .addGap(597, 597, 597)
                        .addComponent(btnRemoverProduto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAdicionarProduto)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelItensLayout.setVerticalGroup(
            panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelItensLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelItensLayout.createSequentialGroup()
                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(panelItensLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNotaFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(panelItensLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNumeroParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(checkParcelarAutomaticamente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6))
                    .addGroup(panelItensLayout.createSequentialGroup()
                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addGroup(panelItensLayout.createSequentialGroup()
                                    .addGap(20, 20, 20)
                                    .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(ComboBoxContas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(RadioBtnEstoque))))
                            .addGroup(panelItensLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(txtValor1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RadioBtnPatrimonio)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionarProduto)
                    .addComponent(btnRemoverProduto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        scrollCadastro.setViewportView(panelItens);

        panelOpcoes.setBackground(new java.awt.Color(255, 255, 255));

        btnSalvar.setText("Salvar");

        btnCancelar.setText("Cancelar");

        javax.swing.GroupLayout panelOpcoesLayout = new javax.swing.GroupLayout(panelOpcoes);
        panelOpcoes.setLayout(panelOpcoesLayout);
        panelOpcoesLayout.setHorizontalGroup(
            panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOpcoesLayout.createSequentialGroup()
                .addContainerGap(619, Short.MAX_VALUE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelOpcoesLayout.setVerticalGroup(
            panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOpcoesLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelOpcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrollCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrollCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarProdutoActionPerformed
        DefaultTableModel model = (DefaultTableModel) this.tabelaParcelar.getModel();
        model.addRow(new Object[]{"", "", ""});
    }//GEN-LAST:event_btnAdicionarProdutoActionPerformed

    private void btnRemoverProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverProdutoActionPerformed
        int linhaselecionada = this.tabelaParcelar.getSelectedRow();
        if (linhaselecionada < 0) {
            Utils.notificacao("Selecione uma parcela!", Utils.TipoNotificacao.erro, 0);
            return;
        }
        this.removerParcela(linhaselecionada);
    }//GEN-LAST:event_btnRemoverProdutoActionPerformed

    private void checkParcelarAutomaticamenteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkParcelarAutomaticamenteItemStateChanged
        if (this.checkParcelarAutomaticamente.isSelected()) {

            DefaultTableModel model = (DefaultTableModel) this.tabelaParcelar.getModel();
            if (model.getRowCount() > 0) {
                for (int i = model.getRowCount() - 1; i > -1; i--) {
                    model.removeRow(i);
                }
            }

            Calendar date = Calendar.getInstance();
            double valor = Double.parseDouble(this.txtValor.getText());
            int parcelas = Integer.parseInt(this.txtNumeroParcelas.getText());
            double valorparcela = valor / parcelas;

            for (int i = 0; i < parcelas; i++) {
                date.add(Calendar.MONTH, 1);
                this.addParcela(date.getTime(), valorparcela, i + 1);
            }

        }
    }//GEN-LAST:event_checkParcelarAutomaticamenteItemStateChanged

    private void RadioBtnPatrimonioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioBtnPatrimonioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RadioBtnPatrimonioActionPerformed

    private void addParcela(Date data, double valor, int numeroparcela) {
        DefaultTableModel model = (DefaultTableModel) this.tabelaParcelar.getModel();
        model.addRow(new Object[]{numeroparcela, Utils.formatData(data), valor});
    }

    private void removerParcela(int index) {
        ((DefaultTableModel) this.tabelaParcelar.getModel()).removeRow(index);
        Utils.notificacao("Removida!", Utils.TipoNotificacao.ok, 0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxContas;
    private javax.swing.JRadioButton RadioBtnEstoque;
    private javax.swing.JRadioButton RadioBtnPatrimonio;
    private javax.swing.JButton btnAdicionarProduto;
    private com.alee.laf.button.WebButton btnCancelar;
    private javax.swing.JButton btnRemoverProduto;
    private com.alee.laf.button.WebButton btnSalvar;
    private com.alee.laf.checkbox.WebCheckBox checkParcelarAutomaticamente;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelItens;
    private javax.swing.JPanel panelOpcoes;
    private javax.swing.JScrollPane scrollCadastro;
    private javax.swing.JTable tabelaParcelar;
    private com.alee.extended.date.WebDateField txtDataEmissao;
    private components.TextFieldFK txtFornecedor;
    private components.TextFieldFK txtNotaFiscal;
    private javax.swing.JTextField txtNumeroParcelas;
    private javax.swing.JTextField txtValor;
    private javax.swing.JTextField txtValor1;
    // End of variables declaration//GEN-END:variables
}
