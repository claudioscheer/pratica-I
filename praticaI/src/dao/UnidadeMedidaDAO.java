package dao;

import java.util.List;
import model.EstUnidadeMedida;
import modelAntigo.UnidadeMedida;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class UnidadeMedidaDAO {    

    public boolean Inserir(EstUnidadeMedida unMedida) {
        Session session = HibernateUtil.getSessionFactory().openSession();
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

    public boolean Alterar(EstUnidadeMedida unMedida) {
        Session session = HibernateUtil.getSessionFactory().openSession();
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
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            EstUnidadeMedida unMedida = (EstUnidadeMedida) session.get(EstUnidadeMedida.class, codigo);
            session.delete(unMedida);                             
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.getTransaction().commit();
            session.close();            
        }
    }

    public List<EstUnidadeMedida> ListarTodas() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            Query query = session.createQuery("from EstUnidadeMedida as u ");
            List<EstUnidadeMedida> unMedida = query.list();
            session.getTransaction().commit();            
            return unMedida;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public UnidadeMedida Buscar(int codigo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            UnidadeMedida unMedida = (UnidadeMedida) session.get(UnidadeMedida.class, codigo);
            session.getTransaction().commit();            
            return unMedida;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }
}
