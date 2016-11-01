package dao;

import java.util.List;
import model.PatNotaFiscal;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class NotaFiscalDAO {

    public Boolean update(PatNotaFiscal notaFiscal) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(notaFiscal);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public Boolean insert(PatNotaFiscal notaFiscal) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(notaFiscal);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public Boolean delete(PatNotaFiscal notaFiscal) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(notaFiscal);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public List<PatNotaFiscal> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("from PatNotaFiscal as n ");
        List<PatNotaFiscal> ativos = query.list();
        session.getTransaction().commit();
        session.close();
        return ativos;
    }

    public PatNotaFiscal get(int nota) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        PatNotaFiscal notaFiscal = (PatNotaFiscal) session.get(PatNotaFiscal.class, nota);
        session.getTransaction().commit();
        session.close();
        return notaFiscal;
    }

}
