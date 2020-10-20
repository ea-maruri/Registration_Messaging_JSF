package com.corejsf.msg;

import com.corejsf.CurrentUserBean;
import com.corejsf.TempUserBean;

import javax.ejb.EJB;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Named
@ViewScoped
public class MsgBackingBean implements Serializable {
    @EJB
    private MessageService msgService;
    @Inject

    @Push
    private PushContext incoming;  // channel

    private final HashMap<String, TempUserBean> registeredUsers = new HashMap<>();
    private List<CurrentUserBean> loggedUsers = new ArrayList<>();



    private void addRegisteredUser() {

    }


    public void getLoggedUsersList() {
        incoming.send("loggedUsersList");
    }

    public void getRegisteredUsers() {
        incoming.send("registeredUsersList");
    }

}
