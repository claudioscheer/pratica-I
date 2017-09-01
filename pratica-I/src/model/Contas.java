/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Diego
 */
public class Contas {

    private Date dataColuna;
    private ArrayList<CarCapContas> valoresEntrada = new ArrayList<CarCapContas>();
    private ArrayList<CarCapContas> valoresSaida = new ArrayList<CarCapContas>();

    public Date getDataColuna() {
        return dataColuna;
    }

    public void setDataColuna(Date dataColuna) {
        this.dataColuna = dataColuna;
    }

    public ArrayList<CarCapContas> getValoresEntrada() {
        return valoresEntrada;
    }

    public void setValoresEntrada(ArrayList<CarCapContas> valoresEntrada) {
        this.valoresEntrada = valoresEntrada;
    }

    public ArrayList<CarCapContas> getValoresSaida() {
        return valoresSaida;
    }

    public void setValoresSaida(ArrayList<CarCapContas> valoresSaida) {
        this.valoresSaida = valoresSaida;
    }
}
