package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "seq_depreciacao", sequenceName = "seq_depreciacao", allocationSize = 1)
@Table(schema = "public")
public class PatDepreciacao implements java.io.Serializable {

    private int depreciacaoCodigo;
    private EstCategoria estCategoria;
    private double depreciacaoTaxaAnual;
    private int depreciacaoVidaUtil;
    private double depreciacaoTaxaMensal;
    private double depreciacaoTaxaDiaria;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_depreciacao")
    @Column(nullable = false)
    public int getDepreciacaoCodigo() {
        return this.depreciacaoCodigo;
    }

    public void setDepreciacaoCodigo(int depreciacaoCodigo) {
        this.depreciacaoCodigo = depreciacaoCodigo;
    }

    @OneToOne
    public EstCategoria getEstCategoria() {
        return this.estCategoria;
    }

    public void setEstCategoria(EstCategoria estCategoria) {
        this.estCategoria = estCategoria;
    }

    @Column(nullable = false)
    public double getDepreciacaoTaxaAnual() {
        return this.depreciacaoTaxaAnual;
    }

    public void setDepreciacaoTaxaAnual(double depreciacaoTaxaAnual) {
        this.depreciacaoTaxaAnual = depreciacaoTaxaAnual;

        this.setDepreciacaoTaxaMensal(Double.valueOf(utils.Utils.removerCaracteresDoubleString(utils.Utils.format(depreciacaoTaxaAnual / 12))));
        this.setDepreciacaoTaxaDiaria(Double.valueOf(utils.Utils.removerCaracteresDoubleString(utils.Utils.format(this.depreciacaoTaxaMensal / 30))));
    }

    @Column(nullable = false)
    public int getDepreciacaoVidaUtil() {
        return this.depreciacaoVidaUtil;
    }

    public void setDepreciacaoVidaUtil(int depreciacaoVidaUtil) {
        this.depreciacaoVidaUtil = depreciacaoVidaUtil;
    }

    @Column(nullable = false)
    public double getDepreciacaoTaxaMensal() {
        return this.depreciacaoTaxaMensal;
    }

    public void setDepreciacaoTaxaMensal(double depreciacaoTaxaMensal) {
        this.depreciacaoTaxaMensal = depreciacaoTaxaMensal;
    }

    @Column(nullable = false)
    public double getDepreciacaoTaxaDiaria() {
        return depreciacaoTaxaDiaria;
    }

    public void setDepreciacaoTaxaDiaria(double depreciacaoTaxaDiaria) {
        this.depreciacaoTaxaDiaria = depreciacaoTaxaDiaria;
    }

}
