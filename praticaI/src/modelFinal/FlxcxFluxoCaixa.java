package modelFinal;
// Generated 22/10/2016 10:09:26 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * FlxcxFluxoCaixa generated by hbm2java
 */
@Entity
@Table(name="flxcx_fluxo_caixa"
    ,schema="public"
)
public class FlxcxFluxoCaixa  implements java.io.Serializable {


     private int fluxCodigo;
     private CarPessoa carPessoa;
     private Integer fluxItem;
     private Date fluxData;
     private Integer fluxQtdColaboradores;
     private Set flxcxEspFluxoCaixas = new HashSet(0);

    public FlxcxFluxoCaixa() {
    }

	
    public FlxcxFluxoCaixa(int fluxCodigo, CarPessoa carPessoa) {
        this.fluxCodigo = fluxCodigo;
        this.carPessoa = carPessoa;
    }
    public FlxcxFluxoCaixa(int fluxCodigo, CarPessoa carPessoa, Integer fluxItem, Date fluxData, Integer fluxQtdColaboradores, Set flxcxEspFluxoCaixas) {
       this.fluxCodigo = fluxCodigo;
       this.carPessoa = carPessoa;
       this.fluxItem = fluxItem;
       this.fluxData = fluxData;
       this.fluxQtdColaboradores = fluxQtdColaboradores;
       this.flxcxEspFluxoCaixas = flxcxEspFluxoCaixas;
    }
   
     @Id 

    
    @Column(name="flux_codigo", unique=true, nullable=false)
    public int getFluxCodigo() {
        return this.fluxCodigo;
    }
    
    public void setFluxCodigo(int fluxCodigo) {
        this.fluxCodigo = fluxCodigo;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="pessoa_id", nullable=false)
    public CarPessoa getCarPessoa() {
        return this.carPessoa;
    }
    
    public void setCarPessoa(CarPessoa carPessoa) {
        this.carPessoa = carPessoa;
    }

    
    @Column(name="flux_item")
    public Integer getFluxItem() {
        return this.fluxItem;
    }
    
    public void setFluxItem(Integer fluxItem) {
        this.fluxItem = fluxItem;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="flux_data", length=13)
    public Date getFluxData() {
        return this.fluxData;
    }
    
    public void setFluxData(Date fluxData) {
        this.fluxData = fluxData;
    }

    
    @Column(name="flux_qtd_colaboradores")
    public Integer getFluxQtdColaboradores() {
        return this.fluxQtdColaboradores;
    }
    
    public void setFluxQtdColaboradores(Integer fluxQtdColaboradores) {
        this.fluxQtdColaboradores = fluxQtdColaboradores;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="flxcxFluxoCaixa")
    public Set getFlxcxEspFluxoCaixas() {
        return this.flxcxEspFluxoCaixas;
    }
    
    public void setFlxcxEspFluxoCaixas(Set flxcxEspFluxoCaixas) {
        this.flxcxEspFluxoCaixas = flxcxEspFluxoCaixas;
    }




}

