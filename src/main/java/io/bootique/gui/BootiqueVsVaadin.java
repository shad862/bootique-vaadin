package io.bootique.gui;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("")
@PageTitle("BootiqueVsVaadin")
public class BootiqueVsVaadin extends VerticalLayout {
    public BootiqueVsVaadin() {
        Button button = new Button("Click me",
                event -> Notification.show("Clicked!"));
        add(button);
        add(new Text("Bootique 1 vs Vaadin 14"));
    }
}
