package com.corejsf.msg;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


/**
 * @author EAMT on 18/10/2020
 */
@Named("messageToRemove")
@RequestScoped
public class MessageToRemoveBean {
    private int id = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
