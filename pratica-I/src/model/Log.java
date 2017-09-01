package model;

import enumeraveis.LogTipo;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;

@Entity
@SequenceGenerator(name = "seq_log", sequenceName = "seq_log", allocationSize = 1)
public class Log implements java.io.Serializable {

    private int logCodigo;
    private String logObjeto;
    private String logDescricao;
    private Date logData;
    private LogTipo logTipo;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_log")
    public int getLogCodigo() {
        return this.logCodigo;
    }

    public void setLogCodigo(int logCodigo) {
        this.logCodigo = logCodigo;
    }

    public String getLogObjeto() {
        return logObjeto;
    }

    public void setLogObjeto(String logObjeto) {
        this.logObjeto = logObjeto;
    }

    public String getLogDescricao() {
        return logDescricao;
    }

    public void setLogDescricao(String logDescricao) {
        this.logDescricao = logDescricao;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getLogData() {
        return logData;
    }

    public void setLogData(Date logData) {
        this.logData = logData;
    }

    public LogTipo getLogTipo() {
        return logTipo;
    }

    public void setLogTipo(LogTipo logTipo) {
        this.logTipo = logTipo;
    }

}
