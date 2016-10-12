package dao;

import model.AtivoImobilizado;
import org.hibernate.Session;
import utils.HibernateUtil;

public class AtivoImobilizadoDAO {

    public Boolean insert(AtivoImobilizado ativoImobilizado) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(ativoImobilizado);
        session.getTransaction().commit();
        session.close();
        return true;
    }

}
