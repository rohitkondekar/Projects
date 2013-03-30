/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tease.dao;

/**
 *
 * @author anusha11
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import tease.bean.Test;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import tease.helper.DBConnection;
/**
 *
 *Details of the assessment test to be attempted by students.
 */
public class TestDAO {
    //Declaring class variables
    private Connection con;
    private ResultSet res;
    private PreparedStatement pstmt;
    private Statement stmt;
    int noOfRows;
    //boolean status;
    int i;
    public TestDAO() {
        i=0;
        noOfRows = count();
        //status = false;
        con = DBConnection.getConnection();
    }
    /** Inserts a row into a Test table of the database.
     *
     *@param test -An object of Test class.
     *
     *@return boolean  - Gives true or false
     *                  When a row is successfully Inserted it returns True.
     *                  When a row is not Inserted it returns False.
     */
    public boolean insertTest(Test test) {
        boolean result = false;
         if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to insert a record
                pstmt = con.prepareStatement("insert into test(idTopic,marks,duration,displayName,fullDescription) values(?,?,?,?,?)");
                // In the SQL statement being prepared, each question mark is a placeholder
                // that must be replaced with a value you provide through a "set" method invocation.
                pstmt.setInt(1, test.getIdTopic());
                pstmt.setFloat(2, test.getMarks());
                pstmt.setInt(3, test.getDuration());
                pstmt.setString(4, test.getDisplayName());
                pstmt.setString(5, test.getFullDescription());
                //Insert the row
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

    /** Deletes a row from Test table of the database.
     *
     *@param idTest -Unique id of the assessment test.
     *
     *@return boolean -Gives true or false.
     *                 When a row is successfully Deleted it returns True.
     *                 When a row is not Deleted it returns False.
     */
    public boolean deleteTest(int idTest) {
       boolean result = false;
       if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
       if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to delete a record
                pstmt = con.prepareStatement("delete from test where idTest=?");
                //Set the value
                pstmt.setInt(1,idTest);
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

    /** Updates a row into Test table of the database.
     *
     *@param test -An object of Test class.
     *
     *@return boolean - Gives true or false
     *                  When a row is successfully Updated it returns True.
     *                  When a row is not Updated it returns False.
     */
    public boolean updateTest(Test test) {
        boolean result = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to update a record
                pstmt = con.prepareStatement("update test set idTopic=?,marks=?,duration=?,displayName=?,fullDescription=? where idTest=?");
                //Set the value
                pstmt.setInt(1, test.getIdTopic());
                pstmt.setFloat(2, test.getMarks());
                pstmt.setInt(3, test.getDuration());
                pstmt.setString(4, test.getDisplayName());
                pstmt.setString(5, test.getFullDescription());
                pstmt.setInt(6, test.getIdTest());
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
      if (con == null) {
            con = DBConnection.getConnection();
      }
      if (con != null) {
            try {
                //if(status== false) {
                String query = "select * from test";
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

    /** shows the details of all rows from a TestAnswers table
     *
     *@return TestAnswers - an object of TestAnswers class
     */

    public Test getTest() {
         Test test = null;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
                 try {
                 String query = "select * from test order by idTest limit "+i+","+ (i+1) ;
                //create a statement
                i++;
                pstmt = con.prepareStatement(query);
                res = pstmt.executeQuery();
                if(res.next()) {
                       test =new Test();
                       test.setIdTest(res.getInt("idTest"));
                       test.setIdTopic(res.getInt("idTopic"));
                       test.setMarks(res.getFloat("marks"));
                       test.setDuration(res.getInt("duration"));
                       test.setDisplayName(res.getString("displayName"));
                       test.setFullDescription(res.getString("fullDescription"));
                }
                return test;
             }catch (SQLException sqle) {
                sqle.printStackTrace();
              }
        }

      return test;
    }
   
    /**
     * Show the details of the test based on test id
     * @param idTest -Unique id of the test
     * @return Test -An object of Test class.
     *                         If the given id is not present then returns null.
     */
    public Test getTestByIdTest(int idTest) {
           Test test = null;
           if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
           if(!DBConnection.isClosed(con)) {
               try {
                   String query = "select idTopic,marks,duration,displayName,fullDescription from test where idTest=?";
                   //create a statement
                   pstmt = con.prepareStatement(query);
                   //set input parameter
                   pstmt.setInt(1, idTest);
                   res = pstmt.executeQuery();
                   //extract data from the ResultSet
                   while (res.next()) {
                       test = new Test();
                       test.setIdTest(idTest);
                       test.setIdTopic(res.getInt("idTopic"));
                       test.setMarks(res.getFloat("marks"));
                       test.setDuration(res.getInt("duration"));
                       test.setDisplayName(res.getString("displayName"));
                       test.setFullDescription(res.getString("fullDescription"));
                   }
               }catch(SQLException sqle) {
                   sqle.printStackTrace();
               }finally {
                   //It's important to close the connectionlist when you are done with it
                   DBConnection.closeStatementSet(pstmt);
                   DBConnection.closeResultSet(res);
                   DBConnection.closeConnection(con);
               }
        }
        return test;
    }
    /**
     * Shows the details of test based on Parameter passed.
     * @param idTest  unique id of the test.
     * @param idTopic  unique id of the topic.
     * @param marks  marks gained in the test.
     * @param duration Gives the duration of test.
     * @param displayName Gives the display name of test.
     * @param fullDescription Gives full description of test.
     * @return Test[] An array of Test class.
     */
    public Test[] getTestByParameter(Integer idTest, Integer idTopic, Float marks, Integer duration, String displayName, String fullDescription) {
        Test[] test = null;
        boolean IsIdTest = false;
        boolean IsIdTopic = false;
        boolean IsDuration = false;
        boolean IsMarks = false;
        boolean IsDisplayName = false;
        boolean IsFullDescription = false;
        boolean flag = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
            try {
                String query = "select * from test where";
                if(idTest !=null) {
                    IsIdTest = true;
                    flag = true;
                    query += " idTest='" +idTest.toString()+"'";
                }
                if(idTopic !=null) {
                    IsIdTopic = true;
                    if(flag==true) {
                        query +=" or idTopic='" +idTopic.toString()+"'";
                    }
                    else {
                        query +=" idTopic='" +idTopic.toString()+"'";
                        flag = true;
                    }
                }
                if(marks !=null) {
                    IsMarks = true;
                    if(flag==true) {
                        query +=" or marks='" +marks.toString()+"'";
                    }
                    else {
                        query +=" marks='" +marks.toString()+"'";
                        flag = true;
                    }
                }
                if(duration !=null) {
                    IsDuration = true;
                    if(flag==true) {
                    query +=" or duration='" +duration.toString()+"'";
                    }
                    else {
                      query +=" duration='" +duration.toString()+"'";
                    }
                }
                if(displayName != null) {
                    IsDisplayName = true;
                    if(flag==true) {
                        query +=" or displayName='" +displayName+"'";
                    }
                    else {
                    query +=" displayName='" +displayName+"'";
                    }
                }
                if(fullDescription != null) {
                    IsFullDescription = true;
                    if(flag==true) {
                        query +=" or fullDescription='" +fullDescription+"'";
                    }
                    else {
                    query +=" fullDescription='" +fullDescription+"'";
                    }
                }
                if(query.equals("select * from test where"))
                    return null;
                pstmt = con.prepareStatement(query);
                res = pstmt.executeQuery();
                res.last();

                int n= res.getRow();
                if(n>0)
                {
                res.beforeFirst();
                query +=";";
                System.out.println(query);
                int j=0;

                test = new Test[n];
                while(res.next()) {
                    test[j] = new Test();
                    test[j].setIdTest(res.getInt("idTest"));
                    test[j].setIdTopic(res.getInt("idTopic"));
                    test[j].setDuration(res.getInt("duration"));
                    test[j].setMarks(res.getFloat("marks"));
                    test[j].setDisplayName(res.getString("displayName"));
                    test[j].setFullDescription(res.getString("fullDescription"));
                    j++;
                }
                return test;}
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
   