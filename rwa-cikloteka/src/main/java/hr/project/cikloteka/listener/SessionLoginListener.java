package hr.project.cikloteka.listener;

import hr.project.cikloteka.dao.RepositoryFactory;
import hr.project.cikloteka.model.Login;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import java.util.Objects;

@WebListener
public class SessionLoginListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if (Objects.equals(event.getName(), "login"))
            try {
                RepositoryFactory.getRepository().addLogin((Login) event.getValue());
                System.out.println(event.getValue());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }
}
