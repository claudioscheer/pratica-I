package components.panelsListagem;

import com.alee.laf.panel.WebPanel;
import dao.CarCapContasDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import model.CarCapContas;
import utils.Utils;

public class PanelConsultaContaPagar extends WebPanel implements ActionListener {

    private List<CarCapContas> contas;
    
    private int indexSelecionado;
    
    public PanelConsultaContaPagar() {
        initComponents();
        this.contas = new ArrayList<>();
        this.loadDatas();

        this.txtBuscar.addOpcoesBuscar(new String[]{
            "ID",
            "CNPJ"
        });

        this.verificaPlaceholderText();
        this.txtBuscar.setEventChangeComboBox(al -> {
            this.verificaPlaceholderText();
        });
        
        this.txtBuscar.setEventBuscar((e) -> {
            this.contas.clear();
            this.loadDatas();
        });
    }
    
    private void verificaPlaceholderText() {
        switch (this.txtBuscar.getFiltroSelecionado()) {
            case 0:
                this.txtBuscar.setPlaceholderText("Digite o ID do fornecedor: ");
                break;

            case 1:
                this.txtBuscar.setPlaceholderText("Digite o CNPJ do fornecedor: ");
                break;
        }
    }

    public void setEvents(ActionListener relatorio) {
        buttonRelatorio.addActionListener(relatorio);
    }

    public void open() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setMaximum(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public class LoadPessoas extends SwingWorker<Void, Void> {
        protected Void doInBackground() throws Exception {
            contas.addAll(new CarCapContasDAO().getAll(txtBuscar.getFiltroSelecionado(), txtBuscar.getText()));
            atualizarTabela();
            return null;
        }
        public void done() {
        }
    }

    public int getIndiceSelecionado() {
        return this.indexSelecionado;
    }
    
    public CarCapContas getContaSelecionada() {
        int linhaselecionada = this.tableLista.getSelectedRow();
        if (linhaselecionada < 0) {
            Utils.notificacao("Selecione uma conta!", Utils.TipoNotificacao.erro, 0);
            return null;
        }
        this.indexSelecionado = linhaselecionada;
        return this.contas.get(linhaselecionada);
    }
    
    private void atualizarTabela() {
        DefaultTableModel model = (DefaultTableModel) tableLista.getModel();
         Utils.clearTableModel(model);
        for (CarCapContas ativo : contas) {
            model.addRow(contaToArray(ativo));
        }
        tableLista.setModel(model);
    }
    
    private Object[] contaToArray(CarCapContas c) {
        Object[] o = new Object[8];
        o[0] = c.getContaId();
        o[1] = c.getCarPessoa();
        o[2] = c.getDataVencimento();
        o[3] = c.getContaNumParcelas();
        o[4] = c.getContaValorTotal();
        o[5] = c.getContaValorPago();
        o[6] = c.getValorPendente();
        return o;
    }
    
    private void loadDatas() {
        new LoadPessoas().execute(); 
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("asdjkfaksldjfnkajsdfnk");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableLista = new com.alee.laf.table.WebTable();
        panelOpcoes = new javax.swing.JPanel();
        buttonRelatorio = new com.alee.laf.button.WebButton();
        txtBuscar = new components.TextFieldBuscar();

        setMinimumSize(new java.awt.Dimension(565, 496));

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tableLista.setAutoCreateRowSorter(true);
        tableLista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fornecedor", "Data de Vencimento", "N° Parcelas", "Valor Total", "Valor Pago", "Valor Pendente"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableLista.setEditable(false);
        tableLista.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(tableLista);
        if (tableLista.getColumnModel().getColumnCount() > 0) {
            tableLista.getColumnModel().getColumn(0).setMinWidth(40);
            tableLista.getColumnModel().getColumn(0).setPreferredWidth(40);
            tableLista.getColumnModel().getColumn(0).setMaxWidth(40);
            tableLista.getColumnModel().getColumn(3).setMinWidth(80);
            tableLista.getColumnModel().getColumn(3).setPreferredWidth(80);
            tableLista.getColumnModel().getColumn(3).setMaxWidth(80);
        }

        panelOpcoes.setBackground(new java.awt.Color(255, 255, 255));

        buttonRelatorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/relatorio_ok.png"))); // NOI18N
        buttonRelatorio.setText("Gerar relatórios");
        buttonRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRelatorioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOpcoesLayout = new javax.swing.GroupLayout(panelOpcoes);
        panelOpcoes.setLayout(panelOpcoesLayout);
        panelOpcoesLayout.setHorizontalGroup(
            panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcoesLayout.createSequentialGroup()
                .addGap(319, 319, 319)
                .addComponent(buttonRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelOpcoesLayout.setVerticalGroup(
            panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelOpcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                .addGap(3, 3, 3)
                .addComponent(panelOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRelatorioActionPerformed
        FormRelatorioContasPagar form = new FormRelatorioContasPagar();
        form.setVisible(true);
// TODO add your handling code here:
    }//GEN-LAST:event_buttonRelatorioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.button.WebButton buttonRelatorio;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelOpcoes;
    private com.alee.laf.table.WebTable tableLista;
    private components.TextFieldBuscar txtBuscar;
    // End of variables declaration//GEN-END:variables
}
