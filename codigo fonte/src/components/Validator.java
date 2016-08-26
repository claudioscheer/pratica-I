package components;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

public class Validator {

    private final Map<JComponent, List<Function<String, Boolean>>> elementos;

    public Validator() {
        this.elementos = new HashMap<>();
    }

    private void addListner(JComponent elemento) {
        if (elemento instanceof JTextComponent) {

            ((JTextComponent) elemento).addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
        } else if (elemento instanceof JComboBox) {
            ((JComboBox) elemento).addActionListener((e) -> {
            });
        }
    }

    public void addValidator(JComponent elemento, Function<String, Boolean> funcao) {
        if (!this.elementos.containsKey(elemento)) {
            elementos.put(elemento, new ArrayList<>());
            addListner(elemento);
        }
    }

}
