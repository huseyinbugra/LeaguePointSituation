package org.example.ui.components;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public class Content extends VerticalLayout {

    public Content() {
    }

    public void setContent(Component component) {
        this.removeAllComponents();
        addComponent(component);
    }
}
