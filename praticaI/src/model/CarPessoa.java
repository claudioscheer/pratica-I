package model;
// Generated 22/10/2016 10:09:26 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * CarPessoa generated by hbm2java
 */
@Entity
@Table(name = "car_pessoa", schema = "public"
)
public class CarPessoa implements java.io.Serializable {

    private int pessoaId;
    private String pessoaNome;
    private Integer pessoaTipo;
    private String pessoaEndereco;
    private Long pessoaCnpjCpf;
    private Set<PatNotaFiscal> patNotaFiscals = new HashSet(0);
    private Set<CarCapContas> carCapContas = new HashSet(0);
    private Set<FlxcxFluxoCaixa> flxcxFluxoCaixas = new HashSet(0);
    private Set<FlxcxMovimentoBancario> flxcxMovimentoBancarios = new HashSet(0);

    public CarPessoa() {
    }

    public CarPessoa(int pessoaId, String pessoaNome) {
        this.pessoaId = pessoaId;
        this.pessoaNome = pessoaNome;
    }

    public CarPessoa(int pessoaId, String pessoaNome, Integer pessoaTipo, String pessoaEndereco, Long pessoaCnpjCpf, Set<PatNotaFiscal> patNotaFiscals, Set<CarCapContas> carCapContas, Set<FlxcxFluxoCaixa> flxcxFluxoCaixas, Set<FlxcxMovimentoBancario> flxcxMovimentoBancarios) {
        this.pessoaId = pessoaId;
        this.pessoaNome = pessoaNome;
        this.pessoaTipo = pessoaTipo;
        this.pessoaEndereco = pessoaEndereco;
        this.pessoaCnpjCpf = pessoaCnpjCpf;
        this.patNotaFiscals = patNotaFiscals;
        this.carCapContas = carCapContas;
        this.flxcxFluxoCaixas = flxcxFluxoCaixas;
        this.flxcxMovimentoBancarios = flxcxMovimentoBancarios;
    }

    @Id

    @Column(name = "pessoa_id", unique = true, nullable = false)
    public int getPessoaId() {
        return this.pessoaId;
    }

    public void setPessoaId(int pessoaId) {
        this.pessoaId = pessoaId;
    }

    @Column(name = "pessoa_nome", nullable = false, length = 100)
    public String getPessoaNome() {
        return this.pessoaNome;
    }

    public void setPessoaNome(String pessoaNome) {
        this.pessoaNome = pessoaNome;
    }

    @Column(name = "pessoa_tipo")
    public Integer getPessoaTipo() {
        return this.pessoaTipo;
    }

    public void setPessoaTipo(Integer pessoaTipo) {
        this.pessoaTipo = pessoaTipo;
    }

    @Column(name = "pessoa_endereco", length = 100)
    public String getPessoaEndereco() {
        return this.pessoaEndereco;
    }

    public void setPessoaEndereco(String pessoaEndereco) {
        this.pessoaEndereco = pessoaEndereco;
    }

    @Column(name = "pessoa_cnpj_cpf", precision = 14, scale = 0)
    public Long getPessoaCnpjCpf() {
        return this.pessoaCnpjCpf;
    }

    public void setPessoaCnpjCpf(Long pessoaCnpjCpf) {
        this.pessoaCnpjCpf = pessoaCnpjCpf;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "carPessoa")
    public Set<PatNotaFiscal> getPatNotaFiscals() {
        return this.patNotaFiscals;
    }

    public void setPatNotaFiscals(Set<PatNotaFiscal> patNotaFiscals) {
        this.patNotaFiscals = patNotaFiscals;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "carPessoa")
    public Set<CarCapContas> getCarCapContases() {
        return this.carCapContas;
    }

    public void setCarCapContases(Set<CarCapContas> carCapContas) {
        this.carCapContas = carCapContas;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "carPessoa")
    public Set<FlxcxFluxoCaixa> getFlxcxFluxoCaixas() {
        return this.flxcxFluxoCaixas;
    }

    public void setFlxcxFluxoCaixas(Set<FlxcxFluxoCaixa> flxcxFluxoCaixas) {
        this.flxcxFluxoCaixas = flxcxFluxoCaixas;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "carPessoa")
    public Set<FlxcxMovimentoBancario> getFlxcxMovimentoBancarios() {
        return this.flxcxMovimentoBancarios;
    }

    public void setFlxcxMovimentoBancarios(Set<FlxcxMovimentoBancario> flxcxMovimentoBancarios) {
        this.flxcxMovimentoBancarios = flxcxMovimentoBancarios;
    }

}
