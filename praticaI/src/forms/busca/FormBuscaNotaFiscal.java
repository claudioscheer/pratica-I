package forms.busca;

import components.JFrameBusca;
import components.TextFieldFK;
import components.panelsCads.PanelCadNotaFiscal;
import components.panelsListagem.PanelConsultaNotaFiscal;
import dao.PatAtivoImobilizadoDAO;
import dao.PatNotaFiscalDAO;
import java.awt.Dimension;
import utils.Utils;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.PatNotaFiscal;

public class FormBuscaNotaFiscal extends JFrameBusca {

    public FormBuscaNotaFiscal() {
        this.initComponents();
    }

    //carrega os componentes do form
    private void initComponents() {
        this.setBounds(10, 10, Utils.wPadrao, Utils.hPadrao);
        this.setMinimumSize(new Dimension(Utils.wPadrao, Utils.hPadrao));
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.addWindowListener(new java.awt.event.WindowAdapter() {

            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                getFrameBloquear().setEnabled(true);
            }
        });

        this.panelConsultaNotaFiscal = new PanelConsultaNotaFiscal();

        this.panelConsultaNotaFiscal.setEventsBuscar((e) -> {
            this.selecionarNotaFiscal();
        }, (e) -> {
            this.addNotaFiscal();
        });

        this.panelConsultaNotaFiscal.setDoubleClickTabela(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    selecionarNotaFiscal();
                }
            }
        });

        this.add(this.panelConsultaNotaFiscal);
    }

    //inicia o form de cadastro
    private void initFormCad() {
        this.panelCadastroNotaFiscal = new PanelCadNotaFiscal(this);
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

        new PatNotaFiscalDAO().inserir(notaFiscal);

        this.panelCadastroNotaFiscal.getAtivos().forEach((key, value) -> {
            new PatAtivoImobilizadoDAO().inserir(value);
        });
        
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

    private void cancelarCadNotaFiscal() {
        this.fecharAbrirPanelCadastro(true);
    }

    private void addNotaFiscal() {
        this.initFormCad();
        this.fecharAbrirPanelCadastro(false);

        this.panelCadastroNotaFiscal.revalidate();
    }

    private void selecionarNotaFiscal() {

        PatNotaFiscal notaFiscal = this.panelConsultaNotaFiscal.getNotaFiscalSelecionada();

        if (notaFiscal == null) {
            return;
        }

        if (this.getFrameBuscaTipo() == JFrameBuscaTipo.textFieldFK) {
            TextFieldFK text = this.getTextFieldFK();
            text.setText(notaFiscal.getNotaCodigo() + " - " + notaFiscal.getNotaChaveAcesso());
            text.setValue(notaFiscal);

            if (text.getFuncaoDepoisSelecionar() != null) {
                text.getFuncaoDepoisSelecionar().accept(notaFiscal);
            }
        }

        this.getFrameBloquear().setEnabled(true);
        this.dispose();
    }

    private PanelConsultaNotaFiscal panelConsultaNotaFiscal;
    private PanelCadNotaFiscal panelCadastroNotaFiscal;

}
