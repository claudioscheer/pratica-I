/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelAntigo;

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
//trib_codigo,trib_descricao
@Entity
@SequenceGenerator(name = "seq_tributacao", sequenceName = "seq_tributacao", allocationSize = 1)
@Table(name = "flxCx_tributacao")
public class Tributacao implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tributacao")
    @Column(name = "trib_codigo")
    private int id;
    
    @Column(name = "trib_descricao")
    private String descricao;

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
    
    
    
}
