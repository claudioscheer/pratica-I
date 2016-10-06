package components.panelsCads;

import com.alee.laf.optionpane.WebOptionPane;
import com.alee.laf.panel.WebPanel;
import components.Validador;
import forms.FormPrincipal;
import forms.busca.FormBuscaCategoria;
import forms.busca.FormBuscaImposto;
import forms.busca.FormBuscaMarca;
import forms.busca.FormBuscaNotaFiscal;
import forms.busca.FormBuscaProdutoImposto;
import forms.busca.FormBuscarFornecedor;
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

public class PanelCadAtivoImobilizado extends WebPanel {

    public Validador validador;
    private List<ItemNota> itensNota;
    private List<ImpostoNotaFiscal> impostosNotaFiscal;

    public PanelCadAtivoImobilizado() {
    }

    public void init() {
        this.initComponents();

        this.itensNota = new ArrayList<>();
        this.impostosNotaFiscal = new ArrayList<>();

        FormBuscaCategoria formCategoria = new FormBuscaCategoria();
        formCategoria.setFrameBloquear(FormPrincipal.getInstance());
        this.txtCategoria.setFrame(formCategoria);

        FormBuscaMarca formMarca = new FormBuscaMarca();
        formMarca.setFrameBloquear(FormPrincipal.getInstance());
        this.txtMarca.setFrame(formMarca);

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
        jLabel1 = new javax.swing.JLabel();
        txtCategoria = new components.TextFieldFK();
        jLabel3 = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        txtMarca = new components.TextFieldFK();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtValorOriginal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtValorAtual = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNotaFiscal = new components.TextFieldFK();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtValorResidual = new javax.swing.JTextField();
        txtTaxaValorResidual = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        comboEstadoBem = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        comboUtilizacaoBem = new javax.swing.JComboBox<>();
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

        jLabel1.setText("Descrição");

        jLabel3.setText("Categoria");

        jLabel4.setText("Marca");

        jLabel2.setText("Valor");

        jLabel5.setText("Valor Atual");

        jLabel6.setText("Nota Fiscal");

        jLabel7.setText("Taxa valor residual");

        jLabel8.setText("Valor Residual");

        txtTaxaValorResidual.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));

        jLabel9.setText("Estado do bem");

        comboEstadoBem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Novo", "Usado" }));

        jLabel10.setText("Utilização do bem");

        comboUtilizacaoBem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Diariamente", "Semanalmente", "Mensalmente", "Semestralmente", "Trimestralmente", "Anualmente" }));

        javax.swing.GroupLayout panelItensLayout = new javax.swing.GroupLayout(panelItens);
        panelItens.setLayout(panelItensLayout);
        panelItensLayout.setHorizontalGroup(
            panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelItensLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelItensLayout.createSequentialGroup()
                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtTaxaValorResidual, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(115, 115, 115)
                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(txtValorResidual, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(215, Short.MAX_VALUE))
                    .addGroup(panelItensLayout.createSequentialGroup()
                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtMarca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtDescricao, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtCategoria, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE))
                                    .addComponent(jLabel4))
                                .addComponent(jLabel6)
                                .addComponent(txtNotaFiscal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelItensLayout.createSequentialGroup()
                                    .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtValorOriginal, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2))
                                    .addGap(18, 18, 18)
                                    .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(txtValorAtual, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))))
                            .addGroup(panelItensLayout.createSequentialGroup()
                                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(comboEstadoBem, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(comboUtilizacaoBem, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
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
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelItensLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNotaFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtValorAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtValorOriginal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtValorResidual, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(txtTaxaValorResidual))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboEstadoBem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelItensLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboUtilizacaoBem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
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
                .addComponent(scrollCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.button.WebButton btnCancelar;
    private com.alee.laf.button.WebButton btnSalvar;
    private javax.swing.JComboBox<String> comboEstadoBem;
    private javax.swing.JComboBox<String> comboUtilizacaoBem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JSpinner txtTaxaValorResidual;
    private javax.swing.JTextField txtValorAtual;
    private javax.swing.JTextField txtValorOriginal;
    private javax.swing.JTextField txtValorResidual;
    // End of variables declaration//GEN-END:variables
}
