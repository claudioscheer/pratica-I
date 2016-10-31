package dao;

import java.util.List;
import modelAntigo.NotaFiscal;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class NotaFiscalDAO {

    public Boolean update(NotaFiscal notaFiscal) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(notaFiscal);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public Boolean insert(NotaFiscal notaFiscal) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(notaFiscal);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public Boolean delete(NotaFiscal notaFiscal) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(notaFiscal);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public List<NotaFiscal> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("from NotaFiscal as n ");
        List<NotaFiscal> ativos = query.list();
        session.getTransaction().commit();
        session.close();
        return ativos;
    }

    public NotaFiscal get(int nota) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        NotaFiscal notaFiscal = (NotaFiscal) session.get(NotaFiscal.class, nota);
        session.getTransaction().commit();
        session.close();
        return notaFiscal;
    }

}
