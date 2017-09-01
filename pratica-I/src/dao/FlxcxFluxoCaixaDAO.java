/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.FlxcxFluxoCaixa;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author Diego
 */
public class FlxcxFluxoCaixaDAO {

    
    public boolean Inserir(FlxcxFluxoCaixa fluxoCaixa) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.getTransaction().begin();
            session.save(fluxoCaixa);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }

    }

    public boolean Alterar(FlxcxFluxoCaixa fluxoCaixa) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.getTransaction().begin();
            session.update(fluxoCaixa);
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

            FlxcxFluxoCaixa fluxoCaixa = (FlxcxFluxoCaixa) session.get(FlxcxFluxoCaixa.class, codigo);
            session.delete(fluxoCaixa);
            return true;

        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }

    }

    public List<FlxcxFluxoCaixa> ListarTodas() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from FlxcxFluxoCaixa as t ");

            List<FlxcxFluxoCaixa> livrosCaixa = query.list();

            return livrosCaixa;

        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public FlxcxFluxoCaixa Buscar(int codigo) {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            session.getTransaction().begin();

            FlxcxFluxoCaixa fluxoCaixa = (FlxcxFluxoCaixa) session.get(FlxcxFluxoCaixa.class, codigo);

            return fluxoCaixa;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }
    
    
}
