package dao;

import java.util.List;
import model.PatAtivoImobilizado;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;
import utils.Utils;

public class PatAtivoImobilizadoDAO {

    public Boolean update(PatAtivoImobilizado ativoImobilizado) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(ativoImobilizado);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public Boolean insert(PatAtivoImobilizado ativoImobilizado) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(ativoImobilizado);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public Boolean delete(PatAtivoImobilizado ativoImobilizado) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(ativoImobilizado);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public List<PatAtivoImobilizado> getAll(int paginaBuscar) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("from PatAtivoImobilizado as a ");
//        query.setFirstResult(paginaBuscar * Utils.MaxResultQuery);
//        query.setMaxResults(Utils.MaxResultQuery);
        List<PatAtivoImobilizado> ativos = query.list();
        session.getTransaction().commit();
        session.close();
        return ativos;
    }

    public PatAtivoImobilizado get(int ativo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        PatAtivoImobilizado ativoImobilizado = (PatAtivoImobilizado) session.get(PatAtivoImobilizado.class, ativo);
        session.getTransaction().commit();
        session.close();
        return ativoImobilizado;
    }

}
