package model;

import com.sun.istack.internal.NotNull;
import enumeraveis.EstadoBem;
import enumeraveis.UtilizacaoBem;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
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
    @NotNull
    public int getAtivoCodigo() {
        return this.ativoCodigo;
    }

    public void setAtivoCodigo(int ativoCodigo) {
        this.ativoCodigo = ativoCodigo;
    }

    @ManyToOne
    @NotNull
    public EstCategoria getEstCategoria() {
        return this.estCategoria;
    }

    public void setEstCategoria(EstCategoria estCategoria) {
        this.estCategoria = estCategoria;
    }

    @ManyToOne
    @NotNull
    public EstMarca getEstMarca() {
        return this.estMarca;
    }

    public void setEstMarca(EstMarca estMarca) {
        this.estMarca = estMarca;
    }

    @NotNull
    public String getAtivoDescricao() {
        return this.ativoDescricao;
    }

    public void setAtivoDescricao(String ativoDescricao) {
        this.ativoDescricao = ativoDescricao;
    }

    @NotNull
    public double getAtivoValorOriginal() {
        return this.ativoValorOriginal;
    }

    public void setAtivoValorOriginal(double ativoValorOriginal) {
        this.ativoValorOriginal = ativoValorOriginal;
    }

    @NotNull
    public double getAtivoValorAtual() {
        return this.ativoValorAtual;
    }

    public void setAtivoValorAtual(double ativoValorAtual) {
        this.ativoValorAtual = ativoValorAtual;
    }

    @NotNull
    public double getAtivoTaxaValorResidual() {
        return this.ativoTaxaValorResidual;
    }

    public void setAtivoTaxaValorResidual(double ativoTaxaValorResidual) {
        this.ativoTaxaValorResidual = ativoTaxaValorResidual;
    }

    @NotNull
    public double getAtivoValorResidual() {
        return this.ativoValorResidual;
    }

    public void setAtivoValorResidual(double ativoValorResidual) {
        this.ativoValorResidual = ativoValorResidual;
    }

    @NotNull
    public EstadoBem getAtivoEstadoBem() {
        return this.ativoEstadoBem;
    }

    public void setAtivoEstadoBem(EstadoBem ativoEstadoBem) {
        this.ativoEstadoBem = ativoEstadoBem;
    }

    @NotNull
    public UtilizacaoBem getAtivoUtilizacao() {
        return this.ativoUtilizacao;
    }

    public void setAtivoUtilizacao(UtilizacaoBem ativoUtilizacao) {
        this.ativoUtilizacao = ativoUtilizacao;
    }

    @OneToMany(mappedBy = "patAtivoImobilizado")
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

    @NotNull
    public boolean isAtivoDepreciavel() {
        return ativoDepreciavel;
    }

    public void setAtivoDepreciavel(boolean ativoDepreciavel) {
        this.ativoDepreciavel = ativoDepreciavel;
    }

    @NotNull
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
