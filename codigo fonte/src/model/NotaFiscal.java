package model;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NotaFiscal")
public class NotaFiscal {

    @Id
    private int codigo;
    private double valor;
    private Date dataEmissao;
    private String chaveAcesso;
    private Date dataEntrada;
    private Fornecedor fornecedor;
    private List<ItemNota> itensNota;
    private List<ImpostoNotaFiscal> impostos;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getChaveAcesso() {
        return chaveAcesso;
    }

    public void setChaveAcesso(String chaveAcesso) {
        this.chaveAcesso = chaveAcesso;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<ItemNota> getItensNota() {
        return itensNota;
    }

    public void setItensNota(List<ItemNota> itensNota) {
        this.itensNota = itensNota;
    }

    public List<ImpostoNotaFiscal> getImpostos() {
        return impostos;
    }

    public void setImpostos(List<ImpostoNotaFiscal> impostos) {
        this.impostos = impostos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.codigo;
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
        final NotaFiscal other = (NotaFiscal) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }

}
