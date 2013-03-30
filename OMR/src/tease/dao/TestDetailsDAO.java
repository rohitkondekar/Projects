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
import tease.bean.TestDetails;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import tease.helper.DBConnection;
/**
 * Details (description of questions) of the test to be given.
 *
 */

public class TestDetailsDAO {
    //Declaring class variables
    private Connection con;
    private ResultSet res;
    private PreparedStatement pstmt;
    private Statement stmt;
     int noOfRows;
    //boolean status;
    int i;
    public TestDetailsDAO() {
        i=0;
        noOfRows = count();
        //status = false;
        con = DBConnection.getConnection();
    }
    /** Inserts a row into TestDetails table of the database.
     *
     *@param testDetails -An object of TestDetails class.
     *
     *@return boolean  -Gives true or false
     *                  When a row is successfully Inserted it returns True.
     *                  When a row is not Inserted it returns False.
     */
    public boolean insertTestDetails(TestDetails testDetails) {
        boolean result = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to insert a record
                pstmt = con.prepareStatement("insert into testdetails(idTest,sequence,questionType,questionOptions,correctAnswer,marks,negativeMarks) values(?,?,?,?,?,?,?)");
                // In the SQL statement being prepared, each question mark is a placeholder
                // that must be replaced with a value you provide through a "set" method invocation.
                pstmt.setInt(1, testDetails.getIdTest());
                pstmt.setInt(2, testDetails.getSequence());
                pstmt.setInt(3, testDetails.getQuestionType());
                pstmt.setInt(4, testDetails.getQuestionOptions());
                pstmt.setString(5, testDetails.getCorrectAnswer());
                pstmt.setFloat(6, testDetails.getMarks());
                pstmt.setFloat(7, testDetails.getNegativeMarks());
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

    /** Delete a row from TestDetails table of the database.
     *
     *@param idTest -Unique id of the assessment test.
     *
     *@return boolean  -Gives true or false.
     *                 When a row is successfully Deleted it returns True.
     *                 When a row is not Deleted it returns False.
     */
    public boolean deleteTestDetails(int idTest) {
        boolean result = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to delete a record
                pstmt = con.prepareStatement("delete from testdetails where idTest=?");
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

    /** Updates a row into TestDetails table of the database.
     *
     *@param testDetails -An object of TestDetails class.
     *
     *@return boolean - Gives true or false
     *                  When a row is successfully updated it returns True.
     *                  When a row is not updated it returns False.
     */
    public boolean updateTestDetails(TestDetails testDetails) {
        boolean result = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to update a record
                pstmt = con.prepareStatement("update testdetails set sequence=?,questionType=?,questionOptions=?,correctAnswer=?,marks=?,negativeMarks=? where idTest=?");
                //Set the valueTestDetailsDAO
                pstmt.setInt(1, testDetails.getSequence());
                pstmt.setInt(2, testDetails.getQuestionType());
                pstmt.setInt(3, testDetails.getQuestionOptions());
                pstmt.setString(4, testDetails.getCorrectAnswer());
                pstmt.setFloat(5, testDetails.getMarks());
                pstmt.setFloat(6, testDetails.getNegativeMarks());
                pstmt.setInt(7, testDetails.getIdTest());
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
                String query = "select * from testdetails";
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

    /** shows the details of all rows from a TestDetails table
     *
     *@return TestDetails - an object of TestDetails class
     */

    public TestDetails getTestDetails() {
        TestDetails testDetails = null;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
                 try {
                 String query = "select * from testdetails order by idTest limit "+i+","+ (i+1) ;
                //create a statement
                i++;
                pstmt = con.prepareStatement(query);
                res = pstmt.executeQuery();
                if(res.next()) {
                       testDetails =new TestDetails();
                       testDetails.setIdTest(res.getInt("idTest"));
                       testDetails.setSequence(res.getInt("sequence"));
                       testDetails.setQuestionType(res.getInt("questionType"));
                       testDetails.setQuestionOptions(res.getInt("questionOptions"));
                       testDetails.setCorrectAnswer(res.getString("correctAnswer"));
                       testDetails.setMarks(res.getFloat("marks"));
                       testDetails.setNegativeMarks(res.getFloat("negativeMarks"));
                }
                return testDetails;
             }catch (SQLException sqle) {
                sqle.printStackTrace();
              }
        }

      return testDetails;
    }
    
    /**
     * Show the Test details based on the parameter passed.
     * @param idTest unique id of the test.
     * @param sequence Gives the sequence.
     * @param questionType  Gives the question type.
     * @param questionOptions Gives the options for the questions.
     * @param correctAnswer  Gives the correct answer.
     * @param marks  Gives the marks gained.
     * @param negativeMarks Gives the negative marks .
     * @return TestDetails[]  An array of TestDetails class.
     */

    public TestDetails[] getTestDetailsByParameter(Integer idTest, Integer sequence, Integer questionType, Integer questionOptions,String correctAnswer, Float marks, Float negativeMarks) {
        TestDetails[] testDetails = null;
        //if(testDetails!= null) {
        boolean IsIdTest = false;
        boolean IsSequence = false;
        boolean IsQuestionType = false;
        boolean IsQuestionOptions = false;
        boolean IsCorrectAnswer = false;
        boolean IsMarks = false;
        boolean IsNegativeMarks = false;
        boolean flag = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
            //if(testDetails!= null) {
            try {
                String query = "select * from testdetails where";
                if(idTest != null) {
                    IsIdTest = true;
                    flag = true;
                    query += " idTest='" +idTest.toString()+"'";
                }
                if(sequence != null) {
                    IsSequence = true;
                    if(flag==true) {
                        query +=" or sequence='" +sequence.toString()+"'";
                    }
                    else {
                        query +=" sequence='" +sequence.toString()+"'";
                        flag = true;
                    }
                }
                if(questionType!= null) {
                    IsQuestionType = true;
                    if(flag==true) {
                        query +=" or questionType='" +questionType.toString()+"'";
                    }
                    else {
                        query +=" questionType='" +questionType.toString()+"'";
                        flag = true;
                    }
                }
                if(questionOptions != null) {
                    IsQuestionOptions = true;
                    if(flag==true) {
                    query +=" or questionOptions='" +questionOptions.toString()+"'";
                    }
                    else {
                      query +=" questionOptions='" +questionOptions.toString()+"'";
                    }
                }
                if(correctAnswer != null) {
                    IsCorrectAnswer = true;
                    if(flag==true) {
                        query +=" or correctAnswer='" +correctAnswer+"'";
                    }
                    else {
                    query +=" correctAnswer='" +correctAnswer+"'";
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
                if(negativeMarks != null) {
                    IsNegativeMarks = true;
                    if(flag==true) {
                        query +=" or negativeMarks='" +negativeMarks.toString()+"'";
                    }
                    else {
                    query +=" negativeMarks='" +negativeMarks.toString()+"'";
                    }
                }
                if(query.equals("select * from testdetails where"))
                    return null;
                pstmt = con.prepareStatement(query);
                res = pstmt.executeQuery();
                res.last();

                int n= res.getRow();
                if(n >0)
                {
                    res.beforeFirst();
                    query +=";";
                    System.out.println(query);
                int j=0;

                testDetails= new TestDetails[n];
                while(res.next()) {
                    testDetails[j] = new TestDetails();
                    testDetails[j].setIdTest(res.getInt("idTest"));
                    testDetails[j].setSequence(res.getInt("sequence"));
                    testDetails[j].setQuestionType(res.getInt("questionType"));
                    testDetails[j].setQuestionOptions(res.getInt("questionOptions"));
                    testDetails[j].setCorrectAnswer(res.getString("correctAnswer"));
                    testDetails[j].setMarks(res.getFloat("marks"));
                    testDetails[j].setNegativeMarks(res.getFloat("negativeMarks"));                   
                    j++;
                }
                return testDetails;}
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


    /** Show the details of Test based on test id
     *
     * @param idTest -Unique id of the test.
     *
     * @return TestDetails -An object of TestDetail class.
     *                 If the given id is not present then returns null.
     */
    public TestDetails getTestDetailsByidTest(int idTest) {
           TestDetails testDetails = null;
           if(DBConnection.isClosed(con))
           con = DBConnection.getConnection();
            //System.out.println("Here");
           if(!DBConnection.isClosed(con)) {
               try {
                   String query = "select sequence,questionType,questionOptions,correctAnswer,marks,negativeMarks from testdetails where idTest=?";
                   //create a statement
                   pstmt = con.prepareStatement(query);
                   //set input parameter
                   pstmt.setInt(1, idTest);
                   res = pstmt.executeQuery();
                   //extract data from the ResultSet
                   while (res.next()) {
                       testDetails = new TestDetails();
                       testDetails.setIdTest(idTest);
                       testDetails.setSequence(res.getInt("sequence"));
                       testDetails.setQuestionType(res.getInt("questionType"));
                       testDetails.setQuestionOptions(res.getInt("questionOptions"));
                       testDetails.setCorrectAnswer(res.getString("correctAnswer"));
                       testDetails.setMarks(res.getFloat("marks"));
                       testDetails.setNegativeMarks(res.getFloat("negativeMarks"));
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
        return testDetails;
    }
}
