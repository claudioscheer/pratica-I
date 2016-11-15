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
import javax.persistence.Transient;

@Entity
@SequenceGenerator(name = "seq_item_nota", sequenceName = "seq_item_nota", allocationSize = 1)
@Table(schema = "public")
public class PatItemNota implements java.io.Serializable {

    private int itemNotaCodigo;
    private EstProduto estProduto;
    private PatNotaFiscal patNotaFiscal;
    private int itemNotaQuantidade;
    private double itemNotaValorUnitario;
    private double itemNotaValorTotal;
    private boolean itemNotacontrolavel;
    private int itemNotaOrdem;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_item_nota")
    @Column(nullable = false)
    public int getItemNotaCodigo() {
        return this.itemNotaCodigo;
    }

    public void setItemNotaCodigo(int itemNotaCodigo) {
        this.itemNotaCodigo = itemNotaCodigo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public EstProduto getEstProduto() {
        return this.estProduto;
    }

    public void setEstProduto(EstProduto estProduto) {
        this.estProduto = estProduto;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public PatNotaFiscal getPatNotaFiscal() {
        return this.patNotaFiscal;
    }

    public void setPatNotaFiscal(PatNotaFiscal patNotaFiscal) {
        this.patNotaFiscal = patNotaFiscal;
    }

    @Column(nullable = false)
    public int getItemNotaQuantidade() {
        return this.itemNotaQuantidade;
    }

    public void setItemNotaQuantidade(int itemNotaQuantidade) {
        this.itemNotaQuantidade = itemNotaQuantidade;
    }

    @Column(nullable = false)
    public double getItemNotaValorUnitario() {
        return this.itemNotaValorUnitario;
    }

    public void setItemNotaValorUnitario(double itemNotaValorUnitario) {
        this.itemNotaValorUnitario = itemNotaValorUnitario;
    }

    @Column(nullable = false)
    public double getItemNotaValorTotal() {
        return this.itemNotaValorTotal;
    }

    public void setItemNotaValorTotal(double itemNotaValorTotal) {
        this.itemNotaValorTotal = itemNotaValorTotal;
    }

    @Column(nullable = false)
    public boolean isItemNotacontrolavel() {
        return itemNotacontrolavel;
    }

    public void setItemNotacontrolavel(boolean itemNotacontrolavel) {
        this.itemNotacontrolavel = itemNotacontrolavel;
    }

    @Column(nullable = false)
    public int getItemNotaOrdem() {
        return itemNotaOrdem;
    }

    public void setItemNotaOrdem(int itemNotaOrdem) {
        this.itemNotaOrdem = itemNotaOrdem;
    }

}
