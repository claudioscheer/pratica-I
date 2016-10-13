/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Alisson Ap
 */
//flux_codigo,flux_item,flux_data,flux_qtd_colaboradores,pessoa
@Entity
@SequenceGenerator(name = "seq_fluxoCaixa", sequenceName = "seq_fluxoCaixa", allocationSize = 1)
@Table(name = "flxCx_fluxo_caixa")
public class FluxodeCaixa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_fluxoCaixa")
    @Column(name = "flux_codigo")
    private int id;
    
    @Column(name = "flux_item")
    private int item;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "flux_data")
    private Date data;
    
    @Column(name = "flux_qtd_colaboradores")
    private int qtdColaboradores;
    
    @ManyToOne
    @Column(name = "pessoa_id")
    private Pessoa pessoa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getQtdColaboradores() {
        return qtdColaboradores;
    }

    public void setQtdColaboradores(int qtdColaboradores) {
        this.qtdColaboradores = qtdColaboradores;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
}
