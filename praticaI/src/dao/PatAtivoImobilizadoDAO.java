package dao;

import java.util.List;
import model.PatAtivoImobilizado;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class PatAtivoImobilizadoDAO {

    public Boolean alterar(PatAtivoImobilizado ativoImobilizado) {
        boolean ok = true;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.update(ativoImobilizado);
            session.getTransaction().commit();
        } catch (Exception e) {
            ok = false;
        } finally {
            session.close();
        }
        return ok;
    }

    public Boolean inserir(PatAtivoImobilizado ativoImobilizado) {
        boolean ok = true;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.save(ativoImobilizado);
            session.getTransaction().commit();
        } catch (Exception e) {
            ok = false;
        } finally {
            session.close();
        }
        return ok;
    }

    public Boolean excluir(PatAtivoImobilizado ativoImobilizado) {
        boolean ok = true;
        ativoImobilizado.setAtivo(false);
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.update(ativoImobilizado);
            session.getTransaction().commit();
        } catch (Exception e) {
            ok = false;
        } finally {
            session.close();
        }
        return true;
    }

    public List<PatAtivoImobilizado> buscarTodos(int paginaBuscar, int indexfiltro, String filtro) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        String where = "";
        if (!filtro.isEmpty()) {
            switch (indexfiltro) {
                case 0:
                    where = " and a.ativoCodigo = :p1";
                    break;
                case 1:
                    where = " and a.ativoDescricao like :p1";
                    break;
            }
        }
        Query query = session.createQuery("from PatAtivoImobilizado a where a.ativo = true " + where);
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
        query.setFirstResult(paginaBuscar * utils.Utils.MaxResultQuery);
        query.setMaxResults(utils.Utils.MaxResultQuery);
        List<PatAtivoImobilizado> ativos = query.list();
        session.getTransaction().commit();
        session.close();
        return ativos;
    }

    public PatAtivoImobilizado buscarUm(int ativo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        PatAtivoImobilizado ativoImobilizado = (PatAtivoImobilizado) session.get(PatAtivoImobilizado.class, ativo);
        session.getTransaction().commit();
        session.close();
        return ativoImobilizado;
    }

    public List<PatAtivoImobilizado> buscarParaRelatorio() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("from PatAtivoImobilizado a left join fetch a.patItemNota where a.ativo = true ");
        List<PatAtivoImobilizado> ativos = query.list();
        session.getTransaction().commit();
        session.close();
        return ativos;
    }

    public List<PatAtivoImobilizado> buscarAtivosParaDepreciar() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("from PatAtivoImobilizado a where a.ativo = true and a.ativoDepreciavel = true ");
        List<PatAtivoImobilizado> ativos = query.list();
        session.getTransaction().commit();
        session.close();
        return ativos;
    }

}
