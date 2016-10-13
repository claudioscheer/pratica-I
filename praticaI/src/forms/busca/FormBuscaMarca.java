package forms.busca;

import components.JFrameBusca;
import components.TextFieldFK;
import dao.CategoriaDAO;
import dao.MarcaDAO;
import java.util.List;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import model.Categoria;
import model.Marca;
import utils.Utils;

public class FormBuscaMarca extends JFrameBusca {

    private LoadMarcas loadMarcas;
    private List<Marca> marcas;

    public FormBuscaMarca() {
        initComponents();
        this.buscarMarcas();
        this.setLocationRelativeTo(null);
    }

    public void buscarMarcas() {
        this.loadMarcas = new LoadMarcas();
        this.loadMarcas.execute();
    }

    public class LoadMarcas extends SwingWorker<Void, Void> {

        protected Void doInBackground() throws Exception {

            DefaultTableModel model = (DefaultTableModel) tabelaMarcas.getModel();

            marcas = new MarcaDAO().getAll();
            for (Marca marca : marcas) {
                Object[] o = new Object[2];
                o[0] = marca.getCodigo();
                o[1] = marca.getDescricao();
                model.addRow(o);
            }
            tabelaMarcas.setModel(model);
            return null;
        }

        public void done() {

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaMarcas = new javax.swing.JTable();
        txtBuscar = new components.TextFieldBuscar();
        panelOpcoes = new javax.swing.JPanel();
        buttonSelecionar = new com.alee.laf.button.WebButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tabelaMarcas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tabelaMarcas);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelOpcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.getFrameBloquear().setEnabled(true);
    }//GEN-LAST:event_formWindowClosing

    private void buttonSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSelecionarActionPerformed
        int linhaselecionada = this.tabelaMarcas.getSelectedRow();
        if (linhaselecionada < 0) {
            Utils.notificacao("Selecione uma marca!", Utils.TipoNotificacao.erro, 0);
            return;
        }

        if (this.getFrameBuscaTipo() == JFrameBuscaTipo.textFieldFK) {
            TextFieldFK text = this.getTextFieldFK();
            Marca marca = this.marcas.get(linhaselecionada);
            text.setText(marca.getCodigo() + " - " + marca.getDescricao());
            text.setValue(marca);
        }

        this.getFrameBloquear().setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_buttonSelecionarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.button.WebButton buttonSelecionar;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelOpcoes;
    private javax.swing.JTable tabelaMarcas;
    private components.TextFieldBuscar txtBuscar;
    // End of variables declaration//GEN-END:variables
}
