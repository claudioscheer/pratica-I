/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import components.Validador;
import dao.EstCategoriaDAO;
import dao.EstMarcaDAO;
import dao.EstMovimentacaoDAO;
import dao.EstProdutoDAO;
import dao.EstTipoOperacaoDAO;
import forms.busca.FormBuscaMarca;
import forms.busca.FormBuscaProduto;
import forms.busca.FormBuscaTipoMovimentacao;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import model.CarEstTipoOperacao;
import model.EstMovimentacao;
import model.EstProduto;

/**
 *
 * @author Anderson
 */
public class FormManutencaoEstoque extends javax.swing.JFrame
{

    private EstProdutoDAO produtoDao = new EstProdutoDAO();
    private EstMovimentacaoDAO movDAO = new EstMovimentacaoDAO();

    private boolean isEditar = false;
    private EstMovimentacao movEditar = new EstMovimentacao();
    public Validador validador;
    FormBuscaMarca formMarca;

    public FormManutencaoEstoque(java.awt.Frame parent, boolean modal)
    {

        initComponents();

        //inicia o validador
      /*  this.validador = new Validador(Validador.TipoValidator.ICONE);
         this.validador.addObrigatorioValidator(this.txtReferencia);
         this.validador.addObrigatorioValidator(this.txtDescricao);
         this.validador.addObrigatorioValidator(this.txtCategoria);
         this.validador.addObrigatorioValidator(this.txtMarca);
         this.validador.addObrigatorioValidator(this.txtUnidadeMedida);

         //abre o form para buscar uma categoria
         FormBuscaCategoria formCategoria = new FormBuscaCategoria();
         formCategoria.setFrameBloquear(FormPrincipal.getInstance());
         this.txtCategoria.setFrame(formCategoria);

         //abre o form para buscar uma marca
         formMarca = new FormBuscaMarca();
         formMarca.setFrameBloquear(FormPrincipal.getInstance());
         this.txtMarca.setFrame(formMarca);

         FormBuscaUnidadeMedida formUn = new FormBuscaUnidadeMedida();
         formUn.setFrameBloquear(FormPrincipal.getInstance());
         this.txtUnidadeMedida.setFrame(formUn);
         */
    }

    public FormManutencaoEstoque(java.awt.Frame parent, boolean modal, EstProduto produto)
    {

        initComponents();

        isEditar = true;

        txtTipoMov.setEditable(false);

        //inicia o validador
        /*this.validador = new Validador(Validador.TipoValidator.ICONE);
         this.validador.addObrigatorioValidator(this.txtReferencia);
         this.validador.addObrigatorioValidator(this.txtDescricao);
         this.validador.addObrigatorioValidator(this.txtCategoria);
         this.validador.addObrigatorioValidator(this.txtMarca);
         this.validador.addObrigatorioValidator(this.txtUnidadeMedida);

         //abre o form para buscar uma categoria
         FormBuscaCategoria formCategoria = new FormBuscaCategoria();
         formCategoria.setFrameBloquear(FormPrincipal.getInstance());
         this.txtCategoria.setFrame(formCategoria);

         //abre o form para buscar uma marca
         formMarca = new FormBuscaMarca();
         formMarca.setFrameBloquear(FormPrincipal.getInstance());
         this.txtMarca.setFrame(formMarca);

         FormBuscaUnidadeMedida formUn = new FormBuscaUnidadeMedida();
         formUn.setFrameBloquear(FormPrincipal.getInstance());
         this.txtUnidadeMedida.setFrame(formUn);              

         this.comboAtivo.setSelectedIndex(produto.getProdutoStatus());
        
         this.produtoEditar = produto;
        
         EstProdutoDAO produtoDao = new EstProdutoDAO();
         EstCategoriaDAO categoriaDao = new EstCategoriaDAO();
         EstMarcaDAO marcaDao = new EstMarcaDAO();
         EstUnidadeMedidaDAO unMedDao = new EstUnidadeMedidaDAO();
                
         EstProduto prod = produtoDao.get(produto.getProdutoId());
         txtReferencia.setText(prod.getProdutoReferencia());
         txtDescricao.setText(prod.getProdutoDescricao());
         EstCategoria categoria = categoriaDao.get(prod.getEstCategoria().getCategoriaId());
         txtCategoria.setText(categoria.getCategoriaId() + " - " + categoria.getCategoriaDescricao());
         txtCategoria.setValue(categoria);
         EstMarca marca = marcaDao.Buscar(prod.getEstMarca().getMarcaId());
         txtMarca.setText(marca.getMarcaId() + " - " + marca.getMarcaDescricao());
         txtMarca.setValue(marca);
         EstUnidadeMedida unMedida = unMedDao.Buscar(prod.getEstUnidadeMedida().getUnidadeMedidaId());
         txtUnidadeMedida.setText(unMedida.getUnidadeMedidaId() + " - " + unMedida.getUnidadeMedidaDescricao());
         txtUnidadeMedida.setValue(unMedida);
         */
    }

    public void setEvents(ActionListener salvar, ActionListener cancelar)
    {
        this.btnSalvar.addActionListener(salvar);
        this.btnCancelar.addActionListener(cancelar);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtProduto = new components.TextFieldFK();
        jLabel6 = new javax.swing.JLabel();
        txt_data_lançamento1 = new com.alee.extended.date.WebDateField();
        webLabel13 = new com.alee.laf.label.WebLabel();
        txtTipoMov = new components.TextFieldFK();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtValor = new components.TextFieldValorMonetario();
        jLabel2 = new javax.swing.JLabel();
        txtUnitario1 = new components.TextFieldValorMonetario();
        txtQteste = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowOpened(java.awt.event.WindowEvent evt)
            {
                iniciaTela(evt);
            }
        });

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel6.setText("Produto");

        txt_data_lançamento1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                txt_data_lançamento1ActionPerformed(evt);
            }
        });

        webLabel13.setText("Data lançamento:");

        txtTipoMov.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                txtTipoMovActionPerformed(evt);
            }
        });

        jLabel7.setText("Tipo Movimentação");

        jLabel8.setText("Quantidade");

        jLabel9.setText("Unitário");

        jLabel2.setText("Valor total");

        txtUnitario1.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                txtUnitario1FocusLost(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_data_lançamento1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(webLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtQteste, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(txtUnitario1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtTipoMov, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnCancelar)
                                    .addGap(52, 52, 52)
                                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(txtProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel6)
                .addGap(13, 13, 13)
                .addComponent(txtProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(webLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_data_lançamento1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTipoMov, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtUnitario1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQteste, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar))
                .addGap(25, 25, 25))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
        txtProduto.setEditable(false);
        FormBuscaProduto buscaProd = new FormBuscaProduto();
        buscaProd.setFrameBloquear(FormPrincipal.getInstance());
        this.txtProduto.setFrame(buscaProd);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed

        EstMovimentacao m = new EstMovimentacao();
        double qtde = Double.parseDouble(txtQteste.getText().replace(",", "."));
        double unit = Double.parseDouble(txtUnitario1.getText().replace(",", "."));
        double tot = Double.parseDouble(txtValor.getText().replace(",", "."));
        m.setEstProduto((EstProduto) this.txtProduto.getValue());
        m.setMovData(this.txt_data_lançamento1.getDate());
        m.setCarEstTipoOperacao((CarEstTipoOperacao) txtTipoMov.getValue());
        m.setMovQuantidade(qtde);
        m.setMovVlrUnit(unit);
        m.setMovTotal(tot);

        if (!isEditar)
        {
            movDAO.insert(m);
            JOptionPane.showMessageDialog(null, "Cadastro Salvo com Sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        } else
        {
            m.setMovId(this.movEditar.getMovId());
            movDAO.update(m);
            JOptionPane.showMessageDialog(null, "Cadastro Alterado com Sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        }

        this.dispose();


        /*EstMovimentacaoDAO mov = new EstMovimentacaoDAO();
         EstMovimentacao m = new EstMovimentacao();
         EstProdutoDAO  pDAO = new EstProdutoDAO();
         EstProduto p = new EstProduto();
        
         EstProduto prod = produtoDao.get(produto.getProdutoId());
         txtProduto.setText(""+m.getEstProduto().getProdutoId());
         txt_data_lançamento1.setText("" + m.getMovData());
         txtTipoMov.setText(""+m.getCarEstTipoOperacao().getTpOpId());
         txtQtde.setText("" + m.getMovQuantidade());
         txtUnitario.setText("" + m.getMovVlrUnit());
         txtTotal.setText("" + m.getMovTotal());
        
         /*       
         EstProduto produto = new EstProduto();
         produto.setProdutoReferencia(this.txtReferencia.getText());
         produto.setProdutoDescricao(this.txtDescricao.getText());
         produto.setEstCategoria((EstCategoria) this.txtCategoria.getValue());
         produto.setEstMarca((EstMarca) this.txtMarca.getValue());
         produto.setEstUnidadeMedida((EstUnidadeMedida) this.txtUnidadeMedida.getValue());

         if (!isEditar) {
         produtoDao.insert(produto);
         JOptionPane.showMessageDialog(null, "Cadastro Salvo com Sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
         } else {            
         produto.setProdutoId(this.produtoEditar.getProdutoId());
         produtoDao.update(produto);
         JOptionPane.showMessageDialog(null, "Cadastro Alterado com Sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
         }

         */
        this.dispose();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void iniciaTela(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_iniciaTela

        FormBuscaTipoMovimentacao b = new FormBuscaTipoMovimentacao();
        b.setFrameBloquear(FormPrincipal.getInstance());
        this.txtTipoMov.setFrame(b);

        txtProduto.setEditable(false);
        FormBuscaProduto buscaProd = new FormBuscaProduto();
        buscaProd.setFrameBloquear(FormPrincipal.getInstance());
        this.txtProduto.setFrame(buscaProd);

        txtValor.setEditable(false);
    }//GEN-LAST:event_iniciaTela

    private void txt_data_lançamento1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_txt_data_lançamento1ActionPerformed
    {//GEN-HEADEREND:event_txt_data_lançamento1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_data_lançamento1ActionPerformed

    private void txtTipoMovActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_txtTipoMovActionPerformed
    {//GEN-HEADEREND:event_txtTipoMovActionPerformed
        FormListaTipoOperacao form = new FormListaTipoOperacao(null, rootPaneCheckingEnabled);
        form.setVisible(true);
    }//GEN-LAST:event_txtTipoMovActionPerformed

    private void txtUnitario1FocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_txtUnitario1FocusLost
    {//GEN-HEADEREND:event_txtUnitario1FocusLost
        if (!txtQteste.getText().trim().isEmpty())
        {
            double qtde = Double.parseDouble(txtQteste.getText());
            double unit = Double.parseDouble(txtUnitario1.getText().replace(",", "."));

            double total = calculaTotal(qtde, unit);
            txtValor.setText(movDAO.format(total));
        }

    }//GEN-LAST:event_txtUnitario1FocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(FormManutencaoEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(FormManutencaoEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(FormManutencaoEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(FormManutencaoEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                FormManutencaoEstoque dialog = new FormManutencaoEstoque(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter()
                {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e)
                    {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    public double calculaTotal(double Qtde, double Unitario)
    {
        return Qtde != 0 ? Qtde * Unitario : 0;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private components.TextFieldFK txtProduto;
    private javax.swing.JTextField txtQteste;
    private components.TextFieldFK txtTipoMov;
    private components.TextFieldValorMonetario txtUnitario1;
    private components.TextFieldValorMonetario txtValor;
    private com.alee.extended.date.WebDateField txt_data_lançamento1;
    private com.alee.laf.label.WebLabel webLabel13;
    // End of variables declaration//GEN-END:variables
}
