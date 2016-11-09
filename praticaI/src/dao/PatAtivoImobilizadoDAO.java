package dao;

import java.util.List;
import model.PatAtivoImobilizado;
import org.hibernate.Query;
import org.hibernate.Session;

public  class PatAtivoImobilizadoDAO {

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

    public List<PatAtivoImobilizado> getAll(int paginaBuscar) {
        Session session = SessaoUnica.getSession(SessaoUnica.Tela.ATIVO_IMOBILIZADO);
        session.getTransaction().begin();
        Query query = session.createQuery("from PatAtivoImobilizado as a ");
//        query.setFirstResult(paginaBuscar * Utils.MaxResultQuery);
//        query.setMaxResults(Utils.MaxResultQuery);
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

}
