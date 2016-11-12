package dao;

import java.util.List;
import model.Log;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class LogDAO {

    public boolean insert(Log tipoBaixa) {
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

    public boolean update(Log tipoBaixa) {
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

    public boolean delete(Log tipoBaixa) {
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

    public List<Log> getAll(int paginaBuscar, int indexfiltro, String filtro) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            String where = "";
            if (!filtro.isEmpty()) {
                switch (indexfiltro) {
                    case 0:
                        where = " AND t.tipoBaixaCodigo = :p1";
                        break;
                    case 1:
                        where = " AND t.tipoBaixaDescricao like :p1";
                        break;
                }
            }
            Query query = session.createQuery("FROM PatTipoBaixa t WHERE 1 = 1 " + where);
            query.setMaxResults(utils.Utils.MaxResultQuery);
            query.setFirstResult(paginaBuscar * utils.Utils.MaxResultQuery);
            if (!filtro.isEmpty()) {
                switch (indexfiltro) {
                    case 0:
                        query.setParameter("p1", Integer.parseInt(filtro));
                        break;
                    case 1:
                        query.setParameter("p1", "%" + filtro + "%");
                        break;
                }
            }
            List<Log> tipOperacao = query.list();
            session.getTransaction().commit();
            return tipOperacao;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public Log get(int codigo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            Log tipOperacao = (Log) session.get(Log.class, codigo);
            session.getTransaction().commit();
            return tipOperacao;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

}
