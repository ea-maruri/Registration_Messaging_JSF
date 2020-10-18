package com.corejsf.msg;

import java.util.Date;
import java.util.Objects;

/**
 * @author EAMT on 17/10/2020
 */
public class Message {
    private final Date dateSent;
    private final String sender;
    private final String receiver;
    private final String message;

    public Message(Date dateSent, String sender, String receiver, String message) {
        this.dateSent = dateSent;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return dateSent.equals(message1.dateSent) &&
                sender.equals(message1.sender) &&
                receiver.equals(message1.receiver) &&
                message.equals(message1.message);
    }


    @Override
    public int hashCode() {
        return Objects.hash(dateSent, sender, receiver, message);
    }


    @Override
    public String toString() {
        return "Message {" +
                "\tid=" + hashCode() +
                "\tdateSent=" + dateSent +
                "\tsender='" + sender + '\'' +
                "\nreceiver='" + receiver + '\'' +
                "\nmessage='" + message + '\'' +
                '}';
    }
}