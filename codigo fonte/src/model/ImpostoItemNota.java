package model;

public class ImpostoItemNota {

    private ItemNota itemNota;
    private Imposto imposto;
    private double valor;

    public ItemNota getItemNota() {
        return itemNota;
    }

    public void setItemNota(ItemNota itemNota) {
        this.itemNota = itemNota;
    }

    public Imposto getImposto() {
        return imposto;
    }

    public void setImposto(Imposto imposto) {
        this.imposto = imposto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
