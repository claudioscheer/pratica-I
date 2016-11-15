package forms.patrimonio;

import com.alee.laf.desktoppane.WebInternalFrame;
import components.panelsCads.PanelCadAtivoImobilizado;
import components.panelsListagem.PanelConsultaAtivoImobilizado;
import dao.PatAtivoImobilizadoDAO;
import java.awt.Dimension;
import model.PatAtivoImobilizado;
import utils.Utils;

public class FormAtivoImobilizado extends WebInternalFrame {

    public int indexEditando;

    public FormAtivoImobilizado() {
        super("Ativos Imobilizados", true, true, true, true);
        this.initComponents();
    }

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
        });

        this.add(this.panelConsultaAtivoImobilizado);
    }

    private void initFormCad() {
        this.panelCadastroAtivoImobilizado = new PanelCadAtivoImobilizado();
        this.panelCadastroAtivoImobilizado.init();

        this.panelCadastroAtivoImobilizado.setEvents((e) -> {
            this.salvarAtivoImobilizado();
        }, (e) -> {
            this.cancelarCadAtivoImobilizado();
        });
    }

    private void salvarAtivoImobilizado() {

        if (!this.panelCadastroAtivoImobilizado.validador.isValid()) {
            return;
        }

        PatAtivoImobilizado ativoImobilizado = this.panelCadastroAtivoImobilizado.getAtivoImobilizado();
        if (!this.panelCadastroAtivoImobilizado.editando) {
            new PatAtivoImobilizadoDAO().insert(ativoImobilizado);
        } else {
            new PatAtivoImobilizadoDAO().update(ativoImobilizado);
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

    private void cancelarCadAtivoImobilizado() {
        this.fecharAbrirPanelCadastro(true);
    }

    private void addAtivoImobilizado() {
        this.initFormCad();
        this.fecharAbrirPanelCadastro(false);

        this.panelCadastroAtivoImobilizado.revalidate();
    }

    private void editarAtivoImobilizado() {

        PatAtivoImobilizado ativoImobilizado = this.panelConsultaAtivoImobilizado.getAtivoImobilizadoSelecionado();

        if (ativoImobilizado == null) {
            return;
        }

        this.indexEditando = this.panelConsultaAtivoImobilizado.getIndiceSelecionado();

        this.initFormCad();
        this.fecharAbrirPanelCadastro(false);

        this.panelCadastroAtivoImobilizado.setDadosEditar(ativoImobilizado);
        this.panelCadastroAtivoImobilizado.revalidate();
    }

//    private void excluirAtivoImobilizado() {
//        PatAtivoImobilizado ativoImobilizado = this.panelConsultaAtivoImobilizado.getAtivoImobilizadoSelecionado();
//
//        if (ativoImobilizado == null) {
//            return;
//        }
//
//        if (WebOptionPane.showConfirmDialog(this.panelConsultaAtivoImobilizado, "Deseja dar baixa no ativo imobilizado?", "Excluir",
//                WebOptionPane.YES_NO_OPTION,
//                WebOptionPane.QUESTION_MESSAGE) == WebOptionPane.OK_OPTION) {
//
//            int index = this.panelConsultaAtivoImobilizado.getIndiceSelecionado();
//
//            new PatAtivoImobilizadoDAO().delete(ativoImobilizado);
//            this.panelConsultaAtivoImobilizado.removeAtivoImobilizado(index);
//            Utils.notificacao("Ativo imobilizado removido!", Utils.TipoNotificacao.ok, 0);
//            
//        }
//
//    }

    private PanelConsultaAtivoImobilizado panelConsultaAtivoImobilizado;
    private PanelCadAtivoImobilizado panelCadastroAtivoImobilizado;

}
