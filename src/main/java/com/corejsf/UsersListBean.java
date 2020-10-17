package com.corejsf;

import com.corejsf.msg.MsgBackingBean;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @author EAMT on 3/10/2020
 */
@Named("users_list")
@ApplicationScoped

public class UsersListBean implements Serializable {
    /**
     * Instance Variables
     * <strong><em>registeredUsers</em></strong>, type: HashMap
     * <strong><em>loggedUsers</em></strong>, type: List
     */
    private final HashMap<String, UserBeanInterpreter> registeredUsers = new HashMap<>();
    private List<UserBean> loggedUsers = new ArrayList<>();


    /*Getters and Setters*/

    /**
     * Returns loggedUsers instance variable
     * @return List
     */
    public List<UserBean> getLoggedUsers() {
        return loggedUsers;
    }

    /**
     * Set loggedUsers given a <strong>list</strong>
     * @param loggedUsers List
     */
    public void setLoggedUsers(List<UserBean> loggedUsers) {
        this.loggedUsers = loggedUsers;
    }

    /**
     * Returns a HashMap of users (from UserBean class): registeredUsers
     * @return HashMap
     */
    public HashMap<String, UserBeanInterpreter> getRegisteredUsers() {
        return registeredUsers;
    }


    // Methods
    /**
     * Add to registeredUsers a user if follows all rules and everything is OK
     * @param user TempUserBean
     * @return String, success or failure (outcome)
     */
    public String registerAUser(UserBeanInterpreter user) {
        System.out.println("Doing a register...");
        System.out.println("This is arriving from front end");
        System.out.println(user.toString());

        if(user.getPassword().equals(user.getConfirmPassword())){
//            UserBean userToRegister = new UserBean();
//            userToRegister.setName(user.getName());
//            userToRegister.setUserName(user.getUserName());
//            userToRegister.setPassword(user.getPassword());

            if (this.registeredUsers.putIfAbsent(user.getUserName(), user) == null) {
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
     * @param user UserBeanInterpreter
     * @return String, success or failure (an outcome)
     */
    public String doLogin(UserBeanInterpreter user, UserBean userBean) {
        System.out.println("Doing a Login...");
        if (this.registeredUsers.containsKey(user.getUserName())) {
            UserBeanInterpreter data = this.registeredUsers.get(user.getUserName());
            if (data.getPassword().equals(user.getPassword())) {
                if(user.isLogged()) {
                    System.out.println("User is logged");
                    System.out.println(OPS_STATUS.SUCCESS);
                    return "failure";
                }else {
                    userBean.setName(data.getName());
                    userBean.setUserName(data.getUserName());
                    userBean.setPassword(data.getPassword());
                    userBean.setLogged(true);
                    System.out.println("This is registered user info: " + userBean.toString());
                    this.loggedUsers.add(userBean);
                    System.out.println(OPS_STATUS.SUCCESS);
//
//                    MsgBackingBean msgBackingBean = new MsgBackingBean();
//                    msgBackingBean.getList();
                    return "success";
                }

            }
        }
        System.out.println(OPS_STATUS.FAILURE);
        return "failure";
    }


    /**
     * Remove a user from loggedUsers and set logged = false
     * @param user UserBean
     * @return String, success or failure (an outcome)
     */

    public String doLogout(UserBean user) {
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
