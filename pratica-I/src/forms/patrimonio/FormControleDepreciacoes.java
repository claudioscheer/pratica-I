package forms.patrimonio;

import com.alee.laf.optionpane.WebOptionPane;
import components.panelsCads.PanelCadDepreciacoes;
import components.panelsListagem.PanelConsultaDepreciacao;
import dao.PatDepreciacaoDAO;
import forms.FormPrincipal;
import utils.Utils;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import model.PatDepreciacao;

public class FormControleDepreciacoes extends JFrame {

    public int indexEditando;

    public FormControleDepreciacoes() {
        this.initComponents();
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                FormPrincipal.getInstance().setEnabled(true);
            }

        });
    }

    private void initComponents() {
        this.setBounds(10, 10, 878, 421);
        this.setMinimumSize(new Dimension(Utils.wPadrao, Utils.hPadrao));

        this.panelConsultaDepreciacao = new PanelConsultaDepreciacao();

        this.panelConsultaDepreciacao.setEvents((e) -> {
            this.addDepreciacao();
        }, (e) -> {
            this.editarDepreciacao();
        }, (e) -> {
            this.excluirDepreciacao();
        });

        this.add(this.panelConsultaDepreciacao);
    }

    private void initFormCad() {
        this.panelCadastroDepreciacao = new PanelCadDepreciacoes();
        this.panelCadastroDepreciacao.init();

        this.panelCadastroDepreciacao.setEvents((e) -> {
            this.salvarDepreciacao();
        }, (e) -> {
            this.cancelarCadDepreciacao();
        });
    }

    private void salvarDepreciacao() {

        if (!this.panelCadastroDepreciacao.validador.isValid()) {
            return;
        }

        PatDepreciacao depreciacao = this.panelCadastroDepreciacao.getDepreciacao();
        if (!this.panelCadastroDepreciacao.editando) {
            new PatDepreciacaoDAO().inserir(depreciacao);
        } else {
            new PatDepreciacaoDAO().alterar(depreciacao);
            this.panelCadastroDepreciacao.editando = false;
            this.panelConsultaDepreciacao.removeDepreciacao(this.indexEditando);
            this.indexEditando = -1;

        }

        this.panelConsultaDepreciacao.addDepreciacao(depreciacao);

        Utils.notificacao("Depreciação salva!", Utils.TipoNotificacao.ok, 0);
        this.fecharAbrirPanelCadastro(true);
    }

    private void fecharAbrirPanelCadastro(boolean fechar) {
        if (fechar) {
            this.remove(this.panelCadastroDepreciacao);
            this.add(this.panelConsultaDepreciacao);
        } else {
            this.remove(this.panelConsultaDepreciacao);
            this.add(this.panelCadastroDepreciacao);
        }

        this.panelConsultaDepreciacao.revalidate();
        this.panelConsultaDepreciacao.repaint();
    }

    private void cancelarCadDepreciacao() {
        this.fecharAbrirPanelCadastro(true);
    }

    private void addDepreciacao() {
        this.initFormCad();
        this.fecharAbrirPanelCadastro(false);

        this.panelCadastroDepreciacao.revalidate();
    }

    private void editarDepreciacao() {

        PatDepreciacao depreciacao = this.panelConsultaDepreciacao.getDepreciacaoSelecionada();

        if (depreciacao == null) {
            return;
        }

        this.indexEditando = this.panelConsultaDepreciacao.getIndiceSelecionado();

        this.initFormCad();
        this.fecharAbrirPanelCadastro(false);

        this.panelCadastroDepreciacao.setDadosEditar(depreciacao);
        this.panelCadastroDepreciacao.revalidate();
    }

    private void excluirDepreciacao() {
        PatDepreciacao depreciacao = this.panelConsultaDepreciacao.getDepreciacaoSelecionada();

        if (depreciacao == null) {
            return;
        }

        if (WebOptionPane.showConfirmDialog(this.panelConsultaDepreciacao, "Deseja deletar a depreciação?", "Excluir",
                WebOptionPane.YES_NO_OPTION,
                WebOptionPane.QUESTION_MESSAGE) == WebOptionPane.OK_OPTION) {

            int index = this.panelConsultaDepreciacao.getIndiceSelecionado();

            new PatDepreciacaoDAO().excluir(depreciacao);
            this.panelConsultaDepreciacao.removeDepreciacao(index);
            Utils.notificacao("Depreciação removida!", Utils.TipoNotificacao.ok, 0);
        }
    }

    private PanelConsultaDepreciacao panelConsultaDepreciacao;
    private PanelCadDepreciacoes panelCadastroDepreciacao;

}
