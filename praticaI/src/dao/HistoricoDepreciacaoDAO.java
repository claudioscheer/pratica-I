package dao;

import java.util.List;
import model.AtivoImobilizado;
import model.HistoricoDepreciacao;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class HistoricoDepreciacaoDAO {

    public Boolean insert(HistoricoDepreciacao historicoDepreciacao) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(historicoDepreciacao);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public List<HistoricoDepreciacao> getAll(AtivoImobilizado ativo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("from HistoricoDepreciacao hd where hd.ativoImobilizado.ativoImobilizado = :ativoImobilizado");
        query.setParameter("ativoImobilizado", ativo.getAtivoImobilizado());
        List<HistoricoDepreciacao> marcas = query.list();
        session.getTransaction().commit();
        session.close();
        return marcas;
    }

}
