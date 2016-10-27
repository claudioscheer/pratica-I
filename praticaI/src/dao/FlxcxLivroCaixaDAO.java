/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.FlxcxLivroCaixa;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author Diego
 */
public class FlxcxLivroCaixaDAO {

    public boolean Inserir(FlxcxLivroCaixa livroCaixa) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.getTransaction().begin();
            session.save(livroCaixa);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }

    }

    public boolean Alterar(FlxcxLivroCaixa livroCaixa) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.getTransaction().begin();
            session.update(livroCaixa);
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

            FlxcxLivroCaixa livroCaixa = (FlxcxLivroCaixa) session.get(FlxcxLivroCaixa.class, codigo);
            session.delete(livroCaixa);
            return true;

        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }

    }

    public List<FlxcxLivroCaixa> ListarTodas() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from FlxcxLivroCaixa as t ");

            List<FlxcxLivroCaixa> livrosCaixa = query.list();

            return livrosCaixa;

        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public FlxcxLivroCaixa Buscar(int codigo) {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            session.getTransaction().begin();

            FlxcxLivroCaixa livroCaixa = (FlxcxLivroCaixa) session.get(FlxcxLivroCaixa.class, codigo);

            return livroCaixa;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }
}
