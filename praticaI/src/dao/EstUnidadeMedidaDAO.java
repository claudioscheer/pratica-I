package dao;

import java.util.List;
import model.EstUnidadeMedida;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class EstUnidadeMedidaDAO {    

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
            Query query = session.createQuery("from EstUnidadeMedida as u ");
            List<EstUnidadeMedida> unMedida = query.list();                     
            return unMedida;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public EstUnidadeMedida Buscar(int codigo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            EstUnidadeMedida unMedida = (EstUnidadeMedida) session.get(EstUnidadeMedida.class, codigo);
            session.getTransaction().commit();            
            return unMedida;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }
}
