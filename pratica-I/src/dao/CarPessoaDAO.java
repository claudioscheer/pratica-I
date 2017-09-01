/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import enumeraveis.TipoConta;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.CarCapContas;
import model.CarPessoa;
import model.FlxcxOperacoes;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author Marcos
 */
public class CarPessoaDAO {
    
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

    public CarPessoa ListarId(int codigo) {
     
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
    try{

        CarPessoa carpessoa = (CarPessoa) session.get(CarPessoa.class, codigo);

        return carpessoa;
        
    }catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }

    }
    
    public List<CarPessoa> ListarTodos() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("from CarPessoa as a");
 
        List<CarPessoa> pessoa = query.list();
        session.getTransaction().commit();
        session.close();
        return pessoa;
    }

    
    public CarPessoa buscarPessoaPeloLogin(String login) {		 
         Session session = HibernateUtil.getSessionFactory().openSession();		
         session.getTransaction().begin();		
         List<CarPessoa> pessoa = new ArrayList<>();		
         try {		
             Query query = session.createQuery("FROM CarPessoa p WHERE p.pessoaLogin = :usuario");		
             query.setParameter("usuario", login);		
             pessoa = query.list();		
             session.getTransaction().commit();		
         } catch (HibernateException e) {		
         } finally {		
             session.close();		
         }		
         if (pessoa.size() > 0) {		
             return pessoa.get(0);		
         }		
         return null;		
     }
    
}
