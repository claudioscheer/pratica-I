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
@Table(schema = "public")
public class PatTipoBaixa implements java.io.Serializable {

    private int tipoBaixaCodigo;
    private String tipoBaixaDescricao;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tipo_baixa")
    public int getTipoBaixaCodigo() {
        return this.tipoBaixaCodigo;
    }

    public void setTipoBaixaCodigo(int tipoBaixaCodigo) {
        this.tipoBaixaCodigo = tipoBaixaCodigo;
    }

    public String getTipoBaixaDescricao() {
        return this.tipoBaixaDescricao;
    }

    public void setTipoBaixaDescricao(String tipoBaixaDescricao) {
        this.tipoBaixaDescricao = tipoBaixaDescricao;
    }

}
