package com.corejsf;

import com.corejsf.msg.Message;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


/**
 * @author EAMT on 3/10/2020
 */
@Named("usersManager")
@ApplicationScoped

public class UsersManagerBean implements Serializable {
    /**
     * Instance Variables
     * <strong><em>registeredUsers</em></strong>, type: HashMap
     * <strong><em>loggedUsers</em></strong>, type: List
     */
    private final HashMap<String, TempUserBean> registeredUsers = new HashMap<>();
    private List<CurrentUserBean> loggedUsers = new ArrayList<>();


    /*Getters and Setters*/
    /**
     * Returns loggedUsers instance variable
     * @return List
     */
    public List<CurrentUserBean> getLoggedUsers() {
        return loggedUsers;
    }


    /**
     * Set loggedUsers given a <strong>list</strong>
     * @param loggedUsers List
     */
    public void setLoggedUsers(List<CurrentUserBean> loggedUsers) {
        this.loggedUsers = loggedUsers;
    }


    /**
     * Returns a HashMap of users (from CurrentUserBean class): registeredUsers
     * @return HashMap
     */
    public HashMap<String, TempUserBean> getRegisteredUsers() {
        return registeredUsers;
    }


    // Methods
    /**
     * Add to registeredUsers a user if follows all rules and everything is OK
     * @param user TempUserBean
     * @return String, success or failure (outcome)
     */
    public String registerAUser(TempUserBean user) {
        System.out.println("Doing a register...");
        System.out.println("User from front end: " + user.toString());

        if(user.getPassword().equals(user.getConfirmPassword())){
//            CurrentUserBean userToRegister = new CurrentUserBean();
//            userToRegister.setName(user.getName());
//            userToRegister.setUserName(user.getUserName());
//            userToRegister.setPassword(user.getPassword());

            if (this.registeredUsers.putIfAbsent(user.getUserName(), user) == null) {
                // Initialize the HashMap associated with the user
                this.registeredUsers.get(user.getUserName()).setMessages(new HashMap<>());

                System.out.println(OPS_STATUS.SUCCESS);
                System.out.println(user.toString());
                return "success";
            }
            else {
                System.out.println(OPS_STATUS.FAILURE);
                return "failure";
            }
        }
        else {
            System.err.println("Passwords do not match");
        }

        System.out.println(OPS_STATUS.FAILURE);
        return "failure";
    }


    /**
     * Add a <strong>registered</strong> user (in registeredUsers) to loggedUsers.
     * set logged = true
     * @param user TempUserBean
     * @return String, success or failure (an outcome)
     */
    public String doLogin(TempUserBean user, CurrentUserBean currentUserBean) {
        System.out.println("Doing a Login...");
        if (this.registeredUsers.containsKey(user.getUserName())) {
            TempUserBean data = this.registeredUsers.get(user.getUserName());
            if (data.getPassword().equals(user.getPassword())) {
                if(user.isLogged()) {
                    System.out.println("User is logged");
                    System.out.println(OPS_STATUS.SUCCESS);
                    return "failure";
                }else {
                    currentUserBean.setName(data.getName());
                    currentUserBean.setUserName(data.getUserName());
                    currentUserBean.setPassword(data.getPassword());
                    currentUserBean.setLogged(true);
                    System.out.println("This is registered user info: " + currentUserBean.toString());
                    this.loggedUsers.add(currentUserBean);
                    System.out.println(OPS_STATUS.SUCCESS);
//
//                    MsgBackingBean msgBackingBean = new MsgBackingBean();
//                    msgBackingBean.getLoggedUsers();
                    return "success";
                }

            }
        }
        System.out.println(OPS_STATUS.FAILURE);
        return "failure";
    }


    /**
     * Remove a user from loggedUsers and set logged = false
     * @param user CurrentUserBean
     * @return String, success or failure (an outcome)
     */
    public String doLogout(CurrentUserBean user) {
        System.out.println("Doing a Logout...");
        System.out.println(user.toString());
        System.out.println("This is user logged status: " + user.isLogged());
        if(user.isLogged()) {
            user.setLogged(false);
            this.loggedUsers.remove(user);
            System.out.println(OPS_STATUS.SUCCESS);
            return "success";
        }else {
            System.out.println(OPS_STATUS.FAILURE);
            return "failure";
        }
    }


    /**
     * Remove a message given the user and the message id
     * @param userName String
     * @param messageId int
     */
    public void removeMessage(String userName, int messageId) {
        if (this.registeredUsers.containsKey(userName)) {
            TempUserBean user = this.registeredUsers.get(userName);

            if (user.getMessages().containsKey(messageId)) {
                user.getMessages().remove(messageId);
                System.out.printf("Message %d of %s removed!\n", messageId, userName);
            }
            else {
                System.err.printf("Message id (%d) does not exist\n", messageId);
            }
        }
        else {
            System.err.printf("User %s does not exist\n", userName);
        }
    }


    /**
     * Returns a user given its name
     * @param userName String
     * @return TempUserBean
     */
    public TempUserBean getUserByName(String userName) {
        TempUserBean user = this.registeredUsers.getOrDefault(userName, null);

        System.out.println("Try to return " + user + ". Received name: " + userName);

        return user;
    }


    /**
     * "Sends" a message to the 'receiver', given the message text and the sender
     * @param sender String
     * @param receiver String
     * @param msgText String
     */
    public void sendMessage(String sender, String receiver, String msgText) {
        TempUserBean tempUserBean = this.getUserByName(receiver);
        if (tempUserBean != null) {
            System.out.printf("Messages of %s: %s\n", tempUserBean, tempUserBean.getMessages());
            System.out.println("Send message to " + tempUserBean);

            Message message = new Message(Calendar.getInstance().getTime(), sender, receiver, msgText);
            tempUserBean.getMessages().put(message.hashCode(), message);
            //tempUserBean.getMessagesList().add(message);
            System.out.printf("Messages of %s: %s\n", tempUserBean, tempUserBean.getMessages());
        }
        else {
            System.out.println("Cannot send message because received is null");
        }

    }


    /**
     * Returns the messages of a user as list
     * @param userName String
     * @return List<Message>
     */
    public List<Message> getMessagesAsList(String userName) {
        List<Message> messagesAsList = new ArrayList<>();

        if(this.registeredUsers.containsKey(userName)) {
            TempUserBean user = this.registeredUsers.get(userName);
            for (Integer id : user.getMessages().keySet()) {
                messagesAsList.add(user.getMessages().get(id));
            }
        }
        else {
            System.err.printf("User \"%s\" does not exist\n", userName);
        }


        return messagesAsList;
    }


    /**
     * OPS_STATUS, indicates the status of an operation.
     */
    private enum OPS_STATUS {
        /**
         * Constants of enum type
         */
        SUCCESS("SUCCESS"),
        FAILURE("FAILURE");

        // Instance Fields
        private final String status;

        /**Constructor with one argument
         * @param status String*/
        OPS_STATUS(String status) {
            this.status = status;
        }

        /**Getter for status
         * @return String*/
        public String getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return "status='" + getStatus() + '\'';
        }
    }

}
