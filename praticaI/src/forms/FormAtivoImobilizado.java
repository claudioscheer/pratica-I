package forms;

import com.alee.laf.desktoppane.WebInternalFrame;
import com.alee.laf.optionpane.WebOptionPane;
import components.panelsCads.PanelCadAtivoImobilizado;
import components.panelsListagem.PanelConsultaAtivoImobilizado;
import dao.AtivoImobilizadoDAO;
import utils.Utils;
import java.awt.Dimension;
import model.AtivoImobilizado;

public class FormAtivoImobilizado extends WebInternalFrame {

    public int indexEditando;

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
            this.addAtivoImobilizado();
        }, (e) -> {
            this.editarAtivoImobilizado();
        }, (e) -> {
            this.excluirAtivoImobilizado();
        });

        this.add(this.panelConsultaAtivoImobilizado);
    }

    //inicia o form de cadastro
    private void initFormCad() {
        this.panelCadastroAtivoImobilizado = new PanelCadAtivoImobilizado();
        this.panelCadastroAtivoImobilizado.init();

        //seta os eventos para o cancelar e o cadastrar da nota fiscal
        this.panelCadastroAtivoImobilizado.setEvents((e) -> {
            this.salvarAtivoImobilizado();
        }, (e) -> {
            this.cancelarCadAtivoImobilizado();
        });
    }

    //evento para salvar o ativo imobilizado
    private void salvarAtivoImobilizado() {

        if (!this.panelCadastroAtivoImobilizado.validador.isValid()) {
            return;
        }

        AtivoImobilizado ativoImobilizado = this.panelCadastroAtivoImobilizado.getAtivoImobilizado();
        if (!this.panelCadastroAtivoImobilizado.editando) {
            new AtivoImobilizadoDAO().insert(ativoImobilizado);
        } else {
            new AtivoImobilizadoDAO().update(ativoImobilizado);
            this.panelCadastroAtivoImobilizado.editando = false;
            this.panelConsultaAtivoImobilizado.removeAtivoImobilizado(this.indexEditando);
            this.indexEditando = -1;

        }

        this.panelConsultaAtivoImobilizado.addAtivoImobilizado(ativoImobilizado);

        Utils.notificacao("Ativo imobilizado salvo!", Utils.TipoNotificacao.ok, 0);
        this.fecharAbrirPanelCadastro(true);
    }

    //toggle o form de cadastro de nota fiscal
    private void fecharAbrirPanelCadastro(boolean fechar) {
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
    private void cancelarCadAtivoImobilizado() {
        this.fecharAbrirPanelCadastro(true);
    }

    //evento para adicionar uma nota fiscal
    private void addAtivoImobilizado() {
        this.initFormCad();
        this.fecharAbrirPanelCadastro(false);

        this.panelCadastroAtivoImobilizado.revalidate();
    }

    //evento para editar uma nota fiscal
    private void editarAtivoImobilizado() {

        AtivoImobilizado ativoImobilizado = this.panelConsultaAtivoImobilizado.getAtivoImobilizadoSelecionado();

        if (ativoImobilizado == null) {
            return;
        }

        this.indexEditando = this.panelConsultaAtivoImobilizado.getIndiceSelecionado();

        this.initFormCad();
        this.fecharAbrirPanelCadastro(false);

        this.panelCadastroAtivoImobilizado.setDadosEditar(ativoImobilizado);
        this.panelCadastroAtivoImobilizado.revalidate();
    }

    //evento para deletar uma nota fiscal
    private void excluirAtivoImobilizado() {
        if (WebOptionPane.showConfirmDialog(this.panelConsultaAtivoImobilizado, "Deseja deletar o ativo imobilizado?", "Excluir",
                WebOptionPane.YES_NO_OPTION,
                WebOptionPane.QUESTION_MESSAGE) == WebOptionPane.OK_OPTION) {

            AtivoImobilizado ativoImobilizado = this.panelConsultaAtivoImobilizado.getAtivoImobilizadoSelecionado();

            if (ativoImobilizado == null) {
                return;
            }

            int index = this.panelConsultaAtivoImobilizado.getIndiceSelecionado();

            new AtivoImobilizadoDAO().delete(ativoImobilizado);
            this.panelConsultaAtivoImobilizado.removeAtivoImobilizado(index);
            Utils.notificacao("Ativo imobilizado removido!", Utils.TipoNotificacao.ok, 0);
        }

    }

    private PanelConsultaAtivoImobilizado panelConsultaAtivoImobilizado;
    private PanelCadAtivoImobilizado panelCadastroAtivoImobilizado;

}
