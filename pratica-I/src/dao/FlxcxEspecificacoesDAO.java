/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.FlxcxEspecificacoes;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author Diego
 */
public class FlxcxEspecificacoesDAO {
 
    
    public boolean Inserir(FlxcxEspecificacoes flxcxEspecificacoes) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.getTransaction().begin();
            session.save(flxcxEspecificacoes);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }

    }

    public boolean Alterar(FlxcxEspecificacoes flxcxEspecificacoes) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.getTransaction().begin();
            session.update(flxcxEspecificacoes);
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

            FlxcxEspecificacoes flxcxEspecificacoes = (FlxcxEspecificacoes) session.get(FlxcxEspecificacoes.class, codigo);
            session.delete(flxcxEspecificacoes);
            return true;

        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }

    }

    public List<FlxcxEspecificacoes> ListarTodas() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from FlxcxEspecificacoes as t ");

            List<FlxcxEspecificacoes> livrosCaixa = query.list();

            return livrosCaixa;

        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public FlxcxEspecificacoes Buscar(int codigo) {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            session.getTransaction().begin();

            FlxcxEspecificacoes flxcxEspecificacoes = (FlxcxEspecificacoes) session.get(FlxcxEspecificacoes.class, codigo);

            return flxcxEspecificacoes;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }
    
}
