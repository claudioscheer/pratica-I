package components.panelsListagem;

import com.alee.laf.panel.WebPanel;
import dao.PatNotaFiscalDAO;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import model.PatNotaFiscal;
import utils.Utils;

public class PanelConsultaNotaFiscal extends WebPanel {

    private List<PatNotaFiscal> notasFiscais;

    private int paginaBuscar;

    private int indexSelecionado;

    public PanelConsultaNotaFiscal() {
        initComponents();

        this.tabelaNotasFiscais.setSortable(true);
        this.tabelaNotasFiscais.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Chave acesso", "Data emissão", "Data entrada", "Fornecedor", "Valor"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        this.tabelaNotasFiscais.setLoadMore(x -> new LoadNotasFiscais().execute());
        this.notasFiscais = new ArrayList<>();

        new LoadNotasFiscais().execute();

        this.txtBuscar.addOpcoesBuscar(new String[]{
            "Código",
            "Chave de acesso"
        });

        this.verificaPlaceholderText();
        this.txtBuscar.setEventChangeComboBox(al -> {
            this.verificaPlaceholderText();
        });

        this.txtBuscar.setEventBuscar((e) -> {
            this.notasFiscais.clear();
            new LoadNotasFiscais().execute();
        });
    }

    private void verificaPlaceholderText() {
        switch (this.txtBuscar.getFiltroSelecionado()) {
            case 0:
                this.txtBuscar.setPlaceholderText("Digite o código da nota");
                break;

            case 1:
                this.txtBuscar.setPlaceholderText("Digite a chave de acesso da nota");
                break;
        }
    }

    public void addNotaFiscal(PatNotaFiscal notaFiscal) {
        notaFiscal = new PatNotaFiscalDAO().get(notaFiscal.getNotaCodigo());
        this.notasFiscais.add(0, notaFiscal);
        DefaultTableModel model = (DefaultTableModel) this.tabelaNotasFiscais.getModel();
        model.insertRow(0, notaToArray(notaFiscal));
        this.tabelaNotasFiscais.setModel(model);
    }

    private class LoadNotasFiscais extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() throws Exception {
            List<PatNotaFiscal> notas = new PatNotaFiscalDAO().getAll(paginaBuscar++, txtBuscar.getFiltroSelecionado(), txtBuscar.getText());
            if (notas.size() > 0) {
                notasFiscais.addAll(notas);
                atualizarTabela(true);
            }
            return null;
        }

        @Override
        public void done() {
        }
    }

    public int getIndiceSelecionado() {
        return this.indexSelecionado;
    }

    public PatNotaFiscal getNotaFiscalSelecionada() {
        int linhaselecionada = this.tabelaNotasFiscais.getSelectedRow();
        if (linhaselecionada < 0) {
            Utils.notificacao("Selecione uma nota fiscal!", Utils.TipoNotificacao.erro, 0);
            return null;
        }
        this.indexSelecionado = linhaselecionada;
        return this.notasFiscais.get(linhaselecionada);
    }

    public void removeNotaFiscal(int index) {
        this.notasFiscais.remove(index);
        DefaultTableModel model = (DefaultTableModel) this.tabelaNotasFiscais.getModel();
        model.removeRow(index);
        this.tabelaNotasFiscais.setModel(model);
    }

    private void atualizarTabela(boolean limpar) {
        DefaultTableModel model = (DefaultTableModel) this.tabelaNotasFiscais.getModel();

        if (limpar) {
            Utils.clearTableModel(model);
        }

        this.notasFiscais.forEach(x -> {
            model.addRow(notaToArray(x));
        });

        this.tabelaNotasFiscais.setModel(model);
    }

    private Object[] notaToArray(PatNotaFiscal nota) {
        Object[] o = new Object[5];
        o[0] = nota.getNotaChaveAcesso();
        o[1] = Utils.formatData(nota.getNotaDataEmissao());
        o[2] = Utils.formatData(nota.getNotaDataEntrada());
        o[3] = nota.getCarPessoa().getPessoaNome();
        o[4] = nota.getNotaValor();
        return o;
    }

    public void setEvents(ActionListener add, ActionListener edit, ActionListener delete) {
        this.buttonAdd.addActionListener(add);
        this.buttonEditar.addActionListener(edit);
        this.buttonExcluir.addActionListener(delete);
    }

    public void setEventsBuscar(ActionListener select, ActionListener novo) {
        this.buttonAdd.addActionListener(select);
        this.buttonEditar.addActionListener(novo);

        this.buttonAdd.setText("Selecionar");
        this.buttonEditar.setText("Novo");
        this.buttonExcluir.setVisible(false);
    }

    public void setDoubleClickTabela(MouseAdapter event) {
        this.tabelaNotasFiscais.addMouseListener(event);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelOpcoes = new javax.swing.JPanel();
        buttonAdd = new com.alee.laf.button.WebButton();
        buttonExcluir = new com.alee.laf.button.WebButton();
        buttonEditar = new com.alee.laf.button.WebButton();
        txtBuscar = new components.TextFieldBuscar();
        tabelaNotasFiscais = new components.JTableLoadScroll();

        setMinimumSize(new java.awt.Dimension(565, 496));

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
                .addContainerGap(342, Short.MAX_VALUE)
                .addComponent(buttonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(tabelaNotasFiscais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabelaNotasFiscais, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.button.WebButton buttonAdd;
    private com.alee.laf.button.WebButton buttonEditar;
    private com.alee.laf.button.WebButton buttonExcluir;
    private javax.swing.JPanel panelOpcoes;
    private components.JTableLoadScroll tabelaNotasFiscais;
    private components.TextFieldBuscar txtBuscar;
    // End of variables declaration//GEN-END:variables
}
