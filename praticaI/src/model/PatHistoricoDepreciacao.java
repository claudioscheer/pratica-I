package model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@SequenceGenerator(name = "seq_historico_depreciacao", sequenceName = "seq_historico_depreciacao", allocationSize = 1)
@Table(name = "pat_historico_depreciacao", schema = "public")
public class PatHistoricoDepreciacao implements java.io.Serializable {

    private int historicoDepreciacaoCodigo;
    private PatAtivoImobilizado patAtivoImobilizado;
    private Date historicoDepreciacaoDia;
    private int historicoDepreciacaoMes;
    private int historicoDepreciacaoAno;
    private double historicoDepreciacaoValor;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_historico_depreciacao")
    @Column(name = "historico_depreciacao_codigo", nullable = false)
    public int getHistoricoDepreciacaoCodigo() {
        return this.historicoDepreciacaoCodigo;
    }

    public void setHistoricoDepreciacaoCodigo(int historicoDepreciacaoCodigo) {
        this.historicoDepreciacaoCodigo = historicoDepreciacaoCodigo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ativo_imobilizado", nullable = false)
    public PatAtivoImobilizado getPatAtivoImobilizado() {
        return this.patAtivoImobilizado;
    }

    public void setPatAtivoImobilizado(PatAtivoImobilizado patAtivoImobilizado) {
        this.patAtivoImobilizado = patAtivoImobilizado;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "historico_depreciacao_dia", nullable = false)
    public Date getHistoricoDepreciacaoDia() {
        return this.historicoDepreciacaoDia;
    }

    public void setHistoricoDepreciacaoDia(Date historicoDepreciacaoDia) {
        this.historicoDepreciacaoDia = historicoDepreciacaoDia;
    }

    @Column(name = "historico_depreciacao_mes", nullable = false)
    public int getHistoricoDepreciacaoMes() {
        return this.historicoDepreciacaoMes;
    }

    public void setHistoricoDepreciacaoMes(int historicoDepreciacaoMes) {
        this.historicoDepreciacaoMes = historicoDepreciacaoMes;
    }

    @Column(name = "historico_depreciacao_ano", nullable = false)
    public int getHistoricoDepreciacaoAno() {
        return this.historicoDepreciacaoAno;
    }

    public void setHistoricoDepreciacaoAno(int historicoDepreciacaoAno) {
        this.historicoDepreciacaoAno = historicoDepreciacaoAno;
    }

    @Column(name = "historico_depreciacao_valor", nullable = false)
    public double getHistoricoDepreciacaoValor() {
        return this.historicoDepreciacaoValor;
    }

    public void setHistoricoDepreciacaoValor(double historicoDepreciacaoValor) {
        this.historicoDepreciacaoValor = historicoDepreciacaoValor;
    }

}
