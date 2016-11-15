/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.alee.laf.WebLookAndFeel;
import com.alee.laf.desktoppane.WebInternalFrame;
import com.alee.managers.language.LanguageManager;
import dao.CarCapContasDAO;
import dao.FlxcxOperacoesDAO;
import enumeraveis.TipoConta;
import forms.busca.FormBuscaFornecedor;
import forms.busca.FormBuscaMarca;
import forms.busca.FormBuscaNotaFiscal;

import components.TextFieldFK;
import dao.EstProdutoDAO;
import dao.carcapOperacoesComerciaisDAO;
import enumeraveis.TipoMovimento;

import components.TextFieldFK;
import dao.CarPessoaDAO;
import dao.PatNotaFiscalDAO;
import enumeraveis.FormaPagamento;
import enumeraveis.MeioRecebimentoPagamento;
import enumeraveis.StatusConta;

import forms.busca.FormBuscaProduto;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import model.CarCapContas;
import model.CarPessoa;
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

        super("Contas a Receber", true, true, true, true);
        this.initComponents();
//        new LoadBackground().execute();
        carregarTudo();
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

        FormBuscaFornecedor buscaFornecedortwoo = new FormBuscaFornecedor();

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

        txtQuantidade.setText(null);

        txt_descricao.setText(null);

        comb_parcelas.setValue(1);

        txt_Valor_Total.setText(null);

        txt_Valor_parcela.setText(null);

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
                j.getQuantidade_produto(), j.getCapContaStatus(), j.getContaValorPago(),j.getValorRecebido()});

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
        txtQuantidade = new javax.swing.JTextField();
        webLabel7 = new com.alee.laf.label.WebLabel();
        txt_preco_uni = new javax.swing.JTextField();
        txt_Valor_Total = new javax.swing.JTextField();
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
        txt_Valor_parcela = new javax.swing.JTextField();
        webLabel21 = new com.alee.laf.label.WebLabel();
        comb_parcelas = new com.alee.laf.spinner.WebSpinner();
        txt_Valor_recebido = new javax.swing.JTextField();
        webLabel22 = new com.alee.laf.label.WebLabel();
        checkboxEntrada4 = new com.alee.laf.checkbox.WebCheckBox();
        checkboxEntrada5 = new com.alee.laf.checkbox.WebCheckBox();
        botao_abrir_relatorios2 = new com.alee.laf.button.WebButton();

        webPanel1.setEnabled(false);
        webPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        webPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(606, 110, 730, 460));

        webLabel1.setText("Tipo de Lançamento:");
        webPanel1.add(webLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 10, -1, -1));

        webLabel2.setText("Quantidade:");
        webPanel1.add(webLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 134, -1, 22));

        webLabel3.setText("Cliente/Fornecedor:");
        webPanel1.add(webLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));

        combo_tip_lancamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ENTRADA", "SAÍDA" }));
        combo_tip_lancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_tip_lancamentoActionPerformed(evt);
            }
        });
        webPanel1.add(combo_tip_lancamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 30, 157, 30));

        comb_meio_recebimento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dinheiro", "Cheque", "Via Banco" }));
        webPanel1.add(comb_meio_recebimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 344, 210, 32));

        webDateField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webDateField1ActionPerformed(evt);
            }
        });
        webPanel1.add(webDateField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 30, 120, -1));

        Comb_forma_pagamento_recebimento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A Prazo", "A Vista", " ", " " }));
        Comb_forma_pagamento_recebimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comb_forma_pagamento_recebimentoActionPerformed(evt);
            }
        });
        webPanel1.add(Comb_forma_pagamento_recebimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 274, 210, 32));

        webLabel4.setText("Parcelas:");
        webPanel1.add(webLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 254, -1, 22));

        txtQuantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantidadeActionPerformed(evt);
            }
        });
        webPanel1.add(txtQuantidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 154, 100, 30));

        webLabel7.setText("Status:");
        webPanel1.add(webLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 60, -1, 20));

        txt_preco_uni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_preco_uniActionPerformed(evt);
            }
        });
        webPanel1.add(txt_preco_uni, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 274, 100, 30));

        txt_Valor_Total.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_Valor_TotalMouseClicked(evt);
            }
        });
        txt_Valor_Total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_Valor_TotalActionPerformed(evt);
            }
        });
        webPanel1.add(txt_Valor_Total, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 344, 100, 30));

        webLabel8.setText("Valor Recebido:");
        webPanel1.add(webLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 380, -1, 20));

        txt_busca_nota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_busca_notaActionPerformed(evt);
            }
        });
        webPanel1.add(txt_busca_nota, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 154, 280, 33));

        webLabel6.setText("Vincular NF:");
        webPanel1.add(webLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 134, -1, 22));

        webLabel9.setText("Produto:");
        webPanel1.add(webLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 78, -1, 22));

        webLabel10.setText("Tipo de Operação:");
        webPanel1.add(webLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 78, -1, -1));

        Comb_tip_operacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comb_tip_operacaoActionPerformed(evt);
            }
        });
        webPanel1.add(Comb_tip_operacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 98, 160, 30));

        botao_abrir_relatorios.setBackground(new java.awt.Color(51, 255, 51));
        botao_abrir_relatorios.setText("Relatórios");
        botao_abrir_relatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_abrir_relatoriosActionPerformed(evt);
            }
        });
        webPanel1.add(botao_abrir_relatorios, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 120, 30));

        botao_salvar.setBackground(new java.awt.Color(51, 255, 51));
        botao_salvar.setText("Salvar");
        botao_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_salvarActionPerformed(evt);
            }
        });
        webPanel1.add(botao_salvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 530, 130, 33));

        webLabel11.setText("Forma de Pagamento/Recebimento:");
        webPanel1.add(webLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 254, -1, 22));

        webLabel12.setText("Descrição:");
        webPanel1.add(webLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 184, -1, 22));

        txt_descricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_descricaoActionPerformed(evt);
            }
        });
        webPanel1.add(txt_descricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 204, 560, 26));

        webLabel13.setText("Data Lançamento:");
        webPanel1.add(webLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        webLabel14.setText("Meio de pagamento/Recebimento:");
        webPanel1.add(webLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, 22));

        checkboxEntrada.setText("Pendente");
        checkboxEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxEntradaActionPerformed(evt);
            }
        });
        webPanel1.add(checkboxEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 80, -1, -1));

        checkboxEntrada3.setText("Estorno");
        checkboxEntrada3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxEntrada3ActionPerformed(evt);
            }
        });
        webPanel1.add(checkboxEntrada3, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 80, -1, -1));

        webLabel15.setText("Valor Total:");
        webPanel1.add(webLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 324, -1, 23));

        txt_data_lançamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_data_lançamentoActionPerformed(evt);
            }
        });
        webPanel1.add(txt_data_lançamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 400, 150, 30));

        webLabel16.setText("Data Inicial:");
        webPanel1.add(webLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 10, -1, 22));

        txt_pess_cad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_pess_cadActionPerformed(evt);
            }
        });
        webPanel1.add(txt_pess_cad, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 30, 430, 30));

        webLabel17.setText("Cliente/Fornecedor:");
        webPanel1.add(webLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, -1, 22));

        comb_status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fehada", "Pendente", "Pendente parcial", "Estorno", " " }));
        comb_status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comb_statusActionPerformed(evt);
            }
        });
        webPanel1.add(comb_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 404, 210, 30));

        webLabel18.setText("Status:");
        webPanel1.add(webLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 384, -1, 22));

        webDateField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webDateField3ActionPerformed(evt);
            }
        });
        webPanel1.add(webDateField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 30, 120, -1));

        webLabel19.setText("Data Final:");
        webPanel1.add(webLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 10, -1, 22));

        botao_alterar.setBackground(new java.awt.Color(51, 255, 51));
        botao_alterar.setText("Alterar");
        webPanel1.add(botao_alterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 530, 130, 33));

        txt_busca_cliente_fornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_busca_cliente_fornecedorActionPerformed(evt);
            }
        });
        webPanel1.add(txt_busca_cliente_fornecedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 390, 30));
        webPanel1.add(txt_busca_produto, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 98, 390, 30));

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
        webPanel1.add(txt_data_lançamento1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 154, 160, 30));

        webLabel20.setText("Data 1° Vencimento:");
        webPanel1.add(webLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 380, -1, 20));

        txt_Valor_parcela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_Valor_parcelaMouseClicked(evt);
            }
        });
        txt_Valor_parcela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_Valor_parcelaActionPerformed(evt);
            }
        });
        webPanel1.add(txt_Valor_parcela, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 340, 140, 31));

        webLabel21.setText("Valor Unitário:");
        webPanel1.add(webLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 254, -1, 20));

        comb_parcelas.setModel(new javax.swing.SpinnerNumberModel(1, 1, 50, 1));
        comb_parcelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comb_parcelasMouseClicked(evt);
            }
        });
        webPanel1.add(comb_parcelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 274, 150, 32));

        txt_Valor_recebido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_Valor_recebidoMouseClicked(evt);
            }
        });
        txt_Valor_recebido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_Valor_recebidoActionPerformed(evt);
            }
        });
        webPanel1.add(txt_Valor_recebido, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 400, 140, 31));

        webLabel22.setText("Valor da Parcela:");
        webPanel1.add(webLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 320, -1, 20));

        checkboxEntrada4.setText("Fechada");
        webPanel1.add(checkboxEntrada4, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 80, -1, -1));

        checkboxEntrada5.setText("Parcialmente Fechada");
        checkboxEntrada5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxEntrada5ActionPerformed(evt);
            }
        });
        webPanel1.add(checkboxEntrada5, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, -1, -1));

        botao_abrir_relatorios2.setBackground(new java.awt.Color(51, 255, 51));
        botao_abrir_relatorios2.setText("Filtrar");
        botao_abrir_relatorios2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_abrir_relatorios2ActionPerformed(evt);
            }
        });
        webPanel1.add(botao_abrir_relatorios2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 60, 90, 33));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(webPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1359, Short.MAX_VALUE)
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

    private void txtQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantidadeActionPerformed


    }//GEN-LAST:event_txtQuantidadeActionPerformed

    private void txt_descricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_descricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_descricaoActionPerformed

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

        PatNotaFiscal chave = Nota.get(chaveNota);

        conta.setOperacaoNota(chave);

        //movimento
        // conta.setMovimento(TipoMovimento.venda);
        //quantiade
        String j = txtQuantidade.getText();

        double b = Double.parseDouble(j);

        conta.setQuantidade(b);

        c.insert(conta);

        for (int i = 0; i < numParcela; i++) {

//             incrementar data
            Date data = txt_data_lançamento.getDate();
            
          
            
        
          data.setDate(data.getDate()+30);
          
          conta02.setContaDataEmissao(data);
          
//          String formato = "dd/MM/yyyy";
//          
//          SimpleDateFormat formatarData = new SimpleDateFormat(formato);
          
          
          
          
          
            //conta02.setPessoaNome(txt_busca_cliente_fornecedor.getText());

          
          
            double valorTotal = Double.parseDouble(txt_Valor_Total.getText());

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

            double valorParcela = Double.parseDouble(txt_Valor_parcela.getText());

            conta02.setContaValorPago(valorParcela);

            // pega o produto
            conta02.setProduto(produto);

            //conta02.setContaDataEmissao(txt_data_lançamento1.getDate());

            conta02.setDescricao(txt_descricao.getText());
            
            int cont = 0;
                cont = i+1;

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
            
            double valorecebido = Double.parseDouble(txt_Valor_recebido.getText());
           
            
            conta02.setValorRecebido(valorecebido);
       
            
            
            
            

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

    private void txt_Valor_TotalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_Valor_TotalMouseClicked
        String b = txtQuantidade.getText();

        b = b.replace(",", "."); //Isso precisa ter pois se o cara digitar o numero com virgula vai dar pau no double

        double q = FormataValor(Double.parseDouble(b));

        String c = txt_preco_uni.getText();

        c = c.replace(",", "."); //Isso precisa ter pois se o cara digitar o numero com virgula vai dar pau no double

        double u = FormataValor(Double.parseDouble(c));

        double soma = q * u;

        String Soma = "" + soma;

        txt_Valor_Total.setText(Soma);


    }//GEN-LAST:event_txt_Valor_TotalMouseClicked

    private void txt_Valor_TotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_Valor_TotalActionPerformed

    }//GEN-LAST:event_txt_Valor_TotalActionPerformed

    private void combo_tip_lancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_tip_lancamentoActionPerformed

    }//GEN-LAST:event_combo_tip_lancamentoActionPerformed

    private void txt_busca_notaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_busca_notaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_busca_notaActionPerformed

    private void txt_data_lançamento1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_data_lançamento1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_data_lançamento1ActionPerformed

    private void txt_busca_cliente_fornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_busca_cliente_fornecedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_busca_cliente_fornecedorActionPerformed

    private void txt_Valor_parcelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_Valor_parcelaMouseClicked

        String b = txtQuantidade.getText();

        b = b.replace(",", "."); //Isso precisa ter pois se o cara digitar o numero com virgula vai dar pau no double

        double q = FormataValor(Double.parseDouble(b));

        String c = txt_preco_uni.getText();

        c = c.replace(",", "."); //Isso precisa ter pois se o cara digitar o numero com virgula vai dar pau no double

        double u = FormataValor(Double.parseDouble(c));

        double soma = q * u;

        String Soma = "" + soma;

        txt_Valor_Total.setText(Soma);

        Object parcela = comb_parcelas.getValue();

        int numParcela = Integer.parseInt(parcela.toString());

        double valor_total = Double.parseDouble(txt_Valor_Total.getText());

        double valor_parcela = 0;

        String setvalorParcela = "";

        setvalorParcela = "" + valor_parcela;

        if (numParcela > 1) {
            valor_parcela = valor_total / numParcela;
            setvalorParcela = "" + valor_parcela;
            txt_Valor_parcela.setText(setvalorParcela);

        } else {

            txt_Valor_parcela.setText(Soma);
            txt_Valor_parcela.setEnabled(false);

        }

        if (numParcela != 1 && Comb_forma_pagamento_recebimento.getSelectedIndex() == 1) {

            JOptionPane.showMessageDialog(null, "Operação inválida, rever número de parcelas em relação ao"
                    + "forma de recebimento *A VISTA*");
            // Utils.notificacao("Operação inválida, rever número de parcelas " , Utils.TipoNotificacao.erro, 0);
        }


    }//GEN-LAST:event_txt_Valor_parcelaMouseClicked

    private void txt_Valor_parcelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_Valor_parcelaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_Valor_parcelaActionPerformed

    private void comb_parcelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comb_parcelasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_comb_parcelasMouseClicked

    private void txt_preco_uniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_preco_uniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_preco_uniActionPerformed

    private void txt_data_lançamento1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_data_lançamento1MouseClicked
        
    }//GEN-LAST:event_txt_data_lançamento1MouseClicked

    private void txt_Valor_recebidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_Valor_recebidoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_Valor_recebidoMouseClicked

    private void txt_Valor_recebidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_Valor_recebidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_Valor_recebidoActionPerformed

    private void checkboxEntrada3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxEntrada3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkboxEntrada3ActionPerformed

    private void checkboxEntrada5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxEntrada5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkboxEntrada5ActionPerformed

    private void botao_abrir_relatorios2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_abrir_relatorios2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botao_abrir_relatorios2ActionPerformed

    private void txt_tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_tabelaMouseClicked
       
        
          int codParcela = RetornarLinhaSelecionada(0);
       
        CarCapContasDAO dao = new CarCapContasDAO();
        
        CarCapContas pagarValor =  dao.BuscarContasId(codParcela);
        
        Double quantidade = pagarValor.getContaValorTotal()/pagarValor.getQuantidade_produto();
        
        
        txt_preco_uni.setText(String.valueOf(quantidade));
        
        txt_data_lançamento.setText(String.valueOf( pagarValor.getContaDataEmissao()));
        
        txt_busca_produto.setText(String.valueOf( pagarValor.getProduto().getProdutoDescricao()));
        
        txtQuantidade.setText(String.valueOf( pagarValor.getQuantidade_produto()));
        
        comb_status.setToolTip(String.valueOf(pagarValor.getContaStatus()));
        
        txt_descricao.setText(pagarValor.getDescricao());
        
        txt_Valor_Total.setText(String.valueOf(pagarValor.getContaValorTotal()));
        txt_Valor_parcela.setText(String.valueOf(pagarValor.getContaValorPago()));
        
        txt_Valor_recebido.setText(String.valueOf(pagarValor.getValorRecebido()));
        
        if(txt_Valor_parcela.getText().equals(txt_Valor_recebido.getText())){

            
            JOptionPane.showMessageDialog(null,"Esta Parcela parece estar quitada :D");
            
            
            
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
       
        
        
        
    }//GEN-LAST:event_txt_tabelaMouseClicked

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

    private static double FormataValor(Double valor) {

        String pattern = "###.##";

        DecimalFormat dm = new DecimalFormat(pattern);

        String str = dm.format(valor);

        return Float.parseFloat(str.replace(',', '.'));

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.combobox.WebComboBox Comb_forma_pagamento_recebimento;
    private com.alee.laf.combobox.WebComboBox Comb_tip_operacao;
    private com.alee.laf.button.WebButton botao_abrir_relatorios;
    private com.alee.laf.button.WebButton botao_abrir_relatorios2;
    private com.alee.laf.button.WebButton botao_alterar;
    private com.alee.laf.button.WebButton botao_salvar;
    private com.alee.laf.checkbox.WebCheckBox checkboxEntrada;
    private com.alee.laf.checkbox.WebCheckBox checkboxEntrada3;
    private com.alee.laf.checkbox.WebCheckBox checkboxEntrada4;
    private com.alee.laf.checkbox.WebCheckBox checkboxEntrada5;
    private com.alee.laf.combobox.WebComboBox comb_meio_recebimento;
    private com.alee.laf.spinner.WebSpinner comb_parcelas;
    private com.alee.laf.combobox.WebComboBox comb_status;
    private com.alee.laf.combobox.WebComboBox combo_tip_lancamento;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JTextField txt_Valor_Total;
    private javax.swing.JTextField txt_Valor_parcela;
    private javax.swing.JTextField txt_Valor_recebido;
    private components.TextFieldFK txt_busca_cliente_fornecedor;
    private components.TextFieldFK txt_busca_nota;
    private components.TextFieldFK txt_busca_produto;
    private com.alee.extended.date.WebDateField txt_data_lançamento;
    private com.alee.extended.date.WebDateField txt_data_lançamento1;
    private javax.swing.JTextField txt_descricao;
    private components.TextFieldFK txt_pess_cad;
    private javax.swing.JTextField txt_preco_uni;
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
