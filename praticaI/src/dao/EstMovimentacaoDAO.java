package dao;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import javafx.scene.chart.PieChart;
import model.CarEstTipoOperacao;

import java.util.Map;
import javafx.scene.chart.PieChart;

import model.EstMovimentacao;
import model.EstProduto;
import model.PatItemNota;
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
public class EstMovimentacaoDAO {

    private EstProdutoDAO produtoDao = new EstProdutoDAO();
    //EstMovimentacaoDAO mDAO = new EstMovimentacaoDAO();

    public Boolean update(EstMovimentacao mov) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(mov);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public Boolean insert(EstMovimentacao mov) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(mov);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public Boolean delete(int codigo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            EstMovimentacaoDAO mov = (EstMovimentacaoDAO) session.get(EstMovimentacaoDAO.class, codigo);
            session.delete(mov);
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public EstMovimentacaoDAO get(int value) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        EstMovimentacaoDAO mov = (EstMovimentacaoDAO) session.get(EstMovimentacaoDAO.class, value);
        session.getTransaction().commit();
        session.close();
        return mov;
    }

    public void GerarRelatorioMovimentacoes() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<EstMovimentacao> listaMov = session.createQuery("from EstMovimentacao").list();
        JRBeanCollectionDataSource jrs = new JRBeanCollectionDataSource(listaMov);
        Map parametros = new HashMap();
        try {
            JasperPrint jpr = JasperFillManager.fillReport("C:\\Users\\Anderson\\Documents\\GitHub\\praticaI\\praticaI\\src\\relatorios\\estoque\\relatorio_movimentacoes.jasper", null, jrs);
            JasperViewer.viewReport(jpr, true);
        } catch (JRException ex) {
            System.out.println("" + ex);
        }
    }

    public boolean AumentarEstoque(int codigoProduto, Date dataMov, int tipoMov, double quantidade, double Unitario) {
        EstProduto produto = new EstProduto();
        EstMovimentacao movimento = new EstMovimentacao();

        movimento.setMovQuantidade(quantidade);

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            produto = produtoDao.get(codigoProduto);
            session.getTransaction().begin();
            //session.save(tipOperacao);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public boolean DiminuirEstoque(int codigoProduto, double quantidade) //List
    {
        return true;
    }

    public List<EstMovimentacao> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from EstMovimentacao ");
        List<EstMovimentacao> movimentacaos = query.list();
        return movimentacaos;
    }

    public List<EstMovimentacao> findByProduto(EstProduto p) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        String hql = "from EstMovimentacao as m where m.estProduto = :produto";
        Query q = session.createQuery(hql);
        q.setParameter("produto", p);
        return q.list();
    }

    public double doubleDecimais(double value) {
        DecimalFormat fmt = new DecimalFormat("0.00");
        String string = fmt.format(value);
        String[] part = string.split("[,]");
        String string2 = part[0] + "." + part[1];
        double vlr = Double.parseDouble(string2);
        return vlr;
    }

    public void itensNota(List<PatItemNota> itens, Date data) {
        for (PatItemNota item : itens) {
            this.atualizaEstoque(item.getEstProduto().getProdutoId(), data, 1, item.getItemNotaQuantidade(), item.getItemNotaValorUnitario());
        }
    }

    public void atualizaEstoque(int codigoProduto, Date dataMov, int tipoMov, double quantidade, double Unitario) {
        EstMovimentacao m = new EstMovimentacao();
        EstMovimentacaoDAO mDao = new EstMovimentacaoDAO();
        EstProdutoDAO pDAO = new EstProdutoDAO();
        EstTipoOperacaoDAO movDAO = new EstTipoOperacaoDAO();

        CarEstTipoOperacao op = movDAO.Buscar(tipoMov);
        EstProduto p = pDAO.get(codigoProduto);

        m.setEstProduto(p);
        m.setMovData(dataMov);
        m.setCarEstTipoOperacao(op);
        m.setMovQuantidade(quantidade);
        m.setMovVlrUnit(Unitario);
        m.setMovTotal(quantidade * Unitario);
        mDao.insert(m);

    }

    public double[] calculaCusto(EstProduto produto) {
        double vet[] = new double[3];
        double qtde = 0.0, custoM = 0.0, total = 0.0;
        for (EstMovimentacao mov : this.findByProduto(produto)) {
            if (mov.getCarEstTipoOperacao().getTpOpTipo() == 1 || mov.getCarEstTipoOperacao().getTpOpTipo() == 2) {
                qtde += mov.getMovQuantidade();
                total += mov.getMovTotal();
            } else {
                qtde += (-mov.getMovQuantidade());
                total += (-mov.getMovTotal());
            }

        }

        vet[0] = qtde;
        vet[1] = (qtde != 0 && total != 0 ? total / qtde : 0);
        vet[2] = total;

        return vet;
    }

}
