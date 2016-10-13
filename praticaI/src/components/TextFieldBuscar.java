package components;

import com.alee.laf.button.WebButton;
import com.alee.laf.text.WebTextField;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;

public class TextFieldBuscar extends WebTextField {

    private final PanelItemBuscar panelItemBuscar;

    public TextFieldBuscar() {
        this.panelItemBuscar = new PanelItemBuscar();
        this.panelItemBuscar.setTextButton("Buscar");
        this.setTrailingComponent(panelItemBuscar);
        this.setFieldMargin(0, 6, 0, 6);
    }

    public void setEventBuscar(ActionListener al) {
        this.panelItemBuscar.setEventBuscar(al);
    }

    public void addOpcoesBuscar(String[] opcoes) {
        this.panelItemBuscar.addOpcoesBuscar(opcoes);
    }

    public void showComboOpcoes(boolean show) {
        this.panelItemBuscar.showComboOpcoes(show);;
    }

    public void setButtonText(String texto) {
        this.panelItemBuscar.btnBuscar.setText(texto);
    }

}
