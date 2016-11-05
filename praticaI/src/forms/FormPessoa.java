package forms;

import com.alee.laf.desktoppane.WebInternalFrame;
import components.panelsCads.PanelCadPessoa;
import components.panelsListagem.PanelConsultaPessoa;
import dao.PessoaDAO;
import utils.Utils;
import java.awt.Dimension;

public class FormPessoa extends WebInternalFrame {

    public FormPessoa() {
        super("Pessoas", true, true, true, true);
        this.initComponents();
    }

    //carrega os componentes do form
    private void initComponents() {
        this.setBounds(10, 10, Utils.wPadrao, Utils.hPadrao);
        this.setMinimumSize(new Dimension(Utils.wPadrao, Utils.hPadrao));
        this.setBackground(Utils.CoresPadrao.fundoPadrao);

        this.setFrameIcon(Utils.getImage(Utils.Image.frame));

        this.panelConsultaPessoa = new PanelConsultaPessoa();

        this.panelConsultaPessoa.setEvents((e) -> {
            this.addPessoa();
        }, (e) -> {
            this.editPessoa();
        }, (e) -> {
            this.deletePessoa();
        });

        this.add(this.panelConsultaPessoa);
    }

    //inicia o form de cadastro
    private void initFormCad() {
        this.panelCadastroPessoa = new PanelCadPessoa();
        this.panelCadastroPessoa.init();

        //seta os eventos para cancelar e cadastrar pessoa
        this.panelCadastroPessoa.setEvents((e) -> {
            this.salvarPessoa();
        }, (e) -> {
            this.cancelarCadPessoa();
        });
    }

    //evento para salvar a PESSOA
    private void salvarPessoa() {

        if (!this.panelCadastroPessoa.validador.isValid()) {
            return;
        }

        PessoaDAO especificacaoDAO = new PessoaDAO();
        // Pessoa p = this.panelCadastroPessoa.getPessoa();
    }

    //toggle o form de cadastro de pessoa
    private void fecharAbrirPanelCadastro(Boolean fechar) {
        if (fechar) {
            this.remove(this.panelCadastroPessoa);
            this.add(this.panelConsultaPessoa);
        } else {
            this.remove(this.panelConsultaPessoa);
            this.add(this.panelCadastroPessoa);
        }

        this.panelConsultaPessoa.revalidate();
        this.panelConsultaPessoa.repaint();
    }

    //evento para cancelar o cadastro
    private void cancelarCadPessoa() {

        Utils.notificacao("Cadastro cancelado", Utils.TipoNotificacao.erro, 2000);

        this.fecharAbrirPanelCadastro(true);
    }

    //evento para adicionar uma pessoa
    private void addPessoa() {
        this.initFormCad();
        this.fecharAbrirPanelCadastro(false);

        this.panelCadastroPessoa.revalidate();
    }

    //evento para editar uma pessoa
    private void editPessoa() {
        System.out.println("editPessoa");
    }

    //evento para deletar uma pessoa
    private void deletePessoa() {
        System.out.println("deletePessoa");
    }

    private PanelConsultaPessoa panelConsultaPessoa;
    private PanelCadPessoa panelCadastroPessoa;

}