package org.jboss.as.quickstarts.greeter.web;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.as.quickstarts.greeter.domain.User;
import org.jboss.as.quickstarts.greeter.domain.UserDao;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import java.util.List;

@Named
@RequestScoped
public class ListController {

    @Inject
    private UserDao userDao;

    private List<User> users;

    private User selectedUser;

    public List<User> getUsers() {
        users = userDao.getAllUsers();

        return users;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser( User user ) {
        this.selectedUser = user;
    }

    public void onDoubleClick(SelectEvent event) {

        selectedUser = (User) event.getObject();
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlg2').show();");

        System.out.println( selectedUser.getFirstName()+ "---------------aaa");
    }

    public void onRowSelect(SelectEvent event) {

        selectedUser = (User) event.getObject();

        FacesMessage msg = new FacesMessage("Car Selected", ((User) event.getObject()).getId() + "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Car Unselected", ((User) event.getObject()).getId() + "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
