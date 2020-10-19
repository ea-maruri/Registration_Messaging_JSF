package com.corejsf;

import com.corejsf.msg.Message;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author EAMT on 3/10/2020
 */
@Named("tempUser")
@RequestScoped
public class TempUserBean implements Serializable {
    // Instance Variables
    protected String name = "";
    protected String userName = "";
    protected String password;
    protected boolean logged = false;
    private String confirmPassword;

    private HashMap<Integer, Message> messages = new HashMap<>();


    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public HashMap<Integer, Message> getMessages() {
        return messages;
    }

    public void setMessages(HashMap<Integer, Message> messages) {
        this.messages = messages;
    }


    // Extra
    public void putLogged(boolean logged){
        setLogged(logged);
    }



    // From Object
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", logged=" + logged +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TempUserBean userBean = (TempUserBean) o;
        return name.equals(userBean.name) && userName.equals(userBean.userName) && password.equals(userBean.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, userName, password);
    }

}
