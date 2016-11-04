package forms.patrimonio;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import components.JFrameBusca;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.PatAtivoImobilizado;
import utils.Utils;

public class FormQrCodeAtivoImobilizado extends JFrameBusca {

    private BufferedImage image;
    private PatAtivoImobilizado ativoImobilizado;

    private int ultimoSize;

    public FormQrCodeAtivoImobilizado() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    @Override
    public void resize(Dimension dmnsn) {
        int W = 2;
        int H = 2;
        Rectangle b = this.getBounds();
        this.setBounds(b.x, b.y, b.width, b.width * H / W);
    }

    public void setAtivoImobilizado(PatAtivoImobilizado ativoImobilizado) {
        this.ativoImobilizado = ativoImobilizado;
        this.gerarQrCode();
    }

    private void gerarQrCode() {
        generateQrCode(String.valueOf(ativoImobilizado.getAtivoCodigo()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtTamanhoImagem = new com.alee.laf.spinner.WebSpinner();
        buttonSalvar = new javax.swing.JButton();
        btnRedimensionarImagem = new javax.swing.JButton();
        scrollQrCode = new javax.swing.JScrollPane();
        panelImage = new QRCodePanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(null);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        txtTamanhoImagem.setModel(new javax.swing.SpinnerNumberModel(328, 1, null, 30));

        buttonSalvar.setText("Salvar");
        buttonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSalvarActionPerformed(evt);
            }
        });

        btnRedimensionarImagem.setText("Redimensionar");
        btnRedimensionarImagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedimensionarImagemActionPerformed(evt);
            }
        });

        panelImage.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelImage.setMaximumSize(null);
        panelImage.setMinimumSize(null);
        panelImage.setPreferredSize(new java.awt.Dimension(328, 328));

        javax.swing.GroupLayout panelImageLayout = new javax.swing.GroupLayout(panelImage);
        panelImage.setLayout(panelImageLayout);
        panelImageLayout.setHorizontalGroup(
            panelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 326, Short.MAX_VALUE)
        );
        panelImageLayout.setVerticalGroup(
            panelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 326, Short.MAX_VALUE)
        );

        scrollQrCode.setViewportView(panelImage);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollQrCode)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtTamanhoImagem, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRedimensionarImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonSalvar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTamanhoImagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRedimensionarImagem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollQrCode, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonSalvar)
                .addGap(11, 11, 11))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalvarActionPerformed
        JFileChooser salvar = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("Image", "png");
        salvar.setFileFilter(filter);

        if (salvar.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        String fileName = salvar.getSelectedFile().getPath().replaceFirst("(.*?)(\\.\\w{3,4})*$", "$1.png");
        try {
            ImageIO.write(this.image, "png", new File(fileName));
        } catch (IOException ex) {
            System.out.println("Erro ao salvar");
        }
    }//GEN-LAST:event_buttonSalvarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.getFrameBloquear().setEnabled(true);
    }//GEN-LAST:event_formWindowClosing

    private void btnRedimensionarImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedimensionarImagemActionPerformed
        this.ultimoSize = this.panelImage.getWidth();
        int size = Integer.parseInt(String.valueOf(this.txtTamanhoImagem.getValue()));
        this.panelImage.setSize(size, size);
        this.panelImage.setPreferredSize(new Dimension(size, size));
        this.gerarQrCode();
    }//GEN-LAST:event_btnRedimensionarImagemActionPerformed

    private class QRCodePanel extends javax.swing.JPanel {

        @Override
        protected void paintComponent(Graphics grphcs) {
            super.paintComponent(grphcs);
            if (image != null) {
                grphcs.drawImage(image, 0, 0, null);
            }
        }
    }

    private void generateQrCode(String messsage) {
        if (messsage == null || messsage.isEmpty()) {
            image = null;
            return;
        }

        try {
            Hashtable hintMap = new Hashtable();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(messsage, BarcodeFormat.QR_CODE,
                    this.panelImage.getWidth(), this.panelImage.getHeight(), hintMap);
            int CrunchifyWidth = byteMatrix.getWidth();
            image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
                    BufferedImage.TYPE_INT_RGB);
            image.createGraphics();
            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
            graphics.setColor(Color.BLACK);
            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            panelImage.repaint();
        } catch (WriterException ex) {
            utils.Utils.notificacao("Erro ao gerar QrCode!", Utils.TipoNotificacao.erro, 0);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRedimensionarImagem;
    private javax.swing.JButton buttonSalvar;
    private javax.swing.JPanel panelImage;
    private javax.swing.JScrollPane scrollQrCode;
    private com.alee.laf.spinner.WebSpinner txtTamanhoImagem;
    // End of variables declaration//GEN-END:variables
}
