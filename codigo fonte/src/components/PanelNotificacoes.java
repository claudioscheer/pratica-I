package components;

import com.alee.extended.layout.VerticalFlowLayout;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import helper.Helper;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import model.Notificacao;
import model.Notificacoes;
import org.jdesktop.swingx.border.DropShadowBorder;

public class PanelNotificacoes extends WebPanel {

    public boolean isOpen = false;
    private WebLabel loadding;
    private ImageIcon gif;

    private Notificacoes notificacoes;
    private WebPanel notificacoesPanel;

    public PanelNotificacoes() {
        initComponents();
        this.lblAtualizar.setIcon(Helper.getImage(Helper.Image.atualizar));
        this.lblAtualizar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                carregarNotificacoes();
            }
        });
        this.scrollPanel.getVerticalScrollBar().setUnitIncrement(20);
        this.carregarNotificacoes();

        DropShadowBorder shadow = new DropShadowBorder();
        shadow.setShadowColor(Color.BLACK);
        shadow.setShowLeftShadow(true);
        shadow.setShowTopShadow(true);
        shadow.setShowBottomShadow(true);
        shadow.setShowRightShadow(true);
        shadow.setShadowSize(4);

        this.panelOpcoes.setBorder(shadow);
    }

    private void loadPanelNotificacoes() {
        if (this.notificacoesPanel == null) {
            this.notificacoesPanel = new WebPanel(new VerticalFlowLayout());
            this.scrollPanel.setViewportView(this.notificacoesPanel);
        }
    }

    private void loadNotificacoes() {
        if (this.notificacoes == null) {
            this.notificacoes = Notificacoes.getInstance();
        }

        for (int i = 0; i < 10; i++) {
            Notificacao n = new Notificacao();
            n.setIndex(i);
            n.setCodigo(i + 1);
            n.setDescricao("Notficação " + n.getCodigo());
            n.setVisualizada(false);
            n.setLink(Notificacao.Link.valueOf("especificacao"));
            this.notificacoes.add(n);
        }
    }

    public void carregarNotificacoes() {
        this.loadPanelNotificacoes();

        this.notificacoesPanel.removeAll();
        this.notificacoesPanel.revalidate();

        this.mostrarCarregando();

        Thread t = new Thread(() -> {
            this.loadNotificacoes();
            for (Notificacao n : notificacoes.getNotificacoes()) {
                if (!n.isVisualizada()) {
                    ItemNotificacao item = new ItemNotificacao();
                    item.setDados(n.getIndex());
                    this.notificacoesPanel.add(item);
                }
            }

            this.esconderCarregando();
        });
        t.start();
    }

    private void mostrarCarregando() {

        if (this.loadding == null) {
            this.gif = Helper.getImage(Helper.Image.gifCarregando);
            this.loadding = new WebLabel(this.gif);
        }

        int w = this.gif.getIconWidth();
        int h = this.gif.getIconHeight();

        int x = (this.notificacoesPanel.getWidth() / 2) - (w / 2);
        int y = (this.notificacoesPanel.getHeight() / 2) - (h / 2);

        this.loadding.setBounds(x, y, w, h);
        this.notificacoesPanel.add(this.loadding);
    }

    private void esconderCarregando() {
        this.notificacoesPanel.remove(loadding);
        this.notificacoesPanel.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPanel = new javax.swing.JScrollPane();
        panelOpcoes = new javax.swing.JPanel();
        lblAtualizar = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        scrollPanel.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanel.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        lblAtualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout panelOpcoesLayout = new javax.swing.GroupLayout(panelOpcoes);
        panelOpcoes.setLayout(panelOpcoesLayout);
        panelOpcoesLayout.setHorizontalGroup(
            panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOpcoesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblAtualizar)
                .addGap(5, 5, 5))
        );
        panelOpcoesLayout.setVerticalGroup(
            panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAtualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
            .addComponent(panelOpcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblAtualizar;
    private javax.swing.JPanel panelOpcoes;
    private javax.swing.JScrollPane scrollPanel;
    // End of variables declaration//GEN-END:variables
}
