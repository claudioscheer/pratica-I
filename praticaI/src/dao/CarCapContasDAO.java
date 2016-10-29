package dao;

import java.util.List;
import model.CarCapContas;
import modelAntigo.AtivoImobilizado;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class CarCapContasDAO {

    public Boolean update(CarCapContas conta) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(conta);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public Boolean insert(CarCapContas conta) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(conta);
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

    public List<AtivoImobilizado> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("from AtivoImobilizado as a ");
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
