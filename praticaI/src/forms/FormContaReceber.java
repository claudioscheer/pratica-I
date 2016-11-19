
package forms;

import com.alee.laf.WebLookAndFeel;
import com.alee.laf.desktoppane.WebInternalFrame;
import com.alee.managers.language.LanguageManager;
import dao.CarCapContasDAO;
import dao.FlxcxOperacoesDAO;
import enumeraveis.TipoConta;
import forms.busca.FormBuscaNotaFiscal;
import dao.EstProdutoDAO;
import dao.carcapOperacoesComerciaisDAO;
import dao.CarPessoaDAO;
import dao.PatNotaFiscalDAO;
import enumeraveis.FormaPagamento;
import enumeraveis.MeioRecebimentoPagamento;
import enumeraveis.StatusConta;
import forms.busca.FormBuscaPessoa;
import forms.busca.FormBuscaProduto;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.CarCapContas;
import model.CarcapOperacoesComerciais;
import model.EstProduto;
import model.FlxcxOperacoes;
import model.PatNotaFiscal;
import utils.Utils;

/**
 *
 * @author Marcos
 */
public class FormContaReceber extends WebInternalFrame {

    /**
     * Creates new form FormMovimento
     */
    public FormContaReceber() {

        super("Contas e Lançamentos", true, true, true, true);
        this.initComponents();
//        new LoadBackground().execute();
        carregarTudo();
    }
    
    public void iniciarCPagar() {
        combo_tip_lancamento.setSelectedIndex(1);
    }

    public int RetornarLinhaSelecionada(int posicao) {
        int valor = Integer.parseInt(txt_tabela.getValueAt(txt_tabela.getSelectedRow(), posicao).toString());
        return valor;
    }

    public boolean pagarConta(int id) {
        carcapOperacoesComerciaisDAO pagar = new carcapOperacoesComerciaisDAO();
        CarcapOperacoesComerciais conta = new CarcapOperacoesComerciais();
        CarCapContas parcelas = new CarCapContas();
        conta.setOperacoesID(id);
        return false;

    }

    private void carregarTudo() {

        FormBuscaProduto buscaProduto = new FormBuscaProduto();
        buscaProduto.setFrameBloquear(FormPrincipal.getInstance());
        txt_busca_produto.setFrame(buscaProduto);
        FormBuscaPessoa buscaFornecedortwoo = new FormBuscaPessoa();
        buscaFornecedortwoo.setFrameBloquear(FormPrincipal.getInstance());
        txt_pess_cad.setFrame(buscaFornecedortwoo);
        txt_busca_cliente_fornecedor.setFrame(buscaFornecedortwoo);
        FormBuscaNotaFiscal buscaNota = new FormBuscaNotaFiscal();
        buscaNota.setFrameBloquear(FormPrincipal.getInstance());
        txt_busca_nota.setFrame(buscaNota);
        Preenche_tabela();
        preenche_Combo();

    }

//        public static Date addMes(Date data, int qtd) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(data);
//		cal.add(Calendar.MONTH, qtd);
//		return cal.getTime();
//	}
//    private class LoadBackground extends SwingWorker<Void, Void> {
//
//        @Override
//        protected Void doInBackground() throws Exception {
//            carregarTudo();
//            return null;
//        }
//
//        @Override
//        protected void done() {
//
//            utils.Utils.notificacao("Valores carregados", Utils.TipoNotificacao.erro, 0);
//
//        }
//
//    }
    //****METODOS DE USO
    public void zeraCampos() {

        txt_busca_cliente_fornecedor.setText(null);
        txt_busca_produto.setText(null);
        txt_busca_nota.setText(null);
        fieldQuantidade.setText(null);
        txt_descricao.setText(null);
        comb_parcelas.setValue(1);
        fieldValorTotal.setText(null);
        fieldValorParcela.setText(null);
        txt_data_lançamento.setDate(null);

    }

    public void preenche_Combo() {

        FlxcxOperacoesDAO Flx = new FlxcxOperacoesDAO();
        List<FlxcxOperacoes> tipo_operacao = Flx.ListarTodas();
        for (FlxcxOperacoes i : tipo_operacao) {
            Comb_tip_operacao.addItem(i.getOpCodigo() + " - " + i.getOpDescricao());
        }
    }

    public void Preenche_tabela() {

        CarCapContasDAO retorn_valores = new CarCapContasDAO();
        List<CarCapContas> conta = retorn_valores.getAll();
        DefaultTableModel tabelamodelo = new DefaultTableModel();
        tabelamodelo.addColumn("ID");
        tabelamodelo.addColumn("Lançamento");
        tabelamodelo.addColumn("Parcelas");
        tabelamodelo.addColumn("D. Vencimento");
        tabelamodelo.addColumn("Produto");
        tabelamodelo.addColumn("Quantidade");
        tabelamodelo.addColumn("Status");
        tabelamodelo.addColumn("V. Parcela");
        tabelamodelo.addColumn("V. Pago");

        for (CarCapContas j : conta) {

            tabelamodelo.addRow(new Object[]{
                j.getContaId(), j.getCarcapOperacoesComerciais().getOperacoesID(),
                j.getContaNumParcelas(), j.getContaDataEmissao(), j.getProduto().getProdutoDescricao(),
                j.getQuantidade_produto(), j.getCapContaStatus(), j.getContaValorPago(), j.getValorRecebido()});

        }

        txt_tabela.setModel(tabelamodelo);

    }

//METODOS DE USO
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        webPanel1 = new com.alee.laf.panel.WebPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_tabela = new com.alee.laf.table.WebTable();
        webLabel1 = new com.alee.laf.label.WebLabel();
        webLabel2 = new com.alee.laf.label.WebLabel();
        webLabel3 = new com.alee.laf.label.WebLabel();
        combo_tip_lancamento = new com.alee.laf.combobox.WebComboBox();
        comb_meio_recebimento = new com.alee.laf.combobox.WebComboBox();
        webDateField1 = new com.alee.extended.date.WebDateField();
        Comb_forma_pagamento_recebimento = new com.alee.laf.combobox.WebComboBox();
        webLabel4 = new com.alee.laf.label.WebLabel();
        webLabel7 = new com.alee.laf.label.WebLabel();
        webLabel8 = new com.alee.laf.label.WebLabel();
        txt_busca_nota = new components.TextFieldFK();
        webLabel6 = new com.alee.laf.label.WebLabel();
        webLabel9 = new com.alee.laf.label.WebLabel();
        webLabel10 = new com.alee.laf.label.WebLabel();
        Comb_tip_operacao = new com.alee.laf.combobox.WebComboBox();
        botao_abrir_relatorios = new com.alee.laf.button.WebButton();
        botao_salvar = new com.alee.laf.button.WebButton();
        webLabel11 = new com.alee.laf.label.WebLabel();
        webLabel12 = new com.alee.laf.label.WebLabel();
        txt_descricao = new javax.swing.JTextField();
        webLabel13 = new com.alee.laf.label.WebLabel();
        webLabel14 = new com.alee.laf.label.WebLabel();
        checkboxEntrada = new com.alee.laf.checkbox.WebCheckBox();
        checkboxEntrada3 = new com.alee.laf.checkbox.WebCheckBox();
        webLabel15 = new com.alee.laf.label.WebLabel();
        txt_data_lançamento = new com.alee.extended.date.WebDateField();
        webLabel16 = new com.alee.laf.label.WebLabel();
        txt_pess_cad = new components.TextFieldFK();
        webLabel17 = new com.alee.laf.label.WebLabel();
        comb_status = new com.alee.laf.combobox.WebComboBox();
        webLabel18 = new com.alee.laf.label.WebLabel();
        webDateField3 = new com.alee.extended.date.WebDateField();
        webLabel19 = new com.alee.laf.label.WebLabel();
        botao_alterar = new com.alee.laf.button.WebButton();
        txt_busca_cliente_fornecedor = new components.TextFieldFK();
        txt_busca_produto = new components.TextFieldFK();
        txt_data_lançamento1 = new com.alee.extended.date.WebDateField();
        webLabel20 = new com.alee.laf.label.WebLabel();
        webLabel21 = new com.alee.laf.label.WebLabel();
        comb_parcelas = new com.alee.laf.spinner.WebSpinner();
        webLabel22 = new com.alee.laf.label.WebLabel();
        checkboxEntrada4 = new com.alee.laf.checkbox.WebCheckBox();
        botao_abrir_relatorios2 = new com.alee.laf.button.WebButton();
        botao_abrir_relatorios1 = new com.alee.laf.button.WebButton();
        fieldValorParcela = new components.TextFieldValorMonetario();
        fieldQuantidade = new components.TextFieldValorMonetario();
        textFieldValorMonetario3 = new components.TextFieldValorMonetario();
        fieldValorUnitario = new components.TextFieldValorMonetario();
        fieldValorTotal = new components.TextFieldValorMonetario();
        fieldValorRecebido = new components.TextFieldValorMonetario();

        webPanel1.setEnabled(false);

        txt_tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "", "", "", "", ""
            }
        ));
        txt_tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_tabelaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(txt_tabela);

        webLabel1.setText("Tipo de Lançamento:");

        webLabel2.setText("Quantidade:");

        webLabel3.setText("Cliente/Fornecedor:");

        combo_tip_lancamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ENTRADA", "SAÍDA" }));
        combo_tip_lancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_tip_lancamentoActionPerformed(evt);
            }
        });

        comb_meio_recebimento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dinheiro", "Cheque", "Via Banco" }));

        webDateField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webDateField1ActionPerformed(evt);
            }
        });

        Comb_forma_pagamento_recebimento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "À Prazo", "À Vista", " ", " " }));
        Comb_forma_pagamento_recebimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comb_forma_pagamento_recebimentoActionPerformed(evt);
            }
        });

        webLabel4.setText("Parcelas:");

        webLabel7.setText("Status:");

        webLabel8.setText("Valor Recebido:");

        webLabel6.setText("Vincular NF:");

        webLabel9.setText("Produto:");

        webLabel10.setText("Tipo de Operação:");

        Comb_tip_operacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comb_tip_operacaoActionPerformed(evt);
            }
        });

        botao_abrir_relatorios.setBackground(new java.awt.Color(51, 255, 51));
        botao_abrir_relatorios.setText("Relatórios");
        botao_abrir_relatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_abrir_relatoriosActionPerformed(evt);
            }
        });

        botao_salvar.setBackground(new java.awt.Color(51, 255, 51));
        botao_salvar.setText("Salvar");
        botao_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_salvarActionPerformed(evt);
            }
        });

        webLabel11.setText("Forma de Pagamento/Recebimento:");

        webLabel12.setText("Descrição:");

        webLabel13.setText("Data Lançamento:");

        webLabel14.setText("Meio de pagamento/Recebimento:");

        checkboxEntrada.setText("Pendente (em ser)");
        checkboxEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxEntradaActionPerformed(evt);
            }
        });

        checkboxEntrada3.setText("Pendente (vencida)");
        checkboxEntrada3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxEntrada3ActionPerformed(evt);
            }
        });

        webLabel15.setText("Valor Total:");

        txt_data_lançamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_data_lançamentoActionPerformed(evt);
            }
        });

        webLabel16.setText("Data Inicial:");

        txt_pess_cad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_pess_cadActionPerformed(evt);
            }
        });

        webLabel17.setText("Cliente/Fornecedor:");

        comb_status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pendente (em ser)", "Pendente (vencida)", "Quitada" }));
        comb_status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comb_statusActionPerformed(evt);
            }
        });

        webLabel18.setText("Status:");

        webDateField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webDateField3ActionPerformed(evt);
            }
        });

        webLabel19.setText("Data Final:");

        botao_alterar.setBackground(new java.awt.Color(51, 255, 51));
        botao_alterar.setText("Alterar");
        botao_alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_alterarActionPerformed(evt);
            }
        });

        txt_data_lançamento1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_data_lançamento1MouseClicked(evt);
            }
        });
        txt_data_lançamento1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_data_lançamento1ActionPerformed(evt);
            }
        });

        webLabel20.setText("Data 1° Vencimento:");

        webLabel21.setText("Valor Unitário:");

        comb_parcelas.setModel(new javax.swing.SpinnerNumberModel(1, 1, 50, 1));
        comb_parcelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comb_parcelasMouseClicked(evt);
            }
        });

        webLabel22.setText("Valor da Parcela:");

        checkboxEntrada4.setText("Quitada");

        botao_abrir_relatorios2.setBackground(new java.awt.Color(51, 255, 51));
        botao_abrir_relatorios2.setText("Filtrar");
        botao_abrir_relatorios2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_abrir_relatorios2ActionPerformed(evt);
            }
        });

        botao_abrir_relatorios1.setBackground(new java.awt.Color(51, 255, 51));
        botao_abrir_relatorios1.setText("Zerar Campos");
        botao_abrir_relatorios1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_abrir_relatorios1ActionPerformed(evt);
            }
        });

        fieldQuantidade.setTextoMonetario(" ");

        javax.swing.GroupLayout webPanel1Layout = new javax.swing.GroupLayout(webPanel1);
        webPanel1.setLayout(webPanel1Layout);
        webPanel1Layout.setHorizontalGroup(
            webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(webPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(webLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_tip_lancamento, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(webLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_busca_cliente_fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_pess_cad, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(webLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(webDateField3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(webLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(webLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(webDateField1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(webPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(webPanel1Layout.createSequentialGroup()
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(webPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(webLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Comb_tip_operacao, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_busca_produto, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(webLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(webPanel1Layout.createSequentialGroup()
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(webLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_data_lançamento1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fieldQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(webLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_busca_nota, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(webLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(webLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(webPanel1Layout.createSequentialGroup()
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Comb_forma_pagamento_recebimento, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(webLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(webLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldValorMonetario3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(webLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comb_parcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(webPanel1Layout.createSequentialGroup()
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(webLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comb_meio_recebimento, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(webLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(webLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldValorParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(webPanel1Layout.createSequentialGroup()
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comb_status, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(webPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(webLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(50, 50, 50)
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(webLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_data_lançamento, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(webPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(webLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(webPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(fieldValorRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(webPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(botao_abrir_relatorios1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(botao_alterar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(botao_abrir_relatorios, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(botao_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(webPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(webLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(webPanel1Layout.createSequentialGroup()
                                .addComponent(checkboxEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(checkboxEntrada3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(checkboxEntrada4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(390, 390, 390)
                        .addComponent(botao_abrir_relatorios2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        webPanel1Layout.setVerticalGroup(
            webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(webPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(webPanel1Layout.createSequentialGroup()
                        .addComponent(webLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(combo_tip_lancamento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(webPanel1Layout.createSequentialGroup()
                        .addComponent(webLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txt_busca_cliente_fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(webLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(webLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(webLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(webPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_pess_cad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(webDateField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(webDateField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(webPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(webPanel1Layout.createSequentialGroup()
                                .addComponent(webLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(Comb_tip_operacao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(webPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(txt_busca_produto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(webLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(webPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(webLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(webPanel1Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(txt_data_lançamento1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(webPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(fieldQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(webLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(webPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(txt_busca_nota, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(webLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(webPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(webLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(webLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(webPanel1Layout.createSequentialGroup()
                                .addComponent(webLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textFieldValorMonetario3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fieldValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(webLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(webPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Comb_forma_pagamento_recebimento, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comb_parcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(webPanel1Layout.createSequentialGroup()
                                .addComponent(webLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(comb_meio_recebimento, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(webPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(webLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(fieldValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(webPanel1Layout.createSequentialGroup()
                                .addComponent(webLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(fieldValorParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(webPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(webPanel1Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(comb_status, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(webLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(webPanel1Layout.createSequentialGroup()
                                .addComponent(webLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_data_lançamento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fieldValorRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(webLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(96, 96, 96)
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botao_abrir_relatorios1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botao_alterar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botao_abrir_relatorios, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botao_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(webPanel1Layout.createSequentialGroup()
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(webPanel1Layout.createSequentialGroup()
                                .addComponent(webLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(checkboxEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(checkboxEntrada3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(checkboxEntrada4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(botao_abrir_relatorios2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(webPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(webPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void webDateField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webDateField1ActionPerformed

    }//GEN-LAST:event_webDateField1ActionPerformed

    private void Comb_tip_operacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Comb_tip_operacaoActionPerformed

        String b = (String) Comb_tip_operacao.getSelectedItem();

    }//GEN-LAST:event_Comb_tip_operacaoActionPerformed

    private void botao_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_salvarActionPerformed

        CarCapContas conta02 = new CarCapContas();
        CarcapOperacoesComerciais conta = new CarcapOperacoesComerciais();
        carcapOperacoesComerciaisDAO c = new carcapOperacoesComerciaisDAO();
        Object parcela = comb_parcelas.getValue();
        int numParcela = Integer.parseInt(parcela.toString());
        TipoConta tipoConta = TipoConta.Entrada;

        // tipo de operação
        if (combo_tip_lancamento.getSelectedIndex() == 0) {
            tipoConta = TipoConta.Entrada;
        } else if (combo_tip_lancamento.getSelectedIndex() == 1) {
            tipoConta = TipoConta.Saida;
        }
        conta.setTipoDeConta(tipoConta);

//        Utils.notificacao("Valor: " + String.valueOf(conta.getTipoDeConta()), Utils.TipoNotificacao.ok, 0);
        String[] txt = txt_busca_produto.getText().split("-");
        int codigo = Integer.parseInt(txt[0]);
        EstProdutoDAO produtoDao = new EstProdutoDAO();
        EstProduto produto = produtoDao.get(codigo);
        conta.setProdutoId(produto);

        //pegar data lançamento
//       
        //Calendar da = Calendar.getInstance();
        Date d = txt_data_lançamento1.getDate();

        conta.setDatLancamento(d);
        //pegar descrição
        conta.setDescricao(txt_descricao.getText());
        //pegar parcela
        conta.setNumeroParcela(numParcela);
        // quantidade produto
        String[] pess = txt_busca_cliente_fornecedor.getText().split("-");
        int cod = Integer.parseInt(txt[0]);
        CarPessoaDAO pessoaDao = new CarPessoaDAO();
        conta.setPessoa(pessoaDao.ListarId(cod));

        //pega nota
        String[] nota = txt_busca_nota.getText().split(" -");
        int chaveNota = Integer.parseInt(nota[0]);
        PatNotaFiscalDAO Nota = new PatNotaFiscalDAO();
        PatNotaFiscal chave = Nota.buscarUm(chaveNota);
        conta.setOperacaoNota(chave);
        //movimento
        // conta.setMovimento(TipoMovimento.venda);
        //quantiade
        String j = fieldQuantidade.getText();
        double b = Double.parseDouble(j);
        conta.setQuantidade(b);
        c.insert(conta);
        for (int i = 0; i < numParcela; i++) {

//             incrementar data
            Date data = txt_data_lançamento.getDate();
            data.setDate(data.getDate() + 30);
            conta02.setContaDataEmissao(data);

//          String formato = "dd/MM/yyyy";
//          
//          SimpleDateFormat formatarData = new SimpleDateFormat(formato);
            //conta02.setPessoaNome(txt_busca_cliente_fornecedor.getText());
            double valorTotal = Double.parseDouble(fieldValorTotal.getText());
            conta02.setContaValorTotal(valorTotal);
            if (comb_status.getSelectedIndex() == 0) {
                conta02.setCapContaStatus(StatusConta.Fechada);
            } else if (comb_status.getSelectedIndex() == 1) {

                conta02.setCapContaStatus(StatusConta.Pendente);
            } else {
                conta02.setCapContaStatus(StatusConta.PendenteParcial);

            //pegar data lançamento
            //pegar data lançamento
            }

            double valorParcela = Double.parseDouble(fieldValorParcela.getText());
            conta02.setContaValorPago(valorParcela);

            // pega o produto
            conta02.setProduto(produto);

            //conta02.setContaDataEmissao(txt_data_lançamento1.getDate());
            conta02.setDescricao(txt_descricao.getText());
            int cont = 0;
            cont = i + 1;
            conta02.setContaNumParcelas(cont);
            String[] pesso = txt_busca_cliente_fornecedor.getText().split("-");
            String nome = (txt[0]);
            conta02.setPessoaNome(nome);
            conta02.setQuantidade_produto(b);
            //pegar nota

//                conta02.setPatNotaFiscal(chave);
            conta02.setForma_rece_pagamento(FormaPagamento.values()[Comb_forma_pagamento_recebimento.getSelectedIndex()]);

            // conta02.setForma_rece_pagamento(FormaPagamento.prazo);
            // Utils.notificacao("Valor: " + p, Utils.TipoNotificacao.ok, 0);
            // conta02.setTipoOperacaoDescricao("iuaszzzdj");
            //Object forma = Comb_forma_pagamento_recebimento.getSelectedItem();
            //Object meio = comb_meio_recebimento.getSelectedItem();
            conta02.setMeio_recebimento(MeioRecebimentoPagamento.values()[comb_meio_recebimento.getSelectedIndex()]);
            conta02.setContaTipo(tipoConta);
            conta02.setCarcapOperacoesComerciais(conta);
            double valorecebido = Double.parseDouble(fieldValorRecebido.getText());
            conta02.setValorRecebido(valorecebido);
            if (valorecebido < valorParcela) {
                conta02.setCapContaStatus(StatusConta.Pendente);
                Utils.notificacao("Lançamento Pendente " + String.valueOf(conta.getTipoDeConta()), Utils.TipoNotificacao.ok, 0);

            } else if (valorecebido > valorParcela) {
                Utils.notificacao("VERIFICAR: recebimento maior que a parcela " + String.valueOf(conta.getTipoDeConta()), Utils.TipoNotificacao.ok, 0);
                conta02.setCapContaStatus(StatusConta.PendenteParcial);
            }
            new CarCapContasDAO().insert(conta02);
            Preenche_tabela();
        }
        zeraCampos();

    }//GEN-LAST:event_botao_salvarActionPerformed

    private void txt_notasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_notasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_notasActionPerformed

    private void txt_pess_cad1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pess_cad1ActionPerformed

    }//GEN-LAST:event_txt_pess_cad1ActionPerformed

    private void txt_data_lançamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_data_lançamentoActionPerformed

    }//GEN-LAST:event_txt_data_lançamentoActionPerformed

    private void txt_pess_cadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pess_cadActionPerformed

    }//GEN-LAST:event_txt_pess_cadActionPerformed

    private void Comb_forma_pagamento_recebimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Comb_forma_pagamento_recebimentoActionPerformed
        String b = (String) Comb_forma_pagamento_recebimento.getSelectedItem();
        if (b.equals("A Vista")) {
            comb_parcelas.setEnabled(false);
        } else {
            comb_parcelas.setEnabled(true);
        }
    }//GEN-LAST:event_Comb_forma_pagamento_recebimentoActionPerformed

    private void comb_statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comb_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comb_statusActionPerformed

    private void checkboxEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxEntradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkboxEntradaActionPerformed

    private void webDateField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webDateField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_webDateField3ActionPerformed

    private void botao_abrir_relatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_abrir_relatoriosActionPerformed
        FormRelatorios_em_tela r = new FormRelatorios_em_tela();
        r.setVisible(true);
    }//GEN-LAST:event_botao_abrir_relatoriosActionPerformed

    private void combo_tip_lancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_tip_lancamentoActionPerformed

    }//GEN-LAST:event_combo_tip_lancamentoActionPerformed

    private void txt_data_lançamento1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_data_lançamento1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_data_lançamento1ActionPerformed

    private void comb_parcelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comb_parcelasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_comb_parcelasMouseClicked

    private void txt_data_lançamento1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_data_lançamento1MouseClicked

    }//GEN-LAST:event_txt_data_lançamento1MouseClicked

    private void checkboxEntrada3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxEntrada3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkboxEntrada3ActionPerformed

    private void botao_abrir_relatorios2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_abrir_relatorios2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botao_abrir_relatorios2ActionPerformed

    private void txt_tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_tabelaMouseClicked

        int codParcela = RetornarLinhaSelecionada(0);
        CarCapContasDAO dao = new CarCapContasDAO();
        CarCapContas pagarValor = dao.BuscarContasId(codParcela);
        Double quantidade = pagarValor.getContaValorTotal() / pagarValor.getQuantidade_produto();
        fieldValorUnitario.setText(String.valueOf(quantidade));
        fieldValorUnitario.setEnabled(false);
        txt_data_lançamento.setText(String.valueOf(pagarValor.getContaDataEmissao()));
        txt_data_lançamento.setEnabled(false);
        txt_busca_produto.setText(String.valueOf(pagarValor.getProduto().getProdutoDescricao()));
        txt_busca_produto.setEnabled(true);
        fieldQuantidade.setText(String.valueOf(pagarValor.getQuantidade_produto()));
        fieldQuantidade.setEnabled(true);
        comb_parcelas.setValue(pagarValor.getContaNumParcelas());
        comb_status.setSelectedIndex(pagarValor.getCapContaStatus().ordinal());
        txt_descricao.setText(pagarValor.getDescricao());
        fieldValorTotal.setText(String.valueOf(pagarValor.getContaValorTotal()));
        fieldValorTotal.setEnabled(true);
        fieldValorParcela.setText(String.valueOf(pagarValor.getContaValorPago()));
        fieldValorParcela.setEnabled(true);
        fieldValorRecebido.setText(String.valueOf(pagarValor.getValorRecebido()));
        if (fieldValorParcela.getText().equals(fieldValorRecebido.getText())) {
            JOptionPane.showMessageDialog(null, "Esta parcela parece estar quitada :D");
        }
    }//GEN-LAST:event_txt_tabelaMouseClicked

    private void botao_alterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_alterarActionPerformed

        int pagaConta = RetornarLinhaSelecionada(0);
        CarCapContasDAO pagar = new CarCapContasDAO();
        CarCapContas altera = new CarCapContas();
        altera = pagar.BuscarContasId(pagaConta);
        altera.setForma_rece_pagamento(FormaPagamento.values()[Comb_forma_pagamento_recebimento.getSelectedIndex()]);
        altera.setDescricao(txt_descricao.getText());
        double valorParcela = Double.parseDouble(fieldValorParcela.getText());
        altera.setContaValorPago(valorParcela);
        double valorecebido = Double.parseDouble(fieldValorRecebido.getText());
        double valortotal = Double.parseDouble(fieldValorTotal.getText());
        altera.setValorRecebido(valorecebido);
        int value = comb_status.getSelectedIndex();
        altera.setCapContaStatus(StatusConta.values()[value]);
        if(value == 1 && valorecebido < valortotal){
            altera.setCapContaStatus(StatusConta.PendenteParcial);
            System.out.println("**********************************************");
        }
        else if (value == 1 && valorecebido > valortotal) {
            // acrescentar nos acrecimos
             altera.setCapContaStatus(StatusConta.Fechada);
        } else if (value == 1 && valorecebido == valortotal){  
            altera.setCapContaStatus(StatusConta.Fechada);
        }
     altera.setContaValorPago(valorecebido);

//        if (valorecebido < valorParcela) {
//
//            altera.setCapContaStatus(StatusConta.Pendente);
//
//               // Utils.notificacao("Lançamento Pendente " + String.valueOf(altera.getTipoDeConta()), Utils.TipoNotificacao.ok, 0);
//        } else if (valorecebido > valorParcela) {
//
//                               // Utils.notificacao("VERIFICAR: recebimento maior que a parcela " + String.valueOf(conta.getTipoDeConta()), Utils.TipoNotificacao.ok, 0);
//            altera.setCapContaStatus(StatusConta.PendenteParcial);
//
//        }
        pagar.update(altera);
        Preenche_tabela();  
        zeraCampos();
    }//GEN-LAST:event_botao_alterarActionPerformed

    private void botao_abrir_relatorios1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_abrir_relatorios1ActionPerformed
        
        zeraCampos();
    }//GEN-LAST:event_botao_abrir_relatorios1ActionPerformed

    /**
         * @param args the command line arguments
         */
        //    public static void main(String args[]) {
        //        /* Set the Nimbus look and feel */
        //        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        //        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
        //         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
        //         */
        //        try {
        //            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        //                if ("Nimbus".equals(info.getName())) {
        //                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
        //                    break;
        //                }
        //            }
        //        } catch (ClassNotFoundException ex) {
        //            java.util.logging.Logger.getLogger(FormContaReceber.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        //        } catch (InstantiationException ex) {
        //            java.util.logging.Logger.getLogger(FormContaReceber.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        //        } catch (IllegalAccessException ex) {
        //            java.util.logging.Logger.getLogger(FormContaReceber.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        //        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        //            java.util.logging.Logger.getLogger(FormContaReceber.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        //        }
        //        //</editor-fold>
        //        //</editor-fold>
        //
        //        /* Create and display the form */
        //        java.awt.EventQueue.invokeLater(new Runnable() {
        //            public void run() {
        //                new FormContaReceber().setVisible(true);
        //            }
        //        });
        //    }
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            WebLookAndFeel.setDecorateAllWindows(true);
            WebLookAndFeel.setDecorateDialogs(true);
            WebLookAndFeel.setDecorateFrames(true);
            LanguageManager.setDefaultLanguage(LanguageManager.PORTUGUESE);
            WebLookAndFeel.install();
            FormContaReceber fluxo = new FormContaReceber();
            fluxo.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.combobox.WebComboBox Comb_forma_pagamento_recebimento;
    private com.alee.laf.combobox.WebComboBox Comb_tip_operacao;
    private com.alee.laf.button.WebButton botao_abrir_relatorios;
    private com.alee.laf.button.WebButton botao_abrir_relatorios1;
    private com.alee.laf.button.WebButton botao_abrir_relatorios2;
    private com.alee.laf.button.WebButton botao_alterar;
    private com.alee.laf.button.WebButton botao_salvar;
    private com.alee.laf.checkbox.WebCheckBox checkboxEntrada;
    private com.alee.laf.checkbox.WebCheckBox checkboxEntrada3;
    private com.alee.laf.checkbox.WebCheckBox checkboxEntrada4;
    private com.alee.laf.combobox.WebComboBox comb_meio_recebimento;
    private com.alee.laf.spinner.WebSpinner comb_parcelas;
    private com.alee.laf.combobox.WebComboBox comb_status;
    private com.alee.laf.combobox.WebComboBox combo_tip_lancamento;
    private components.TextFieldValorMonetario fieldQuantidade;
    private components.TextFieldValorMonetario fieldValorParcela;
    private components.TextFieldValorMonetario fieldValorRecebido;
    private components.TextFieldValorMonetario fieldValorTotal;
    private components.TextFieldValorMonetario fieldValorUnitario;
    private javax.swing.JScrollPane jScrollPane2;
    private components.TextFieldValorMonetario textFieldValorMonetario3;
    private components.TextFieldFK txt_busca_cliente_fornecedor;
    private components.TextFieldFK txt_busca_nota;
    private components.TextFieldFK txt_busca_produto;
    private com.alee.extended.date.WebDateField txt_data_lançamento;
    private com.alee.extended.date.WebDateField txt_data_lançamento1;
    private javax.swing.JTextField txt_descricao;
    private components.TextFieldFK txt_pess_cad;
    private com.alee.laf.table.WebTable txt_tabela;
    private com.alee.extended.date.WebDateField webDateField1;
    private com.alee.extended.date.WebDateField webDateField3;
    private com.alee.laf.label.WebLabel webLabel1;
    private com.alee.laf.label.WebLabel webLabel10;
    private com.alee.laf.label.WebLabel webLabel11;
    private com.alee.laf.label.WebLabel webLabel12;
    private com.alee.laf.label.WebLabel webLabel13;
    private com.alee.laf.label.WebLabel webLabel14;
    private com.alee.laf.label.WebLabel webLabel15;
    private com.alee.laf.label.WebLabel webLabel16;
    private com.alee.laf.label.WebLabel webLabel17;
    private com.alee.laf.label.WebLabel webLabel18;
    private com.alee.laf.label.WebLabel webLabel19;
    private com.alee.laf.label.WebLabel webLabel2;
    private com.alee.laf.label.WebLabel webLabel20;
    private com.alee.laf.label.WebLabel webLabel21;
    private com.alee.laf.label.WebLabel webLabel22;
    private com.alee.laf.label.WebLabel webLabel3;
    private com.alee.laf.label.WebLabel webLabel4;
    private com.alee.laf.label.WebLabel webLabel6;
    private com.alee.laf.label.WebLabel webLabel7;
    private com.alee.laf.label.WebLabel webLabel8;
    private com.alee.laf.label.WebLabel webLabel9;
    private com.alee.laf.panel.WebPanel webPanel1;
    // End of variables declaration//GEN-END:variables
}
