package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name = "seq_baixa", sequenceName = "seq_baixa", allocationSize = 1)
@Table(name = "pat_baixa")
public class Baixa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_baixa")
    @Column(name = "baixa_codigo")
    private int baixa;

    @OneToOne
    @Column(name = "baixa_ativo_imobilizado", nullable = false)
    private AtivoImobilizado ativoImobilizado;

    @Temporal(TemporalType.DATE)
    @Column(name = "baixa_dia", nullable = false)
    private Date dataBaixa;

    @ManyToOne
    @Column(name = "baixa_tipo", nullable = false)
    private TipoBaixa tipoBaixa;

    public int getBaixa() {
        return baixa;
    }

    public void setBaixa(int baixa) {
        this.baixa = baixa;
    }

    public AtivoImobilizado getAtivoImobilizado() {
        return ativoImobilizado;
    }

    public void setAtivoImobilizado(AtivoImobilizado ativoImobilizado) {
        this.ativoImobilizado = ativoImobilizado;
    }

    public Date getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(Date dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    public TipoBaixa getTipoBaixa() {
        return tipoBaixa;
    }

    public void setTipoBaixa(TipoBaixa tipoBaixa) {
        this.tipoBaixa = tipoBaixa;
    }

}
