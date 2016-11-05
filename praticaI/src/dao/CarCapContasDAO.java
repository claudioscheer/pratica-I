package dao;

import enumeraveis.TipoConta;
import java.util.Date;
import java.util.List;
import model.CarCapContas;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

public class CarCapContasDAO {

    public Boolean update(CarCapContas conta) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(conta);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public Boolean insert(CarCapContas conta) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(conta);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public Boolean delete(CarCapContas conta) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(conta);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public List<CarCapContas> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("from car_cap_contas as a ");
        List<CarCapContas> ativos = query.list();
        session.getTransaction().commit();
        session.close();
        return ativos;
    }

  

    public List<CarCapContas> ListarTodos(Date dataInicial, Date dataFinal) {

        System.out.println("DataInicial: " + dataInicial + " DataFinal: " + dataFinal);

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("from CarCapContas as a where conta_data_emissao BETWEEN :datainicial and :datafinal");
        query.setParameter("datainicial", dataInicial);
        query.setParameter("datafinal", dataFinal);
        List<CarCapContas> contas = query.list();
        session.getTransaction().commit();
        session.close();
        return contas;
    }

    public List<CarCapContas> ListarContas(TipoConta tipo, Date dataInicial, Date dataFinal) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        System.out.println("DataFinal" + dataFinal + "DataInicial: " + dataInicial);
//and conta_data_emissao >=:dataInicial and conta_data_emissao <=:dataFinal
        Query query = session.createQuery("from car_cap_contas as a");
//        query.setParameter("dataInicial", dataInicial);
//        query.setParameter("dataFinal", dataFinal);
        query.setParameter("tipo", tipo);

        List<CarCapContas> contas = query.list();
        session.getTransaction().commit();
        session.close();
        return contas;

    }

    public double SomarContas(TipoConta tipo, Date dataInicial, Date dataFinal) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        System.out.println("DataFinal" + dataFinal + "DataInicial: " + dataInicial);

        Query query = session.createQuery("select sum(a.contaValorPago) from CarCapContas as a where contaTipo =:tipo and conta_data_emissao BETWEEN :dataInicial and :dataFinal");
        query.setParameter("dataInicial", dataInicial);
        query.setParameter("dataFinal", dataFinal);
        query.setParameter("tipo", tipo);

        double total = (double) (query.uniqueResult() == null ? 0.0 : query.uniqueResult());
        
        session.getTransaction().commit();
        session.close();
        return total;

    }

}
