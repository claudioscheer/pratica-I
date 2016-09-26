package forms;

import com.alee.laf.desktoppane.WebInternalFrame;
import dao.NotaFiscalDAO;
import utils.Utils;
import java.awt.Dimension;
import model.NotaFiscal;

public class FormNotaFiscal extends WebInternalFrame {

    public FormNotaFiscal() {
        super("Notas Fiscais", true, true, true, true);
        initComponents();
    }

    private void initFormCad() {
        this.panelCadCadastro = new components.panelsCads.PanelCadNotaFiscal();
        this.panelCadCadastro.init();

        this.panelCadCadastro.setEvents((e) -> {
            this.salvarEspecificacao();
        }, (e) -> {
            this.cancelarEspecificacao();
        });
    }

    private void initComponents() {
        this.setBounds(10, 10, Utils.wPadrao, Utils.hPadrao);
        this.setMinimumSize(new Dimension(Utils.wPadrao, Utils.hPadrao));
        this.setBackground(Utils.CoresPadrao.fundoPadrao);

        this.setFrameIcon(Utils.getImage(Utils.Image.frame));

        this.panelCadastro = new components.panelsListagem.PanelEspecificacao();

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

        NotaFiscalDAO especificacaoDAO = new NotaFiscalDAO();
        NotaFiscal especificacao = panelCadCadastro.getEspecificacao();
//        if (especificacaoDAO.insert(especificacao)) {
//            this.fecharAbrirPanelCadastro(true);
//        } else {
//            JOptionPane.showMessageDialog(null, "Erro ao inserir");
//        }
    }

    private void fecharAbrirPanelCadastro(Boolean fechar) {
        if (fechar) {
            this.remove(this.panelCadCadastro);
            this.add(this.panelCadastro);
        } else {
            this.remove(this.panelCadastro);
            this.add(this.panelCadCadastro);
        }

        this.panelCadastro.revalidate();
    }

    private void cancelarEspecificacao() {

        Utils.notificacao("Blzasdfsaldk fjsd sjdfg kd!as", Utils.TipoNotificacao.erro, 2000);

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
    private components.panelsCads.PanelCadNotaFiscal panelCadCadastro;

}
