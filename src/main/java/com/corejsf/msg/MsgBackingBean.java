package com.corejsf.msg;

import javax.ejb.EJB;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class
MsgBackingBean implements Serializable {
    @EJB
    private MessageService msgService;
    @Inject
    @Push
    private PushContext incoming;

    private String enteredMessage;

    public List<String> getMessages() {
        return msgService.getMessages();
    }
    public void onSendMessage() {
        msgService.add(enteredMessage);
        incoming.send("newmessage");
    }

    // Getter
    public void setEnteredMessage(String inputMessage){
        enteredMessage = inputMessage;
    }

    public String getEnteredMessage(){
        return enteredMessage;
    }
}
