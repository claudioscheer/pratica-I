package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "seq_depreciacao", sequenceName = "seq_depreciacao", allocationSize = 1)
@Table(name = "pat_depreciacao")
public class Depreciacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_depreciacao")
    @Column(name = "depreciacao_codigo")
    private int depreciacao;

    @OneToOne
    @Column(name = "depreciacao_categoria", nullable = false)
    private Categoria categoria;

    @Column(name = "depreciacao_taxa_anual", nullable = false)
    private double taxaAnual;

    @Column(name = "depreciacao_vida_util", nullable = false)
    private int vidaUtil;

    @Column(name = "depreciacao_taxa_mensal", nullable = false)
    private double taxaMensal;

}
