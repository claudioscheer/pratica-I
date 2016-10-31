package components;

import com.alee.laf.text.WebTextField;
import javax.swing.JLabel;

public class TextFieldValorMonetario extends WebTextField {

    private String textoMonetario = " R$";
    private final JLabel label;

    public TextFieldValorMonetario() {
        this.label = new JLabel();
        this.label.setText(this.textoMonetario);
        this.setLeadingComponent(this.label);
        this.setFieldMargin(0, 6, 0, 6);
    }

    public void setTextoMonetario(String textoMonetario) {
        this.textoMonetario = textoMonetario;
        this.label.setText(this.textoMonetario);
    }
}
