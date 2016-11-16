package components.panelsCads;

import com.alee.laf.panel.WebPanel;
import components.Validador;
import dao.PessoaDAO;
import java.awt.event.ActionListener;
import model.CarPessoa;
import model.TipoUsuario;

public class PanelCadPessoa extends WebPanel {

    public Validador validador;
    private CarPessoa pessoa;
    public boolean editando;
    
    PessoaDAO p = new PessoaDAO();

    public PanelCadPessoa() {}

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
    
    public CarPessoa getPessoa() {
        if (!this.editando) {
            this.pessoa = new CarPessoa();
        }
        
        if (radiobtnPF.isSelected()) {     // verifica se a pessoa é do tipo Física
            pessoa.setPessoaTipo(0); }  // atribui ao tipo o valor adequado
        else {                              // senão, verifica se a pessoa é do tipo Jurídica
            if (radiobtnPJ.isSelected()) {
                pessoa.setPessoaTipo(1);  // atribui ao tipo o valor adequado
            }
        }
        pessoa.setPessoaCpfCnpj(fieldCpfCnpj.getText());
        pessoa.setPessoaNome(fieldNome.getText());
        pessoa.setPessoaEndereco(fieldEndereco.getText());
        pessoa.setPessoaComplemento(fieldComplemento.getText());
        pessoa.setPessoaBairro(fieldBairro.getText());
        pessoa.setPessoaFone(fieldFone.getText());        
        pessoa.setPessoaEmail(fieldEmail.getText());
        pessoa.setPessoaCEP(Integer.parseInt(fieldCEP.getText()));
        pessoa.setPessoaCidade(fieldCidade.getText());
        pessoa.setPessoaUF((String) comboboxUF.getSelectedItem());
        if (radiobtnAdmin.isSelected()) {
            pessoa.setPessoaLevel(TipoUsuario.ADMIN); }
        else {
            if (radiobtnUser.isSelected()) {
                pessoa.setPessoaLevel(TipoUsuario.USER); }
        }
        pessoa.setPessoaLogin(fieldUser.getText());
        pessoa.setPessoaSenha(fieldSenha.getText());
        return this.pessoa;
    }

    public void setDadosEditar(CarPessoa pessoa) {
        this.pessoa = pessoa;
        this.editando = true;
        this.setDadosForm();
    }
    
    private void setDadosForm() {
        this.fieldNome.setText(this.pessoa.getPessoaNome());
        this.fieldCpfCnpj.setText(this.pessoa.getPessoaCpfCnpj());
        this.fieldFone.setText(this.pessoa.getPessoaFone());
        this.fieldEmail.setText(this.pessoa.getPessoaEmail());
        this.fieldEndereco.setText(this.pessoa.getPessoaEndereco());
        this.fieldComplemento.setText(this.pessoa.getPessoaComplemento());
        this.fieldBairro.setText(this.pessoa.getPessoaBairro());
        this.fieldCidade.setText(this.pessoa.getPessoaCidade());
        this.comboboxUF.setSelectedItem(this.pessoa.getPessoaUF());
        this.fieldCEP.setText(String.valueOf(this.pessoa.getPessoaCEP()));
        if (this.pessoa.getPessoaTipo() == 0) {
            radiobtnPF.setSelected(true);
        }
        if (this.pessoa.getPessoaTipo() == 1) {
            radiobtnPJ.setSelected(true);
        }
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
    
    private void ativaCampos() {
        if (radiobtnAdmin.isSelected()) {
            fieldUser.setEnabled(true);
            fieldUser.requestFocus();
            fieldSenha.setEnabled(true);
            txtLogin.setEnabled(true);
            txtSenha.setEnabled(true);
        } else if (radiobtnUser.isSelected()) {
            fieldUser.setEnabled(true);
            fieldUser.requestFocus();
            fieldSenha.setEnabled(true);
            txtLogin.setEnabled(true);
            txtSenha.setEnabled(true);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupTipo = new javax.swing.ButtonGroup();
        btnGroupLogin = new javax.swing.ButtonGroup();
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
        comboboxUF = new javax.swing.JComboBox<>();
        txtLogin = new javax.swing.JLabel();
        fieldUser = new javax.swing.JTextField();
        txtSenha = new javax.swing.JLabel();
        fieldSenha = new javax.swing.JPasswordField();
        radiobtnAdmin = new javax.swing.JRadioButton();
        radiobtnUser = new javax.swing.JRadioButton();
        txtNivel = new javax.swing.JLabel();
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

        btnGroupTipo.add(radiobtnPF);
        radiobtnPF.setText("Pessoa Física");
        radiobtnPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiobtnPFActionPerformed(evt);
            }
        });

        btnGroupTipo.add(radiobtnPJ);
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

        comboboxUF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));

        txtLogin.setText("Login:");
        txtLogin.setEnabled(false);

        fieldUser.setEnabled(false);

        txtSenha.setText("Senha:");
        txtSenha.setEnabled(false);

        fieldSenha.setEnabled(false);

        btnGroupLogin.add(radiobtnAdmin);
        radiobtnAdmin.setText("Administrador");
        radiobtnAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiobtnAdminActionPerformed(evt);
            }
        });

        btnGroupLogin.add(radiobtnUser);
        radiobtnUser.setText("Usuário");
        radiobtnUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiobtnUserActionPerformed(evt);
            }
        });

        txtNivel.setText("Nível de acesso:");

        javax.swing.GroupLayout panelItensLayout = new javax.swing.GroupLayout(panelItens);
        panelItens.setLayout(panelItensLayout);
        panelItensLayout.setHorizontalGroup(
            panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelItensLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelItensLayout.createSequentialGroup()
                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtComplemento)
                            .addComponent(fieldComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBairro)
                            .addComponent(fieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtEndereco)
                    .addComponent(txtCpfCnpj)
                    .addComponent(fieldCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCadastrar)
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
                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelItensLayout.createSequentialGroup()
                                .addGap(225, 225, 225)
                                .addComponent(txtUF))
                            .addGroup(panelItensLayout.createSequentialGroup()
                                .addComponent(fieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboboxUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtCidade))
                        .addGap(16, 16, 16)
                        .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCEP)
                            .addComponent(fieldCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(fieldEndereco)
                    .addComponent(fieldNome))
                .addGap(45, 45, 45)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSenha)
                    .addComponent(txtLogin)
                    .addGroup(panelItensLayout.createSequentialGroup()
                        .addComponent(radiobtnAdmin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radiobtnUser))
                    .addComponent(fieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNivel)
                    .addComponent(fieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(136, Short.MAX_VALUE))
        );
        panelItensLayout.setVerticalGroup(
            panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelItensLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(txtCadastrar)
                .addGap(28, 28, 28)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTipo)
                    .addComponent(txtNivel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radiobtnPF)
                    .addComponent(radiobtnPJ)
                    .addComponent(radiobtnAdmin)
                    .addComponent(radiobtnUser))
                .addGap(18, 18, 18)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome)
                    .addComponent(txtLogin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCpfCnpj)
                    .addComponent(txtSenha))
                .addGap(21, 21, 21)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(txtFone)
                .addGap(18, 18, 18)
                .addComponent(fieldFone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtEmail)
                .addGap(25, 25, 25)
                .addComponent(fieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtEndereco)
                .addGap(18, 18, 18)
                .addComponent(fieldEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtComplemento)
                    .addComponent(txtBairro))
                .addGap(18, 18, 18)
                .addGroup(panelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
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
                            .addComponent(comboboxUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(94, Short.MAX_VALUE))
        );

        scrollCadastro.setViewportView(panelItens);

        panelOpcoes.setBackground(new java.awt.Color(255, 255, 255));

        btnSalvar.setText("Salvar");

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
        this.validador.isValid();
        fieldNome.requestFocus();
    }//GEN-LAST:event_radiobtnPFActionPerformed

    private void radiobtnPJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiobtnPJActionPerformed
        this.validador.isValid();
        fieldNome.requestFocus();
    }//GEN-LAST:event_radiobtnPJActionPerformed

    private void radiobtnAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiobtnAdminActionPerformed
        ativaCampos();
    }//GEN-LAST:event_radiobtnAdminActionPerformed

    private void radiobtnUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiobtnUserActionPerformed
        ativaCampos();
    }//GEN-LAST:event_radiobtnUserActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.button.WebButton btnCancelar;
    private javax.swing.ButtonGroup btnGroupLogin;
    private javax.swing.ButtonGroup btnGroupTipo;
    private com.alee.laf.button.WebButton btnSalvar;
    private javax.swing.JComboBox<String> comboboxUF;
    private javax.swing.JTextField fieldBairro;
    private javax.swing.JTextField fieldCEP;
    private javax.swing.JTextField fieldCidade;
    private javax.swing.JTextField fieldComplemento;
    private javax.swing.JTextField fieldCpfCnpj;
    private javax.swing.JTextField fieldEmail;
    private javax.swing.JTextField fieldEndereco;
    private javax.swing.JTextField fieldFone;
    private javax.swing.JTextField fieldNome;
    private javax.swing.JPasswordField fieldSenha;
    private javax.swing.JTextField fieldUser;
    private javax.swing.JPanel panelItens;
    private javax.swing.JPanel panelOpcoes;
    private javax.swing.JRadioButton radiobtnAdmin;
    private javax.swing.JRadioButton radiobtnPF;
    private javax.swing.JRadioButton radiobtnPJ;
    private javax.swing.JRadioButton radiobtnUser;
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
    private javax.swing.JLabel txtLogin;
    private javax.swing.JLabel txtNivel;
    private javax.swing.JLabel txtNome;
    private javax.swing.JLabel txtSenha;
    private javax.swing.JLabel txtTipo;
    private javax.swing.JLabel txtUF;
    // End of variables declaration//GEN-END:variables

}