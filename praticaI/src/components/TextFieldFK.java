package components;

import com.alee.laf.label.WebLabel;
import com.alee.laf.text.WebTextField;
import forms.FormPrincipal;
import utils.Utils;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TextFieldFK extends WebTextField implements MouseListener {

    private Object value;
    private JFrameBuscar frame;

    public TextFieldFK() {
        this.addMouseListener(this);

        WebLabel imgbuscar = new WebLabel(Utils.getImage(Utils.Image.buscar));
        imgbuscar.setMargin(new Insets(0, 0, 0, 5));
        this.setTrailingComponent(imgbuscar);
        this.setFieldMargin(0, 6, 0, 6);
        this.setEditable(false);
    }

    public void setFrame(JFrameBuscar frame) {
        this.frame = frame;
        this.frame.setTextFieldFK(this);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        this.frame.setVisible(true);

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
}
