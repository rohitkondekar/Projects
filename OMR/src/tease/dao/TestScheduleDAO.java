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
import tease.bean.TestSchedule;
import java.sql.SQLException;
import tease.helper.DBConnection;
import java.util.Calendar;
import java.util.*;
import java.sql.Timestamp;
/**
 *
 * Schedule of test to be given to student groups.
 */
public class TestScheduleDAO {
    //Declaring class variables
    private Connection con;
    private ResultSet res;
    private PreparedStatement pstmt;
    private Statement stmt;
    int noOfRows;
    //boolean status;
    int i;
    public TestScheduleDAO() {
        i=0;
        noOfRows = count();
        //status = false;
        con = DBConnection.getConnection();
    }
    /** Inserts a row into TestSchedule table of the database.
     *
     *@param testSchedule  an object of TestSchedule class.
     *
     *@return boolean - Gives true or false
     *                  When a row is successfully Inserted it returns True.
     *                  When a row is not Inserted it returns False.
     */
    public boolean insertTestSchedule(TestSchedule testSchedule) {
        boolean result = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to insert a record
                pstmt = con.prepareStatement("insert into testschedule(idTest,idGroup,location,startTime,endTime) values(?,?,?,?,?)");
                // In the SQL statement being prepared, each question mark is a placeholder
                // that must be replaced with a value you provide through a "set" method invocation.
                pstmt.setInt(1, testSchedule.getIdTest());
                pstmt.setInt(2, testSchedule.getIdGroup());
                pstmt.setString(3, testSchedule.getLocation());
                /*java.util.Date date = new java.util.Date();
                long t = date.getTime();
                java.sql.Timestamp startTime = new java.sql.Timestamp(t);
                pstmt.setTimestamp(4, testSchedule.getStartTime());
                java.sql.Timestamp endTime = new java.sql.Timestamp(t);
                pstmt.setTimestamp(5, testSchedule.getStartTime());*/

                Calendar cal = Calendar.getInstance();
                pstmt.setTimestamp(4, new java.sql.Timestamp(cal.getTimeInMillis()));
                Calendar cal1 = Calendar.getInstance();
                pstmt.setTimestamp(5, new java.sql.Timestamp(cal.getTimeInMillis()));
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

    /** Delete a row from TestSchedule table of the database.
     *
     *@param idTest  unique id of the assessment test.
     *
     *@return boolean -Gives true or false.
     *                 When a row is successfully Deleted it returns True.
     *                 When a row is not Deleted it returns False.
     */
    public boolean deleteTestSchedule(int idTest) {
        boolean result = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to delete a record
                pstmt = con.prepareStatement("delete from testschedule where idTest=?");
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

    /** Update a row into a TestSchedule table of the database.
     *
     *@param testSchedule  an object of TestSchedule class.
     *
     *@return boolean - Gives true or false
     *                  When a row is successfully upgetTopicdated it returns True.
     *                  When a row is not updated it returns False.
     */
    public boolean updateTestSchedule(TestSchedule testSchedule) {
        boolean result = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to update a record
                pstmt = con.prepareStatement("update testschedule set idGroup=?,location=?,startTime=?,endTime=? where idTest=? ");
                //Set the value
               
                pstmt.setInt(1,testSchedule.getIdGroup());
                pstmt.setString(2, testSchedule.getLocation());
                pstmt.setTimestamp(3, testSchedule.getStartTime());
                pstmt.setTimestamp(4, testSchedule.getEndTime());
                pstmt.setInt(5,testSchedule.getIdTest());
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
                String query = "select * from testschedule";
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

    /** shows the Schedule of all rows from a TestSchedule table.
     *
     *@return TestSchedule - An object of TestSchedule class.
     */

    public TestSchedule getTestSchedule() {
        TestSchedule testSchedule = null;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
                 try {
                 String query = "select * from testschedule order by idTest limit "+i+","+ (i+1) ;
                //create a statement
                i++;
                pstmt = con.prepareStatement(query);
                res = pstmt.executeQuery();
                if(res.next()) {
                       testSchedule =new TestSchedule();
                       testSchedule.setIdTest(res.getInt("idTest"));
                       testSchedule.setIdGroup(res.getInt("idGroup"));
                       testSchedule.setLocation(res.getString("location"));
                       testSchedule.setStartTime(res.getTimestamp("startTime"));
                       testSchedule.setEndTime(res.getTimestamp("endTime"));

                }
                return testSchedule;
             }catch (SQLException sqle) {
                sqle.printStackTrace();
              }
        }

      return testSchedule;
    }
   
    /**
     * Shows the details of TestSchedule based on the parameter passed.
     * @param idTest Unique id of the test.
     * @param idGroup Gives the group id.
     * @param location  Gives the location.
     * @param startTime Gives the start time.
     * @param endTime Gives the end time.
     * @return TestSchedule[] An array of TestSchedule class.
     *                                         If the given parameter is not present then returns null.
     */
    public TestSchedule[] getTestScheduleByParameter(Integer idTest, Integer idGroup, String location, String startTime, String endTime) {
        TestSchedule[] testSchedule = null;
        boolean IsIdTest = false;
        boolean IsIdGroup = false;
        boolean IsLocation = false;
        boolean IsStartTime = false;
        boolean IsEndTime = false;
        boolean flag = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
            try {
                String query = "select * from testschedule where";
                if(idTest !=null) {
                    IsIdTest = true;
                    flag = true;
                    query += " idTest='" +idTest.toString()+"'";
                }
                if(idGroup !=null) {
                    IsIdGroup = true;
                    if(flag==true) {
                        query +=" or idGroup='" +idGroup.toString()+"'";
                    }
                    else {
                        query +=" idGroup='" +idGroup.toString()+"'";
                        flag = true;
                    }
                }
                if(location !=null) {
                    IsLocation = true;
                    if(flag==true) {
                        query +=" or location='" +location+"'";
                    }
                    else {
                        query +=" location='" +location+"'";
                        flag = true;
                    }
                }
                if(startTime!=null) {
                    IsStartTime = true;
                    if(flag==true) {
                    query +=" or startTime='" +startTime.toString()+"'";
                    }
                    else {
                      query +=" startTime='" +startTime.toString()+"'";
                    }
                }
                if(endTime != null) {
                    IsEndTime = true;
                    if(flag==true) {
                        query +=" or endTime='" +endTime.toString()+"'";
                    }
                    else {
                    query +=" endTime='" +endTime.toString()+"'";
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

                testSchedule= new TestSchedule[n];
                while(res.next()) {
                    testSchedule[j] = new TestSchedule();
                    testSchedule[j].setIdTest(res.getInt("idTest"));
                    testSchedule[j].setIdGroup(res.getInt("idGroup"));
                    testSchedule[j].setLocation(res.getString("location"));
                    testSchedule[j].setStartTime(res.getTimestamp("startTime"));
                    testSchedule[j].setEndTime(res.getTimestamp("endTime"));
                    j++;
                }
                return testSchedule;}
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
    /** Show the details of testSchedule based on test id
     *
     * @param idTest  unique id of the test
     *
     * @return TestSchedule- An object of TestSchedule calss.
     *                                        If the given id is not present then returns null.
     */
    public TestSchedule getTestScheduleByIdTest(int idTest) {
           TestSchedule testSchedule = null;
           if(DBConnection.isClosed(con))
           con = DBConnection.getConnection();
           //System.out.println("Here");
           if(!DBConnection.isClosed(con)) {
               try {
                   String query = "select idGroup,location,startTime,endTime from testschedule where idTest=?";
                   //create a statement
                   pstmt = con.prepareStatement(query);
                   //set input parameter
                   pstmt.setInt(1, idTest);
                   res = pstmt.executeQuery();
                   //extract data from the ResultSet
                   while (res.next()) {
                       testSchedule = new  TestSchedule();
                       testSchedule.setIdTest(idTest);
                       testSchedule.setIdGroup(res.getInt("idGroup"));
                       testSchedule.setLocation(res.getString("location"));
                       testSchedule.setStartTime(res.getTimestamp("startTime"));
                       testSchedule.setEndTime(res.getTimestamp("endTime"));
                   }
               }catch(SQLException sqle) {
                   sqle.printStackTrace();
               }finally {
                   //It's important to close the connectionlist when you are done with it
                   DBConnection.closePreparedStatementSet(pstmt);
                   DBConnection.closeResultSet(res);
                   DBConnection.closeConnection(con);
                   DBConnection.closeStatementSet(stmt);
               }
        }
        return testSchedule;
    }
}


