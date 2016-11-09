package components.panelsCads;

import com.alee.laf.panel.WebPanel;
import components.Validador;
import forms.FormPrincipal;
import forms.busca.FormBuscaCategoria;
import forms.busca.FormBuscaMarca;
import forms.busca.FormBuscaNotaFiscal;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import model.EstCategoria;
import model.EstMarca;
import model.EstadoBem;
import model.PatAtivoImobilizado;
import model.PatItemNota;
import model.PatNotaFiscal;
import model.UtilizacaoBem;

public class PanelCadAtivoImobilizado extends WebPanel {

    public Validador validador;
    private PatAtivoImobilizado ativoImobilizado;

    private PatNotaFiscal notaFiscal;

    public boolean editando;

    public PanelCadAtivoImobilizado() {
    }

    public void setDadosEditar(PatAtivoImobilizado ativoImobilizado) {
        this.ativoImobilizado = ativoImobilizado;
        this.editando = true;
        this.setDadosForm();
    }

    private void setDadosForm() {
        this.txtDescricao.setText(this.ativoImobilizado.getAtivoDescricao());
        this.txtCategoria.setValue(this.ativoImobilizado.getEstCategoria());
        this.txtCategoria.setText(this.ativoImobilizado.getEstCategoria().getCategoriaDescricao()
                + " - " + this.ativoImobilizado.getEstCategoria().getCategoriaId());
        this.txtMarca.setValue(this.ativoImobilizado.getEstMarca());
        this.txtMarca.setText(this.ativoImobilizado.getEstMarca().getMarcaId()
                + " - " + this.ativoImobilizado.getEstMarca().getMarcaDescricao());
        this.txtValorOriginal.setValue(this.ativoImobilizado.getAtivoValorOriginal());
        this.txtValorAtual.setValue(this.ativoImobilizado.getAtivoValorAtual());
        this.txtTaxaValorResidual.setValue(this.ativoImobilizado.getAtivoTaxaValorResidual());
        this.txtValorResidual.setValue(this.ativoImobilizado.getAtivoValorResidual());
        this.comboEstadoBem.setSelectedIndex(this.ativoImobilizado.getAtivoEstadoBem().ordinal());
        this.comboUtilizacaoBem.setSelectedIndex(this.ativoImobilizado.getAtivoUtilizacao().ordinal());
        this.cbAtivoDepreciavel.setSelected(this.ativoImobilizado.isAtivoDepreciavel());

        if (this.ativoImobilizado.getPatItemNota() != null) {
            this.txtNotaFiscal.setText(this.ativoImobilizado.getPatItemNota().getPatNotaFiscal().getNotaCodigo()
                    + " - " + this.ativoImobilizado.getPatItemNota().getPatNotaFiscal().getNotaChaveAcesso());
            this.txtNotaFiscal.setValue(this.ativoImobilizado.getPatItemNota().getPatNotaFiscal());
            this.notaFiscal = this.ativoImobilizado.getPatItemNota().getPatNotaFiscal();
            this.recarregarItensNota();

            this.comboItemNota.setSelectedIndex(this.ativoImobilizado.getPatItemNota().getItemNotaOrdem() - 1);
        }
    }

    public void init() {
        this.initComponents();

        this.validador = new Validador(Validador.TipoValidator.ICONE);
        this.validador.addObrigatorioValidator(this.txtDescricao);
        this.validador.addObrigatorioValidator(this.txtCategoria);
        this.validador.addObrigatorioValidator(this.txtMarca);
        this.validador.addObrigatorioValidator(this.txtValorOriginal);
        this.validador.addApenasNumeroValidator(this.txtValorOriginal);
        this.validador.addObrigatorioValidator(this.txtValorAtual);
        this.validador.addApenasNumeroValidator(this.txtValorAtual);
        this.validador.addObrigatorioValidator(this.txtTaxaValorResidual);
        this.validador.addApenasNumeroValidator(this.txtTaxaValorResidual);
        this.validador.addObrigatorioValidator(this.txtValorResidual);
        this.validador.addApenasNumeroValidator(this.txtValorResidual);

        FormBuscaCategoria formCategoria = new FormBuscaCategoria();
        formCategoria.setFrameBloquear(FormPrincipal.getInstance());
        this.txtCategoria.setFrame(formCategoria);

        FormBuscaMarca formMarca = new FormBuscaMarca();
        formMarca.setFrameBloquear(FormPrincipal.getInstance());
        this.txtMarca.setFrame(formMarca);

        FormBuscaNotaFiscal formNotaFiscal = new FormBuscaNotaFiscal();
        formNotaFiscal.setFrameBloquear(FormPrincipal.getInstance());
        this.txtNotaFiscal.setFrame(formNotaFiscal);
        this.txtNotaFiscal.setFuncaoDepoisSelecionar(n -> {
            this.notaFiscal = (PatNotaFiscal) n;
            this.recarregarItensNota();
        });

        this.scrollCadastro.getVerticalScrollBar().setUnitIncrement(20);
    }

    public void setEvents(ActionListener salvar, ActionListener cancelar) {
        this.btnSalvar.addActionListener(salvar);
        this.btnCancelar.addActionListener(cancelar);
    }

    private void recarregarItensNota() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) this.comboItemNota.getModel();
        for (PatItemNota itemNota : this.notaFiscal.getPatItemNotas()) {
            model.addElement(itemNota.getEstProduto().getProdutoDescricao());
        }
    }

    public PatAtivoImobilizado getAtivoImobilizado() {
        if (!this.editando) {
            this.ativoImobilizado = new PatAtivoImobilizado();
        }

        this.ativoImobilizado.setAtivoDepreciavel(this.cbAtivoDepreciavel.isSelected());
        this.ativoImobilizado.setAtivoDescricao(this.txtDescricao.getText());
        this.ativoImobilizado.setEstCategoria((EstCategoria) this.txtCategoria.getValue());
        this.ativoImobilizado.setEstMarca((EstMarca) this.txtMarca.getValue());
        this.ativoImobilizado.setAtivoValorOriginal(this.txtValorOriginal.getValue());
        this.ativoImobilizado.setAtivoValorAtual(this.txtValorAtual.getValue());
        this.ativoImobilizado.setAtivoTaxaValorResidual(this.txtTaxaValorResidual.getValue());
        this.ativoImobilizado.setAtivoValorResidual(this.txtValorResidual.getValue());
        this.ativoImobilizado.setAtivoEstadoBem(EstadoBem.values()[this.comboEstadoBem.getSelectedIndex()]);
        this.ativoImobilizado.setAtivoUtilizacao(UtilizacaoBem.values()[this.comboUtilizacaoBem.getSelectedIndex()]);

        if (this.notaFiscal != null) {
            this.ativoImobilizado.setPatItemNota(this.notaFiscal.getPatItemNotas().get(this.comboItemNota.getSelectedIndex()));
        }

        return this.ativoImobilizado;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollCadastro = new javax.swing.JScrollPane();
        panelItens = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCategoria = new components.TextFieldFK();
        jLabel3 = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        txtMarca = new components.TextFieldFK();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNotaFiscal = new components.TextFieldFK();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        comboEstadoBem = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        comboUtilizacaoBem = new javax.swing.JComboBox<>();
        txtValorOriginal = new components.TextFieldValorMonetario();
        txtValorAtual = new components.TextFieldValorMonetario();
        txtTaxaValorResidual = new components.TextFieldValorMonetario();
        txtValorResidual = new components.TextFieldValorMonetario();
        comboItemNota = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cbAtivoDepreciavel = new com.alee.laf.checkbox.WebCheckBox();
        jLabel12 = new javax.swing.JLabel();
        txtValorJaDepreciado = new components.TextFieldValorMonetario();
        panelOpcoes = new javax.swing.JPanel();
        btnSalvar = new com.alee.laf.button.WebButton();
        btnCancelar = new com.alee.laf.button.WebButton();

        setMinimumSize(null);

        scrollCadastro.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollCadastro.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollCadastro.setMaximumSize(new java.awt.Dimension(0, 0));

        panelItens.setBackground(new java.awt.Color(255, 255, 255));
        panelItens.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelItens.setPreferredSize(new java.awt.Dimension(0, 500));

        jLabel1.setText("Descrição");

        jLabel3.setText("Categoria");

        jLabel4.setText("Marca");

        jLabel2.setText("Valor");

        jLabel5.setText("Valor Atual");

        jLabel6.setText("Nota Fiscal");

        jLabel7.setText("Taxa valor residual");

        jLabel8.setText("Valor Residual");

        jLabel9.setText("Estado do bem");

        comboEstadoBem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Novo", "Usado" }));

        jLabel10.setText("Utilização do bem");

        comboUtilizacaoBem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Diariamente", "Semanalmente", "Mensalmente", "Semestralmente", "Trimestralmente", "Anualmente" }));

        jLabel11.setText("Produto da nota");

        cbAtivoDepreciavel.setText("Ativo é depreciável?");

        jLabel12.setText("Valor já depreciado");

        javax.swing.GroupLayout panelItensLayout = new javax.swing.GroupLayout(panelItens);
        panelItens.setLayout(panelItensLayout);
        panelItensLayout.setHorizontalGroup(
            panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelItensLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtMarca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDescricao)
                        .addComponent(jLabel7)
                        .addComponent(jLabel1)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)
                        .addComponent(jLabel6)
                        .addGroup(panelItensLayout.createSequentialGroup()
                            .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2)
                                    .addComponent(txtValorOriginal, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                                    .addComponent(txtTaxaValorResidual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(cbAtivoDepreciavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(52, 52, 52)
                            .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtValorAtual, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                .addComponent(txtValorResidual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtValorJaDepreciado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelItensLayout.createSequentialGroup()
                                    .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel12))
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addComponent(txtNotaFiscal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addComponent(comboItemNota, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panelItensLayout.createSequentialGroup()
                            .addGap(282, 282, 282)
                            .addComponent(comboUtilizacaoBem, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelItensLayout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addGap(211, 211, 211)
                            .addComponent(jLabel10))
                        .addGroup(panelItensLayout.createSequentialGroup()
                            .addComponent(comboEstadoBem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(282, 282, 282))))
                .addContainerGap(299, Short.MAX_VALUE))
        );
        panelItensLayout.setVerticalGroup(
            panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelItensLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNotaFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboItemNota, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValorOriginal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTaxaValorResidual, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorResidual, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtValorJaDepreciado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbAtivoDepreciavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelItensLayout.createSequentialGroup()
                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboEstadoBem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(comboUtilizacaoBem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
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
                .addContainerGap(703, Short.MAX_VALUE)
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
                .addComponent(scrollCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.button.WebButton btnCancelar;
    private com.alee.laf.button.WebButton btnSalvar;
    private com.alee.laf.checkbox.WebCheckBox cbAtivoDepreciavel;
    private javax.swing.JComboBox<String> comboEstadoBem;
    private javax.swing.JComboBox<String> comboItemNota;
    private javax.swing.JComboBox<String> comboUtilizacaoBem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel panelItens;
    private javax.swing.JPanel panelOpcoes;
    private javax.swing.JScrollPane scrollCadastro;
    private components.TextFieldFK txtCategoria;
    private javax.swing.JTextField txtDescricao;
    private components.TextFieldFK txtMarca;
    private components.TextFieldFK txtNotaFiscal;
    private components.TextFieldValorMonetario txtTaxaValorResidual;
    private components.TextFieldValorMonetario txtValorAtual;
    private components.TextFieldValorMonetario txtValorJaDepreciado;
    private components.TextFieldValorMonetario txtValorOriginal;
    private components.TextFieldValorMonetario txtValorResidual;
    // End of variables declaration//GEN-END:variables
}
