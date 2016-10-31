package components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;

public class PanelItemBuscar extends javax.swing.JPanel {

    public PanelItemBuscar() {
        initComponents();
        this.setBackground(Color.white);
        this.btnBuscar.setCursor(Cursor.getDefaultCursor());
        this.comboOpcoesBuscar.setCursor(Cursor.getDefaultCursor());
    }

    public void setEventBuscar(ActionListener al) {
        this.btnBuscar.addActionListener(al);
    }

    public void setTextButton(String text) {
        this.btnBuscar.setText(text);
    }

    public void showComboOpcoes(boolean show) {
        this.comboOpcoesBuscar.setVisible(show);
    }

    public void addOpcoesBuscar(String[] opcoes) {
        this.comboOpcoesBuscar.setModel(new DefaultComboBoxModel(opcoes));
    }

    public int getFiltroSelecionado() {
        return this.comboOpcoesBuscar.getSelectedIndex();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBuscar = new com.alee.laf.button.WebButton();
        comboOpcoesBuscar = new com.alee.laf.combobox.WebComboBox();

        btnBuscar.setText("webButton1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(comboOpcoesBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(comboOpcoesBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public com.alee.laf.button.WebButton btnBuscar;
    public com.alee.laf.combobox.WebComboBox comboOpcoesBuscar;
    // End of variables declaration//GEN-END:variables
}
