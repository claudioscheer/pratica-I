package modelAntigo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name = "seq_movimentacao", sequenceName = "seq_movimentacao", allocationSize = 1)
public class Movimentacao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_movimentacao")
    private int codigo;
    @Temporal(TemporalType.DATE)
    private Date dataMovimentacao;
    private int quantidade;
    private double valorUnitario;
    private double total;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private TipoOperacao tipoOperacao;
}
