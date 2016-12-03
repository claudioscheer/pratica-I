package forms;

import com.alee.laf.WebLookAndFeel;
import com.alee.laf.desktoppane.WebInternalFrame;
import com.alee.managers.language.LanguageManager;
import components.Validador;
import dao.CarCapContasDAO;
import enumeraveis.TipoConta;
import forms.busca.FormBuscaNotaFiscal;
import dao.PatNotaFiscalDAO;
import dao.carcapOperacoesComerciaisDAO;
import enumeraveis.FormaPagamento;
import enumeraveis.StatusConta;
import forms.busca.FormBuscaPessoa;
import forms.busca.FormBuscaProduto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;
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
    
    PatNotaFiscal nf = new PatNotaFiscal();
    private List<CarCapContas> contas;
    private Validador validador;
    private Stack<CarcapOperacoesComerciais> parc;
    Date dataAtual = new Date();

    /*
    public class Teste {
        Stack<Parcelas> parc = new Stack<Parcelas>();
        Parcelas parcInsere = new Parcelas();
        parc.push(parcInsere);
        Parcelas parcRemove = pilha.pop();
        if (pilha.isEmpty()) {
          System.out.println("A pilha está vazia");
        }
}  */
    
    public FormContaPagar() {
        super("Contas a Pagar", true, true, true, true);
        this.initComponents();
        //this.validador.addObrigatorioValidator(fieldNotaFiscal);
      
        carregarTudo();
        fieldParcelas.getModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                fieldValorParcela.setValue(fieldValorTotal.getValue() / (Integer) fieldParcelas.getValue());
            }
        });
    }
    
    public void PreencheDadosNF() { // verifica se uma NF foi selecionada e preenche os campos com os dados referentes a ela
        fieldDescrição.setEnabled(true);
        fieldValorTotal.setEnabled(true);
        ComboBoxFormaPgto.setEnabled(true);
        ComboBoxMeioPgto.setEnabled(true);
        fieldDescrição.setText(nf.getNotaDescricao());
        fieldValorTotal.setValue(nf.getNotaValor());
    }

    public int RetornarLinhaSelecionada(int posicao) {
        int valor = Integer.parseInt(tableLista.getValueAt(tableLista.getSelectedRow(), posicao).toString());
        return valor;
    }

    private void carregarTudo() {
        FormBuscaNotaFiscal buscaNota = new FormBuscaNotaFiscal();
        fieldNotaFiscal.setFrame(buscaNota);
        buscaNota.setFrameBloquear(FormPrincipal.getInstance());
        
        
        fieldNotaFiscal.setFuncaoDepoisSelecionar(x -> {
            nf = (PatNotaFiscal)x;
            PreencheDadosNF();
        });
        
        FormBuscaProduto buscaProduto = new FormBuscaProduto();
        buscaProduto.setFrameBloquear(FormPrincipal.getInstance());
        
        FormBuscaPessoa buscaFornecedortwoo = new FormBuscaPessoa();
        buscaFornecedortwoo.setFrameBloquear(FormPrincipal.getInstance());
        
        Date d = new Date();
        fieldDataEntrada.setDate(d);
        atualizarTabela();
    }

    public void zeraCampos() {
        fieldNotaFiscal.setText(null);
        fieldDescrição.setText(null);
        fieldParcelas.setValue(1);
        fieldValorTotal.setValue(0);
        fieldValorParcela.setValue(0);
        fieldDataParcela.setDate(null);
    }

    public void EnabledCampos() {
        fieldParcelas.setEnabled(false);
        fieldValorTotal.setEnabled(false);
        fieldValorParcela.setEnabled(false);
        fieldDataParcela.setEnabled(false);
    }
    
    public void SalvarConta() {
        CarCapContas conta = new CarCapContas();
        CarCapContasDAO contaDAO = new CarCapContasDAO();
        
        
        
        CarcapOperacoesComerciais parcelas = new CarcapOperacoesComerciais();
        carcapOperacoesComerciaisDAO parcelaDAO = new carcapOperacoesComerciaisDAO();
        int numParcelas = (Integer) fieldParcelas.getValue();
        TipoConta tipoConta = TipoConta.Saida;
        parcelas.setTipoDeConta(tipoConta);
        parcelas.setDatLancamento(fieldDataEntrada.getDate());
        parcelas.setDescricao(fieldDescrição.getText());
        parcelas.setNumeroParcela(numParcelas);
        parcelas.setPessoa(nf.getCarPessoa());
        parcelas.setOperacaoNota(nf);
        
        if (fieldNotaFiscal.getValue() != null) {
            parcelas.setOperacaoNota((PatNotaFiscal)fieldNotaFiscal.getValue());
        }
        
        for (int i = 0; i < numParcelas; i++) {
            parcelas.setValorTotal(fieldValorTotal.getValue());
            parcelas.setValorRecebido(fieldValorRecebido.getValue());
            parcelas.setValorPendente(fieldValorTotal.getValue() - fieldValorRecebido.getValue());
            parcelas.setValorParcela(parcelas.getValorPendente() / numParcelas);
            parcelas.setDataParcela(fieldDataParcela.getDate());
                                        
            if (fieldValorRecebido.getValue() >= parcelas.getValorTotal()) {
                parcelas.setContaStatus(StatusConta.Quitada);
            }
            else if ( ((fieldValorRecebido.getValue()) < (parcelas.getValorTotal())) && ((fieldDataParcela.getDate().before(dataAtual)))) {
                parcelas.setContaStatus(StatusConta.PendenteVencida);
            }
            else if ((fieldValorRecebido.getValue() < parcelas.getValorTotal()) && (fieldDataParcela.getDate().after(dataAtual) || fieldDataParcela.getValue().equals(dataAtual))) {
                parcelas.setContaStatus(StatusConta.PendenteEmser);
            }
            
            conta.setCarcapOperacoesComerciais(parcelas);
            contaDAO.insert(conta);
            
           // parcelaDAO.insert(parcelas);
        }
    }

    private void atualizarTabela() {
        DefaultTableModel model = (DefaultTableModel) tableLista.getModel();
        Utils.clearTableModel(model);
        contas = new ArrayList<>();
        for (CarCapContas ativo : contas) {
            model.addRow(contaToArray(ativo));
        }
        tableLista.setModel(model);
    }
    
    private Object[] contaToArray(CarCapContas c) {
        Object[] o = new Object[8];
        o[0] = c.getContaId();
        o[1] = c.getCarcapOperacoesComerciais().getDataParcela();
        o[2] = c.getCarcapOperacoesComerciais().getPessoa();
        o[3] = c.getCarcapOperacoesComerciais().getParcelas();
        o[4] = c.getCarcapOperacoesComerciais().getNumeroParcela();
        o[5] = c.getContaValorTotal();
        o[6] = c.getContaValorPago();
        o[7] = c.getValorPendente();
        return o;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        webPanel1 = new com.alee.laf.panel.WebPanel();
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
        fieldDataParcela = new com.alee.extended.date.WebDateField();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tableLista = new javax.swing.JTable();

        webPanel1.setEnabled(false);

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

        fieldDataParcela.setToolTipText("Informe a data de pagamento da primeira parcela");

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
        fieldValorRecebido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fieldValorRecebidoKeyReleased(evt);
            }
        });

        txtValorPendente.setText("Valor Pendente:");

        fieldValorPendente.setEnabled(false);

        tableLista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Vencimento", "Fornecedor", "Parcelas", "Parcelas Pendentes", "Valor Total", "Valor Pago", "Valor Pendente"
            }
        ));
        jScrollPane1.setViewportView(tableLista);

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
                                    .addComponent(fieldDataParcela, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(webPanel1Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(btnParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        webPanel1Layout.setVerticalGroup(
            webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(webPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                    .addComponent(fieldDataParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(165, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, webPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(webPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(43, 43, 43))))
                    .addGroup(webPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
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
                .addGap(0, 0, Short.MAX_VALUE))
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
        atualizarTabela();
    }//GEN-LAST:event_btnParcelasActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        SalvarConta();
        zeraCampos();
        atualizarTabela();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void ComboBoxFormaPgtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxFormaPgtoActionPerformed
        String b = (String) ComboBoxFormaPgto.getSelectedItem();
        if (b.equals("A Vista")) {
            fieldParcelas.setEnabled(false);
        } else {
            fieldParcelas.setEnabled(true);
        }
    }//GEN-LAST:event_ComboBoxFormaPgtoActionPerformed

    private void fieldParcelasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_fieldParcelasStateChanged
            // TODO add your handling code here:
            System.out.println("parcela alterada");
    }//GEN-LAST:event_fieldParcelasStateChanged

    private void fieldValorRecebidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldValorRecebidoKeyReleased
        
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldValorRecebidoKeyReleased

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
    private com.alee.extended.date.WebDateField fieldDataParcela;
    private javax.swing.JTextField fieldDescrição;
    private components.TextFieldFK fieldNotaFiscal;
    private com.alee.laf.spinner.WebSpinner fieldParcelas;
    private components.TextFieldValorMonetario fieldValorParcela;
    private components.TextFieldValorMonetario fieldValorPendente;
    private components.TextFieldValorMonetario fieldValorRecebido;
    private components.TextFieldValorMonetario fieldValorTotal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableLista;
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
