/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tease.dao;

/**
 *
 * @author nishi11
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import tease.bean.Teacher;
import java.sql.SQLException;
import tease.helper.DBConnection;
/**
 * Represents a Teacher enrolled in the school.
 */
public class TeacherDAO {
    //declaring class variables
    private Connection con;
    private ResultSet res;
    private PreparedStatement pstmt;
    private Statement stmt;
    int noOfRows;
    //boolean status;
    int i;
    public TeacherDAO() {
        i=0;
        noOfRows = count();
        //status = false;
        con = DBConnection.getConnection();
    }
    /** Inserts a row into a Teacher table of the database.
     *
     *@param teacher  an object of Teacher class.
     *
     * @return boolean - When a row is successfully inserted in teacher table it returns true.
     *                   When a row is not inserted in teacher table it returns false.
     */
    public boolean insertTeacher(Teacher teacher) {
        boolean result = false;
       if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to insert a record
                pstmt = con.prepareStatement("insert into teacher(firstName,lastName,designation,qualification) values(?,?,?,?)");
                // In the SQL statement being prepared, each question mark is a placeholder
                // that must be replaced with a value you provide through a "set" method invocation.
                pstmt.setString(1, teacher.getFirstName());
                pstmt.setString(2, teacher.getLastName());
                pstmt.setString(3, teacher.getDesignation());
                pstmt.setString(4, teacher.getQualification());
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
                DBConnection.closeConnection(con);
            }
        }
        return result;
    }

    /** Delete a row from a Teacher table of the database.
     *
     *@param idTeacher  unique id of the teacher
     *
     *@return boolean - When a row is successfully deleted from teacher table it returns true.
     *                  When a row is not deleted from teacher table it returns false.
     */
    public boolean deleteTeacher(int idTeacher) {
        boolean result = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to delete a record
                pstmt = con.prepareStatement("delete from teacher where idTeacher=?");
                //Set the value
                pstmt.setInt(1,idTeacher);
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
                DBConnection.closeConnection(con);
            }
        }
        return result;
    }

    /** Updates a row into a Teacher table of the database
     *
     *@param teacher  an object of the Teacher class.
     *
     *@return boolean - When a row is successfully updated in teacher table it returns true.
     *                  When a row is not updated in teacher table it returns false.
     */
    public boolean updateTeacher(Teacher teacher) {
        boolean result = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to update a record
                pstmt = con.prepareStatement("update teacher set firstName=?,lastName=?,designation=?,qualification=? where idTeacher=?");
                //Set the value
                pstmt.setString(1, teacher.getFirstName());
                pstmt.setString(2, teacher.getLastName());
                pstmt.setString(3, teacher.getDesignation());
                pstmt.setString(4, teacher.getQualification());
                pstmt.setInt(5, teacher.getIdTeacher());
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
                DBConnection.closeConnection(con);
            }
        }
        return result;
    }
    /** count the total number of rows in teacher table.
     * 
     * @return noOfRows - the number of rows present in teacher table. 
     */

    private int count() {
    int n=0;
     if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                String query = "select * from teacher";
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
    /** shows whether teacher table has next row.
     * 
     * @return boolean - when teacher table has next row it returns true.
     *                   when teacher table does not have next row it returns false.
     */

    public boolean hasNext() {
      boolean result = false;
      if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {

        if(i < noOfRows)
            result = true;
    }
        return result;
    }

    /** shows the details of all rows one by one from a Teacher table
     *
     *@return Teacher - an object of Teacher class
     */

     public Teacher getTeacher() {
         Teacher teacher = null;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
                 try {
                 String query = "select * from teacher order by idTeacher limit "+i+","+ (i+1) ;
                //create a statement
                i++;
                pstmt = con.prepareStatement(query);
                res = pstmt.executeQuery();
                if(res.next()) {
                    teacher = new Teacher();
                    teacher.setIdTeacher(res.getInt("idTeacher"));
                    teacher.setFirstName(res.getString("firstName"));
                    teacher.setLastName(res.getString("lastName"));
                    teacher.setDesignation(res.getString("designation"));
                    teacher.setQualification(res.getString("qualification"));
                }
                return teacher;
             }catch (SQLException sqle) {
                sqle.printStackTrace();
              }
        }

      return teacher;
    }


    /** show the details of teacher based on teacher id
     *
     * @param idTeacher  unique id of the teacher
     *
     * @return Teacher - an object of Teacher class.
     */
    public Teacher getTeacherByIdTeacher(int idTeacher) {
        Teacher teacher = null;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                String query = "select firstName,lastName,designation,qualification from teacher where idTeacher=?";
                //create a statement
                pstmt = con.prepareStatement(query);
                //set input parameter
                pstmt.setInt(1, idTeacher);
                res = pstmt.executeQuery();
                //extract data from the ResultSet
                if(res.next()) {
                    teacher = new Teacher();
                    teacher.setIdTeacher(idTeacher);
                    teacher.setFirstName(res.getString("firstName"));
                    teacher.setLastName(res.getString("lastName"));
                    teacher.setDesignation(res.getString("designation"));
                    teacher.setQualification(res.getString("qualification"));
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
        return teacher;
    }

    /** shows the details of teacher based on the parameter passed.
     *
     * @param idTeacher unique id of teacher
     * @param firstName the first name of teacher
     * @param lastName the last name of teacher
     * @param designation the designation place of teacher
     * @param qualification the qualification of teacher
     *
     * @return Teacher[]- an array of Teacher object
     */
    public Teacher[] getTeacherByParameter(Integer idTeacher, String firstName, String lastName, String designation, String qualification) {
        Teacher[] teacher = null;
        boolean IsIdTeacher = false;
        boolean IsFirstName = false;
        boolean IsLastName = false;
        boolean IsDesignation = false;
        boolean IsQualification = false;
        boolean flag = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                String query = "select * from teacher where";
                if(idTeacher != null) {
                    IsIdTeacher = true;
                    flag = true;
                    query += " idTeacher='" +idTeacher.toString()+"'";
                }
                if(firstName != null) {
                    IsFirstName = true;
                    if(flag==true) {
                        query +=" or firstName='" +firstName+"'";
                    }
                    else {
                        query +=" firstName='" +firstName+"'";
                        flag = true;
                    }
                }
                if(lastName != null) {
                    IsLastName = true;
                    if(flag==true) {
                        query +=" or lastName='" +lastName+"'";
                    }
                    else {
                        query +=" lastName='" +lastName+"'";
                        flag = true;
                    }
                }
                if(designation != null) {
                    IsDesignation = true;
                    if(flag==true) {
                    query +=" or designation='" +designation+"'";
                    }
                    else {
                      query +=" designation='" +designation+"'";
                      flag = true;
                    }
                }
                if(qualification != null) {
                    IsQualification = true;
                    if(flag==true) {
                        query +=" or qualification='" +qualification+"'";
                    }
                    else {
                    query +=" qualification='" +qualification+"'";
                    flag = true;
                    }
                }
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
                teacher = new Teacher[n];
                while(res.next()) {
                    teacher[j] = new Teacher();
                    teacher[j].setIdTeacher(res.getInt("idTeacher"));
                    teacher[j].setFirstName(res.getString("firstName"));
                    teacher[j].setLastName(res.getString("lastName"));
                    teacher[j].setDesignation(res.getString("designation"));
                    teacher[j].setQualification(res.getString("qualification"));
                    j++;
                }
                return teacher;
                }
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