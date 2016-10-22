package dao;

import java.util.List;
import modelAntigo.Imposto;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class ImpostoDAO {

    public Boolean update(Imposto imposto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(imposto);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public Boolean insert(Imposto imposto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(imposto);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public Boolean delete(Imposto imposto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(imposto);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public List<Imposto> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("from Imposto as i ");
        List<Imposto> impostos = query.list();
        session.getTransaction().commit();
        session.close();
        return impostos;
    }

    public Imposto get(int impostoCod) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Imposto imposto = (Imposto) session.get(Imposto.class, impostoCod);
        session.getTransaction().commit();
        session.close();
        return imposto;
    }

}
