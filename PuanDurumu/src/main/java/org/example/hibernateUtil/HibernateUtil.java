package org.example.hibernateUtil;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

        try{

            Configuration cfg = new Configuration();
            SessionFactory sessionFactory = cfg.configure().buildSessionFactory();
            return sessionFactory;

        } catch (Exception e){
            System.out.println("Session oluşturulurken hata meydana geldi." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}