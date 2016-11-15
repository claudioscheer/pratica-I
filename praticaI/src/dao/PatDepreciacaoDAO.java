package dao;

import java.util.Calendar;
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
        boolean ok = true;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.save(depreciacao);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            ok = false;
        } finally {
            session.close();
        }
        return ok;
    }

    public boolean update(PatDepreciacao depreciacao) {
        boolean ok = true;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.update(depreciacao);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            ok = false;
        } finally {
            session.close();
        }
        return ok;
    }

    public boolean delete(PatDepreciacao depreciacao) {
        boolean ok = true;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.delete(depreciacao);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            ok = false;
        } finally {
            session.close();
        }
        return ok;
    }

    public List<PatDepreciacao> getAll() {
        List<PatDepreciacao> depreciacao = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            Query query = session.createQuery("from PatDepreciacao as t ");
            depreciacao = query.list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
        return depreciacao;
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

    public String depreciarAtivoImobilizado(PatAtivoImobilizado ativo, Date dia) {
        PatHistoricoDepreciacao historico = new PatHistoricoDepreciacaoDAO().getDepreciacaoMes(ativo, dia);
        if (historico != null) {
            return "O ativo " + ativo.getAtivoDescricao() + " já foi depreciado!";
        }

        Calendar c = Calendar.getInstance();
        c.setTime(dia);

        historico = new PatHistoricoDepreciacao();
        historico.setHistoricoDepreciacaoAno(c.get(Calendar.YEAR));
        historico.setHistoricoDepreciacaoMes(c.get(Calendar.MONTH));

        PatDepreciacao depreciacao = ativo.getEstCategoria().getDepreciacao();
        if (depreciacao == null) {
            return "A categoria " + ativo.getEstCategoria().getCategoriaDescricao() + " não possui depreciação relacionada!";
        }
        double valororiginal = ativo.getAtivoValorOriginal();
        double valordepreciar = valororiginal * (depreciacao.getDepreciacaoTaxaMensal() / 100);

        historico.setHistoricoDepreciacaoValor(valordepreciar);
        historico.setHistoricoDepreciacaoDia(Calendar.getInstance().getTime());
        ativo.setAtivoValorAtual(ativo.getAtivoValorAtual() - valordepreciar);
        historico.setPatAtivoImobilizado(ativo);

        new PatHistoricoDepreciacaoDAO().insert(historico);

        return "Depreciação do ativo " + ativo.getAtivoDescricao() + " realizada!";
    }

}
