package forms.patrimonio;

import components.panelsListagem.PanelDepreciar;
import forms.FormPessoa;
import forms.FormPrincipal;
import utils.Utils;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class FormDepreciar extends JFrame {

    public FormDepreciar() {
        this.initComponents();
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                FormPrincipal.getInstance().setEnabled(true);
            }
        });
    }

    private void initComponents() {
        this.setBounds(10, 10, Utils.wPadrao, Utils.hPadrao);
        this.setMinimumSize(new Dimension(Utils.wPadrao, Utils.hPadrao));

        this.panelGerarDepreciacao = PanelDepreciar.getInstance();

        this.panelGerarDepreciacao.setEvents((e) -> {
            this.gerarDepreciacao();
        }, (e) -> {
            this.cancelarDepreciacao();
        });

        this.add(this.panelGerarDepreciacao);
    }

    private void gerarDepreciacao() {
        this.panelGerarDepreciacao.startDepreciacao();
    }

    private void cancelarDepreciacao() {
        this.panelGerarDepreciacao.fecharPanel();
        FormPrincipal.getInstance().setEnabled(true);
        this.dispose();
    }

    private PanelDepreciar panelGerarDepreciacao;

}
