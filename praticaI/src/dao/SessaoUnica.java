package dao;

import org.hibernate.Session;
import utils.HibernateUtil;

public class SessaoUnica {

    public enum Tela {
        ATIVO_IMOBILIZADO,
        NOTA_FISCAL
    }

    private static final Session[] sessions = new Session[Tela.values().length];

    public SessaoUnica() {
    }

    public static Session getSession(Tela telaSessao) {
        Session session = sessions[telaSessao.ordinal()];
        if (session == null) {
            return openSession(telaSessao);
        }
        return session;
    }

    private static Session openSession(Tela telaSessao) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        sessions[telaSessao.ordinal()] = session;
        return session;
    }

    public static void closeSession(Tela telaSessao) {
        Session session = sessions[telaSessao.ordinal()];
        if (session == null) {
            return;
        }
        try {
            session.close();
        } catch (Exception e) {
        }
    }

}
