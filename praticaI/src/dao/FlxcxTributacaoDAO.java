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

    private final Session session = HibernateUtil.getSessionFactory().openSession();

    public boolean Inserir(FlxcxTributacao tributacao) {

        try {
            this.session.getTransaction().begin();
            this.session.save(tributacao);
            this.session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            this.session.close();
        }

    }

    public boolean Alterar(FlxcxTributacao tributacao) {

        try {
            this.session.getTransaction().begin();
            this.session.update(tributacao);
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

            FlxcxTributacao tributacao = (FlxcxTributacao) session.get(FlxcxTributacao.class, codigo);
            this.session.delete(tributacao);
            return true;

        } catch (HibernateException e) {
            throw e;
        } finally {
            this.session.close();
        }

    }

    public List<FlxcxTributacao> ListarTodas() {
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("select t from FlxcxTributacao as t ");

            List<FlxcxTributacao> tributacao = query.list();

            return tributacao;

        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public FlxcxTributacao Buscar(int codigo) {
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
