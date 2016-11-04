package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name = "seq_nota_fiscal", sequenceName = "seq_nota_fiscal", allocationSize = 1)
@Table(name = "pat_nota_fiscal", schema = "public")
public class PatNotaFiscal implements java.io.Serializable {

    private int notaCodigo;
    private CarPessoa carPessoa;
    private double notaValor;
    private Date notaDataEmissao;
    private String notaChaveAcesso;
    private Date notaDataEntrada;
    private List<PatItemNota> patItemNotas;
    private boolean notaAtiva;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_nota_fiscal")
    @Column(name = "nota_codigo", nullable = false)
    public int getNotaCodigo() {
        return this.notaCodigo;
    }

    public void setNotaCodigo(int notaCodigo) {
        this.notaCodigo = notaCodigo;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pessoa", nullable = false)
    public CarPessoa getCarPessoa() {
        return this.carPessoa;
    }

    public void setCarPessoa(CarPessoa carPessoa) {
        this.carPessoa = carPessoa;
    }

    @Column(name = "nota_valor", nullable = false)
    public double getNotaValor() {
        return this.notaValor;
    }

    public void setNotaValor(double notaValor) {
        this.notaValor = notaValor;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "nota_data_emissao", nullable = false)
    public Date getNotaDataEmissao() {
        return this.notaDataEmissao;
    }

    public void setNotaDataEmissao(Date notaDataEmissao) {
        this.notaDataEmissao = notaDataEmissao;
    }

    @Column(name = "nota_chave_acesso", nullable = false)
    public String getNotaChaveAcesso() {
        return this.notaChaveAcesso;
    }

    public void setNotaChaveAcesso(String notaChaveAcesso) {
        this.notaChaveAcesso = notaChaveAcesso;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "nota_data_entrada", nullable = false)
    public Date getNotaDataEntrada() {
        return this.notaDataEntrada;
    }

    public void setNotaDataEntrada(Date notaDataEntrada) {
        this.notaDataEntrada = notaDataEntrada;
    }

    @OneToMany(mappedBy = "patNotaFiscal", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("ordem ASC")
    public List<PatItemNota> getPatItemNotas() {
        if (this.patItemNotas == null) {
            this.patItemNotas = new ArrayList<>();
        }
        return this.patItemNotas;
    }

    public void setPatItemNotas(List<PatItemNota> patItemNotas) {
        this.patItemNotas = patItemNotas;
    }

    public boolean isNotaAtiva() {
        return notaAtiva;
    }

    public void setNotaAtiva(boolean notaAtiva) {
        this.notaAtiva = notaAtiva;
    }

    public void addItemNota(int index, PatItemNota itemNota) {
        if (itemNota != null) {
            itemNota.setPatNotaFiscal(this);
            if (index > -1) {
                this.getPatItemNotas().add(index, itemNota);
            } else {
                this.getPatItemNotas().add(itemNota);
            }
        }
    }

    public void removeItemNota(int index) {
        this.getPatItemNotas().remove(index);
    }
}
