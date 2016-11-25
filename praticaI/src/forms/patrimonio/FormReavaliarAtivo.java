package forms.patrimonio;

import components.Validador;
import components.panelsListagem.PanelConsultaAtivoImobilizado;
import dao.PatAtivoImobilizadoDAO;
import dao.PatBaixaDAO;
import dao.PatHistoricoDepreciacaoDAO;
import forms.FormPrincipal;
import forms.busca.FormBuscaTipoBaixa;
import java.util.Calendar;
import model.PatAtivoImobilizado;
import model.PatBaixa;
import model.PatHistoricoDepreciacao;
import model.PatTipoBaixa;
import utils.Utils;

public class FormReavaliarAtivo extends javax.swing.JFrame {

    public Validador validador;
    private PatAtivoImobilizado ativoImobilizado;

    private PatBaixa baixaAtivo;
    private PatTipoBaixa tipoBaixa;

    private PanelConsultaAtivoImobilizado panelConsultaAtivoImobilizado;

    public FormReavaliarAtivo() {
        initComponents();
        this.setTitle("Reavaliar ativo");
        this.setLocationRelativeTo(null);

        this.txtDataReavaliacao.setDateFormat(utils.Utils.formatoDataPadrao);
        this.txtDataReavaliacao.setDate(Calendar.getInstance().getTime());
    }

    public void setAtivoImobilizado(PatAtivoImobilizado ativoImobilizado, PanelConsultaAtivoImobilizado panelConsultaAtivoImobilizado) {
        this.ativoImobilizado = ativoImobilizado;
        this.panelConsultaAtivoImobilizado = panelConsultaAtivoImobilizado;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        webDateFieldStyle1 = new com.alee.extended.date.WebDateFieldStyle();
        enumConverter1 = new com.thoughtworks.xstream.converters.enums.EnumConverter();
        webPanelUI1 = new com.alee.laf.panel.WebPanelUI();
        webPanelStyle1 = new com.alee.laf.panel.WebPanelStyle();
        webPanelPainter1 = new com.alee.managers.style.skin.web.WebPanelPainter();
        webPanel1 = new com.alee.laf.panel.WebPanel();
        jScrollBar1 = new javax.swing.JScrollBar();
        panelOpcoes = new javax.swing.JPanel();
        btnSalvar = new com.alee.laf.button.WebButton();
        btnCancelar = new com.alee.laf.button.WebButton();
        jPanel1 = new javax.swing.JPanel();
        txtDataReavaliacao = new com.alee.extended.date.WebDateField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNovoValor = new components.TextFieldValorMonetario();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(null);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        panelOpcoes.setBackground(new java.awt.Color(255, 255, 255));

        btnSalvar.setText("Reavaliar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOpcoesLayout = new javax.swing.GroupLayout(panelOpcoes);
        panelOpcoes.setLayout(panelOpcoesLayout);
        panelOpcoesLayout.setHorizontalGroup(
            panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOpcoesLayout.createSequentialGroup()
                .addContainerGap(199, Short.MAX_VALUE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelOpcoesLayout.setVerticalGroup(
            panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOpcoesLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Data reavaliação");

        jLabel4.setText("Novo valor");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtNovoValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtDataReavaliacao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addComponent(jLabel4)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDataReavaliacao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(0, 0, 0)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNovoValor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelOpcoes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
        FormPrincipal.getInstance().setEnabled(true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed

        double valor = this.txtNovoValor.getValue();
        if (valor <= 0) {
            Utils.notificacao("O valor precisa ser maior que 0!", Utils.TipoNotificacao.erro, 10000);
            return;
        }

        Calendar c = Calendar.getInstance();
        c.setTime(this.txtDataReavaliacao.getDate());

        PatHistoricoDepreciacao historicoDepreciacao = new PatHistoricoDepreciacao();

        this.ativoImobilizado.setAtivoValorAtual(valor);

        historicoDepreciacao.setHistoricoDepreciacaoDescricao("Reavaliação do ativo no valor de " + valor);
        historicoDepreciacao.setHistoricoDepreciacaoDia(c.getTime());
        historicoDepreciacao.setHistoricoDepreciacaoAno(c.get(Calendar.YEAR));
        historicoDepreciacao.setHistoricoDepreciacaoMes(c.get(Calendar.MONTH));
        historicoDepreciacao.setPatAtivoImobilizado(this.ativoImobilizado);
        historicoDepreciacao.setHistoricoDepreciacaoValor(0);

        new PatAtivoImobilizadoDAO().reavaliarAtivo(this.ativoImobilizado, historicoDepreciacao);

        Utils.notificacao("Ativo reavaliado", Utils.TipoNotificacao.ok, 0);
        this.panelConsultaAtivoImobilizado.resetBusca();
        FormPrincipal.getInstance().setEnabled(true);

        this.dispose();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        FormPrincipal.getInstance().setEnabled(true);
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.button.WebButton btnCancelar;
    private com.alee.laf.button.WebButton btnSalvar;
    private com.thoughtworks.xstream.converters.enums.EnumConverter enumConverter1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JPanel panelOpcoes;
    private com.alee.extended.date.WebDateField txtDataReavaliacao;
    private components.TextFieldValorMonetario txtNovoValor;
    private com.alee.extended.date.WebDateFieldStyle webDateFieldStyle1;
    private com.alee.laf.panel.WebPanel webPanel1;
    private com.alee.managers.style.skin.web.WebPanelPainter webPanelPainter1;
    private com.alee.laf.panel.WebPanelStyle webPanelStyle1;
    private com.alee.laf.panel.WebPanelUI webPanelUI1;
    // End of variables declaration//GEN-END:variables
}
