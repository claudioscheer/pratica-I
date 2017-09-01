package components;

import com.alee.laf.text.WebTextField;
import java.awt.*;
import java.text.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.*;
import utils.Utils;

public class TextFieldValorMonetario extends WebTextField {

    private String textoMonetario = " R$";
    private final JLabel label;

    private DecimalFormat format;
    private String decimal;

    private AbstractDocument doc;
    private FiltroTexto textFilter;

    public TextFieldValorMonetario() {
        this.label = new JLabel();
        this.format = new DecimalFormat("###,##0.00");
        this.decimal = Character.toString(this.format.getDecimalFormatSymbols().getDecimalSeparator());

        this.label.setText(this.textoMonetario);
        this.setLeadingComponent(this.label);
        this.setFieldMargin(0, 6, 0, 6);
        this.setColumns(this.format.toPattern().length());
        this.setHorizontalAlignment(JFormattedTextField.TRAILING);

        this.textFilter = new FiltroTexto();
        this.doc = (AbstractDocument) this.getDocument();
        this.doc.setDocumentFilter(this.textFilter);

        this.setValue(0.0);
    }

    public double getValue() {
        return Double.parseDouble(utils.Utils.removerCaracteresDoubleString(this.getText()));
    }

    public void setValue(double valor) {
        String s = this.format.format(valor);
//        this.doc.setDocumentFilter(null);
        this.setText(s);
//        this.doc.setDocumentFilter(this.textFilter);
    }

    public void setTextoMonetario(String textoMonetario) {
        this.textoMonetario = textoMonetario;
        this.label.setText(this.textoMonetario);
    }

    @Override
    public void setText(String text) {
        Number number = this.format.parse(text, new ParsePosition(0));
        if (number != null) {
            super.setText(text);
        }
    }

    public class FiltroTexto extends DocumentFilter {

        @Override
        public void insertString(FilterBypass fb, int offs, String str, AttributeSet a)
                throws BadLocationException {
            replace(fb, offs, 0, str, a);
        }

        @Override
        public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a)
                throws BadLocationException {

            if ("0123456789".contains(str)) {
                Document doc = fb.getDocument();
                StringBuilder sb = new StringBuilder(doc.getText(0, doc.getLength()));
                int decimalOffset = sb.indexOf(decimal);
                if (decimalOffset != -1) {
                    sb.deleteCharAt(decimalOffset);
                    sb.insert(decimalOffset + 1, decimal);
                }
                sb.append(str);
                try {
                    String text = format.format(format.parse(sb.toString()));
                    super.replace(fb, 0, doc.getLength(), text, a);
                } catch (ParseException e) {
                }
            } else if (Utils.isDouble(str)) {
                try {
                    String text = format.format(format.parse(str));
                    super.replace(fb, 0, doc.getLength(), text, a);
                } catch (ParseException ex) {
                }
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        }

        @Override
        public void remove(DocumentFilter.FilterBypass fb, int offset, int length)
                throws BadLocationException {
            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder(doc.getText(0, doc.getLength()));

            int decimalOffset = sb.indexOf(decimal);

            if (decimalOffset != -1) {
                sb.deleteCharAt(decimalOffset);
                sb.insert(decimalOffset - 1, decimal);
            }

            sb.deleteCharAt(sb.length() - 1);

            try {
                String text = format.format(format.parse(sb.toString()));
                super.replace(fb, 0, doc.getLength(), text, null);
            } catch (ParseException e) {
            }
        }
    }
}
