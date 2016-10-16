package model;

import java.io.Serializable;
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
@SequenceGenerator(name = "seq_imposto_item_nota", sequenceName = "seq_imposto_item_nota", allocationSize = 1)
@Table(name = "pat_imposto_item_nota")
public class ImpostoItemNota implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_imposto_item_nota")
    @Column(name = "imposto_item_nota_codigo")
    private int codigo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imposto_item_nota_item_nota")
    private ItemNota itemNota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imposto_item_nota_imposto")
    private Imposto imposto;

    @Column(name = "imposto_item_nota_valor", nullable = true)
    private double valor;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public ItemNota getItemNota() {
        return itemNota;
    }

    public void setItemNota(ItemNota itemNota) {
        this.itemNota = itemNota;
    }

    public Imposto getImposto() {
        return imposto;
    }

    public void setImposto(Imposto imposto) {
        this.imposto = imposto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
