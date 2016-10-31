package modelAntigo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name = "seq_nota_fiscal", sequenceName = "seq_nota_fiscal", allocationSize = 1)
@Table(name = "pat_nota_fiscal")
public class NotaFiscal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_nota_fiscal")
//    @Column(name = "nota_codigo")
    private int notaFiscal;

//    @Column(name = "nota_valor")
    private double valor;

//    @Column(name = "nota_data_emissao")
    @Temporal(TemporalType.DATE)
    private Date dataEmissao;

//    @Column(name = "nota_chave_acesso")
    private String chaveAcesso;

//    @Column(name = "nota_data_entrada")
    @Temporal(TemporalType.DATE)
    private Date dataEntrada;

    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "nota_fornecedor")
    private Fornecedor fornecedor;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "nota", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<ItemNota> itensNota = new HashSet<>(0);

    public int getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(int notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getChaveAcesso() {
        return chaveAcesso;
    }

    public void setChaveAcesso(String chaveAcesso) {
        this.chaveAcesso = chaveAcesso;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Set<ItemNota> getItensNota() {
        return itensNota;
    }

    public void setItensNota(Set<ItemNota> itensNota) {
        this.itensNota = itensNota;
    }

}
