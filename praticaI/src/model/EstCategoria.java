package model;
// Generated 22/10/2016 10:09:26 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "est_categoria", schema = "public")
@SequenceGenerator(name = "seq_categoria", sequenceName = "seq_categoria", allocationSize = 1)
public class EstCategoria implements java.io.Serializable {

    private int categoriaId;
    private String categoriaDescricao;
    private PatDepreciacao depreciacao;

    private Set<EstProduto> estProdutos = new HashSet(0);
    private Set<PatDepreciacao> patDepreciacaos = new HashSet(0);
    private Set<PatAtivoImobilizado> patAtivoImobilizados = new HashSet(0);

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_categoria")
    @Column(name = "categoria_id", unique = true, nullable = false)
    public int getCategoriaId() {
        return this.categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    @Column(name = "categoria_descricao", nullable = false, length = 200)
    public String getCategoriaDescricao() {
        return this.categoriaDescricao;
    }

    public void setCategoriaDescricao(String categoriaDescricao) {
        this.categoriaDescricao = categoriaDescricao;
    }

    @OneToOne(mappedBy = "estCategoria", fetch = FetchType.EAGER)
    public PatDepreciacao getDepreciacao() {
        return depreciacao;
    }

    public void setDepreciacao(PatDepreciacao depreciacao) {
        this.depreciacao = depreciacao;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "estCategoria")
    public Set<EstProduto> getEstProdutos() {
        return this.estProdutos;
    }

    public void setEstProdutos(Set<EstProduto> estProdutos) {
        this.estProdutos = estProdutos;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "estCategoria")
    public Set<PatDepreciacao> getPatDepreciacaos() {
        return this.patDepreciacaos;
    }

    public void setPatDepreciacaos(Set<PatDepreciacao> patDepreciacaos) {
        this.patDepreciacaos = patDepreciacaos;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "estCategoria")
    public Set<PatAtivoImobilizado> getPatAtivoImobilizados() {
        return this.patAtivoImobilizados;
    }

    public void setPatAtivoImobilizados(Set<PatAtivoImobilizado> patAtivoImobilizados) {
        this.patAtivoImobilizados = patAtivoImobilizados;
    }

}
