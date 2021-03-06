/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import components.Validador;
import dao.EstCategoriaDAO;
import dao.EstMarcaDAO;
import dao.EstProdutoDAO;
import dao.EstUnidadeMedidaDAO;
import forms.busca.FormBuscaCategoria;
import forms.busca.FormBuscaMarca;
import forms.busca.FormBuscaUnidadeMedida;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.EstCategoria;
import model.EstMarca;
import model.EstProduto;
import model.EstUnidadeMedida;

/**
 *
 * @author Anderson
 */
public class FormManutencaoMateriais extends javax.swing.JFrame {

    private EstProdutoDAO produtoDao = new EstProdutoDAO();
    private boolean isEditar = false;
    private EstProduto produtoEditar = new EstProduto();
    public Validador validador;
    FormBuscaMarca formMarca;

    public FormManutencaoMateriais(java.awt.Frame parent, boolean modal) {

        initComponents();

        //inicia o validador
        this.validador = new Validador(Validador.TipoValidator.ICONE);
        this.validador.addObrigatorioValidator(this.txtReferencia);
        this.validador.addObrigatorioValidator(this.txtDescricao);
        this.validador.addObrigatorioValidator(this.txtCategoria);
        this.validador.addObrigatorioValidator(this.txtMarca);
        this.validador.addObrigatorioValidator(this.txtUnidadeMedida);

        //abre o form para buscar uma categoria
        FormBuscaCategoria formCategoria = new FormBuscaCategoria();
        formCategoria.buscarCategorias();
        formCategoria.setFrameBloquear(FormPrincipal.getInstance());
        this.txtCategoria.setFrame(formCategoria);

        //abre o form para buscar uma marca
        formMarca = new FormBuscaMarca();
        formMarca.setFrameBloquear(FormPrincipal.getInstance());
        this.txtMarca.setFrame(formMarca);

        //busca un medida
        FormBuscaUnidadeMedida formUn = new FormBuscaUnidadeMedida();
        formUn.setFrameBloquear(FormPrincipal.getInstance());
        this.txtUnidadeMedida.setFrame(formUn);
    }

    public FormManutencaoMateriais(java.awt.Frame parent, boolean modal, EstProduto produto) {

        initComponents();

        isEditar = true;
        
        //inicia o validador
        this.validador = new Validador(Validador.TipoValidator.ICONE);
        this.validador.addObrigatorioValidator(this.txtReferencia);
        this.validador.addObrigatorioValidator(this.txtDescricao);
        this.validador.addObrigatorioValidator(this.txtCategoria);
        this.validador.addObrigatorioValidator(this.txtMarca);
        this.validador.addObrigatorioValidator(this.txtUnidadeMedida);

        //abre o form para buscar uma categoria
        FormBuscaCategoria formCategoria = new FormBuscaCategoria();
        formCategoria.buscarCategorias();
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
    }

    public void setEvents(ActionListener salvar, ActionListener cancelar) {
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
    private void initComponents() {

        txtReferencia = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        comboAtivo = new javax.swing.JComboBox<>();
        txtDescricao = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCategoria = new components.TextFieldFK();
        txtMarca = new components.TextFieldFK();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtUnidadeMedida = new components.TextFieldFK();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Produtos");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                iniciaTela(evt);
            }
        });

        jLabel2.setText("Referência");

        comboAtivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ativo", "Inativo" }));
        comboAtivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboAtivoActionPerformed(evt);
            }
        });

        jLabel1.setText("Descrição");

        jLabel3.setText("Categoria");

        jLabel4.setText("Marca");

        jLabel5.setText("Unidade Medida");

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUnidadeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnCancelar)
                                .addGap(9, 9, 9)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboAtivo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSalvar))))
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel3))
                    .addContainerGap(26, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboAtivo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(96, 96, 96)
                .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUnidadeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar))
                .addGap(25, 25, 25))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(81, 81, 81)
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(16, 16, 16)
                    .addComponent(jLabel3)
                    .addContainerGap(258, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed

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

        this.dispose();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void iniciaTela(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_iniciaTela
        // TODO add your handling code here:
     
        
    }//GEN-LAST:event_iniciaTela

    private void comboAtivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboAtivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboAtivoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormManutencaoMateriais.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormManutencaoMateriais.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormManutencaoMateriais.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormManutencaoMateriais.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormManutencaoMateriais dialog = new FormManutencaoMateriais(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> comboAtivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private components.TextFieldFK txtCategoria;
    private javax.swing.JTextField txtDescricao;
    private components.TextFieldFK txtMarca;
    private javax.swing.JTextField txtReferencia;
    private components.TextFieldFK txtUnidadeMedida;
    // End of variables declaration//GEN-END:variables
}
