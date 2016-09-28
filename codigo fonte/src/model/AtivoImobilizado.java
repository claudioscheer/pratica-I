package model;

public class AtivoImobilizado {

    private int codigo;
    private String descricao;
    private Categoria categoria;
    private Marca marca;
    private double valor;
    private double valorAtual;
    private double taxaValorResidual;
    private double valorResidual;
    private NotaFiscal nota;
    private EstadoBem estadoBem;
    private UtilizacaoBem utilizacao;

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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(double valorAtual) {
        this.valorAtual = valorAtual;
    }

    public double getTaxaValorResidual() {
        return taxaValorResidual;
    }

    public void setTaxaValorResidual(double taxaValorResidual) {
        this.taxaValorResidual = taxaValorResidual;
    }

    public double getValorResidual() {
        return valorResidual;
    }

    public void setValorResidual(double valorResidual) {
        this.valorResidual = valorResidual;
    }

    public NotaFiscal getNota() {
        return nota;
    }

    public void setNota(NotaFiscal nota) {
        this.nota = nota;
    }

    public EstadoBem getEstadoBem() {
        return estadoBem;
    }

    public void setEstadoBem(EstadoBem estadoBem) {
        this.estadoBem = estadoBem;
    }

    public UtilizacaoBem getUtilizacao() {
        return utilizacao;
    }

    public void setUtilizacao(UtilizacaoBem utilizacao) {
        this.utilizacao = utilizacao;
    }

    
    
}
