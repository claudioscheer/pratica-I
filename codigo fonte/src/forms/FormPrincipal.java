package forms;

import com.alee.laf.WebLookAndFeel;
import com.alee.managers.language.LanguageManager;
import components.DesktopPaneIconMoveAdapter;
import components.IconDesktop;
import components.PanelNotificacoes;
import helper.Helper;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FormPrincipal extends javax.swing.JFrame {

    private PanelNotificacoes notificacoes;

    public FormPrincipal() {
        initComponents();
        this.loadComponents();
    }

    private void loadComponents() {
        this.loadEspecificacao();
        this.loadBackgroud();
    }

    private void loadBackgroud() {
        imageBackgroud.setIcon(Helper.getImage(Helper.Image.fundo));

        JLabel logo = new JLabel(Helper.getImage(Helper.Image.logo));
        logo.setBounds(desktopPanel.getWidth() + 50, desktopPanel.getHeight(), 246, 169);
        desktopPanel.add(logo);

        panelInformacoes.setBackground(new Color(0, 0, 0, 255));

        lblNotificacoes.setIcon(Helper.getImage(Helper.Image.notificacao));

        lblNotificacoes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (notificacoes == null) {
                    notificacoes = new PanelNotificacoes();
                }

                if (!notificacoes.isOpen) {
                    int w = desktopPanel.getWidth() / 4;
                    int h = desktopPanel.getHeight();
                    int x = desktopPanel.getWidth() - w;
                    notificacoes.setBounds(x, 15, w, h - 15);
                    desktopPanel.add(notificacoes);
                    desktopPanel.setComponentZOrder(notificacoes, 1);
                } else {
                    desktopPanel.remove(notificacoes);
                    desktopPanel.revalidate();
                    desktopPanel.repaint();
                }
                notificacoes.isOpen = !notificacoes.isOpen;
            }
        });

    }

    private void loadEspecificacao() {

        final IconDesktop iconDesktop = new IconDesktop("Especificação", Helper.getImage(Helper.Image.delete));

        iconDesktop.setActionListener((e) -> {
            if (iconDesktop.getClientProperty(DesktopPaneIconMoveAdapter.DRAGGED_MARK) != null) {
                return;
            }
            FormEspecificacao form = new FormEspecificacao();
            desktopPanel.add(form);
            form.open();
        });

        iconDesktop.setLocation(new Point(0, 0));
        desktopPanel.add(iconDesktop);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPanel = new com.alee.laf.desktoppane.WebDesktopPane();
        imageBackgroud = new com.alee.extended.image.WebImage();
        panelInformacoes = new javax.swing.JPanel();
        lblNomeUsuario = new javax.swing.JLabel();
        labelBorda = new javax.swing.JLabel();
        lblNotificacoes = new javax.swing.JLabel();

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

        lblNotificacoes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout panelInformacoesLayout = new javax.swing.GroupLayout(panelInformacoes);
        panelInformacoes.setLayout(panelInformacoesLayout);
        panelInformacoesLayout.setHorizontalGroup(
            panelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelBorda, javax.swing.GroupLayout.DEFAULT_SIZE, 1034, Short.MAX_VALUE)
            .addGroup(panelInformacoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblNotificacoes)
                .addGap(15, 15, 15))
        );
        panelInformacoesLayout.setVerticalGroup(
            panelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacoesLayout.createSequentialGroup()
                .addComponent(labelBorda, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNomeUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(lblNotificacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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

    public static void start() {

        WebLookAndFeel.setDecorateAllWindows(true);
        WebLookAndFeel.setDecorateDialogs(true);
        WebLookAndFeel.setDecorateFrames(true);

        LanguageManager.setDefaultLanguage(LanguageManager.PORTUGUESE);

        WebLookAndFeel.install();

        FormPrincipal frame = new FormPrincipal();
        frame.setVisible(true);

        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        frame.setMaximizedBounds(env.getMaximumWindowBounds());
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
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
    private javax.swing.JPanel panelInformacoes;
    // End of variables declaration//GEN-END:variables
}
