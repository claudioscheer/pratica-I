package components.panelsListagem;

import com.alee.laf.panel.WebPanel;
import com.alee.laf.table.WebTable;
import dao.PessoaDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import model.CarPessoa;

public class PanelConsultaPessoa extends WebPanel implements ActionListener {

    private LoadPessoas loadPessoas;
    private List<CarPessoa> pessoas;
    
    private int indexSelecionado;
    
    public PanelConsultaPessoa() {
        initComponents();
        this.loadDatas();
        this.txtBuscar.setEventBuscar((e) -> {
            System.out.println("buscar");
        });
    }

    public void setEvents(ActionListener add, ActionListener edit, ActionListener delete) {
        buttonAdd.addActionListener(add);
        buttonEditar.addActionListener(edit);
        buttonExcluir.addActionListener(delete);
    }

    public class LoadPessoas extends SwingWorker<Void, Void> {
        protected Void doInBackground() throws Exception {
            pessoas = new PessoaDAO().getAll();
            atualizarTabela();
            return null;
        }
        public void done() {
        }
    }

    public int getIndiceSelecionado() {
        return this.indexSelecionado;
    }
    
    private void atualizarTabela() {
        DefaultTableModel model = (DefaultTableModel) tableLista.getModel();
        for (CarPessoa ativo : pessoas) {
            model.addRow(pessoaToArray(ativo));
        }
        tableLista.setModel(model);
    }
    
    private Object[] pessoaToArray(CarPessoa p) {
        Object[] o = new Object[4];
        o[0] = p.getPessoaNome();
        o[1] = p.getPessoaCnpjCpf();
        o[2] = p.getPessoaFone();
        o[3] = p.getPessoaEmail();
        return o;
    }
    
    private void loadDatas() {
        this.loadPessoas  = new LoadPessoas();
        this.loadPessoas.execute(); 
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
        txtBuscar = new components.TextFieldBuscar();

        setMinimumSize(new java.awt.Dimension(565, 496));

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tableLista.setAutoCreateRowSorter(true);
        tableLista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "CPF / CNPJ", "Fone", "E-mail"
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

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });

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

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void buttonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonEditarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.button.WebButton buttonAdd;
    private com.alee.laf.button.WebButton buttonEditar;
    private com.alee.laf.button.WebButton buttonExcluir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelOpcoes;
    private com.alee.laf.table.WebTable tableLista;
    private components.TextFieldBuscar txtBuscar;
    // End of variables declaration//GEN-END:variables
}
