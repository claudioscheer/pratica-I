/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelAntigo;

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
//fech_codigo,fech_data,fech_saldo_mes,fech_saldo_disponivel
@Entity
@SequenceGenerator(name = "seq_FlxCxFechamento", sequenceName = "seq_FlxCxFechamento", allocationSize = 1)
@Table(name = "flxCx_fluxo_caixa_fechamento")
public class FluxoCaixa_Fechamento implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_FlxCxFechamento")
    @Column(name = "fech_codigo")
    private int id;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fech_data")
    private Date data;
    
    @Column(name = "fech_saldo_mes")
    private double saldo_mes;
    
    @Column(name = "fech_saldo_disponivel")
    private double saldo_disponivel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getSaldo_mes() {
        return saldo_mes;
    }

    public void setSaldo_mes(double saldo_mes) {
        this.saldo_mes = saldo_mes;
    }

    public double getSaldo_disponivel() {
        return saldo_disponivel;
    }

    public void setSaldo_disponivel(double saldo_disponivel) {
        this.saldo_disponivel = saldo_disponivel;
    }

    
}
