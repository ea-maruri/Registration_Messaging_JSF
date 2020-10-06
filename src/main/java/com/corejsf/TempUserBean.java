package com.corejsf;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * @author EAMT on 3/10/2020
 */
@Named("temp_user")
@RequestScoped
public class TempUserBean extends UserBean{
    // Instance variables
    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
