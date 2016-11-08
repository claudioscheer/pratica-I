package components.panelsListagem;

import com.alee.laf.menu.PopupMenuWay;
import com.alee.laf.menu.WebMenuItem;
import com.alee.laf.menu.WebPopupMenu;
import com.alee.laf.panel.WebPanel;
import com.alee.managers.hotkey.Hotkey;
import dao.PatAtivoImobilizadoDAO;
import forms.FormPrincipal;
import forms.patrimonio.FormBaixaAtivo;
import forms.patrimonio.FormControleDepreciacoes;
import forms.patrimonio.FormDepreciar;
import forms.patrimonio.FormHistoricoDepreciacoes;
import forms.patrimonio.FormQrCodeAtivoImobilizado;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import model.PatAtivoImobilizado;
import model.PatBaixa;
import utils.Utils;

public class PanelConsultaAtivoImobilizado extends WebPanel {

    private List<PatAtivoImobilizado> ativosImobilizados;

    private AdjustmentListener eventoScroll;

    private int paginaBuscar;
    private int ultimaPosicaoTabela = 0;
    private List<PatBaixa> baixas;

    private int indexSelecionado;

    public PanelConsultaAtivoImobilizado() {
        initComponents();
        this.ativosImobilizados = new ArrayList<>();

        this.createOpcoesButton();
//        this.eventoScroll = (e) -> {
//            onScroll();
//        };
        new LoadAtivosImobilizados().execute();

        this.txtBuscar.setEventBuscar((e) -> {
            this.ativosImobilizados.clear();
            new LoadAtivosImobilizados().execute();
        });

        this.txtBuscar.addOpcoesBuscar(new String[]{
            "Código",
            "Descrição"
        });

        this.verificaPlaceholderText();
        this.txtBuscar.setEventChangeComboBox(al -> {
            this.verificaPlaceholderText();
        });
    }

    private void createOpcoesButton() {
        WebPopupMenu popupMenuOpcoes = new WebPopupMenu();
        WebMenuItem menuQrCode = new WebMenuItem("QrCode", Hotkey.NUMBER_1);
        menuQrCode.addActionListener((e) -> {
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
        });
        popupMenuOpcoes.add(menuQrCode);

        WebMenuItem menuHistoricoDepreciacao = new WebMenuItem("Histórico depreciação", Hotkey.NUMBER_2);
        menuHistoricoDepreciacao.addActionListener((e) -> {
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
        });
        popupMenuOpcoes.add(menuHistoricoDepreciacao);
        buttonOpcoes.setPopupMenuWay(PopupMenuWay.aboveStart);
        buttonOpcoes.setPopupMenu(popupMenuOpcoes);

        WebPopupMenu popupMenuManutencao = new WebPopupMenu();
        WebMenuItem menuDepreciar = new WebMenuItem("Depreciar ativos", Hotkey.NUMBER_1);
        menuDepreciar.addActionListener((e) -> {
            FormDepreciar depreciacoes = new FormDepreciar();
            depreciacoes.setVisible(true);

            FormPrincipal.getInstance().setEnabled(false);
        });
        popupMenuManutencao.add(menuDepreciar);

        WebMenuItem menuGerenciarDepreciacoes = new WebMenuItem("Gerenciar depreciação", Hotkey.NUMBER_2);
        menuGerenciarDepreciacoes.addActionListener((e) -> {
            FormControleDepreciacoes depreciacoes = new FormControleDepreciacoes();
            depreciacoes.setVisible(true);

            FormPrincipal.getInstance().setEnabled(false);
        });
        popupMenuManutencao.add(menuGerenciarDepreciacoes);

        buttonManutencoes.setPopupMenuWay(PopupMenuWay.aboveStart);
        buttonManutencoes.setPopupMenu(popupMenuManutencao);
    }

    public class LoadAtivosImobilizados extends SwingWorker<Void, Void> {

        protected Void doInBackground() throws Exception {
//            scrollPanel.getVerticalScrollBar().removeAdjustmentListener(eventoScroll);
            ativosImobilizados.addAll(new PatAtivoImobilizadoDAO().getAll(paginaBuscar));
            atualizarTabela(true);
            return null;
        }

        public void done() {
//            ultimaPosicaoTabela = scrollPanel.getVerticalScrollBar().getMaximum();
//            scrollPanel.getVerticalScrollBar().addAdjustmentListener(eventoScroll);
        }
    }

    private void verificaPlaceholderText() {
        switch (this.txtBuscar.getFiltroSelecionado()) {
            case 0:
                this.txtBuscar.setPlaceholderText("Digite o código do ativo");
                break;

            case 1:
                this.txtBuscar.setPlaceholderText("Digite a descrição do ativo");
                break;
        }
    }

    public int getIndiceSelecionado() {
        return this.indexSelecionado;
    }

    public void addAtivoImobilizado(PatAtivoImobilizado ativo) {
        ativo = new PatAtivoImobilizadoDAO().get(ativo.getAtivoCodigo());
        this.ativosImobilizados.add(0, ativo);
        DefaultTableModel model = (DefaultTableModel) this.tabelaAtivosImobilizados.getModel();
        model.insertRow(0, ativoToArray(ativo));
        this.tabelaAtivosImobilizados.setModel(model);
    }

    public void removeAtivoImobilizado(int index) {
        this.ativosImobilizados.remove(index);
        DefaultTableModel model = (DefaultTableModel) this.tabelaAtivosImobilizados.getModel();
        model.removeRow(index);
        this.tabelaAtivosImobilizados.setModel(model);
    }

    private void atualizarTabela(boolean limpar) {
        DefaultTableModel model = (DefaultTableModel) this.tabelaAtivosImobilizados.getModel();

        if (limpar) {
            Utils.clearTableModel(model);
        }

        this.ativosImobilizados.forEach(x -> {
            model.addRow(ativoToArray(x));
        });

        this.tabelaAtivosImobilizados.setModel(model);
    }

    private Object[] ativoToArray(PatAtivoImobilizado ativo) {
        Object[] o = new Object[5];
        o[0] = ativo.getAtivoCodigo();
        o[1] = ativo.getAtivoDescricao();
        o[2] = ativo.getEstCategoria().getCategoriaDescricao();
        o[3] = ativo.getEstMarca().getMarcaDescricao();
        o[4] = ativo.getAtivoValorAtual();
        return o;
    }

    public void setEvents(ActionListener add, ActionListener edit, ActionListener delete) {
        buttonAdd.addActionListener(add);
        buttonEditar.addActionListener(edit);
        buttonExcluir.addActionListener(delete);
    }

    private void onScroll() {
        int hPos = this.scrollPanel.getVerticalScrollBar().getValue() + this.scrollPanel.getVerticalScrollBar().getHeight();

        if (hPos >= this.ultimaPosicaoTabela) {
            this.paginaBuscar++;
            new LoadAtivosImobilizados().execute();
        }
    }

    public PatAtivoImobilizado getAtivoImobilizadoSelecionado() {
        int linhaselecionada = this.tabelaAtivosImobilizados.getSelectedRow();
        if (linhaselecionada < 0) {
            Utils.notificacao("Selecione um ativo imobilizado!", Utils.TipoNotificacao.erro, 0);
            return null;
        }
        this.indexSelecionado = linhaselecionada;
        return this.ativosImobilizados.get(linhaselecionada);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPanel = new javax.swing.JScrollPane();
        tabelaAtivosImobilizados = new com.alee.laf.table.WebTable();
        panelOpcoes = new javax.swing.JPanel();
        buttonAdd = new com.alee.laf.button.WebButton();
        buttonExcluir = new com.alee.laf.button.WebButton();
        buttonEditar = new com.alee.laf.button.WebButton();
        buttonOpcoes = new com.alee.extended.button.WebSplitButton();
        buttonManutencoes = new com.alee.extended.button.WebSplitButton();
        buttonBaixa = new com.alee.laf.button.WebButton();
        txtBuscar = new components.TextFieldBuscar();

        setMinimumSize(new java.awt.Dimension(565, 496));

        scrollPanel.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

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
        scrollPanel.setViewportView(tabelaAtivosImobilizados);

        panelOpcoes.setBackground(new java.awt.Color(255, 255, 255));

        buttonAdd.setText("Novo");
        buttonAdd.setMaximumSize(new java.awt.Dimension(24, 24));
        buttonAdd.setPreferredSize(new java.awt.Dimension(24, 24));

        buttonExcluir.setText("Excluir");

        buttonEditar.setText("Editar");

        buttonOpcoes.setText("Opções");

        buttonManutencoes.setText("Manutenções");

        buttonBaixa.setText("Dar Baixa");
        buttonBaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBaixaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOpcoesLayout = new javax.swing.GroupLayout(panelOpcoes);
        panelOpcoes.setLayout(panelOpcoesLayout);
        panelOpcoesLayout.setHorizontalGroup(
            panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonManutencoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonBaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelOpcoesLayout.setVerticalGroup(
            panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOpcoesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonManutencoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonBaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelOpcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                .addGap(3, 3, 3)
                .addComponent(panelOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonBaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBaixaActionPerformed

        int linhaselecionada = this.tabelaAtivosImobilizados.getSelectedRow();
        if (linhaselecionada < 0) {
            Utils.notificacao("Selecione um ativo!", Utils.TipoNotificacao.erro, 0);
            return;
        }
        FormPrincipal formBloquear = FormPrincipal.getInstance();

        FormBaixaAtivo form = new FormBaixaAtivo();
        form.setAtivoImobilizado(this.ativosImobilizados.get(linhaselecionada));
        form.setVisible(true);
        formBloquear.setEnabled(false);

// TODO add your handling code here:
    }//GEN-LAST:event_buttonBaixaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.button.WebButton buttonAdd;
    private com.alee.laf.button.WebButton buttonBaixa;
    private com.alee.laf.button.WebButton buttonEditar;
    private com.alee.laf.button.WebButton buttonExcluir;
    private com.alee.extended.button.WebSplitButton buttonManutencoes;
    private com.alee.extended.button.WebSplitButton buttonOpcoes;
    private javax.swing.JPanel panelOpcoes;
    private javax.swing.JScrollPane scrollPanel;
    private com.alee.laf.table.WebTable tabelaAtivosImobilizados;
    private components.TextFieldBuscar txtBuscar;
    // End of variables declaration//GEN-END:variables
}
