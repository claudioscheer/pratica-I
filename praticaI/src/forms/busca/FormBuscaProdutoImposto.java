package forms.busca;

import components.JFrameBusca;
import components.Validador;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import model.Imposto;
import model.ImpostoItemNota;
import model.ItemNota;
import model.Produto;
import utils.Utils;

public class FormBuscaProdutoImposto extends JFrameBusca {

    private List<ImpostoItemNota> impostosItens;
    private Validador validador;

    public FormBuscaProdutoImposto() {
        initComponents();
        this.setLocationRelativeTo(null);

        this.impostosItens = new ArrayList<>();
        this.init();
        this.loadValidator();
    }

    private void init() {

        FormBuscaProduto f = new FormBuscaProduto();
        f.setFrameBloquear(this);
        this.txtProduto.setFrame(f);

    }

    private void loadValidator() {
        this.validador = new Validador(Validador.TipoValidator.ICONE);
        validador.addObrigatorioValidator(txtProduto);
        validador.addObrigatorioValidator(txtQuantidade);
        validador.addObrigatorioValidator(txtValorUnitario);
        validador.addObrigatorioValidator(txtValorTotal);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnFinalizar = new javax.swing.JButton();
        txtProduto = new components.TextFieldFK();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        txtValorUnitario = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaImpostos = new javax.swing.JTable();
        btnRemoverImposto = new javax.swing.JButton();
        btnAdicionarImposto = new javax.swing.JButton();
        txtValorTotal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        btnFinalizar.setText("Finalizar");
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        jLabel1.setText("Produto");

        jLabel3.setText("Quantidade");

        txtQuantidade.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        txtQuantidade.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                txtQuantidadeStateChanged(evt);
            }
        });

        jLabel4.setText("Valor unitário");

        txtValorUnitario.setText("0.0");
        txtValorUnitario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorUnitarioKeyReleased(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel6.setText("Impostos");

        tabelaImpostos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Imposto", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabelaImpostos);

        btnRemoverImposto.setText("Remover");
        btnRemoverImposto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverImpostoActionPerformed(evt);
            }
        });

        btnAdicionarImposto.setText("Adicionar");
        btnAdicionarImposto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarImpostoActionPerformed(evt);
            }
        });

        txtValorTotal.setText("0.0");

        jLabel2.setText("Valor total");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnRemoverImposto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAdicionarImposto))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCancelar)
                                .addGap(18, 18, 18)
                                .addComponent(btnFinalizar))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtProduto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionarImposto)
                    .addComponent(btnRemoverImposto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFinalizar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.getFrameBloquear().setEnabled(true);
    }//GEN-LAST:event_formWindowClosing

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing

    }//GEN-LAST:event_formInternalFrameClosing

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed

        if (!validador.isValid()) {
            return;
        }

        ItemNota n = new ItemNota();
        Produto p = new Produto();
        p.setDescricao("produto");
        n.setProduto(p);
        n.setQuantidade(2);
        n.setValorUnitario(40);
        n.setValorTotal(n.getQuantidade() * n.getValorUnitario());

        Consumer<Object> function = this.getFunction();
        function.accept(n);

        this.getFrameBloquear().setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.getFrameBloquear().setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAdicionarImpostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarImpostoActionPerformed
        FormBuscaImposto f = new FormBuscaImposto();
        f.setVisible(true);
        f.setFunction(n -> {
            Imposto imposto = (Imposto) n;
            addImpostoTabela(imposto);
        });

        f.setFrameBloquear(this);
        this.setEnabled(false);
    }//GEN-LAST:event_btnAdicionarImpostoActionPerformed

    private void btnRemoverImpostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverImpostoActionPerformed
        int linhaselecionada = this.tabelaImpostos.getSelectedRow();
        if (linhaselecionada < 0) {
            Utils.notificacao("Selecione um imposto!", Utils.TipoNotificacao.erro, 0);
            return;
        }
        this.removerImpostoTabela(linhaselecionada);
    }//GEN-LAST:event_btnRemoverImpostoActionPerformed

    private void txtQuantidadeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_txtQuantidadeStateChanged
        this.calculaValorTotal();
    }//GEN-LAST:event_txtQuantidadeStateChanged

    private void txtValorUnitarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorUnitarioKeyReleased
        this.calculaValorTotal();
    }//GEN-LAST:event_txtValorUnitarioKeyReleased

    private void addImpostoTabela(Imposto imposto) {
        ImpostoItemNota item = new ImpostoItemNota();
        item.setImposto(imposto);

        DefaultTableModel model = (DefaultTableModel) this.tabelaImpostos.getModel();
        model.addRow(new Object[]{
            imposto.getNome(),
            imposto.getPorcentagem() * 10
        });

        this.impostosItens.add(item);
    }

    private void removerImpostoTabela(int index) {
        ((DefaultTableModel) this.tabelaImpostos.getModel()).removeRow(index);
        this.impostosItens.remove(index);
        Utils.notificacao("Removido!", Utils.TipoNotificacao.ok, 0);
    }

    private void calculaValorTotal() {
        int quantidade;
        try {
            quantidade = Integer.parseInt(String.valueOf(this.txtQuantidade.getValue()));
        } catch (Exception e) {
            quantidade = 0;
        }

        double valor;
        try {
            valor = Double.parseDouble(txtValorUnitario.getText().replace(",", "."));
        } catch (Exception e) {
            valor = 0;
        }

        double valortotal = quantidade * valor;
        this.txtValorTotal.setText(String.valueOf(valortotal));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarImposto;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnRemoverImposto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabelaImpostos;
    private components.TextFieldFK txtProduto;
    private javax.swing.JSpinner txtQuantidade;
    private javax.swing.JTextField txtValorTotal;
    private javax.swing.JTextField txtValorUnitario;
    // End of variables declaration//GEN-END:variables
}
