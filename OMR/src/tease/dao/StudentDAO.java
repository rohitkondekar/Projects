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
import tease.bean.Student;
import java.sql.SQLException;
import tease.helper.DBConnection;
import java.util.Calendar;
import java.sql.*;
//import java.util.*;

/**
 * Student table containing bare bones student data
 */
public class StudentDAO {
    //declaring class variables
    private Connection con;
    private ResultSet res;
    private PreparedStatement pstmt;
    private Statement stmt;
    int i;
    int noOfRows;
    public StudentDAO() {
        i=0;
        noOfRows = count();
        con = DBConnection.getConnection();
    }
    /** Inserts a row into student table of the database.
     *
     *@param student  an object of Student class.
     *
     * @return boolean - When a row is successfully inserted in student table it returns true.
     *                   When a row is not inserted in student table it returns false.
     */
    public boolean insertStudent(Student student) {
        boolean result = false;
         if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to insert a record
                pstmt = con.prepareStatement("insert into student(firstName,lastName,rollNumber,birthDate,gender,email,mobile) values(?,?,?,?,?,?,?)");
                // In the SQL statement being prepared, each question mark is a placeholder
                // that must be replaced with a value you provide through a "set" method invocation.
                pstmt.setString(1, student.getFirstName());
                pstmt.setString(2, student.getLastName());
                pstmt.setString(3, student.getRollNumber());
                pstmt.setDate(4, student.getBirthDate());
                //Calendar cal = Calendar.getInstance();
                //pstmt.setDate(5, new java.sql.Date(cal.getTimeInMillis()));
                pstmt.setString(5, String.valueOf(student.getGender()));
                pstmt.setString(6, student.getEmail());
                pstmt.setLong(7, student.getMobile());
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

    /** Deletes a row from student table of the database.
     *
     *@param idStudent  unique id of the student.
     *
     *@return boolean - When a row is successfully deleted from student table it returns true.
     *                  When a row is not deleted from student table it returns false.
     */
    public boolean deleteStudent(int idStudent) {
        boolean result = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to delete a record
                pstmt = con.prepareStatement("delete from student where idStudent=?");
                //Set the value
                pstmt.setInt(1,idStudent);
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

    /** update a row into student table of the database.
     *
     *@param student  an object of Student class.
     *
     * @return boolean - When a row is successfully updated in student table it returns true.
     *                   When a row is not updated in student table it returns false.
     */
    public boolean updateStudent(Student student) {
        boolean result = false;
         if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                System.out.println("Again here");
                //prepare a statement to update a record
                pstmt = con.prepareStatement("update student set firstName=?,lastName=?,rollNumber=?,birthDate=?,gender=?,email=?,mobile=? where idStudent=?");
                // In the SQL statement being prepared, each question mark is a placeholder
                // that must be replaced with a value you provide through a "set" method invocation.
                pstmt.setString(1, student.getFirstName());
                pstmt.setString(2, student.getLastName());
                pstmt.setString(3, student.getRollNumber());
                pstmt.setDate(4, student.getBirthDate());
                //Calendar cal = Calendar.getInstance();
                //pstmt.setDate(4, new java.sql.Date(cal.getTimeInMillis()));
                pstmt.setString(5, String.valueOf(student.getGender()));
                pstmt.setString(6, student.getEmail());
                pstmt.setLong(7, student.getMobile());
                pstmt.setInt(8, student.getIdStudent());
                System.out.println("here");
                int rowUpdated = pstmt.executeUpdate();
                System.out.println("now here");
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
    /** count the total number of rows in student table.
     * 
     * @return noOfRows - the number of rows present in student table. 
     */

     private int count() {
     int n=0;
      if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                String query = "select * from student";
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
     /** shows whether student table has next row.
      * 
      * @return boolean - when student table has next row it returns true.
      *                   when student table does not have next row it returns false. 
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

    /** shows the details of all rows one by one from a Student table
     *
     *@return Student - an object of Student class
     */

     public Student getStudent() {
         Student student = null;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
                 try {
                 String query = "select * from student order by idStudent limit "+i+","+ (i+1) ;
                //create a statement
                i++;
                pstmt = con.prepareStatement(query);
                res = pstmt.executeQuery();
                if(res.next()) {
                    student = new Student();
                    student.setIdStudent(res.getInt("idStudent"));
                    student.setFirstName(res.getString("firstName"));
                    student.setLastName(res.getString("lastName"));
                    student.setRollNumber(res.getString("rollNumber"));
                    student.setBirthDate(res.getDate("birthDate"));
                    student.setGender(res.getString("gender").charAt(0));
                    student.setEmail(res.getString("email"));
                    student.setMobile(res.getLong("mobile"));
                }
                return student;
             }catch (SQLException sqle) {
                sqle.printStackTrace();
              }
        }
      return student;
    }

    /** Show the details of student based on student id
     *
     * @param idStudent  unique id of the student
     *
     * @return Student - an object of student class.
     */
    public Student getStudentByIdStudent(int idStudent) {
        Student student = null;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                String query = "select firstName,lastName,rollNumber,birthDate,gender,email,mobile from student where idStudent=?";
                //create a statement
                pstmt = con.prepareStatement(query);
                //set input parameter
                pstmt.setInt(1, idStudent);
                res = pstmt.executeQuery();
                //extract data from the ResultSet
                if(res.next()) {
                    student = new Student();
                    student.setIdStudent(idStudent);
                    student.setFirstName(res.getString("firstName"));
                    student.setLastName(res.getString("lastName"));
                    student.setRollNumber(res.getString("rollNumber"));
                    student.setBirthDate(res.getDate("birthDate"));
                    student.setGender(res.getString("gender").charAt(0));
                    student.setEmail(res.getString("email"));
                    student.setMobile(res.getLong("mobile"));
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
        return student;
    }

    /** shows the details of the student based on the parameter passed
     *
     * @param idStudent unique id of student
     * @param firstName the first name of student
     * @param lastName the last name of student
     * @param rollNumber the roll number of student
     * @param birthDate the date of birth of student
     * @param gender the gender of student
     * @param email the email id of student
     * @param mobile the mobile number of student
     *
     * @return Student[] - an array of Student object
     */
    public Student[] getStudentByParameter(Integer idStudent, String firstName, String lastName,String rollNumber, Date birthDate, char gender, String email, long mobile) {
        Student[] student = null;
        boolean IsIdStudent = false;
        boolean IsFirstName = false;
        boolean IsLastName = false;
        boolean IsRollNumber = false;
        boolean IsBirthDate = false;
        boolean IsGender = false;
        boolean IsEmail = false;
        boolean IsMobile = false;
        boolean flag = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
        String query = "select * from student where";
        if(idStudent != null) {
            IsIdStudent = true;
            flag = true;
            query += " idStudent ='" +idStudent+"'";
        }
        if(firstName != null) {
            IsFirstName = true;
            if(flag == true) {
            query += " or firstName='" +firstName+"'";
            }
            else {
                        query +=" firstName='" +firstName+"'";
                        flag = true;
                    }
        }
        if(lastName != null) {
            IsLastName = true;
            if(flag == true) {
            query += " or lastName='" +lastName+"'";
            }
            else {
                        query +=" lastName='" +lastName+"'";
                        flag = true;
                    }
        }
        if(rollNumber != null) {
            IsRollNumber = true;
            if(flag == true) {
            query += " or rollNumber='" +rollNumber+"'";
            }
            else {
                        query +=" rollNumber='" +rollNumber+"'";
                        flag = true;
                    }
        }
        if(birthDate != null) {
            IsBirthDate = true;
            if(flag == true) {
            query += " or birthDate='" +birthDate+"'";
            }
            else {
                        query +=" birthDate='" +birthDate+"'";
                        flag = true;
                    }
        }
        
        if(gender != '\u0000') {
            IsGender = true;
            if(flag == true) {
            query += "or gender ='" +gender+"'";
            }
            else {
                        query +=" gender='" +gender+"'";
                        flag = true;
                    }
            
        }
        if(email != null) {
            IsEmail = true;
            if(flag == true) {
            query += " or email='" +email+"'";
            }
            else {
                        query +=" email='" +email+"'";
                        flag = true;
                    }
        }
        if(mobile >0l) {
            IsMobile = true;
            if(flag == true) {
            query += " or mobile=" +mobile;
            }
            else {
                        query +=" mobile=" +mobile;
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
                student = new Student[n];
                while(res.next()) {
                    student[j] = new Student();
                    student[j].setIdStudent(res.getInt("idStudent"));
                    student[j].setFirstName(res.getString("firstName"));
                    student[j].setLastName(res.getString("lastName"));
                    student[j].setRollNumber(res.getString("rollNumber"));
                    student[j].setBirthDate(res.getDate("birthDate"));
                    student[j].setGender(res.getString("gender").charAt(0));
                    student[j].setEmail(res.getString("email"));
                    student[j].setMobile(res.getLong("mobile"));
                    j++;
                }
                return student;
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