package forms.patrimonio;

import com.alee.laf.desktoppane.WebInternalFrame;
import com.alee.laf.optionpane.WebOptionPane;
import components.panelsCads.PanelCadNotaFiscal;
import components.panelsListagem.PanelConsultaNotaFiscal;
import dao.EstMovimentacaoDAO;
import dao.PatAtivoImobilizadoDAO;
import dao.PatNotaFiscalDAO;
import forms.FormPrincipal;
import utils.Utils;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.PatNotaFiscal;

public class FormNotaFiscal extends WebInternalFrame {

    public int indexEditando;

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

        this.panelConsultaNotaFiscal.setDoubleClickTabela(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    editNotaFiscal();
                }
            }
        });

        this.add(this.panelConsultaNotaFiscal);
    }

    //inicia o form de cadastro
    private void initFormCad() {
        this.panelCadastroNotaFiscal = new PanelCadNotaFiscal(FormPrincipal.getInstance());
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

        PatNotaFiscal notaFiscal = this.panelCadastroNotaFiscal.getNotaFiscal();

        if (!this.panelCadastroNotaFiscal.editando) {
            new PatNotaFiscalDAO().inserir(notaFiscal);

            this.panelCadastroNotaFiscal.getAtivos().forEach((key, value) -> {
                new PatAtivoImobilizadoDAO().inserir(value);
            });

        } else {
            new PatNotaFiscalDAO().alterar(notaFiscal);
            this.panelCadastroNotaFiscal.editando = false;
            this.panelConsultaNotaFiscal.removeNotaFiscal(this.indexEditando);
            this.indexEditando = -1;
        }

        try {
            new EstMovimentacaoDAO().itensNota(notaFiscal.getPatItemNotas(), notaFiscal.getNotaDataEntrada());
        } catch (Exception e) {
            utils.Utils.notificacao("Erro ao atualizar itens da nota!", Utils.TipoNotificacao.erro, 0);
        }

        this.panelConsultaNotaFiscal.addNotaFiscal(notaFiscal);

        Utils.notificacao("Nota fiscal salva!", Utils.TipoNotificacao.ok, 0);
        this.fecharAbrirPanelCadastro(true);
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

        PatNotaFiscal notaFiscal = this.panelConsultaNotaFiscal.getNotaFiscalSelecionada();

        if (notaFiscal == null) {
            return;
        }

        this.indexEditando = this.panelConsultaNotaFiscal.getIndiceSelecionado();

        this.initFormCad();
        this.fecharAbrirPanelCadastro(false);

        this.panelCadastroNotaFiscal.setDadosEditar(notaFiscal);
        this.panelCadastroNotaFiscal.revalidate();
    }

    private void deleteNotaFiscal() {
        PatNotaFiscal notaFiscal = this.panelConsultaNotaFiscal.getNotaFiscalSelecionada();
        if (notaFiscal == null) {
            return;
        }

        if (WebOptionPane.showConfirmDialog(this.panelConsultaNotaFiscal, "Deseja deletar a nota fiscal?", "Excluir",
                WebOptionPane.YES_NO_OPTION,
                WebOptionPane.QUESTION_MESSAGE) == WebOptionPane.OK_OPTION) {

            int index = this.panelConsultaNotaFiscal.getIndiceSelecionado();

            if (new PatNotaFiscalDAO().excluir(notaFiscal)) {
                this.panelConsultaNotaFiscal.removeNotaFiscal(index);
                Utils.notificacao("Nota fiscal removida!", Utils.TipoNotificacao.ok, 0);
            } else {
                Utils.notificacao("Nota fiscal já está sendo usada!", Utils.TipoNotificacao.erro, 0);
            }

        }
    }

    private PanelConsultaNotaFiscal panelConsultaNotaFiscal;
    private PanelCadNotaFiscal panelCadastroNotaFiscal;

}
