package dao;

import java.util.ArrayList;
import java.util.List;
import model.PatTipoBaixa;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class PatTipoBaixaDAO {

    public Boolean inserir(PatTipoBaixa tipoBaixa) {
        boolean ok = true;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.save(tipoBaixa);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            ok = false;
        } finally {
            session.close();
        }
        return ok;
    }

    public Boolean alterar(PatTipoBaixa tipoBaixa) {
        boolean ok = true;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.update(tipoBaixa);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            ok = false;
        } finally {
            session.close();
        }
        return ok;
    }

    public Boolean excluir(PatTipoBaixa tipoBaixa) {
        boolean ok = true;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.delete(tipoBaixa);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            ok = false;
        } finally {
            session.close();
        }
        return ok;
    }

    public List<PatTipoBaixa> buscarTodos(int paginaBuscar, int indexfiltro, String filtro) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<PatTipoBaixa> tipOperacao = new ArrayList<>();
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
            tipOperacao = query.list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
        return tipOperacao;
    }

    public PatTipoBaixa buscarUm(int codigo) {
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
