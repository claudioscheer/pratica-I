package dao;

import java.util.List;
import modelAntigo.AtivoImobilizado;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;
import utils.Utils;

public class AtivoImobilizadoDAO {

    public Boolean update(AtivoImobilizado ativoImobilizado) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(ativoImobilizado);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public Boolean insert(AtivoImobilizado ativoImobilizado) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(ativoImobilizado);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public Boolean delete(AtivoImobilizado ativoImobilizado) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(ativoImobilizado);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public List<AtivoImobilizado> getAll(int paginaBuscar) {
        System.out.println("---------------------------------------------"
                + "------------------------------------------------------"
                + "----------------------------------------- " + paginaBuscar);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("from AtivoImobilizado as a ");
        query.setFirstResult(paginaBuscar * Utils.MaxResultQuery);
        query.setMaxResults(Utils.MaxResultQuery);
        List<AtivoImobilizado> ativos = query.list();
        session.getTransaction().commit();
        session.close();
        return ativos;
    }

    public AtivoImobilizado get(int ativo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        AtivoImobilizado ativoImobilizado = (AtivoImobilizado) session.get(AtivoImobilizado.class, ativo);
        session.getTransaction().commit();
        session.close();
        return ativoImobilizado;
    }

}
