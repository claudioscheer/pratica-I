package model;
// Generated 22/10/2016 10:09:26 by Hibernate Tools 4.3.1

import com.sun.istack.internal.NotNull;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name = "seq_baixa", sequenceName = "seq_baixa", allocationSize = 1)
@Table(schema = "public")
public class PatBaixa implements java.io.Serializable {

    private int baixaCodigo;
    private PatAtivoImobilizado patAtivoImobilizado;
    private PatTipoBaixa patTipoBaixa;
    private Date baixaDataBaixa;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_baixa")
    @NotNull
    public int getBaixaCodigo() {
        return this.baixaCodigo;
    }

    public void setBaixaCodigo(int baixaCodigo) {
        this.baixaCodigo = baixaCodigo;
    }

    @NotNull
    @ManyToOne
    @JoinColumn(unique = true)
    public PatAtivoImobilizado getPatAtivoImobilizado() {
        return this.patAtivoImobilizado;
    }

    public void setPatAtivoImobilizado(PatAtivoImobilizado patAtivoImobilizado) {
        this.patAtivoImobilizado = patAtivoImobilizado;
    }

    @NotNull
    @ManyToOne
    public PatTipoBaixa getPatTipoBaixa() {
        return this.patTipoBaixa;
    }

    public void setPatTipoBaixa(PatTipoBaixa patTipoBaixa) {
        this.patTipoBaixa = patTipoBaixa;
    }

    @NotNull
    @Temporal(TemporalType.DATE)
    public Date getBaixaDataBaixa() {
        return this.baixaDataBaixa;
    }

    public void setBaixaDataBaixa(Date baixaDataBaixa) {
        this.baixaDataBaixa = baixaDataBaixa;
    }

}
