package components;

import com.alee.managers.language.data.TooltipWay;
import com.alee.managers.tooltip.TooltipManager;
import utils.Utils;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class Validador {

    public static enum TipoValidator {
        ICONE,
        TEXTO
    }

    private final Map<JComponent, List<FuncaoValidador>> componentes;
    private final TipoValidator tipoValidator;

    public Validador(TipoValidator tipoValidator) {
        componentes = new HashMap<>();
        this.tipoValidator = tipoValidator;
    }

    public void addObrigatorioValidator(JComponent componente) {
        this.addValidador(componente, (s) -> (!s.isEmpty()), "Campo obrigatório");
    }

    public void addMaxLengthValidator(JComponent componente, int tamanho) {
        this.addValidador(componente, (s) -> (s.length() <= tamanho), "O tamanho máximo é de " + tamanho + " caracteres");
    }

    public void addMinLengthValidator(JComponent componente, int tamanho) {
        this.addValidador(componente, (s) -> (s.length() >= tamanho), "O tamanho mínimo é de " + tamanho + " caracteres");
    }

    public void addMaiorQueValidator(JComponent componente, double minimo) {
        this.addValidador(componente, (s) -> maiorQue(s, minimo), "O valor mínimo é " + minimo);
    }

    public void addMenorQueValidator(JComponent componente, double maximo) {
        this.addValidador(componente, (s) -> menorQue(s, maximo), "O valor máximo é " + maximo);
    }

    public void addApenasNumeroValidator(JComponent componente) {
        this.addValidador(componente, (s) -> apenasNumeros(s), "Apenas números são permitidos");
    }

    public void addValidador(JComponent componente, Predicate<String> function, String mensagem) {
        if (!componentes.containsKey(componente)) {
            componentes.put(componente, new ArrayList<>());
            criarListener(componente);
        }
        componentes.get(componente).add(new FuncaoValidador(componente, function, mensagem));
    }

    private boolean menorQue(String s, double maximo) {
        try {
            s = s.replace(",", ".");
            double valor = Double.parseDouble(s);
            return valor <= maximo;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean maiorQue(String s, double minimo) {
        try {
            s = s.replace(",", ".");
            double valor = Double.parseDouble(s);
            return valor >= minimo;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean apenasNumeros(String s) {
        try {
            s = s.replace(",", ".");
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void criarListener(JComponent componente) {
        if (componente instanceof JTextComponent) {
            ((JTextComponent) componente).addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    validarComponente(componente);
                }
            });
        } else if (componente instanceof JComboBox) {
            ((JComboBox) componente).addActionListener((e) -> {
                validarComponente(componente);
            });
        }
    }

    public boolean isValid() {
        boolean valido = true;
        for (Map.Entry<JComponent, List<FuncaoValidador>> entry : componentes.entrySet()) {
            JComponent componente = entry.getKey();
            if (!validarComponente(componente)) {
                valido = false;
            }
        }
        return valido;
    }

    private boolean validarComponente(JComponent componente) {
        boolean valido = true;
        List<FuncaoValidador> funcoes = componentes.get(componente);
        int indiceValidar = -1;

        for (int i = 0; i < funcoes.size(); i++) {
            FuncaoValidador funcaoValidador = funcoes.get(i);
            if (!funcaoValidador.funcao.test(getValorComponente(componente))) {
                valido = false;
                indiceValidar = i;
                break;
            }
            funcaoValidador.escondeValidador();
        }

        if (valido) {
            funcoes.get(0).toggleValidador(valido, this.tipoValidator);
        } else {
            funcoes.get(indiceValidar).toggleValidador(valido, this.tipoValidator);
        }
        return valido;
    }

    private String getValorComponente(JComponent componente) {
        if (componente instanceof JTextArea) {
            return ((JTextArea) componente).getText();
        } else if (componente instanceof JTextField) {
            return ((JTextField) componente).getText();
        } else if (componente instanceof JComboBox) {
            return ((JComboBox) componente).getSelectedItem() + "";
        } else if (componente instanceof JSpinner) {
            return ((JSpinner) componente).getValue() + "";
        } else {
            return "";
        }
    }

    private class FuncaoValidador {

        private Predicate<String> funcao;
        private boolean valido;
        private final String mensagem;
        private final JLabel label;
        private final JComponent componente;

        private FuncaoValidador(JComponent componente, Predicate<String> function, String mensagem) {
            this.componente = componente;
            this.funcao = function;
            this.valido = false;
            this.mensagem = mensagem;
            this.label = new JLabel(mensagem);
            this.initLabel();
        }

        private void initLabel() {
            Container parent = this.componente;
            while (parent != null && !(parent instanceof JPanel || parent instanceof JFrame || parent instanceof JDialog)) {
                parent = parent.getParent();
            }
            if (parent != null) {
                parent.add(label);
            }
            this.label.setVisible(false);
        }

        private void calculaPosicaoValidador(TipoValidator tipoValidator) {
            if (tipoValidator == TipoValidator.ICONE) {
                int x = this.componente.getX() + this.componente.getWidth() + 3;
                int y = this.componente.getY() + (this.componente.getHeight() / 2) - 8;
                this.label.setLabelFor(this.componente);
                this.label.setBounds(x, y, 16, 16);
            } else {
                this.label.setFont(new Font(this.label.getFont().getName(), Font.PLAIN, 10));
                this.label.setForeground(Color.RED);
                this.label.setBounds(this.componente.getX(), this.componente.getY() + this.componente.getHeight() - 2, this.componente.getWidth(), 16);
            }
        }

        public void escondeValidador() {
            this.label.setVisible(false);
        }

        public void toggleValidador(boolean valido, TipoValidator tipoValidator) {
            this.valido = valido;

            if (valido) {
                if (tipoValidator == TipoValidator.ICONE) {
                    this.label.setIcon(Utils.getImage(Utils.Image.ok));
                    TooltipManager.setTooltip(this.label, "Válido", TooltipWay.left, 0);
                } else {
                    this.label.setText("");
                }
            } else if (tipoValidator == TipoValidator.ICONE) {
                this.label.setIcon(Utils.getImage(Utils.Image.erro));
                TooltipManager.setTooltip(this.label, this.mensagem, TooltipWay.left, 0);
            } else {
                this.label.setText(this.mensagem);
            }
            this.label.setVisible(true);
            calculaPosicaoValidador(tipoValidator);
        }
    }
}
