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
import model.PatDepreciacao;

public class PanelCadDepreciacoes extends WebPanel {

    public Validador validador;
    private PatDepreciacao depreciacao;

    public boolean editando;

    public PanelCadDepreciacoes() {
    }

    public void setDadosEditar(PatDepreciacao ativoImobilizado) {
        this.depreciacao = ativoImobilizado;
        this.editando = true;
        this.setDadosForm();
    }

    private void setDadosForm() {
        this.txtCategoria.setValue(this.depreciacao.getEstCategoria());
        this.txtCategoria.setText(this.depreciacao.getEstCategoria().getCategoriaId()
                + " - " + this.depreciacao.getEstCategoria().getCategoriaDescricao());
        this.txtTaxaAnual.setValue(this.depreciacao.getDepreciacaoTaxaAnual());
        this.txtTaxaMensal.setValue(this.depreciacao.getDepreciacaoTaxaMensal());
        this.txtTaxaDiaria.setValue(this.depreciacao.getDepreciacaoTaxaDiaria());
        this.txtVidaUtil.setValue(this.depreciacao.getDepreciacaoVidaUtil());
    }

    public void init() {
        this.initComponents();

        this.validador = new Validador(Validador.TipoValidator.ICONE);
        this.validador.addObrigatorioValidator(this.txtCategoria);
        this.validador.addObrigatorioValidator(this.txtTaxaAnual);
        this.validador.addApenasNumeroValidator(this.txtTaxaMensal);
        this.validador.addObrigatorioValidator(this.txtTaxaDiaria);

        FormBuscaCategoria formCategoria = new FormBuscaCategoria();
        formCategoria.buscarCategorias();
        formCategoria.setFrameBloquear(FormPrincipal.getInstance());
        this.txtCategoria.setFrame(formCategoria);
        this.scrollCadastro.getVerticalScrollBar().setUnitIncrement(20);
    }

    public void setEvents(ActionListener salvar, ActionListener cancelar) {
        this.btnSalvar.addActionListener(salvar);
        this.btnCancelar.addActionListener(cancelar);
    }

    public PatDepreciacao getDepreciacao() {
        if (!this.editando) {
            this.depreciacao = new PatDepreciacao();
        }

        this.depreciacao.setDepreciacaoVidaUtil(Integer.valueOf(this.txtVidaUtil.getValue().toString()));
        this.depreciacao.setEstCategoria((EstCategoria) this.txtCategoria.getValue());
        this.depreciacao.setDepreciacaoTaxaAnual(this.txtTaxaAnual.getValue());

        return this.depreciacao;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollCadastro = new javax.swing.JScrollPane();
        panelItens = new javax.swing.JPanel();
        txtCategoria = new components.TextFieldFK();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTaxaAnual = new components.TextFieldValorMonetario();
        txtTaxaMensal = new components.TextFieldValorMonetario();
        txtTaxaDiaria = new components.TextFieldValorMonetario();
        txtVidaUtil = new com.alee.laf.spinner.WebSpinner();
        panelOpcoes = new javax.swing.JPanel();
        btnSalvar = new com.alee.laf.button.WebButton();
        btnCancelar = new com.alee.laf.button.WebButton();

        setMinimumSize(null);

        scrollCadastro.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollCadastro.setMaximumSize(new java.awt.Dimension(0, 0));

        panelItens.setBackground(new java.awt.Color(255, 255, 255));
        panelItens.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelItens.setPreferredSize(new java.awt.Dimension(0, 400));

        jLabel3.setText("Categoria");

        jLabel2.setText("Vida útil (anos)");

        jLabel5.setText("Taxa depreciação anual (%)");

        jLabel7.setText("Taxa depreciação mensal (%)");

        jLabel8.setText("Taxa depreciação diária (%)");

        txtTaxaAnual.setTextoMonetario("");
        txtTaxaAnual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTaxaAnualKeyReleased(evt);
            }
        });

        txtTaxaMensal.setEnabled(false);
        txtTaxaMensal.setTextoMonetario("");

        txtTaxaDiaria.setEnabled(false);
        txtTaxaDiaria.setTextoMonetario("");

        txtVidaUtil.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        txtVidaUtil.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                txtVidaUtilStateChanged(evt);
            }
        });

        javax.swing.GroupLayout panelItensLayout = new javax.swing.GroupLayout(panelItens);
        panelItens.setLayout(panelItensLayout);
        panelItensLayout.setHorizontalGroup(
            panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelItensLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel3)
                    .addGroup(panelItensLayout.createSequentialGroup()
                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtTaxaMensal, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVidaUtil, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTaxaAnual, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addGroup(panelItensLayout.createSequentialGroup()
                                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtTaxaDiaria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        panelItensLayout.setVerticalGroup(
            panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelItensLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTaxaAnual, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txtVidaUtil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTaxaMensal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTaxaDiaria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(180, Short.MAX_VALUE))
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
                .addContainerGap(432, Short.MAX_VALUE)
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
                .addComponent(scrollCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtVidaUtilStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_txtVidaUtilStateChanged
        this.calcularTaxas();
    }//GEN-LAST:event_txtVidaUtilStateChanged

    private void txtTaxaAnualKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTaxaAnualKeyReleased
        this.calcularVidaUtil();
        this.calcularTaxas();
    }//GEN-LAST:event_txtTaxaAnualKeyReleased

    private void calcularVidaUtil() {
        double taxaanual = this.txtTaxaAnual.getValue();
        Double vidautil = 0d;
        if (taxaanual > 0d) {
            vidautil = (100 / taxaanual);
        }
        this.txtVidaUtil.setValue(vidautil.intValue());
    }

    private void calcularTaxas() {
        double taxaanual = this.txtTaxaAnual.getValue();

        double taxamensal = taxaanual / 12;
        double taxadiaria = taxamensal / 30;

        this.txtTaxaMensal.setValue(taxamensal);
        this.txtTaxaDiaria.setValue(taxadiaria);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.button.WebButton btnCancelar;
    private com.alee.laf.button.WebButton btnSalvar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel panelItens;
    private javax.swing.JPanel panelOpcoes;
    private javax.swing.JScrollPane scrollCadastro;
    private components.TextFieldFK txtCategoria;
    private components.TextFieldValorMonetario txtTaxaAnual;
    private components.TextFieldValorMonetario txtTaxaDiaria;
    private components.TextFieldValorMonetario txtTaxaMensal;
    private com.alee.laf.spinner.WebSpinner txtVidaUtil;
    // End of variables declaration//GEN-END:variables
}
