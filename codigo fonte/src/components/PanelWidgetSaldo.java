package components;

import com.alee.managers.language.data.TooltipWay;
import com.alee.managers.tooltip.TooltipManager;
import com.alee.utils.ThreadUtils;
import helper.Helper;
import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class PanelWidgetSaldo extends javax.swing.JPanel {

    private double saldo;

    private final int borda = 5;
    private Calendar calendar;

    private static PanelWidgetSaldo instance = null;

    public PanelWidgetSaldo() {
        initComponents();
        this.calendar = Calendar.getInstance();
        this.loadSaldo();
        this.setMesAno();

        //só pra teste
        this.saldo = 200;

        Border border = BorderFactory.createEmptyBorder(borda, borda, borda, borda);
        this.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), border));

        this.loadIcones();
        this.setTooltips();

        MoverComponente moveItem = new MoverComponente();
        this.addMouseListener(moveItem);
        this.addMouseMotionListener(moveItem);
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

    public static PanelWidgetSaldo getInstance() {
        if (instance == null) {
            instance = new PanelWidgetSaldo();
        }
        return instance;
    }

    private void setTooltips() {
        TooltipManager.setTooltip(this.lblAnterior, "Mês anterior", TooltipWay.down, 0);
        TooltipManager.setTooltip(this.lblProximo, "Próximo mês", TooltipWay.down, 0);
        TooltipManager.setTooltip(this.lblMesAno, "Mês atual", TooltipWay.down, 0);
        TooltipManager.setTooltip(this.lblAtualizar, "Atualizar", TooltipWay.left, 0);
    }

    private void loadSaldo() {
        this.lblValorSaldo.setText("...");
        this.lblValorSaldo.setForeground(Color.BLACK);
        Thread t = new Thread(() -> {
            ThreadUtils.sleepSafely(500);
            this.setSaldo();
        });
        t.start();
    }

    private void setSaldo() {
        Color c;
        if (this.saldo < 0) {
            c = Color.RED;
        } else if (this.saldo > 0) {
            c = Helper.CoresPadrao.verde;
        } else {
            c = Helper.CoresPadrao.fundoDesktop;
        }
        this.lblValorSaldo.setForeground(c);
        this.lblValorSaldo.setText("R$ " + new DecimalFormat("#0.00").format(this.saldo));
    }

    private String getMesAno() {
        String mesano = new SimpleDateFormat("MMMM").format(this.calendar.getTime()) + " / " + this.calendar.get(Calendar.YEAR);
        return mesano;
    }

    private void setMesAno() {
        this.lblMesAno.setText(this.getMesAno());
    }

    private void loadIcones() {
        this.lblAnterior.setIcon(Helper.getImage(Helper.Image.anterior));
        this.lblProximo.setIcon(Helper.getImage(Helper.Image.proximo));
        this.lblAtualizar.setIcon(Helper.getImage(Helper.Image.atualizar));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelItens = new javax.swing.JPanel();
        lblAnterior = new javax.swing.JLabel();
        lblProximo = new javax.swing.JLabel();
        lblMesAno = new javax.swing.JLabel();
        lblValorSaldo = new javax.swing.JLabel();
        lblAtualizar = new javax.swing.JLabel();

        setOpaque(false);

        lblAnterior.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAnterior.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnteriorMouseClicked(evt);
            }
        });

        lblProximo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblProximo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblProximoMouseClicked(evt);
            }
        });

        lblMesAno.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMesAno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMesAno.setText("Agosto / 2016");
        lblMesAno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMesAno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMesAnoMouseClicked(evt);
            }
        });

        lblValorSaldo.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        lblValorSaldo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblValorSaldo.setText("jLabel1");

        lblAtualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout panelItensLayout = new javax.swing.GroupLayout(panelItens);
        panelItens.setLayout(panelItensLayout);
        panelItensLayout.setHorizontalGroup(
            panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelItensLayout.createSequentialGroup()
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelItensLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblAtualizar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelItensLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblValorSaldo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelItensLayout.createSequentialGroup()
                                .addComponent(lblAnterior)
                                .addGap(18, 18, 18)
                                .addComponent(lblMesAno, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(lblProximo)))))
                .addGap(5, 5, 5))
        );
        panelItensLayout.setVerticalGroup(
            panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelItensLayout.createSequentialGroup()
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblProximo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMesAno, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(lblValorSaldo, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                .addGap(21, 21, 21)
                .addComponent(lblAtualizar)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelItens, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelItens, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblAnteriorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnteriorMouseClicked
        this.calendar.add(Calendar.MONTH, -1);
        
        //só pra teste
        this.saldo -= 100;
        
        this.setMesAno();
        this.loadSaldo();
    }//GEN-LAST:event_lblAnteriorMouseClicked

    private void lblProximoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblProximoMouseClicked
        this.calendar.add(Calendar.MONTH, 1);

        //só pra teste
        this.saldo += 100;

        this.setMesAno();
        this.loadSaldo();
    }//GEN-LAST:event_lblProximoMouseClicked

    private void lblMesAnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMesAnoMouseClicked
        this.calendar = Calendar.getInstance();
        this.setMesAno();
        this.loadSaldo();
    }//GEN-LAST:event_lblMesAnoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblAnterior;
    private javax.swing.JLabel lblAtualizar;
    private javax.swing.JLabel lblMesAno;
    private javax.swing.JLabel lblProximo;
    private javax.swing.JLabel lblValorSaldo;
    private javax.swing.JPanel panelItens;
    // End of variables declaration//GEN-END:variables
}
