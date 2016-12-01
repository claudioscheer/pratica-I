package forms;

import com.alee.laf.WebLookAndFeel;
import com.alee.laf.desktoppane.WebInternalFrame;
import com.alee.managers.language.LanguageManager;
import dao.CarCapContasDAO;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import model.CarCapContas;
import model.CarcapOperacoesComerciais;
import model.PatNotaFiscal;
import utils.Utils;

/**
 *
 * @author dimhr12
 */

public class FormContaPagar extends WebInternalFrame {

    public FormContaPagar() {
        super("Contas a Pagar", true, true, true, true);
        this.initComponents();
        carregarTudo();
        fieldParcelas.getModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                fieldValorParcela.setValue(fieldValorTotal.getValue() / (Integer) fieldParcelas.getValue());
            }
        });
    }
    
    public void ConfereNF() { // verifica se uma NF foi selecionada e preenche os campos com os dados referentes a ela
        PatNotaFiscal nf = new PatNotaFiscal();
        if (fieldNotaFiscal != null) {
            fieldDescrição.setEnabled(true);
            fieldValorTotal.setEnabled(true);
            ComboBoxFormaPgto.setEnabled(true);
            ComboBoxMeioPgto.setEnabled(true);
            fieldDescrição.setText(nf.getNotaDescricao());
            fieldValorTotal.setTextoMonetario(String.valueOf(nf.getNotaValor()));
        }
    }

    public int RetornarLinhaSelecionada(int posicao) {
        int valor = Integer.parseInt(table_lista.getValueAt(table_lista.getSelectedRow(), posicao).toString());
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
        FormBuscaPessoa buscaFornecedortwoo = new FormBuscaPessoa();
        buscaFornecedortwoo.setFrameBloquear(FormPrincipal.getInstance());
        FormBuscaNotaFiscal buscaNota = new FormBuscaNotaFiscal();
        buscaNota.setFrameBloquear(FormPrincipal.getInstance());
        fieldNotaFiscal.setFrame(buscaNota);
        Date d = new Date();
        fieldDataEntrada.setDate(d);
        Preenche_tabela();
    }

    public void zeraCampos() {
        fieldNotaFiscal.setText(null);
        fieldDescrição.setText(null);
        fieldParcelas.setValue(1);
        fieldValorTotal.setValue(0);
        fieldValorParcela.setValue(0);
        fieldDataLancamento.setDate(null);
    }

    public void EnabledCampos() {
        fieldParcelas.setEnabled(false);
        fieldValorTotal.setEnabled(false);
        fieldValorParcela.setEnabled(false);
        fieldDataLancamento.setEnabled(false);
    }
    
    public void SalvarConta() {
        CarcapOperacoesComerciais conta = new CarcapOperacoesComerciais();
        carcapOperacoesComerciaisDAO c = new carcapOperacoesComerciaisDAO();
        int numParcela = (Integer) fieldParcelas.getValue();
        TipoConta tipoConta = TipoConta.Entrada;
        conta.setTipoDeConta(tipoConta);
        EstProdutoDAO produtoDao = new EstProdutoDAO();
        Date d = fieldDataEntrada.getDate();

        conta.setDatLancamento(d);
        conta.setDescricao(fieldDescrição.getText());
        conta.setNumeroParcela(numParcela);
        CarPessoaDAO pessoaDao = new CarPessoaDAO();

        if (!fieldNotaFiscal.getText().trim().isEmpty()) {
            String[] nota = fieldNotaFiscal.getText().split(" -");
            int chaveNota = Integer.parseInt(nota[0]);
            PatNotaFiscalDAO Nota = new PatNotaFiscalDAO();
            PatNotaFiscal chave = Nota.buscarUm(chaveNota);
            conta.setOperacaoNota(chave);
        }
        c.insert(conta);
        for (int i = 0; i < numParcela; i++) {
            CarCapContas conta02 = new CarCapContas();

       
            Date data = fieldDataLancamento.getDate();
            Calendar cal = Calendar.getInstance();
            cal.setTime(data);
            cal.add(Calendar.MONTH, i);
            conta02.setContaDataEmissao(cal.getTime());

            fieldValorParcela.setValue(fieldValorTotal.getValue() / (Integer) fieldParcelas.getValue());
            conta02.setContaValorTotal(fieldValorTotal.getValue());
            conta02.setContaValorPago(fieldValorParcela.getValue());
            if (fieldValorRecebido.getValue() == 0) {
                conta02.setCapContaStatus(StatusConta.PendenteEmser);
            } else if (fieldValorRecebido.getValue() < fieldValorParcela.getValue()) {
                conta02.setCapContaStatus(StatusConta.PendenteVencida);
            } else if (fieldValorRecebido.getValue() >= fieldValorParcela.getValue()) {
                conta02.setCapContaStatus(StatusConta.Quitada);
            }

            conta02.setDescricao(fieldDescrição.getText());
            conta02.setContaNumParcelas(i + 1);

            conta02.setQuantidade_produto(conta.getQuantidade());

            conta02.setForma_rece_pagamento(FormaPagamento.values()[ComboBoxFormaPgto.getSelectedIndex()]);

            conta02.setMeio_recebimento(MeioRecebimentoPagamento.values()[ComboBoxMeioPgto.getSelectedIndex()]);
            conta02.setContaTipo(tipoConta);
            conta02.setCarcapOperacoesComerciais(conta);
            conta02.setValorRecebido(fieldValorRecebido.getValue());
            if (conta02.getValorRecebido() < conta02.getContaValorPago()) {
                conta02.setCapContaStatus(StatusConta.PendenteEmser);
                Utils.notificacao("Lançamento Pendente " + String.valueOf(conta.getTipoDeConta()), Utils.TipoNotificacao.ok, 0);

            } else if (conta02.getValorRecebido() > conta02.getContaValorPago()) {
                Utils.notificacao("VERIFICAR: recebimento maior que a parcela " + String.valueOf(conta.getTipoDeConta()), Utils.TipoNotificacao.ok, 0);
                conta02.setCapContaStatus(StatusConta.PendenteEmser);
            }
            new CarCapContasDAO().insert(conta02);
            Preenche_tabela();
        }
    }

    public void Preenche_tabela() {

        CarCapContasDAO retorn_valores = new CarCapContasDAO();
        List<CarCapContas> conta = retorn_valores.getAll();
        DefaultTableModel tabelamodelo = (DefaultTableModel) table_lista.getModel();
        tabelamodelo.setRowCount(0);

    /*    for (CarCapContas j : conta) {
            tabelamodelo.addRow(new Object[]{
//                j.getContaId(), 
//                j.getvencimento(),
//                j.getstatus (),
//                j.getparcelas pagas (), 
//                j.getparcelas pendentes(), 
//                j.getR$ total(), 
//                j.getR$ pendente()});
        }
        table_lista.setModel(tabelamodelo);
    }
*/
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        webPanel1 = new com.alee.laf.panel.WebPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_lista = new com.alee.laf.table.WebTable();
        ComboBoxMeioPgto = new com.alee.laf.combobox.WebComboBox();
        ComboBoxFormaPgto = new com.alee.laf.combobox.WebComboBox();
        txtParcelas = new com.alee.laf.label.WebLabel();
        txtValorRecebido = new com.alee.laf.label.WebLabel();
        fieldNotaFiscal = new components.TextFieldFK();
        txtNotaFiscal = new com.alee.laf.label.WebLabel();
        btnSalvar = new com.alee.laf.button.WebButton();
        txtFormaPagamento = new com.alee.laf.label.WebLabel();
        txtDescricao = new com.alee.laf.label.WebLabel();
        fieldDescrição = new javax.swing.JTextField();
        txtDataPagamento = new com.alee.laf.label.WebLabel();
        txtMeioPagamento = new com.alee.laf.label.WebLabel();
        txtValorTotal = new com.alee.laf.label.WebLabel();
        fieldDataLancamento = new com.alee.extended.date.WebDateField();
        btnParcelas = new com.alee.laf.button.WebButton();
        fieldDataEntrada = new com.alee.extended.date.WebDateField();
        txtPrimeiroVencimento = new com.alee.laf.label.WebLabel();
        fieldParcelas = new com.alee.laf.spinner.WebSpinner();
        txtValorParcela = new com.alee.laf.label.WebLabel();
        fieldValorParcela = new components.TextFieldValorMonetario();
        fieldValorTotal = new components.TextFieldValorMonetario();
        fieldValorRecebido = new components.TextFieldValorMonetario();
        txtValorPendente = new com.alee.laf.label.WebLabel();
        fieldValorPendente = new components.TextFieldValorMonetario();

        webPanel1.setEnabled(false);

        table_lista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Vencimento", "Status", "Parcelas pagas", "Parcelas pendentes", "R$ Total", "R$ Pendente"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_lista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_listaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_lista);
        if (table_lista.getColumnModel().getColumnCount() > 0) {
            table_lista.getColumnModel().getColumn(0).setPreferredWidth(50);
            table_lista.getColumnModel().getColumn(0).setMaxWidth(50);
            table_lista.getColumnModel().getColumn(1).setPreferredWidth(100);
            table_lista.getColumnModel().getColumn(2).setPreferredWidth(100);
            table_lista.getColumnModel().getColumn(3).setPreferredWidth(100);
            table_lista.getColumnModel().getColumn(4).setPreferredWidth(100);
            table_lista.getColumnModel().getColumn(5).setPreferredWidth(100);
            table_lista.getColumnModel().getColumn(6).setPreferredWidth(100);
            table_lista.getColumnModel().getColumn(7).setPreferredWidth(100);
            table_lista.getColumnModel().getColumn(8).setPreferredWidth(100);
        }

        ComboBoxMeioPgto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Dinheiro", "Cheque", "Banco" }));
        ComboBoxMeioPgto.setEnabled(false);

        ComboBoxFormaPgto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "À Vista", "A Prazo" }));
        ComboBoxFormaPgto.setEnabled(false);
        ComboBoxFormaPgto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxFormaPgtoActionPerformed(evt);
            }
        });

        txtParcelas.setText("Parcelas:");

        txtValorRecebido.setText("Valor Recebido / Entrada:");

        fieldNotaFiscal.setToolTipText("Selecione a nota fiscal a ser vinculada a esta conta");

        txtNotaFiscal.setText("Nota Fiscal:");

        btnSalvar.setBackground(new java.awt.Color(51, 255, 51));
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/salvar_ok.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        txtFormaPagamento.setText("Forma de Pagamento:");

        txtDescricao.setText("Descrição:");

        fieldDescrição.setToolTipText("");
        fieldDescrição.setEnabled(false);

        txtDataPagamento.setText("Data de pagamento:");

        txtMeioPagamento.setText("Meio de Pagamento:");

        txtValorTotal.setText("Valor Total:");

        fieldDataLancamento.setToolTipText("Informe a data de pagamento da primeira parcela");

        btnParcelas.setBackground(new java.awt.Color(51, 255, 51));
        btnParcelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/editar_ok.png"))); // NOI18N
        btnParcelas.setText("Parcelas");
        btnParcelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParcelasActionPerformed(evt);
            }
        });

        fieldDataEntrada.setToolTipText("Informe a data de entrada desta conta");
        fieldDataEntrada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fieldDataEntradaMouseClicked(evt);
            }
        });

        txtPrimeiroVencimento.setText("Data 1° Vencimento:");

        fieldParcelas.setModel(new javax.swing.SpinnerNumberModel(1, 1, 50, 1));
        fieldParcelas.setToolTipText("Informe o número de parcelas da conta");
        fieldParcelas.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                fieldParcelasStateChanged(evt);
            }
        });

        txtValorParcela.setText("Valor da Parcela:");

        fieldValorParcela.setEditable(false);
        fieldValorParcela.setEnabled(false);

        fieldValorTotal.setEditable(false);
        fieldValorTotal.setEnabled(false);

        fieldValorRecebido.setToolTipText("Caso tenha sido pago algum valor da conta, informe aqui");

        txtValorPendente.setText("Valor Pendente:");

        fieldValorPendente.setEnabled(false);

        javax.swing.GroupLayout webPanel1Layout = new javax.swing.GroupLayout(webPanel1);
        webPanel1.setLayout(webPanel1Layout);
        webPanel1Layout.setHorizontalGroup(
            webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(webPanel1Layout.createSequentialGroup()
                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(webPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(webPanel1Layout.createSequentialGroup()
                                .addComponent(txtDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(fieldDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(webPanel1Layout.createSequentialGroup()
                                .addComponent(txtNotaFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(fieldNotaFiscal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(fieldDescrição, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(webPanel1Layout.createSequentialGroup()
                                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fieldValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ComboBoxFormaPgto, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ComboBoxMeioPgto, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMeioPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(webPanel1Layout.createSequentialGroup()
                                .addComponent(txtValorPendente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fieldValorPendente, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(webPanel1Layout.createSequentialGroup()
                                .addComponent(txtValorRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(fieldValorRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, webPanel1Layout.createSequentialGroup()
                                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtValorParcela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPrimeiroVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fieldValorParcela, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                    .addComponent(fieldParcelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(fieldDataLancamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(18, 43, Short.MAX_VALUE))
                    .addGroup(webPanel1Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(btnParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
                .addContainerGap())
        );
        webPanel1Layout.setVerticalGroup(
            webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(webPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(webPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(22, Short.MAX_VALUE))
                    .addGroup(webPanel1Layout.createSequentialGroup()
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldNotaFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNotaFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(webPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fieldDescrição, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(webPanel1Layout.createSequentialGroup()
                                        .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(fieldValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(webPanel1Layout.createSequentialGroup()
                                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtMeioPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(ComboBoxFormaPgto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ComboBoxMeioPgto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtValorRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fieldValorRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtValorPendente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fieldValorPendente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fieldParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtValorParcela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fieldValorParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPrimeiroVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fieldDataLancamento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, webPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(43, 43, 43))))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(webPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(webPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_notasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_notasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_notasActionPerformed

    private void txt_pess_cad1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pess_cad1ActionPerformed

    }//GEN-LAST:event_txt_pess_cad1ActionPerformed

    private void fieldDataEntradaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fieldDataEntradaMouseClicked

    }//GEN-LAST:event_fieldDataEntradaMouseClicked

    private void btnParcelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParcelasActionPerformed

        int pagaConta = RetornarLinhaSelecionada(0);
        CarCapContasDAO pagar = new CarCapContasDAO();
        CarCapContas altera = pagar.BuscarContasId(pagaConta);
        altera.setForma_rece_pagamento(FormaPagamento.values()[ComboBoxFormaPgto.getSelectedIndex()]);
        altera.setDescricao(fieldDescrição.getText());
        double valorParcela = fieldValorParcela.getValue();
        double valorecebido = fieldValorRecebido.getValue();
        altera.setValorRecebido(valorecebido);
        if (valorecebido == 0) {
            altera.setCapContaStatus(StatusConta.PendenteEmser);
        } else if (valorecebido < valorParcela) {
            altera.setCapContaStatus(StatusConta.PendenteVencida);
        } else if (valorecebido >= valorParcela) {
            altera.setCapContaStatus(StatusConta.Quitada);
        }

        altera.setContaValorPago(valorecebido);

        pagar.update(altera);
        Preenche_tabela();
    }//GEN-LAST:event_btnParcelasActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        SalvarConta();
        zeraCampos();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void ComboBoxFormaPgtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxFormaPgtoActionPerformed
        String b = (String) ComboBoxFormaPgto.getSelectedItem();
        if (b.equals("A Vista")) {
            fieldParcelas.setEnabled(false);
        } else {
            fieldParcelas.setEnabled(true);
        }
    }//GEN-LAST:event_ComboBoxFormaPgtoActionPerformed

    private void table_listaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_listaMouseClicked

        int codParcela = RetornarLinhaSelecionada(0);
        CarCapContasDAO dao = new CarCapContasDAO();
        CarCapContas pagarValor = dao.BuscarContasId(codParcela);
        fieldDataLancamento.setText(String.valueOf(pagarValor.getContaDataEmissao()));
        fieldDataLancamento.setEnabled(false);

        fieldParcelas.setValue(pagarValor.getContaNumParcelas());

        fieldDescrição.setText(pagarValor.getDescricao());
        fieldValorTotal.setText(String.valueOf(pagarValor.getContaValorTotal()));
        fieldValorTotal.setEnabled(true);
        fieldValorParcela.setText(String.valueOf(pagarValor.getContaValorPago()));
        fieldValorParcela.setEnabled(true);
        fieldValorRecebido.setText(String.valueOf(pagarValor.getValorRecebido()));
        if (fieldValorParcela.getText().equals(fieldValorRecebido.getText())) {
            JOptionPane.showMessageDialog(null, "Esta parcela parece estar quitada :D");
        }
        
        EnabledCampos();
    }//GEN-LAST:event_table_listaMouseClicked

    private void fieldParcelasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_fieldParcelasStateChanged
            // TODO add your handling code here:
            System.out.println("parcela alterada");
    }//GEN-LAST:event_fieldParcelasStateChanged

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            WebLookAndFeel.setDecorateAllWindows(true);
            WebLookAndFeel.setDecorateDialogs(true);
            WebLookAndFeel.setDecorateFrames(true);
            LanguageManager.setDefaultLanguage(LanguageManager.PORTUGUESE);
            WebLookAndFeel.install();
            FormContaPagar fluxo = new FormContaPagar();
            fluxo.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.combobox.WebComboBox ComboBoxFormaPgto;
    private com.alee.laf.combobox.WebComboBox ComboBoxMeioPgto;
    private com.alee.laf.button.WebButton btnParcelas;
    private com.alee.laf.button.WebButton btnSalvar;
    private com.alee.extended.date.WebDateField fieldDataEntrada;
    private com.alee.extended.date.WebDateField fieldDataLancamento;
    private javax.swing.JTextField fieldDescrição;
    private components.TextFieldFK fieldNotaFiscal;
    private com.alee.laf.spinner.WebSpinner fieldParcelas;
    private components.TextFieldValorMonetario fieldValorParcela;
    private components.TextFieldValorMonetario fieldValorPendente;
    private components.TextFieldValorMonetario fieldValorRecebido;
    private components.TextFieldValorMonetario fieldValorTotal;
    private javax.swing.JScrollPane jScrollPane2;
    private com.alee.laf.table.WebTable table_lista;
    private com.alee.laf.label.WebLabel txtDataPagamento;
    private com.alee.laf.label.WebLabel txtDescricao;
    private com.alee.laf.label.WebLabel txtFormaPagamento;
    private com.alee.laf.label.WebLabel txtMeioPagamento;
    private com.alee.laf.label.WebLabel txtNotaFiscal;
    private com.alee.laf.label.WebLabel txtParcelas;
    private com.alee.laf.label.WebLabel txtPrimeiroVencimento;
    private com.alee.laf.label.WebLabel txtValorParcela;
    private com.alee.laf.label.WebLabel txtValorPendente;
    private com.alee.laf.label.WebLabel txtValorRecebido;
    private com.alee.laf.label.WebLabel txtValorTotal;
    private com.alee.laf.panel.WebPanel webPanel1;
    // End of variables declaration//GEN-END:variables
}
