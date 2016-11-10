package forms;

import com.alee.laf.desktoppane.WebInternalFrame;
import com.alee.laf.optionpane.WebOptionPane;
import components.panelsCads.PanelCadPessoa;
import components.panelsListagem.PanelConsultaPessoa;
import dao.PessoaDAO;
import forms.busca.FormBuscaPessoa;
import utils.Utils;
import java.awt.Dimension;
import model.CarPessoa;

public class FormPessoa extends WebInternalFrame {

    private int indexEditando;
    
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
        }, (e) -> {
            this.telaRelatorio();
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
            CarPessoa pssoa = this.panelCadastroPessoa.getPessoa();
        if (!this.panelCadastroPessoa.editando) {
            new PessoaDAO().insert(pssoa);
        } else {
            new PessoaDAO().update(pssoa);
            this.panelCadastroPessoa.editando = false;
            this.panelConsultaPessoa.removePessoa(this.indexEditando);
            this.indexEditando = -1;
        }
        
        this.panelConsultaPessoa.addPessoas(pssoa);

        Utils.notificacao("Pessoa salva!", Utils.TipoNotificacao.ok, 0);
         this.fecharAbrirPanelCadastro(true);
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
    
    // evento para abrir tela de gerar relatórios
private void telaRelatorio() {
        this.formBuscaPessoa = new FormBuscaPessoa();
        this.formBuscaPessoa.setVisible(true);
        System.out.println("abre tela");
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
        CarPessoa pessoa = this.panelConsultaPessoa.getPessoaSelecionada();
        if (pessoa == null) {
            return;
        }
        this.indexEditando = this.panelConsultaPessoa.getIndiceSelecionado();
        this.initFormCad();
        this.fecharAbrirPanelCadastro(false);
        this.panelCadastroPessoa.setDadosEditar(pessoa);
        this.panelCadastroPessoa.revalidate();
    }
    
    //evento para deletar uma pessoa
    private void deletePessoa() {
        CarPessoa pessoa = this.panelConsultaPessoa.getPessoaSelecionada();
        if (pessoa == null) {
            return;
        }
        if (WebOptionPane.showConfirmDialog(this.panelConsultaPessoa, "Deseja excluir a pessoa selecionada?", "Excluir",
                WebOptionPane.YES_NO_OPTION,
                WebOptionPane.QUESTION_MESSAGE) == WebOptionPane.OK_OPTION) {
            int index = this.panelConsultaPessoa.getIndiceSelecionado();
            new PessoaDAO().delete(pessoa);
            this.panelConsultaPessoa.removePessoa(index);
            Utils.notificacao("Pessoa removida!", Utils.TipoNotificacao.ok, 0);
        }
        System.out.println("deletePessoa"); 
    }

    private PanelConsultaPessoa panelConsultaPessoa;
    private PanelCadPessoa panelCadastroPessoa;
    private FormBuscaPessoa formBuscaPessoa;
    
            
}