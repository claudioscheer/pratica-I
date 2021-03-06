package components.panelsCads;

import com.alee.laf.optionpane.WebOptionPane;
import com.alee.laf.panel.WebPanel;
import components.Validador;
import forms.FormPrincipal;
import forms.busca.FormBuscaItemNota;
import forms.busca.FormBuscaNotaFiscal;
import forms.busca.FormBuscaPessoa;
import forms.patrimonio.FormAddAtivoNotaFiscal;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import model.CarPessoa;
import model.PatAtivoImobilizado;
import model.PatItemNota;
import model.PatNotaFiscal;
import utils.Utils;

public class PanelCadNotaFiscal extends WebPanel {

    private Map<Integer, PatAtivoImobilizado> ativos;
    public PatNotaFiscal notaFiscal;
    public Validador validador;
    private int indexEditandoItemNota;
    public boolean editando;

    public Frame frameBloquear;

    public PanelCadNotaFiscal(Frame frameBloquear) {
        this.frameBloquear = frameBloquear;
    }

    public void init() {
        this.initComponents();

        this.ativos = new HashMap<>();
        this.notaFiscal = new PatNotaFiscal();

        this.tabelaProdutos.getModel().addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();
            if (column == 5) {
                TableModel model = (TableModel) e.getSource();
                Boolean checked = (Boolean) model.getValueAt(row, column);
                if (checked) {
                    this.abrirFormAddAtivo(row);
                } else if (WebOptionPane.showConfirmDialog(null, "Não controlar o item?", "Remover ativo",
                        WebOptionPane.YES_NO_OPTION,
                        WebOptionPane.QUESTION_MESSAGE) == WebOptionPane.OK_OPTION) {
                    this.ativos.remove(row);
                }
            }
        });

        this.tabelaProdutos.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent me) {
                JTable table = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    editarItemNota(table.getSelectedRow());
                }
            }
        });

        FormBuscaPessoa f = new FormBuscaPessoa();
        f.setFrameBloquear(this.frameBloquear);
        this.txtFornecedor.setFrame(f);

        this.txtDataEmissao.setDateFormat(Utils.formatoDataPadrao);
        this.txtDataEntrada.setDateFormat(Utils.formatoDataPadrao);
        this.txtDataEntrada.setDate(new Date());

        this.scrollCadastro.getVerticalScrollBar().setUnitIncrement(20);

        this.validador = new Validador(Validador.TipoValidator.ICONE);
        this.validador.addObrigatorioValidator(this.txtChaveAcesso);
        this.validador.addApenasNumeroValidator(this.txtChaveAcesso);
        this.validador.addMaxLengthValidator(this.txtChaveAcesso, 44);
        this.validador.addMinLengthValidator(this.txtChaveAcesso, 44);
        this.validador.addObrigatorioValidator(this.txtDescricao);
        this.validador.addApenasNumeroValidator(this.txtValor);
        this.validador.addObrigatorioValidator(this.txtFornecedor);
        this.validador.addObrigatorioValidator(this.txtDataEmissao);
        this.validador.addObrigatorioValidator(this.txtDataEntrada);
    }

    public void setDadosEditar(PatNotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
        this.editando = true;
        TableColumnModel tcm = this.tabelaProdutos.getColumnModel();
        tcm.removeColumn(tcm.getColumn(5));
        this.setDadosForm();
    }

    private void abrirFormAddAtivo(int linha) {
        FormAddAtivoNotaFiscal form = new FormAddAtivoNotaFiscal();
        form.panelCadastroAtivoImobilizado.setEnableItemNota(false);
        form.panelCadastroAtivoImobilizado.setEvents((e) -> {
            if (!form.panelCadastroAtivoImobilizado.validador.isValid()) {
                return;
            }

            PatAtivoImobilizado ativo = form.panelCadastroAtivoImobilizado.getAtivoImobilizado();
            ativo.setPatItemNota(this.notaFiscal.getPatItemNotas().get(linha));
            this.ativos.put(linha, ativo);
            form.dispose();
        }, (e) -> {
            form.dispose();
        });
        FormPrincipal.getInstance().setEnabled(false);
        form.setVisible(true);
    }

    private void setDadosForm() {
        this.txtDescricao.setText(this.notaFiscal.getNotaDescricao());
        this.txtChaveAcesso.setText(this.notaFiscal.getNotaChaveAcesso());
        this.txtValor.setValue(this.notaFiscal.getNotaValor());
        if (this.notaFiscal.getCarPessoa() != null) {
            this.txtFornecedor.setText(this.notaFiscal.getCarPessoa().getPessoaId() + " - " + this.notaFiscal.getCarPessoa().getPessoaNome());
            this.txtFornecedor.setValue(this.notaFiscal.getCarPessoa());
        }
        this.txtDataEmissao.setDate(this.notaFiscal.getNotaDataEmissao());
        this.txtDataEntrada.setDate(this.notaFiscal.getNotaDataEntrada());
        this.atualizarItensNota();
    }

    public void setEvents(ActionListener salvar, ActionListener cancelar) {
        this.btnSalvar.addActionListener(salvar);
        this.btnCancelar.addActionListener(cancelar);
    }

    public Map<Integer, PatAtivoImobilizado> getAtivos() {
        return this.ativos;
    }

    public PatNotaFiscal getNotaFiscal() {
        this.notaFiscal.setNotaChaveAcesso(this.txtChaveAcesso.getText());
        this.notaFiscal.setNotaDataEmissao(this.txtDataEmissao.getDate());
        this.notaFiscal.setNotaDataEntrada(this.txtDataEntrada.getDate());
        this.notaFiscal.setCarPessoa((CarPessoa) this.txtFornecedor.getValue());
        this.notaFiscal.setNotaDescricao(this.txtDescricao.getText());
        this.notaFiscal.setNotaValor(this.txtValor.getValue());
        this.notaFiscal.setNotaAtiva(true);

        TableModel model = (TableModel) tabelaProdutos.getModel();
        for (int i = 0; i < this.notaFiscal.getPatItemNotas().size(); i++) {
            this.notaFiscal.getPatItemNotas().get(i).setItemNotaOrdem(i + 1);
            this.notaFiscal.getPatItemNotas().get(i).setItemNotacontrolavel((Boolean) model.getValueAt(i, 5));
        }

        return this.notaFiscal;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollCadastro = new javax.swing.JScrollPane();
        panelItens = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtFornecedor = new components.TextFieldFK();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtDataEmissao = new com.alee.extended.date.WebDateField();
        txtDataEntrada = new com.alee.extended.date.WebDateField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnAdicionarProduto = new javax.swing.JButton();
        btnRemoverProduto = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        buttonEditar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaProdutos = new com.alee.laf.table.WebTable();
        txtValor = new components.TextFieldValorMonetario();
        txtChaveAcesso = new com.alee.laf.text.WebTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDescricao = new com.alee.laf.text.WebTextField();
        panelOpcoes = new javax.swing.JPanel();
        btnSalvar = new com.alee.laf.button.WebButton();
        btnCancelar = new com.alee.laf.button.WebButton();

        setMinimumSize(null);

        scrollCadastro.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollCadastro.setMaximumSize(new java.awt.Dimension(0, 0));

        panelItens.setBackground(new java.awt.Color(255, 255, 255));
        panelItens.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelItens.setPreferredSize(new java.awt.Dimension(800, 500));

        jLabel1.setText("Chave acesso");

        jLabel3.setText("Fornecedor");

        jLabel4.setText("Data emissão");

        jLabel5.setText("Data entrada");

        jLabel6.setText("Produtos");

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

        jLabel2.setText("Valor total");

        buttonEditar.setText("Editar");
        buttonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditarActionPerformed(evt);
            }
        });

        tabelaProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Produto", "Qntd.", "Valor unitário", "Valor total", "Controlável"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaProdutos);

        jLabel7.setText("Descrição");

        javax.swing.GroupLayout panelItensLayout = new javax.swing.GroupLayout(panelItens);
        panelItens.setLayout(panelItensLayout);
        panelItensLayout.setHorizontalGroup(
            panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelItensLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelItensLayout.createSequentialGroup()
                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(panelItensLayout.createSequentialGroup()
                                .addGap(492, 492, 492)
                                .addComponent(buttonEditar)
                                .addGap(18, 18, 18)
                                .addComponent(btnRemoverProduto)
                                .addGap(18, 18, 18)
                                .addComponent(btnAdicionarProduto))))
                    .addComponent(jLabel7)
                    .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtDescricao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelItensLayout.createSequentialGroup()
                            .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addGroup(panelItensLayout.createSequentialGroup()
                                    .addComponent(txtChaveAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(59, 59, 59)
                                    .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(48, 48, 48)
                            .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        panelItensLayout.setVerticalGroup(
            panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelItensLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtChaveAcesso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonEditar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAdicionarProduto)
                        .addComponent(btnRemoverProduto)))
                .addContainerGap(59, Short.MAX_VALUE))
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
                .addContainerGap(614, Short.MAX_VALUE)
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
                .addComponent(scrollCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarProdutoActionPerformed
        FormBuscaItemNota f = new FormBuscaItemNota();
        f.setVisible(true);
        f.setFunction(n -> {
            PatItemNota itemNota = (PatItemNota) n;
            this.notaFiscal.addItemNota(-1, itemNota);
            addItemNotaTabela(this.notaFiscal.getPatItemNotas().size() - 1);
        });

        Frame form;
        if (this.getRootPane().getParent() instanceof FormBuscaNotaFiscal) {
            form = (Frame) this.getRootPane().getParent();
        } else {
            form = FormPrincipal.getInstance();
        }
        f.setFrameBloquear(form);
        form.setEnabled(false);
    }//GEN-LAST:event_btnAdicionarProdutoActionPerformed

    private void btnRemoverProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverProdutoActionPerformed
        int linhaselecionada = this.tabelaProdutos.getSelectedRow();
        if (linhaselecionada < 0) {
            Utils.notificacao("Selecione um item!", Utils.TipoNotificacao.erro, 0);
            return;
        }
        this.removerItemNotaTabela(linhaselecionada);
    }//GEN-LAST:event_btnRemoverProdutoActionPerformed

    private void buttonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditarActionPerformed
        int linhaselecionada = this.tabelaProdutos.getSelectedRow();
        if (linhaselecionada < 0) {
            Utils.notificacao("Selecione um item!", Utils.TipoNotificacao.erro, 0);
            return;
        }

        this.editarItemNota(linhaselecionada);
    }//GEN-LAST:event_buttonEditarActionPerformed

    private void editarItemNota(int linhaselecionada) {
        this.indexEditandoItemNota = linhaselecionada;

        FormBuscaItemNota f = new FormBuscaItemNota();
        f.setVisible(true);
        f.setDados(this.notaFiscal.getPatItemNotas().get(linhaselecionada));
        f.setFunction(n -> {
            PatItemNota nota = (PatItemNota) n;
            this.notaFiscal.removeItemNota(this.indexEditandoItemNota);
            this.notaFiscal.addItemNota(this.indexEditandoItemNota, nota);
            this.atualizarItensNota();
        });

        FormPrincipal form = FormPrincipal.getInstance();
        f.setFrameBloquear(form);
        form.setEnabled(false);
    }

    private void recalcularValorTotal() {
        double valortotal = 0;
        for (PatItemNota itemNota : this.notaFiscal.getPatItemNotas()) {
            valortotal += itemNota.getItemNotaValorTotal();
        }

        this.txtValor.setValue(valortotal);
    }

    private void addItemNotaTabela(int index) {
        PatItemNota item = this.notaFiscal.getPatItemNotas().get(index);
        DefaultTableModel model = (DefaultTableModel) this.tabelaProdutos.getModel();

        model.addRow(new Object[]{
            index + 1,
            item.getEstProduto().getProdutoDescricao(),
            item.getItemNotaQuantidade(),
            item.getItemNotaValorUnitario(),
            item.getItemNotaValorTotal(),
            false
        });
        this.recalcularValorTotal();
    }

    private void atualizarItensNota() {
        DefaultTableModel model = (DefaultTableModel) this.tabelaProdutos.getModel();
        Utils.clearTableModel(model);
        for (int i = 0; i < this.notaFiscal.getPatItemNotas().size(); i++) {
            this.addItemNotaTabela(i);
        }
    }

    private void removerItemNotaTabela(int index) {
        this.notaFiscal.removeItemNota(index);
        ((DefaultTableModel) this.tabelaProdutos.getModel()).removeRow(index);
        Utils.notificacao("Removido!", Utils.TipoNotificacao.ok, 0);
        this.atualizarItensNota();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarProduto;
    private com.alee.laf.button.WebButton btnCancelar;
    private javax.swing.JButton btnRemoverProduto;
    private com.alee.laf.button.WebButton btnSalvar;
    private javax.swing.JButton buttonEditar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelItens;
    private javax.swing.JPanel panelOpcoes;
    private javax.swing.JScrollPane scrollCadastro;
    private com.alee.laf.table.WebTable tabelaProdutos;
    private com.alee.laf.text.WebTextField txtChaveAcesso;
    private com.alee.extended.date.WebDateField txtDataEmissao;
    private com.alee.extended.date.WebDateField txtDataEntrada;
    private com.alee.laf.text.WebTextField txtDescricao;
    private components.TextFieldFK txtFornecedor;
    private components.TextFieldValorMonetario txtValor;
    // End of variables declaration//GEN-END:variables
}
