package components.panelsListagem;

import com.alee.laf.panel.WebPanel;
import com.alee.laf.table.WebTable;
import dao.AtivoImobilizadoDAO;
import forms.patrimonio.FormAtivoImobilizado;
import forms.patrimonio.FormHistoricoDepreciacoes;
import forms.FormPrincipal;
import forms.patrimonio.FormQrCodeAtivoImobilizado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import model.AtivoImobilizado;
import utils.Utils;

public class PanelConsultaAtivoImobilizado extends WebPanel implements ActionListener {

    private LoadAtivosImobilizados loadAtivosImobilizados;
    private List<AtivoImobilizado> ativosImobilizados;

    private int indexSelecionado;

    public PanelConsultaAtivoImobilizado() {
        initComponents();
        this.loadDatas();

        this.txtBuscar.setEventBuscar((e) -> {
            System.out.println("buscar");
        });
        this.txtBuscar.addOpcoesBuscar(new String[]{"Código", "Descrição"});
    }

    public class LoadAtivosImobilizados extends SwingWorker<Void, Void> {

        protected Void doInBackground() throws Exception {

            ativosImobilizados = new AtivoImobilizadoDAO().getAll();
            atualizarTabela();
            return null;
        }

        public void done() {

        }
    }

    public int getIndiceSelecionado() {
        return this.indexSelecionado;
    }

    public void addAtivoImobilizado(AtivoImobilizado ativo) {
        DefaultTableModel model = (DefaultTableModel) tabelaAtivosImobilizados.getModel();
        model.insertRow(0, ativoToArray(ativo));
        tabelaAtivosImobilizados.setModel(model);
    }

    public void removeAtivoImobilizado(int index) {
        DefaultTableModel model = (DefaultTableModel) tabelaAtivosImobilizados.getModel();
        model.removeRow(index);
        tabelaAtivosImobilizados.setModel(model);
    }

    private void atualizarTabela() {
        DefaultTableModel model = (DefaultTableModel) tabelaAtivosImobilizados.getModel();

        for (AtivoImobilizado ativo : ativosImobilizados) {
            model.addRow(ativoToArray(ativo));
        }

        tabelaAtivosImobilizados.setModel(model);
    }

    private Object[] ativoToArray(AtivoImobilizado ativo) {
        Object[] o = new Object[5];
        o[0] = ativo.getAtivoImobilizado();
        o[1] = ativo.getDescricao();
        o[2] = ativo.getCategoria().getDescricao();
        o[3] = ativo.getMarca().getDescricao();
        o[4] = ativo.getValorAtual();
        return o;
    }

    public void setEvents(ActionListener add, ActionListener edit, ActionListener delete) {
        buttonAdd.addActionListener(add);
        buttonEditar.addActionListener(edit);
        buttonExcluir.addActionListener(delete);
    }

    private void loadDatas() {
        this.loadAtivosImobilizados = new LoadAtivosImobilizados();
        this.loadAtivosImobilizados.execute();
    }

    public AtivoImobilizado getAtivoImobilizadoSelecionado() {
        int linhaselecionada = this.tabelaAtivosImobilizados.getSelectedRow();
        if (linhaselecionada < 0) {
            Utils.notificacao("Selecione um ativo imobilizado!", Utils.TipoNotificacao.erro, 0);
            return null;
        }
        this.indexSelecionado = linhaselecionada;
        return this.ativosImobilizados.get(linhaselecionada);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("asdjkfaksldjfnkajsdfnk");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaAtivosImobilizados = new com.alee.laf.table.WebTable();
        panelOpcoes = new javax.swing.JPanel();
        buttonAdd = new com.alee.laf.button.WebButton();
        buttonExcluir = new com.alee.laf.button.WebButton();
        buttonEditar = new com.alee.laf.button.WebButton();
        btnReavaliarAtivo = new com.alee.laf.button.WebButton();
        btnGerarQrCode = new com.alee.laf.button.WebButton();
        buttonHistoricoDepreciacao = new com.alee.laf.button.WebButton();
        txtBuscar = new components.TextFieldBuscar();

        setMinimumSize(new java.awt.Dimension(565, 496));

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tabelaAtivosImobilizados.setAutoCreateRowSorter(true);
        tabelaAtivosImobilizados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descrição", "Categoria", "Marca", "Valor atual"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaAtivosImobilizados.setEditable(false);
        tabelaAtivosImobilizados.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(tabelaAtivosImobilizados);

        panelOpcoes.setBackground(new java.awt.Color(255, 255, 255));

        buttonAdd.setText("Novo");
        buttonAdd.setMaximumSize(new java.awt.Dimension(24, 24));
        buttonAdd.setPreferredSize(new java.awt.Dimension(24, 24));

        buttonExcluir.setText("Excluir");

        buttonEditar.setText("Editar");

        btnReavaliarAtivo.setText("Reavaliar");

        btnGerarQrCode.setText("QrCode");
        btnGerarQrCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarQrCodeActionPerformed(evt);
            }
        });

        buttonHistoricoDepreciacao.setText("Depreciações");
        buttonHistoricoDepreciacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHistoricoDepreciacaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOpcoesLayout = new javax.swing.GroupLayout(panelOpcoes);
        panelOpcoes.setLayout(panelOpcoesLayout);
        panelOpcoesLayout.setHorizontalGroup(
            panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnReavaliarAtivo, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGerarQrCode, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonHistoricoDepreciacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                .addComponent(buttonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelOpcoesLayout.setVerticalGroup(
            panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOpcoesLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReavaliarAtivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGerarQrCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonHistoricoDepreciacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelOpcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                .addGap(3, 3, 3)
                .addComponent(panelOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonHistoricoDepreciacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHistoricoDepreciacaoActionPerformed

        int linhaselecionada = this.tabelaAtivosImobilizados.getSelectedRow();
        if (linhaselecionada < 0) {
            Utils.notificacao("Selecione um ativo imobilizado!", Utils.TipoNotificacao.erro, 0);
            return;
        }

        FormPrincipal formBloquear = FormPrincipal.getInstance();

        FormHistoricoDepreciacoes form = new FormHistoricoDepreciacoes();
        form.setFrameBloquear(formBloquear);
        form.setAtivoImobilizado(this.ativosImobilizados.get(linhaselecionada));
        form.setVisible(true);

        formBloquear.setEnabled(false);
    }//GEN-LAST:event_buttonHistoricoDepreciacaoActionPerformed

    private void btnGerarQrCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarQrCodeActionPerformed
        int linhaselecionada = this.tabelaAtivosImobilizados.getSelectedRow();
        if (linhaselecionada < 0) {
            Utils.notificacao("Selecione um ativo imobilizado!", Utils.TipoNotificacao.erro, 0);
            return;
        }

        FormPrincipal formBloquear = FormPrincipal.getInstance();

        FormQrCodeAtivoImobilizado form = new FormQrCodeAtivoImobilizado();
        form.setFrameBloquear(formBloquear);
        form.setAtivoImobilizado(this.ativosImobilizados.get(linhaselecionada));
        form.setVisible(true);

        formBloquear.setEnabled(false);
    }//GEN-LAST:event_btnGerarQrCodeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.button.WebButton btnGerarQrCode;
    private com.alee.laf.button.WebButton btnReavaliarAtivo;
    private com.alee.laf.button.WebButton buttonAdd;
    private com.alee.laf.button.WebButton buttonEditar;
    private com.alee.laf.button.WebButton buttonExcluir;
    private com.alee.laf.button.WebButton buttonHistoricoDepreciacao;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelOpcoes;
    private com.alee.laf.table.WebTable tabelaAtivosImobilizados;
    private components.TextFieldBuscar txtBuscar;
    // End of variables declaration//GEN-END:variables
}
