package components.panelsListagem;

import com.alee.laf.optionpane.WebOptionPane;
import com.alee.laf.panel.WebPanel;
import dao.PatAtivoImobilizadoDAO;
import dao.PatHistoricoDepreciacaoDAO;
import forms.patrimonio.FormHistoricoDepreciacoes;
import forms.FormPrincipal;
import forms.patrimonio.FormQrCodeAtivoImobilizado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import model.PatAtivoImobilizado;
import model.PatHistoricoDepreciacao;
import utils.Utils;

public class PanelGerarDepreciacao extends WebPanel {

    private PatHistoricoDepreciacao ultimaDepreciacao;
    private boolean jaDepreciadoMesAtual;

    private int valueProgress;

    public PanelGerarDepreciacao() {
        this.initComponents();
        new LoadDados().execute();
    }

    private class LoadDados extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() throws Exception {
            getUltimaDepreciacao();
            return null;
        }

        @Override
        public void done() {
            buttonDepreciar.setEnabled(true);
        }
    }

    public void getUltimaDepreciacao() {
        PatHistoricoDepreciacao depreciacao = new PatHistoricoDepreciacaoDAO().getUltimaDepreciacao();
        String mes_ano;
        if (depreciacao == null) {
            mes_ano = "Nenhum valor depreciado ainda.";
        } else {
            Calendar c = Calendar.getInstance();
            if (c.get(Calendar.MONTH) == depreciacao.getHistoricoDepreciacaoMes() && c.get(Calendar.YEAR) == depreciacao.getHistoricoDepreciacaoAno()) {
                mes_ano = "Mês atual já foi depreciado.";
                this.jaDepreciadoMesAtual = true;
            } else {
                this.jaDepreciadoMesAtual = false;
                mes_ano = new SimpleDateFormat("MMMM").format(depreciacao.getHistoricoDepreciacaoDia()).toLowerCase() + "/" + depreciacao.getHistoricoDepreciacaoAno();
            }
        }
        this.lblDataDepreciado.setText(mes_ano);
    }

    private class RealizarDepreciacao extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() throws Exception {
            int ativos = 10;
            progressBar.setMaximum(ativos);
            progressBar.setValue(valueProgress);
            for (int i = 0; i < ativos; i++) {
                Thread.sleep(1000);
                progressBar.setValue(++valueProgress);
            }
            return null;
        }

        @Override
        public void done() {
            Utils.notificacao("Depreciação finalizada!", Utils.TipoNotificacao.ok, 0);
        }
    }

    public void startDepreciacao() {

        if (this.jaDepreciadoMesAtual) {
            WebOptionPane.showMessageDialog(this, "Serão depreciados apenas os ativos não depreciados este mês!", "Aviso", WebOptionPane.INFORMATION_MESSAGE);
        }

        this.valueProgress = 0;
        new RealizarDepreciacao().execute();
    }

    public void setEvents(ActionListener depreciar, ActionListener cancelar) {
        buttonDepreciar.addActionListener(depreciar);
        buttonCancelar.addActionListener(cancelar);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        buttonDepreciar = new com.alee.laf.button.WebButton();
        buttonCancelar = new com.alee.laf.button.WebButton();
        jLabel1 = new javax.swing.JLabel();
        lblDataDepreciado = new javax.swing.JLabel();
        progressBar = new com.alee.laf.progressbar.WebProgressBar();

        setMinimumSize(new java.awt.Dimension(565, 496));

        buttonDepreciar.setText("Depreciar");
        buttonDepreciar.setEnabled(false);

        buttonCancelar.setText("Cancelar");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Último mês depreciado:");

        lblDataDepreciado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDataDepreciado.setText("Buscando informações...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonDepreciar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lblDataDepreciado)
                        .addGap(0, 228, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblDataDepreciado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 392, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonDepreciar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.button.WebButton buttonCancelar;
    private com.alee.laf.button.WebButton buttonDepreciar;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblDataDepreciado;
    private com.alee.laf.progressbar.WebProgressBar progressBar;
    // End of variables declaration//GEN-END:variables
}
