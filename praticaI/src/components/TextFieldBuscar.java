package components;

import com.alee.laf.text.WebTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TextFieldBuscar extends WebTextField implements KeyListener {

    private final PanelItemBuscar panelItemBuscar;

    public TextFieldBuscar() {
        this.panelItemBuscar = new PanelItemBuscar();
        this.panelItemBuscar.setTextButton("Buscar");
        this.setTrailingComponent(panelItemBuscar);
        this.addKeyListener(this);
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

    public int getFiltroSelecionado() {
        return this.panelItemBuscar.getFiltroSelecionado();
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
            this.panelItemBuscar.btnBuscar.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }
}
