package dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import model.PatAtivoImobilizado;
import model.PatHistoricoDepreciacao;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class PatHistoricoDepreciacaoDAO {

    public Boolean inserir(PatHistoricoDepreciacao historicoDepreciacao) {
        boolean ok = true;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.update(historicoDepreciacao.getPatAtivoImobilizado());
            session.save(historicoDepreciacao);
            session.getTransaction().commit();
        } catch (Exception e) {
            ok = false;
        } finally {
            session.close();
        }
        return ok;
    }

    public List<PatHistoricoDepreciacao> buscarTodos(PatAtivoImobilizado ativo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("FROM PatHistoricoDepreciacao hd WHERE hd.ativoImobilizado.ativoImobilizado = :ativoImobilizado");
        query.setParameter("ativoImobilizado", ativo.getAtivoCodigo());
        List<PatHistoricoDepreciacao> marcas = query.list();
        session.getTransaction().commit();
        session.close();
        return marcas;
    }

    public PatHistoricoDepreciacao buscarUltimaDepreciacao(Calendar c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("FROM PatHistoricoDepreciacao hd WHERE hd.historicoDepreciacaoMes = :mes AND "
                + "hd.historicoDepreciacaoAno = :ano AND hd.historicoDepreciacaoValor != 0 "
                + "ORDER BY hd.historicoDepreciacaoCodigo DESC");
        query.setParameter("mes", c.get(Calendar.MONTH));
        query.setParameter("ano", c.get(Calendar.YEAR));
        query.setMaxResults(1);
        List<PatHistoricoDepreciacao> historico = query.list();
        session.getTransaction().commit();
        session.close();
        if (historico.size() > 0) {
            return historico.get(0);
        }
        return null;
    }

    public PatHistoricoDepreciacao buscarDepreciacaoMes(PatAtivoImobilizado ativo, Date dia) {
        Calendar c = Calendar.getInstance();
        c.setTime(dia);

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("FROM PatHistoricoDepreciacao hd "
                + "WHERE hd.patAtivoImobilizado = :ativo AND hd.historicoDepreciacaoMes = :mes "
                + "AND hd.historicoDepreciacaoAno = :ano AND hd.historicoDepreciacaoValor != 0");
        query.setParameter("mes", c.get(Calendar.MONTH));
        query.setParameter("ano", c.get(Calendar.YEAR));
        query.setParameter("ativo", ativo);
        query.setMaxResults(1);
        List<PatHistoricoDepreciacao> historico = query.list();
        session.getTransaction().commit();
        session.close();
        if (historico.size() > 0) {
            return historico.get(0);
        }
        return null;
    }

}
