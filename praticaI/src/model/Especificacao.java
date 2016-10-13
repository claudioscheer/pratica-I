/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Alisson Ap
 */
//flxCx_especificacoes" (esp_codigo,esp_descricao,esp_codigo_pai

@Entity
@SequenceGenerator(name = "seq_especificacao", sequenceName = "seq_especificacao", allocationSize = 1)
@Table(name = "flxCx_especificacoes")
public class Especificacao implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_especificacao")
    @Column(name = "esp_codigo")
    private int id;
    
    @Column(name = "esp_descricao")
    private String descricao;
    
    @Column(name = "esp_codigo_pai")
    private int codigoPai;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCodigoPai() {
        return codigoPai;
    }

    public void setCodigoPai(int codigoPai) {
        this.codigoPai = codigoPai;
    }
    
}
