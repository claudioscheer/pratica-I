package model;
// Generated 22/10/2016 10:09:26 by Hibernate Tools 4.3.1

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
import javax.persistence.UniqueConstraint;

/**
 * PatNotaFiscal generated by hbm2java
 */
@Entity
@SequenceGenerator(name = "seq_nota_fiscal", sequenceName = "seq_nota_fiscal", allocationSize = 1)
@Table(name = "pat_nota_fiscal", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = "pessoa"))
public class PatNotaFiscal implements java.io.Serializable {

    private int notaCodigo;
    private CarPessoa carPessoa;
    private double notaValor;
    private Date notaDataEmissao;
    private String notaChaveAcesso;
    private Date notaDataEntrada;
    private Set<PatItemNota> patItemNotas = new HashSet(0);

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_nota_fiscal")
    @Column(name = "nota_codigo", unique = true, nullable = false)
    public int getNotaCodigo() {
        return this.notaCodigo;
    }

    public void setNotaCodigo(int notaCodigo) {
        this.notaCodigo = notaCodigo;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pessoa", unique = false)
    public CarPessoa getCarPessoa() {
        return this.carPessoa;
    }

    public void setCarPessoa(CarPessoa carPessoa) {
        this.carPessoa = carPessoa;
    }

    @Column(name = "nota_valor", nullable = false, precision = 17, scale = 17)
    public double getNotaValor() {
        return this.notaValor;
    }

    public void setNotaValor(double notaValor) {
        this.notaValor = notaValor;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "nota_data_emissao", nullable = false, length = 13)
    public Date getNotaDataEmissao() {
        return this.notaDataEmissao;
    }

    public void setNotaDataEmissao(Date notaDataEmissao) {
        this.notaDataEmissao = notaDataEmissao;
    }

    @Column(name = "nota_chave_acesso", nullable = false, length = 100)
    public String getNotaChaveAcesso() {
        return this.notaChaveAcesso;
    }

    public void setNotaChaveAcesso(String notaChaveAcesso) {
        this.notaChaveAcesso = notaChaveAcesso;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "nota_data_entrada", nullable = false, length = 13)
    public Date getNotaDataEntrada() {
        return this.notaDataEntrada;
    }

    public void setNotaDataEntrada(Date notaDataEntrada) {
        this.notaDataEntrada = notaDataEntrada;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patNotaFiscal", orphanRemoval = true)
    public Set<PatItemNota> getPatItemNotas() {
        return this.patItemNotas;
    }

    public void setPatItemNotas(Set<PatItemNota> patItemNotas) {
        this.patItemNotas = patItemNotas;
    }

}
