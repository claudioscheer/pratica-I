package dao;

import java.util.List;
import model.PatAtivoImobilizado;
import model.PatHistoricoDepreciacao;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class HistoricoDepreciacaoDAO {

    public Boolean insert(PatHistoricoDepreciacao historicoDepreciacao) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(historicoDepreciacao);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public List<PatHistoricoDepreciacao> getAll(PatAtivoImobilizado ativo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("from HistoricoDepreciacao hd where hd.ativoImobilizado.ativoImobilizado = :ativoImobilizado");
        query.setParameter("ativoImobilizado", ativo.getAtivoCodigo());
        List<PatHistoricoDepreciacao> marcas = query.list();
        session.getTransaction().commit();
        session.close();
        return marcas;
    }

}
