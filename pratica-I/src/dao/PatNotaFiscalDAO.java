package dao;

import java.util.List;
import model.PatNotaFiscal;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class PatNotaFiscalDAO {

    public Boolean alterar(PatNotaFiscal notaFiscal) {
        boolean ok = true;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.update(notaFiscal);
            session.getTransaction().commit();
        } catch (Exception e) {
            ok = false;
        } finally {
            session.close();
        }
        return ok;
    }

    public Boolean inserir(PatNotaFiscal notaFiscal) {
        boolean ok = true;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.save(notaFiscal);
            session.getTransaction().commit();
        } catch (Exception e) {
            ok = false;
        } finally {
            session.close();
        }
        return ok;
    }

    public Boolean excluir(PatNotaFiscal notaFiscal) {
        boolean ok = true;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.delete(notaFiscal);
            session.getTransaction().commit();
        } catch (Exception e) {
            ok = false;
        } finally {
            session.close();
        }
        return ok;
    }

    public Boolean inativaNotaFiscal(PatNotaFiscal notaFiscal) {
        boolean ok = true;
        notaFiscal.setNotaAtiva(false);
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.update(notaFiscal);
            session.getTransaction().commit();
        } catch (Exception e) {
            ok = false;
        } finally {
            session.close();
        }
        return ok;
    }

    public List<PatNotaFiscal> buscarTodos(int paginaBuscar, int indexfiltro, String filtro) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        String where = "";
        if (!filtro.isEmpty()) {
            switch (indexfiltro) {
                case 0:
                    where = " and n.notaCodigo = :p1";
                    break;
                case 1:
                    where = " and n.notaChaveAcesso like :p1";
                    break;
            }
        }
        Query query = session.createQuery("from PatNotaFiscal as n where n.notaAtiva = true " + where);
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
        query.setMaxResults(utils.Utils.MaxResultQuery);
        query.setFirstResult(paginaBuscar * utils.Utils.MaxResultQuery);
        List<PatNotaFiscal> ativos = query.list();
        session.getTransaction().commit();
        session.close();
        return ativos;
    }

    public PatNotaFiscal buscarUm(int nota) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        PatNotaFiscal notaFiscal = (PatNotaFiscal) session.get(PatNotaFiscal.class, nota);
        session.getTransaction().commit();
        session.close();
        return notaFiscal;
    }

}
