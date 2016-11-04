package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "seq_depreciacao", sequenceName = "seq_depreciacao", allocationSize = 1)
@Table(name = "pat_depreciacao", schema = "public")
public class PatDepreciacao implements java.io.Serializable {

    private int depreciacaoCodigo;
    private EstCategoria estCategoria;
    private double depreciacaoTaxaAnual;
    private int depreciacaoVidaUtil;
    private double depreciacaoTaxaMensal;
    private double depreciacaoDiaria;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_depreciacao")
    @Column(name = "depreciacao_codigo", nullable = false)
    public int getDepreciacaoCodigo() {
        return this.depreciacaoCodigo;
    }

    public void setDepreciacaoCodigo(int depreciacaoCodigo) {
        this.depreciacaoCodigo = depreciacaoCodigo;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria", nullable = false)
    public EstCategoria getEstCategoria() {
        return this.estCategoria;
    }

    public void setEstCategoria(EstCategoria estCategoria) {
        this.estCategoria = estCategoria;
    }

    @Column(name = "depreciacao_taxa_anual", nullable = false)
    public double getDepreciacaoTaxaAnual() {
        return this.depreciacaoTaxaAnual;
    }

    public void setDepreciacaoTaxaAnual(double depreciacaoTaxaAnual) {
        this.depreciacaoTaxaAnual = depreciacaoTaxaAnual;

        this.setDepreciacaoTaxaMensal(depreciacaoTaxaAnual / 12);
        this.setDepreciacaoDiaria(this.depreciacaoTaxaMensal / 30);
    }

    @Column(name = "depreciacao_vida_util", nullable = false)
    public int getDepreciacaoVidaUtil() {
        return this.depreciacaoVidaUtil;
    }

    public void setDepreciacaoVidaUtil(int depreciacaoVidaUtil) {
        this.depreciacaoVidaUtil = depreciacaoVidaUtil;
    }

    @Column(name = "depreciacao_taxa_mensal", nullable = false)
    public double getDepreciacaoTaxaMensal() {
        return this.depreciacaoTaxaMensal;
    }

    public void setDepreciacaoTaxaMensal(double depreciacaoTaxaMensal) {
        this.depreciacaoTaxaMensal = depreciacaoTaxaMensal;
    }

    @Column(name = "depreciacao_taxa_diaria", nullable = false)
    public double getDepreciacaoDiaria() {
        return depreciacaoDiaria;
    }

    public void setDepreciacaoDiaria(double depreciacaoDiaria) {
        this.depreciacaoDiaria = depreciacaoDiaria;
    }

}
