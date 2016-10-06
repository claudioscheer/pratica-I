package forms;

import com.alee.laf.desktoppane.WebInternalFrame;
import components.panelsCads.PanelCadAtivoImobilizado;
import components.panelsListagem.PanelConsultaAtivoImobilizado;
import dao.NotaFiscalDAO;
import utils.Utils;
import java.awt.Dimension;
import model.NotaFiscal;

public class FormAtivoImobilizado extends WebInternalFrame {

    public FormAtivoImobilizado() {
        super("Ativos Imobilizados", true, true, true, true);
        this.initComponents();
    }

    //carrega os componentes do form
    private void initComponents() {
        this.setBounds(10, 10, Utils.wPadrao, Utils.hPadrao);
        this.setMinimumSize(new Dimension(Utils.wPadrao, Utils.hPadrao));
        this.setBackground(Utils.CoresPadrao.fundoPadrao);

        this.setFrameIcon(Utils.getImage(Utils.Image.frame));

        this.panelConsultaAtivoImobilizado = new PanelConsultaAtivoImobilizado();

        this.panelConsultaAtivoImobilizado.setEvents((e) -> {
            this.addNotaFiscal();
        }, (e) -> {
            this.editNotaFiscal();
        }, (e) -> {
            this.deleteNotaFiscal();
        });

        this.add(this.panelConsultaAtivoImobilizado);
    }

    //inicia o form de cadastro
    private void initFormCad() {
        this.panelCadastroAtivoImobilizado = new PanelCadAtivoImobilizado();
        this.panelCadastroAtivoImobilizado.init();

        //seta os eventos para o cancelar e o cadastrar da nota fiscal
        this.panelCadastroAtivoImobilizado.setEvents((e) -> {
            this.salvarNotaFiscal();
        }, (e) -> {
            this.cancelarCadNotaFiscal();
        });
    }

    //evento para salvar a nota fiscal
    private void salvarNotaFiscal() {

        if (!this.panelCadastroAtivoImobilizado.validador.isValid()) {
            return;
        }

        NotaFiscalDAO especificacaoDAO = new NotaFiscalDAO();
        NotaFiscal nota = this.panelCadastroAtivoImobilizado.getNotaFiscal();
    }

    //toggle o form de cadastro de nota fiscal
    private void fecharAbrirPanelCadastro(Boolean fechar) {
        if (fechar) {
            this.remove(this.panelCadastroAtivoImobilizado);
            this.add(this.panelConsultaAtivoImobilizado);
        } else {
            this.remove(this.panelConsultaAtivoImobilizado);
            this.add(this.panelCadastroAtivoImobilizado);
        }

        this.panelConsultaAtivoImobilizado.revalidate();
        this.panelConsultaAtivoImobilizado.repaint();
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

        this.panelCadastroAtivoImobilizado.revalidate();
    }

    //evento para editar uma nota fiscal
    private void editNotaFiscal() {
        System.out.println("editNotaFiscal");
    }

    //evento para deletar uma nota fiscal
    private void deleteNotaFiscal() {
        System.out.println("deleteNotaFiscal");
    }

    private PanelConsultaAtivoImobilizado panelConsultaAtivoImobilizado;
    private PanelCadAtivoImobilizado panelCadastroAtivoImobilizado;

}
