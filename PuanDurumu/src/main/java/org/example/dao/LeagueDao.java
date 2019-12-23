package org.example.dao;

import com.vaadin.ui.Notification;
import org.example.domain.League;
import org.example.hibernateUtil.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.PersistenceException;

public class LeagueDao {
    public void saveLeagueView(League league) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession();) {
            session.getTransaction().begin();
            try {
                session.merge(league);
                session.getTransaction().commit();
                Notification.show("İşlem Başarılı.");
            }
            catch (PersistenceException e) {
                Notification.show("username already exist");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            Notification.show(ex.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
    }

}
