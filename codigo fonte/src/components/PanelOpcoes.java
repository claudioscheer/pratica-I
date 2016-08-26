package components;

import java.awt.event.ActionListener;

public class PanelOpcoes extends javax.swing.JPanel {

    public PanelOpcoes() {
        initComponents();
    }

    public void setEventAdd(ActionListener actionBtnAdd) {
        buttonAdd.addActionListener(actionBtnAdd);
    }

    public void setEventEditar(ActionListener actionBtnEditar) {
        buttonEditar.addActionListener(actionBtnEditar);
    }

    public void setEventExcluir(ActionListener actionBtnExcluir) {
        buttonExcluir.addActionListener(actionBtnExcluir);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonAdd = new com.alee.laf.button.WebButton();
        buttonExcluir = new com.alee.laf.button.WebButton();
        buttonEditar = new com.alee.laf.button.WebButton();

        setBackground(new java.awt.Color(255, 255, 255));

        buttonAdd.setText("Novo");
        buttonAdd.setMaximumSize(new java.awt.Dimension(24, 24));
        buttonAdd.setPreferredSize(new java.awt.Dimension(24, 24));

        buttonExcluir.setText("Excluir");

        buttonEditar.setText("Editar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(289, Short.MAX_VALUE)
                .addComponent(buttonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.button.WebButton buttonAdd;
    private com.alee.laf.button.WebButton buttonEditar;
    private com.alee.laf.button.WebButton buttonExcluir;
    // End of variables declaration//GEN-END:variables
}
