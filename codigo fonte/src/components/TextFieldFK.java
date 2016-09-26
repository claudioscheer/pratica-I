package components;

import com.alee.laf.label.WebLabel;
import com.alee.laf.text.WebTextField;
import forms.FormBuscaEspecificacao;
import forms.FormPrincipal;
import utils.Utils;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TextFieldFK extends WebTextField implements MouseListener, KeyListener {

    private Object value;

    public TextFieldFK() {
        this.addMouseListener(this);
        this.addKeyListener(this);

        WebLabel imgbuscar = new WebLabel(Utils.getImage(Utils.Image.buscar));
        imgbuscar.setMargin(new Insets(0, 0, 0, 5));
        this.setTrailingComponent(imgbuscar);

        this.setFieldMargin(0, 6, 0, 6);
        this.setEditable(false);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        FormBuscaEspecificacao t = new FormBuscaEspecificacao();
        t.setVisible(true);

        FormPrincipal f = FormPrincipal.getInstance();
        f.setEnabled(false);
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

}
