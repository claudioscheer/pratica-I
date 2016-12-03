package components.panelsListagem;

import com.alee.laf.panel.WebPanel;
import dao.PessoaDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import model.CarCapContas;
import model.CarPessoa;
import utils.Utils;

public class PanelConsultaContaPagar extends WebPanel implements ActionListener {

    private List<CarCapContas> contas;
    
    private int indexSelecionado;
    
    public PanelConsultaContaPagar() {
        initComponents();
        this.pessoas = new ArrayList<>();
        this.loadDatas();

        this.txtBuscar.addOpcoesBuscar(new String[]{
            "ID",
            "Nome"
        });

        this.verificaPlaceholderText();
        this.txtBuscar.setEventChangeComboBox(al -> {
            this.verificaPlaceholderText();
        });
        
        this.txtBuscar.setEventBuscar((e) -> {
            this.pessoas.clear();
            this.loadDatas();
        });
    }
    
    private void verificaPlaceholderText() {
        switch (this.txtBuscar.getFiltroSelecionado()) {
            case 0:
                this.txtBuscar.setPlaceholderText("Digite o ID da pessoa: ");
                break;

            case 1:
                this.txtBuscar.setPlaceholderText("Digite o nome da pessoa: ");
                break;
        }
    }

    public void setEvents(ActionListener add, ActionListener edit, ActionListener delete, ActionListener relatorio) {
        buttonRelatorio.addActionListener(relatorio);
        buttonAdd.addActionListener(add);
        buttonEditar.addActionListener(edit);
        buttonExcluir.addActionListener(delete);
    }

    public class LoadPessoas extends SwingWorker<Void, Void> {
        protected Void doInBackground() throws Exception {
            pessoas.addAll(new PessoaDAO().getAll(txtBuscar.getFiltroSelecionado(), txtBuscar.getText()));
            atualizarTabela();
            return null;
        }
        public void done() {
        }
    }

    public int getIndiceSelecionado() {
        return this.indexSelecionado;
    }
    
    public void removePessoa(int index) {
        this.pessoas.remove(index);
        DefaultTableModel model = (DefaultTableModel) this.tableLista.getModel();
        model.removeRow(index);
        this.tableLista.setModel(model);
    }
    
    public void addPessoas(CarPessoa ativo) {
        ativo = new PessoaDAO().get(ativo.getPessoaId());
        this.pessoas.add(0, ativo);
        DefaultTableModel model = (DefaultTableModel) this.tableLista.getModel();
        model.insertRow(0, pessoaToArray(ativo));
        this.tableLista.setModel(model);
    }
    
    public CarPessoa getPessoaSelecionada() {
        int linhaselecionada = this.tableLista.getSelectedRow();
        if (linhaselecionada < 0) {
            Utils.notificacao("Selecione uma pessoa!", Utils.TipoNotificacao.erro, 0);
            return null;
        }
        this.indexSelecionado = linhaselecionada;
        return this.pessoas.get(linhaselecionada);
    }
    
    private void atualizarTabela() {
        DefaultTableModel model = (DefaultTableModel) tableLista.getModel();
         Utils.clearTableModel(model);
        for (CarPessoa ativo : pessoas) {
            model.addRow(pessoaToArray(ativo));
        }
        tableLista.setModel(model);
    }
    
    private Object[] pessoaToArray(CarPessoa p) {
        Object[] o = new Object[8];
        o[0] = p.getPessoaId();
        o[1] = p.getPessoaNome();
        o[2] = p.getPessoaCpfCnpj();
        o[3] = p.getPessoaFone();
        o[4] = p.getPessoaEmail();
        o[5] = p.getPessoaEndereco();
        o[6] = p.getPessoaComplemento();
        o[7] = p.getPessoaCidade();
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
        buttonAdd = new com.alee.laf.button.WebButton();
        buttonExcluir = new com.alee.laf.button.WebButton();
        buttonEditar = new com.alee.laf.button.WebButton();
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
        ));
        tableLista.setEditable(false);
        tableLista.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(tableLista);

        panelOpcoes.setBackground(new java.awt.Color(255, 255, 255));

        buttonAdd.setText("Novo");
        buttonAdd.setMaximumSize(new java.awt.Dimension(24, 24));
        buttonAdd.setPreferredSize(new java.awt.Dimension(24, 24));

        buttonExcluir.setText("Excluir");

        buttonEditar.setText("Editar");
        buttonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditarActionPerformed(evt);
            }
        });

        buttonRelatorio.setText("Gerar relatórios");

        javax.swing.GroupLayout panelOpcoesLayout = new javax.swing.GroupLayout(panelOpcoes);
        panelOpcoes.setLayout(panelOpcoesLayout);
        panelOpcoesLayout.setHorizontalGroup(
            panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(buttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void buttonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonEditarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.button.WebButton buttonAdd;
    private com.alee.laf.button.WebButton buttonEditar;
    private com.alee.laf.button.WebButton buttonExcluir;
    private com.alee.laf.button.WebButton buttonRelatorio;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelOpcoes;
    private com.alee.laf.table.WebTable tableLista;
    private components.TextFieldBuscar txtBuscar;
    // End of variables declaration//GEN-END:variables
}
