package org.example.ui.views;

import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.shared.ui.MultiSelectMode;
import com.vaadin.ui.*;
import org.example.domain.Teams;
import org.example.hibernateUtil.HibernateUtil;
import org.example.ui.components.SaveButton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ListTeam extends HorizontalLayout {

    private Table table;
    private TextField idField;
    private TextField nameField;
    private SaveButton saveButton;
    private IndexedContainer indexedContainer;

    private FormLayout formLayout;

    public ListTeam() {

        setSpacing(true);
        setMargin(true);

        buildTableContainer();

        buildTable();
        addComponent(table);

        buildFormLayout();
        addComponent(formLayout);

        fillTable();
    }

    private void fillTable() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession();) {
            Query query = session.createQuery("Select team From Teams team");
            List<Teams> teamsList = query.list();
            for (Teams team : teamsList) {
                Item item = indexedContainer.addItem(team);
                item.getItemProperty("id").setValue(team.getId());
                item.getItemProperty("name").setValue(team.getTeamName());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void buildTableContainer() {

        indexedContainer = new IndexedContainer();
        indexedContainer.addContainerProperty("id", Long.class, null);
        indexedContainer.addContainerProperty("name", String.class, null);
    }

    private void buildTable() {
        table = new Table();
        table.setContainerDataSource(indexedContainer);
        table.setColumnHeaders("NO", "TAKIM ADI");
        table.setSelectable(true);
        table.setMultiSelectMode(MultiSelectMode.SIMPLE);
        table.setMultiSelect(false);
        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                Teams team = (Teams) itemClickEvent.getItemId();
                idField.setValue(team.getId().toString());
                nameField.setValue(team.getTeamName());
            }
        });
    }

    private void buildFormLayout() {

        formLayout = new FormLayout();

        idField = new TextField("Id");
        idField.setEnabled(false);
        formLayout.addComponent(idField);

        nameField = new TextField("Takım Adı");
        formLayout.addComponent(nameField);

        saveButton = new SaveButton();
        saveButton.setCaption("Güncelle");
        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

                try (Session session = sessionFactory.openSession();) {
                    session.getTransaction().begin();
                    String nameFieldValue = nameField.getValue();

                    Teams team = new Teams();
                    team.setId(Long.parseLong(idField.getValue()));
                    team.setTeamName(nameFieldValue);
                    team = (Teams) session.merge(team);

                    idField.setValue(team.getId().toString());
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