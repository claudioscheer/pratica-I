package forms.patrimonio;

import com.alee.laf.desktoppane.WebInternalFrame;
import com.alee.laf.optionpane.WebOptionPane;
import components.panelsCads.PanelCadAtivoImobilizado;
import components.panelsListagem.PanelConsultaAtivoImobilizado;
import components.panelsListagem.PanelGerarDepreciacao;
import dao.PatAtivoImobilizadoDAO;
import forms.FormPrincipal;
import utils.Utils;
import java.awt.Dimension;
import model.PatAtivoImobilizado;

public class FormDepreciacoes extends WebInternalFrame {

    public FormDepreciacoes() {
        super("Depreciações", true, true, true, true);
        this.initComponents();
        
        FormPrincipal.getInstance().setEnabled(true);
    }

    private void initComponents() {
        this.setBounds(10, 10, Utils.wPadrao, Utils.hPadrao);
        this.setMinimumSize(new Dimension(Utils.wPadrao, Utils.hPadrao));
        this.setBackground(Utils.CoresPadrao.fundoPadrao);

        this.setFrameIcon(Utils.getImage(Utils.Image.frame));

        this.panelGerarDepreciacao = new PanelGerarDepreciacao();

        this.panelGerarDepreciacao.setEvents((e) -> {
            this.gerarDepreciacao();
        }, (e) -> {
            this.cancelarDepreciacao();
        });

        this.add(this.panelGerarDepreciacao);
    }

    private void gerarDepreciacao() {

    }

    private void cancelarDepreciacao() {
        this.dispose();
        FormPrincipal.getInstance().setEnabled(true);
    }

    private PanelGerarDepreciacao panelGerarDepreciacao;

}
