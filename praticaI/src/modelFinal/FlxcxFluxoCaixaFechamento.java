package modelFinal;
// Generated 22/10/2016 10:09:26 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * FlxcxFluxoCaixaFechamento generated by hbm2java
 */
@Entity
@Table(name="flxcx_fluxo_caixa_fechamento"
    ,schema="public"
)
public class FlxcxFluxoCaixaFechamento  implements java.io.Serializable {


     private int fechCodigo;
     private Date fechData;
     private BigDecimal fechSaldoMes;
     private BigDecimal fechSaldoDisponivel;

    public FlxcxFluxoCaixaFechamento() {
    }

	
    public FlxcxFluxoCaixaFechamento(int fechCodigo) {
        this.fechCodigo = fechCodigo;
    }
    public FlxcxFluxoCaixaFechamento(int fechCodigo, Date fechData, BigDecimal fechSaldoMes, BigDecimal fechSaldoDisponivel) {
       this.fechCodigo = fechCodigo;
       this.fechData = fechData;
       this.fechSaldoMes = fechSaldoMes;
       this.fechSaldoDisponivel = fechSaldoDisponivel;
    }
   
     @Id 

    
    @Column(name="fech_codigo", unique=true, nullable=false)
    public int getFechCodigo() {
        return this.fechCodigo;
    }
    
    public void setFechCodigo(int fechCodigo) {
        this.fechCodigo = fechCodigo;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fech_data", length=13)
    public Date getFechData() {
        return this.fechData;
    }
    
    public void setFechData(Date fechData) {
        this.fechData = fechData;
    }

    
    @Column(name="fech_saldo_mes", precision=14)
    public BigDecimal getFechSaldoMes() {
        return this.fechSaldoMes;
    }
    
    public void setFechSaldoMes(BigDecimal fechSaldoMes) {
        this.fechSaldoMes = fechSaldoMes;
    }

    
    @Column(name="fech_saldo_disponivel", precision=14)
    public BigDecimal getFechSaldoDisponivel() {
        return this.fechSaldoDisponivel;
    }
    
    public void setFechSaldoDisponivel(BigDecimal fechSaldoDisponivel) {
        this.fechSaldoDisponivel = fechSaldoDisponivel;
    }




}


