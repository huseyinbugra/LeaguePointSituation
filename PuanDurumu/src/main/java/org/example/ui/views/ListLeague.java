package org.example.ui.views;

import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.shared.ui.MultiSelectMode;
import com.vaadin.ui.*;
import org.example.domain.League;
import org.example.domain.Teams;
import org.example.hibernateUtil.HibernateUtil;
import org.example.ui.components.SaveButton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class ListLeague extends HorizontalLayout {

    private Table table;
    private TextField idField;
    private TextField nameField;
    private TextField victoryField;
    private TextField defeatField;
    private TextField pointField;
    private TextField tieField;
    private SaveButton saveButton;
    private IndexedContainer indexedContainer;

    private FormLayout formLayout;

    public ListLeague() {
        setSpacing(true);
        setMargin(true);

        buildTableContainer();

        buildTable();
        addComponent(table);

        buildFormLayout();
        addComponent(formLayout);

        fillTable();
    }

    private void buildTableContainer() {
        indexedContainer = new IndexedContainer();
        indexedContainer.addContainerProperty("id", Long.class, null);
        indexedContainer.addContainerProperty("team", Teams.class, null);
        indexedContainer.addContainerProperty("victory", Integer.class, null);
        indexedContainer.addContainerProperty("defeat", Integer.class, null);
        indexedContainer.addContainerProperty("tie", Integer.class, null);
        indexedContainer.addContainerProperty("point", Integer.class, null);
    }

    private void fillTable() {
        long i = 1;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession();) {
            Query query = session.createQuery("Select league From League league left join fetch league.teams team order by league.point desc");
            List<League> leagueList = query.list();
            for (League league : leagueList) {
                Item item = indexedContainer.addItem(league);
                item.getItemProperty("team").setValue(league.getTeams());
                item.getItemProperty("id").setValue(i);
                item.getItemProperty("defeat").setValue(league.getDefeat());
                item.getItemProperty("point").setValue(league.getPoint());
                item.getItemProperty("tie").setValue(league.getTie());
                item.getItemProperty("victory").setValue(league.getVictory());
                i++;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    private void buildTable() {
        table = new Table();
        table.setContainerDataSource(indexedContainer);
        table.setColumnHeaders("Sıra", "Team","Victory","Defeat","Tie","Point");
        table.setSelectable(true);
        table.setMultiSelectMode(MultiSelectMode.SIMPLE);
        table.setMultiSelect(false);
        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                League league = (League) itemClickEvent.getItemId();
                idField.setValue(league.getId().toString());
                nameField.setValue(league.getTeams().toString());
                victoryField.setValue(String.valueOf(league.getVictory()));
                defeatField.setValue(String.valueOf(league.getDefeat()));
                pointField.setValue(String.valueOf(league.getPoint()));
                tieField.setValue(String.valueOf(league.getTie()));
            }
        });
    }

    private void buildFormLayout() {

        formLayout = new FormLayout();

        idField = new TextField("Id");
        idField.setEnabled(false);
        formLayout.addComponent(idField);

        nameField = new TextField("Name");
        nameField.setEnabled(false);
        formLayout.addComponent(nameField);

        victoryField = new TextField("Victory");
        formLayout.addComponent(victoryField);

        defeatField = new TextField("Defeat");
        formLayout.addComponent(defeatField);

        tieField = new TextField("Tie");
        formLayout.addComponent(tieField);

        pointField = new TextField("Point");
        formLayout.addComponent(pointField);

        saveButton = new SaveButton();
        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
                try (Session session = sessionFactory.openSession();) {
                    session.getTransaction().begin();
                    int victoryFiledValue = Integer.getInteger(victoryField.getValue());
                    int defeatFiledValue = Integer.getInteger(defeatField.getValue());
                    int tieFiledValue = Integer.getInteger(tieField.getValue());
                    int pointFiledValue = Integer.getInteger(pointField.getValue());

                    League league = new League();
                    league.setId(Long.parseLong(idField.getValue()));
                    league.setVictory(victoryFiledValue);
                    league.setDefeat(defeatFiledValue);
                    league.setTie(tieFiledValue);
                    league.setPoint(pointFiledValue);
                    league = (League) session.merge(league);

                    idField.setValue(league.getId().toString());
                    session.getTransaction().commit();
                    Notification.show("İşlem Başarılı");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    Notification.show(ex.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
            }
        });
        formLayout.addComponent(saveButton);
    }


}