package com.corejsf.ws;

import com.corejsf.CurrentUserBean;
import com.corejsf.TempUserBean;
import com.corejsf.UsersManagerBean;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * @author EAMT on 20/10/2020
 */

@Singleton
public class UsersService {
    private List<CurrentUserBean> loggedInUsers;

    @PostConstruct
    private void init() {
        loggedInUsers = new ArrayList<>();
    }

    public void addLoggedUser(CurrentUserBean currentUserBean) {
        loggedInUsers.add(currentUserBean);
    }


    // Getter
    public List<CurrentUserBean> getLoggedInUsers() {
        return loggedInUsers;
    }
}
