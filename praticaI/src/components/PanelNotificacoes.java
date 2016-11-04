package components;

import com.alee.extended.layout.VerticalFlowLayout;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.utils.ThreadUtils;
import forms.FormPrincipal;
import utils.Utils;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import model.Notificacao;
import model.Notificacoes;

public class PanelNotificacoes extends WebPanel {

    private final int borda = 5;

    public boolean isOpen = false;
    private WebLabel loadding;
    private ImageIcon gif;

    private Notificacoes notificacoes;
    private WebPanel notificacoesPanel;

    public PanelNotificacoes() {
        initComponents();

        Border border = BorderFactory.createEmptyBorder(borda, borda, borda, borda);
        this.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), border));

        this.carregarNotificacoes();

        this.lblFechar.setIcon(Utils.getImage(Utils.Image.fechar));

        this.lblAtualizar.setIcon(Utils.getImage(Utils.Image.atualizar));
        this.scrollPanel.getVerticalScrollBar().setUnitIncrement(20);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        int shade = 0;
        int topOpacity = 80;
        for (int i = 0; i < borda; i++) {
            grphcs.setColor(new Color(shade, shade, shade, ((topOpacity / borda) * i)));
            grphcs.drawRect(i, i, this.getWidth() - ((i * 2) + 1), this.getHeight() - ((i * 2) + 1));
        }
    }

    private void loadPanelNotificacoes() {
        if (this.notificacoesPanel == null) {
            this.notificacoesPanel = new WebPanel(new VerticalFlowLayout());
            this.notificacoesPanel.setBackground(Utils.CoresPadrao.fundoPadrao);
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
            ThreadUtils.sleepSafely(2000);
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
            this.gif = Utils.getImage(Utils.Image.gifcarregando);
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

        jPanel1 = new javax.swing.JPanel();
        panelOpcoes = new javax.swing.JPanel();
        lblAtualizar = new javax.swing.JLabel();
        lblFechar = new javax.swing.JLabel();
        scrollPanel = new javax.swing.JScrollPane();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);

        lblAtualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAtualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAtualizarMouseClicked(evt);
            }
        });

        lblFechar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblFechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFecharMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelOpcoesLayout = new javax.swing.GroupLayout(panelOpcoes);
        panelOpcoes.setLayout(panelOpcoesLayout);
        panelOpcoesLayout.setHorizontalGroup(
            panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOpcoesLayout.createSequentialGroup()
                .addContainerGap(559, Short.MAX_VALUE)
                .addComponent(lblAtualizar)
                .addGap(20, 20, 20)
                .addComponent(lblFechar)
                .addGap(8, 8, 8))
        );
        panelOpcoesLayout.setVerticalGroup(
            panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
            .addComponent(lblFechar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        scrollPanel.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanel.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelOpcoes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(scrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(panelOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 497, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblFecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFecharMouseClicked
        FormPrincipal formPrincipal = FormPrincipal.getInstance();
        formPrincipal.toggleNotificacoes();
    }//GEN-LAST:event_lblFecharMouseClicked

    private void lblAtualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAtualizarMouseClicked
        this.carregarNotificacoes();
    }//GEN-LAST:event_lblAtualizarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAtualizar;
    private javax.swing.JLabel lblFechar;
    private javax.swing.JPanel panelOpcoes;
    private javax.swing.JScrollPane scrollPanel;
    // End of variables declaration//GEN-END:variables
}
