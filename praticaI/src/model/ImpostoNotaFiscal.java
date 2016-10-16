package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "seq_imposto_nota_fiscal", sequenceName = "seq_imposto_nota_fiscal", allocationSize = 1)
@Table(name = "pat_item_nota")
public class ImpostoNotaFiscal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_imposto_nota_fiscal")
    @Column(name = "imposto_nota_fiscal_codigo")
    private int codigo;

    @ManyToOne
    @JoinColumn(name = "imposto_nota_fiscal_imposto")
    private Imposto imposto;

    @ManyToOne
    @JoinColumn(name = "imposto_nota_fiscal_nota_fiscal")
    private NotaFiscal notaFiscal;

    @Column(name = "imposto_nota_fiscal_valor", nullable = false)
    private double valor;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Imposto getImposto() {
        return imposto;
    }

    public void setImposto(Imposto imposto) {
        this.imposto = imposto;
    }

    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
