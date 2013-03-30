/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tease.dao;

/**
 *
 * @author administrator
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import tease.bean.Login;
import java.sql.SQLException;
import tease.helper.DBConnection;
/**
 * Shows the login details.
 */
public class LoginDAO {
    //Declaring class variables
    private Connection con;
    private ResultSet res;
    private PreparedStatement pstmt;
    private Statement stmt;
    int noOfRows;
    //boolean status;
    int i;
    public LoginDAO() {
        i=0;
        noOfRows = count();
        //status = false;
        con = DBConnection.getConnection();
    }

    /**
     * Inserts a row into a Login table of the database.
     *
     * @param login An object of Login class.
     *
     * @return  boolean  - Gives true or false
     *                  When a row is successfully Inserted it returns True.
     *                  When a row is not Inserted it returns False.
     */
    public boolean insertLogin(Login login) {
        boolean result = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to insert a record
                pstmt = con.prepareStatement("insert into login(userId,userName,password) values(?,?,?)");
                // In the SQL statement being prepared, each question mark is a placeholder
                // that must be replaced with a value you provide through a "set" method invocation.

                pstmt.setInt(1, login.getUserId());

                pstmt.setString(2, login.getUserName());
                pstmt.setString(3, login.getPassword());
                //insert the row
                int rowUpdated = pstmt.executeUpdate();
                if(rowUpdated == 1) {
                    result = true;
                }
            }catch(SQLException sqle) {
                sqle.printStackTrace();
            }finally {
                //It's important to close the connection when you are done with it
                DBConnection.closeStatementSet(pstmt);
                DBConnection.closeResultSet(res);
                DBConnection.closeConnection(con);
            }
        }
        return result;
    }
    /**
     * Deletes a row into a Login table of the database.
     *
     * @param userId  unique user id.
     *
     * @return boolean -Gives true or false.
     *                 When a row is successfully Deleted it returns True.
     *                 When a row is not Deleted it returns False.
     */
    public boolean deleteLogin(int userId) {
        boolean result = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to delete a record
                pstmt = con.prepareStatement("delete from login where userId=?");
                //Set the value
                pstmt.setInt(1, userId);
                //Delete the row
                int rowUpdated = pstmt.executeUpdate();
                if(rowUpdated == 1) {
                    result = true;
                }
            }catch(SQLException sqle) {
                sqle.printStackTrace();
            }finally {
                //It's important to close the connection when you are done with it
                 DBConnection.closePreparedStatementSet(pstmt);
                 DBConnection.closeResultSet(res);
                 DBConnection.closeConnection(con);
                 DBConnection.closeStatementSet(stmt);
            }
        }
        return result;
    }
    /**
     * Updates a row into Login table of the database.
     *
     * @param login A n object of Login class.
     *
     * @return boolean Gives true or false
     *                  When a row is successfully Updated it returns True.
     *                  When a row is not Updated it returns False.
     */
    public boolean updateLogin(Login login) {
        boolean result = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to update a record
                pstmt = con.prepareStatement("update login set userName=?,password=? where userId=?");
                //Set the value

                pstmt.setString(1, login.getUserName());
                pstmt.setString(2, login.getPassword());
                pstmt.setInt(3, login.getUserId());

                //Update the row
                int rowUpdated = pstmt.executeUpdate();
                if(rowUpdated == 1) {
                    result = true;
                }
            }catch(SQLException sqle) {
                sqle.printStackTrace();
            }finally {
                //It's important to close the connection when you are done with it
                 DBConnection.closePreparedStatementSet(pstmt);
                 DBConnection.closeResultSet(res);
                 DBConnection.closeConnection(con);
                 DBConnection.closeStatementSet(stmt);
            }
        }
        return result;
    }
    private int count() {
      int n=0;
      if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
      if(!DBConnection.isClosed(con)) {
            try {
                //if(status== false) {
                String query = "select * from login";
                pstmt = con.prepareStatement(query);
                res = pstmt.executeQuery();
                res.last();
                noOfRows= res.getRow();
                res.beforeFirst();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
            return noOfRows;
    }
    /**
     * Shows whether it has next row.
     * @return boolean Gives true or false.
     */

    public boolean hasNext() {
      boolean result = false;
      if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
      if(!DBConnection.isClosed(con)) {

        if(i < noOfRows)
            result = true;
    }
        return result;
    }

    /** shows the details of all rows from a login table
     *
     *@return login - an object of Login class
     */

    public Login getLogin() {
         Login login = null;
         if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
         if(!DBConnection.isClosed(con)) {
                 try {
                 String query = "select * from login order by userId limit "+i+","+ (i+1) ;
                //create a statement
                i++;
                pstmt = con.prepareStatement(query);
                res = pstmt.executeQuery();
                if(res.next()) {
                    login = new Login();
                    login.setUserId(res.getInt("userId"));
                    login.setUserName(res.getString("userName"));
                    login.setPassword(res.getString("password"));
                }
                return login;
             }catch (SQLException sqle) {
                sqle.printStackTrace();
              }
        }

      return login;
    }
    
    /**
     * Shows the login details.
     * @param userId  Displays the user id.
     * @param userName Displays name of the user.
     * @param password  Gives the password for the user.
     * @return login[] An array of login object.
     *         If the given parameter is not present it returns null.
     */
    public Login[] getLoginByParameter( Integer userId, String userName, String password) {
        Login[] login = null;
        boolean IsUserId = false;
        boolean IsUserName = false;
        boolean IsPassword = false;
        boolean flag = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
            try {
                String query = "select * from login where";
                if(userId !=null) {
                    IsUserId = true;
                    if(flag==true) {
                        query +=" or userId='" +userId+"'";
                    }
                    else {
                        query +=" userId='" +userId+"'";
                        flag = true;
                    }
                }
                if(userName !=null) {
                    IsUserName = true;
                    if(flag==true) {
                        query +=" or userName='" +userName+"'";
                    }
                    else {
                        query +=" userName='" +userName+"'";
                        flag = true;
                    }
                }
                if(password !=null) {
                    IsPassword= true;
                    if(flag==true) {
                        query +=" or password='" +password+"'";
                    }
                    else {
                        query +=" password='" +password+"'";
                        flag = true;
                    }
                }
                pstmt = con.prepareStatement(query);
                res = pstmt.executeQuery();
                //int n =count("select * from test where idtest=?,firstName=?,lastName=?,designation=?,displayName=?");
                res.last();

                int n= res.getRow();
                if(n>0)
                {
                res.beforeFirst();
                query +=";";
                System.out.println(query);
                int j=0;
                login = new Login[n];
                while(res.next()) {
                    login[j] = new Login();
                    login[j].setUserId(res.getInt("userId"));
                    login[j].setUserName(res.getString("userName"));
                    login[j].setPassword(res.getString("password"));
                    j++;

                }
                return login;}
                return null;
           } catch (SQLException sqle) {
                sqle.printStackTrace();
            } finally {
                //It's important to close the connection when you are done with it
                DBConnection.closeStatementSet(pstmt);
                DBConnection.closeResultSet(res);
                DBConnection.closeConnection(con);
            }
        }
      return null;
    }
    
}