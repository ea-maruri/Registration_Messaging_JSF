
package com.corejsf.msg;

import com.corejsf.UserBeanInterpreter;
import com.corejsf.UsersListBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.Calendar;
import java.util.Date;

/**
 * @author EAMT on 17/10/2020
 */
@Named("message_sender")
@ApplicationScoped
public class MessageSenderBean {
    //private UsersListBean usersListBean = new UsersListBean();// Has an instance of the userListBean
    private static UsersListBean usersListBean;

    public UsersListBean getUsersListBean() {
        return usersListBean.getInstance();
    }

    public void setUsersListBean() {
        usersListBean = usersListBean.getInstance();
    }


    public void sendMessage(String sender, String receiver, String msgText) {
        UserBeanInterpreter userBeanInterpreter = usersListBean.getUserByName(receiver);
        if (userBeanInterpreter != null) {
            System.out.println("Send message to " + userBeanInterpreter);

            Message message = new Message(Calendar.getInstance().getTime(), sender, receiver, msgText);
            userBeanInterpreter.getMessagesList().add(message);
            System.out.printf("Messages of %s: %s\n", userBeanInterpreter, userBeanInterpreter.getMessagesList());
            System.out.println("HERE");
        }
        else {
            System.err.println("Cannot send message because received is null");
        }

    }

//    public void sendMessage(Message message) {
//        UserBeanInterpreter userBeanInterpreter = usersListBean.getUserByName(message.getReceiver());
//
//        userBeanInterpreter.getMessagesList().add(message);
//
//        System.out.printf("Messages of %s: %s", userBeanInterpreter, userBeanInterpreter.getMessagesList());
//    }
}
