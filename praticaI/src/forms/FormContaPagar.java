package forms;

import com.alee.laf.desktoppane.WebInternalFrame;
import components.panelsCads.PanelCadContaPagar;
import components.panelsCads.PanelCadNotaFiscal;
import components.panelsListagem.PanelConsultaContaPagar;
import components.panelsListagem.PanelConsultaNotaFiscal;
import dao.PatAtivoImobilizadoDAO;
import utils.Utils;
import java.awt.Dimension;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FormContaPagar extends WebInternalFrame {

    public FormContaPagar() {
        super("Contas à pagar", true, true, true, true);
        this.initComponents();
    }

    //carrega os componentes do form
    private void initComponents() {
        this.setBounds(10, 10, Utils.wPadrao, Utils.hPadrao);
        this.setMinimumSize(new Dimension(Utils.wPadrao, Utils.hPadrao));
        this.setBackground(Utils.CoresPadrao.fundoPadrao);

        this.setFrameIcon(Utils.getImage(Utils.Image.frame));

        this.panelConsultaContaPagar = new PanelConsultaContaPagar();

        this.panelConsultaContaPagar.setEvents((e) -> {
            this.addNotaFiscal();
        }, (e) -> {
            this.editNotaFiscal();
        }, (e) -> {
            this.deleteNotaFiscal();
        });

        this.add(this.panelConsultaContaPagar);
    }

    //inicia o form de cadastro
    private void initFormCad() {
        this.panelCadContaPagar = new PanelCadContaPagar();
        this.panelCadContaPagar.init();

        //seta os eventos para o cancelar e o cadastrar da nota fiscal
        this.panelCadContaPagar.setEvents((e) -> {
            this.salvarNotaFiscal();
        }, (e) -> {
            this.cancelarCadNotaFiscal();
        });
    }

    //evento para salvar a nota fiscal
    private void salvarNotaFiscal() {

        if (!this.panelCadContaPagar.validador.isValid()) {
            return;
        }

        PatAtivoImobilizadoDAO especificacaoDAO = new PatAtivoImobilizadoDAO();
//        NotaFiscalDAO nota = this.panelCadContaPagar.getNotaFiscal();
    }

    //toggle o form de cadastro de nota fiscal
    private void fecharAbrirPanelCadastro(Boolean fechar) {
        if (fechar) {
            this.remove(this.panelCadContaPagar);
            this.add(this.panelConsultaContaPagar);
        } else {
            this.remove(this.panelConsultaContaPagar);
            this.add(this.panelCadContaPagar);
        }

        this.panelConsultaContaPagar.revalidate();
        this.panelConsultaContaPagar.repaint();
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

        this.panelCadContaPagar.revalidate();
    }

    //evento para editar uma nota fiscal
    private void editNotaFiscal() {
        System.out.println("editNotaFiscal");
    }

    //evento para deletar uma nota fiscal
    private void deleteNotaFiscal() {
        System.out.println("deleteNotaFiscal");
    }

    private PanelConsultaContaPagar panelConsultaContaPagar;
    private PanelCadContaPagar panelCadContaPagar;

}
