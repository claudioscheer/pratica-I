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
 * EstMarca generated by hbm2java
 */
@Entity
@Table(name="est_marca"
    ,schema="public"
)
public class EstMarca  implements java.io.Serializable {


     private int marcaId;
     private String marcaDescricao;
     private Set patAtivoImobilizados = new HashSet(0);
     private Set estProdutos = new HashSet(0);

    public EstMarca() {
    }

	
    public EstMarca(int marcaId, String marcaDescricao) {
        this.marcaId = marcaId;
        this.marcaDescricao = marcaDescricao;
    }
    public EstMarca(int marcaId, String marcaDescricao, Set patAtivoImobilizados, Set estProdutos) {
       this.marcaId = marcaId;
       this.marcaDescricao = marcaDescricao;
       this.patAtivoImobilizados = patAtivoImobilizados;
       this.estProdutos = estProdutos;
    }
   
     @Id 

    
    @Column(name="marca_id", unique=true, nullable=false)
    public int getMarcaId() {
        return this.marcaId;
    }
    
    public void setMarcaId(int marcaId) {
        this.marcaId = marcaId;
    }

    
    @Column(name="marca_descricao", nullable=false, length=200)
    public String getMarcaDescricao() {
        return this.marcaDescricao;
    }
    
    public void setMarcaDescricao(String marcaDescricao) {
        this.marcaDescricao = marcaDescricao;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="estMarca")
    public Set getPatAtivoImobilizados() {
        return this.patAtivoImobilizados;
    }
    
    public void setPatAtivoImobilizados(Set patAtivoImobilizados) {
        this.patAtivoImobilizados = patAtivoImobilizados;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="estMarca")
    public Set getEstProdutos() {
        return this.estProdutos;
    }
    
    public void setEstProdutos(Set estProdutos) {
        this.estProdutos = estProdutos;
    }




}

