package forms;

import com.alee.extended.window.WebProgressDialog;
import com.alee.laf.WebLookAndFeel;
import com.alee.managers.language.LanguageManager;
import com.alee.utils.ThreadUtils;
import components.Validador;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FormLogin extends javax.swing.JFrame {

    private Boolean fechouProgress;
    private Validador validador;

    public FormLogin() {
        initComponents();
        this.initValidador();
    }

    public void initValidador() {
        this.validador = new Validador(Validador.TipoValidator.TEXTO);
        validador.addObrigatorioValidator(txtUsuario);
        validador.addObrigatorioValidator(txtSenha);

        validador.addMaxLengthValidator(txtUsuario, 30);
        validador.addMaxLengthValidator(txtSenha, 30);
    }

    private boolean valida(String s) {
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnLogar = new com.alee.laf.button.WebButton();
        btnEsqueciSenha = new com.alee.laf.button.WebButton();
        txtUsuario = new javax.swing.JTextField();
        txtSenha = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setMaximumSize(new java.awt.Dimension(423, 205));
        setMinimumSize(new java.awt.Dimension(423, 205));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Usuário");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Senha");

        btnLogar.setText("Logar");
        btnLogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogarActionPerformed(evt);
            }
        });

        btnEsqueciSenha.setText("Esqueci a senha");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSenha)
                    .addComponent(txtUsuario)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 180, Short.MAX_VALUE)
                        .addComponent(btnEsqueciSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLogar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEsqueciSenha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogarActionPerformed

        if (!this.validador.isValid()) {
            return;
        }

        this.startCarregandoSistema();
    }//GEN-LAST:event_btnLogarActionPerformed

    private void startCarregandoSistema() {

        final WebProgressDialog progress = new WebProgressDialog(this, "");
        progress.setText("Configurando sistema...");

        fechouProgress = false;

        final Thread t = new Thread(() -> {

            for (int i = 0; i <= 100 && !fechouProgress; i++) {
                ThreadUtils.sleepSafely(20);
                progress.setProgress(i);
                switch (i) {
                    case 29:
                        progress.setText("Carregando módulos...");
                        break;
                    case 83:
                        progress.setText("Finalizando...");
                        break;
                    default:
                        break;
                }
            }

            if (!fechouProgress) {
                progress.setVisible(false);
                FormPrincipal.start();
                this.dispose();
            }
        });

        progress.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                fechouProgress = true;
            }
        });

        t.setDaemon(true);
        t.start();

        progress.setModal(true);
        progress.setVisible(true);
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {

            WebLookAndFeel.setDecorateAllWindows(true);
            WebLookAndFeel.setDecorateDialogs(true);
            WebLookAndFeel.setDecorateFrames(true);

            LanguageManager.setDefaultLanguage(LanguageManager.PORTUGUESE);

            WebLookAndFeel.install();

            FormLogin form = new FormLogin();
            form.setLocationRelativeTo(null);
            form.setVisible(true);

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.button.WebButton btnEsqueciSenha;
    private com.alee.laf.button.WebButton btnLogar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtSenha;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
