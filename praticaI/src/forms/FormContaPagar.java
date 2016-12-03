package forms;

import com.alee.laf.desktoppane.WebInternalFrame;
import components.Validador;
import enumeraveis.TipoConta;
import forms.busca.FormBuscaNotaFiscal;
import dao.carcapOperacoesComerciaisDAO;
import enumeraveis.StatusConta;
import forms.busca.FormBuscaPessoa;
import forms.busca.FormBuscaProduto;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.CarCapContas;
import model.CarcapOperacoesComerciais;
import model.PatNotaFiscal;
import utils.Utils;

/**
 *
 * @author dimhr12
 */
public class FormContaPagar extends WebInternalFrame {

    PatNotaFiscal nf = new PatNotaFiscal();
    private List<CarcapOperacoesComerciais> contas;

    private Validador validador;
    Date dataAtual = new Date();

    public FormContaPagar() {
        super("Contas a Pagar", true, true, true, true);
        this.initComponents();
        this.setBackground(Utils.CoresPadrao.fundoPadrao);

        fieldDataParcela.setDateFormat(Utils.formatoDataPadrao);
        fieldDataEntrada.setDateFormat(Utils.formatoDataPadrao);

        this.validador = new Validador(Validador.TipoValidator.ICONE);
        this.validador.addObrigatorioValidator(fieldNotaFiscal);
        this.validador.addObrigatorioValidator(fieldDescrição);

        carregarTudo();
    }

    public void PreencheDadosNF() { // verifica se uma NF foi selecionada e preenche os campos com os dados referentes a ela
        fieldDescrição.setEnabled(true);
        fieldValorTotal.setEnabled(true);
        ComboBoxFormaPgto.setEnabled(true);
        ComboBoxMeioPgto.setEnabled(true);
        fieldDescrição.setText(nf.getNotaDescricao());
        fieldValorTotal.setValue(nf.getNotaValor());
    }

    public int RetornarLinhaSelecionada(int posicao) {
        int valor = Integer.parseInt(tableLista.getValueAt(tableLista.getSelectedRow(), posicao).toString());
        return valor;
    }

    private void carregarTudo() {
        FormBuscaNotaFiscal buscaNota = new FormBuscaNotaFiscal();
        fieldNotaFiscal.setFrame(buscaNota);
        buscaNota.setFrameBloquear(FormPrincipal.getInstance());

        fieldNotaFiscal.setFuncaoDepoisSelecionar(x -> {
            nf = (PatNotaFiscal) x;
            PreencheDadosNF();
        });

        FormBuscaProduto buscaProduto = new FormBuscaProduto();
        buscaProduto.setFrameBloquear(FormPrincipal.getInstance());

        FormBuscaPessoa buscaFornecedortwoo = new FormBuscaPessoa();
        buscaFornecedortwoo.setFrameBloquear(FormPrincipal.getInstance());

        Date d = new Date();
        fieldDataEntrada.setDate(d);
        atualizarTabela();
    }

    public void zeraCampos() {
        fieldNotaFiscal.setText(null);
        fieldDescrição.setText(null);
        fieldParcelas.setValue(1);
        fieldValorTotal.setValue(0);
        fieldValorParcela.setValue(0);
        fieldDataParcela.setDate(null);
    }

    public void EnabledCampos() {
        fieldParcelas.setEnabled(false);
        fieldValorTotal.setEnabled(false);
        fieldValorParcela.setEnabled(false);
        fieldDataParcela.setEnabled(false);
    }

    public void SalvarConta() {
        CarcapOperacoesComerciais conta = new CarcapOperacoesComerciais();
        carcapOperacoesComerciaisDAO contaDAO = new carcapOperacoesComerciaisDAO();

        int numParcelas = (Integer) fieldParcelas.getValue();
        TipoConta tipoConta = TipoConta.Saida;
        conta.setTipoDeConta(tipoConta);
        conta.setDatLancamento(fieldDataEntrada.getDate());
        conta.setDescricao(fieldDescrição.getText());
        conta.setNumeroParcela(numParcelas);
        conta.setPessoa(nf.getCarPessoa());
        conta.setOperacaoNota(nf);
        conta.setValorTotal(fieldValorTotal.getValue());
        conta.setValorRecebido(fieldValorRecebido.getValue());
        conta.setValorPendente(fieldValorTotal.getValue() - fieldValorRecebido.getValue());
        conta.setValorParcela(conta.getValorPendente() / numParcelas);

        if (fieldNotaFiscal.getValue() != null) {
            conta.setOperacaoNota((PatNotaFiscal) fieldNotaFiscal.getValue());
        }

        Calendar dataPrimeiraParcela = Calendar.getInstance();
        dataPrimeiraParcela.setTime(fieldDataParcela.getDate());

        for (int i = 0; i < numParcelas; i++) {
            CarCapContas parcela = new CarCapContas();

            parcela.setCapContaStatus(StatusConta.PendenteEmser);
            parcela.setDataVencimento(dataPrimeiraParcela.getTime());

            conta.addParcela(parcela);
            dataPrimeiraParcela.add(Calendar.MONTH, 1);
        }
        contaDAO.insert(conta);
    }

    private void atualizarTabela() {
        DefaultTableModel model = (DefaultTableModel) tableLista.getModel();
        Utils.clearTableModel(model);
        contas = new carcapOperacoesComerciaisDAO().buscarContasPagar();
        for (CarcapOperacoesComerciais operacao : contas) {
            model.addRow(contaToArray(operacao));
        }
        tableLista.setModel(model);
    }

    private Object[] contaToArray(CarcapOperacoesComerciais c) {
        Object[] o = new Object[6];
        o[0] = c.getPessoa();
        o[1] = c.getDescricao();
        o[2] = c.getNumeroParcela();
        o[3] = c.getValorTotal();
        o[4] = c.getValorRecebido();
        o[5] = c.getValorPendente();
        return o;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        webPanel1 = new com.alee.laf.panel.WebPanel();
        ComboBoxMeioPgto = new com.alee.laf.combobox.WebComboBox();
        ComboBoxFormaPgto = new com.alee.laf.combobox.WebComboBox();
        txtParcelas = new com.alee.laf.label.WebLabel();
        txtValorRecebido = new com.alee.laf.label.WebLabel();
        fieldNotaFiscal = new components.TextFieldFK();
        txtNotaFiscal = new com.alee.laf.label.WebLabel();
        btnSalvar = new com.alee.laf.button.WebButton();
        txtFormaPagamento = new com.alee.laf.label.WebLabel();
        txtDescricao = new com.alee.laf.label.WebLabel();
        fieldDescrição = new javax.swing.JTextField();
        txtDataPagamento = new com.alee.laf.label.WebLabel();
        txtMeioPagamento = new com.alee.laf.label.WebLabel();
        txtValorTotal = new com.alee.laf.label.WebLabel();
        fieldDataParcela = new com.alee.extended.date.WebDateField();
        btnParcelas = new com.alee.laf.button.WebButton();
        fieldDataEntrada = new com.alee.extended.date.WebDateField();
        txtPrimeiroVencimento = new com.alee.laf.label.WebLabel();
        fieldParcelas = new com.alee.laf.spinner.WebSpinner();
        txtValorParcela = new com.alee.laf.label.WebLabel();
        fieldValorParcela = new components.TextFieldValorMonetario();
        fieldValorTotal = new components.TextFieldValorMonetario();
        fieldValorRecebido = new components.TextFieldValorMonetario();
        txtValorPendente = new com.alee.laf.label.WebLabel();
        fieldValorPendente = new components.TextFieldValorMonetario();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableLista = new javax.swing.JTable();

        webPanel1.setEnabled(false);

        ComboBoxMeioPgto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dinheiro", "Cheque", "Banco" }));
        ComboBoxMeioPgto.setEnabled(false);

        ComboBoxFormaPgto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "À Vista", "A Prazo" }));
        ComboBoxFormaPgto.setEnabled(false);
        ComboBoxFormaPgto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxFormaPgtoActionPerformed(evt);
            }
        });

        txtParcelas.setText("Parcelas");

        txtValorRecebido.setText("Valor entrada");

        fieldNotaFiscal.setToolTipText("Selecione a nota fiscal a ser vinculada a esta conta");

        txtNotaFiscal.setText("Nota fiscal");

        btnSalvar.setBackground(new java.awt.Color(51, 255, 51));
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        txtFormaPagamento.setText("Forma de pagamento");

        txtDescricao.setText("Descrição:");

        fieldDescrição.setToolTipText("");
        fieldDescrição.setEnabled(false);

        txtDataPagamento.setText("Data da nota fiscal:");

        txtMeioPagamento.setText("Meio de pagamento");

        txtValorTotal.setText("Valor total:");

        fieldDataParcela.setToolTipText("Informe a data de pagamento da primeira parcela");

        btnParcelas.setBackground(new java.awt.Color(51, 255, 51));
        btnParcelas.setText("Parcelas");
        btnParcelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParcelasActionPerformed(evt);
            }
        });

        fieldDataEntrada.setToolTipText("Informe a data de entrada desta conta");
        fieldDataEntrada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fieldDataEntradaMouseClicked(evt);
            }
        });

        txtPrimeiroVencimento.setText("Data 1° vencimento");

        fieldParcelas.setModel(new javax.swing.SpinnerNumberModel(1, 1, 50, 1));
        fieldParcelas.setToolTipText("Informe o número de parcelas da conta");
        fieldParcelas.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                fieldParcelasStateChanged(evt);
            }
        });

        txtValorParcela.setText("Valor da parcela");

        fieldValorParcela.setEditable(false);
        fieldValorParcela.setEnabled(false);

        fieldValorTotal.setEditable(false);
        fieldValorTotal.setEnabled(false);

        fieldValorRecebido.setToolTipText("Caso tenha sido pago algum valor da conta, informe aqui");
        fieldValorRecebido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fieldValorRecebidoKeyReleased(evt);
            }
        });

        txtValorPendente.setText("Valor pendente");

        fieldValorPendente.setEnabled(false);

        tableLista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fornecedor", "Descrição", "Parcelas", "Valor Total", "Valor Pago", "Valor Pendente"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableLista);

        javax.swing.GroupLayout webPanel1Layout = new javax.swing.GroupLayout(webPanel1);
        webPanel1.setLayout(webPanel1Layout);
        webPanel1Layout.setHorizontalGroup(
            webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(webPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldNotaFiscal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fieldDescrição)
                    .addGroup(webPanel1Layout.createSequentialGroup()
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNotaFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(webPanel1Layout.createSequentialGroup()
                                .addComponent(txtParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(235, 235, 235)
                                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fieldValorParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtValorParcela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(webPanel1Layout.createSequentialGroup()
                                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fieldDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(88, 88, 88)
                                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fieldValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(webPanel1Layout.createSequentialGroup()
                                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ComboBoxFormaPgto, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtValorRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(88, 88, 88)
                                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtValorPendente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ComboBoxMeioPgto, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMeioPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fieldValorPendente, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldValorRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrimeiroVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 22, Short.MAX_VALUE))
                    .addGroup(webPanel1Layout.createSequentialGroup()
                        .addComponent(fieldDataParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(webPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE))
                    .addGroup(webPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        webPanel1Layout.setVerticalGroup(
            webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(webPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(webPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(webPanel1Layout.createSequentialGroup()
                        .addComponent(txtNotaFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldNotaFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldDescrição, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMeioPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(webPanel1Layout.createSequentialGroup()
                                .addComponent(ComboBoxFormaPgto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtValorRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fieldValorRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(webPanel1Layout.createSequentialGroup()
                                .addComponent(ComboBoxMeioPgto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtValorPendente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fieldValorPendente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtValorParcela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fieldParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldValorParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(txtPrimeiroVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldDataParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 16, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(webPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(webPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_notasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_notasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_notasActionPerformed

    private void txt_pess_cad1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pess_cad1ActionPerformed

    }//GEN-LAST:event_txt_pess_cad1ActionPerformed

    private void fieldDataEntradaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fieldDataEntradaMouseClicked

    }//GEN-LAST:event_fieldDataEntradaMouseClicked

    private void btnParcelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParcelasActionPerformed

        if (tableLista.getSelectedRow() < 0) {
            return;
        }

        FormParcelasContaPagar parcelas = new FormParcelasContaPagar();
        parcelas.setConta(contas.get(tableLista.getSelectedRow()));
        parcelas.BuscarParcelas();
        parcelas.setVisible(true);

    }//GEN-LAST:event_btnParcelasActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (!validador.isValid()) {
            return;
        }

        SalvarConta();
        zeraCampos();
        atualizarTabela();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void ComboBoxFormaPgtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxFormaPgtoActionPerformed
        fieldParcelas.setEnabled(ComboBoxFormaPgto.getSelectedIndex() == 1);
    }//GEN-LAST:event_ComboBoxFormaPgtoActionPerformed

    private void fieldParcelasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_fieldParcelasStateChanged
        fieldValorParcela.setValue(fieldValorPendente.getValue() / (Integer) fieldParcelas.getValue());
    }//GEN-LAST:event_fieldParcelasStateChanged

    private void fieldValorRecebidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldValorRecebidoKeyReleased
        fieldValorPendente.setValue(fieldValorTotal.getValue() - fieldValorRecebido.getValue());
    }//GEN-LAST:event_fieldValorRecebidoKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.combobox.WebComboBox ComboBoxFormaPgto;
    private com.alee.laf.combobox.WebComboBox ComboBoxMeioPgto;
    private com.alee.laf.button.WebButton btnParcelas;
    private com.alee.laf.button.WebButton btnSalvar;
    private com.alee.extended.date.WebDateField fieldDataEntrada;
    private com.alee.extended.date.WebDateField fieldDataParcela;
    private javax.swing.JTextField fieldDescrição;
    private components.TextFieldFK fieldNotaFiscal;
    private com.alee.laf.spinner.WebSpinner fieldParcelas;
    private components.TextFieldValorMonetario fieldValorParcela;
    private components.TextFieldValorMonetario fieldValorPendente;
    private components.TextFieldValorMonetario fieldValorRecebido;
    private components.TextFieldValorMonetario fieldValorTotal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableLista;
    private com.alee.laf.label.WebLabel txtDataPagamento;
    private com.alee.laf.label.WebLabel txtDescricao;
    private com.alee.laf.label.WebLabel txtFormaPagamento;
    private com.alee.laf.label.WebLabel txtMeioPagamento;
    private com.alee.laf.label.WebLabel txtNotaFiscal;
    private com.alee.laf.label.WebLabel txtParcelas;
    private com.alee.laf.label.WebLabel txtPrimeiroVencimento;
    private com.alee.laf.label.WebLabel txtValorParcela;
    private com.alee.laf.label.WebLabel txtValorPendente;
    private com.alee.laf.label.WebLabel txtValorRecebido;
    private com.alee.laf.label.WebLabel txtValorTotal;
    private com.alee.laf.panel.WebPanel webPanel1;
    // End of variables declaration//GEN-END:variables
}
