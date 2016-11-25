package model;

import java.util.Date;
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
@Table(schema = "public")
public class PatHistoricoDepreciacao implements java.io.Serializable {

    private int historicoDepreciacaoCodigo;
    private String historicoDepreciacaoDescricao;
    private PatAtivoImobilizado patAtivoImobilizado;
    private Date historicoDepreciacaoDia;
    private int historicoDepreciacaoMes;
    private int historicoDepreciacaoAno;
    private double historicoDepreciacaoValor;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_historico_depreciacao")
    public int getHistoricoDepreciacaoCodigo() {
        return this.historicoDepreciacaoCodigo;
    }

    public void setHistoricoDepreciacaoCodigo(int historicoDepreciacaoCodigo) {
        this.historicoDepreciacaoCodigo = historicoDepreciacaoCodigo;
    }

    public String getHistoricoDepreciacaoDescricao() {
        return historicoDepreciacaoDescricao;
    }

    public void setHistoricoDepreciacaoDescricao(String historicoDepreciacaoDescricao) {
        this.historicoDepreciacaoDescricao = historicoDepreciacaoDescricao;
    }

    @ManyToOne
    public PatAtivoImobilizado getPatAtivoImobilizado() {
        return this.patAtivoImobilizado;
    }

    public void setPatAtivoImobilizado(PatAtivoImobilizado patAtivoImobilizado) {
        this.patAtivoImobilizado = patAtivoImobilizado;
    }

    @Temporal(TemporalType.DATE)
    public Date getHistoricoDepreciacaoDia() {
        return this.historicoDepreciacaoDia;
    }

    public void setHistoricoDepreciacaoDia(Date historicoDepreciacaoDia) {
        this.historicoDepreciacaoDia = historicoDepreciacaoDia;
    }

    public int getHistoricoDepreciacaoMes() {
        return this.historicoDepreciacaoMes;
    }

    public void setHistoricoDepreciacaoMes(int historicoDepreciacaoMes) {
        this.historicoDepreciacaoMes = historicoDepreciacaoMes;
    }

    public int getHistoricoDepreciacaoAno() {
        return this.historicoDepreciacaoAno;
    }

    public void setHistoricoDepreciacaoAno(int historicoDepreciacaoAno) {
        this.historicoDepreciacaoAno = historicoDepreciacaoAno;
    }

    public double getHistoricoDepreciacaoValor() {
        return this.historicoDepreciacaoValor;
    }

    public void setHistoricoDepreciacaoValor(double historicoDepreciacaoValor) {
        this.historicoDepreciacaoValor = historicoDepreciacaoValor;
    }

}
