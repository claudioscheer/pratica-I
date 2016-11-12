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

public class EstProdutoDAO {

    public Boolean update(EstProduto produto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(produto);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public Boolean insert(EstProduto produto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(produto);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    /* movimentação fazer getAll  Est Produto  */
    public Boolean delete(int codigo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            EstProduto produto = (EstProduto) session.get(EstProduto.class, codigo);
            session.delete(produto);
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public List<EstProduto> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from EstProduto ");
        List<EstProduto> produto = query.list();
        return produto;
    }

    public EstProduto get(int value) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        EstProduto produto = (EstProduto) session.get(EstProduto.class, value);
        session.getTransaction().commit();
        session.close();
        return produto;
    }

    public void GerarRelatorioProdutos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<EstProduto> listaProdutos = session.createQuery("from EstProduto").list();
        JRBeanCollectionDataSource jrs = new JRBeanCollectionDataSource(listaProdutos);
        Map parametros = new HashMap();
        try {
            JasperPrint jpr = JasperFillManager.fillReport("src/relatorios/estoque/relatorio_produtos.jasper", null, jrs);
            JasperViewer.viewReport(jpr, true);
        } catch (JRException ex) {
            System.out.println("" + ex);
        }
    }

    public List<EstProduto> buscarProdutos(String descricao, EstCategoria categoria, EstUnidadeMedida unMedida, EstMarca marca) {
        String hql = "FROM EstProduto WHERE lower(produto_descricao) like lower(:produto_descricao)";// and estCategoria = :categoria";// and produto_marca = :marca and produto_unid_medida : unMedida";
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery(hql);
        query.setParameter("produto_descricao", descricao + "%");
        //query.setParameter("estCategoria", categoria);
        //query.setParameter("produto_marca", marca);
        //query.setParameter("produto_unid_medida", unMedida);
        List<EstProduto> produto = query.list();
        return produto;
    }
}
