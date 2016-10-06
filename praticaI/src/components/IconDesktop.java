/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import com.alee.laf.button.WebButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

/**
 *
 * @author claudio
 */
public class IconDesktop extends WebButton {

    public IconDesktop(String titulo, ImageIcon icone) {
        super(titulo, icone);
        this.initIcon();
    }

    private void initIcon() {
        this.setRolloverDecoratedOnly(true);
        this.setHorizontalTextPosition(WebButton.CENTER);
        this.setVerticalTextPosition(WebButton.BOTTOM);
        this.setSize(120, 75);

        MoverComponente moveItem = new MoverComponente();
        this.addMouseListener(moveItem);
        this.addMouseMotionListener(moveItem);
    }

    public void setActionListener(ActionListener action) {
        this.addActionListener(action);
    }

}
