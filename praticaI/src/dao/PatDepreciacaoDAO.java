package dao;

import java.util.Date;
import java.util.List;
import model.EstCategoria;
import model.PatAtivoImobilizado;
import model.PatDepreciacao;
import model.PatHistoricoDepreciacao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class PatDepreciacaoDAO {

    public boolean insert(PatDepreciacao depreciacao) {
        Session session = SessaoUnica.getSession(SessaoUnica.Tela.ATIVO_IMOBILIZADO);
        try {
            session.getTransaction().begin();
            session.save(depreciacao);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
        }
    }

    public boolean update(PatDepreciacao depreciacao) {
        Session session = SessaoUnica.getSession(SessaoUnica.Tela.ATIVO_IMOBILIZADO);
        try {
            session.getTransaction().begin();
            session.update(depreciacao);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
        }
    }

    public boolean delete(PatDepreciacao depreciacao) {
        Session session = SessaoUnica.getSession(SessaoUnica.Tela.ATIVO_IMOBILIZADO);
        try {
            session.getTransaction().begin();
            session.delete(depreciacao);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
        }
    }

    public List<PatDepreciacao> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            Query query = session.createQuery("from PatDepreciacao as t ");
            List<PatDepreciacao> depreciacao = query.list();
            session.getTransaction().commit();
            return depreciacao;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public PatDepreciacao get(int codigo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            PatDepreciacao depreciacao = (PatDepreciacao) session.get(PatDepreciacao.class, codigo);
            session.getTransaction().commit();
            return depreciacao;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public Boolean depreciarAtivoImobilizado(PatAtivoImobilizado ativo, Date dia) {
        PatHistoricoDepreciacao historico = new PatHistoricoDepreciacaoDAO().getDepreciacaoMes(ativo, dia);
        if (historico == null) {
            return false;
        }

        PatDepreciacao depreciacao = ativo.getEstCategoria().getDepreciacao();
        double valororiginal = ativo.getAtivoValorOriginal();

        double valordepreciar = valororiginal * depreciacao.getDepreciacaoTaxaMensal();

        ativo.setAtivoValorAtual(ativo.getAtivoValorAtual() - valordepreciar);

        return true;
    }

}
