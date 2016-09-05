package forms;

import com.alee.global.StyleConstants;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.button.WebButton;
import com.alee.laf.rootpane.WebRootPaneStyle;
import com.alee.managers.language.LanguageManager;
import components.DesktopPaneIconMoveAdapter;
import components.IconDesktop;
import java.awt.Color;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

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
        btnInformacoes.setIcon(new ImageIcon(getClass().getResource("/imagens/windows.png")));

        btnInformacoes.setRolloverDecoratedOnly(true);
        btnInformacoes.setHorizontalTextPosition(WebButton.CENTER);
        btnInformacoes.setVerticalTextPosition(WebButton.BOTTOM);
    }

    private void loadEspecificacao() {

        final IconDesktop iconDesktop = new IconDesktop("Especificação", new ImageIcon(getClass().getResource("/imagens/icon_delete.png")));

        iconDesktop.setActionListener((e) -> {
            if (iconDesktop.getClientProperty(DesktopPaneIconMoveAdapter.DRAGGED_MARK) != null) {
                return;
            }
            FormEspecificacao form = new FormEspecificacao();
            desktopPanel.add(form);
            form.open();
        });

        iconDesktop.setLocation(new Point(25, 25));
        desktopPanel.add(iconDesktop);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPanel = new com.alee.laf.desktoppane.WebDesktopPane();
        imageBackgroud = new com.alee.extended.image.WebImage();
        panelInformacoes = new javax.swing.JPanel();
        lblNomeUsuario = new javax.swing.JLabel();
        btnInformacoes = new com.alee.laf.button.WebButton();
        labelBorda = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(778, 508));

        desktopPanel.setOpaque(false);

        panelInformacoes.setBackground(new java.awt.Color(255, 255, 255));
        panelInformacoes.setOpaque(false);

        lblNomeUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNomeUsuario.setText("Claudio Scheer (ADMIN)");

        labelBorda.setBackground(new java.awt.Color(0, 0, 0));
        labelBorda.setOpaque(true);

        javax.swing.GroupLayout panelInformacoesLayout = new javax.swing.GroupLayout(panelInformacoes);
        panelInformacoes.setLayout(panelInformacoesLayout);
        panelInformacoesLayout.setHorizontalGroup(
            panelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacoesLayout.createSequentialGroup()
                .addComponent(btnInformacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblNomeUsuario)
                .addContainerGap())
            .addComponent(labelBorda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelInformacoesLayout.setVerticalGroup(
            panelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacoesLayout.createSequentialGroup()
                .addComponent(labelBorda, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(panelInformacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnInformacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNomeUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)))
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
                .addComponent(panelInformacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(imageBackgroud, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void start() {

        JFrame.setDefaultLookAndFeelDecorated(false);
        JDialog.setDefaultLookAndFeelDecorated(false);

        WebLookAndFeel.setDecorateAllWindows(true);
        WebLookAndFeel.setDecorateDialogs(true);
        WebLookAndFeel.setDecorateFrames(true);

        LanguageManager.setDefaultLanguage(LanguageManager.PORTUGUESE);

        WebLookAndFeel.install();

        FormPrincipal frame = new FormPrincipal();
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    public static void main(String[] args) {
        start();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.button.WebButton btnInformacoes;
    private com.alee.laf.desktoppane.WebDesktopPane desktopPanel;
    private com.alee.extended.image.WebImage imageBackgroud;
    private javax.swing.JLabel labelBorda;
    private javax.swing.JLabel lblNomeUsuario;
    private javax.swing.JPanel panelInformacoes;
    // End of variables declaration//GEN-END:variables
}
