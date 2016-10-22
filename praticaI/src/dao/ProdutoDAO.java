package dao;

import java.util.List;
import modelAntigo.Categoria;
import modelAntigo.Produto;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class ProdutoDAO {

    public Boolean insert(Produto produto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(produto);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public List<Produto> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("from Produto as p ");
        List<Produto> produtos = query.list();
        session.getTransaction().commit();
        session.close();

        return produtos;
    }

}
