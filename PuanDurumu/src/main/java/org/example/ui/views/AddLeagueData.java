package org.example.ui.views;

import com.vaadin.ui.*;
import javafx.beans.binding.IntegerBinding;
import org.example.domain.Teams;
import org.example.domain.League;
import org.example.hibernateUtil.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class AddLeagueData extends VerticalLayout {
    ComboBox comboTeam = null;
    private FormLayout mainLayout;

    public AddLeagueData() {
        mainLayout = new FormLayout();
        addComponent(mainLayout);

        TextField idField = new TextField("Id");
        idField.setEnabled(false);
        mainLayout.addComponent(idField);

        TextField victoryField = new TextField("Victory");
        mainLayout.addComponent(victoryField);

        TextField defeatField = new TextField("Defeat");
        mainLayout.addComponent(defeatField);

        TextField tieField = new TextField("Tie");
        mainLayout.addComponent(tieField);

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession();) {
                Query query = session.createQuery("Select team From Teams team");
                List<Teams> teamsList = query.list();
                comboTeam = new ComboBox("Teams", teamsList);
                mainLayout.addComponent(comboTeam);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        Button btnSave = new Button("Ekle");
        btnSave.setCaption("Ekle");
        btnSave.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
                try (Session session = sessionFactory.openSession();) {
                    session.getTransaction().begin();

                    int victoryFieldValue = Integer.parseInt(victoryField.getValue());
                    int defeatFieldValue = Integer.parseInt(defeatField.getValue());
                    int tieFieldValue = Integer.parseInt(tieField.getValue());
                    int pointFieldValue = victoryFieldValue*3 + tieFieldValue*1;
                    Teams kategoriFieldValue = (Teams)comboTeam.getValue();

                    League league = new League();
                    league.setTeams(kategoriFieldValue);
                    league.setVictory(victoryFieldValue);
                    league.setDefeat(defeatFieldValue);
                    league.setPoint(pointFieldValue);
                    league.setTie(tieFieldValue);
                    session.merge(league);
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