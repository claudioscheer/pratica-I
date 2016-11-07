/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.CarPessoa;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author dimhr12
 */
public class PessoaDAO {
    
    public Boolean update(CarPessoa pessoa) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(pessoa);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public Boolean insert(CarPessoa pessoa) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(pessoa);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public Boolean delete(CarPessoa pessoa) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(pessoa);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public List<CarPessoa> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("from CarPessoa as a ");
        List<CarPessoa> ativos = query.list();
        session.getTransaction().commit();
        session.close();
        return ativos;
    }

    public CarPessoa get(int ativo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        CarPessoa pessoa = (CarPessoa) session.get(CarPessoa.class, ativo);
        session.getTransaction().commit();
        session.close();
        return pessoa;
    }
    
}