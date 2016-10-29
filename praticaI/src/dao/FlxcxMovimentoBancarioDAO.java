/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.FlxcxMovimentoBancario;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author Diego
 */
public class FlxcxMovimentoBancarioDAO {
    
    public boolean Inserir(FlxcxMovimentoBancario  movBancario) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.getTransaction().begin();
            session.save( movBancario);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }

    }

    public boolean Alterar(FlxcxMovimentoBancario  movBancario) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.getTransaction().begin();
            session.update( movBancario);
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

            FlxcxMovimentoBancario  movBancario = (FlxcxMovimentoBancario) session.get(FlxcxMovimentoBancario.class, codigo);
            session.delete( movBancario);
            return true;

        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }

    }

    public List<FlxcxMovimentoBancario> ListarTodas() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from FlxcxMovimentoBancario as t ");

            List<FlxcxMovimentoBancario> livrosCaixa = query.list();

            return livrosCaixa;

        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public FlxcxMovimentoBancario Buscar(int codigo) {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            session.getTransaction().begin();

            FlxcxMovimentoBancario  movBancario = (FlxcxMovimentoBancario) session.get(FlxcxMovimentoBancario.class, codigo);

            return  movBancario;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }
}
