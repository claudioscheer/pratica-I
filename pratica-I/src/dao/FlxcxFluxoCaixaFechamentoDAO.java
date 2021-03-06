/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import enumeraveis.TipoConta;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import model.FlxcxFluxoCaixaFechamento;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import sun.util.calendar.Gregorian;
import utils.HibernateUtil;

/**
 *
 * @author Diego
 */
public class FlxcxFluxoCaixaFechamentoDAO {

    public boolean Inserir(FlxcxFluxoCaixaFechamento fluxoCaixaFechamento) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.getTransaction().begin();
            session.save(fluxoCaixaFechamento);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }

    }

    public boolean Alterar(FlxcxFluxoCaixaFechamento fluxoCaixaFechamento) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.getTransaction().begin();
            session.update(fluxoCaixaFechamento);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }

    }

    public boolean Excluir(int codigo) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            FlxcxFluxoCaixaFechamento fluxoCaixaFechamento = (FlxcxFluxoCaixaFechamento) session.get(FlxcxFluxoCaixaFechamento.class, codigo);
            session.delete(fluxoCaixaFechamento);
            return true;

        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }

    }

    public List<FlxcxFluxoCaixaFechamento> ListarTodas() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from FlxcxFluxoCaixaFechamento as t ");

            List<FlxcxFluxoCaixaFechamento> livrosCaixa = query.list();

            return livrosCaixa;

        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public FlxcxFluxoCaixaFechamento Buscar(int codigo) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.getTransaction().begin();

            FlxcxFluxoCaixaFechamento fluxoCaixaFechamento = (FlxcxFluxoCaixaFechamento) session.get(FlxcxFluxoCaixaFechamento.class, codigo);

            return fluxoCaixaFechamento;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public FlxcxFluxoCaixaFechamento BuscarUltimoSaldo() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from FlxcxFluxoCaixaFechamento as t ");

            List<FlxcxFluxoCaixaFechamento> livrosCaixa = query.list();

             Calendar c = Calendar.getInstance();

            c.set(1900, 1,1); //Valor inicial pra comparacao.
            
            Date ultimaData = c.getTime();
            FlxcxFluxoCaixaFechamento ultimoSaldo = new FlxcxFluxoCaixaFechamento();
            for (FlxcxFluxoCaixaFechamento f : livrosCaixa){
            
                if (ultimaData.before(f.getFechData())){
                
                    ultimoSaldo = f;
                }
                
            }
            
            return ultimoSaldo;

        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    
    public FlxcxFluxoCaixaFechamento BuscarSaldoMes(int mes, int ano) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from FlxcxFluxoCaixaFechamento as t where mesEquivalente = :mes and anoEquivalente = :ano ");

            query.setParameter("mes", mes);
            query.setParameter("ano", ano);
            
            List<FlxcxFluxoCaixaFechamento> livrosCaixa = query.list();

             Calendar c = Calendar.getInstance();

            c.set(1900, 1,1); //Valor inicial pra comparacao.
            
            Date ultimaData = c.getTime();
            
            FlxcxFluxoCaixaFechamento ultimoSaldo = new FlxcxFluxoCaixaFechamento();
            
            for (FlxcxFluxoCaixaFechamento f : livrosCaixa){
            
                ultimoSaldo = f;
                break;
                
            }
                                  
            return ultimoSaldo;

        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }
    
    
    public void VerificaFluxoCaixa() {

        //se tem algum registro no fechamento que tem o ano e mes em branco
        Calendar c = Calendar.getInstance();

        c.set(Calendar.DAY_OF_MONTH, 1);

        Date dataInicial = c.getTime();

        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));

        Date dataFinal = c.getTime();

    }
    
    public boolean verificaLancamentoInicial(){
        
        Session session = HibernateUtil.getSessionFactory().openSession();

            session.getTransaction().begin();

            Query query = session.createQuery("from FlxcxFluxoCaixaFechamento as t ");

            FlxcxFluxoCaixaFechamento rs = (FlxcxFluxoCaixaFechamento) query.uniqueResult();

            
            if(rs != null){
                
                return true;
                
            }else{
                
                
                return false;
            }
        
    }

    public void FecharCaixa(Date dataInicial, Date dataFinal) {

//        insert fechamento, em branco mes e ano
        CarCapContasDAO contasDAO = new CarCapContasDAO();

        Calendar c = Calendar.getInstance();
        
        c.setTime(dataInicial);
        c.add(Calendar.DAY_OF_MONTH, -1);

        Date dtIni = c.getTime();
        
        c.setTime(dataFinal);
        c.add(Calendar.DAY_OF_MONTH, -1);

        Date dtFim = c.getTime();
        
        
        double entrada = contasDAO.SomarContas(TipoConta.Entrada, dtIni, dtFim);

        double saida = contasDAO.SomarContas(TipoConta.Saida, dtIni, dtFim);

        FlxcxFluxoCaixaFechamento ultimoSaldo = BuscarUltimoSaldo();
        
        double finalMes = entrada - saida;
        
        double saldodisponivel = ultimoSaldo.getFechSaldoDisponivel() + finalMes;
        
        FlxcxFluxoCaixaFechamento fx = new FlxcxFluxoCaixaFechamento();

        
        Date data = new GregorianCalendar().getTime();
        
        fx.setFechData(data);
        fx.setMesEquivalente(Integer.valueOf(new SimpleDateFormat("MM").format(dtIni)));
        fx.setAnoEquivalente(Integer.valueOf(new SimpleDateFormat("yyyy").format(dtFim)));
        
        fx.setFechSaldoDisponivel(saldodisponivel);
        fx.setFechSaldoMes(finalMes);
        Inserir(fx);
        
        //update fechamento
    }

}
