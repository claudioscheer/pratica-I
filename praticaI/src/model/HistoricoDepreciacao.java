package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name = "seq_historico_depreciacao", sequenceName = "seq_historico_depreciacao", allocationSize = 1)
@Table(name = "pat_historico_depreciacao")
public class HistoricoDepreciacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_historico_depreciacao")
    @Column(name = "historico_depreciacao_codigo")
    private int historicoDepreciacao;

    @ManyToOne
    @Column(name = "historico_depreciacao_ativo_imobilizado", nullable = false)
    private AtivoImobilizado ativoImobilizado;

    @Temporal(TemporalType.DATE)
    @Column(name = "historico_depreciacao_dia", nullable = false)
    private Date dia;

    @Column(name = "historico_depreciacao_mes", nullable = false)
    private int mes;

    @Column(name = "historico_depreciacao_ano", nullable = false)
    private int ano;

    @Column(name = "historico_depreciacao_valor", nullable = false)
    private double valor;

}
