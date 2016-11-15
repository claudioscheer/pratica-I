package forms.patrimonio;

import components.panelsCads.PanelCadAtivoImobilizado;
import forms.FormPrincipal;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import utils.Utils;

public class FormAddAtivoNotaFiscal extends JFrame {

    public FormAddAtivoNotaFiscal() {
        this.initFormCad();
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent we) {
                FormPrincipal.getInstance().setEnabled(true);
            }

        });
    }

    private void initFormCad() {
        this.setBounds(10, 10, Utils.wPadrao, Utils.hPadrao);
        this.setMinimumSize(new Dimension(Utils.wPadrao, Utils.hPadrao));

        this.panelCadastroAtivoImobilizado = new PanelCadAtivoImobilizado();
        this.panelCadastroAtivoImobilizado.init();

        this.add(this.panelCadastroAtivoImobilizado);
    }

    public PanelCadAtivoImobilizado panelCadastroAtivoImobilizado;

}
