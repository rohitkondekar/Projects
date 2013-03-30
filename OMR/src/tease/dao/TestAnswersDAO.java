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
import tease.bean.TestAnswers;
import java.sql.SQLException;
import tease.helper.DBConnection;
/**
 *
 *Answers given by the students.
 */
public class TestAnswersDAO {
    //Declaring class variables
    private Connection con;
    private ResultSet res;
    private PreparedStatement pstmt;
    private Statement stmt;
    int noOfRows;
    //boolean status;
    int i;
    public TestAnswersDAO() {
        i=0;
        noOfRows = count();
        //status = false;
        con = DBConnection.getConnection();
    }
    
    /** Inserts a row into a TestAnswers table of the database.
     *
     *@param testAnswers -An object of testAnswers class.
     *
     *@return boolean - Gives true or false
     *                  When a row is successfully Inserted it returns True.
     *                  When a row is not Inserted it returns False.
     */
    public boolean insertTestAnswers(TestAnswers testAnswers) {
        boolean result = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to insert a record
                pstmt = con.prepareStatement("insert into testanswers(idTest,idStudent,answers,correct,incorrect,marks) values(?,?,?,?,?,?)");
                // In the SQL statement being prepared, each question mark is a placeholder
                // that must be replaced with a value you provide through a "set" method invocation.
                pstmt.setInt(1, testAnswers.getIdTest());
                pstmt.setInt(2, testAnswers.getIdStudent());
                pstmt.setString(3, testAnswers.getAnswers());
                pstmt.setInt(4, testAnswers.getCorrect());
                pstmt.setInt(5, testAnswers.getInCorrect());
                pstmt.setFloat(6, testAnswers.getMarks());
                //Insert the row
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

    /** Delete a row from a TestAnswers table of the database.
     *
     *@param idTest -Unique id of the assessment test.
     *
     *@return boolean -Gives true or false.
     *                 When a row is successfully Deleted it returns True.
     *                 When a row is not Deleted it returns False.
     */
    public boolean deleteTestAnswers(int idTest) {
         boolean result = false;
         if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
         if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to delete a record
                pstmt = con.prepareStatement("delete from testanswers where idTest=?");
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
                 DBConnection.closeStatementSet(pstmt);
                 DBConnection.closeResultSet(res);
                 DBConnection.closeConnection(con);
            }
        }
        return result;
    }

    /** Update a row into a TestAnswers table of the database.
     *
     *@param testAnswers -An object of testAnswers class.
     *
     *@return boolean - Gives true or false
     *                  When a row is successfully updated it returns True.
     *                  When a row is not updated it returns False.
     */
    public boolean updateTestAnswers(TestAnswers testAnswers) {
        boolean result = false;
        if(testAnswers == null)
            return false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to update a record
                pstmt = con.prepareStatement("update testanswers set answers=?,correct=?,inCorrect=?,marks=? where idTest=? and idStudent =?");
                //Set the value
                // pstmt.setInt(1, testAnswers.getIdStudent());
                pstmt.setString(1, testAnswers.getAnswers());
                pstmt.setInt(2, testAnswers.getCorrect());
                pstmt.setInt(3, testAnswers.getInCorrect());
                pstmt.setFloat(4,testAnswers.getMarks());
                pstmt.setInt(5,testAnswers.getIdTest());
                pstmt.setInt(6, testAnswers.getIdStudent());
                //Update the row
                int rowUpdated = pstmt.executeUpdate();
                if(rowUpdated == 1) {
                    result = true;
                }
            }catch(SQLException sqle) {
                sqle.printStackTrace();
            }finally {
                //It's important to close the connection when you are done with it
                DBConnection.closeStatementSet(pstmt);
                // DBConnection.closeResultSet(res);
                DBConnection.closeConnection(con);;
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
                String query = "select * from testanswers";
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

    public TestAnswers getTestAnswers() {
         TestAnswers testAnswers = null;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
                 try {
                 String query = "select * from testanswers order by idTest limit "+i+","+ (i+1) ;
                //create a statement
                i++;
                pstmt = con.prepareStatement(query);
                res = pstmt.executeQuery();
                if(res.next()) {
                       testAnswers =new TestAnswers();
                       testAnswers.setIdTest(res.getInt("idTest"));
                       testAnswers.setIdStudent(res.getInt("idStudent"));
                       testAnswers.setAnswers(res.getString("answers"));
                       testAnswers.setCorrect(res.getInt("correct"));
                       testAnswers.setInCorrect(res.getInt("inCorrect"));
                       testAnswers.setMarks(res.getFloat("marks"));
                }
                return testAnswers;
             }catch (SQLException sqle) {
                sqle.printStackTrace();
              }
        }

      return testAnswers;
    }
   
    
    /**
     * Show the details of Test Answers based on id Test
     * @param idTest -Unique id of the Test
     * @return TestAnswers- An object of TestAnswers class.
     *                 If the given id is not present it returns null.
     */
    public TestAnswers getTestAnswersByIdTest(int idTest) {
           TestAnswers testAnswers = null;
           if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
           if(!DBConnection.isClosed(con)) {
               try {
                   String query = "select idStudent,answers,correct,inCorrect,marks from testanswers where idTest=?";
                   //create a statement
                   pstmt = con.prepareStatement(query);
                   //set input parameter
                   pstmt.setInt(1, idTest);
                   res = pstmt.executeQuery();
                   //extract data from the ResultSet
                   while (res.next()) {
                       testAnswers = new TestAnswers();
                       testAnswers.setIdTest(idTest);
                       testAnswers.setIdStudent(res.getInt("idStudent"));
                       testAnswers.setAnswers(res.getString("answers"));
                       testAnswers.setCorrect(res.getInt("correct"));
                       testAnswers.setInCorrect(res.getInt("incorrect"));
                       testAnswers.setMarks(res.getFloat("marks"));
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
        return testAnswers;
    }
    /**
     * shows the details of testanswers based on the parameter passed.
     * @param idTest Unique id of the test.
     * @param idStudent Unique id of the student.
     * @param answers Gives the answers.
     * @param correct  The answers which are correct.
     * @param inCorrect The answers which are incorrect.
     * @param marks  marks gained in test.
     * @return TestAnswers[] An array of TestAnswers object.
     *         If the given parameter is not present then returns null.
     */
    public TestAnswers[] getTestAnswersByParameter(Integer idTest,Integer idStudent, String answers, Integer correct, Integer inCorrect,Float marks) {
        TestAnswers[] testAnswers = null;
        boolean IsIdTest = false;
        boolean IsIdStudent = false;
        boolean IsAnswers = false;
        boolean IsCorrect = false;
        boolean IsInCorrect = false;
        boolean IsMarks = false;
        boolean flag = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
            try {
                String query = "select * from testanswers where";
                if(idTest != null) {
                    IsIdTest= true;
                    if(flag==true) {
                        query +=" or idTest='" +idTest.toString()+"'";
                    }
                    else {
                        query +=" idTest='" +idTest.toString()+"'";
                        flag = true;
                    }
                }
                if(idStudent != null) {
                    IsIdStudent= true;
                    if(flag==true) {
                        query +=" or idStudent='" +idStudent.toString()+"'";
                    }
                    else {
                        query +=" idStudent='" +idStudent.toString()+"'";
                        flag = true;
                    }
                }
                if(answers != null) {
                    IsAnswers = true;
                    if(flag==true) {
                        query +=" or answers='" +answers+"'";
                    }
                    else {
                        query +=" answers='" +answers+"'";
                        flag = true;
                    }
                }
                if(correct !=null) {
                    IsCorrect = true;
                    if(flag==true) {
                        query +=" or correct='" +correct.toString()+"'";
                    }
                    else {
                    query +=" correct='" +correct.toString()+"'";
                    flag = true;
                    }
                }
                if(inCorrect !=null) {
                    IsInCorrect = true;
                    if(flag==true) {
                        query +=" or inCorrect='" +inCorrect.toString()+"'";
                    }
                    else {
                    query +=" inCorrect='" +inCorrect.toString()+"'";
                    }
                }
                if(marks !=null) {
                     IsMarks = true;
                    if(flag==true) {
                        query +=" or marks='" +marks.toString()+"'";
                    }
                    else {
                    query +=" marks='" +marks.toString()+"'";
                    }

                }
                
                if(query.equals("select * from testanswers where"))
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
                testAnswers = new TestAnswers[n];
                while(res.next()) {
                    testAnswers[j] = new TestAnswers();
                    testAnswers[j].setIdTest(res.getInt("idTest"));
                    testAnswers[j].setIdStudent(res.getInt("idStudent"));
                    testAnswers[j].setAnswers(res.getString("answers"));
                    testAnswers[j].setCorrect(res.getInt("correct"));
                    testAnswers[j].setInCorrect(res.getInt("inCorrect"));
                    testAnswers[j].setMarks(res.getFloat("marks"));
                   
                    j++;
                }
                return testAnswers;}
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
    
