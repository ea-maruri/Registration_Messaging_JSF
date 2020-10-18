package com.corejsf;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Objects;


/**
 * @author EAMT on 3/10/2020
 */
@Named("currentUser")
@SessionScoped
public class CurrentUserBean implements Serializable {
    // Instance Variables
    protected String name = "";
    protected String userName = "";
    protected String password;
    protected boolean logged = false;


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

    // Extra
    public String getGreeting() {
        if (userName.length() == 0) return "";
        else return "Welcome to Messaging, " + this.userName + "!";
    }

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

        CurrentUserBean currentUserBean = (CurrentUserBean) o;
        return name.equals(currentUserBean.name) && userName.equals(currentUserBean.userName) && password.equals(currentUserBean.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, userName, password);
    }
}
