package model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TipoBaixa")
public class TipoBaixa {

//    @Id
//    @Column(name = "codigo")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigo;

//    @Column(name = "descricao")
    private String descricao;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.codigo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoBaixa other = (TipoBaixa) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }

}
