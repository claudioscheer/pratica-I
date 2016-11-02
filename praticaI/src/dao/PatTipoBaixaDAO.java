package dao;

import java.util.List;
import model.PatTipoBaixa;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class PatTipoBaixaDAO {

    public boolean insert(PatTipoBaixa tipoBaixa) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.save(tipoBaixa);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public boolean update(PatTipoBaixa tipoBaixa) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.update(tipoBaixa);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public boolean delete(PatTipoBaixa tipoBaixa) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.delete(tipoBaixa);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public List<PatTipoBaixa> getall() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            Query query = session.createQuery("from PatTipoBaixa as t ");
            List<PatTipoBaixa> tipOperacao = query.list();
            session.getTransaction().commit();
            return tipOperacao;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public PatTipoBaixa get(int codigo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            PatTipoBaixa tipOperacao = (PatTipoBaixa) session.get(PatTipoBaixa.class, codigo);
            session.getTransaction().commit();
            return tipOperacao;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

}
