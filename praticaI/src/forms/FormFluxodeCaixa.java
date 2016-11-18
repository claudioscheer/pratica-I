/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.alee.laf.WebLookAndFeel;
import com.alee.laf.desktoppane.WebInternalFrame;
import com.alee.laf.optionpane.WebOptionPane;
import com.alee.laf.table.WebTable;
import com.alee.managers.language.LanguageManager;
import com.alee.managers.notification.NotificationManager;
import com.alee.managers.notification.WebNotification;
import components.panelsListagem.PanelConsultaAtivoImobilizado;
import dao.CarCapContasDAO;
import dao.ExportacaoParaExcel;
import dao.FlxcxFluxoCaixaFechamentoDAO;
import enumeraveis.TipoConta;
import enumeraveis.TipoGrafico;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import model.CarCapContas;
import dao.Graficos;
import dao.PatAtivoImobilizadoDAO;
import java.util.ArrayList;
import javax.swing.SwingWorker;
import model.FlxcxFluxoCaixaFechamento;
import model.PatAtivoImobilizado;
import relatorios.relatorioFluxoDeCaixa.Relatorios;
import net.sf.jasperreports.engine.JRException;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import utils.Utils;

/**
 *
 * @author Alisson Ap
 */
public class FormFluxodeCaixa extends WebInternalFrame {

    /**
     * Creates new form FormFluxodeCaixa
     */
    TipoGrafico tipo;
    String titulo;
    Date dataInicial;
    Date dataFinal;
    int paginaBuscar = 0;

    private final CarCapContasDAO buscarconta = new CarCapContasDAO();
    private List<CarCapContas> contas;

    public FormFluxodeCaixa() {

        super("Fluxo de Caixa", true, true, true, true);

        // estancia o array de contas
        

        this.initComponents();

        //--ajustes data inicial --\\
        Calendar c = Calendar.getInstance();
        dataFinal = c.getTime();
        txtDataFinal.setDate(dataFinal);
        c.add(Calendar.DATE, -1);
        dataInicial = c.getTime();
        txtDataInicial.setDate(dataInicial);
        tipo = TipoGrafico.barras;
        titulo = "Grafico de Barras";
        // - Final ajuste data inicial -\\

        this.contas = this.buscarconta.ListarTodos(dataInicial, dataFinal);
        verificaTipoGrafico(TipoConta.ambos, 1);
        
        grapBarras.setSelected(true);
        grapBarras.setIcon(Utils.getImage(Utils.Image.barraMarcado));

        // Marca as CheckBox \\
        checkbox_Grafico.setSelected(true);
        checkbox_Lista.setSelected(true);
        checkboxEntrada.setSelected(true);
        checkboxSaida.setSelected(true);

//        this.webPanel_Tabela.setSortable(true);
//        this.webPanel_Tabela.setLoadMore(x -> new FormFluxodeCaixa.CarregarContas().execute());
//
//        new CarregarContas().execute();

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        webPanel3 = new com.alee.laf.panel.WebPanel();
        webPanel_Split = new com.alee.laf.splitpane.WebSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        webTable1 = new com.alee.laf.table.WebTable();
        WebPanelGrafico = new com.alee.extended.breadcrumb.WebBreadcrumbPanel();
        webPanel_Tabela = new components.JTableLoadScroll();
        webBreadcrumb1 = new com.alee.extended.breadcrumb.WebBreadcrumb();
        webLabel1 = new com.alee.laf.label.WebLabel();
        webLabel2 = new com.alee.laf.label.WebLabel();
        txtDataInicial = new com.alee.extended.date.WebDateField();
        txtDataFinal = new com.alee.extended.date.WebDateField();
        comboFiltroData = new com.alee.laf.combobox.WebComboBox();
        webLabel4 = new com.alee.laf.label.WebLabel();
        checkboxEntrada = new com.alee.laf.checkbox.WebCheckBox();
        checkboxSaida = new com.alee.laf.checkbox.WebCheckBox();
        webLabel5 = new com.alee.laf.label.WebLabel();
        checkbox_Grafico = new com.alee.laf.checkbox.WebCheckBox();
        checkbox_Lista = new com.alee.laf.checkbox.WebCheckBox();
        webLabel11 = new com.alee.laf.label.WebLabel();
        btnFiltrar = new com.alee.laf.button.WebButton();
        webBreadcrumb2 = new com.alee.extended.breadcrumb.WebBreadcrumb();
        webButton4 = new com.alee.laf.button.WebButton();
        btn_GerarRelatorio = new com.alee.laf.button.WebButton();
        webButton6 = new com.alee.laf.button.WebButton();
        GrapLinhas = new com.alee.laf.checkbox.WebCheckBox();
        grapBarras = new com.alee.laf.checkbox.WebCheckBox();
        grapPizza = new com.alee.laf.checkbox.WebCheckBox();
        btnAdd = new com.alee.laf.button.WebButton();
        webLabel3 = new com.alee.laf.label.WebLabel();
        webBreadcrumb3 = new com.alee.extended.breadcrumb.WebBreadcrumb();
        webLabel7 = new com.alee.laf.label.WebLabel();
        webLabel8 = new com.alee.laf.label.WebLabel();
        webLabel9 = new com.alee.laf.label.WebLabel();
        txtTotalEntradas = new com.alee.laf.text.WebTextField();
        txtTotalSaidas = new com.alee.laf.text.WebTextField();
        txtTotalDisponivel = new com.alee.laf.text.WebTextField();

        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        webPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        webPanel_Split.setAlignmentX(-40.0F);
        webPanel_Split.setPreferredSize(new java.awt.Dimension(7, 1));
        webPanel_Split.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                webPanel_SplitAncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        webPanel_Split.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                webPanel_SplitMouseDragged(evt);
            }
        });
        webPanel_Split.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                webPanel_SplitFocusLost(evt);
            }
        });
        webPanel_Split.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
                webPanel_SplitAncestorMoved1(evt);
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
            }
        });
        webPanel_Split.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                webPanel_SplitMousePressed(evt);
            }
        });
        webPanel_Split.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                webPanel_SplitComponentMoved(evt);
            }
            public void componentResized(java.awt.event.ComponentEvent evt) {
                webPanel_SplitComponentResized(evt);
            }
        });
        webPanel_Split.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                webPanel_SplitPropertyChange(evt);
            }
        });

        webTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(webTable1);

        webPanel_Split.setLeftComponent(jScrollPane1);

        WebPanelGrafico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        webPanel_Split.setLeftComponent(WebPanelGrafico);
        webPanel_Split.setRightComponent(webPanel_Tabela);

        webPanel3.add(webPanel_Split, java.awt.BorderLayout.CENTER);

        getContentPane().add(webPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(117, 79, 1220, 470));

        webLabel1.setText("Data Inicial:");

        webLabel2.setText("Data Final:");

        txtDataInicial.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtDataInicialCaretUpdate(evt);
            }
        });
        txtDataInicial.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txtDataInicialAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        txtDataInicial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDataInicialFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDataInicialFocusLost(evt);
            }
        });
        txtDataInicial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDataInicialMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtDataInicialMouseEntered(evt);
            }
        });
        txtDataInicial.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtDataInicialInputMethodTextChanged(evt);
            }
        });
        txtDataInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataInicialActionPerformed(evt);
            }
        });
        txtDataInicial.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtDataInicialPropertyChange(evt);
            }
        });
        txtDataInicial.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                txtDataInicialVetoableChange(evt);
            }
        });

        txtDataFinal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDataFinalFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDataFinalFocusLost(evt);
            }
        });

        comboFiltroData.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Diário", "Mensal", "Anual" }));
        comboFiltroData.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboFiltroDataItemStateChanged(evt);
            }
        });
        comboFiltroData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFiltroDataActionPerformed(evt);
            }
        });

        webLabel4.setText("Tipo de Operação");
        webLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        checkboxEntrada.setText("Entrada");
        checkboxEntrada.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                checkboxEntradaStateChanged(evt);
            }
        });
        checkboxEntrada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkboxEntradaMouseClicked(evt);
            }
        });

        checkboxSaida.setText("Saída");
        checkboxSaida.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                checkboxSaidaStateChanged(evt);
            }
        });
        checkboxSaida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkboxSaidaMouseClicked(evt);
            }
        });

        webLabel5.setText("Visualização");
        webLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        checkbox_Grafico.setText("Gráfico");
        checkbox_Grafico.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                checkbox_GraficoStateChanged(evt);
            }
        });
        checkbox_Grafico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkbox_GraficoMouseClicked(evt);
            }
        });

        checkbox_Lista.setText("Lista");
        checkbox_Lista.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                checkbox_ListaStateChanged(evt);
            }
        });
        checkbox_Lista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkbox_ListaMouseClicked(evt);
            }
        });

        webLabel11.setText("Forma de visualização:");

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout webBreadcrumb1Layout = new javax.swing.GroupLayout(webBreadcrumb1);
        webBreadcrumb1.setLayout(webBreadcrumb1Layout);
        webBreadcrumb1Layout.setHorizontalGroup(
            webBreadcrumb1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(webBreadcrumb1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(webLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(txtDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(webLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(txtDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(webLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(comboFiltroData, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addGroup(webBreadcrumb1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(webLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(webBreadcrumb1Layout.createSequentialGroup()
                        .addComponent(checkboxEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(checkboxSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(webBreadcrumb1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(webLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(webBreadcrumb1Layout.createSequentialGroup()
                        .addComponent(checkbox_Grafico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(checkbox_Lista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(66, 66, 66)
                .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        webBreadcrumb1Layout.setVerticalGroup(
            webBreadcrumb1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, webBreadcrumb1Layout.createSequentialGroup()
                .addGroup(webBreadcrumb1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(webBreadcrumb1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(webLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(webBreadcrumb1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkbox_Grafico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkbox_Lista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, webBreadcrumb1Layout.createSequentialGroup()
                        .addGroup(webBreadcrumb1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(webBreadcrumb1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(webLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(webBreadcrumb1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(txtDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(webBreadcrumb1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(webLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(webBreadcrumb1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(txtDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(webBreadcrumb1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(webBreadcrumb1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(webBreadcrumb1Layout.createSequentialGroup()
                                        .addComponent(webLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5)
                                        .addGroup(webBreadcrumb1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(checkboxEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(checkboxSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(webBreadcrumb1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(webLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(comboFiltroData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, webBreadcrumb1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnFiltrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        getContentPane().add(webBreadcrumb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(117, 11, 1220, 61));

        webBreadcrumb2.setBackground(new java.awt.Color(0, 204, 255));
        webBreadcrumb2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        webBreadcrumb2.setForeground(new java.awt.Color(0, 204, 255));

        webButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/head (1).png"))); // NOI18N
        webButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webButton4ActionPerformed(evt);
            }
        });

        btn_GerarRelatorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/reports-icon.png"))); // NOI18N
        btn_GerarRelatorio.setToolTipText("Gerar relatório em PDF");
        btn_GerarRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GerarRelatorioActionPerformed(evt);
            }
        });

        webButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/businesswoman.png"))); // NOI18N
        webButton6.setToolTipText("Gerar relatório em Excel");
        webButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webButton6ActionPerformed(evt);
            }
        });

        GrapLinhas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/linhasDesmarcado.png"))); // NOI18N
        GrapLinhas.setToolTipText("Gráfio de Linhas");
        GrapLinhas.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                GrapLinhasStateChanged(evt);
            }
        });
        GrapLinhas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GrapLinhasMouseClicked(evt);
            }
        });
        GrapLinhas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GrapLinhasActionPerformed(evt);
            }
        });

        grapBarras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/barraDesmarcado.png"))); // NOI18N
        grapBarras.setToolTipText("Gráfico de Barras");
        grapBarras.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                grapBarrasStateChanged(evt);
            }
        });
        grapBarras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grapBarrasMouseClicked(evt);
            }
        });
        grapBarras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grapBarrasActionPerformed(evt);
            }
        });

        grapPizza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pizzaDesmarcado.png"))); // NOI18N
        grapPizza.setToolTipText("Gráfico de Pizza");
        grapPizza.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                grapPizzaStateChanged(evt);
            }
        });
        grapPizza.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grapPizzaMouseClicked(evt);
            }
        });
        grapPizza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grapPizzaActionPerformed(evt);
            }
        });

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/shopping-bag (1).png"))); // NOI18N
        btnAdd.setToolTipText("Adicionar saldo incial");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        webLabel3.setText("GRÁFICOS");
        webLabel3.setFont(new java.awt.Font("Segoe UI Semilight", 1, 11)); // NOI18N

        javax.swing.GroupLayout webBreadcrumb2Layout = new javax.swing.GroupLayout(webBreadcrumb2);
        webBreadcrumb2.setLayout(webBreadcrumb2Layout);
        webBreadcrumb2Layout.setHorizontalGroup(
            webBreadcrumb2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(webBreadcrumb2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(webBreadcrumb2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, webBreadcrumb2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(webBreadcrumb2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(webButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(webBreadcrumb2Layout.createSequentialGroup()
                        .addGroup(webBreadcrumb2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(webButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_GerarRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(grapPizza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(webBreadcrumb2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(webLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(webBreadcrumb2Layout.createSequentialGroup()
                                    .addComponent(grapBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(GrapLinhas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        webBreadcrumb2Layout.setVerticalGroup(
            webBreadcrumb2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(webBreadcrumb2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(btn_GerarRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(webButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(webButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(webLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(webBreadcrumb2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(grapBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GrapLinhas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(grapPizza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        getContentPane().add(webBreadcrumb2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 100, 600));

        webBreadcrumb3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        webLabel7.setText("Total de Entradas:");

        webLabel8.setText("Total de Saídas:");

        webLabel9.setText("Total:");

        javax.swing.GroupLayout webBreadcrumb3Layout = new javax.swing.GroupLayout(webBreadcrumb3);
        webBreadcrumb3.setLayout(webBreadcrumb3Layout);
        webBreadcrumb3Layout.setHorizontalGroup(
            webBreadcrumb3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(webBreadcrumb3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(webLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(webLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalSaidas, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(webLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTotalDisponivel, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(611, Short.MAX_VALUE))
        );
        webBreadcrumb3Layout.setVerticalGroup(
            webBreadcrumb3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, webBreadcrumb3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(webBreadcrumb3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(webLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(webLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(webLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalSaidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalDisponivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        getContentPane().add(webBreadcrumb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 560, 1220, 51));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDataInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataInicialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataInicialActionPerformed

    public class CarregarContas extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() throws Exception {

            contas = new ArrayList<CarCapContas>();

            CarCapContasDAO buscar = new CarCapContasDAO();

            contas = buscar.ListarTodosPaginacao(paginaBuscar++, dataInicial, dataFinal);

            CarregarGraficoJTable(null, null, 3, TipoConta.ambos, dataInicial, dataFinal);

            atualizarTabela();

            return null;
        }

        @Override
        protected void done() {
            super.done(); //To change body of generated methods, choose Tools | Templates.
        }

    }

    private void atualizarTabela() {
        DefaultTableModel model = (DefaultTableModel) this.webPanel_Tabela.getModel();
        Utils.clearTableModel(model);

        this.contas.forEach(x -> {
            model.addRow(ativoToArray(x));
        });

        this.webPanel_Tabela.setModel(model);
    }

    private Object[] ativoToArray(CarCapContas conta) {

        Object[] o = new Object[6];
        o[0] = conta.getContaId();
        o[1] = conta.getContaStatus();
        o[2] = conta.getContaDataEmissao();
        o[3] = conta.getContaTipo();
        o[4] = conta.getContaValorTotal();
        o[5] = conta.getContaValorPago();

        return o;
    }

    public List<CarCapContas> CarregarGraficoJTable(String nome, TipoGrafico tipografico, int posicao, TipoConta tipoconta, Date DataInicial, Date DataFinal) {

        //Calendar c2 = Calendar.getInstance();
        // posicao = 1 (esquerda), posicao = 2 (direita), posicao 3 = JTable direita
        if (posicao == 1) {

            if (tipografico.equals(TipoGrafico.barras) || tipografico.equals(TipoGrafico.pizza)) {

                Graficos grap = new Graficos(tipo, this.titulo);
                JFreeChart j = null;

                if (tipografico.equals(TipoGrafico.barras)) {

                    j = grap.GraficoBarras(contas, "Entradas X Saidas");

                } else if (tipografico.equals(TipoGrafico.pizza)) {

                    j = grap.GraficoPizza("Gráfico de Pizza", contas);

                }

                ChartPanel chart = new ChartPanel(j);

                WebPanelGrafico.removeAll();

                webPanel_Split.validate();

                int largura = WebPanelGrafico.getWidth();
                int altura = WebPanelGrafico.getHeight();

                WebPanelGrafico.setBounds(0, 0, largura, altura);

                chart.setBounds(0, 0, largura, altura);
                chart.addPropertyChangeListener(null);

                WebPanelGrafico.add(chart);

                WebPanelGrafico.revalidate();
                WebPanelGrafico.repaint();

            } else {

                Graficos grap = new Graficos(tipo, this.titulo);

                ChartPanel c = grap.informarDadosGrafico("grafico", contas);

                WebPanelGrafico.removeAll();

                webPanel_Split.validate();

                int largura = WebPanelGrafico.getWidth();
                int altura = WebPanelGrafico.getHeight();

                WebPanelGrafico.setBounds(0, 0, largura, altura);

                c.setBounds(0, 0, largura, altura);
                c.addPropertyChangeListener(null);

                WebPanelGrafico.add(c);

                WebPanelGrafico.revalidate();
                WebPanelGrafico.repaint();

            }

        } else if (posicao == 2) {

            if (tipografico.equals(TipoGrafico.barras) || tipografico.equals(TipoGrafico.pizza)) {

                Graficos grap = new Graficos(tipo, this.titulo);
                JFreeChart j = null;

                if (tipografico.equals(TipoGrafico.barras)) {

                    j = grap.GraficoBarras(contas, "Entradas X Saidas");

                } else if (tipografico.equals(TipoGrafico.pizza)) {

                    j = grap.GraficoPizza("Gráfico de Pizza", contas);

                }

                ChartPanel chart = new ChartPanel(j);

                webPanel_Tabela.removeAll();

                webPanel_Split.validate();

                int largura = webPanel_Tabela.getWidth();
                int altura = webPanel_Tabela.getHeight();

                webPanel_Tabela.setBounds(0, 0, largura, altura);

                chart.setBounds(0, 0, largura, altura);

                webPanel_Tabela.add(chart);

                webPanel_Tabela.revalidate();
                webPanel_Tabela.repaint();

            } else {

                Graficos grap = new Graficos(tipo, this.titulo);

                ChartPanel c = grap.informarDadosGrafico("grafico", contas);

                webPanel_Tabela.removeAll();

                webPanel_Split.validate();

                int largura = webPanel_Tabela.getWidth();
                int altura = webPanel_Tabela.getHeight();

                webPanel_Tabela.setBounds(0, 0, largura, altura);

                c.setBounds(0, 0, largura, altura);

                webPanel_Tabela.add(c);

                webPanel_Tabela.revalidate();
                webPanel_Tabela.repaint();

            }

        } else if (posicao == 3) {

            double totalEntradas = 0;
            double totalSaidas = 0;
            double totalDisponivel = 0;

            WebTable tablenovo = new WebTable();

            DefaultTableModel modelTabela = new DefaultTableModel(new Object[]{"Id", "Status", "Data", "Tipo", "Valor Total", "Valor Pago"}, 0);

            for (CarCapContas i : contas) {

                Object[] data = {i.getContaId(), i.getCapContaStatus(), i.getContaDataEmissao(), i.getContaTipo(), i.getContaValorTotal(), i.getContaValorPago()};

                modelTabela.addRow(data);

                if (i.getContaTipo().equals(TipoConta.Entrada)) {

                    totalEntradas += i.getContaValorPago();

                } else if (i.getContaTipo().equals(TipoConta.Saida)) {

                    totalSaidas += i.getContaValorPago();

                }
            }

            txtTotalEntradas.setText(String.valueOf(Utils.format(totalEntradas)));
            txtTotalSaidas.setText(String.valueOf(Utils.format(totalSaidas)));

            tablenovo.setModel(modelTabela);

            JScrollPane tableContainer = new JScrollPane(tablenovo);

            webPanel_Tabela.removeAll();

            webPanel_Split.validate();

            int largura = webPanel_Tabela.getWidth();
            int altura = webPanel_Tabela.getHeight();

            webPanel_Tabela.setBounds(0, 0, largura, altura);

            tableContainer.setBounds(0, 0, largura, altura);

            webPanel_Tabela.add(tableContainer);

            webPanel_Tabela.revalidate();
            webPanel_Tabela.repaint();

        } else if (posicao == 4) {

            return contas;

        }

        return null;
    }

    public List<CarCapContas> verificaTipoGrafico(TipoConta tipoconta, int opcao) {

        if (GrapLinhas.isSelected() && !grapBarras.isSelected() && !grapPizza.isSelected()) {

            this.titulo = "Gráfico de Linhas";
            this.tipo = TipoGrafico.linear;
            CarregarGraficoJTable(titulo, tipo, 1, tipoconta, txtDataInicial.getDate(), txtDataFinal.getDate());

            // Recarrega a WebPanel
            CarregarGraficoJTable("", null, 3, tipoconta, txtDataInicial.getDate(), txtDataFinal.getDate());

            checkbox_Lista.setSelected(true);

        } else if (!GrapLinhas.isSelected() && grapBarras.isSelected() && !grapPizza.isSelected()) {

            this.titulo = "Entradas X Saídas por mês";
            this.tipo = TipoGrafico.barras;
            CarregarGraficoJTable(titulo, tipo, 1, tipoconta, txtDataInicial.getDate(), txtDataFinal.getDate());

            CarregarGraficoJTable("", null, 3, tipoconta, txtDataInicial.getDate(), txtDataFinal.getDate());

            checkbox_Lista.setSelected(true);

        } else if (!GrapLinhas.isSelected() && !grapBarras.isSelected() && grapPizza.isSelected()) {

            this.titulo = "Gráfico de Pizza";
            this.tipo = TipoGrafico.pizza;
            CarregarGraficoJTable(titulo, tipo, 1, tipoconta, txtDataInicial.getDate(), txtDataFinal.getDate());

            CarregarGraficoJTable("", null, 3, tipoconta, txtDataInicial.getDate(), txtDataFinal.getDate());

            checkbox_Lista.setSelected(true);

        } else if (GrapLinhas.isSelected() && grapBarras.isSelected() && !grapPizza.isSelected() && checkbox_Grafico.isSelected()) {

            //  webPanel_Split.setDividerLocation(.5f);
            this.titulo = "Gráfico de Linhas";
            this.tipo = TipoGrafico.linear;
            CarregarGraficoJTable(titulo, tipo, 1, tipoconta, txtDataInicial.getDate(), txtDataFinal.getDate());

            this.titulo = "Gráfico de Barras";
            this.tipo = TipoGrafico.barras;
            CarregarGraficoJTable(titulo, tipo, 2, tipoconta, txtDataInicial.getDate(), txtDataFinal.getDate());

            checkbox_Lista.setSelected(false);

        } else if (!GrapLinhas.isSelected() && grapBarras.isSelected() && grapPizza.isSelected() && checkbox_Grafico.isSelected()) {

            this.titulo = "Gráfico de Barras";
            this.tipo = TipoGrafico.barras;
            CarregarGraficoJTable(titulo, tipo, 1, tipoconta, txtDataInicial.getDate(), txtDataFinal.getDate());

            this.titulo = "Gráfico de Pizza";
            this.tipo = TipoGrafico.pizza;
            CarregarGraficoJTable(titulo, tipo, 2, tipoconta, txtDataInicial.getDate(), txtDataFinal.getDate());

            checkbox_Lista.setSelected(false);

        } else if (GrapLinhas.isSelected() && !grapBarras.isSelected() && grapPizza.isSelected() && checkbox_Grafico.isSelected()) {

            this.titulo = "Gráfico de Linhas";
            this.tipo = TipoGrafico.linear;
            CarregarGraficoJTable(titulo, tipo, 1, tipoconta, txtDataInicial.getDate(), txtDataFinal.getDate());

            this.titulo = "Gráfico de Pizza";
            this.tipo = TipoGrafico.pizza;
            CarregarGraficoJTable(titulo, tipo, 2, tipoconta, txtDataInicial.getDate(), txtDataFinal.getDate());

            checkbox_Lista.setSelected(false);

        } else if (!GrapLinhas.isSelected() && !grapBarras.isSelected() && !grapPizza.isSelected() && checkbox_Grafico.isSelected() && checkbox_Lista.isSelected()) {

            CarregarGraficoJTable(titulo, tipo, 1, TipoConta.ambos, dataInicial, dataFinal);
            CarregarGraficoJTable(null, null, 3, TipoConta.ambos, dataInicial, dataFinal);

        } else {

            CarregarGraficoJTable(null, null, 3, tipoconta, txtDataInicial.getDate(), txtDataFinal.getDate());

        }

        if (opcao == 1) {

            return CarregarGraficoJTable(null, null, 4, tipoconta, txtDataInicial.getDate(), txtDataFinal.getDate());
        }

        return null;
    }

    public void verificaSpliPanel() {

        if (checkbox_Grafico.isSelected()) {

            if (checkbox_Lista.isSelected()) {

                webPanel_Split.setDividerLocation(.5f);

            } else {

                if (!grapPizza.isSelected() && !grapBarras.isSelected() && !GrapLinhas.isSelected()) {
                    webPanel_Split.setDividerLocation(1210);
                } else {

                    // if(grapPizza.isSelected() || grapBarras.isSelected() || GrapLinhas.isSelected())
                    checkbox_Lista.setSelected(true);
                    webPanel_Split.setDividerLocation(.5f);
                }

            }

        } else {

            if (checkbox_Lista.isSelected()) {

                webPanel_Split.setDividerLocation(1);

            } else {
//                if(!checkbox_Lista.getActionCommand().equals("Lista")) {

                webPanel_Split.setDividerLocation(1);
                checkbox_Lista.setSelected(true);

                CarregarNotificacao("Filtro por Lista aplicado automaticamente!");

            }

        }

    }

    public void CarregarNotificacao(String msg) {

        WebNotification notificationPopup = new WebNotification();
        notificationPopup.setDisplayTime(5000);

        notificationPopup.setContent(msg);

        NotificationManager.showNotification(notificationPopup);

    }

    public boolean MensagensConfirmacao(String msg, String tituloPainel) {

        int resposta = WebOptionPane.showConfirmDialog(null, msg, tituloPainel, WebOptionPane.YES_NO_OPTION,
                WebOptionPane.QUESTION_MESSAGE);

        if (resposta == WebOptionPane.YES_OPTION) {

            return true;

        } else {

            return false;

        }

    }

    public int verificaMarcados() {

        int conta = 0;

        if (GrapLinhas.isSelected()) {

            conta++;

        }

        if (grapBarras.isSelected()) {

            conta++;

        }

        if (grapPizza.isSelected()) {

            conta++;
        }

        return conta;

    }

    public TipoConta verificaTipodeConta() {

        if (checkboxEntrada.isSelected() && checkboxSaida.isSelected()) {

            verificaTipoGrafico(TipoConta.ambos, 0);

            return TipoConta.ambos;

        } else if (!checkboxEntrada.isSelected() && checkboxSaida.isSelected()) {

            verificaTipoGrafico(TipoConta.Saida, 0);

            return TipoConta.Saida;

        } else if (checkboxEntrada.isSelected() && !checkboxSaida.isSelected()) {

            verificaTipoGrafico(TipoConta.Entrada, 0);

            return TipoConta.Entrada;

        }

        return null;
    }

    public void verificaFiltroData() {

        int posicao = comboFiltroData.getSelectedIndex();

        System.out.println("posicao" + posicao);

        Calendar c = Calendar.getInstance();

        switch (posicao) {

            case 0:

                txtDataInicial.setDate(c.getTime());
                txtDataFinal.setDate(c.getTime());

                break;

            case 1:

                c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

                txtDataInicial.setDate(c.getTime());

                c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

                txtDataFinal.setDate(c.getTime());

                break;

            case 2:

                c.set(Calendar.DAY_OF_MONTH, 1);

                txtDataInicial.setDate(c.getTime());

                c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));

                txtDataFinal.setDate(c.getTime());

                break;

        }

    }

    public void Excel() {

        JFileChooser fileChooser = new JFileChooser();

        if (fileChooser.showSaveDialog(fileChooser.getComponent(0)) == JFileChooser.APPROVE_OPTION) { //Exibe janela onde salvar o arquivo HTML

            File file = fileChooser.getSelectedFile(); //Pega o caminho completo

            String caminho;
            if (file.getPath().contains(".xls")) {

                caminho = file.getPath();

            } else {
                caminho = file.getPath() + ".xls";

            }

            new ExportacaoParaExcel().Exportar(caminho, dataInicial, dataFinal, comboFiltroData.getSelectedIndex());

        }

    }

//    private void BuscaValoresTotais(){
//        CarCapContasDAO contasDao = new CarCapContasDAO();
//        double totalEntrada = contasDao.SomarContas(TipoConta.Entrada, dataInicial, dataFinal);
//        double totalSaida = contasDao.SomarContas(TipoConta.Saida, dataInicial, dataFinal);
//        double saldo = totalEntrada - totalSaida;
//        
//        txtTotalEntradas.setText(String.valueOf(totalEntrada));
//        txtTotalSaidas.setText(String.valueOf(totalSaida));
//        
//        txtTotalDisponivel.setText(String.valueOf(saldo));
//        
//    }

    private void checkbox_GraficoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_checkbox_GraficoStateChanged

        verificaSpliPanel();
        verificaTipodeConta();

        if (!checkbox_Grafico.isSelected()) {

            grapBarras.setSelected(false);
            grapPizza.setSelected(false);
            GrapLinhas.setSelected(false);

            checkbox_Lista.setSelected(true);
        }


    }//GEN-LAST:event_checkbox_GraficoStateChanged

    private void checkbox_ListaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_checkbox_ListaStateChanged

        verificaSpliPanel();


    }//GEN-LAST:event_checkbox_ListaStateChanged

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened

        verificaSpliPanel();

    }//GEN-LAST:event_formInternalFrameOpened

    private void btn_GerarRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GerarRelatorioActionPerformed

        boolean retornoMetodo = MensagensConfirmacao("O relatório será gerado de acordo com os filtros aplicados em tela, deseja prosseguir?", "Tela de Confirmação");

        if (retornoMetodo) {

            // Passa segundo parametro como 1, para apenas retornas os dados em ArrayList para preencher o relatório
            List<CarCapContas> data = verificaTipoGrafico(verificaTipodeConta(), 1);

            Relatorios report = new Relatorios();

            try {
                report.RelatorioFluxoCaixa(data, txtDataInicial.getDate(), txtDataFinal.getDate());
            } catch (JRException ex) {
                Logger.getLogger(FormFluxodeCaixa.class.getName()).log(Level.SEVERE, null, ex);
            }

            CarregarNotificacao("Relatório gerado com sucesso!");

        }


    }//GEN-LAST:event_btn_GerarRelatorioActionPerformed

    private void GrapLinhasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GrapLinhasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GrapLinhasActionPerformed

    private void grapBarrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grapBarrasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grapBarrasActionPerformed

    private void grapPizzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grapPizzaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grapPizzaActionPerformed

    private void webPanel_SplitComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_webPanel_SplitComponentResized

    }//GEN-LAST:event_webPanel_SplitComponentResized

    private void webPanel_SplitComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_webPanel_SplitComponentMoved

    }//GEN-LAST:event_webPanel_SplitComponentMoved

    private void webPanel_SplitAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_webPanel_SplitAncestorMoved

    }//GEN-LAST:event_webPanel_SplitAncestorMoved

    private void webPanel_SplitFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_webPanel_SplitFocusLost

    }//GEN-LAST:event_webPanel_SplitFocusLost

    private void webPanel_SplitAncestorMoved1(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_webPanel_SplitAncestorMoved1

    }//GEN-LAST:event_webPanel_SplitAncestorMoved1

    private void webPanel_SplitPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_webPanel_SplitPropertyChange

        verificaTipoGrafico(verificaTipodeConta(), 0);

    }//GEN-LAST:event_webPanel_SplitPropertyChange


    private void webPanel_SplitMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_webPanel_SplitMouseDragged
        // ajustaGrafico();
    }//GEN-LAST:event_webPanel_SplitMouseDragged

    private void webPanel_SplitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_webPanel_SplitMousePressed

    }//GEN-LAST:event_webPanel_SplitMousePressed

    private void GrapLinhasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_GrapLinhasStateChanged

        int marcados = verificaMarcados();

        if (marcados <= 2) {

            verificaTipoGrafico(verificaTipodeConta(), 0);

            if (GrapLinhas.isSelected()) {

                GrapLinhas.setIcon(null);
                GrapLinhas.setIcon(Utils.getImage(Utils.Image.linhasMarcado));
                checkbox_Grafico.setSelected(true);
            } else {

                GrapLinhas.setIcon(null);
                GrapLinhas.setIcon(Utils.getImage(Utils.Image.linhasDesmarcado));

                if (marcados == 0) {

                    checkbox_Grafico.setSelected(false);

                }

            }

        } else if (GrapLinhas.isSelected()) {

            CarregarNotificacao("Só é possível visualizar 2 gráficos por vez!");

            GrapLinhas.setSelected(false);

            GrapLinhas.setIcon(null);
            GrapLinhas.setIcon(Utils.getImage(Utils.Image.linhasDesmarcado));

        }
    }//GEN-LAST:event_GrapLinhasStateChanged

    private void grapBarrasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_grapBarrasStateChanged

        int marcados = verificaMarcados();

        if (marcados <= 2) {

            verificaTipoGrafico(verificaTipodeConta(), 0);

            if (grapBarras.isSelected()) {

                grapBarras.setIcon(null);
                grapBarras.setIcon(Utils.getImage(Utils.Image.barraMarcado));
                checkbox_Grafico.setSelected(true);

            } else {

                grapBarras.setIcon(null);
                grapBarras.setIcon(Utils.getImage(Utils.Image.barraDesmarcado));

                if (marcados == 0) {

                    checkbox_Grafico.setSelected(false);

                }

            }

        } else if (grapBarras.isSelected()) {

            CarregarNotificacao("Só é possível visualizar 2 gráficos por vez!");

            grapBarras.setSelected(false);
            grapBarras.setIcon(Utils.getImage(Utils.Image.barraDesmarcado));

        }
    }//GEN-LAST:event_grapBarrasStateChanged

    private void grapPizzaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_grapPizzaStateChanged

        //   verificaSpliPanel();
        int marcados = verificaMarcados();

        if (marcados <= 2) {

            verificaTipoGrafico(verificaTipodeConta(), 0);

            if (grapPizza.isSelected()) {

                grapPizza.setIcon(null);
                grapPizza.setIcon(Utils.getImage(Utils.Image.pizzaMarcado));
                checkbox_Grafico.setSelected(true);
            } else {

                grapPizza.setIcon(null);
                grapPizza.setIcon(Utils.getImage(Utils.Image.pizzaDesmarcado));

                if (marcados == 0) {

                    checkbox_Grafico.setSelected(false);

                }
            }

        } else if (grapPizza.isSelected()) {

            CarregarNotificacao("Só é possível visualizar 2 gráficos por vez!");

            grapPizza.setSelected(false);
            grapPizza.setIcon(null);
            grapPizza.setIcon(Utils.getImage(Utils.Image.pizzaDesmarcado));
        }


    }//GEN-LAST:event_grapPizzaStateChanged

    private void checkboxEntradaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_checkboxEntradaStateChanged


    }//GEN-LAST:event_checkboxEntradaStateChanged

    private void checkboxSaidaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_checkboxSaidaStateChanged


    }//GEN-LAST:event_checkboxSaidaStateChanged

    private void checkboxEntradaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkboxEntradaMouseClicked

//        verificaTipoGrafico(verificaTipodeConta(), 0);

    }//GEN-LAST:event_checkboxEntradaMouseClicked

    private void checkboxSaidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkboxSaidaMouseClicked

//        verificaTipoGrafico(verificaTipodeConta(), 0);

    }//GEN-LAST:event_checkboxSaidaMouseClicked

    private void checkbox_GraficoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkbox_GraficoMouseClicked


    }//GEN-LAST:event_checkbox_GraficoMouseClicked

    private void checkbox_ListaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkbox_ListaMouseClicked


    }//GEN-LAST:event_checkbox_ListaMouseClicked

    private void GrapLinhasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GrapLinhasMouseClicked

        //verificaSpliPanel();

    }//GEN-LAST:event_GrapLinhasMouseClicked


    private void grapBarrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grapBarrasMouseClicked

        //verificaSpliPanel();

    }//GEN-LAST:event_grapBarrasMouseClicked

    private void grapPizzaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grapPizzaMouseClicked


    }//GEN-LAST:event_grapPizzaMouseClicked

    private void txtDataInicialCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDataInicialCaretUpdate

    }//GEN-LAST:event_txtDataInicialCaretUpdate

    private void txtDataInicialInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtDataInicialInputMethodTextChanged

    }//GEN-LAST:event_txtDataInicialInputMethodTextChanged

    private void txtDataInicialPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtDataInicialPropertyChange

    }//GEN-LAST:event_txtDataInicialPropertyChange

    private void txtDataInicialVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_txtDataInicialVetoableChange

    }//GEN-LAST:event_txtDataInicialVetoableChange

    private void txtDataInicialMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDataInicialMouseEntered

    }//GEN-LAST:event_txtDataInicialMouseEntered

    private void txtDataInicialAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtDataInicialAncestorAdded

    }//GEN-LAST:event_txtDataInicialAncestorAdded

    private void txtDataInicialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDataInicialMouseClicked


    }//GEN-LAST:event_txtDataInicialMouseClicked

    private void txtDataInicialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDataInicialFocusLost

//        verificaTipoGrafico(verificaTipodeConta(), 0);

    }//GEN-LAST:event_txtDataInicialFocusLost

    private void txtDataInicialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDataInicialFocusGained

//        verificaTipoGrafico(verificaTipodeConta(), 0);
    }//GEN-LAST:event_txtDataInicialFocusGained

    private void txtDataFinalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDataFinalFocusGained

//        verificaTipoGrafico(verificaTipodeConta(), 0);
    }//GEN-LAST:event_txtDataFinalFocusGained

    private void txtDataFinalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDataFinalFocusLost

//        verificaTipoGrafico(verificaTipodeConta(), 0);

    }//GEN-LAST:event_txtDataFinalFocusLost

    private void comboFiltroDataItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboFiltroDataItemStateChanged

        verificaFiltroData();
//        verificaTipoGrafico(verificaTipodeConta(), 0);

    }//GEN-LAST:event_comboFiltroDataItemStateChanged

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
//        verificaFiltroData();
        TipoConta tipoConta = verificaTipodeConta();

        dataInicial = txtDataInicial.getDate();
        dataFinal = txtDataFinal.getDate();

        if (tipoConta == TipoConta.ambos) {
            contas = this.buscarconta.ListarTodos(dataInicial, dataFinal);
        } else {
            contas = this.buscarconta.ListarContas(tipoConta, dataInicial, dataFinal);
        }

        verificaTipoGrafico(tipoConta, 0);
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        FormAddSaldoInicial formSaldo = new FormAddSaldoInicial(null, rootPaneCheckingEnabled);
        formSaldo.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void comboFiltroDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFiltroDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboFiltroDataActionPerformed

    private void webButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webButton6ActionPerformed
        Excel();
    }//GEN-LAST:event_webButton6ActionPerformed

    private void webButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webButton4ActionPerformed

        Calendar cIni = Calendar.getInstance();
                
        
        cIni.set(Calendar.DAY_OF_MONTH, 1);

        Calendar cFim = Calendar.getInstance();

        cFim.set(Calendar.DAY_OF_MONTH, cFim.getActualMaximum(Calendar.DAY_OF_MONTH));

        

        new FlxcxFluxoCaixaFechamentoDAO().FecharCaixa(cIni.getTime(), cFim.getTime());
    }//GEN-LAST:event_webButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {

            WebLookAndFeel.setDecorateAllWindows(true);
            WebLookAndFeel.setDecorateDialogs(true);
            WebLookAndFeel.setDecorateFrames(true);

            LanguageManager.setDefaultLanguage(LanguageManager.PORTUGUESE);

            WebLookAndFeel.install();

            FormFluxodeCaixa fluxo = new FormFluxodeCaixa();
            fluxo.setVisible(true);

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.checkbox.WebCheckBox GrapLinhas;
    private com.alee.extended.breadcrumb.WebBreadcrumbPanel WebPanelGrafico;
    private com.alee.laf.button.WebButton btnAdd;
    private com.alee.laf.button.WebButton btnFiltrar;
    private com.alee.laf.button.WebButton btn_GerarRelatorio;
    private com.alee.laf.checkbox.WebCheckBox checkboxEntrada;
    private com.alee.laf.checkbox.WebCheckBox checkboxSaida;
    private com.alee.laf.checkbox.WebCheckBox checkbox_Grafico;
    private com.alee.laf.checkbox.WebCheckBox checkbox_Lista;
    private com.alee.laf.combobox.WebComboBox comboFiltroData;
    private com.alee.laf.checkbox.WebCheckBox grapBarras;
    private com.alee.laf.checkbox.WebCheckBox grapPizza;
    private javax.swing.JScrollPane jScrollPane1;
    private com.alee.extended.date.WebDateField txtDataFinal;
    private com.alee.extended.date.WebDateField txtDataInicial;
    private com.alee.laf.text.WebTextField txtTotalDisponivel;
    private com.alee.laf.text.WebTextField txtTotalEntradas;
    private com.alee.laf.text.WebTextField txtTotalSaidas;
    private com.alee.extended.breadcrumb.WebBreadcrumb webBreadcrumb1;
    private com.alee.extended.breadcrumb.WebBreadcrumb webBreadcrumb2;
    private com.alee.extended.breadcrumb.WebBreadcrumb webBreadcrumb3;
    private com.alee.laf.button.WebButton webButton4;
    private com.alee.laf.button.WebButton webButton6;
    private com.alee.laf.label.WebLabel webLabel1;
    private com.alee.laf.label.WebLabel webLabel11;
    private com.alee.laf.label.WebLabel webLabel2;
    private com.alee.laf.label.WebLabel webLabel3;
    private com.alee.laf.label.WebLabel webLabel4;
    private com.alee.laf.label.WebLabel webLabel5;
    private com.alee.laf.label.WebLabel webLabel7;
    private com.alee.laf.label.WebLabel webLabel8;
    private com.alee.laf.label.WebLabel webLabel9;
    private com.alee.laf.panel.WebPanel webPanel3;
    private com.alee.laf.splitpane.WebSplitPane webPanel_Split;
    private components.JTableLoadScroll webPanel_Tabela;
    private com.alee.laf.table.WebTable webTable1;
    // End of variables declaration//GEN-END:variables

    private FormAddSaldoInicial formAddSaldo;

}
