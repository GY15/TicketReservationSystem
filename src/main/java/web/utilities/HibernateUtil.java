package web.utilities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import web.model.*;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        try {
            Configuration config;
            ServiceRegistry serviceRegistry;
            config = new Configuration().configure();
            config.addAnnotatedClass(ValidUser.class);
            config.addAnnotatedClass(Member.class);
            config.addAnnotatedClass(Venue.class);
            config.addAnnotatedClass(SeatMap.class);
            config.addAnnotatedClass(Plan.class);
            config.addAnnotatedClass(Ticket.class);
            config.addAnnotatedClass(Order.class);
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
            sessionFactory = config.buildSessionFactory(serviceRegistry);
            return sessionFactory;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * gerCurrentSession
     */
    public static Session getSession() {
        return getSessionFactory().getCurrentSession();
    }


    public static final ThreadLocal threadLocal = new ThreadLocal();

    public static Session openSession() {
        Session currentSession = (Session) threadLocal.get();
        if (currentSession == null) {
            currentSession = sessionFactory.openSession();
            threadLocal.set(currentSession);
        }
        return currentSession;
    }

    public static void closeSession() {
        Session currentSession = (Session) threadLocal.get();
        if (currentSession == null) {
            currentSession.close();
        }
        threadLocal.set(null);
    }

}
