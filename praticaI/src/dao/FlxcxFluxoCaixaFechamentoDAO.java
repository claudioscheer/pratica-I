/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.FlxcxFluxoCaixaFechamento;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
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
}
