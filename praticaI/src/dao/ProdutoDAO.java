package dao;

import java.util.List;
import model.EstProduto;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class ProdutoDAO
{

    public Boolean update(EstProduto produto)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(produto);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public Boolean insert(EstProduto produto)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(produto);
        session.getTransaction().commit();
        session.close();
        return true;
    }
     /* movimentação fazer getAll  Est Produto  */
    public Boolean delete(EstProduto produto)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(produto);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public List<EstProduto> getAll()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("from est_produto ");
        List<EstProduto> produto = query.list();
        session.getTransaction().commit();
        session.close();
        return produto;
    }

    public EstProduto get(int value)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        EstProduto produto = (EstProduto) session.get(EstProduto.class, value);
        session.getTransaction().commit();
        session.close();
        return produto;
    }

}
