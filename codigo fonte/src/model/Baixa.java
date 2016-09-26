package model;

import java.util.Date;

public class Baixa {

    private int codigo;
    private AtivoImobilizado ativoImobilizado;
    private Date dataBaixa;
    private TipoBaixa tipoBaixa;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public AtivoImobilizado getAtivoImobilizado() {
        return ativoImobilizado;
    }

    public void setAtivoImobilizado(AtivoImobilizado ativoImobilizado) {
        this.ativoImobilizado = ativoImobilizado;
    }

    public Date getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(Date dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    public TipoBaixa getTipoBaixa() {
        return tipoBaixa;
    }

    public void setTipoBaixa(TipoBaixa tipoBaixa) {
        this.tipoBaixa = tipoBaixa;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.codigo;
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
        final Baixa other = (Baixa) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }

}
