package dao;

import java.util.List;
import model.PatBaixa;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class PatBaixaDAO {

    public boolean insert(PatBaixa baixa) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.save(baixa);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public boolean update(PatBaixa baixa) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.update(baixa);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public boolean delete(PatBaixa baixa) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.delete(baixa);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public List<PatBaixa> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            Query query = session.createQuery("from PatBaixa as t ");
            List<PatBaixa> tipOperacao = query.list();
            session.getTransaction().commit();
            return tipOperacao;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public PatBaixa get(int codigo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            PatBaixa tipOperacao = (PatBaixa) session.get(PatBaixa.class, codigo);
            session.getTransaction().commit();
            return tipOperacao;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

}