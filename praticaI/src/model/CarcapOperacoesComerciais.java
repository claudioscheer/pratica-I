package model;

import enumeraveis.StatusConta;
import enumeraveis.TipoConta;
import enumeraveis.TipoMovimento;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
@SequenceGenerator(name = "seq_opComercias", sequenceName = "seq_opComercias", allocationSize = 1)
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
    private double ValorTotal;
    private double ValorRecebido;
    private double ValorPendente;
    private double ValorParcela;
    private StatusConta contaStatus;
    
    private List<CarCapContas> parcelas;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_opComercias")
    public int getOperacoesID() {
        return operacoesID;
    }

    public void setOperacoesID(int operacoesID) {
        this.operacoesID = operacoesID;
    }

    @OneToMany(mappedBy = "carcapOperacoesComerciais", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<CarCapContas> getParcelas() {
        if (this.parcelas == null) {
            this.parcelas = new ArrayList<>();
        }
        return this.parcelas;
    }

    public void setParcelas(List<CarCapContas> parcelas) {
        this.parcelas = parcelas;
    }

    public void addParcela(CarCapContas parcela) {
        if (parcela != null) {
            parcela.setCarcapOperacoesComerciais(this);
            this.getParcelas().add(parcela);
        }
    }

    @Temporal(javax.persistence.TemporalType.DATE)
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

    @Enumerated
    public TipoConta getTipoDeConta() {
        return tipoDeConta;
    }

    public void setTipoDeConta(TipoConta tipoDeConta) {
        this.tipoDeConta = tipoDeConta;
    }

}
