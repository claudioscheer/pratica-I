package dao;

import java.util.List;
import model.CarEstTipoOperacao;
import modelAntigo.TipoOperacao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class TipoOperacaoDAO {

    public boolean Inserir(CarEstTipoOperacao tipOperacao) {
        Session session = HibernateUtil.getSessionFactory().openSession();
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
        Session session = HibernateUtil.getSessionFactory().openSession();
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
        Session session = HibernateUtil.getSessionFactory().openSession();
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

    public List<CarEstTipoOperacao> ListarTodas() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            Query query = session.createQuery("from CarEstTipoOperacao as t ");
            List<CarEstTipoOperacao> tipOperacao = query.list();
            session.getTransaction().commit();            
            return tipOperacao;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public TipoOperacao Buscar(int codigo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            TipoOperacao tipOperacao = (TipoOperacao) session.get(TipoOperacao.class, codigo);
            session.getTransaction().commit();            
            return tipOperacao;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }
}
