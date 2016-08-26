package components;

import com.alee.extended.image.WebImage;
import com.alee.laf.text.WebTextField;
import com.alee.managers.language.data.TooltipWay;
import com.alee.managers.tooltip.TooltipManager;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class TextField extends WebTextField implements KeyListener {

    private final ImageIcon valido = new ImageIcon(getClass().getResource("/imagens/cancel.png"));
    private final ImageIcon invalido = new ImageIcon(getClass().getResource("/imagens/checked.png"));

    private Boolean required;
    private int maxlength;
    private JLabel icon;
    private Boolean valid;

    public TextField() {
        this.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.initJLabel();
        //this.icon.setCursor(Cursor.getDefaultCursor());
        //this.icon.setMargin(5, 0, 5, 3);
        this.setTrailingComponent(this.icon);
        this.setFieldMargin(0, 6, 0, 6);
        this.addKeyListener(this);
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
        if (required) {
            this.setInvalid();
        }
    }

    public Boolean getValid() {
        return valid;
    }

    public int getMaxlength() {
        return maxlength;
    }

    public void setMaxlength(int maxlength) {
        this.maxlength = maxlength;
    }

    private void setInvalid() {
        TooltipManager.setTooltip(this.icon, "Inválido", TooltipWay.trailing, 0);
        this.icon.setIcon(this.invalido);
        this.valid = false;
    }

    private void initJLabel() {
        this.icon = new JLabel();

        Border border = this.icon.getBorder();
        Border margin = new EmptyBorder(0, 5, 0, 5);
        this.icon.setBorder(new CompoundBorder(border, margin));
    }

    private void setValid() {
        TooltipManager.setTooltip(this.icon, "Válido", TooltipWay.trailing, 0);
        this.icon.setIcon(this.valido);
        this.valid = true;
    }

    private void limparValidator() {
        this.icon.setIcon(null);
        this.valid = true;
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (this.required) {
            if (this.getText().isEmpty()) {
                this.setInvalid();
            } else {
                this.setValid();
            }
        }

        if (this.maxlength != 0) {
            if (this.getText().length() >= this.maxlength) {
                //getToolkit().beep();
                this.setText(this.getText().substring(0, maxlength));
            }
        }
    }

}
