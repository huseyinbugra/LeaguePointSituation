package org.example.ui.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

public class MenuButton extends Button {

    public MenuButton(FontAwesome fontAwesome) {
        setIcon(fontAwesome);
        setWidth(100, Unit.PERCENTAGE);
        addStyleName(ValoTheme.BUTTON_PRIMARY);
    }
}
