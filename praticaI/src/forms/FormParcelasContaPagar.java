package forms;

import components.Validador;
import dao.CarCapContasDAO;
import dao.carcapOperacoesComerciaisDAO;
import enumeraveis.StatusConta;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.CarCapContas;
import model.CarcapOperacoesComerciais;

public class FormParcelasContaPagar extends javax.swing.JFrame {

    private CarcapOperacoesComerciais conta;
    private Validador validador;
    private int parcelaPagando;

    public FormParcelasContaPagar() {
        initComponents();
        setLocationRelativeTo(null);
        this.lblNumeroParcela.setText("0");
        fieldValorAcrescimo.setTextoMonetario(" %");

        this.validador = new Validador(Validador.TipoValidator.ICONE);
        this.validador.addMaiorQueValidator(fieldValorAcrescimo, 0.0);
        this.validador.addMenorQueValidator(fieldValorAcrescimo, 100.0);

        tableLista.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (me.getClickCount() == 1) {
                    JTable table = (JTable) me.getSource();
                    Point p = me.getPoint();
                    int row = table.rowAtPoint(p);

                    if (Boolean.valueOf(((DefaultTableModel) table.getModel()).getValueAt(row, 0).toString())) {
                        return;
                    }

                    parcelaPagando = row;
                    lblNumeroParcela.setText(((DefaultTableModel) table.getModel()).getValueAt(row, 1).toString());
                }
            }
        });

    }

    public void setConta(CarcapOperacoesComerciais conta) {
        this.conta = conta;
    }

    public void BuscarParcelas() {
        fieldValorParcela.setValue(conta.getValorParcela());
        fieldValorTotal.setValue(conta.getValorParcela());
        atualizarParcelas();
    }

    private void atualizarParcelas() {
        DefaultTableModel model = (DefaultTableModel) tableLista.getModel();
        utils.Utils.clearTableModel(model);
        int numero = 1;
        for (CarCapContas parcela : conta.getParcelas()) {
            model.addRow(parcelaToArray(numero++, parcela));
        }
    }

    private Object[] parcelaToArray(int numero, CarCapContas c) {
        Object[] o = new Object[6];
        o[0] = c.getCapContaStatus();
        o[1] = numero;
        o[2] = c.getDataVencimento();
        o[3] = c.getCarcapOperacoesComerciais().getValorTotal();
        o[4] = c.getValorRecebido();
        o[5] = c.getAcrescimo();
        return o;
    }

    private void pagarParcela() {
        if (parcelaPagando < 0) {
            return;
        }

        double saldo = new CarCapContasDAO().Fechamento(Calendar.getInstance().getTime());

        CarCapContas parcela = conta.getParcelas().get(parcelaPagando);
        parcela.setAcrescimo(fieldValorTotal.getValue() - fieldValorParcela.getValue());

        if (conta.getValorParcela() > saldo) {
            utils.Utils.notificacao("Saldo insuficiente", utils.Utils.TipoNotificacao.erro, 5000);
            return;
        }

        if (conta.getValorParcela() == fieldValorTotal.getValue()) {
            parcela.setCapContaStatus(StatusConta.Quitada);
        } else if (conta.getValorParcela() > fieldValorTotal.getValue()) {
            double soma = fieldValorTotal.getValue() + parcela.getValorRecebido();
            if (soma == conta.getValorParcela()) {
                parcela.setCapContaStatus(StatusConta.Quitada);
            } else {
                parcela.setValorRecebido(soma);
            }

            // chamar um JFrame pra escolher o que fazer com o valor pendente
            // se fica como vencido ou se prorroga, criando mais uma parcela depois da última com o valor restante
        }
        parcela.setDataPagamento(Calendar.getInstance().getTime());
        parcela.setValorRecebido(fieldValorTotal.getValue());

        new carcapOperacoesComerciaisDAO().update(conta);
        
      //  new 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableLista = new javax.swing.JTable();
        txtNotaFiscal = new com.alee.laf.label.WebLabel();
        fieldValorParcela = new components.TextFieldValorMonetario();
        fieldValorTotal = new components.TextFieldValorMonetario();
        txtNotaFiscal1 = new com.alee.laf.label.WebLabel();
        txtNotaFiscal2 = new com.alee.laf.label.WebLabel();
        fieldValorAcrescimo = new components.TextFieldValorMonetario();
        btnPagar = new com.alee.laf.button.WebButton();
        txtNotaFiscal3 = new com.alee.laf.label.WebLabel();
        lblNumeroParcela = new com.alee.laf.label.WebLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tableLista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Número", "Quitada?", "Data vencimento", "Valor pago", "Acréscimo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableLista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableListaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableLista);

        txtNotaFiscal.setText("Valor da parcela:");

        fieldValorParcela.setToolTipText("");
        fieldValorParcela.setEnabled(false);
        fieldValorParcela.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fieldValorParcelaKeyReleased(evt);
            }
        });

        fieldValorTotal.setToolTipText("");
        fieldValorTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fieldValorTotalKeyReleased(evt);
            }
        });

        txtNotaFiscal1.setText("Valor a pagar");

        txtNotaFiscal2.setText("Porcentagem acréscimo/desconto (%)");

        fieldValorAcrescimo.setToolTipText("");
        fieldValorAcrescimo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fieldValorAcrescimoKeyReleased(evt);
            }
        });

        btnPagar.setText("Pagar");
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
            }
        });

        txtNotaFiscal3.setText("Você está pagando a parcela:");

        lblNumeroParcela.setText("1");
        lblNumeroParcela.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Desconto");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Acréscimo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtNotaFiscal3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblNumeroParcela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNotaFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldValorParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNotaFiscal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNotaFiscal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldValorAcrescimo, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton2))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(btnPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(157, 157, 157)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNotaFiscal3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNumeroParcela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtNotaFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldValorParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addGap(18, 18, 18)
                .addComponent(txtNotaFiscal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldValorAcrescimo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtNotaFiscal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fieldValorParcelaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldValorParcelaKeyReleased

    }//GEN-LAST:event_fieldValorParcelaKeyReleased

    private void fieldValorTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldValorTotalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldValorTotalKeyReleased

    private void fieldValorAcrescimoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldValorAcrescimoKeyReleased
        fieldValorTotal.setValue(fieldValorParcela.getValue() + (fieldValorParcela.getValue() * (fieldValorAcrescimo.getValue() / 100)));
    }//GEN-LAST:event_fieldValorAcrescimoKeyReleased

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed
        pagarParcela();
        atualizarParcelas();
    }//GEN-LAST:event_btnPagarActionPerformed

    private void tableListaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableListaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableListaMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.button.WebButton btnPagar;
    private javax.swing.ButtonGroup buttonGroup1;
    private components.TextFieldValorMonetario fieldValorAcrescimo;
    private components.TextFieldValorMonetario fieldValorParcela;
    private components.TextFieldValorMonetario fieldValorTotal;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private com.alee.laf.label.WebLabel lblNumeroParcela;
    private javax.swing.JTable tableLista;
    private com.alee.laf.label.WebLabel txtNotaFiscal;
    private com.alee.laf.label.WebLabel txtNotaFiscal1;
    private com.alee.laf.label.WebLabel txtNotaFiscal2;
    private com.alee.laf.label.WebLabel txtNotaFiscal3;
    // End of variables declaration//GEN-END:variables
}
