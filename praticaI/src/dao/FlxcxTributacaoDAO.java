/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.FlxcxTributacao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author Diego
 */
public class FlxcxTributacaoDAO {

    public boolean Inserir(FlxcxTributacao tributacao) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            session.getTransaction().begin();
            System.out.println("Aqui: " + session.isConnected());
            session.save(tributacao);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }

    }

    public boolean Alterar(FlxcxTributacao tributacao) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            session.getTransaction().begin();
            session.update(tributacao);
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

            FlxcxTributacao tributacao = (FlxcxTributacao) session.get(FlxcxTributacao.class, codigo);
            session.delete(tributacao);
            return true;

        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }

    }

    public List<FlxcxTributacao> ListarTodas() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from model.FlxcxTributacao as t ");

            List<FlxcxTributacao> tributacao = query.list();

            return tributacao;

        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public FlxcxTributacao Buscar(int codigo) {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            session.getTransaction().begin();

            FlxcxTributacao tipOperacao = (FlxcxTributacao) session.get(FlxcxTributacao.class, codigo);

            return tipOperacao;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

}
