package forms;

import com.alee.extended.panel.CenterPanel;
import com.alee.extended.panel.GroupPanel;
import com.alee.extended.transition.ComponentTransition;
import com.alee.extended.transition.TransitionListener;
import com.alee.extended.transition.effects.Direction;
import com.alee.extended.transition.effects.curtain.CurtainTransitionEffect;
import com.alee.extended.transition.effects.curtain.CurtainType;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.toolbar.WebToolBar;
import com.alee.managers.language.LanguageManager;
import com.alee.utils.SwingUtils;
import utils.Utils;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class teste extends javax.swing.JFrame {

    public teste() {
        initComponents();
        b();
    }

    public void b() {

        WebLabel loader = new WebLabel(Utils.getImage(Utils.Image.gifcarregando));
        loader.setPreferredSize(new Dimension(48, 48));
        loader.setOpaque(true);
        
        

    }

    public void a() {

        // Transition panel
        final ComponentTransition componentTransition = new ComponentTransition();

        // Transition effect
        final CurtainTransitionEffect effect = new CurtainTransitionEffect();
        effect.setType(CurtainType.fade);
        effect.setSize(10);
        effect.setSpeed(2);
        componentTransition.setTransitionEffect(effect);

        // Toolbar #1
        final WebToolBar toolBar1 = new WebToolBar(WebToolBar.HORIZONTAL);
        toolBar1.setFloatable(false);
        toolBar1.add(WebButton.createIconWebButton(Utils.getImage(Utils.Image.adicionar), true));
        toolBar1.addSeparator();
        toolBar1.add(WebButton.createIconWebButton(Utils.getImage(Utils.Image.deletar), true));
        toolBar1.addSeparator();
        toolBar1.add(WebButton.createIconWebButton(Utils.getImage(Utils.Image.editar), true));
        toolBar1.addToEnd(WebButton.createIconWebButton(Utils.getImage(Utils.Image.erro), true));

        // Toolbar #2
        final WebToolBar toolBar2 = new WebToolBar(WebToolBar.HORIZONTAL);
        toolBar2.setFloatable(false);
        toolBar2.addSpacing();
        toolBar2.add(new WebLabel("Curtain transition example"));
        toolBar2.addSpacing();
        componentTransition.setContent(toolBar2);

        // Initial transition panel state
        componentTransition.setContent(toolBar1);
        componentTransition.setPreferredSize(SwingUtils.max(toolBar1, toolBar2));

        // States switch button
        final WebButton switchView = new WebButton(Utils.getImage(Utils.Image.frame));
        switchView.setDrawFocus(false);
        switchView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (componentTransition.getContent() == toolBar1) {
                    effect.setDirection(Direction.right);
                    componentTransition.performTransition(toolBar2);
                } else {
                    effect.setDirection(Direction.left);
                    componentTransition.performTransition(toolBar1);
                }
            }
        });
        componentTransition.addTransitionListener(new TransitionListener() {
            @Override
            public void transitionStarted() {
                switchView.setEnabled(false);
            }

            @Override
            public void transitionFinished() {
                switchView.setEnabled(true);
            }
        });

        GroupPanel g = new GroupPanel(10, componentTransition, new CenterPanel(switchView));

        g.setBounds(0, 0, 1000, 30);

        this.add(g);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 446, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                WebLookAndFeel.setDecorateAllWindows(true);
                WebLookAndFeel.setDecorateDialogs(true);
                WebLookAndFeel.setDecorateFrames(true);

                LanguageManager.setDefaultLanguage(LanguageManager.PORTUGUESE);

                WebLookAndFeel.install();
                new teste().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
