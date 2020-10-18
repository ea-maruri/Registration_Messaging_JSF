
package com.corejsf.msg;

import com.corejsf.TempUserBean;
import com.corejsf.UsersListBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.Calendar;

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
        TempUserBean tempUserBean = usersListBean.getUserByName(receiver);
        if (tempUserBean != null) {
            System.out.println("Send message to " + tempUserBean);

            Message message = new Message(Calendar.getInstance().getTime(), sender, receiver, msgText);
            tempUserBean.getMessagesList().add(message);
            System.out.printf("Messages of %s: %s\n", tempUserBean, tempUserBean.getMessagesList());
            System.out.println("HERE");
        }
        else {
            System.err.println("Cannot send message because received is null");
        }

    }

//    public void sendMessage(Message message) {
//        TempUserBean userBeanInterpreter = usersListBean.getUserByName(message.getReceiver());
//
//        userBeanInterpreter.getMessagesList().add(message);
//
//        System.out.printf("Messages of %s: %s", userBeanInterpreter, userBeanInterpreter.getMessagesList());
//    }
}
