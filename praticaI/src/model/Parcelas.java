package model;

import java.util.Date;

public class Parcelas {

    private Date dataPagamento;

    private Date dataVencimento;

    private Double acrescimos;

    private Operacoes contaDebito;

    private int valorParcela;

    private Double valorPago;

    private static class Operacoes {

        public Operacoes() {
        }
    }

}
