package model;

import enumeraveis.EstadoBem;
import enumeraveis.UtilizacaoBem;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Temporal;

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
    private Date ativoDataCadastro;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ativo_imobilizado")
    public int getAtivoCodigo() {
        return this.ativoCodigo;
    }

    public void setAtivoCodigo(int ativoCodigo) {
        this.ativoCodigo = ativoCodigo;
    }

    @ManyToOne
    public EstCategoria getEstCategoria() {
        return this.estCategoria;
    }

    public void setEstCategoria(EstCategoria estCategoria) {
        this.estCategoria = estCategoria;
    }

    @ManyToOne
    public EstMarca getEstMarca() {
        return this.estMarca;
    }

    public void setEstMarca(EstMarca estMarca) {
        this.estMarca = estMarca;
    }

    public String getAtivoDescricao() {
        return this.ativoDescricao;
    }

    public void setAtivoDescricao(String ativoDescricao) {
        this.ativoDescricao = ativoDescricao;
    }

    public double getAtivoValorOriginal() {
        return this.ativoValorOriginal;
    }

    public void setAtivoValorOriginal(double ativoValorOriginal) {
        this.ativoValorOriginal = ativoValorOriginal;
    }

    public double getAtivoValorAtual() {
        return this.ativoValorAtual;
    }

    public void setAtivoValorAtual(double ativoValorAtual) {
        this.ativoValorAtual = ativoValorAtual;
    }

    public double getAtivoTaxaValorResidual() {
        return this.ativoTaxaValorResidual;
    }

    public void setAtivoTaxaValorResidual(double ativoTaxaValorResidual) {
        this.ativoTaxaValorResidual = ativoTaxaValorResidual;
    }

    public double getAtivoValorResidual() {
        return this.ativoValorResidual;
    }

    public void setAtivoValorResidual(double ativoValorResidual) {
        this.ativoValorResidual = ativoValorResidual;
    }

    public EstadoBem getAtivoEstadoBem() {
        return this.ativoEstadoBem;
    }

    public void setAtivoEstadoBem(EstadoBem ativoEstadoBem) {
        this.ativoEstadoBem = ativoEstadoBem;
    }

    public UtilizacaoBem getAtivoUtilizacao() {
        return this.ativoUtilizacao;
    }

    public void setAtivoUtilizacao(UtilizacaoBem ativoUtilizacao) {
        this.ativoUtilizacao = ativoUtilizacao;
    }

    @OneToMany(mappedBy = "patAtivoImobilizado", fetch = FetchType.EAGER)
    public List<PatHistoricoDepreciacao> getPatHistoricoDepreciacaos() {
        if (this.patHistoricoDepreciacaos == null) {
            this.patHistoricoDepreciacaos = new ArrayList<>();
        }
        return this.patHistoricoDepreciacaos;
    }

    public void setPatHistoricoDepreciacaos(List<PatHistoricoDepreciacao> patHistoricoDepreciacaos) {
        this.patHistoricoDepreciacaos = patHistoricoDepreciacaos;
    }

    @ManyToOne
    public PatItemNota getPatItemNota() {
        return patItemNota;
    }

    public void setPatItemNota(PatItemNota patItemNota) {
        this.patItemNota = patItemNota;
    }

    public boolean isAtivoDepreciavel() {
        return ativoDepreciavel;
    }

    public void setAtivoDepreciavel(boolean ativoDepreciavel) {
        this.ativoDepreciavel = ativoDepreciavel;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getAtivoDataCadastro() {
        return ativoDataCadastro;
    }

    public void setAtivoDataCadastro(Date ativoDataCadastro) {
        this.ativoDataCadastro = ativoDataCadastro;
    }

}
