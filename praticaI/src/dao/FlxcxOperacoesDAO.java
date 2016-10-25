/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.FlxcxOperacoes;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author Diego
 */
public class FlxcxOperacoesDAO {
    
     private final Session session = HibernateUtil.getSessionFactory().openSession();

    public boolean Inserir(FlxcxOperacoes operacoes) {

        try {
            this.session.getTransaction().begin();
            this.session.save(operacoes);
            this.session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            this.session.close();
        }

    }

    public boolean Alterar(FlxcxOperacoes operacoes) {

        try {
            this.session.getTransaction().begin();
            this.session.update(operacoes);
            this.session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            this.session.close();
        }

    }

    public boolean Excluir(int codigo) {

        try {

            FlxcxOperacoes operacoes = (FlxcxOperacoes) session.get(FlxcxOperacoes.class, codigo);
            this.session.delete(operacoes);
            return true;

        } catch (HibernateException e) {
            throw e;
        } finally {
            this.session.close();
        }

    }

    public List<FlxcxOperacoes> ListarTodas() {
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from FlxcxOperacoes as t ");

            List<FlxcxOperacoes> operacoes = query.list();

            return operacoes;

        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public FlxcxOperacoes Buscar(int codigo) {
        try {
            session.getTransaction().begin();

            FlxcxOperacoes tipOperacao = (FlxcxOperacoes) session.get(FlxcxOperacoes.class, codigo);

            return tipOperacao;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }
}
