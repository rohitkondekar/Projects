/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tease.bean;

/**
 *
 * @author nishi11
 */
/**
 *
 * Shows the login details.
 */
public class Login {
    int userId;
    String userName;
    String password;

     //setter methods to set the value for class variable
    /**
     *  Represents the User id of the Test.
     *
     * @param userId  Unique id of the user.
     *
     * @return void.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /**
     * Represents the user name.
     * 
     * @param userName  name of the user
     * 
     * @return void.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * Represents the password
     *
     * @param password  password must be secret
     *
     * @return  void.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    //Getter methods to get the value of class variables
    /**
     * Gives the user id.
     *
     * @return  userId id of the user.
     */
   
    public int getUserId() {
        return userId;
    }
    /**
     * Gives the user name.
     *
     * @return userName  name of the user.
     */
    public String getUserName() {
        return userName;
    }
    /**
     * Gives the password.
     *
     * @return password password must be secret
     */
    public String getPassword() {
        return password;
    }

}