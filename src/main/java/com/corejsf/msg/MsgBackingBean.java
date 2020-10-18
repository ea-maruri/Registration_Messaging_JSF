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
public class MsgBackingBean implements Serializable {
    @EJB
    private MessageService msgService;
    @Inject

    @Push
    private PushContext incoming;

/*    @Push
    private PushContext logged_in_users;*/

    private String enteredMessage;

    public List<String> getMessages() {
        return msgService.getMessages();
    }

//    public void onSendMessage() {
//        msgService.add(enteredMessage);
//        incoming.send("newmessage");
//    }

    public void getList() {
        incoming.send("newmessage");
    }

    public void getRegistered() {
        incoming.send("newmessage1");
    }

    // Sender
    public void setEnteredMessage(String inputMessage){
        enteredMessage = inputMessage;
    }

    // Getter
    public String getEnteredMessage(){
        return enteredMessage;
    }
}
