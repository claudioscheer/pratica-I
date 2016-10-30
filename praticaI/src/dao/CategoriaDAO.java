package dao;

import java.util.List;
import model.EstCategoria;
import model.EstUnidadeMedida;
import modelAntigo.Categoria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class CategoriaDAO {

    public Boolean Inserir(EstCategoria categoria) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(categoria);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public boolean Alterar(EstCategoria categoria) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.update(categoria);
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
            EstCategoria categoria = (EstCategoria) session.get(EstCategoria.class, codigo);
            session.delete(categoria);                      
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.getTransaction().commit();
            session.close();            
        }
    }

    public List<EstCategoria> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("from EstCategoria as c ");
        List<EstCategoria> categorias = query.list();
        session.getTransaction().commit();
        session.close();

        return categorias;
    }

    public Categoria get(int c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Categoria categoria = (Categoria) session.get(Categoria.class, c);
        session.getTransaction().commit();
        session.close();
        return categoria;
    }

}
