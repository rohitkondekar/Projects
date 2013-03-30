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
import tease.bean.Assignment;
import java.sql.*;
import java.sql.SQLException;
import tease.helper.DBConnection;

/**
 * Displays the assignment of teachers to student group.
 */
public class AssignmentDAO {
    //declaring class variables
    private Connection con;
    private ResultSet res;
    private PreparedStatement pstmt;
    private Statement stmt;
    int i;
    int noOfRows;
    public AssignmentDAO() {
        i=0;
        noOfRows = count();
        con = DBConnection.getConnection();
    }

    /** Inserts a row into an Assignment table of the database.
     *
     * @param assignment  an object of Assignment class
     *
     * @return boolean - When a row is successfully inserted in assignment table it returns true.
     *                   When a row is not inserted in assignment table it returns false.
     */
    public boolean insertAssignment(Assignment assignment) {
        boolean result = false;
       if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to insert a record
                pstmt = con.prepareStatement("insert into assignment(idTeacher,idGroup,idTopic,assignmentcol) values(?,?,?,?)");
                // In the SQL statement being prepared, each question mark is a placeholder
                // that must be replaced with a value you provide through a "set" method invocation.
                pstmt.setInt(1, assignment.getIdTeacher());
                pstmt.setInt(2, assignment.getIdGroup());
                pstmt.setInt(3, assignment.getIdTopic());
                pstmt.setString(4, assignment.getAssignmentcol());
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

    /** Deletes a row from an Assignment table of the database.
     *
     * @param idTeacher   unique id of the teacher.
     *
     * @return boolean - When a row is successfully deleted from assignment table it returns true.
     *                   When a row is not deleted from assignment table it returns false.
     */
    public boolean deleteAssignment(int idTeacher) {
        boolean result = false;
       if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to delete a record
                pstmt = con.prepareStatement("delete from assignment where idTeacher=?");
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

    /** Updates a row into an Assignment table of the database.
     *
     * @param assignment  an object of Assignment class
     *
     * @return boolean -  When a row is successfully updated in assignment table it returns true.
     *                    When a row is not updated in assignment table it returns false.
     */
    public boolean updateAssignment(Assignment assignment) {
        boolean result = false;
       if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to update a record
                pstmt = con.prepareStatement("update assignment set assignmentcol=? where idTeacher=? and idGroup=? and idTopic=?");
                //Set the value
                
                pstmt.setString(1, assignment.getAssignmentcol());
                pstmt.setInt(2, assignment.getIdTeacher());
                pstmt.setInt(3, assignment.getIdGroup());
                pstmt.setInt(4, assignment.getIdTopic());
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
    /** count the total number of rows in assignment table.
     * 
     * @return noOfRows - the number of rows present in assignment table. 
     */

    private int count() {
    int n=0;
     if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                String query = "select * from assignment";
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
    /** shows whether assignment table has next row.
     * 
     * @return boolean - when assignment table has next row it returns true.
     *                   when assignment table does not have next row it returns false.
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

    /** shows the details of all rows one by one from an Assignment table
     *
     *@return Assignment - an object of Assignment class
     */

    public Assignment getAssignment() {
         Assignment assignment = null;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
                 try {
                 String query = "select * from assignment limit "+i+","+ (i+1) ;
                //create a statement
                i++;
                pstmt = con.prepareStatement(query);
                res = pstmt.executeQuery();
                if(res.next()) {
                    assignment = new Assignment();
                    assignment.setIdTeacher(res.getInt("idTeacher"));
                    assignment.setIdGroup(res.getInt("idGroup"));
                    assignment.setIdTopic(res.getInt("idTopic"));
                    assignment.setAssignmentcol(res.getString("assignmentcol"));
                }
                return assignment;
             }catch (SQLException sqle) {
                sqle.printStackTrace();
              }
        }

      return assignment;
    }




    /** shows the details of assignment based on teacher id
     *
     * @param idTeacher  unique id of the teacher
     *
     * @return Assignment - an object of assignment class.
     */
    public Assignment getAssignmentByIdTeacher(int idTeacher) {
        Assignment assignment = null;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                String query = "select idGroup,idTopic,assignmentcol from assignment where idTeacher=?";
                //create a statement
                pstmt = con.prepareStatement(query);
                //set input parameter
                pstmt.setInt(1, idTeacher);
                res = pstmt.executeQuery();
                //extract data from the ResultSet
                if(res.next()) {
                    assignment = new Assignment();
                    assignment.setIdTeacher(idTeacher);
                    assignment.setIdGroup(res.getInt("idGroup"));
                    assignment.setIdTopic(res.getInt("idTopic"));
                    assignment.setAssignmentcol(res.getString("assignmentcol"));
                }
            }catch(SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return assignment;
    }

    /** shows the details of teacher based on the parameter passed.
     * 
     *@param idTeacher  unique id of the teacher.
     *@param idGroup  unique id of the group
     *@param idTopic  unique id of the topic.
     *@param assignmentcol  assignment to student by teacher. 
     *
     * @return Assignment[]- an array of Assignment object
     */
    public Assignment[] getAssignmentByParameter(Integer idTeacher,Integer idGroup,Integer idTopic, String assignmentcol) {
        Assignment[] assignment = null;
        boolean IsIdTeacher = false;
        boolean IsIdGroup = false;
        boolean IsIdTopic = false;
        boolean IsAssignmentcol = false;
        boolean IsQualification = false;
        boolean flag = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                String query = "select * from assignment where";
                if(idTeacher != null) {
                    IsIdTeacher = true;
                    flag = true;
                    query += " idTeacher='" +idTeacher.toString()+"'";
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
                if(idTopic != null) {
                    IsIdTopic = true;
                    if(flag==true) {
                        query +=" or idTopic='" +idTopic.toString()+"'";
                    }
                    else {
                        query +=" idTopic='" +idTopic.toString()+"'";
                        flag = true;
                    }
                }
                if(assignmentcol != null) {
                    IsAssignmentcol = true;
                    if(flag==true) {
                    query +=" or assignmentcol='" +assignmentcol+"'";
                    }
                    else {
                      query +=" assignmentcol='" +assignmentcol+"'";
                      flag = true;
                    }
                }
                if(query.equals("select * from assignment where"))
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
                assignment = new Assignment[n];
                while(res.next()) {
                    assignment[j] = new Assignment();
                    assignment[j].setIdTeacher(res.getInt("idTeacher"));
                    assignment[j].setIdGroup(res.getInt("idGroup"));
                    assignment[j].setIdTopic(res.getInt("idTopic"));
                    assignment[j].setAssignmentcol(res.getString("assignmentcol"));
                    j++;
                }
                return assignment;
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





    