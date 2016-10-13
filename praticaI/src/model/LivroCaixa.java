/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Alisson Ap
 */

@Entity
@SequenceGenerator(name = "seq_livro_caixa", sequenceName = "seq_livro_caixa", allocationSize = 1)
@Table(name = "flxCx_livro_caixa")
public class LivroCaixa implements Serializable {
   
   @Id 
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_livro_caixa")
   @Column(name = "lvr_cx_codigo")
   private int id;
   
   @Column(name = "lvr_cx_saldo_anterior")
   private double saldo_anterior;
   
   @Column(name = "lvr_cx_saldo_atual")
   private double saldo_atual;
   
   @Column(name = "lvr_cx_total_entrada")
   private double total_entrada;
   
   @Column(name = "lvr_cx_total_saida")
   private double total_saida;
   
   @Temporal(TemporalType.DATE)
   @Column(name = "lvr_cx_data_inicial")   
   private Date data_inicial;
   
   @Temporal(TemporalType.DATE)
   @Column(name = "lvr_cx_data_final")
   private Date data_final;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSaldo_anterior() {
        return saldo_anterior;
    }

    public void setSaldo_anterior(double saldo_anterior) {
        this.saldo_anterior = saldo_anterior;
    }

    public double getSaldo_atual() {
        return saldo_atual;
    }

    public void setSaldo_atual(double saldo_atual) {
        this.saldo_atual = saldo_atual;
    }

    public double getTotal_entrada() {
        return total_entrada;
    }

    public void setTotal_entrada(double total_entrada) {
        this.total_entrada = total_entrada;
    }

    public double getTotal_saida() {
        return total_saida;
    }

    public void setTotal_saida(double total_saida) {
        this.total_saida = total_saida;
    }

    public Date getData_inicial() {
        return data_inicial;
    }

    public void setData_inicial(Date data_inicial) {
        this.data_inicial = data_inicial;
    }

    public Date getData_final() {
        return data_final;
    }

    public void setData_final(Date data_final) {
        this.data_final = data_final;
    }
    
}
