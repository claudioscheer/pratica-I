/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.EstMovimentacao;
import model.EstProduto;
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

    public boolean AumentarEstoque(int codigoProduto, double quantidade)
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

    public boolean DiminuirEstoque(int codigoProduto, double quantidade)
    {
        return true;
    }

   public List<EstMovimentacao> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from EstMovimentacao ");
        List<EstMovimentacao> movimentacaos = query.list();
        return movimentacaos;
    }
}
