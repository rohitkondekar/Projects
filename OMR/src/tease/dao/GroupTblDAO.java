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
import tease.bean.GroupTbl;
import java.sql.SQLException;
import tease.helper.DBConnection;

/**
 * Grouping of students. Groups have hierarchy. Students added to each group.
 */
public class GroupTblDAO {
    //declaring class variables
    private Connection con;
    private ResultSet res;
    private PreparedStatement pstmt;
    private Statement stmt;
    int i;
    int noOfRows;
    public GroupTblDAO() {
        i=0;
        noOfRows = count();
        con = DBConnection.getConnection();
    }

    /** Inserts a row into GroupTbl table of the database.
     *
     * @param grouptbl  an object of GroupTbl class.
     *
     * @return boolean - When a row is successfully inserted in groupTbl table it returns true.
     *                   When a row is not inserted in groupTbl table it returns false.
     */
    public boolean insertGroupTbl(GroupTbl grouptbl) {
        boolean result = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to insert a record
                pstmt = con.prepareStatement("insert into grouptbl(idparentGroup,displayName,fullDescription) values(?,?,?)");
                // In the SQL statement being prepared, each question mark is a placeholder
                // that must be replaced with a value you provide through a "set" method invocation.
                pstmt.setInt(1, grouptbl.getIdparentGroup());
                pstmt.setString(2, grouptbl.getDisplayName());
                pstmt.setString(3, grouptbl.getFullDescription());
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

    /** Deletes a row from a groupTbl table of the database.
     *
     * @param idGroup unique id of the group.
     *
     * @return boolean -  When a row is successfully deleted from groupTbl table it returns true.
     *                    When a row is not deleted from groupTbl table it returns false.
     */
    public boolean deleteGroupTbl(int idGroup) {
        boolean result = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to delete a record
                pstmt = con.prepareStatement("delete from grouptbl where idGroup=?");
                //Set the value
                pstmt.setInt(1,idGroup);
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

    /** Updates a row into a groupTbl table of the database.
     *
     * @param grouptbl  an object of the GroupTbl class.
     *
     * @return boolean - When a row is successfully updated in groupTbl table it returns true.
     *                   When a row is not updated in groupTbl table it returns false.
     */
    public boolean updateGroupTbl(GroupTbl grouptbl) {
        boolean result = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to update a record
                pstmt = con.prepareStatement("update grouptbl set idparentGroup=?,displayName=?,fullDescription=? where idGroup=?");
                //Set the value
                pstmt.setInt(1, grouptbl.getIdparentGroup());
                pstmt.setString(2, grouptbl.getDisplayName());
                pstmt.setString(3, grouptbl.getFullDescription());
                pstmt.setInt(4, grouptbl.getIdGroup());
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
    /** count the total number of rows in group table.
     * 
     * @return noOfRows - the number of rows present in group table. 
     */

    private int count() {
    int n=0;
      if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                String query = "select * from grouptbl";
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
    /** shows whether group table has next row.
     * 
     * @return boolean - when group table has next row it returns true
     *                   when group table does not have next row it returns false.
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

    /** shows the details of all rows one by one from group table
     *
     *@return GroupTbl - an object of Group class
     */

     public GroupTbl getGroupTbl() {
         GroupTbl groupTbl = null;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
                 try {
                 String query = "select * from grouptbl order by idGroup limit "+i+","+ (i+1) ;
                //create a statement
                i++;
                pstmt = con.prepareStatement(query);
                res = pstmt.executeQuery();
                if(res.next()) {
                    groupTbl = new GroupTbl();
                    groupTbl.setIdGroup(res.getInt("idGroup"));
                    groupTbl.setIdparentGroup(res.getInt("idparentGroup"));
                    groupTbl.setDisplayName(res.getString("displayName"));
                    groupTbl.setFullDescription(res.getString("fullDescription"));
                }
                return groupTbl;
             }catch (SQLException sqle) {
                sqle.printStackTrace();
              }
        }
      return groupTbl;
    }

    /** Shows the details of group based on group id
     *
     * @param idGroup  unique id of the group
     *
     * @return GroupTbl - an object of GroupTbl class.
     */
    public GroupTbl getGroupByIdGroup(int idGroup) {
        GroupTbl  groupTbl = null;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                String query = "select idparentGroup,displayName,fullDescription from grouptbl where idGroup=?";
                //create a statement
                pstmt = con.prepareStatement(query);
                //set input parameter
                pstmt.setInt(1, idGroup);
                res = pstmt.executeQuery();
                //extract data from the ResultSet
                if(res.next()) {
                    groupTbl = new GroupTbl();
                    groupTbl.setIdGroup(idGroup);
                    groupTbl.setIdparentGroup(res.getInt("idparentGroup"));
                    groupTbl.setDisplayName(res.getString("displayName"));
                    groupTbl.setFullDescription(res.getString("fullDescription"));
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
        return groupTbl;
    }

    /** shows the details of group based on the parameter passed.
     *
     * @param idGroup unique id of the group
     * @param idparentGroup id of the parent group
     * @param displayName the display name of the group
     * @param fullDEscription the full description of the group
     *
     * @return GroupTbl[]- an array of Group object
     */
    public GroupTbl[] getGroupByParameter(Integer idGroup, Integer idparentGroup, String displayName, String fullDescription) {
        GroupTbl[] groupTbl = null;
        boolean IsIdGroup = false;
        boolean IsIdparentGroup = false;
        boolean IsDisplayName = false;
        boolean IsFullDescription = false;
        boolean flag = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                String query = "select * from grouptbl where";
                if(idGroup !=null) {
                    IsIdGroup = true;
                    flag = true;
                    query += " idGroup='" +idGroup.toString()+"'";
                }
                if(idparentGroup !=null) {
                    IsIdparentGroup = true;
                    if(flag==true) {
                        query +=" or idparentGroup='" +idparentGroup.toString()+"'";
                    }
                    else {
                        query +=" idparentGroup='" +idparentGroup.toString()+"'";
                        flag = true;
                    }
                }
                if(displayName != null) {
                    IsDisplayName = true;
                    if(flag==true) {
                        query +=" or displayName='" +displayName+"'";
                    }
                    else {
                        query +=" displayName='" +displayName+"'";
                        flag = true;
                    }
                }
                if(fullDescription != null) {
                    IsFullDescription = true;
                    if(flag==true) {
                    query +=" or fullDescription='" +fullDescription+"'";
                    }
                    else {
                      query +=" fullDescription='" +fullDescription+"'";
                      flag = true;
                    }
                }
                if(query.equals("select * from grouptbl where"))
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
                    groupTbl = new GroupTbl[n];
                    while(res.next())
                    {
                        groupTbl[j] = new GroupTbl();
                        groupTbl[j].setIdGroup(res.getInt("idGroup"));
                        groupTbl[j].setIdparentGroup(res.getInt("idparentGroup"));
                        groupTbl[j].setDisplayName(res.getString("displayName"));
                        groupTbl[j].setFullDescription(res.getString("fullDescription"));
                        j++;
                    }
                    return groupTbl;
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








   