package org.example.ui.views;

import com.vaadin.ui.*;
import org.example.dao.LeagueDao;
import org.example.domain.Teams;
import org.example.domain.League;
import org.example.hibernateUtil.HibernateUtil;
import org.example.ui.components.SaveButton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.PersistenceException;
import java.util.List;

public class AddLeagueData extends VerticalLayout {
    ComboBox comboTeam = null;
    private FormLayout mainLayout;
    private TextField victoryField;
    private TextField defeatField;
    private TextField tieField;
    private TextField idField;

    public AddLeagueData() {
        mainLayOut();
    }

    private void mainLayOut() {
        mainLayout = new FormLayout();
        addComponent(mainLayout);

        idField = new TextField("Id");
        idField.setEnabled(false);
        mainLayout.addComponent(idField);

        victoryField = new TextField("Victory");
        mainLayout.addComponent(victoryField);

        defeatField = new TextField("Defeat");
        mainLayout.addComponent(defeatField);

        tieField = new TextField("Tie");
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
        SaveButton btnSave = new SaveButton();
        btnSave.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                addLeagueView();
            }
        });
        mainLayout.addComponent(btnSave);
    }

    private void addLeagueView() {
        int victoryFieldValue = Integer.parseInt(victoryField.getValue());
        int defeatFieldValue = Integer.parseInt(defeatField.getValue());
        int tieFieldValue = Integer.parseInt(tieField.getValue());
        int pointFieldValue = victoryFieldValue*3 + tieFieldValue*1;
        Teams teamFieldValue = (Teams)comboTeam.getValue();

        League league = new League();
        league.setTeams(teamFieldValue);
        league.setVictory(victoryFieldValue);
        league.setDefeat(defeatFieldValue);
        league.setPoint(pointFieldValue);
        league.setTie(tieFieldValue);

        LeagueDao leagueDao = new LeagueDao();
        leagueDao.saveLeagueView(league);
    }


}