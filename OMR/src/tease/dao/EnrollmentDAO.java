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
import tease.bean.Enrollment;
import java.sql.SQLException;
import tease.helper.DBConnection;

/**
 * Enrollment of students into groups.
 */
public class EnrollmentDAO {
    //declaring class variables
    private Connection con;
    private ResultSet res;
    private PreparedStatement pstmt;
    private Statement stmt;
    int i;
    int noOfRows;
    public EnrollmentDAO() {
        i=0;
        noOfRows = count();
        con = DBConnection.getConnection();
    }

    /** Inserts a row into a Enrollment table of the database.
     *
     * @param enrollment  an object of Enrollment class.
     *
     * @return boolean - When a row is successfully inserted in enrollment table it returns true.
     *                   When a row is not inserted in enrollment table it returns false.
     */
    public boolean insertEnrollment(Enrollment enrollment) {
        boolean result = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to insert a record
                pstmt = con.prepareStatement("insert into enrollment(idEnrollment,idStudent,idGroup) values(?,?,?)");
                // In the SQL statement being prepared, each question mark is a placeholder
                // that must be replaced with a value you provide through a "set" method invocation.
                pstmt.setString(1, enrollment.getIdEnrollment());
                pstmt.setInt(2, enrollment.getIdStudent());
                pstmt.setInt(3, enrollment.getIdGroup());
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

    /** Deletes a row from an Enrollment table of the database.
     *
     * @param idEnrollment  enrollment id of the student.
     *
     * @return boolean - When a row is successfully deleted from enrollment table it returns true.
     *                   When a row is not deleted from enrollment table it returns false.
     */
    public boolean deleteEnrollment(String idEnrollment) {
        boolean result = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to delete a record
                pstmt = con.prepareStatement("delete from enrollment where idEnrollment=?");
                //Set the value
                pstmt.setString(1,idEnrollment);
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

    /** Updates a row into Enrollment table of the database.
     *
     * @param enrollment  an object of the Enrollment class.
     *
     * @return boolean -  When a row is successfully updated in enrollment table it returns true.
     *                    When a row is not updated in enrollment table it returns false.
     */
    public boolean updateEnrollment(Enrollment enrollment) {
        boolean result = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to update a record
                pstmt = con.prepareStatement("update enrollment set idStudent=?,idGroup=? where idEnrollment=?");
                //Set the value
                pstmt.setInt(1, enrollment.getIdStudent());
                pstmt.setInt(2, enrollment.getIdGroup());
                pstmt.setString(3, enrollment.getIdEnrollment());
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
    /** count the total number of rows in enrollment table.
     * 
     * @return noOfRows - the number of rows present in enrollment table. 
     */

    private int count() {
    //int n=0;
      if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                String query = "select * from enrollment";
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
    /** shows whether enrollment table has next row.
     * 
     * @return boolean - when enrollment table has next row it returns true.
     *                   when enrollment table does not have next row it returns false.
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

    /** shows the details of all rows one by one from an enrollment table
     *
     *@return Enrollment - an object of Enrollment class
     */

     public Enrollment getEnrollment() {
         Enrollment enrollment = null;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
                 try {
                 String query = "select * from enrollment order by idEnrollment limit "+i+","+ (i+1) ;
                //create a statement
                i++;
                pstmt = con.prepareStatement(query);
                res = pstmt.executeQuery();
                if(res.next()) {
                    enrollment = new Enrollment();
                    enrollment.setIdStudent(res.getInt("idStudent"));
                    enrollment.setIdGroup(res.getInt("idGroup"));
                    enrollment.setIdEnrollment(res.getString("idEnrollment"));
                }
                return enrollment;
             }catch (SQLException sqle) {
                sqle.printStackTrace();
              }
        }
      return enrollment;
    }
    /** shows the total number of students in a particular group
      * 
      * @param idGroup  unique id of the group
      * @return studentnum - 
      */
     public int noOfStudents(int idGroup) {
      int n=0;
      int studentnum=0;
      if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                String query = "select * from enrollment where idGroup=? ";
        
                pstmt = con.prepareStatement(query);
                pstmt.setInt(1,idGroup);
                
                res = pstmt.executeQuery();
                res.last();
                studentnum = res.getRow();
                res.beforeFirst();                                                           
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
            return studentnum;
        }


    /** show the details of enrollment based on the enrollment id.
     *
     * @param idEnrollment  unique id of the enrollment of student.
     *
     * @return Enrollment - an object of Enrollment class.
     */
    public Enrollment getEnrollmentByIdEnrollment(String idEnrollment) {
        Enrollment enrollment = null;
         if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                String query = "select idStudent,idGroup from enrollment where idEnrollment=?";
                //create a statement
                pstmt = con.prepareStatement(query);
                //set input parameter
                pstmt.setString(1, idEnrollment);
                res = pstmt.executeQuery();
                //extract data from the ResultSet
                while (res.next()) {
                    enrollment = new Enrollment();
                    enrollment.setIdEnrollment(idEnrollment);
                    enrollment.setIdStudent(res.getInt("idStudent"));
                    enrollment.setIdGroup(res.getInt("idGroup"));
                    
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
        return enrollment;
    }

    /** shows the details of enrollment based on the parameter passed.
     *
     * @param idEnrollment unique id of enrollment
     * @param idStudent unique id of the student
     * @param idGroup unique id of the group
     *
     * @return Enrollment[]- an array of Enrollment object
     */
    public Enrollment[] getEnrollmentByParameter(String idEnrollment, Integer idStudent, Integer idGroup) {
        Enrollment[] enrollment = null;
        boolean IsIdEnrollment = false;
        boolean IsIdStudent = false;
        boolean IsIdGroup = false;
        boolean flag = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                String query = "select * from enrollment where";
                if(idEnrollment != null) {
                    IsIdEnrollment = true;
                    flag = true;
                    query += " idEnrollment='" +idEnrollment+"'";
                }
                if(idStudent != null) {
                    IsIdStudent = true;
                    if(flag==true) {
                        query +=" or idStudent='" +idStudent.toString()+"'";
                    }
                    else {
                        query +=" idStudent='" +idStudent.toString()+"'";
                        flag = true;
                    }
                }
                if(idGroup != null) {
                    IsIdGroup = true;
                    if(flag==true) {
                        query +=" or idGroup='" +idGroup.toString()+"'";
                    }
                    else {
                        query +=" idGroup='" +idGroup.toString()+"'";
                        flag = true;
                    }
                }
                if(query.equals("select * from enrollment where"))
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
                enrollment = new Enrollment[n];
                while(res.next()) {
                    enrollment[j] = new Enrollment();
                    enrollment[j].setIdStudent(res.getInt("idStudent"));
                    enrollment[j].setIdGroup(res.getInt("idGroup"));
                    enrollment[j].setIdEnrollment(res.getString("idEnrollment"));
                    j++;
                }
                return enrollment;
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