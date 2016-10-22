package dao;

import java.util.List;
import modelAntigo.Categoria;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class CategoriaDAO {

    public Boolean insert(Categoria categoria) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(categoria);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public List<Categoria> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("from Categoria as c ");
        List<Categoria> categorias = query.list();
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
