package forms.busca;

import components.JFrameBusca;
import components.TextFieldFK;
import dao.EstProdutoDAO;
import dao.EstTipoOperacaoDAO;
import java.util.List;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import model.CarEstTipoOperacao;
import model.EstProduto;
import utils.Utils;

public class FormBuscaTipoMovimentacao extends JFrameBusca
{

    private LoadTipoMov loadTipoMov;
    private List<CarEstTipoOperacao> op;

    public FormBuscaTipoMovimentacao()
    {
        initComponents();
        this.buscarTpMov();
        this.setLocationRelativeTo(null);
    }

    public void buscarTpMov()
    {
        this.loadTipoMov = new LoadTipoMov();
        this.loadTipoMov.execute();
    }

    public class LoadTipoMov extends SwingWorker<Void, Void>
    {

        protected Void doInBackground() throws Exception
        {

            DefaultTableModel model = (DefaultTableModel) tabelaTpMov.getModel();

            EstTipoOperacaoDAO tpMovDAO = new EstTipoOperacaoDAO();

            op = tpMovDAO.ListarTodas();
            for (CarEstTipoOperacao opp : op)
            {
                Object[] o = new Object[2];
                o[0] = opp.getTpOpId();
                o[1] = opp.getTpOpDescricao();
                model.addRow(o);
            }
            tabelaTpMov.setModel(model);
            return null;
        }

        public void done()
        {

        }

        private Object estMovDao()
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaTpMov = new javax.swing.JTable();
        txtBuscar = new components.TextFieldBuscar();
        panelOpcoes = new javax.swing.JPanel();
        buttonSelecionar = new com.alee.laf.button.WebButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                formWindowClosing(evt);
            }
        });

        tabelaTpMov.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Código", "Descrição"
            }
        )
        {
            boolean[] canEdit = new boolean []
            {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabelaTpMov);

        panelOpcoes.setBackground(new java.awt.Color(255, 255, 255));

        buttonSelecionar.setText("Selecionar");
        buttonSelecionar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                buttonSelecionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOpcoesLayout = new javax.swing.GroupLayout(panelOpcoes);
        panelOpcoes.setLayout(panelOpcoesLayout);
        panelOpcoesLayout.setHorizontalGroup(
            panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcoesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonSelecionar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelOpcoesLayout.setVerticalGroup(
            panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOpcoesLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(buttonSelecionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelOpcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.getFrameBloquear().setEnabled(true);
    }//GEN-LAST:event_formWindowClosing

    private void buttonSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSelecionarActionPerformed
        int linhaselecionada = this.tabelaTpMov.getSelectedRow();
        if (linhaselecionada < 0)
        {
            Utils.notificacao("Selecione um Tipo de Movimentação!", Utils.TipoNotificacao.erro, 0);
            return;
        }

        TextFieldFK text = this.getTextFieldFK();
        CarEstTipoOperacao produto = this.op.get(linhaselecionada);
        if (this.getFrameBuscaTipo() == JFrameBuscaTipo.textFieldFK)
        {
            text.setText(produto.getTpOpId() + "-" + produto.getTpOpDescricao());
            text.setValue(produto);
        }

        if (text.getFuncaoDepoisSelecionar() != null)
        {
            text.getFuncaoDepoisSelecionar().accept(produto);
        }

        this.getFrameBloquear().setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_buttonSelecionarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.button.WebButton buttonSelecionar;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelOpcoes;
    private javax.swing.JTable tabelaTpMov;
    private components.TextFieldBuscar txtBuscar;
    // End of variables declaration//GEN-END:variables
}
