package org.example.ui.views;

import com.vaadin.data.util.filter.Not;
import com.vaadin.ui.*;
import org.example.domain.Teams;
import org.example.hibernateUtil.HibernateUtil;
import org.example.ui.components.SaveButton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.PersistenceException;

public class AddTeams extends VerticalLayout {

    private FormLayout mainLayout;

    public AddTeams() {
        mainLayout = new FormLayout();
        addComponent(mainLayout);

        TextField idField = new TextField("Id");
        idField.setEnabled(false);
        mainLayout.addComponent(idField);

        TextField nameField = new TextField("Name");
        mainLayout.addComponent(nameField);

        SaveButton btnSave = new SaveButton();
        btnSave.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
                try (Session session = sessionFactory.openSession();) {

                    session.getTransaction().begin();
                    String nameFieldValue = nameField.getValue();

                    Teams team = new Teams();
                    team.setTeamName(nameFieldValue);

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
        });
        mainLayout.addComponent(btnSave);
    }
}
