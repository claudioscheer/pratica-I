package dao;

import model.Marca;
import org.hibernate.Session;
import utils.HibernateUtil;

public class MarcaDAO {

    public Boolean insert(Marca marca) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(marca);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    public static void main(String[] args) {
        Marca marca = new Marca();
        marca.setCodigo(1);
        marca.setDescricao("marca 1");
        
        new MarcaDAO().insert(marca);
    }

}
