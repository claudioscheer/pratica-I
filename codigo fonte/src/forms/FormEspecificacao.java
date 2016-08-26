package forms;

import com.alee.laf.desktoppane.WebInternalFrame;
import java.awt.Dimension;

public class FormEspecificacao extends WebInternalFrame {

    public FormEspecificacao() {
        super("Especificação", true, true, true, true);
        initComponents();
    }

    private void initFormCad() {
        panelCadCadastro = new components.panelsCads.PanelCadEspecificacao();
        panelCadCadastro.init();

        panelCadCadastro.setEvents((e) -> {
            this.salvarEspecificacao();
        }, (e) -> {
            this.cancelarEspecificacao();
        });
    }

    private void initComponents() {
        this.setMinimumSize(new Dimension(565, 496));
        this.setPreferredSize(new Dimension(565, 496));

        panelCadastro = new components.panelsListagem.PanelEspecificacao();

        panelCadastro.setEvents((e) -> {
            this.addEspecificacao();
        }, (e) -> {
            this.editEspecificacao();
        }, (e) -> {
            this.deleteEspecificacao();
        });

        this.add(panelCadastro);
    }

    private void salvarEspecificacao() {
        this.cancelarEspecificacao();
    }

    private void cancelarEspecificacao() {
        this.remove(panelCadCadastro);
        this.add(panelCadastro);

        panelCadastro.revalidate();
        panelCadastro.repaint();
    }

    private void addEspecificacao() {
        this.initFormCad();

        this.remove(panelCadastro);
        this.add(panelCadCadastro);

        panelCadCadastro.revalidate();
        panelCadCadastro.repaint();
    }

    private void editEspecificacao() {
        System.out.println("editEspecificacao");
    }

    private void deleteEspecificacao() {
        System.out.println("deleteEspecificacao");
    }

    private components.panelsListagem.PanelEspecificacao panelCadastro;
    private components.panelsCads.PanelCadEspecificacao panelCadCadastro;

}
