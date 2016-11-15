package model;

import enumeraveis.EstadoBem;
import enumeraveis.UtilizacaoBem;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "seq_ativo_imobilizado", sequenceName = "seq_ativo_imobilizado", allocationSize = 1)
@Table(schema = "public")
public class PatAtivoImobilizado implements java.io.Serializable {

    private int ativoCodigo;
    private EstCategoria estCategoria;
    private EstMarca estMarca;
    private PatItemNota patItemNota;
    private String ativoDescricao;
    private double ativoValorOriginal;
    private double ativoValorAtual;
    private double ativoTaxaValorResidual;
    private double ativoValorResidual;
    private EstadoBem ativoEstadoBem;
    private UtilizacaoBem ativoUtilizacao;
    private List<PatHistoricoDepreciacao> patHistoricoDepreciacaos;
    private boolean ativoDepreciavel;
    private boolean ativo;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ativo_imobilizado")
    @Column(nullable = false)
    public int getAtivoCodigo() {
        return this.ativoCodigo;
    }

    public void setAtivoCodigo(int ativoCodigo) {
        this.ativoCodigo = ativoCodigo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public EstCategoria getEstCategoria() {
        return this.estCategoria;
    }

    public void setEstCategoria(EstCategoria estCategoria) {
        this.estCategoria = estCategoria;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public EstMarca getEstMarca() {
        return this.estMarca;
    }

    public void setEstMarca(EstMarca estMarca) {
        this.estMarca = estMarca;
    }

    @Column(nullable = false)
    public String getAtivoDescricao() {
        return this.ativoDescricao;
    }

    public void setAtivoDescricao(String ativoDescricao) {
        this.ativoDescricao = ativoDescricao;
    }

    @Column(nullable = false)
    public double getAtivoValorOriginal() {
        return this.ativoValorOriginal;
    }

    public void setAtivoValorOriginal(double ativoValorOriginal) {
        this.ativoValorOriginal = ativoValorOriginal;
    }

    @Column(nullable = false)
    public double getAtivoValorAtual() {
        return this.ativoValorAtual;
    }

    public void setAtivoValorAtual(double ativoValorAtual) {
        this.ativoValorAtual = ativoValorAtual;
    }

    @Column(nullable = false)
    public double getAtivoTaxaValorResidual() {
        return this.ativoTaxaValorResidual;
    }

    public void setAtivoTaxaValorResidual(double ativoTaxaValorResidual) {
        this.ativoTaxaValorResidual = ativoTaxaValorResidual;
    }

    @Column(nullable = false)
    public double getAtivoValorResidual() {
        return this.ativoValorResidual;
    }

    public void setAtivoValorResidual(double ativoValorResidual) {
        this.ativoValorResidual = ativoValorResidual;
    }

    @Column(nullable = false)
    public EstadoBem getAtivoEstadoBem() {
        return this.ativoEstadoBem;
    }

    public void setAtivoEstadoBem(EstadoBem ativoEstadoBem) {
        this.ativoEstadoBem = ativoEstadoBem;
    }

    @Column(nullable = false)
    public UtilizacaoBem getAtivoUtilizacao() {
        return this.ativoUtilizacao;
    }

    public void setAtivoUtilizacao(UtilizacaoBem ativoUtilizacao) {
        this.ativoUtilizacao = ativoUtilizacao;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patAtivoImobilizado")
    public List<PatHistoricoDepreciacao> getPatHistoricoDepreciacaos() {
        if (this.patHistoricoDepreciacaos == null) {
            this.patHistoricoDepreciacaos = new ArrayList<>();
        }
        return this.patHistoricoDepreciacaos;
    }

    public void setPatHistoricoDepreciacaos(List<PatHistoricoDepreciacao> patHistoricoDepreciacaos) {
        this.patHistoricoDepreciacaos = patHistoricoDepreciacaos;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public PatItemNota getPatItemNota() {
        return patItemNota;
    }

    public void setPatItemNota(PatItemNota patItemNota) {
        this.patItemNota = patItemNota;
    }

    @Column(nullable = false)
    public boolean isAtivoDepreciavel() {
        return ativoDepreciavel;
    }

    public void setAtivoDepreciavel(boolean ativoDepreciavel) {
        this.ativoDepreciavel = ativoDepreciavel;
    }

    @Column(nullable = false)
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
