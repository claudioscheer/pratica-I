package dao;

import java.util.List;
import model.PatBaixa;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class PatBaixaDAO {

    public boolean insert(PatBaixa baixa) {
        boolean ok = true;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            baixa.getPatAtivoImobilizado().setAtivo(false);
            session.update(baixa.getPatAtivoImobilizado());
            session.save(baixa);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            ok = false;
        } finally {
            session.close();
        }
        return ok;
    }

    public boolean update(PatBaixa baixa) {
        boolean ok = true;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.update(baixa);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            ok = false;
        } finally {
            session.close();
        }
        return ok;
    }

    public boolean delete(PatBaixa baixa) {
        boolean ok = true;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.delete(baixa);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            ok = false;
        } finally {
            session.close();
        }
        return ok;
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
