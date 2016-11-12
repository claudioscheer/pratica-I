package dao;

import java.util.List;
import model.PatNotaFiscal;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class PatNotaFiscalDAO {

    public Boolean update(PatNotaFiscal notaFiscal) {
        Session session = SessaoUnica.getSession(SessaoUnica.Tela.NOTA_FISCAL);
        try {
            session.getTransaction().begin();
            session.update(notaFiscal);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Boolean insert(PatNotaFiscal notaFiscal) {
        Session session = SessaoUnica.getSession(SessaoUnica.Tela.NOTA_FISCAL);
        try {
            session.getTransaction().begin();
            session.save(notaFiscal);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Boolean delete(PatNotaFiscal notaFiscal) {
        Session session = SessaoUnica.getSession(SessaoUnica.Tela.NOTA_FISCAL);
        try {
            session.getTransaction().begin();
            session.delete(notaFiscal);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Boolean inativaNotaFiscal(PatNotaFiscal notaFiscal) {
        notaFiscal.setNotaAtiva(false);
        Session session = SessaoUnica.getSession(SessaoUnica.Tela.NOTA_FISCAL);
        try {
            session.getTransaction().begin();
            session.update(notaFiscal);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<PatNotaFiscal> getAll(int paginaBuscar, int indexfiltro, String filtro) {
        Session session = SessaoUnica.getSession(SessaoUnica.Tela.NOTA_FISCAL);
        session.getTransaction().begin();
        String where = "";
        if (!filtro.isEmpty()) {
            switch (indexfiltro) {
                case 0:
                    where = " and n.notaCodigo = :p1";
                    break;
                case 1:
                    where = " and n.notaChaveAcesso like :p1";
                    break;
            }
        }
        Query query = session.createQuery("from PatNotaFiscal as n where n.notaAtiva = true " + where);
        if (!filtro.isEmpty()) {
            switch (indexfiltro) {
                case 0:
                    query.setParameter("p1", Integer.parseInt(filtro));
                    break;
                case 1:
                    query.setParameter("p1", "%" + filtro + "%");
                    break;
            }
        }
        query.setMaxResults(utils.Utils.MaxResultQuery);
        query.setFirstResult(paginaBuscar * utils.Utils.MaxResultQuery);
        List<PatNotaFiscal> ativos = query.list();
        session.getTransaction().commit();
        return ativos;
    }

    public PatNotaFiscal get(int nota) {
        Session session = SessaoUnica.getSession(SessaoUnica.Tela.NOTA_FISCAL);
        session.getTransaction().begin();
        PatNotaFiscal notaFiscal = (PatNotaFiscal) session.get(PatNotaFiscal.class, nota);
        session.getTransaction().commit();
        return notaFiscal;
    }

}
