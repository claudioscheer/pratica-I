package model;
// Generated 22/10/2016 10:09:26 by Hibernate Tools 4.3.1

import enumeraveis.StatusConta;
import enumeraveis.TipoConta;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * CarCapContas generated by hbm2java
 */
@Entity
@Table(name = "car_cap_contas", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = "conta_nota_fiscal")
)
public class CarCapContas implements java.io.Serializable {

    @Id
    @Column(name = "conta_id", unique = true, nullable = false)
    private int contaId;

    @Enumerated
    @Column(name = "contastatus")
    private StatusConta contaStatus;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private CarEstTipoOperacao carEstTipoOperacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_pessoa")
    private CarPessoa carPessoa;

    @ManyToOne(fetch = FetchType.LAZY)
    private FlxcxOperacoes flxcxOperacoes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_nota_fiscal", unique = true)
    private PatNotaFiscal patNotaFiscal;

    @Column(name = "conta_valor_total", nullable = false, precision = 17, scale = 17)
    private double contaValorTotal;

    @Temporal(TemporalType.DATE)
    @Column(name = "conta_data_emissao", nullable = false, length = 13)
    private Date contaDataEmissao;

    @Column(name = "conta_num_parcelas", nullable = false)
    private int contaNumParcelas;

    @Column(name = "conta_valor_pago", nullable = false, precision = 17, scale = 17)
    private double contaValorPago;

    @Enumerated
    private TipoConta contaTipo;

    @OneToMany(mappedBy = "carCapContas")
    private Set<FlxcxEspecificacoes> flxcxEspecificacoeses = new HashSet(0);

    @OneToMany(mappedBy = "carCapContas")
    private Set<CarCapParcela> carCapParcelas = new HashSet(0);

    @OneToMany(mappedBy = "carCapContas")
    private Set<FlxcxMovimentoBancario> flxcxMovimentosBancarios;

    public CarCapContas() {
    }

    public CarCapContas(int contaId, double contaValorTotal, Date contaDataEmissao, int contaNumParcelas, double contaValorPago, TipoConta contaTipo) {
        this.contaId = contaId;
        this.contaValorTotal = contaValorTotal;
        this.contaDataEmissao = contaDataEmissao;
        this.contaNumParcelas = contaNumParcelas;
        this.contaValorPago = contaValorPago;
        this.contaTipo = contaTipo;
    }

    public CarCapContas(int contaId, StatusConta contaStatus, CarEstTipoOperacao carEstTipoOperacao, CarPessoa carPessoa, FlxcxOperacoes flxcxOperacoes, PatNotaFiscal patNotaFiscal, double contaValorTotal, Date contaDataEmissao, int contaNumParcelas, double contaValorPago, TipoConta contaTipo, Set<FlxcxEspecificacoes> flxcxEspecificacoeses, Set<CarCapParcela> carCapParcelas, Set<FlxcxMovimentoBancario> flxcxMovimentosBancarios) {
        this.contaId = contaId;
        this.contaStatus = contaStatus;
        this.carEstTipoOperacao = carEstTipoOperacao;
        this.carPessoa = carPessoa;
        this.flxcxOperacoes = flxcxOperacoes;
        this.patNotaFiscal = patNotaFiscal;
        this.contaValorTotal = contaValorTotal;
        this.contaDataEmissao = contaDataEmissao;
        this.contaNumParcelas = contaNumParcelas;
        this.contaValorPago = contaValorPago;
        this.contaTipo = contaTipo;
        this.flxcxEspecificacoeses = flxcxEspecificacoeses;
        this.carCapParcelas = carCapParcelas;
        this.flxcxMovimentosBancarios = flxcxMovimentosBancarios;
    }

    public int getContaId() {
        return this.contaId;
    }

    public void setContaId(int contaId) {
        this.contaId = contaId;
    }

    public StatusConta getCapContaStatus() {
        return this.contaStatus;
    }

    public void setCapContaStatus(StatusConta contaStatus) {
        this.contaStatus = contaStatus;
    }

    public CarEstTipoOperacao getCarEstTipoOperacao() {
        return this.carEstTipoOperacao;
    }

    public void setCarEstTipoOperacao(CarEstTipoOperacao carEstTipoOperacao) {
        this.carEstTipoOperacao = carEstTipoOperacao;
    }

    public CarPessoa getCarPessoa() {
        return this.carPessoa;
    }

    public void setCarPessoa(CarPessoa carPessoa) {
        this.carPessoa = carPessoa;
    }

    public FlxcxOperacoes getFlxcxOperacoes() {
        return this.flxcxOperacoes;
    }

    public void setFlxcxOperacoes(FlxcxOperacoes flxcxOperacoes) {
        this.flxcxOperacoes = flxcxOperacoes;
    }

    public PatNotaFiscal getPatNotaFiscal() {
        return this.patNotaFiscal;
    }

    public void setPatNotaFiscal(PatNotaFiscal patNotaFiscal) {
        this.patNotaFiscal = patNotaFiscal;
    }

    public double getContaValorTotal() {
        return this.contaValorTotal;
    }

    public void setContaValorTotal(double contaValorTotal) {
        this.contaValorTotal = contaValorTotal;
    }

    public Date getContaDataEmissao() {
        return this.contaDataEmissao;
    }

    public void setContaDataEmissao(Date contaDataEmissao) {
        this.contaDataEmissao = contaDataEmissao;
    }

    public int getContaNumParcelas() {
        return this.contaNumParcelas;
    }

    public void setContaNumParcelas(int contaNumParcelas) {
        this.contaNumParcelas = contaNumParcelas;
    }

    public double getContaValorPago() {
        return this.contaValorPago;
    }

    public void setContaValorPago(double contaValorPago) {
        this.contaValorPago = contaValorPago;
    }

    public TipoConta getContaTipo() {
        return this.contaTipo;
    }

    public void setContaTipo(TipoConta contaTipo) {
        this.contaTipo = contaTipo;
    }

    public Set<FlxcxEspecificacoes> getFlxcxEspecificacoeses() {
        return this.flxcxEspecificacoeses;
    }

    public void setFlxcxEspecificacoeses(Set<FlxcxEspecificacoes> flxcxEspecificacoeses) {
        this.flxcxEspecificacoeses = flxcxEspecificacoeses;
    }

    public Set<CarCapParcela> getCarCapParcelas() {
        return this.carCapParcelas;
    }

    public void setCarCapParcelas(Set<CarCapParcela> carCapParcelas) {
        this.carCapParcelas = carCapParcelas;
    }

    public Set<FlxcxMovimentoBancario> getFlxcxMovimentosBancarios() {
        return flxcxMovimentosBancarios;
    }

    public void setFlxcxMovimentosBancarios(Set<FlxcxMovimentoBancario> flxcxMovimentosBancarios) {
        this.flxcxMovimentosBancarios = flxcxMovimentosBancarios;
    }

}
