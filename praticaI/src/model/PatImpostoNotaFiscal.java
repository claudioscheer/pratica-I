package model;
// Generated 22/10/2016 10:09:26 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PatImpostoNotaFiscal generated by hbm2java
 */
@Entity
@Table(name = "pat_imposto_nota_fiscal", schema = "public"
)
public class PatImpostoNotaFiscal implements java.io.Serializable {

    private int impostoNotaFiscalCodigo;
    private PatImposto patImposto;
    private PatNotaFiscal patNotaFiscal;
    private double impostoNotaFiscalValor;

    public PatImpostoNotaFiscal() {
    }

    public PatImpostoNotaFiscal(int impostoNotaFiscalCodigo, double impostoNotaFiscalValor) {
        this.impostoNotaFiscalCodigo = impostoNotaFiscalCodigo;
        this.impostoNotaFiscalValor = impostoNotaFiscalValor;
    }

    public PatImpostoNotaFiscal(int impostoNotaFiscalCodigo, PatImposto patImposto, PatNotaFiscal patNotaFiscal, double impostoNotaFiscalValor) {
        this.impostoNotaFiscalCodigo = impostoNotaFiscalCodigo;
        this.patImposto = patImposto;
        this.patNotaFiscal = patNotaFiscal;
        this.impostoNotaFiscalValor = impostoNotaFiscalValor;
    }

    @Id

    @Column(name = "imposto_nota_fiscal_codigo", unique = true, nullable = false)
    public int getImpostoNotaFiscalCodigo() {
        return this.impostoNotaFiscalCodigo;
    }

    public void setImpostoNotaFiscalCodigo(int impostoNotaFiscalCodigo) {
        this.impostoNotaFiscalCodigo = impostoNotaFiscalCodigo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imposto")
    public PatImposto getPatImposto() {
        return this.patImposto;
    }

    public void setPatImposto(PatImposto patImposto) {
        this.patImposto = patImposto;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nota_fiscal")
    public PatNotaFiscal getPatNotaFiscal() {
        return this.patNotaFiscal;
    }

    public void setPatNotaFiscal(PatNotaFiscal patNotaFiscal) {
        this.patNotaFiscal = patNotaFiscal;
    }

    @Column(name = "imposto_nota_fiscal_valor", nullable = false, precision = 17, scale = 17)
    public double getImpostoNotaFiscalValor() {
        return this.impostoNotaFiscalValor;
    }

    public void setImpostoNotaFiscalValor(double impostoNotaFiscalValor) {
        this.impostoNotaFiscalValor = impostoNotaFiscalValor;
    }

}
