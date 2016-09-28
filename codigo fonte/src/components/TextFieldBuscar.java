package components;

import com.alee.laf.button.WebButton;
import com.alee.laf.text.WebTextField;
import java.awt.Cursor;
import java.awt.event.ActionListener;

public class TextFieldBuscar extends WebTextField {

    private final WebButton button;

    public TextFieldBuscar() {

        this.button = new WebButton("Buscar");
        this.button.setCursor(Cursor.getDefaultCursor());
        this.button.setLeftRightSpacing(10);

        this.setTrailingComponent(button);
        this.setFieldMargin(0, 6, 0, 6);
    }

    

    public void setEventBuscar(ActionListener al) {
        this.button.addActionListener(al);
    }

    public void setButtonText(String texto) {
        this.button.setText(texto);
    }

}
