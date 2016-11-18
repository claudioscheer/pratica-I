package dao;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.chart.PieChart;
import model.EstMovimentacao;
import model.EstProduto;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author Anderson
 */
public class EstMovimentacaoDAO
{

    private EstProdutoDAO produtoDao = new EstProdutoDAO();

    public Boolean update(EstMovimentacaoDAO mov)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(mov);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public Boolean insert(EstMovimentacaoDAO mov)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(mov);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public Boolean delete(int codigo)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try
        {
            session.getTransaction().begin();
            EstMovimentacaoDAO mov = (EstMovimentacaoDAO) session.get(EstMovimentacaoDAO.class, codigo);
            session.delete(mov);
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

    public EstMovimentacaoDAO get(int value)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        EstMovimentacaoDAO mov = (EstMovimentacaoDAO) session.get(EstMovimentacaoDAO.class, value);
        session.getTransaction().commit();
        session.close();
        return mov;
    }

    public void GerarRelatorioMovimentacoes()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<EstMovimentacao> listaMov = session.createQuery("from EstMovimentacao").list();
        JRBeanCollectionDataSource jrs = new JRBeanCollectionDataSource(listaMov);
        Map parametros = new HashMap();
        try
        {
            JasperPrint jpr = JasperFillManager.fillReport("C:\\Users\\Anderson\\Documents\\GitHub\\praticaI\\praticaI\\src\\relatorios\\estoque\\relatorio_movimentacoes.jasper", null, jrs);
            JasperViewer.viewReport(jpr, true);
        } catch (JRException ex)
        {
            System.out.println("" + ex);
        }
    }

    public boolean AumentarEstoque(int codigoProduto, Date dataMov, int tipoMov, double quantidade, double Unitario)
    {
        EstProduto produto = new EstProduto();
        EstMovimentacao movimento = new EstMovimentacao();
        
        
        movimento.setMovQuantidade(quantidade);

        Session session = HibernateUtil.getSessionFactory().openSession();
        try
        {
            produto = produtoDao.get(codigoProduto);
            session.getTransaction().begin();
            //session.save(tipOperacao);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e)
        {
            throw e;
        } finally
        {
            session.close();
        }
    }

    public boolean DiminuirEstoque(int codigoProduto, double quantidade) //List
    {
        return true;
    }

    public List<EstMovimentacao> getAll()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from EstMovimentacao ");
        List<EstMovimentacao> movimentacaos = query.list();
        return movimentacaos;
    }

    public List<EstMovimentacao> findByProduto(EstProduto p)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        String hql = "from EstMovimentacao as m where m.estProduto = :produto";
        Query q = session.createQuery(hql);
        q.setParameter("produto", p);
        return q.list();
    }

    public double doubleDecimais(double value)
    {
        DecimalFormat fmt = new DecimalFormat("0.00");
        String string = fmt.format(value);
        String[] part = string.split("[,]");
        String string2 = part[0] + "." + part[1];
        double vlr = Double.parseDouble(string2);
        return vlr;
    }

}
