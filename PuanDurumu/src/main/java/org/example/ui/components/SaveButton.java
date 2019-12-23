package org.example.ui.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;

public class SaveButton extends Button {

    public SaveButton() {
        setIcon(FontAwesome.CHECK);
        setCaption("Ekle");
    }
}
