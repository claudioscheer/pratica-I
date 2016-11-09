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

    public Boolean insert(PatHistoricoDepreciacao historicoDepreciacao) {
        Session session = SessaoUnica.getSession(SessaoUnica.Tela.ATIVO_IMOBILIZADO);
        session.getTransaction().begin();
        session.save(historicoDepreciacao);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public List<PatHistoricoDepreciacao> getAll(PatAtivoImobilizado ativo) {
        Session session = SessaoUnica.getSession(SessaoUnica.Tela.ATIVO_IMOBILIZADO);
        session.getTransaction().begin();
        Query query = session.createQuery("FROM PatHistoricoDepreciacao hd WHERE hd.ativoImobilizado.ativoImobilizado = :ativoImobilizado");
        query.setParameter("ativoImobilizado", ativo.getAtivoCodigo());
        List<PatHistoricoDepreciacao> marcas = query.list();
        session.getTransaction().commit();
        session.close();
        return marcas;
    }

    public PatHistoricoDepreciacao getUltimaDepreciacao() {
        Session session = SessaoUnica.getSession(SessaoUnica.Tela.ATIVO_IMOBILIZADO);
        session.getTransaction().begin();
        Query query = session.createQuery("FROM PatHistoricoDepreciacao hd ORDER BY hd.historicoDepreciacaoCodigo DESC");
        query.setMaxResults(1);
        List<PatHistoricoDepreciacao> historico = query.list();
        session.getTransaction().commit();
        session.close();
        if (historico.size() > 0) {
            return historico.get(0);
        }
        return null;
    }

    public PatHistoricoDepreciacao getDepreciacaoMes(PatAtivoImobilizado ativo, Date dia) {
        Calendar c = Calendar.getInstance();
        c.setTime(dia);

        Session session = SessaoUnica.getSession(SessaoUnica.Tela.ATIVO_IMOBILIZADO);
        session.getTransaction().begin();
        Query query = session.createQuery("FROM PatHistoricoDepreciacao hd "
                + "WHERE hd.patAtivoImobilizado = :ativo AND hd.historicoDepreciacaoMes = :mes "
                + "AND hd.historicoDepreciacaoAno = :ano");
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
