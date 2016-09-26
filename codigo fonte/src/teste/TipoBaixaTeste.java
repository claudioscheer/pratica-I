package teste;

import model.TipoBaixa;
import org.hibernate.Session;
import utils.HibernateUtil;

public class TipoBaixaTeste {

    public static void main(String[] args) {
        TipoBaixa t = new TipoBaixa();
        t.setCodigo(1);
        t.setDescricao("asdfasdfasd");

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(t);
        session.getTransaction().commit();
        session.close();

    }

}
