package org.example.ui.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import org.example.ui.views.AddTeams;
import org.example.ui.views.AddLeagueData;
import org.example.ui.views.ListTeam;
import org.example.ui.views.ListLeague;

public class SideBar extends VerticalLayout {

    private Header header;
    private Content content;

    private MenuButton btnTakimEkle;
    private MenuButton btnTakimListeleButton;
    private MenuButton btnLigListele;
    private MenuButton BtnLigVeriEkle;


    public SideBar(Header header, Content content) {

        this.header = header;
        this.content = content;

        setSpacing(true);
        setMargin(true);

        btnTakimEkle = new MenuButton(FontAwesome.PLUS_SQUARE);
        btnTakimEkle.setCaption("Takım Ekle");
        btnTakimEkle.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                header.setHeader("Takım Ekle");
                AddTeams addTeams = new AddTeams();
                content.setContent(addTeams);
            }
        });
        addComponent(btnTakimEkle);

        btnTakimListeleButton = new MenuButton(FontAwesome.LIST);
        btnTakimListeleButton.setCaption("Takım Listele");
        btnTakimListeleButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                header.setHeader("Takım Listele");
                ListTeam listTeam = new ListTeam();
                content.setContent(listTeam);
            }
        });
        addComponent(btnTakimListeleButton);

        BtnLigVeriEkle = new MenuButton(FontAwesome.PLUS_SQUARE);
        BtnLigVeriEkle.setCaption("Lig Veri Ekle");
        BtnLigVeriEkle.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                header.setHeader("Lig Veri Ekle");
                AddLeagueData addLeagueData = new AddLeagueData();
                content.setContent(addLeagueData);
            }
        });
        addComponent(BtnLigVeriEkle);

        btnLigListele = new MenuButton(FontAwesome.LIST);
        btnLigListele.setCaption("Lig Veri Listele");
        btnLigListele.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                header.setHeader("Lig Veri Listele");
                ListLeague listLeague = new ListLeague();
                content.setContent(listLeague);
            }
        });
        addComponent(btnLigListele);
    }
}