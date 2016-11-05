/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.alee.laf.WebLookAndFeel;
import com.alee.laf.desktoppane.WebInternalFrame;
import com.alee.managers.language.LanguageManager;
import dao.CarCapContasDAO;
import dao.FlxcxOperacoesDAO;
import enumeraveis.TipoConta;
import forms.busca.FormBuscaFornecedor;
import forms.busca.FormBuscaMarca;
import forms.busca.FormBuscaNotaFiscal;
import forms.busca.FormBuscaProduto;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import model.CarCapContas;
import model.FlxcxOperacoes;
import utils.Utils;

/**
 *
 * @author Marcos
 */
public class FormContaReceber extends WebInternalFrame {

    /**
     * Creates new form FormMovimento
     */
    public FormContaReceber() {

        super("Contas a Receber", true, true, true, true);
        this.initComponents();
        new LoadBackground().execute();
    }

    private void carregarTudo() {
        FormBuscaProduto buscaProduto = new FormBuscaProduto();

        buscaProduto.setFrameBloquear(FormPrincipal.getInstance());

        txt_busca_produto.setFrame(buscaProduto);

        FormBuscaFornecedor buscaFornecedortwoo = new FormBuscaFornecedor();

        buscaFornecedortwoo.setFrameBloquear(FormPrincipal.getInstance());

        txt_pess_cad.setFrame(buscaFornecedortwoo);

        txt_busca_cliente_fornecedor.setFrame(buscaFornecedortwoo);

        FormBuscaNotaFiscal buscaNota = new FormBuscaNotaFiscal();

        buscaNota.setFrameBloquear(FormPrincipal.getInstance());

        txt_busca_nota.setFrame(buscaNota);

        Preenche_tabela();
        preenche_Combo();

    }

    private class LoadBackground extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() throws Exception {
            carregarTudo();
            return null;
        }

        @Override
        protected void done() {

            utils.Utils.notificacao("sdjkghsdflkjhgksjdf", Utils.TipoNotificacao.erro, 0);

        }

    }

    //****METODOS DE USO
    public void preenche_Combo() {

        FlxcxOperacoesDAO Flx = new FlxcxOperacoesDAO();

        List<FlxcxOperacoes> tipo_operacao = Flx.ListarTodas();

        for (FlxcxOperacoes i : tipo_operacao) {

            Comb_tip_operacao.addItem(i.getOpCodigo() + " - " + i.getOpDescricao());

        }

    }

    public void Preenche_tabela() {

        CarCapContasDAO retorn_valores = new CarCapContasDAO();

        List<CarCapContas> conta = retorn_valores.ListarTodos(new Date(), new Date());

        String[] colunas = {"Data", "Produto", "Quantidade", "Status", "Valor da parcela"};

        DefaultTableModel tabelamodelo = new DefaultTableModel(colunas, 0);

        for (CarCapContas j : conta) {

            tabelamodelo.addRow(new Object[]{
                j.getContaDataEmissao(), j.getProduto(), j.getQuantidade_produto(), j.getCapContaStatus(), j.getCarCapParcelas()});

        }

        txt_tabela.setModel(tabelamodelo);

    }

    public void cadastro_parcela(int parcela) {

        for (int i = 1; i <= parcela; i++) {

        }

    }

//METODOS DE USO
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        webPanel1 = new com.alee.laf.panel.WebPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_tabela = new com.alee.laf.table.WebTable();
        webLabel1 = new com.alee.laf.label.WebLabel();
        webLabel2 = new com.alee.laf.label.WebLabel();
        webLabel3 = new com.alee.laf.label.WebLabel();
        combo_tip_lancamento = new com.alee.laf.combobox.WebComboBox();
        webComboBox2 = new com.alee.laf.combobox.WebComboBox();
        webDateField1 = new com.alee.extended.date.WebDateField();
        Comb_forma_pagamento_recebimento = new com.alee.laf.combobox.WebComboBox();
        webLabel4 = new com.alee.laf.label.WebLabel();
        txtQuantidade = new javax.swing.JTextField();
        webLabel7 = new com.alee.laf.label.WebLabel();
        txt_preco_uni = new javax.swing.JTextField();
        txt_Valor_Total = new javax.swing.JTextField();
        webLabel8 = new com.alee.laf.label.WebLabel();
        txt_busca_nota = new components.TextFieldFK();
        webLabel6 = new com.alee.laf.label.WebLabel();
        webLabel9 = new com.alee.laf.label.WebLabel();
        webLabel10 = new com.alee.laf.label.WebLabel();
        Comb_tip_operacao = new com.alee.laf.combobox.WebComboBox();
        botao_abrir_relatorios = new com.alee.laf.button.WebButton();
        botao_excluir = new com.alee.laf.button.WebButton();
        botao_salvar = new com.alee.laf.button.WebButton();
        webLabel11 = new com.alee.laf.label.WebLabel();
        webLabel12 = new com.alee.laf.label.WebLabel();
        txt_descricao = new javax.swing.JTextField();
        webLabel13 = new com.alee.laf.label.WebLabel();
        webLabel14 = new com.alee.laf.label.WebLabel();
        checkboxEntrada = new com.alee.laf.checkbox.WebCheckBox();
        checkboxEntrada3 = new com.alee.laf.checkbox.WebCheckBox();
        webLabel15 = new com.alee.laf.label.WebLabel();
        txt_data_lançamento = new com.alee.extended.date.WebDateField();
        webLabel16 = new com.alee.laf.label.WebLabel();
        comb_parcelas = new com.alee.laf.spinner.WebSpinner();
        txt_pess_cad = new components.TextFieldFK();
        webLabel17 = new com.alee.laf.label.WebLabel();
        comb_status = new com.alee.laf.combobox.WebComboBox();
        webLabel18 = new com.alee.laf.label.WebLabel();
        webDateField3 = new com.alee.extended.date.WebDateField();
        webLabel19 = new com.alee.laf.label.WebLabel();
        botao_alterar = new com.alee.laf.button.WebButton();
        txt_busca_cliente_fornecedor = new components.TextFieldFK();
        txt_busca_produto = new components.TextFieldFK();
        txt_data_lançamento1 = new com.alee.extended.date.WebDateField();
        webLabel20 = new com.alee.laf.label.WebLabel();

        webPanel1.setEnabled(false);
        webPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "", "", "", "", ""
            }
        ));
        jScrollPane2.setViewportView(txt_tabela);

        webPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(636, 100, 610, 440));

        webLabel1.setText("Lançamento tipo:");
        webPanel1.add(webLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 24, -1, 22));

        webLabel2.setText("Quantidade:");
        webPanel1.add(webLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, -1, 22));

        webLabel3.setText("Cliente/Fornecedor:");
        webPanel1.add(webLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(249, 20, -1, 22));

        combo_tip_lancamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ENTRADA", "SAÍDA" }));
        combo_tip_lancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_tip_lancamentoActionPerformed(evt);
            }
        });
        webPanel1.add(combo_tip_lancamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 16, 109, 30));

        webComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dinheiro/Cheque", "Via Banco" }));
        webPanel1.add(webComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, 210, 32));

        webDateField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webDateField1ActionPerformed(evt);
            }
        });
        webPanel1.add(webDateField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 60, 102, -1));

        Comb_forma_pagamento_recebimento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "À Vista", "À prazo", " " }));
        Comb_forma_pagamento_recebimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comb_forma_pagamento_recebimentoActionPerformed(evt);
            }
        });
        webPanel1.add(Comb_forma_pagamento_recebimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 250, 210, 32));

        webLabel4.setText("Parcelas:");
        webPanel1.add(webLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 250, -1, 22));

        txtQuantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantidadeActionPerformed(evt);
            }
        });
        webPanel1.add(txtQuantidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, 96, -1));

        webLabel7.setText("Status:");
        webPanel1.add(webLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 50, -1, 43));
        webPanel1.add(txt_preco_uni, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 340, 90, 30));

        txt_Valor_Total.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_Valor_TotalMouseClicked(evt);
            }
        });
        txt_Valor_Total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_Valor_TotalActionPerformed(evt);
            }
        });
        webPanel1.add(txt_Valor_Total, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 80, 31));

        webLabel8.setText("Valor unitário:");
        webPanel1.add(webLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, -1, 28));

        txt_busca_nota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_busca_notaActionPerformed(evt);
            }
        });
        webPanel1.add(txt_busca_nota, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 120, 90, 33));

        webLabel6.setText("Vincular NF:");
        webPanel1.add(webLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 120, -1, 22));

        webLabel9.setText("Produto:");
        webPanel1.add(webLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, -1, 22));

        webLabel10.setText("Tipo de operação:");
        webPanel1.add(webLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 72, -1, 28));

        Comb_tip_operacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comb_tip_operacaoActionPerformed(evt);
            }
        });
        webPanel1.add(Comb_tip_operacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 72, 160, 28));

        botao_abrir_relatorios.setBackground(new java.awt.Color(51, 255, 51));
        botao_abrir_relatorios.setText("Relatórios");
        botao_abrir_relatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_abrir_relatoriosActionPerformed(evt);
            }
        });
        webPanel1.add(botao_abrir_relatorios, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 480, 120, 33));

        botao_excluir.setBackground(new java.awt.Color(51, 255, 51));
        botao_excluir.setText("Excluir");
        webPanel1.add(botao_excluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, 120, 33));

        botao_salvar.setBackground(new java.awt.Color(51, 255, 51));
        botao_salvar.setText("Salvar");
        botao_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_salvarActionPerformed(evt);
            }
        });
        webPanel1.add(botao_salvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 480, 130, 33));

        webLabel11.setText("Forma de pagamento/Recebimento:");
        webPanel1.add(webLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, 22));

        webLabel12.setText("Descrição:");
        webPanel1.add(webLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, 22));

        txt_descricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_descricaoActionPerformed(evt);
            }
        });
        webPanel1.add(txt_descricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 490, 26));

        webLabel13.setText("Data lançamento:");
        webPanel1.add(webLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, 22));

        webLabel14.setText("Meio de pagamento/Recebimento:");
        webPanel1.add(webLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, 22));

        checkboxEntrada.setText("Pendente");
        checkboxEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxEntradaActionPerformed(evt);
            }
        });
        webPanel1.add(checkboxEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 60, -1, -1));

        checkboxEntrada3.setText("Pago");
        webPanel1.add(checkboxEntrada3, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 60, -1, -1));

        webLabel15.setText("Valor total:");
        webPanel1.add(webLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 340, -1, 23));

        txt_data_lançamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_data_lançamentoActionPerformed(evt);
            }
        });
        webPanel1.add(txt_data_lançamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 111, -1));

        webLabel16.setText("Data Inicial:");
        webPanel1.add(webLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 60, -1, 22));

        comb_parcelas.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        webPanel1.add(comb_parcelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 250, 100, 32));

        txt_pess_cad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_pess_cadActionPerformed(evt);
            }
        });
        webPanel1.add(txt_pess_cad, new org.netbeans.lib.awtextra.AbsoluteConstraints(755, 16, 490, 30));

        webLabel17.setText("Cliente/Fornecedor:");
        webPanel1.add(webLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(641, 20, -1, 22));

        comb_status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pago", "Pendente", " " }));
        comb_status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comb_statusActionPerformed(evt);
            }
        });
        webPanel1.add(comb_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, 110, 30));

        webLabel18.setText("Status:");
        webPanel1.add(webLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 300, -1, 22));

        webDateField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webDateField3ActionPerformed(evt);
            }
        });
        webPanel1.add(webDateField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 60, 102, -1));

        webLabel19.setText("Data Final:");
        webPanel1.add(webLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 60, -1, 22));

        botao_alterar.setBackground(new java.awt.Color(51, 255, 51));
        botao_alterar.setText("Alterar");
        webPanel1.add(botao_alterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 480, 130, 33));

        txt_busca_cliente_fornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_busca_cliente_fornecedorActionPerformed(evt);
            }
        });
        webPanel1.add(txt_busca_cliente_fornecedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(349, 14, 220, 34));
        webPanel1.add(txt_busca_produto, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 220, 28));

        txt_data_lançamento1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_data_lançamento1ActionPerformed(evt);
            }
        });
        webPanel1.add(txt_data_lançamento1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 340, 120, 30));

        webLabel20.setText("Data Vencimento:");
        webPanel1.add(webLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 340, -1, 28));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(webPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(webPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void webDateField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webDateField1ActionPerformed

    }//GEN-LAST:event_webDateField1ActionPerformed

    private void Comb_tip_operacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Comb_tip_operacaoActionPerformed

        String b = (String) Comb_tip_operacao.getSelectedItem();


    }//GEN-LAST:event_Comb_tip_operacaoActionPerformed

    private void txtQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantidadeActionPerformed


    }//GEN-LAST:event_txtQuantidadeActionPerformed

    private void txt_descricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_descricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_descricaoActionPerformed

    private void botao_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_salvarActionPerformed
        boolean contareceber = true;

        CarCapContas conta = new CarCapContas();

        if (contareceber) {
            conta.setContaTipo(TipoConta.Entrada);
            conta.setContaDataEmissao(txt_data_lançamento.getDate());
            conta.setProduto(txt_busca_produto.getText());

            double j = Double.parseDouble(txtQuantidade.getText());

            conta.setQuantidade_produto(j);
            conta.setPatNotaFiscal(null);
            conta.setDescricao(txt_descricao.getText());

            // conta.setContaNumParcelas();
            conta.setContaNumParcelas(Integer.parseInt(txtQuantidade.getText()));

            new CarCapContasDAO().insert(conta);
        }


    }//GEN-LAST:event_botao_salvarActionPerformed

    private void txt_notasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_notasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_notasActionPerformed

    private void txt_pess_cad1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pess_cad1ActionPerformed
        String a = (String) Comb_forma_pagamento_recebimento.getSelectedItem();
    }//GEN-LAST:event_txt_pess_cad1ActionPerformed

    private void txt_data_lançamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_data_lançamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_data_lançamentoActionPerformed

    private void txt_pess_cadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pess_cadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_pess_cadActionPerformed

    private void Comb_forma_pagamento_recebimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Comb_forma_pagamento_recebimentoActionPerformed

        String b = (String) Comb_forma_pagamento_recebimento.getSelectedItem();

        if (b.equals("À Vista")) {

            comb_parcelas.setEnabled(false);
        } else {
            comb_parcelas.setEnabled(true);

        }


    }//GEN-LAST:event_Comb_forma_pagamento_recebimentoActionPerformed

    private void comb_statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comb_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comb_statusActionPerformed

    private void checkboxEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxEntradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkboxEntradaActionPerformed

    private void webDateField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webDateField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_webDateField3ActionPerformed

    private void botao_abrir_relatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_abrir_relatoriosActionPerformed
        FormRelatorios_em_tela r = new FormRelatorios_em_tela();

        r.setVisible(true);


    }//GEN-LAST:event_botao_abrir_relatoriosActionPerformed

    private void txt_Valor_TotalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_Valor_TotalMouseClicked
        String b = txtQuantidade.getText();

        b = b.replace(",", "."); //Isso precisa ter pois se o cara digitar o numero com virgula vai dar pau no double

        double q = FormataValor(Double.parseDouble(b));

        String c = txt_preco_uni.getText();

        c = c.replace(",", "."); //Isso precisa ter pois se o cara digitar o numero com virgula vai dar pau no double

        double u = FormataValor(Double.parseDouble(c));

        double soma = q * u;

        String Soma = "" + soma;

        txt_Valor_Total.setText(Soma);


    }//GEN-LAST:event_txt_Valor_TotalMouseClicked

    private void txt_Valor_TotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_Valor_TotalActionPerformed

    }//GEN-LAST:event_txt_Valor_TotalActionPerformed

    private void combo_tip_lancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_tip_lancamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_tip_lancamentoActionPerformed

    private void txt_busca_notaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_busca_notaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_busca_notaActionPerformed

    private void txt_data_lançamento1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_data_lançamento1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_data_lançamento1ActionPerformed

    private void txt_busca_cliente_fornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_busca_cliente_fornecedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_busca_cliente_fornecedorActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FormContaReceber.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FormContaReceber.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FormContaReceber.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FormContaReceber.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FormContaReceber().setVisible(true);
//            }
//        });
//    }
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {

            WebLookAndFeel.setDecorateAllWindows(true);
            WebLookAndFeel.setDecorateDialogs(true);
            WebLookAndFeel.setDecorateFrames(true);

            LanguageManager.setDefaultLanguage(LanguageManager.PORTUGUESE);

            WebLookAndFeel.install();

            FormContaReceber fluxo = new FormContaReceber();
            fluxo.setVisible(true);

        });
    }

    private static double FormataValor(Double valor) {

        String pattern = "###.##";

        DecimalFormat dm = new DecimalFormat(pattern);

        String str = dm.format(valor);

        return Float.parseFloat(str.replace(',', '.'));

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.combobox.WebComboBox Comb_forma_pagamento_recebimento;
    private com.alee.laf.combobox.WebComboBox Comb_tip_operacao;
    private com.alee.laf.button.WebButton botao_abrir_relatorios;
    private com.alee.laf.button.WebButton botao_alterar;
    private com.alee.laf.button.WebButton botao_excluir;
    private com.alee.laf.button.WebButton botao_salvar;
    private com.alee.laf.checkbox.WebCheckBox checkboxEntrada;
    private com.alee.laf.checkbox.WebCheckBox checkboxEntrada3;
    private com.alee.laf.spinner.WebSpinner comb_parcelas;
    private com.alee.laf.combobox.WebComboBox comb_status;
    private com.alee.laf.combobox.WebComboBox combo_tip_lancamento;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JTextField txt_Valor_Total;
    private components.TextFieldFK txt_busca_cliente_fornecedor;
    private components.TextFieldFK txt_busca_nota;
    private components.TextFieldFK txt_busca_produto;
    private com.alee.extended.date.WebDateField txt_data_lançamento;
    private com.alee.extended.date.WebDateField txt_data_lançamento1;
    private javax.swing.JTextField txt_descricao;
    private components.TextFieldFK txt_pess_cad;
    private javax.swing.JTextField txt_preco_uni;
    private com.alee.laf.table.WebTable txt_tabela;
    private com.alee.laf.combobox.WebComboBox webComboBox2;
    private com.alee.extended.date.WebDateField webDateField1;
    private com.alee.extended.date.WebDateField webDateField3;
    private com.alee.laf.label.WebLabel webLabel1;
    private com.alee.laf.label.WebLabel webLabel10;
    private com.alee.laf.label.WebLabel webLabel11;
    private com.alee.laf.label.WebLabel webLabel12;
    private com.alee.laf.label.WebLabel webLabel13;
    private com.alee.laf.label.WebLabel webLabel14;
    private com.alee.laf.label.WebLabel webLabel15;
    private com.alee.laf.label.WebLabel webLabel16;
    private com.alee.laf.label.WebLabel webLabel17;
    private com.alee.laf.label.WebLabel webLabel18;
    private com.alee.laf.label.WebLabel webLabel19;
    private com.alee.laf.label.WebLabel webLabel2;
    private com.alee.laf.label.WebLabel webLabel20;
    private com.alee.laf.label.WebLabel webLabel3;
    private com.alee.laf.label.WebLabel webLabel4;
    private com.alee.laf.label.WebLabel webLabel6;
    private com.alee.laf.label.WebLabel webLabel7;
    private com.alee.laf.label.WebLabel webLabel8;
    private com.alee.laf.label.WebLabel webLabel9;
    private com.alee.laf.panel.WebPanel webPanel1;
    // End of variables declaration//GEN-END:variables
}
