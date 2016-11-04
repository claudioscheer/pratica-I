package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "seq_tipo_baixa", sequenceName = "seq_tipo_baixa", allocationSize = 1)
@Table(name = "pat_tipo_baixa", schema = "public")
public class PatTipoBaixa implements java.io.Serializable {

    private int tipoBaixaCodigo;
    private String tipoBaixaDescricao;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tipo_baixa")
    @Column(name = "tipo_baixa_codigo", nullable = false)
    public int getTipoBaixaCodigo() {
        return this.tipoBaixaCodigo;
    }

    public void setTipoBaixaCodigo(int tipoBaixaCodigo) {
        this.tipoBaixaCodigo = tipoBaixaCodigo;
    }

    @Column(name = "tipo_baixa_descricao", nullable = false)
    public String getTipoBaixaDescricao() {
        return this.tipoBaixaDescricao;
    }

    public void setTipoBaixaDescricao(String tipoBaixaDescricao) {
        this.tipoBaixaDescricao = tipoBaixaDescricao;
    }

}