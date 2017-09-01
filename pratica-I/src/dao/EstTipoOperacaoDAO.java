package dao;

import java.util.List;
import model.CarEstTipoOperacao;
import model.EstProduto;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class EstTipoOperacaoDAO {

    public boolean Inserir(CarEstTipoOperacao tipOperacao) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.save(tipOperacao);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public boolean Alterar(CarEstTipoOperacao tipOperacao) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.update(tipOperacao);
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
            CarEstTipoOperacao operacao = (CarEstTipoOperacao) session.get(CarEstTipoOperacao.class, codigo);
            session.getTransaction().begin();
            session.delete(operacao);                        
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.getTransaction().commit();
            session.close();
        }       
    }

    public List<CarEstTipoOperacao> ListarTodas() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {            
            Query query = session.createQuery("from CarEstTipoOperacao as t ");
            List<CarEstTipoOperacao> tipOperacao = query.list();                   
            return tipOperacao;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }
    
    public List<EstTipoOperacaoDAO> getAll()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from CarEstTipoOperacao as t ");
        List<EstTipoOperacaoDAO> tpMov = query.list();
        return tpMov;
    }

    public CarEstTipoOperacao Buscar(int codigo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            CarEstTipoOperacao tipOperacao = (CarEstTipoOperacao) session.get(CarEstTipoOperacao.class, codigo);
            session.getTransaction().commit();            
            return tipOperacao;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }
}
