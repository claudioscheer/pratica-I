package forms.patrimonio;

import com.alee.laf.desktoppane.WebInternalFrame;
import components.panelsCads.PanelCadNotaFiscal;
import components.panelsListagem.PanelConsultaNotaFiscal;
import dao.AtivoImobilizadoDAO;
import utils.Utils;
import java.awt.Dimension;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.NotaFiscal;

public class FormNotaFiscal extends WebInternalFrame {

    public FormNotaFiscal() {
        super("Notas Fiscais", true, true, true, true);
        this.initComponents();
    }

    //carrega os componentes do form
    private void initComponents() {
        this.setBounds(10, 10, Utils.wPadrao, Utils.hPadrao);
        this.setMinimumSize(new Dimension(Utils.wPadrao, Utils.hPadrao));
        this.setBackground(Utils.CoresPadrao.fundoPadrao);

        this.setFrameIcon(Utils.getImage(Utils.Image.frame));

        this.panelConsultaNotaFiscal = new PanelConsultaNotaFiscal();

        this.panelConsultaNotaFiscal.setEvents((e) -> {
            this.addNotaFiscal();
        }, (e) -> {
            this.editNotaFiscal();
        }, (e) -> {
            this.deleteNotaFiscal();
        });

        this.add(this.panelConsultaNotaFiscal);
    }

    //inicia o form de cadastro
    private void initFormCad() {
        this.panelCadastroNotaFiscal = new PanelCadNotaFiscal();
        this.panelCadastroNotaFiscal.init();

        //seta os eventos para o cancelar e o cadastrar da nota fiscal
        this.panelCadastroNotaFiscal.setEvents((e) -> {
            this.salvarNotaFiscal();
        }, (e) -> {
            this.cancelarCadNotaFiscal();
        });
    }

    //evento para salvar a nota fiscal
    private void salvarNotaFiscal() {

        if (!this.panelCadastroNotaFiscal.validador.isValid()) {
            return;
        }

        AtivoImobilizadoDAO especificacaoDAO = new AtivoImobilizadoDAO();
        NotaFiscal nota = this.panelCadastroNotaFiscal.getNotaFiscal();
    }

    //toggle o form de cadastro de nota fiscal
    private void fecharAbrirPanelCadastro(Boolean fechar) {
        if (fechar) {
            this.remove(this.panelCadastroNotaFiscal);
            this.add(this.panelConsultaNotaFiscal);
        } else {
            this.remove(this.panelConsultaNotaFiscal);
            this.add(this.panelCadastroNotaFiscal);
        }

        this.panelConsultaNotaFiscal.revalidate();
        this.panelConsultaNotaFiscal.repaint();
    }

    //evento para cancelar o cadastro
    private void cancelarCadNotaFiscal() {

        Utils.notificacao("Blzasdfsaldk fjsd sjdfg kd!as", Utils.TipoNotificacao.erro, 2000);

        this.fecharAbrirPanelCadastro(true);
    }

    //evento para adicionar uma nota fiscal
    private void addNotaFiscal() {
        this.initFormCad();
        this.fecharAbrirPanelCadastro(false);

        this.panelCadastroNotaFiscal.revalidate();
    }

    //evento para editar uma nota fiscal
    private void editNotaFiscal() {
        System.out.println("editNotaFiscal");
    }

    //evento para deletar uma nota fiscal
    private void deleteNotaFiscal() {
        System.out.println("deleteNotaFiscal");
    }

    private PanelConsultaNotaFiscal panelConsultaNotaFiscal;
    private PanelCadNotaFiscal panelCadastroNotaFiscal;

}
