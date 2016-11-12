package dao;

import java.util.List;
import model.PatAtivoImobilizado;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class PatAtivoImobilizadoDAO {

    public Boolean update(PatAtivoImobilizado ativoImobilizado) {
        Session session = SessaoUnica.getSession(SessaoUnica.Tela.ATIVO_IMOBILIZADO);
        session.getTransaction().begin();
        session.update(ativoImobilizado);
        session.getTransaction().commit();
        return true;
    }

    public Boolean insert(PatAtivoImobilizado ativoImobilizado) {
        Session session = SessaoUnica.getSession(SessaoUnica.Tela.ATIVO_IMOBILIZADO);
        session.getTransaction().begin();
        session.save(ativoImobilizado);
        session.getTransaction().commit();
        return true;
    }

    public Boolean delete(PatAtivoImobilizado ativoImobilizado) {
        ativoImobilizado.setAtivo(false);
        Session session = SessaoUnica.getSession(SessaoUnica.Tela.ATIVO_IMOBILIZADO);
        session.getTransaction().begin();
        session.update(ativoImobilizado);
        session.getTransaction().commit();
        return true;
    }

    public List<PatAtivoImobilizado> getAll(int paginaBuscar, int indexfiltro, String filtro) {
        Session session = SessaoUnica.getSession(SessaoUnica.Tela.ATIVO_IMOBILIZADO);
        session.getTransaction().begin();
        String where = "";
        if (!filtro.isEmpty()) {
            switch (indexfiltro) {
                case 0:
                    where = " and a.ativoCodigo = :p1";
                    break;
                case 1:
                    where = " and a.ativoDescricao like :p1";
                    break;
            }
        }
        Query query = session.createQuery("from PatAtivoImobilizado a where a.ativo = true " + where);
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
        List<PatAtivoImobilizado> ativos = query.list();
        session.getTransaction().commit();
        return ativos;
    }

    public PatAtivoImobilizado get(int ativo) {
        Session session = SessaoUnica.getSession(SessaoUnica.Tela.ATIVO_IMOBILIZADO);
        session.getTransaction().begin();
        PatAtivoImobilizado ativoImobilizado = (PatAtivoImobilizado) session.get(PatAtivoImobilizado.class, ativo);
        session.getTransaction().commit();
        return ativoImobilizado;
    }

    public List<PatAtivoImobilizado> getParaRelatorio() {
        Session session = SessaoUnica.getSession(SessaoUnica.Tela.ATIVO_IMOBILIZADO);
        session.getTransaction().begin();
        Query query = session.createQuery("from PatAtivoImobilizado a left join fetch a.patItemNota where a.ativo = true ");
        List<PatAtivoImobilizado> ativos = query.list();
        session.getTransaction().commit();
        return ativos;
    }

    public List<PatAtivoImobilizado> getAtivosParaDepreciar() {
        Session session = SessaoUnica.getSession(SessaoUnica.Tela.ATIVO_IMOBILIZADO);
        session.getTransaction().begin();
        Query query = session.createQuery("from PatAtivoImobilizado a where a.ativo = true and a.ativoDepreciavel = true ");
        List<PatAtivoImobilizado> ativos = query.list();
        session.getTransaction().commit();
        return ativos;
    }

}
