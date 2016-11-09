package components.panelsListagem;

import com.alee.laf.optionpane.WebOptionPane;
import com.alee.laf.panel.WebPanel;
import dao.PatAtivoImobilizadoDAO;
import dao.PatDepreciacaoDAO;
import dao.PatHistoricoDepreciacaoDAO;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.SwingWorker;
import model.PatAtivoImobilizado;
import model.PatHistoricoDepreciacao;
import utils.Utils;

public class PanelDepreciar extends WebPanel {

    private static PanelDepreciar panelGerarDepreciacao;

    private PatHistoricoDepreciacao ultimaDepreciacao;
    private boolean jaDepreciadoMesAtual;

    private boolean depreciando;

    private int ativosSize;
    private int valueProgress;

    public PanelDepreciar() {
        this.initComponents();

        this.txtMesDepreciar.setDateFormat(new SimpleDateFormat("MM/yyyy"));
        this.txtMesDepreciar.setDate(new Date());

        new LoadDados().execute();
    }

    public static PanelDepreciar getInstance() {
        if (panelGerarDepreciacao == null) {
            panelGerarDepreciacao = new PanelDepreciar();
        }

        return panelGerarDepreciacao;
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
            depreciando = true;
            List<PatAtivoImobilizado> ativos = new PatAtivoImobilizadoDAO().getAll(0);
            ativosSize = 5;
            progressBar.setMaximum(ativosSize);
            progressBar.setValue(valueProgress);
            Date dia = txtMesDepreciar.getDate();
            for (int i = 0; i < ativosSize; i++) {
                PatAtivoImobilizado ativo = ativos.get(i);
                new PatDepreciacaoDAO().depreciarAtivoImobilizado(ativo, dia);
                progressBar.setValue(++valueProgress);
                setPorcentagem();
                Thread.sleep(1000);
            }
            return null;
        }

        @Override
        public void done() {
            depreciando = false;
            fecharPanel();
            Utils.notificacao("Depreciação finalizada!", Utils.TipoNotificacao.ok, 0);
        }
    }

    private void setPorcentagem() {
        this.lblPorcentagem.setText(Utils.removerCaracteresDoubleString(Utils.format((valueProgress * 100) / ativosSize)) + "%");
    }

    public void fecharPanel() {
        if (!this.depreciando) {
            panelGerarDepreciacao = null;
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
        this.buttonDepreciar.addActionListener(depreciar);
        this.buttonCancelar.addActionListener(cancelar);
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
        lblPorcentagem = new com.alee.laf.label.WebLabel();
        txtMesDepreciar = new com.alee.extended.date.WebDateField();
        webLabel1 = new com.alee.laf.label.WebLabel();

        setMinimumSize(new java.awt.Dimension(565, 496));

        buttonDepreciar.setText("Depreciar");
        buttonDepreciar.setEnabled(false);

        buttonCancelar.setText("Fechar");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Último mês depreciado:");

        lblDataDepreciado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDataDepreciado.setText("Buscando informações...");

        lblPorcentagem.setText("0.00%");

        webLabel1.setText("Selecione o mês para depreciar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(buttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonDepreciar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblPorcentagem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(lblDataDepreciado))
                            .addComponent(webLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMesDepreciar, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(93, 93, 93)
                .addComponent(webLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMesDepreciar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 229, Short.MAX_VALUE)
                .addComponent(lblPorcentagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
    private com.alee.laf.label.WebLabel lblPorcentagem;
    private com.alee.laf.progressbar.WebProgressBar progressBar;
    private com.alee.extended.date.WebDateField txtMesDepreciar;
    private com.alee.laf.label.WebLabel webLabel1;
    // End of variables declaration//GEN-END:variables
}
