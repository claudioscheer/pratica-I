package components.panelsListagem;

import com.alee.laf.panel.WebPanel;
import dao.PatDepreciacaoDAO;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import model.PatDepreciacao;
import utils.Utils;

public class PanelConsultaDepreciacao extends WebPanel {

    private List<PatDepreciacao> depreciacoes;

    private AdjustmentListener eventoScroll;

    private int paginaBuscar;
    private int ultimaPosicaoTabela = 0;

    private int indexSelecionado;

    public PanelConsultaDepreciacao() {
        initComponents();
        this.depreciacoes = new ArrayList<>();

//        this.eventoScroll = (e) -> {
//            onScroll();
//        };
        new LoadDepreciacoes().execute();

        this.txtBuscar.setEventBuscar((e) -> {
            this.depreciacoes.clear();
            new LoadDepreciacoes().execute();
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

    public class LoadDepreciacoes extends SwingWorker<Void, Void> {

        protected Void doInBackground() throws Exception {
//            scrollPanel.getVerticalScrollBar().removeAdjustmentListener(eventoScroll);
            depreciacoes.addAll(new PatDepreciacaoDAO().buscarTodos());
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

    public void addDepreciacao(PatDepreciacao ativo) {
        ativo = new PatDepreciacaoDAO().buscarUm(ativo.getDepreciacaoCodigo());
        this.depreciacoes.add(0, ativo);
        DefaultTableModel model = (DefaultTableModel) this.tabelaDepreciacao.getModel();
        model.insertRow(0, depreciacaoToArray(ativo));
        this.tabelaDepreciacao.setModel(model);
    }

    public void removeDepreciacao(int index) {
        this.depreciacoes.remove(index);
        DefaultTableModel model = (DefaultTableModel) this.tabelaDepreciacao.getModel();
        model.removeRow(index);
        this.tabelaDepreciacao.setModel(model);
    }

    private void atualizarTabela(boolean limpar) {
        DefaultTableModel model = (DefaultTableModel) this.tabelaDepreciacao.getModel();
        
        if (limpar) {
            Utils.clearTableModel(model);
        }

        this.depreciacoes.forEach(x -> {
            model.addRow(depreciacaoToArray(x));
        });

        this.tabelaDepreciacao.setModel(model);
    }

    private Object[] depreciacaoToArray(PatDepreciacao ativo) {
        Object[] o = new Object[5];
        o[0] = ativo.getEstCategoria().getCategoriaDescricao();
        o[1] = ativo.getDepreciacaoVidaUtil();
        o[2] = ativo.getDepreciacaoTaxaAnual();
        o[3] = ativo.getDepreciacaoTaxaMensal();
        o[4] = ativo.getDepreciacaoTaxaDiaria();
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
            new LoadDepreciacoes().execute();
        }
    }

    public PatDepreciacao getDepreciacaoSelecionada() {
        int linhaselecionada = this.tabelaDepreciacao.getSelectedRow();
        if (linhaselecionada < 0) {
            Utils.notificacao("Selecione uma depreciação!", Utils.TipoNotificacao.erro, 0);
            return null;
        }
        this.indexSelecionado = linhaselecionada;
        return this.depreciacoes.get(linhaselecionada);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPanel = new javax.swing.JScrollPane();
        tabelaDepreciacao = new com.alee.laf.table.WebTable();
        panelOpcoes = new javax.swing.JPanel();
        buttonAdd = new com.alee.laf.button.WebButton();
        buttonExcluir = new com.alee.laf.button.WebButton();
        buttonEditar = new com.alee.laf.button.WebButton();
        txtBuscar = new components.TextFieldBuscar();

        setMinimumSize(new java.awt.Dimension(565, 496));

        scrollPanel.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tabelaDepreciacao.setAutoCreateRowSorter(true);
        tabelaDepreciacao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Categoria", "Vida útil", "Taxa anual", "Taxa mensal", "Taxa diária"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaDepreciacao.setEditable(false);
        tabelaDepreciacao.setSelectionBackground(new java.awt.Color(204, 204, 204));
        scrollPanel.setViewportView(tabelaDepreciacao);

        panelOpcoes.setBackground(new java.awt.Color(255, 255, 255));

        buttonAdd.setText("Novo");
        buttonAdd.setMaximumSize(new java.awt.Dimension(24, 24));
        buttonAdd.setPreferredSize(new java.awt.Dimension(24, 24));

        buttonExcluir.setText("Excluir");

        buttonEditar.setText("Editar");

        javax.swing.GroupLayout panelOpcoesLayout = new javax.swing.GroupLayout(panelOpcoes);
        panelOpcoes.setLayout(panelOpcoesLayout);
        panelOpcoesLayout.setHorizontalGroup(
            panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcoesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.button.WebButton buttonAdd;
    private com.alee.laf.button.WebButton buttonEditar;
    private com.alee.laf.button.WebButton buttonExcluir;
    private javax.swing.JPanel panelOpcoes;
    private javax.swing.JScrollPane scrollPanel;
    private com.alee.laf.table.WebTable tabelaDepreciacao;
    private components.TextFieldBuscar txtBuscar;
    // End of variables declaration//GEN-END:variables
}
