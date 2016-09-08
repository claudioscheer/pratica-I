package components;

import forms.FormEspecificacao;
import helper.Helper;
import java.awt.Color;
import java.awt.Container;
import model.Notificacao;
import model.Notificacoes;
import org.jdesktop.swingx.border.DropShadowBorder;

public class ItemNotificacao extends javax.swing.JPanel {

    private int indexNotificacao;

    public ItemNotificacao() {
        initComponents();
        this.setBackground(Helper.CoresPadrao.fundoPadrao);

        DropShadowBorder shadow = new DropShadowBorder();
        shadow.setShadowColor(Color.BLACK);
        shadow.setShowLeftShadow(true);
        shadow.setShowTopShadow(true);
        shadow.setShowBottomShadow(true);
        shadow.setShowRightShadow(true);
        shadow.setShadowSize(4);

        this.panelDados.setBorder(shadow);

    }

    public void setDados(int indexNotificacao) {
        this.indexNotificacao = indexNotificacao;

        Notificacoes notificacoes = Notificacoes.getInstance();

        this.lblTextoNotificacao.setText(notificacoes.get(indexNotificacao).getDescricao());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDados = new javax.swing.JPanel();
        lblTextoNotificacao = new javax.swing.JLabel();
        btnVerNotificacao = new com.alee.extended.label.WebLinkLabel();
        btnFecharNotificacão = new com.alee.extended.label.WebLinkLabel();

        setBackground(new java.awt.Color(204, 204, 204));

        panelDados.setBackground(new java.awt.Color(255, 255, 255));

        lblTextoNotificacao.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTextoNotificacao.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblTextoNotificacao.setMaximumSize(new java.awt.Dimension(3000, 15));
        lblTextoNotificacao.setMinimumSize(new java.awt.Dimension(0, 15));
        lblTextoNotificacao.setPreferredSize(new java.awt.Dimension(0, 15));

        btnVerNotificacao.setText("Mais informações");
        btnVerNotificacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVerNotificacaoMouseClicked(evt);
            }
        });

        btnFecharNotificacão.setText("Fechar");
        btnFecharNotificacão.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFecharNotificacãoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelDadosLayout = new javax.swing.GroupLayout(panelDados);
        panelDados.setLayout(panelDadosLayout);
        panelDadosLayout.setHorizontalGroup(
            panelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDadosLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDadosLayout.createSequentialGroup()
                        .addComponent(btnVerNotificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addComponent(btnFecharNotificacão, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTextoNotificacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        panelDadosLayout.setVerticalGroup(
            panelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDadosLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lblTextoNotificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFecharNotificacão, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerNotificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(panelDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(panelDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnFecharNotificacãoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFecharNotificacãoMouseClicked
        Container c = this.getParent();
        if (c != null) {
            c.remove(this);
            c.repaint();

            Notificacoes.getInstance().setVisualizada(this.indexNotificacao);
        }
    }//GEN-LAST:event_btnFecharNotificacãoMouseClicked

    private void btnVerNotificacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVerNotificacaoMouseClicked
        Notificacoes notificacoes = Notificacoes.getInstance();
        Notificacao n = notificacoes.get(indexNotificacao);

        switch (n.getLink()) {
            case especificacao:
                FormEspecificacao form = new FormEspecificacao();
                Container c = this.getParent().getParent().getParent().getParent().getParent();
                c.add(form);
                form.open();
                break;
        }
    }//GEN-LAST:event_btnVerNotificacaoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.extended.label.WebLinkLabel btnFecharNotificacão;
    private com.alee.extended.label.WebLinkLabel btnVerNotificacao;
    private javax.swing.JLabel lblTextoNotificacao;
    private javax.swing.JPanel panelDados;
    // End of variables declaration//GEN-END:variables
}
