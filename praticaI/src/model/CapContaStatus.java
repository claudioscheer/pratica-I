package model;
// Generated 22/10/2016 10:09:26 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * CapContaStatus generated by hbm2java
 */
@Entity
@Table(name="cap_conta_status"
    ,schema="public"
)
public class CapContaStatus  implements java.io.Serializable {


     private int contaStatusId;
     private String contaStatusDescricao;
     private int contaStatusTipo;
     private Set carCapContases = new HashSet(0);

    public CapContaStatus() {
    }

	
    public CapContaStatus(int contaStatusId, String contaStatusDescricao, int contaStatusTipo) {
        this.contaStatusId = contaStatusId;
        this.contaStatusDescricao = contaStatusDescricao;
        this.contaStatusTipo = contaStatusTipo;
    }
    public CapContaStatus(int contaStatusId, String contaStatusDescricao, int contaStatusTipo, Set carCapContases) {
       this.contaStatusId = contaStatusId;
       this.contaStatusDescricao = contaStatusDescricao;
       this.contaStatusTipo = contaStatusTipo;
       this.carCapContases = carCapContases;
    }
   
     @Id 

    
    @Column(name="conta_status_id", unique=true, nullable=false)
    public int getContaStatusId() {
        return this.contaStatusId;
    }
    
    public void setContaStatusId(int contaStatusId) {
        this.contaStatusId = contaStatusId;
    }

    
    @Column(name="conta_status_descricao", nullable=false, length=50)
    public String getContaStatusDescricao() {
        return this.contaStatusDescricao;
    }
    
    public void setContaStatusDescricao(String contaStatusDescricao) {
        this.contaStatusDescricao = contaStatusDescricao;
    }

    
    @Column(name="conta_status_tipo", nullable=false)
    public int getContaStatusTipo() {
        return this.contaStatusTipo;
    }
    
    public void setContaStatusTipo(int contaStatusTipo) {
        this.contaStatusTipo = contaStatusTipo;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="capContaStatus")
    public Set getCarCapContases() {
        return this.carCapContases;
    }
    
    public void setCarCapContases(Set carCapContases) {
        this.carCapContases = carCapContases;
    }




}

