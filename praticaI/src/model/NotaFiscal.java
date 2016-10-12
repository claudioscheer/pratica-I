package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    @Column(name = "nota_codigo")
    private int notaFiscal;

    @Column(name = "nota_valor", nullable = false)
    private double valor;

    @Column(name = "nota_data_emissao", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataEmissao;

    @Column(name = "nota_chave_acesso", nullable = false)
    private String chaveAcesso;

    @Column(name = "nota_data_entrada", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataEntrada;

    @ManyToOne
    @Column(name = "nota_fornecedor", nullable = false)
    private Fornecedor fornecedor;

    @OneToMany
    @Column(name = "nota_ativo_imobilizado", nullable = false)
    private AtivoImobilizado ativoImobilizado;

    private List<ItemNota> itensNota;

    private List<ImpostoNotaFiscal> impostoNota;

}
