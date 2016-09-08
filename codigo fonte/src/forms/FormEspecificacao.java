package forms;

import com.alee.laf.desktoppane.WebInternalFrame;
import dao.EspecificacaoDAO;
import helper.Helper;
import java.awt.Color;
import java.awt.Dimension;
import model.Especificacao;

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
        this.setBounds(10, 10, 565, 496);
        this.setMinimumSize(new Dimension(565, 496));
        this.setBackground(Helper.CoresPadrao.fundoPadrao);

        this.setFrameIcon(Helper.getImage(Helper.Image.frame));

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

        if (!panelCadCadastro.validador.isValid()) {
            return;
        }

        EspecificacaoDAO especificacaoDAO = new EspecificacaoDAO();
        Especificacao especificacao = panelCadCadastro.getEspecificacao();
//        if (especificacaoDAO.insert(especificacao)) {
//            this.fecharAbrirPanelCadastro(true);
//        } else {
//            JOptionPane.showMessageDialog(null, "Erro ao inserir");
//        }
    }

    private void fecharAbrirPanelCadastro(Boolean fechar) {
        if (fechar) {
            this.remove(panelCadCadastro);
            this.add(panelCadastro);
        } else {
            this.remove(panelCadastro);
            this.add(panelCadCadastro);
        }

        panelCadastro.revalidate();
    }

    private void cancelarEspecificacao() {

        Helper.notificacao("Blzasdfsaldk fjsd sjdfg kd!as", Helper.TipoNotificacao.erro, 2000);

        //this.fecharAbrirPanelCadastro(true);
    }

    private void addEspecificacao() {
        this.initFormCad();
        this.fecharAbrirPanelCadastro(false);

        panelCadCadastro.revalidate();
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
