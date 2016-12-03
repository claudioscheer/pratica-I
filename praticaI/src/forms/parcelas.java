/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import dao.CarCapContasDAO;
import enumeraveis.FormaPagamento;
import enumeraveis.StatusConta;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.CarCapContas;
import model.CarcapOperacoesComerciais;
import utils.Utils;

/**
 *
 * @author Marcos
 */
public class parcelas extends javax.swing.JFrame {

    /**
     * Creates new form parcelas
     */
    
    private int codigoParcela;
    
    public parcelas(int codigoParcela) {
        
        this.codigoParcela = codigoParcela;
        
        initComponents();
        this.setLocationRelativeTo(null);
       filtraParcelas(this.codigoParcela);
    }
    FormContaReceber lin = new FormContaReceber();
    
// int a = lin.RetornarLinhaSelecionada(0);

  
    
    public int RetornarLinhaSelecionada(int posicao) {
        int valor = Integer.parseInt(tabela_parcelas.getValueAt(tabela_parcelas.getSelectedRow(), posicao).toString());
        return valor;
    }
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela_parcelas = new com.alee.laf.table.WebTable();
        webButton1 = new com.alee.laf.button.WebButton();
        webButton2 = new com.alee.laf.button.WebButton();
        webButton3 = new com.alee.laf.button.WebButton();
        fieldValorParcela = new components.TextFieldValorMonetario();
        fieldValorRecebido = new components.TextFieldValorMonetario();
        webLabel1 = new com.alee.laf.label.WebLabel();
        webLabel2 = new com.alee.laf.label.WebLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabela_parcelas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "N° Movimento", "Parcelas", "Data", "Produto", "Quantidade", "Valor Pendente ", "Status"
            }
        ));
        tabela_parcelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabela_parcelasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabela_parcelas);
        if (tabela_parcelas.getColumnModel().getColumnCount() > 0) {
            tabela_parcelas.getColumnModel().getColumn(0).setMinWidth(5);
            tabela_parcelas.getColumnModel().getColumn(0).setPreferredWidth(5);
            tabela_parcelas.getColumnModel().getColumn(0).setMaxWidth(8);
            tabela_parcelas.getColumnModel().getColumn(1).setMinWidth(10);
            tabela_parcelas.getColumnModel().getColumn(1).setPreferredWidth(10);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, 949, -1));

        webButton1.setText("Pagar");
        webButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(webButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 331, 118, 33));

        webButton2.setText("S/Função ");
        webButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(webButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(738, 331, 98, 33));

        webButton3.setText("s/Função");
        getContentPane().add(webButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(501, 331, 97, 33));

        fieldValorParcela.setEditable(false);
        fieldValorParcela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldValorParcelaActionPerformed(evt);
            }
        });
        getContentPane().add(fieldValorParcela, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 167, 30));
        getContentPane().add(fieldValorRecebido, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, 160, 30));

        webLabel1.setText("Valor a Pagar");
        getContentPane().add(webLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        webLabel2.setText("Valor Pago");
        getContentPane().add(webLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void webButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_webButton2ActionPerformed

    private void webButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webButton1ActionPerformed
         int pagaConta = RetornarLinhaSelecionada(0);
        CarCapContasDAO pagar = new CarCapContasDAO();
        CarCapContas altera = pagar.BuscarContasId(pagaConta);
   //     altera.setForma_rece_pagamento(FormaPagamento.values()[Comb_forma_pagamento_recebimento.getSelectedIndex()]);
     //   altera.setDescricao(txt_descricao.getText());
        double valorParcela = fieldValorParcela.getValue();
        // altera.setContaValorPago(fieldValorRecebido.getValue());
        double valorecebido = fieldValorRecebido.getValue();
        double valor_pendete = valorParcela - valorecebido;
        
        
        
        altera.setValorRecebido(valor_pendete);
        if (valor_pendete == 0) {
            altera.setCapContaStatus(StatusConta.Quitada);
        } else if (valor_pendete < valorParcela) {
            altera.setCapContaStatus(StatusConta.PendenteEmser);
        } else if (valor_pendete >= valorParcela) {
            altera.setCapContaStatus(StatusConta.PendenteEmser);
        }

//        int value = comb_status.getSelectedIndex();
//        altera.setCapContaStatus(StatusConta.values()[value]);
//        if (value == 1 && valorecebido < valortotal) {
//            altera.setCapContaStatus(StatusConta.PendenteParcial);
//            System.out.println("**********************************************");
//        } else if (value == 1 && valorecebido > valortotal) {
//            // acrescentar nos acrecimos
//            altera.setCapContaStatus(StatusConta.Fechada);
//        } else if (value == 1 && valorecebido == valortotal) {
//            altera.setCapContaStatus(StatusConta.Fechada);
//        }
        altera.setContaValorPago(valorecebido);

        //        if (valorecebido < valorParcela) {
        //
        //            altera.setCapContaStatus(StatusConta.Pendente);
        //
        //               // Utils.notificacao("Lançamento Pendente " + String.valueOf(altera.getTipoDeConta()), Utils.TipoNotificacao.ok, 0);
        //        } else if (valorecebido > valorParcela) {
        //
        //                               // Utils.notificacao("VERIFICAR: recebimento maior que a parcela " + String.valueOf(conta.getTipoDeConta()), Utils.TipoNotificacao.ok, 0);
        //            altera.setCapContaStatus(StatusConta.PendenteParcial);
        //
        //        }
        pagar.update(altera);
//        Preenche_tabela();
filtraParcelas(this.codigoParcela);
       
      
    }//GEN-LAST:event_webButton1ActionPerformed

    private void fieldValorParcelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldValorParcelaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldValorParcelaActionPerformed

    private void tabela_parcelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabela_parcelasMouseClicked
       
         int pagaConta = RetornarLinhaSelecionada(0);
        CarCapContasDAO pagar = new CarCapContasDAO();
        CarCapContas pagarValor = pagar.BuscarContasId(pagaConta);
        
        fieldValorParcela.setText(Utils.format(pagarValor.getContaValorPago()));
        fieldValorRecebido.setText(Utils.format(pagarValor.getContaValorPago()));
        
        
        
        
    }//GEN-LAST:event_tabela_parcelasMouseClicked

    public void filtraParcelas(int a){
        
        CarCapContasDAO linha_pacela = new CarCapContasDAO();
        List<CarCapContas> conta_Parcela = linha_pacela.BuscarparcelasId(a);
        
         DefaultTableModel tabelamodelo = (DefaultTableModel) tabela_parcelas.getModel();
        tabelamodelo.setRowCount(0);
        
          for (CarCapContas j : conta_Parcela) {

            tabelamodelo.addRow(new Object[]{
                  j.getContaId(), j.getCarcapOperacoesComerciais().getOperacoesID(),
                j.getContaNumParcelas(), j.getContaDataEmissao(), j.getProduto().getProdutoDescricao(),
                j.getQuantidade_produto(),j.getContaValorPago(), j.getCapContaStatus()});

        }

        tabela_parcelas.setModel(tabelamodelo);

       
    
        
    }
    
        
            public void Preenche_tabela() {

        CarCapContasDAO retorn_valores = new CarCapContasDAO();
        List<CarCapContas> conta = retorn_valores.getAll();
//        DefaultTableModel tabelamodelo = new DefaultTableModel();
        DefaultTableModel tabelamodelo = (DefaultTableModel) tabela_parcelas.getModel();
        tabelamodelo.setRowCount(0);
//        tabelamodelo.addColumn("ID");
//        tabelamodelo.addColumn("Lançamento");
//        tabelamodelo.addColumn("Parcelas");
//        tabelamodelo.addColumn("D. Vencimento");
//        tabelamodelo.addColumn("Produto");
//        tabelamodelo.addColumn("Quantidade");
//        tabelamodelo.addColumn("Status");
//        tabelamodelo.addColumn("V. Parcela");
//        tabelamodelo.addColumn("V. Pago");

        for (CarCapContas j : conta) {

            tabelamodelo.addRow(new Object[]{
                j.getContaId(), j.getCarcapOperacoesComerciais().getOperacoesID(),
                j.getContaNumParcelas(), j.getContaDataEmissao(), j.getProduto().getProdutoDescricao(),
                j.getQuantidade_produto(), j.getContaValorPago(), j.getValorRecebido(), j.getCapContaStatus()});

        }

        tabela_parcelas.setModel(tabelamodelo);

    
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.TextFieldValorMonetario fieldValorParcela;
    private components.TextFieldValorMonetario fieldValorRecebido;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private com.alee.laf.table.WebTable tabela_parcelas;
    private com.alee.laf.button.WebButton webButton1;
    private com.alee.laf.button.WebButton webButton2;
    private com.alee.laf.button.WebButton webButton3;
    private com.alee.laf.label.WebLabel webLabel1;
    private com.alee.laf.label.WebLabel webLabel2;
    // End of variables declaration//GEN-END:variables
}
