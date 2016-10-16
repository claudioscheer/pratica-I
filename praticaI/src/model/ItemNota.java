package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
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
@SequenceGenerator(name = "seq_item_nota", sequenceName = "seq_item_nota", allocationSize = 1)
@Table(name = "pat_item_nota")
public class ItemNota implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_item_nota")
    @Column(name = "item_nota_codigo")
    private int itemNota;

    @ManyToOne
    @JoinColumn(name = "item_nota_produto")
    private Produto produto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_nota_nota")
    private NotaFiscal nota;

    @Column(name = "item_nota_quantidade")
    private int quantidade;

    @Column(name = "item_nota_valor_unitario")
    private double valorUnitario;

    @Column(name = "item_nota_valor_total")
    private double valorTotal;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "itemNota", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<ImpostoItemNota> impostoItem = new HashSet<>(0);

    public int getItemNota() {
        return itemNota;
    }

    public void setItemNota(int itemNota) {
        this.itemNota = itemNota;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public NotaFiscal getNota() {
        return nota;
    }

    public void setNota(NotaFiscal nota) {
        this.nota = nota;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Set<ImpostoItemNota> getImpostoItem() {
        return impostoItem;
    }

    public void setImpostoItem(Set<ImpostoItemNota> impostoItem) {
        this.impostoItem = impostoItem;
    }

}
