package org.example.ui.views;

import com.vaadin.ui.*;
import org.example.domain.Teams;
import org.example.hibernateUtil.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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

        Button btnSave = new Button("Kaydet");
        btnSave.setCaption("Kaydet");
        btnSave.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
                try (Session session = sessionFactory.openSession();) {
                    session.getTransaction().begin();
                    String nameFieldValue = nameField.getValue();
                    Teams team = new Teams();
                    team.setTeamName(nameFieldValue);
                    session.save(team);
                    session.getTransaction().commit();
                    Notification.show("İşlem Başarılı");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    Notification.show(ex.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
            }
        });
        mainLayout.addComponent(btnSave);
    }
}
