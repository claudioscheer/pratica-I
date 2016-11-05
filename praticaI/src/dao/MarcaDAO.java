package dao;

import java.util.List;
import model.EstMarca;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class MarcaDAO {

    public Boolean Inserir(EstMarca marca) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(marca);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public boolean Alterar(EstMarca marca) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.update(marca);
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
            EstMarca marca = (EstMarca) session.get(EstMarca.class, codigo);
            session.delete(marca);
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public List<EstMarca> ListarTodas() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("from EstMarca as m ");
        List<EstMarca> marcas = query.list();
        session.getTransaction().commit();
        session.close();
        return marcas;
    }

    public EstMarca Buscar(int codigo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        EstMarca marca = (EstMarca) session.get(EstMarca.class, codigo);
        session.getTransaction().commit();
        session.close();
        return marca;
    }

}
