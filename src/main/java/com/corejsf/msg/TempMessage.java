package com.corejsf.msg;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * @author EAMT on 18/10/2020
 */
@Named("temp_message")
@RequestScoped
public class TempMessage implements Serializable {
    private String receiver;
    private String message;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
