package model;

import java.util.List;

public class ItemNota {

    private int codigo;
    private Produto produto;
    private NotaFiscal nota;
    private int quantidade;
    private double valorUnitario;
    private double valorTotal;
    private List<ImpostoItemNota> impostos;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public NotaFiscal getNota() {
        return nota;
    }

    public void setNota(NotaFiscal nota) {
        this.nota = nota;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ImpostoItemNota> getImpostos() {
        return impostos;
    }

    public void setImpostos(List<ImpostoItemNota> impostos) {
        this.impostos = impostos;
    }

}
