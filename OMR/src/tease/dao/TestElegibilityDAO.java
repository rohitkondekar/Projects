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
import tease.bean.TestElegibility;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import tease.helper.DBConnection;
/**
 * The groups/subgroups to whom the test needs to be delivered.
 *
 */
public class TestElegibilityDAO {
    //Declaring class variables
    private Connection con;
    private ResultSet res;
    private PreparedStatement pstmt;
    private Statement stmt;
    int noOfRows;
    //boolean status;
    int i;
    public TestElegibilityDAO() {
        i=0;
        noOfRows = count();
        //status = false;
        con = DBConnection.getConnection();
    }
    /** Inserts a row into TestElegibility table of the database.
     *
     *@param testElegibility -An object of TestElegibility class.
     *
     *@return boolean - Gives true or false
     *                  When a row is successfully Inserted it returns True.
     *                  When a row is not Inserted it returns False.
     */
    public boolean insertTestElegibility(TestElegibility testElegibility) {
        boolean result = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to insert a record
                pstmt = con.prepareStatement("insert into testelegibility(idTest,idGroup,status) values(?,?,?)");
                // In the SQL statement being prepared, each question mark is a placeholder
                // that must be replaced with a value you provide through a "set" method invocation.
                pstmt.setInt(1, testElegibility.getIdTest());
                pstmt.setInt(2, testElegibility.getIdGroup());
                pstmt.setInt(3, testElegibility.getStatus());
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

    /** Delete a row from TestElegibility table of the database.
     *
     *@param idTest  unique id of the assessment test.
     *
     *@return boolean -Gives true or false.
     *                 When a row is successfully Deleted it returns True.
     *                 When a row is not Deleted it returns False.
     */
    public boolean deleteTestElegibility(int idTest) {
        boolean result = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to delete a record
                pstmt = con.prepareStatement("delete from testelegibility where idTest=?");
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

    /** Update a row into TestElegibility table of the database.
     *
     *@param testElegibility -An object of TestElegibility class.
     *
     *@return boolean - Gives true or false
     *                  When a row is successfully updated it returns True.
     *                  When a row is not updated it returns False.
     */
    public boolean updateTestElegibility(TestElegibility testElegibility) {
        boolean result = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to update a record
                pstmt = con.prepareStatement("update testelegibility  set idGroup=?,status=? where idTest=?");
                //Set the value
                pstmt.setInt(1, testElegibility.getIdGroup());
                pstmt.setInt(2, testElegibility.getStatus());
                pstmt.setInt(3, testElegibility.getIdTest());
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
                String query = "select * from testelegibility";
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
      if (con == null) {
            con = DBConnection.getConnection();
        }
        if (con != null) {

        if(i < noOfRows)
            result = true;
    }
        return result;
    }

    /** shows the Elegibility of all rows from a TestElegibility table.
     *
     *@return TestElegibility - an object of TestElegibility class.
     */

    public TestElegibility getTestElegibility() {
        TestElegibility testElegibility = null;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
                 try {
                 String query = "select * from testelegibility order by idTest limit "+i+","+ (i+1) ;
                //create a statement
                i++;
                pstmt = con.prepareStatement(query);
                res = pstmt.executeQuery();
                if(res.next()) {
                       testElegibility =new TestElegibility();
                       testElegibility.setIdTest(res.getInt("idTest"));
                       testElegibility.setIdGroup(res.getInt("idGroup"));
                       testElegibility.setStatus(res.getInt("status"));

                }
                return testElegibility;
             }catch (SQLException sqle) {
                sqle.printStackTrace();
              }
        }

      return testElegibility;
    }
   
    /**
     * Shows theDetails of TestElegibility based on the parameter passed.
     * @param idTest unique id of the test.
     * @param idGroup Gives the group id.
     * @param status Gives the status.
     * @return TestElegibility[]  An array of TestElegibility class.
     *         If the given parameter is not present then returns null.
     */
    public TestElegibility[] getTestElegibilityByParameter(Integer idTest, Integer idGroup,Integer status) {
        TestElegibility[] testElegibility = null;
        boolean IsIdTest = false;
        boolean IsIdGroup = false;
        boolean IsStatus = false;
        boolean flag = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
            try {
                String query = "select * from testelegibility where";
                if(idTest !=null) {
                    IsIdTest = true;
                    flag = true;
                    query += " idTest='" +idTest.toString()+"'";
                }
                if(idGroup!=null) {
                    IsIdGroup = true;
                    if(flag==true) {
                        query +=" or idGroup='" +idGroup.toString()+"'";
                    }
                    else {
                        query +=" idGroup='" +idGroup.toString()+"'";
                        flag = true;
                    }
                }
                if(status !=null) {
                    IsStatus = true;
                    if(flag==true) {
                        query +=" or status='" +status.toString()+"'";
                    }
                    else {
                        query +=" status='" +status.toString()+"'";
                        flag = true;
                    }
                }
                if(query.equals("select * from testelegibility where"))
                    return null;
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

                testElegibility = new TestElegibility[n];
                while(res.next()) {
                    testElegibility[j] = new TestElegibility();
                    testElegibility[j].setIdTest(res.getInt("idTest"));
                    testElegibility[j].setIdGroup(res.getInt("idGroup"));
                    testElegibility[j].setStatus(res.getInt("status"));
                    j++;
                }
                return testElegibility;}
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
    /** list the Elegibility of testElegibility table based on test id.
     *
     * @param idTest -Unique id of test.
     *
     * @return TestElegibility- An object of TestElegibility class.
     *                                    
     */
    public TestElegibility getTestElegibilityByIdTest(int idTest) {
           TestElegibility testElegibility = null;
           if(DBConnection.isClosed(con))
           con = DBConnection.getConnection();
            //System.out.println("Here");
           if(!DBConnection.isClosed(con)) {
               try {
                   String query = "select idGroup,status from testelegibility where idTest=?";
                   //create a statement
                   pstmt = con.prepareStatement(query);
                   //set input parameter
                   pstmt.setInt(1, idTest);
                   res = pstmt.executeQuery();
                   //extract data from the ResultSet
                   while (res.next()) {
                       testElegibility = new  TestElegibility();
                       testElegibility.setIdTest(idTest);
                       testElegibility.setIdGroup(res.getInt("idGroup"));
                       testElegibility.setStatus(res.getInt("status"));
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
        return testElegibility;
    }    
}
