package model;

import java.util.Objects;

public class EspecificacaoProduto {

    private Produto produto;
    private Especificacao especificacao;
    private String descricao;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Especificacao getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(Especificacao especificacao) {
        this.especificacao = especificacao;
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
        hash = 89 * hash + Objects.hashCode(this.produto);
        hash = 89 * hash + Objects.hashCode(this.especificacao);
        hash = 89 * hash + Objects.hashCode(this.descricao);
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
        final EspecificacaoProduto other = (EspecificacaoProduto) obj;
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.produto, other.produto)) {
            return false;
        }
        if (!Objects.equals(this.especificacao, other.especificacao)) {
            return false;
        }
        return true;
    }

}
