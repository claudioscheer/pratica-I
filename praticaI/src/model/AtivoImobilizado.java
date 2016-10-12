package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "seq_ativo_imobilizado", sequenceName = "seq_ativo_imobilizado", allocationSize = 1)
@Table(name = "pat_ativo_imobilizado")
public class AtivoImobilizado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ativo_imobilizado")
    @Column(name = "ativo_codigo")
    private int ativoImobilizado;

    @Column(name = "ativo_descricao", nullable = false)
    private String descricao;

    @ManyToOne
    @Column(name = "ativo_categoria", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @Column(name = "ativo_marca", nullable = false)
    private Marca marca;

//    @ManyToOne
//    @Column(name = "ativo_nota_fiscal")
//    private NotaFiscal notaFiscal;
    @Column(name = "ativo_valor_original", nullable = false)
    private double valorOriginal;

    @Column(name = "ativo_valor_atual", nullable = false)
    private double valorAtual;

    @Column(name = "ativo_taxa_valor_residual", nullable = false)
    private double taxaValorResidual;

    @Column(name = "ativo_valor_residual", nullable = false)
    private double valorResidual;

    @Enumerated
    @Column(name = "ativo_estado_bem", nullable = false)
    private EstadoBem estadoBem;

    @Enumerated
    @Column(name = "ativo_utilizacao", nullable = false)
    private UtilizacaoBem utilizacao;

    public int getAtivoImobilizado() {
        return ativoImobilizado;
    }

    public void setAtivoImobilizado(int ativoImobilizado) {
        this.ativoImobilizado = ativoImobilizado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public double getValorOriginal() {
        return valorOriginal;
    }

    public void setValorOriginal(double valorOriginal) {
        this.valorOriginal = valorOriginal;
    }

    public double getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(double valorAtual) {
        this.valorAtual = valorAtual;
    }

    public double getTaxaValorResidual() {
        return taxaValorResidual;
    }

    public void setTaxaValorResidual(double taxaValorResidual) {
        this.taxaValorResidual = taxaValorResidual;
    }

    public double getValorResidual() {
        return valorResidual;
    }

    public void setValorResidual(double valorResidual) {
        this.valorResidual = valorResidual;
    }

//    public NotaFiscal getNotaFiscal() {
//        return notaFiscal;
//    }
//
//    public void setNotaFiscal(NotaFiscal notaFiscal) {
//        this.notaFiscal = notaFiscal;
//    }
    public EstadoBem getEstadoBem() {
        return estadoBem;
    }

    public void setEstadoBem(EstadoBem estadoBem) {
        this.estadoBem = estadoBem;
    }

    public UtilizacaoBem getUtilizacao() {
        return utilizacao;
    }

    public void setUtilizacao(UtilizacaoBem utilizacao) {
        this.utilizacao = utilizacao;
    }

}
