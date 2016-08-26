package forms;

import com.alee.laf.WebLookAndFeel;
import components.DesktopPaneIconMoveAdapter;
import components.IconDesktop;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class FrameMain extends javax.swing.JFrame {

    public FrameMain() {
        initComponents();
        this.loadComponents();
    }

    private void loadComponents() {
        this.loadEdspecificacao();
        this.loadBackgroud();
    }

    private void loadBackgroud() {
        imageBackgroud.setIcon(new ImageIcon(getClass().getResource("/imagens/bg.jpg")));
    }

    private void loadEdspecificacao() {
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(778, 508));

        desktopPanel.setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(imageBackgroud, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addComponent(imageBackgroud, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                    .addGap(2, 2, 2)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                WebLookAndFeel.install();

                FrameMain frame = new FrameMain();
                frame.setVisible(true);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.desktoppane.WebDesktopPane desktopPanel;
    private com.alee.extended.image.WebImage imageBackgroud;
    // End of variables declaration//GEN-END:variables
}
