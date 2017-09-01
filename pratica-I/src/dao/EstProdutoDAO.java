package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.EstCategoria;
import model.EstMarca;
import model.EstProduto;
import model.EstUnidadeMedida;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jboss.logging.Logger;
import utils.HibernateUtil;

public class EstProdutoDAO
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
    public Boolean delete(int codigo)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try
        {
            session.getTransaction().begin();
            EstProduto produto = (EstProduto) session.get(EstProduto.class, codigo);
            session.delete(produto);
            return true;
        } catch (HibernateException e)
        {
            throw e;
        } finally
        {
            session.getTransaction().commit();
            session.close();
        }
    }

    public List<EstProduto> getAll()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from EstProduto ");
        List<EstProduto> produto = query.list();
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

    public void GerarRelatorioProdutos()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<EstProduto> listaProdutos = session.createQuery("from EstProduto").list();
        JRBeanCollectionDataSource jrs = new JRBeanCollectionDataSource(listaProdutos);
        try
        {
            JasperPrint jpr = JasperFillManager.fillReport("src/relatorios/estoque/RelatorioProduto.jasper", null, jrs);
            JasperViewer.viewReport(jpr, false);
        } catch (JRException ex) {
            System.out.println("" + ex);
        }
    }

    public EstProduto findById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        EstProduto produto = (EstProduto) session.get(EstProduto.class, id);
        return produto;
    }

    public List<EstProduto> buscarProdutos(String descricao, EstCategoria categoria, EstUnidadeMedida unMedida, EstMarca marca) {
        String hql = "FROM EstProduto WHERE lower(produtoDescricao) like lower(:produtoDescricao) ";
        hql += categoria == null ? "" : "and estcategoria_categoriaid = :estcategoria_categoriaid ";
        hql += marca == null ? "" : "and estmarca_marcaid = :estmarca_marcaid ";
        hql += unMedida == null ? "" : "and estunidademedida_unidademedidaid = :estunidademedida_unidademedidaid";

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery(hql);
        query.setParameter("produtoDescricao", descricao + "%");
        if (categoria != null) {
            query.setParameter("estcategoria_categoriaid", categoria.getCategoriaId());
        }
        if (marca != null) {
            query.setParameter("estmarca_marcaid", marca.getMarcaId());
        }
        if (unMedida != null) {
            query.setParameter("estunidademedida_unidademedidaid", unMedida.getUnidadeMedidaId());
        }
        List<EstProduto> produto = query.list();
        return produto;
    }
}
