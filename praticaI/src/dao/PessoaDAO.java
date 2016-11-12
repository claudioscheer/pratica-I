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

    public List<CarPessoa> getAll(int indexfiltro, String filtro) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        String where = "";
        if (!filtro.isEmpty()) {
            switch (indexfiltro) {
                case 0:
                    where = " and a.pessoaId = :p1";
                    break;
                case 1:
                    where = " and a.pessoaNome like :p1";
                    break;
                case 2:
                    where = " and a.pessoaCpfCnpj like :p1";
                    break;
            }
        }
        Query query = session.createQuery("from CarPessoa a where 1 = 1 " + where);
        if (!filtro.isEmpty()) {
            switch (indexfiltro) {
                case 0:
                    query.setParameter("p1", Integer.parseInt(filtro));
                    break;
                case 1:
                    query.setParameter("p1", "%" + filtro + "%");
                    break;
                case 2:
                    query.setParameter("p1", filtro);
                    break;
            }
        }
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