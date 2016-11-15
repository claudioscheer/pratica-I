package model;
// Generated 22/10/2016 10:09:26 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

/**
 * EstProduto generated by hbm2java
 */
@Entity
@SequenceGenerator(name = "seq_produto", sequenceName = "seq_produto", allocationSize = 1)
public class EstProduto implements java.io.Serializable {

    private int produtoId;
    private EstCategoria estCategoria;
    private EstMarca estMarca;
    private EstUnidadeMedida estUnidadeMedida;
    private String produtoReferencia;
    private String produtoDescricao;
    private int produtoStatus;
    private Set<Saldo> saldos = new HashSet(0);
    private Set<PatItemNota> patItemNotas = new HashSet(0);
    private Set<EstMovimentacao> estMovimentacaos = new HashSet(0);
    private CarcapOperacoesComerciais carcapOperacoesComerciais;
    private CarCapContas carCapContas;

    public EstProduto() {
    }

    public EstProduto(int produtoId, String produtoReferencia, String produtoDescricao, int produtoStatus) {
        this.produtoId = produtoId;
        this.produtoReferencia = produtoReferencia;
        this.produtoDescricao = produtoDescricao;
        this.produtoStatus = produtoStatus;
    }

    public EstProduto(int produtoId, EstCategoria estCategoria, EstMarca estMarca, EstUnidadeMedida estUnidadeMedida, String produtoReferencia, String produtoDescricao, int produtoStatus, Set<Saldo> saldos, Set<PatItemNota> patItemNotas, Set<EstMovimentacao> estMovimentacaos) {
        this.produtoId = produtoId;
        this.estCategoria = estCategoria;
        this.estMarca = estMarca;
        this.estUnidadeMedida = estUnidadeMedida;
        this.produtoReferencia = produtoReferencia;
        this.produtoDescricao = produtoDescricao;
        this.produtoStatus = produtoStatus;
        this.saldos = saldos;
        this.patItemNotas = patItemNotas;
        this.estMovimentacaos = estMovimentacaos;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_produto")        
    public int getProdutoId() {
        return this.produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    @ManyToOne(fetch = FetchType.LAZY)    
    public EstCategoria getEstCategoria() {
        return this.estCategoria;
    }

    public void setEstCategoria(EstCategoria estCategoria) {
        this.estCategoria = estCategoria;
    }

    @ManyToOne(fetch = FetchType.LAZY)    
    public EstMarca getEstMarca() {
        return this.estMarca;
    }

    public void setEstMarca(EstMarca estMarca) {
        this.estMarca = estMarca;
    }

    @ManyToOne(fetch = FetchType.LAZY)    
    public EstUnidadeMedida getEstUnidadeMedida() {
        return this.estUnidadeMedida;
    }

    public void setEstUnidadeMedida(EstUnidadeMedida estUnidadeMedida) {
        this.estUnidadeMedida = estUnidadeMedida;
    }
    
    public String getProdutoReferencia() {
        return this.produtoReferencia;
    }

    public void setProdutoReferencia(String produtoReferencia) {
        this.produtoReferencia = produtoReferencia;
    }
    
    public String getProdutoDescricao() {
        return this.produtoDescricao;
    }

    public void setProdutoDescricao(String produtoDescricao) {
        this.produtoDescricao = produtoDescricao;
    }
    
    public int getProdutoStatus() {
        return this.produtoStatus;
    }

    public void setProdutoStatus(int produtoStatus) {
        this.produtoStatus = produtoStatus;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estProduto")
    public Set<Saldo> getSaldos() {
        return this.saldos;
    }

    public void setSaldos(Set<Saldo> saldos) {
        this.saldos = saldos;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estProduto")
    public Set<PatItemNota> getPatItemNotas() {
        return this.patItemNotas;
    }

    public void setPatItemNotas(Set<PatItemNota> patItemNotas) {
        this.patItemNotas = patItemNotas;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estProduto")
    public Set<EstMovimentacao> getEstMovimentacaos() {
        return this.estMovimentacaos;
    }

    public void setEstMovimentacaos(Set<EstMovimentacao> estMovimentacaos) {
        this.estMovimentacaos = estMovimentacaos;
    }

   
    @OneToOne
    public CarcapOperacoesComerciais getCarcapOperacoesComerciais() {
        return carcapOperacoesComerciais;
    }

    public void setCarcapOperacoesComerciais(CarcapOperacoesComerciais carcapOperacoesComerciais) {
        this.carcapOperacoesComerciais = carcapOperacoesComerciais;
    }


    @OneToOne(mappedBy = "produto")
    public CarCapContas getCarCapContas() {
        return carCapContas;
    }

    public void setCarCapContas(CarCapContas carCapContas) {
        this.carCapContas = carCapContas;
    }

}
