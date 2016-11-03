package forms.busca;

import components.JFrameBusca;
import components.TextFieldFK;
import dao.CategoriaDAO;
import java.util.List;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import model.EstCategoria;
import utils.Utils;

public class FormBuscaCategoria extends JFrameBusca {

    private LoadCategorias loadCategorias;
    private List<EstCategoria> categorias;

    public FormBuscaCategoria() {
        initComponents();
        this.buscarCategorias();
        this.setLocationRelativeTo(null);
    }

    public void buscarCategorias() {
        this.loadCategorias = new LoadCategorias();
        this.loadCategorias.execute();
    }

    public class LoadCategorias extends SwingWorker<Void, Void> {

        protected Void doInBackground() throws Exception {

            DefaultTableModel model = (DefaultTableModel) tabelaCategoria.getModel();

            categorias = new CategoriaDAO().getAll();
            for (EstCategoria categoria : categorias) {
                Object[] o = new Object[2];
                o[0] = categoria.getCategoriaId();
                o[1] = categoria.getCategoriaDescricao();
                model.addRow(o);
            }
            tabelaCategoria.setModel(model);
            return null;
        }

        public void done() {

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtBuscar = new components.TextFieldBuscar();
        panelOpcoes = new javax.swing.JPanel();
        buttonSelecionar = new com.alee.laf.button.WebButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaCategoria = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        panelOpcoes.setBackground(new java.awt.Color(255, 255, 255));

        buttonSelecionar.setText("Selecionar");
        buttonSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSelecionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOpcoesLayout = new javax.swing.GroupLayout(panelOpcoes);
        panelOpcoes.setLayout(panelOpcoesLayout);
        panelOpcoesLayout.setHorizontalGroup(
            panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcoesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonSelecionar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelOpcoesLayout.setVerticalGroup(
            panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOpcoesLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(buttonSelecionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        tabelaCategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descrição"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabelaCategoria);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelOpcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.getFrameBloquear().setEnabled(true);
    }//GEN-LAST:event_formWindowClosing

    private void buttonSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSelecionarActionPerformed
        int linhaselecionada = this.tabelaCategoria.getSelectedRow();
        if (linhaselecionada < 0) {
            Utils.notificacao("Selecione uma categoria!", Utils.TipoNotificacao.erro, 0);
            return;
        }

        if (this.getFrameBuscaTipo() == JFrameBuscaTipo.textFieldFK) {
            TextFieldFK text = this.getTextFieldFK();
            EstCategoria categoria = this.categorias.get(linhaselecionada);
            text.setText(categoria.getCategoriaId() + " - " + categoria.getCategoriaDescricao());
            text.setValue(categoria);
        }

        this.getFrameBloquear().setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_buttonSelecionarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.button.WebButton buttonSelecionar;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelOpcoes;
    private javax.swing.JTable tabelaCategoria;
    private components.TextFieldBuscar txtBuscar;
    // End of variables declaration//GEN-END:variables
}
