/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tease.test;

/**
 *
 * @author administrator
 */
import tease.bean.Login;
import tease.dao.LoginDAO;
import java.util.*;
public class TestLoginDAO {
    //declaring class variables
    LoginDAO dao = new LoginDAO();
    Login login = new Login();
    Scanner scan = new Scanner(System.in);
    public TestLoginDAO() {
    }
    public void TestInsertLogin() {
        System.out.println(dao.insertLogin(login));
    }
    public void TestDeleteLogin() {
        System.out.println("enter the userid to be deleted");
        int i = scan.nextInt();
        System.out.println(dao.deleteLogin(i));
    }
    public void TestUpdateLogin() {
        System.out.println("enter the parameter value to update");
        System.out.println("user id:");
        int userId = scan.nextInt();
        login.setUserId(userId);
        System.out.println("user Name:");
        String userName = scan.next();
        login.setUserName(userName);
        System.out.println("password:");
        String password = scan.next();
        login.setPassword(password);

        System.out.println(dao.updateLogin(login));
    }
    public void TestHasNext() {
        System.out.println(dao.hasNext());
    }
    public void TestGetLogin() {
  
        for(login=dao.getLogin();login!=null;) {

            System.out.println(login.getUserId());
            System.out.println(login.getUserName());
            System.out.println(login.getPassword());
            login = dao.getLogin();

        }

    }
    
    public void TestGetLoginByParameter() {
        Login[] login = null;
        System.out.println("enter the value for parameters");
        System.out.println("\nuserId:");
        int userId = scan.nextInt();
        System.out.println("\nuserName:");
        String userName = scan.next();
        System.out.println("\npassword:");
        String password = scan.next();

        login= dao.getLoginByParameter(userId, userName, password);
        if(login == null)
                System.out.println("not found the parameter");
        else
         {

           int size = login.length;
           for(int i=0; i<size;i++) {
               System.out.println(login[i].getUserId());
               System.out.println(login[i].getUserName());
               System.out.println(login[i].getPassword());
           }
        }
    }
    public static void main(String args[]) {
        TestLoginDAO test= new TestLoginDAO();
        System.out.println("enter your choice to test method");
        Scanner scan = new Scanner(System.in);
        int i= scan.nextInt();
        switch(i) {
            case 1:test.TestInsertLogin();
            break;
            case 2:test.TestDeleteLogin();
            break;
            case 3:test.TestUpdateLogin();
            break;
            case 4:test.TestHasNext();
            break;
            case 5:test.TestGetLogin();
            break;
            case 6:test.TestGetLoginByParameter();
            break;
            default: System.out.println("exit");
        }
    }

}
