package forms;

import forms.patrimonio.FormNotaFiscal;
import forms.patrimonio.FormAtivoImobilizado;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.desktoppane.WebDesktopPane;
import com.alee.managers.language.LanguageManager;
import components.MoverComponente;
import components.IconDesktop;
import components.PanelNotificacoes;
import components.PanelWidgetSaldo;
import forms.patrimonio.FormAtivoImobilizado;
import forms.patrimonio.FormDepreciacoes;
import utils.Utils;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class FormPrincipal extends javax.swing.JFrame {

    private static FormPrincipal instance = null;
    private PanelNotificacoes notificacoes;
    private PanelWidgetSaldo saldo;

    public FormPrincipal() {
        initComponents();
    }

    public static FormPrincipal getInstance() {
        if (instance == null) {
            instance = new FormPrincipal();
        }
        return instance;
    }

    public WebDesktopPane getDesktopPanel() {
        return this.desktopPanel;
    }

    public static void setBloqueado(boolean bloqueado) {
        FormPrincipal f = getInstance();
        f.setEnabled(!bloqueado);
    }

    public void setQntdNotificacoes(String qntd) {
        this.lblQntdNotificacoes.setText(qntd);
    }

    private void loadComponents() {
        this.loadNotaFiscal();
        this.loadAtivoImobilizado();
        this.loadContasPagar();
        this.loadBackgroud();
        this.loadWidgetSaldo();
        this.loadFluxoCaixa();
        this.loadContasReceber();
        this.loadaddpessaoa();
        this.loadControleEstoque();
        this.loadMateriais();
    }

    private void loadWidgetSaldo() {
        this.saldo = PanelWidgetSaldo.getInstance();

        Dimension d = this.saldo.getMinimumSize();

        int w = (int) d.getWidth();
        int h = (int) d.getHeight();
        int x = this.desktopPanel.getWidth() - w;
        this.saldo.setBounds(x, 0, w, h);
        this.desktopPanel.add(this.saldo);
        this.desktopPanel.setComponentZOrder(this.saldo, 1);
    }

    private void loadBackgroud() {
        this.desktopPanel.setOpaque(true);
        this.desktopPanel.setBackground(Utils.CoresPadrao.fundoDesktop);

        ImageIcon logoImagem = Utils.getImage(Utils.Image.logo);
        JLabel logo = new JLabel(logoImagem);
        logo.setBounds(this.desktopPanel.getWidth() - logoImagem.getIconWidth() - 2, this.desktopPanel.getHeight() - logoImagem.getIconHeight() - 2, logoImagem.getIconWidth(), logoImagem.getIconHeight());
        this.desktopPanel.add(logo);

        this.panelInformacoes.setBackground(Utils.CoresPadrao.fundoPadrao);

        this.lblNotificacoes.setIcon(Utils.getImage(Utils.Image.notificacao));

    }

    public void toggleNotificacoes() {
        if (!this.notificacoes.isOpen) {
            int w = this.desktopPanel.getWidth() / 4;
            int h = this.desktopPanel.getHeight();
            int x = this.desktopPanel.getWidth() - w;
            this.notificacoes.setBounds(x + 3, 15, w, h - 12);
            this.desktopPanel.add(this.notificacoes);
            this.desktopPanel.setComponentZOrder(this.notificacoes, 1);
        } else {
            this.desktopPanel.remove(this.notificacoes);
            this.desktopPanel.repaint();
        }
        this.notificacoes.isOpen = !this.notificacoes.isOpen;
    }

    private void carrregarNotificacoes() {
        if (this.notificacoes == null) {
            this.notificacoes = new PanelNotificacoes();
        }
    }

    private void loadNotaFiscal() {

        final IconDesktop iconDesktop = new IconDesktop("Notas Fiscais", Utils.getImage(Utils.Image.notafiscal));

        iconDesktop.setActionListener((e) -> {
            if (iconDesktop.getClientProperty(MoverComponente.DRAGGED_MARK) != null) {
                return;
            }
            FormNotaFiscal form = new FormNotaFiscal();
            this.desktopPanel.add(form);
            form.open();
            try {
                form.setMaximum(true);
            } catch (PropertyVetoException ex) {
            }
        });

        iconDesktop.setLocation(new Point(0, 0));
        this.desktopPanel.add(iconDesktop);
    }

    private void loadAtivoImobilizado() {

        final IconDesktop iconDesktop = new IconDesktop("Ativos Imobilizados", Utils.getImage(Utils.Image.ativoimobilizado));

        iconDesktop.setActionListener((e) -> {
            if (iconDesktop.getClientProperty(MoverComponente.DRAGGED_MARK) != null) {
                return;
            }
            FormAtivoImobilizado form = new FormAtivoImobilizado();
            this.desktopPanel.add(form);
            form.open();
            try {
                form.setMaximum(true);
            } catch (PropertyVetoException ex) {
            }
        });

        iconDesktop.setLocation(new Point(0, 80));
        this.desktopPanel.add(iconDesktop);
    }

    private void loadContasPagar() {

        final IconDesktop iconDesktop = new IconDesktop("Contas à pagar", Utils.getImage(Utils.Image.pagar));

        iconDesktop.setActionListener((e) -> {
            if (iconDesktop.getClientProperty(MoverComponente.DRAGGED_MARK) != null) {
                return;
            }
            FormContaPagar form = new FormContaPagar();
            this.desktopPanel.add(form);
            form.open();
            try {
                form.setMaximum(true);
            } catch (PropertyVetoException ex) {
            }
        });

        iconDesktop.setLocation(new Point(0, 160));
        this.desktopPanel.add(iconDesktop);
    }

    private void loadFluxoCaixa() {

        final IconDesktop iconDesktop = new IconDesktop("Fluxo de Caixa", Utils.getImage(Utils.Image.FluxoCaixa));

        iconDesktop.setActionListener((e) -> {
            if (iconDesktop.getClientProperty(MoverComponente.DRAGGED_MARK) != null) {
                return;
            }
            FormFluxodeCaixa form = new FormFluxodeCaixa();
            this.desktopPanel.add(form);
            form.open();
            try {
                form.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(FormPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        iconDesktop.setLocation(new Point(0, 240));
        this.desktopPanel.add(iconDesktop);

    }

    private void loadContasReceber() {

        final IconDesktop iconDesktop = new IconDesktop("Contas a Receber", Utils.getImage(Utils.Image.ContasReceber));

        iconDesktop.setActionListener((e) -> {
            if (iconDesktop.getClientProperty(MoverComponente.DRAGGED_MARK) != null) {
                return;
            }
            FormContaReceber form = new FormContaReceber();
            this.desktopPanel.add(form);
            form.open();
            try {
                form.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(FormPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        iconDesktop.setLocation(new Point(0, 320));
        this.desktopPanel.add(iconDesktop);

    }

    private void loadaddpessaoa() {

        final IconDesktop iconDesktop = new IconDesktop("Pessoa", Utils.getImage(Utils.Image.addpessoa));

        iconDesktop.setActionListener((e) -> {
            if (iconDesktop.getClientProperty(MoverComponente.DRAGGED_MARK) != null) {
                return;
            }
            FormPessoa form = new FormPessoa();
            this.desktopPanel.add(form);
            form.open();
            try {
                form.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(FormPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        iconDesktop.setLocation(new Point(0, 550));
        this.desktopPanel.add(iconDesktop);

    }

    private void loadControleEstoque() {

        final IconDesktop iconDesktop = new IconDesktop("Estoque", Utils.getImage(Utils.Image.estoque));

        iconDesktop.setActionListener((e) -> {
            if (iconDesktop.getClientProperty(MoverComponente.DRAGGED_MARK) != null) {
                return;
            }
            FormEstoque form = new FormEstoque(this, rootPaneCheckingEnabled);
            form.setVisible(true);
        });

        iconDesktop.setLocation(new Point(0, 440));
        this.desktopPanel.add(iconDesktop);
    }

    private void loadMateriais() {

        final IconDesktop iconDesktop = new IconDesktop("Materiais", Utils.getImage(Utils.Image.estoque));

        iconDesktop.setActionListener((e) -> {
            if (iconDesktop.getClientProperty(MoverComponente.DRAGGED_MARK) != null) {
                return;
            }
            FormMateriais form = new FormMateriais(this, rootPaneCheckingEnabled);
            form.setVisible(true);

        });

        iconDesktop.setLocation(new Point(100, 440));
        this.desktopPanel.add(iconDesktop);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPanel = new com.alee.laf.desktoppane.WebDesktopPane();
        imageBackgroud = new com.alee.extended.image.WebImage();
        panelInformacoes = new javax.swing.JPanel();
        lblNomeUsuario = new javax.swing.JLabel();
        labelBorda = new javax.swing.JLabel();
        panelDetalhesNotificacao = new javax.swing.JPanel();
        lblNotificacoes = new javax.swing.JLabel();
        lblQntdNotificacoes = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(778, 508));

        desktopPanel.setOpaque(false);

        panelInformacoes.setBackground(new java.awt.Color(255, 255, 255));
        panelInformacoes.setMaximumSize(new java.awt.Dimension(32767, 50));
        panelInformacoes.setMinimumSize(new java.awt.Dimension(0, 50));

        lblNomeUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNomeUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblNomeUsuario.setText("Claudio Scheer (ADMIN)");

        labelBorda.setBackground(new java.awt.Color(153, 153, 153));
        labelBorda.setOpaque(true);

        panelDetalhesNotificacao.setOpaque(false);

        lblNotificacoes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblNotificacoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNotificacoesMouseClicked(evt);
            }
        });

        lblQntdNotificacoes.setBackground(new java.awt.Color(255, 255, 255));
        lblQntdNotificacoes.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblQntdNotificacoes.setForeground(new java.awt.Color(255, 0, 0));
        lblQntdNotificacoes.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout panelDetalhesNotificacaoLayout = new javax.swing.GroupLayout(panelDetalhesNotificacao);
        panelDetalhesNotificacao.setLayout(panelDetalhesNotificacaoLayout);
        panelDetalhesNotificacaoLayout.setHorizontalGroup(
            panelDetalhesNotificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetalhesNotificacaoLayout.createSequentialGroup()
                .addComponent(lblNotificacoes, javax.swing.GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelDetalhesNotificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDetalhesNotificacaoLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblQntdNotificacoes)
                    .addGap(5, 5, 5)))
        );
        panelDetalhesNotificacaoLayout.setVerticalGroup(
            panelDetalhesNotificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDetalhesNotificacaoLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(lblNotificacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelDetalhesNotificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDetalhesNotificacaoLayout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(lblQntdNotificacoes)
                    .addGap(0, 0, 0)))
        );

        javax.swing.GroupLayout panelInformacoesLayout = new javax.swing.GroupLayout(panelInformacoes);
        panelInformacoes.setLayout(panelInformacoesLayout);
        panelInformacoesLayout.setHorizontalGroup(
            panelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelDetalhesNotificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
            .addComponent(labelBorda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelInformacoesLayout.setVerticalGroup(
            panelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacoesLayout.createSequentialGroup()
                .addComponent(labelBorda, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(panelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNomeUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(panelDetalhesNotificacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelInformacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(desktopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1033, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(imageBackgroud, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1033, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(panelInformacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(imageBackgroud, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblNotificacoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNotificacoesMouseClicked
        this.toggleNotificacoes();
    }//GEN-LAST:event_lblNotificacoesMouseClicked

    public static void start() {

        WebLookAndFeel.setDecorateAllWindows(true);
        WebLookAndFeel.setDecorateDialogs(true);
        WebLookAndFeel.setDecorateFrames(true);

        LanguageManager.setDefaultLanguage(LanguageManager.PORTUGUESE);

        WebLookAndFeel.install();

        FormPrincipal frame = FormPrincipal.getInstance();
        frame.setVisible(true);

        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle r = env.getMaximumWindowBounds();
        frame.setMaximizedBounds(r);
        frame.setExtendedState(frame.getState() | JFrame.MAXIMIZED_BOTH);
        frame.loadComponents();
        frame.carrregarNotificacoes();
    }

    public static void main(String[] args) {
        start();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.desktoppane.WebDesktopPane desktopPanel;
    private com.alee.extended.image.WebImage imageBackgroud;
    private javax.swing.JLabel labelBorda;
    private javax.swing.JLabel lblNomeUsuario;
    private javax.swing.JLabel lblNotificacoes;
    private javax.swing.JLabel lblQntdNotificacoes;
    private javax.swing.JPanel panelDetalhesNotificacao;
    private javax.swing.JPanel panelInformacoes;
    // End of variables declaration//GEN-END:variables
}
