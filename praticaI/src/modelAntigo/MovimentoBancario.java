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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Alisson Ap
 *///mov_ban_codigo,mov_ban_data,mov_ban_documento,mov_ban_numero,mov_ban_entrada
  //,mov_ban_saida,op_codigo,pessoa
@Entity
@SequenceGenerator(name = "seq_mov_banc", sequenceName = "seq_mov_banc", allocationSize = 1)
@Table(name = "flxCx_movimento_bancario")
public class MovimentoBancario implements Serializable {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mov_banc")
    @Column(name = "mov_ban_codigo")
    private int id;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "mov_ban_data")
    private Date data;
    
    @Column(name = "mov_ban_documento")
    private double documento;
    
    @Column(name = "mov_ban_numero")
    private double numero;
    
    @Column(name = "mov_ban_entrada")
    private double entrada;
    
    @Column(name = "mov_ban_saida")
    private double saida;
    
    
    @ManyToOne
    @Column(name = "op_codigo")
    private Operacoes operacao;
    
    @ManyToOne
    @Column(name = "pessoa_id")
    private Pessoa pessoa;
    
}
