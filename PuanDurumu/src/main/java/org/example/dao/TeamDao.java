package org.example.dao;

import com.vaadin.ui.Notification;
import org.example.domain.Teams;
import org.example.hibernateUtil.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.PersistenceException;

public class TeamDao {

    public void saveTeamView(Teams team) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession();) {
            session.getTransaction().begin();

            try {
                session.merge(team);
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
