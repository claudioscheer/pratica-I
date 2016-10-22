package modelFinal;
// Generated 22/10/2016 10:09:26 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * FlxcxMovimentoBancario generated by hbm2java
 */
@Entity
@Table(name="flxcx_movimento_bancario"
    ,schema="public"
)
public class FlxcxMovimentoBancario  implements java.io.Serializable {


     private int movBanCodigo;
     private CarPessoa carPessoa;
     private FlxcxOperacoes flxcxOperacoes;
     private Date movBanData;
     private BigDecimal movBanDocumento;
     private BigDecimal movBanNumero;
     private BigDecimal movBanEntrada;
     private BigDecimal movBanSaida;
     private Set flxcxEspecificacoeses = new HashSet(0);

    public FlxcxMovimentoBancario() {
    }

	
    public FlxcxMovimentoBancario(int movBanCodigo, CarPessoa carPessoa, FlxcxOperacoes flxcxOperacoes) {
        this.movBanCodigo = movBanCodigo;
        this.carPessoa = carPessoa;
        this.flxcxOperacoes = flxcxOperacoes;
    }
    public FlxcxMovimentoBancario(int movBanCodigo, CarPessoa carPessoa, FlxcxOperacoes flxcxOperacoes, Date movBanData, BigDecimal movBanDocumento, BigDecimal movBanNumero, BigDecimal movBanEntrada, BigDecimal movBanSaida, Set flxcxEspecificacoeses) {
       this.movBanCodigo = movBanCodigo;
       this.carPessoa = carPessoa;
       this.flxcxOperacoes = flxcxOperacoes;
       this.movBanData = movBanData;
       this.movBanDocumento = movBanDocumento;
       this.movBanNumero = movBanNumero;
       this.movBanEntrada = movBanEntrada;
       this.movBanSaida = movBanSaida;
       this.flxcxEspecificacoeses = flxcxEspecificacoeses;
    }
   
     @Id 

    
    @Column(name="mov_ban_codigo", unique=true, nullable=false)
    public int getMovBanCodigo() {
        return this.movBanCodigo;
    }
    
    public void setMovBanCodigo(int movBanCodigo) {
        this.movBanCodigo = movBanCodigo;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="pessoa_id", nullable=false)
    public CarPessoa getCarPessoa() {
        return this.carPessoa;
    }
    
    public void setCarPessoa(CarPessoa carPessoa) {
        this.carPessoa = carPessoa;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="op_codigo", nullable=false)
    public FlxcxOperacoes getFlxcxOperacoes() {
        return this.flxcxOperacoes;
    }
    
    public void setFlxcxOperacoes(FlxcxOperacoes flxcxOperacoes) {
        this.flxcxOperacoes = flxcxOperacoes;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="mov_ban_data", length=13)
    public Date getMovBanData() {
        return this.movBanData;
    }
    
    public void setMovBanData(Date movBanData) {
        this.movBanData = movBanData;
    }

    
    @Column(name="mov_ban_documento", precision=30, scale=0)
    public BigDecimal getMovBanDocumento() {
        return this.movBanDocumento;
    }
    
    public void setMovBanDocumento(BigDecimal movBanDocumento) {
        this.movBanDocumento = movBanDocumento;
    }

    
    @Column(name="mov_ban_numero", precision=30, scale=0)
    public BigDecimal getMovBanNumero() {
        return this.movBanNumero;
    }
    
    public void setMovBanNumero(BigDecimal movBanNumero) {
        this.movBanNumero = movBanNumero;
    }

    
    @Column(name="mov_ban_entrada", precision=14)
    public BigDecimal getMovBanEntrada() {
        return this.movBanEntrada;
    }
    
    public void setMovBanEntrada(BigDecimal movBanEntrada) {
        this.movBanEntrada = movBanEntrada;
    }

    
    @Column(name="mov_ban_saida", precision=14)
    public BigDecimal getMovBanSaida() {
        return this.movBanSaida;
    }
    
    public void setMovBanSaida(BigDecimal movBanSaida) {
        this.movBanSaida = movBanSaida;
    }

@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="flxcx_movimentos_especificacoes", schema="public", joinColumns = { 
        @JoinColumn(name="mov_ban_codigo", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="esp_codigo", nullable=false, updatable=false) })
    public Set getFlxcxEspecificacoeses() {
        return this.flxcxEspecificacoeses;
    }
    
    public void setFlxcxEspecificacoeses(Set flxcxEspecificacoeses) {
        this.flxcxEspecificacoeses = flxcxEspecificacoeses;
    }




}


