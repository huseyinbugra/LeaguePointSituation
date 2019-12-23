package org.example.ui.views;

import com.vaadin.data.util.filter.Not;
import com.vaadin.ui.*;
import org.example.dao.TeamDao;
import org.example.domain.Teams;
import org.example.hibernateUtil.HibernateUtil;
import org.example.ui.components.SaveButton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.PersistenceException;

public class AddTeams extends VerticalLayout {

    private TextField idField;
    private TextField nameField;
    private FormLayout mainLayout;

    public AddTeams() {
        mainLayOut();
    }

    private void mainLayOut() {
        mainLayout = new FormLayout();
        addComponent(mainLayout);

        idField = new TextField("Id");
        idField.setEnabled(false);
        mainLayout.addComponent(idField);

        nameField = new TextField("Name");
        mainLayout.addComponent(nameField);

        SaveButton btnSave = new SaveButton();
        btnSave.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                addTeamView();
            }
        });
        mainLayout.addComponent(btnSave);
    }

    private void addTeamView() {
        String nameFieldValue = nameField.getValue();
        Teams team = new Teams();
        team.setTeamName(nameFieldValue);
        TeamDao teamDao = new TeamDao();
        teamDao.saveTeamView(team);
    }


}
