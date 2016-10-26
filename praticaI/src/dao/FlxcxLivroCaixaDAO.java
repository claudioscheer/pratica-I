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
     private final Session session = HibernateUtil.getSessionFactory().openSession();

    public boolean Inserir(FlxcxLivroCaixa livroCaixa) {

        try {
            this.session.getTransaction().begin();
            this.session.save(livroCaixa);
            this.session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            this.session.close();
        }

    }

    public boolean Alterar(FlxcxLivroCaixa livroCaixa) {

        try {
            this.session.getTransaction().begin();
            this.session.update(livroCaixa);
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

            FlxcxLivroCaixa livroCaixa = (FlxcxLivroCaixa) session.get(FlxcxLivroCaixa.class, codigo);
            this.session.delete(livroCaixa);
            return true;

        } catch (HibernateException e) {
            throw e;
        } finally {
            this.session.close();
        }

    }

    public List<FlxcxLivroCaixa> ListarTodas() {
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
