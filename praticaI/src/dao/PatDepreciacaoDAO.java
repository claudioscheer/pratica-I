package dao;

import java.util.List;
import model.PatDepreciacao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class PatDepreciacaoDAO {

    public boolean insert(PatDepreciacao depreciacao) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.save(depreciacao);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public boolean update(PatDepreciacao depreciacao) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.update(depreciacao);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public boolean delete(PatDepreciacao depreciacao) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.delete(depreciacao);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            System.out.println("sijsidf");
            session.close();
        }
    }

    public List<PatDepreciacao> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            Query query = session.createQuery("from PatDepreciacao as t ");
            List<PatDepreciacao> depreciacao = query.list();
            session.getTransaction().commit();
            return depreciacao;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public PatDepreciacao get(int codigo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            PatDepreciacao depreciacao = (PatDepreciacao) session.get(PatDepreciacao.class, codigo);
            session.getTransaction().commit();
            return depreciacao;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

}
