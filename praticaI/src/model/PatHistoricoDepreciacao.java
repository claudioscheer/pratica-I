package model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "pat_historico_depreciacao", schema = "public")
public class PatHistoricoDepreciacao implements java.io.Serializable {

    private int historicoDepreciacaoCodigo;
    private PatAtivoImobilizado patAtivoImobilizado;
    private Date historicoDepreciacaoDia;
    private int historicoDepreciacaoMes;
    private Date historicoDepreciacaoAno;
    private double historicoDepreciacaoValor;

    @Id
    @Column(name = "historico_depreciacao_codigo", nullable = false)
    public int getHistoricoDepreciacaoCodigo() {
        return this.historicoDepreciacaoCodigo;
    }

    public void setHistoricoDepreciacaoCodigo(int historicoDepreciacaoCodigo) {
        this.historicoDepreciacaoCodigo = historicoDepreciacaoCodigo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ativo_imobilizado")
    public PatAtivoImobilizado getPatAtivoImobilizado() {
        return this.patAtivoImobilizado;
    }

    public void setPatAtivoImobilizado(PatAtivoImobilizado patAtivoImobilizado) {
        this.patAtivoImobilizado = patAtivoImobilizado;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "historico_depreciacao_dia", nullable = false, length = 13)
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

    @Temporal(TemporalType.DATE)
    @Column(name = "historico_depreciacao_ano", nullable = false, length = 13)
    public Date getHistoricoDepreciacaoAno() {
        return this.historicoDepreciacaoAno;
    }

    public void setHistoricoDepreciacaoAno(Date historicoDepreciacaoAno) {
        this.historicoDepreciacaoAno = historicoDepreciacaoAno;
    }

    @Column(name = "historico_depreciacao_valor", nullable = false, precision = 17, scale = 17)
    public double getHistoricoDepreciacaoValor() {
        return this.historicoDepreciacaoValor;
    }

    public void setHistoricoDepreciacaoValor(double historicoDepreciacaoValor) {
        this.historicoDepreciacaoValor = historicoDepreciacaoValor;
    }

}
