package components.panelsCads;

import com.alee.laf.panel.WebPanel;
import components.Validador;
import java.awt.event.ActionListener;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import model.CarPessoa;
import utils.Utils;

public class PanelCadPessoa extends WebPanel {

    public Validador validador;
    

    public PanelCadPessoa() {
        
    }

    public void init() {
        this.initComponents();
        this.validador = new Validador(Validador.TipoValidator.ICONE);
        this.validador.addObrigatorioValidator(fieldNome);
        this.validador.addObrigatorioValidator(fieldCpfCnpj);
        this.validador.addObrigatorioValidator(fieldFone);
        this.validador.addValidador(fieldNome, s -> validaNome(s), "Nome inválido");
        this.validador.addValidador(fieldCpfCnpj, s -> validaCpfCnpj(s), "CPF/CNPJ inválido");
        this.validador.addValidador(fieldFone, s -> validaFone(s), "Fone inválido");
        this.validador.addValidador(fieldEmail, s -> validaEmail(s), "E-mail inválido");
        
        scrollCadastro.getVerticalScrollBar().setUnitIncrement(20);
        
    }

    public void setEvents(ActionListener salvar, ActionListener cancelar) {
        this.btnSalvar.addActionListener(salvar);
        this.btnCancelar.addActionListener(cancelar);
    }

    public boolean validaNome(String texto) {
        if (radiobtnPF.isSelected()) {
            if (texto.matches("([a-zA-Z ])+")) {   // valida se o texto do campo Nome possui somente letras
                return true;
            } else {
                return false;
            }
        } else if (radiobtnPJ.isSelected()) {
            //if (texto.matches("([a-zA-Z0-9 ])")) {
            return true;
            //}  // valida o Nome, permitindo letras e numeros, para nome de empresas
        }
        return false;
    }
    
    public boolean validaFone(String texto) {
        return texto.matches("(?:\\d*\\.)?\\d+");
    }
    public boolean validaEmail(String texto) {
        return texto.matches("[(a-zA-Z-0-9-\\_\\+\\.\\-)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}\\.[(a-zA-Z)]{2,3}|[(a-zA-Z-0-9-\\_\\+\\.\\-)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}");
    }

    public boolean validaCpfCnpj(String texto) {
        if (radiobtnPF.isSelected()) {
            return texto.matches("\\b\\d{11}");
        } else if (radiobtnPJ.isSelected()) {
            return texto.matches("\\b\\d{14}");
        }
        return true;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        scrollCadastro = new javax.swing.JScrollPane();
        panelItens = new javax.swing.JPanel();
        fieldNome = new javax.swing.JTextField();
        txtNome = new javax.swing.JLabel();
        txtCpfCnpj = new javax.swing.JLabel();
        fieldCpfCnpj = new javax.swing.JTextField();
        txtTipo = new javax.swing.JLabel();
        radiobtnPF = new javax.swing.JRadioButton();
        radiobtnPJ = new javax.swing.JRadioButton();
        txtEndereco = new javax.swing.JLabel();
        fieldEndereco = new javax.swing.JTextField();
        txtCadastrar = new javax.swing.JLabel();
        txtFone = new javax.swing.JLabel();
        fieldFone = new javax.swing.JTextField();
        txtEmail = new javax.swing.JLabel();
        fieldEmail = new javax.swing.JTextField();
        txtComplemento = new javax.swing.JLabel();
        fieldComplemento = new javax.swing.JTextField();
        txtBairro = new javax.swing.JLabel();
        fieldBairro = new javax.swing.JTextField();
        txtCidade = new javax.swing.JLabel();
        fieldCidade = new javax.swing.JTextField();
        txtUF = new javax.swing.JLabel();
        txtCEP = new javax.swing.JLabel();
        fieldCEP = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        panelOpcoes = new javax.swing.JPanel();
        btnSalvar = new com.alee.laf.button.WebButton();
        btnCancelar = new com.alee.laf.button.WebButton();

        setMinimumSize(null);

        scrollCadastro.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollCadastro.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollCadastro.setMaximumSize(new java.awt.Dimension(0, 0));

        panelItens.setBackground(new java.awt.Color(255, 255, 255));
        panelItens.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        panelItens.setPreferredSize(new java.awt.Dimension(0, 1300));

        txtNome.setText("Nome:");

        txtCpfCnpj.setText("CPF / CNPJ:");

        txtTipo.setText("Tipo:");

        buttonGroup1.add(radiobtnPF);
        radiobtnPF.setText("Pessoa Física");
        radiobtnPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiobtnPFActionPerformed(evt);
            }
        });

        buttonGroup1.add(radiobtnPJ);
        radiobtnPJ.setText("Pessoa Jurídica");
        radiobtnPJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiobtnPJActionPerformed(evt);
            }
        });

        txtEndereco.setText("Endereço:");

        txtCadastrar.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtCadastrar.setText("CADASTRAR PESSOA - Informe os dados a seguir");

        txtFone.setText("Fone:");

        txtEmail.setText("E-mail:");

        txtComplemento.setText("Complemento:");

        txtBairro.setText("Bairro:");

        txtCidade.setText("Cidade:");

        txtUF.setText("UF:");

        txtCEP.setText("CEP: ");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));

        javax.swing.GroupLayout panelItensLayout = new javax.swing.GroupLayout(panelItens);
        panelItens.setLayout(panelItensLayout);
        panelItensLayout.setHorizontalGroup(
            panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelItensLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCpfCnpj)
                    .addComponent(fieldCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCadastrar)
                    .addComponent(fieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNome)
                    .addComponent(txtTipo)
                    .addGroup(panelItensLayout.createSequentialGroup()
                        .addComponent(radiobtnPF)
                        .addGap(8, 8, 8)
                        .addComponent(radiobtnPJ))
                    .addComponent(txtFone)
                    .addComponent(txtEmail)
                    .addComponent(fieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldFone, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelItensLayout.createSequentialGroup()
                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelItensLayout.createSequentialGroup()
                                .addComponent(fieldEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fieldComplemento)
                                    .addGroup(panelItensLayout.createSequentialGroup()
                                        .addComponent(txtUF)
                                        .addGap(41, 41, 41)
                                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCEP)
                                            .addComponent(fieldCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 21, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelItensLayout.createSequentialGroup()
                                .addComponent(txtEndereco)
                                .addGap(167, 167, 167)
                                .addComponent(txtComplemento))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelItensLayout.createSequentialGroup()
                                .addComponent(fieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtCidade, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBairro)
                            .addComponent(fieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(208, Short.MAX_VALUE))
        );
        panelItensLayout.setVerticalGroup(
            panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelItensLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(txtCadastrar)
                .addGap(28, 28, 28)
                .addComponent(txtTipo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radiobtnPF)
                    .addComponent(radiobtnPJ))
                .addGap(18, 18, 18)
                .addComponent(txtNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtCpfCnpj)
                .addGap(18, 18, 18)
                .addComponent(fieldCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtFone)
                .addGap(18, 18, 18)
                .addComponent(fieldFone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtEmail)
                .addGap(18, 18, 18)
                .addComponent(fieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEndereco)
                    .addComponent(txtComplemento)
                    .addComponent(txtBairro))
                .addGap(18, 18, 18)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelItensLayout.createSequentialGroup()
                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCidade)
                            .addComponent(txtUF))
                        .addGap(18, 18, 18)
                        .addComponent(fieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelItensLayout.createSequentialGroup()
                        .addComponent(txtCEP)
                        .addGap(18, 18, 18)
                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(195, Short.MAX_VALUE))
        );

        scrollCadastro.setViewportView(panelItens);

        panelOpcoes.setBackground(new java.awt.Color(255, 255, 255));

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");

        javax.swing.GroupLayout panelOpcoesLayout = new javax.swing.GroupLayout(panelOpcoes);
        panelOpcoes.setLayout(panelOpcoesLayout);
        panelOpcoesLayout.setHorizontalGroup(
            panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOpcoesLayout.createSequentialGroup()
                .addContainerGap(619, Short.MAX_VALUE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelOpcoesLayout.setVerticalGroup(
            panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOpcoesLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelOpcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrollCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrollCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void radiobtnPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiobtnPFActionPerformed
        this.validador.isValid();        // TODO add your handling code here:
    }//GEN-LAST:event_radiobtnPFActionPerformed

    private void radiobtnPJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiobtnPJActionPerformed
        this.validador.isValid();        // TODO add your handling code here:
    }//GEN-LAST:event_radiobtnPJActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        CarPessoa c = new CarPessoa();
        if (radiobtnPF.isSelected()) {     // verifica se a pessoa é do tipo Física
            c.setPessoaTipo(0); }  // atribui ao tipo o valor adequado
        else {                              // senão, verifica se a pessoa é do tipo Jurídica
            if (radiobtnPJ.isSelected()) {
                c.setPessoaTipo(1);  // atribui ao tipo o valor adequado
            }
        }
        c.setPessoaCpfCnpj(fieldCpfCnpj.getText());
        c.setPessoaNome(fieldNome.getText());
        c.setPessoaEndereco(fieldEndereco.getText());
        c.setPessoaComplemento(fieldComplemento.getText());
        c.setPessoaBairro(fieldBairro.getText());
        c.setPessoaFone(fieldFone.getText());        
        c.setPessoaEmail(fieldEmail.getText());

        if (this.validador.isValid()) // se a validação estiver OK
        {
            utils.Utils.notificacao("Cadastro feito com sucesso", Utils.TipoNotificacao.ok, DO_NOTHING_ON_CLOSE);
        } else {                             // senão, há algum erro
            utils.Utils.notificacao("Erro!", Utils.TipoNotificacao.erro, ERROR);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.button.WebButton btnCancelar;
    private com.alee.laf.button.WebButton btnSalvar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField fieldBairro;
    private javax.swing.JTextField fieldCEP;
    private javax.swing.JTextField fieldCidade;
    private javax.swing.JTextField fieldComplemento;
    private javax.swing.JTextField fieldCpfCnpj;
    private javax.swing.JTextField fieldEmail;
    private javax.swing.JTextField fieldEndereco;
    private javax.swing.JTextField fieldFone;
    private javax.swing.JTextField fieldNome;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JPanel panelItens;
    private javax.swing.JPanel panelOpcoes;
    private javax.swing.JRadioButton radiobtnPF;
    private javax.swing.JRadioButton radiobtnPJ;
    private javax.swing.JScrollPane scrollCadastro;
    private javax.swing.JLabel txtBairro;
    private javax.swing.JLabel txtCEP;
    private javax.swing.JLabel txtCadastrar;
    private javax.swing.JLabel txtCidade;
    private javax.swing.JLabel txtComplemento;
    private javax.swing.JLabel txtCpfCnpj;
    private javax.swing.JLabel txtEmail;
    private javax.swing.JLabel txtEndereco;
    private javax.swing.JLabel txtFone;
    private javax.swing.JLabel txtNome;
    private javax.swing.JLabel txtTipo;
    private javax.swing.JLabel txtUF;
    // End of variables declaration//GEN-END:variables
}
