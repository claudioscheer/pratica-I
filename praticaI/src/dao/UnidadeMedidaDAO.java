package dao;

import java.util.List;
import modelAntigo.UnidadeMedida;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class UnidadeMedidaDAO {

    Session session = HibernateUtil.getSessionFactory().openSession();

    public boolean Inserir(UnidadeMedida unMedida) {
        try {
            session.getTransaction().begin();
            session.save(unMedida);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public boolean Alterar(UnidadeMedida unMedida) {
        try {
            session.getTransaction().begin();
            session.update(unMedida);
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
            UnidadeMedida unMedida = (UnidadeMedida) session.get(UnidadeMedida.class, codigo);
            session.delete(unMedida);
            session.close();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public List<UnidadeMedida> ListarTodas() {
        try {
            session.getTransaction().begin();
            Query query = session.createQuery("from UnidadeMedida as u ");
            List<UnidadeMedida> unMedida = query.list();
            session.getTransaction().commit();
            session.close();
            return unMedida;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public UnidadeMedida Buscar(int codigo) {
        try {
            session.getTransaction().begin();
            UnidadeMedida unMedida = (UnidadeMedida) session.get(UnidadeMedida.class, codigo);
            session.getTransaction().commit();
            session.close();
            return unMedida;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }
}
