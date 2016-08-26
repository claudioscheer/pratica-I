package components.panelsListagem;

import com.alee.laf.button.WebButton;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.table.WebTable;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class PanelEspecificacao extends WebPanel implements ActionListener {

    public PanelEspecificacao() {
        initComponents();
        this.loadDatas();
        this.loadTextBuscar();
    }

    public void setEvents(ActionListener add, ActionListener edit, ActionListener delete) {
        panelOpcoes.setEventAdd(add);
        panelOpcoes.setEventEditar(edit);
        panelOpcoes.setEventExcluir(delete);
    }

    private void loadDatas() {
        String[] headers = {"Header 1", "Header 2", "Header 3", "Header 4", "Header 5", "Header 6"};

        String[][] data = {
            {"1", "2", "3", "4", "5", "6"},
            {"7", "8", "9", "10", "11", "12"},
            {"13", "14", "15", "16", "17", "18"},
            {"19", "20", "21", "22", "23", "24"},
            {"25", "26", "27", "28", "29", "30"},
            {"31", "32", "33", "34", "35", "36"},
            {"37", "38", "39", "40", "41", "42"}
        };

        tableLista.setModel(new DefaultTableModel(data, headers));
        tableLista.setAutoResizeMode(WebTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    private void loadTextBuscar() {

        WebButton button = new WebButton("Buscar");
        button.addActionListener(this);
        button.setCursor(Cursor.getDefaultCursor());
        button.setLeftRightSpacing(10);

        textBusca.setTrailingComponent(button);
        textBusca.setFieldMargin(0, 6, 0, 6);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("asdjkfaksldjfnkajsdfnk");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableLista = new com.alee.laf.table.WebTable();
        panelOpcoes = new components.PanelOpcoes();
        textBusca = new com.alee.laf.text.WebTextField();

        setMinimumSize(new java.awt.Dimension(565, 496));

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tableLista.setAutoCreateRowSorter(true);
        tableLista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableLista.setEditable(false);
        tableLista.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(tableLista);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(textBusca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
            .addComponent(panelOpcoes, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(textBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private components.PanelOpcoes panelOpcoes;
    private com.alee.laf.table.WebTable tableLista;
    private com.alee.laf.text.WebTextField textBusca;
    // End of variables declaration//GEN-END:variables
}
