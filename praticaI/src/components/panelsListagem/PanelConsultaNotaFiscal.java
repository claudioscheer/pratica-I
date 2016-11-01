package components.panelsListagem;

import com.alee.laf.panel.WebPanel;
import com.alee.laf.table.WebTable;
import dao.NotaFiscalDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import model.PatNotaFiscal;
import utils.Utils;

public class PanelConsultaNotaFiscal extends WebPanel implements ActionListener {

    private List<PatNotaFiscal> notasFiscais;

    private AdjustmentListener eventoScroll;

    private int paginaBuscar;
    private int ultimaPosicaoTabela = 0;

    private int indexSelecionado;

    public PanelConsultaNotaFiscal() {
        initComponents();
        this.notasFiscais = new ArrayList<>();

        this.eventoScroll = (e) -> {
            onScroll();
        };

        new LoadNotasFiscais().execute();

        this.txtBuscar.setEventBuscar((e) -> {
            System.out.println("buscar");
        });
    }

    public class LoadNotasFiscais extends SwingWorker<Void, Void> {

        protected Void doInBackground() throws Exception {
            scrollPanel.getVerticalScrollBar().removeAdjustmentListener(eventoScroll);
            notasFiscais.addAll(new NotaFiscalDAO().getAll());
            atualizarTabela(true);
            return null;
        }

        public void done() {
            ultimaPosicaoTabela = scrollPanel.getVerticalScrollBar().getMaximum();
            scrollPanel.getVerticalScrollBar().addAdjustmentListener(eventoScroll);
        }
    }

    private void atualizarTabela(boolean limpar) {
        DefaultTableModel model = (DefaultTableModel) this.tabelaNotasFiscais.getModel();

        if (limpar) {
            Utils.clearTableModel(model);
        }

        int filtro = this.txtBuscar.getFiltroSelecionado();

        List<PatNotaFiscal> notasFiltrado = this.notasFiscais;

//        String textBuscar = this.txtBuscar.getText();
//
//        if (!textBuscar.isEmpty()) {
//            if (filtro == 0) {
//                try {
//                    int codigo = Integer.parseInt(textBuscar);
//                    ativosFiltrado = this.ativosImobilizados.stream().filter(x -> x.getAtivoImobilizado() == codigo).collect(Collectors.toList());
//                } catch (Exception e) {
//                }
//            } else if (filtro == 1) {
//                ativosFiltrado = this.ativosImobilizados.stream().filter(x -> x.getDescricao().contains(textBuscar)).collect(Collectors.toList());
//            }
//        }
//        for (int i = ultimoIndex; i < ativosFiltrado.size(); i++) {
//            
//        }
        notasFiltrado.forEach(x -> {
            model.addRow(notaToArray(x));
        });

        this.tabelaNotasFiscais.setModel(model);
    }

    private Object[] notaToArray(PatNotaFiscal nota) {
        Object[] o = new Object[5];
        o[0] = nota.getNotaChaveAcesso();
        o[1] = nota.getNotaDataEmissao();
        o[2] = nota.getNotaDataEntrada();
        o[3] = nota.getCarPessoa().getPessoaNome();
        o[4] = nota.getNotaValor();
        return o;
    }

    private void onScroll() {
        int hPos = this.scrollPanel.getVerticalScrollBar().getValue() + this.scrollPanel.getVerticalScrollBar().getHeight();

        if (hPos >= this.ultimaPosicaoTabela) {
            this.paginaBuscar++;
            new LoadNotasFiscais().execute();
        }
    }

    public void setEvents(ActionListener add, ActionListener edit, ActionListener delete) {
        buttonAdd.addActionListener(add);
        buttonAdd.addActionListener(edit);
        buttonAdd.addActionListener(delete);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("asdjkfaksldjfnkajsdfnk");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPanel = new javax.swing.JScrollPane();
        tabelaNotasFiscais = new com.alee.laf.table.WebTable();
        panelOpcoes = new javax.swing.JPanel();
        buttonAdd = new com.alee.laf.button.WebButton();
        buttonExcluir = new com.alee.laf.button.WebButton();
        buttonEditar = new com.alee.laf.button.WebButton();
        txtBuscar = new components.TextFieldBuscar();

        setMinimumSize(new java.awt.Dimension(565, 496));

        scrollPanel.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tabelaNotasFiscais.setAutoCreateRowSorter(true);
        tabelaNotasFiscais.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Chave acesso", "Data emissão", "Data entrada", "Fornecedor", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaNotasFiscais.setEditable(false);
        tabelaNotasFiscais.setSelectionBackground(new java.awt.Color(204, 204, 204));
        scrollPanel.setViewportView(tabelaNotasFiscais);

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
                .addContainerGap(316, Short.MAX_VALUE)
                .addComponent(buttonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
                    .addComponent(buttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelOpcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrollPanel)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
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
    private com.alee.laf.table.WebTable tabelaNotasFiscais;
    private components.TextFieldBuscar txtBuscar;
    // End of variables declaration//GEN-END:variables
}
