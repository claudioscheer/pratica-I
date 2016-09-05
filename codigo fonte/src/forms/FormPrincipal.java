package forms;

import com.alee.global.StyleConstants;
import com.alee.laf.WebLookAndFeel;
import com.alee.managers.language.LanguageManager;
import components.DesktopPaneIconMoveAdapter;
import components.IconDesktop;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class FormPrincipal extends javax.swing.JFrame {

    public FormPrincipal() {
        initComponents();
        this.loadComponents();
    }

    private void loadComponents() {
        this.loadEspecificacao();
        this.loadBackgroud();
    }

    private void loadBackgroud() {
        imageBackgroud.setIcon(new ImageIcon(getClass().getResource("/imagens/bg.jpg")));
    }

    private void loadEspecificacao() {
        final FormEspecificacao form = new FormEspecificacao();

        final IconDesktop iconDesktop = new IconDesktop("Especificação", new ImageIcon(getClass().getResource("/imagens/icon_delete.png")));

        iconDesktop.setActionListener((e) -> {
            if (iconDesktop.getClientProperty(DesktopPaneIconMoveAdapter.DRAGGED_MARK) != null) {
                return;
            }

            if (form.isClosed()) {
                if (form.getParent() == null) {
                    desktopPanel.add(form);
                }
                form.open();
                form.setIcon(false);
            } else {
                form.setIcon(!form.isIcon());
            }
        });

        iconDesktop.setLocation(new Point(25, 25));
        desktopPanel.add(iconDesktop);

        form.setBounds(25, 25, 565, 496);
        form.close();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPanel = new com.alee.laf.desktoppane.WebDesktopPane();
        imageBackgroud = new com.alee.extended.image.WebImage();
        panelInformacoes = new javax.swing.JPanel();
        btnInformacoes = new com.alee.laf.button.WebButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(778, 508));

        desktopPanel.setOpaque(false);

        panelInformacoes.setBackground(new java.awt.Color(255, 255, 255));

        btnInformacoes.setRound(100);
        btnInformacoes.setText("webButton1");

        javax.swing.GroupLayout panelInformacoesLayout = new javax.swing.GroupLayout(panelInformacoes);
        panelInformacoes.setLayout(panelInformacoesLayout);
        panelInformacoesLayout.setHorizontalGroup(
            panelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnInformacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(724, Short.MAX_VALUE))
        );
        panelInformacoesLayout.setVerticalGroup(
            panelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnInformacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelInformacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(desktopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(imageBackgroud, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelInformacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.button.WebButton btnInformacoes;
    private com.alee.laf.desktoppane.WebDesktopPane desktopPanel;
    private com.alee.extended.image.WebImage imageBackgroud;
    private javax.swing.JPanel panelInformacoes;
    // End of variables declaration//GEN-END:variables
}
