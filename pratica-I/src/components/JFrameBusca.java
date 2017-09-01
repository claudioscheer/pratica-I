package components;

import java.awt.Frame;
import java.util.function.Consumer;
import javax.swing.JFrame;

public class JFrameBusca extends JFrame {

    public static enum JFrameBuscaTipo {
        funtion,
        textFieldFK
    }

    private Frame frameBloquear;
    private TextFieldFK textFieldFK;
    private Consumer<Object> function;
    private JFrameBuscaTipo frameBuscaTipo;

    public Frame getFrameBloquear() {
        return frameBloquear;
    }

    public void setFrameBloquear(Frame frameBloquear) {
        this.frameBloquear = frameBloquear;
    }

    public TextFieldFK getTextFieldFK() {
        return this.textFieldFK;
    }

    public void setTextFieldFK(TextFieldFK textFieldFK) {
        this.textFieldFK = textFieldFK;
        this.setFrameBuscaTipo(JFrameBuscaTipo.textFieldFK);
    }

    public Consumer<Object> getFunction() {
        return function;
    }

    public void setFunction(Consumer<Object> function) {
        this.function = function;
        this.setFrameBuscaTipo(JFrameBuscaTipo.funtion);
    }

    public JFrameBuscaTipo getFrameBuscaTipo() {
        return frameBuscaTipo;
    }

    public void setFrameBuscaTipo(JFrameBuscaTipo frameBuscaTipo) {
        this.frameBuscaTipo = frameBuscaTipo;
    }
}
