package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "seq_tipo_baixa", sequenceName = "seq_tipo_baixa", allocationSize = 1)
@Table(name = "pat_tipo_baixa")
public class TipoBaixa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tipo_baixa")
    @Column(name = "tipo_baixa_codigo")
    private int tipoBaixa;

    @Column(name = "tipo_baixa_descricao", nullable = false)
    private String descricao;

    public int getTipoBaixa() {
        return tipoBaixa;
    }

    public void setTipoBaixa(int tipoBaixa) {
        this.tipoBaixa = tipoBaixa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
