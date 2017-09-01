/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.FlxcxOperacoes;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author Diego
 */
public class FlxcxOperacoesDAO {
    
    public boolean Inserir(FlxcxOperacoes operacoes) {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            session.getTransaction().begin();
            session.save(operacoes);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }

    }

    public boolean Alterar(FlxcxOperacoes operacoes) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            session.getTransaction().begin();
            session.update(operacoes);
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

            FlxcxOperacoes operacoes = (FlxcxOperacoes) session.get(FlxcxOperacoes.class, codigo);
            session.delete(operacoes);
            return true;

        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }

    }

    public List<FlxcxOperacoes> ListarTodas() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from FlxcxOperacoes as t ");

            List<FlxcxOperacoes> operacoes = query.list();

            return operacoes;

        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public FlxcxOperacoes Buscar(int codigo) {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            session.getTransaction().begin();

            FlxcxOperacoes tipOperacao = (FlxcxOperacoes) session.get(FlxcxOperacoes.class, codigo);

            return tipOperacao;
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }
    
    public List<FlxcxOperacoes> BuscaOperacoes(int codigoEspecificacao) {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from FlxcxOperacoes as t where flxcxEspecificacoes_espCodigo = :codigo");
            query.setParameter("codigo", codigoEspecificacao);
            
            List<FlxcxOperacoes> operacoes = query.list();

            return operacoes;

        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }
    }

    
}
