package ar.jujuy.pov.hibernate.configuracion;

import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static final Session session;
    
    static {
        try {
            sessionFactory = new AnnotationConfiguration().configure("ar/jujuy/pov/hibernate/configuracion/hibernate.cfg.xml").buildSessionFactory();
            session=sessionFactory.openSession();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
