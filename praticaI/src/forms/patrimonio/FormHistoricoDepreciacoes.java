package forms.patrimonio;

import components.JFrameBusca;
import dao.HistoricoDepreciacaoDAO;
import java.util.List;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import model.PatAtivoImobilizado;
import model.PatHistoricoDepreciacao;

public class FormHistoricoDepreciacoes extends JFrameBusca {

    private PatAtivoImobilizado ativoImobilizado;

    public FormHistoricoDepreciacoes() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.txtBuscar.showComboOpcoes(false);
    }

    public void setAtivoImobilizado(PatAtivoImobilizado ativoImobilizado) {
        this.ativoImobilizado = ativoImobilizado;
        this.atualizarTabela();
    }

    private void atualizarTabela() {
        LoadHistoricosDepreciacoes load = new LoadHistoricosDepreciacoes();
        load.execute();
    }

    private class LoadHistoricosDepreciacoes extends SwingWorker<Void, Void> {

        protected Void doInBackground() throws Exception {

            DefaultTableModel model = (DefaultTableModel) tabelaHistoricoDepreciacoes.getModel();

            List<PatHistoricoDepreciacao> historicoDepreciacoes = new HistoricoDepreciacaoDAO().getAll(ativoImobilizado);
            for (PatHistoricoDepreciacao hd : historicoDepreciacoes) {
                Object[] o = new Object[2];
                o[0] = hd.getHistoricoDepreciacaoDia();
                o[1] = hd.getHistoricoDepreciacaoValor();
                model.addRow(o);
            }

            tabelaHistoricoDepreciacoes.setModel(model);
            return null;
        }

        public void done() {

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textFieldBuscar1 = new components.TextFieldBuscar();
        txtBuscar = new components.TextFieldBuscar();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaHistoricoDepreciacoes = new com.alee.laf.table.WebTable();

        textFieldBuscar1.setText("textFieldBuscar1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tabelaHistoricoDepreciacoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dia", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaHistoricoDepreciacoes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.getFrameBloquear().setEnabled(true);
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private com.alee.laf.table.WebTable tabelaHistoricoDepreciacoes;
    private components.TextFieldBuscar textFieldBuscar1;
    private components.TextFieldBuscar txtBuscar;
    // End of variables declaration//GEN-END:variables
}
