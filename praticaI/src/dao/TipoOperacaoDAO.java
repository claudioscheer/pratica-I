
package dao;

import java.util.List;
import modelAntigo.TipoOperacao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class TipoOperacaoDAO {
       Session session = HibernateUtil.getSessionFactory().openSession();

    public boolean Inserir(TipoOperacao tipOperacao) {
        try {
            session.getTransaction().begin();
            session.save(tipOperacao);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public boolean Alterar(TipoOperacao tipOperacao) {
        try {
            session.getTransaction().begin();
            session.update(tipOperacao);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public boolean Excluir(int codigo) {
        try {
            TipoOperacao operacao = (TipoOperacao) session.get(TipoOperacao.class, codigo);
            session.delete(operacao);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public List<TipoOperacao> ListarTodas() {
        try {
            session.getTransaction().begin();
            Query query = session.createQuery("from TipoOperacao as t ");
            List<TipoOperacao> tipOperacao = query.list();
            session.getTransaction().commit();
            session.close();
            return tipOperacao;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public TipoOperacao Buscar(int codigo) {
        try {
            session.getTransaction().begin();
            TipoOperacao tipOperacao = (TipoOperacao) session.get(TipoOperacao.class, codigo);
            session.getTransaction().commit();
            session.close();
            return tipOperacao;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }
}
