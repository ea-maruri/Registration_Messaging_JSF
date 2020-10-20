package com.corejsf.msg;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class MessageService {
    private List<String> messages;


    @PostConstruct
    private void init() {
        messages = new ArrayList<>();
    }
    public void add(String message) {
        messages.add(message);
    }
    public List<String> getMessages(){
        return messages;
    }

}
