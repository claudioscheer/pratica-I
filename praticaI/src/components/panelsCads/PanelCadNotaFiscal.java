package components.panelsCads;

import com.alee.laf.optionpane.WebOptionPane;
import com.alee.laf.panel.WebPanel;
import components.Validador;
import forms.FormPrincipal;
import forms.busca.FormBuscaImposto;
import forms.busca.FormBuscaProdutoImposto;
import forms.busca.FormBuscaFornecedor;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Imposto;
import model.ImpostoItemNota;
import model.ImpostoNotaFiscal;
import model.ItemNota;
import model.NotaFiscal;
import utils.Utils;

public class PanelCadNotaFiscal extends WebPanel {

    public Validador validador;
    private List<ItemNota> itensNota;
    private List<ImpostoNotaFiscal> impostosNotaFiscal;

    public PanelCadNotaFiscal() {
    }

    public void init() {
        this.initComponents();

        this.itensNota = new ArrayList<>();
        this.impostosNotaFiscal = new ArrayList<>();

        FormBuscaFornecedor f = new FormBuscaFornecedor();
        f.setFrameBloquear(FormPrincipal.getInstance());
        this.txtFornecedor.setFrame(f);

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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        txtFornecedor = new components.TextFieldFK();
        jLabel3 = new javax.swing.JLabel();
        txtChaveAcesso = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDataEmissao = new com.alee.extended.date.WebDateField();
        txtDataEntrada = new com.alee.extended.date.WebDateField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaProdutos = new javax.swing.JTable();
        btnAdicionarProduto = new javax.swing.JButton();
        btnRemoverProduto = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelaImpostosNotaFiscal = new javax.swing.JTable();
        btnRemoverImpostoNotaFiscal = new javax.swing.JButton();
        btnAdicionarImpostoNotaFiscal = new javax.swing.JButton();
        panelOpcoes = new javax.swing.JPanel();
        btnSalvar = new com.alee.laf.button.WebButton();
        btnCancelar = new com.alee.laf.button.WebButton();

        setMinimumSize(null);

        scrollCadastro.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollCadastro.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollCadastro.setMaximumSize(new java.awt.Dimension(0, 0));

        panelItens.setBackground(new java.awt.Color(255, 255, 255));
        panelItens.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelItens.setPreferredSize(new java.awt.Dimension(0, 1300));

        jLabel1.setText("Chave acesso");

        jLabel2.setText("Valor");

        jLabel3.setText("Fornecedor");

        jLabel4.setText("Data emissão");

        jLabel5.setText("Data entrada");

        jLabel6.setText("Produtos");

        tabelaProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produto", "Qntd.", "Valor Unitário", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabelaProdutos);

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

        jLabel7.setText("Impostos");

        tabelaImpostosNotaFiscal.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tabelaImpostosNotaFiscal);

        btnRemoverImpostoNotaFiscal.setText("Remover");
        btnRemoverImpostoNotaFiscal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverImpostoNotaFiscalActionPerformed(evt);
            }
        });

        btnAdicionarImpostoNotaFiscal.setText("Adicionar");
        btnAdicionarImpostoNotaFiscal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarImpostoNotaFiscalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelItensLayout = new javax.swing.GroupLayout(panelItens);
        panelItens.setLayout(panelItensLayout);
        panelItensLayout.setHorizontalGroup(
            panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelItensLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 755, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 755, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelItensLayout.createSequentialGroup()
                        .addGap(597, 597, 597)
                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelItensLayout.createSequentialGroup()
                                .addComponent(btnRemoverImpostoNotaFiscal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAdicionarImpostoNotaFiscal))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelItensLayout.createSequentialGroup()
                                .addComponent(btnRemoverProduto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAdicionarProduto))))
                    .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtChaveAcesso, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtFornecedor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelItensLayout.createSequentialGroup()
                            .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addGap(18, 18, 18)
                            .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4))
                            .addGap(18, 18, 18)
                            .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelItensLayout.setVerticalGroup(
            panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelItensLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtChaveAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel4)
                        .addComponent(jLabel2))
                    .addGroup(panelItensLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionarProduto)
                    .addComponent(btnRemoverProduto))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionarImpostoNotaFiscal)
                    .addComponent(btnRemoverImpostoNotaFiscal))
                .addContainerGap(56, Short.MAX_VALUE))
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
                .addComponent(scrollCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarProdutoActionPerformed
        FormBuscaProdutoImposto f = new FormBuscaProdutoImposto();
        f.setVisible(true);
        f.setFunction(n -> {
            ItemNota nota = (ItemNota) n;
            this.itensNota.add(nota);
            addItemNotaTabela();
        });

        FormPrincipal form = FormPrincipal.getInstance();
        f.setFrameBloquear(form);
        form.setEnabled(false);
    }//GEN-LAST:event_btnAdicionarProdutoActionPerformed

    private void btnRemoverProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverProdutoActionPerformed
        int linhaselecionada = this.tabelaProdutos.getSelectedRow();
        if (linhaselecionada < 0) {
            Utils.notificacao("Selecione um produto!", Utils.TipoNotificacao.erro, 0);
            return;
        }
        this.removerItemNotaTabela(linhaselecionada);
    }//GEN-LAST:event_btnRemoverProdutoActionPerformed

    private void btnAdicionarImpostoNotaFiscalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarImpostoNotaFiscalActionPerformed
        FormBuscaImposto f = new FormBuscaImposto();
        f.setVisible(true);
        f.setFunction(n -> {
            Imposto imposto = (Imposto) n;
            addImpostoNota(imposto);
        });

        FormPrincipal form = FormPrincipal.getInstance();
        f.setFrameBloquear(form);
        form.setEnabled(false);
    }//GEN-LAST:event_btnAdicionarImpostoNotaFiscalActionPerformed

    private void btnRemoverImpostoNotaFiscalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverImpostoNotaFiscalActionPerformed
        int linhaselecionada = this.tabelaImpostosNotaFiscal.getSelectedRow();
        if (linhaselecionada < 0) {
            Utils.notificacao("Selecione um imposto!", Utils.TipoNotificacao.erro, 0);
            return;
        }
        this.removerImpostoNota(linhaselecionada);
    }//GEN-LAST:event_btnRemoverImpostoNotaFiscalActionPerformed

    private void addImpostoNota(Imposto imposto) {

        if (this.impostosNotaFiscal.stream().filter(x -> x.getImposto().getImposto() == imposto.getImposto()).count() > 0) {
            Utils.notificacao("Imposto já adicionado!", Utils.TipoNotificacao.erro, 0);
            return;
        }

        ImpostoNotaFiscal item = new ImpostoNotaFiscal();
        item.setImposto(imposto);
        item.setValor(100);
        DefaultTableModel model = (DefaultTableModel) this.tabelaImpostosNotaFiscal.getModel();
        model.addRow(new Object[]{
            imposto.getNome(),
            item.getValor()
        });

        this.impostosNotaFiscal.add(item);
    }

    private void removerImpostoNota(int index) {
        ((DefaultTableModel) this.tabelaImpostosNotaFiscal.getModel()).removeRow(index);
        this.impostosNotaFiscal.remove(index);
        Utils.notificacao("Removido!", Utils.TipoNotificacao.ok, 0);
    }

    private void addItemNotaTabela() {
        ItemNota item = this.itensNota.get(this.itensNota.size() - 1);
        DefaultTableModel model = (DefaultTableModel) this.tabelaProdutos.getModel();
        model.addRow(new Object[]{
            item.getProduto().getNome(),
            item.getQuantidade(),
            item.getValorUnitario(),
            item.getValorTotal()
        });
    }

    private void removerItemNotaTabela(int index) {
        ((DefaultTableModel) this.tabelaProdutos.getModel()).removeRow(index);
        this.itensNota.remove(index);
        Utils.notificacao("Removido!", Utils.TipoNotificacao.ok, 0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarImpostoNotaFiscal;
    private javax.swing.JButton btnAdicionarProduto;
    private com.alee.laf.button.WebButton btnCancelar;
    private javax.swing.JButton btnRemoverImpostoNotaFiscal;
    private javax.swing.JButton btnRemoverProduto;
    private com.alee.laf.button.WebButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel panelItens;
    private javax.swing.JPanel panelOpcoes;
    private javax.swing.JScrollPane scrollCadastro;
    private javax.swing.JTable tabelaImpostosNotaFiscal;
    private javax.swing.JTable tabelaProdutos;
    private javax.swing.JTextField txtChaveAcesso;
    private com.alee.extended.date.WebDateField txtDataEmissao;
    private com.alee.extended.date.WebDateField txtDataEntrada;
    private components.TextFieldFK txtFornecedor;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
