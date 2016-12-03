
package model;

import enumeraveis.StatusConta;
import enumeraveis.TipoConta;
import enumeraveis.TipoMovimento;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@SequenceGenerator(name = "seq_opComercias", sequenceName = "seq_opComercias",
        allocationSize = 1)
public class CarcapOperacoesComerciais implements java.io.Serializable {

    private int operacoesID;
    private Date datLancamento;
    private Date DataParcela;
    private PatNotaFiscal OperacaoNota;
    private int numeroParcela;
    private EstProduto produto;
    private CarPessoa pessoa;
    private String Descricao;
    private TipoMovimento movimento;
    private double quantidade;
    private TipoConta tipoDeConta;
    private List<CarCapContas> contas;
    private Stack<CarCapContas> parcelas;
    private double ValorTotal;
    private double ValorRecebido;
    private double ValorPendente;
    private double ValorParcela;
    private StatusConta contaStatus;

    public Date getDataParcela() {
        return DataParcela;
    }

    public void setDataParcela(Date DataParcela) {
        this.DataParcela = DataParcela;
    }

    public double getValorParcela() {
        return ValorParcela;
    }

    public void setValorParcela(double ValorParcela) {
        this.ValorParcela = ValorParcela;
    }
    
    public StatusConta getContaStatus() {
        return contaStatus;
    }

    public void setContaStatus(StatusConta contaStatus) {
        this.contaStatus = contaStatus;
    }
    
    public double getValorTotal() {
        return ValorTotal;
    }

    public void setValorTotal(double ValorTotal) {
        this.ValorTotal = ValorTotal;
    }

    public double getValorRecebido() {
        return ValorRecebido;
    }

    public void setValorRecebido(double ValorRecebido) {
        this.ValorRecebido = ValorRecebido;
    }

    public double getValorPendente() {
        return ValorPendente;
    }

    public void setValorPendente(double ValorPendente) {
        this.ValorPendente = ValorPendente;
    }

    public Stack<CarCapContas> getParcelas() {
        return parcelas;
    }

    public void setParcelas(Stack<CarCapContas> parcelas) {
        this.parcelas = parcelas;
    }
    
    public CarcapOperacoesComerciais() {
    }

    public CarcapOperacoesComerciais(int operacoesID, Date datLancamento, int numeroParcela, String Descricao, double quantidade) {
        this.operacoesID = operacoesID;
        this.datLancamento = datLancamento;
        this.numeroParcela = numeroParcela;
        this.Descricao = Descricao;
        this.quantidade = quantidade;
    }

    public CarcapOperacoesComerciais(int operacoesID, Date datLancamento, PatNotaFiscal OperacaoNota, int numeroParcela, EstProduto idProduto, CarPessoa pessoa, String Descricao, TipoMovimento movimento, double quantidade) {
        this.operacoesID = operacoesID;
        this.datLancamento = datLancamento;
        this.OperacaoNota = OperacaoNota;
        this.numeroParcela = numeroParcela;
        this.produto = idProduto;
        this.pessoa = pessoa;
        this.Descricao = Descricao;
        this.movimento = movimento;
        this.quantidade = quantidade;    
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_opComercias")
    public int getOperacoesID() {
        return operacoesID;
    }

    public void setOperacoesID(int operacoesID) {
        this.operacoesID = operacoesID;
    }

    @Temporal(TemporalType.DATE)
    public Date getDatLancamento() {
        return datLancamento;
    }

    public void setDatLancamento(Date datLancamento) {
        this.datLancamento = datLancamento;
    }

    @OneToOne
    public PatNotaFiscal getOperacaoNota() {
        return OperacaoNota;
    }

    public void setOperacaoNota(PatNotaFiscal OperacaoNota) {
        this.OperacaoNota = OperacaoNota;
    }

    public int getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(int numeroParcela) {
        this.numeroParcela = numeroParcela;
    }
    
    @ManyToOne
    public EstProduto getProdutoId() {
        return produto;
    }

    public void setProdutoId(EstProduto produtoId) {
        this.produto = produtoId;
    }

    @OneToOne
    @ManyToOne
    public CarPessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(CarPessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    @Enumerated 
    public TipoMovimento getMovimento() {
        return movimento;
    }

    public void setMovimento(TipoMovimento movimento) {
        this.movimento = movimento;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
    
//    @OneToOne
//      public EstProduto getProduto() {
//        return produto;
//    }
//
//    public void setProduto(EstProduto produto) {
//        this.produto = produto;
//    }

    @Enumerated
    public TipoConta getTipoDeConta() {
        return tipoDeConta;
    }

    public void setTipoDeConta(TipoConta tipoDeConta) {
        this.tipoDeConta = tipoDeConta;
    }

    @OneToMany(mappedBy = "carcapOperacoesComerciais")
    public List<CarCapContas> getContas() {
        return contas;
    }

    public void setContas(List<CarCapContas> contas) {
        this.contas = contas;
    }
     
}