package forms.patrimonio;

import components.JFrameBusca;
import java.util.List;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import model.PatAtivoImobilizado;
import model.PatHistoricoDepreciacao;
import utils.Utils;

public class FormHistoricoDepreciacoes extends JFrameBusca {

    private PatAtivoImobilizado ativoImobilizado;

    public FormHistoricoDepreciacoes() {
        initComponents();
        this.setTitle("Histórico depreciação");
        this.setLocationRelativeTo(null);
    }

    public void setAtivoImobilizado(PatAtivoImobilizado ativoImobilizado) {
        this.ativoImobilizado = ativoImobilizado;
        this.atualizarTabela();
    }

    private void atualizarTabela() {
        new LoadHistoricosDepreciacoes().execute();
    }

    private class LoadHistoricosDepreciacoes extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() throws Exception {

            DefaultTableModel model = (DefaultTableModel) tabelaHistoricoDepreciacoes.getModel();

            List<PatHistoricoDepreciacao> historicoDepreciacoes = ativoImobilizado.getPatHistoricoDepreciacaos();
            for (PatHistoricoDepreciacao hd : historicoDepreciacoes) {
                Object[] o = new Object[3];
                o[0] = hd.getHistoricoDepreciacaoDescricao();
                o[1] = Utils.formatData(hd.getHistoricoDepreciacaoDia());
                o[2] = hd.getHistoricoDepreciacaoValor();
                model.addRow(o);
            }

            tabelaHistoricoDepreciacoes.setModel(model);
            return null;
        }

        @Override
        public void done() {

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaHistoricoDepreciacoes = new com.alee.laf.table.WebTable();

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
                "Descrição", "Data", "Valor depreciação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
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
    // End of variables declaration//GEN-END:variables
}
