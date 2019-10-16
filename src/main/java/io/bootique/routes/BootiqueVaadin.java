package io.bootique.routes;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("")
@PageTitle("BootiqueVaadin")
public class BootiqueVaadin extends VerticalLayout {
    public BootiqueVaadin() {
        Button button = new Button("Click me",
                event -> Notification.show("Clicked!"));
        add(button);
        add(new Text("Bootique With Vaadin"));
    }
}
