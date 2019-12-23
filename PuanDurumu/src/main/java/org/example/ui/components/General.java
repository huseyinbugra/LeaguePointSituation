package org.example.ui.components;

import com.vaadin.ui.VerticalLayout;

public class General extends VerticalLayout {

    public General() {
        Header header = new Header();
        addComponent(header);

        Container container = new Container(header);
        addComponent(container);
    }
}
