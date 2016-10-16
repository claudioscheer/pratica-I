package dao;

import java.util.List;
import model.Categoria;
import model.Marca;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class MarcaDAO {

    public Boolean insert(Marca marca) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(marca);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public List<Marca> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("from Marca as m ");
        List<Marca> marcas = query.list();
        session.getTransaction().commit();
        session.close();

        return marcas;
    }

    public Marca get(int m) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Marca marca = (Marca) session.get(Marca.class, m);
        session.getTransaction().commit();
        session.close();
        return marca;
    }

}
