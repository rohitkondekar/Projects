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
import tease.bean.Topic;
import java.sql.SQLException;
import tease.helper.DBConnection;

/**
 * Hierarchical topic descriptions. A topic is included in pare
 */
public class TopicDAO {
    //declaring class variables
    private Connection con;
    private ResultSet res;
    private PreparedStatement pstmt;
    private Statement stmt;
    int i;
    int noOfRows;
    public TopicDAO() {
        i=0;
        noOfRows = count();
        con = DBConnection.getConnection();
    }

    /** Inserts a row into Topic table of the database.
     *
     *@param topic  an object of Topic class.
     *
     *@return boolean - When a row is successfully inserted in topic table it returns true.
     *                  When a row is not inserted in topic table it returns false.
     */
    public boolean insertTopic(Topic topic) {
        boolean result = false;
       if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to insert a record
                pstmt = con.prepareStatement("insert into topic(idTopic,idParent,displayName,fullName) values(?,?,?,?)");
                // In the SQL statement being prepared, each question mark is a placeholder
                // that must be replaced with a value you provide through a "set" method invocation.
                pstmt.setInt(1, topic.getIdTopic());
                pstmt.setInt(2, topic.getIdParent());
                pstmt.setString(3, topic.getDisplayName());
                pstmt.setString(4, topic.getFullName());
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

    /** Delete a row from  Topic table of the database.
     *
     *@param idTopic  unique id of the topic
     *
     *@return boolean - When a row is successfully deleted from topic table it returns true.
     *                  When a row is not deleted from topic table it returns false.
     */
    public boolean deleteTopic(int idTopic) {
        boolean result = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to delete a record
                pstmt = con.prepareStatement("delete from topic where idTopic=?");
                //Set the value
                pstmt.setInt(1,idTopic);
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

    /** Update a row into a Topic table of the database.
     *
     *@param topic  an object of the Topic class.
     *
     *@return boolean - When a row is successfully updated in topic table it returns true.
     *                  When a row is not updated in topic table it returns false.
     */
    public boolean updateTopic(Topic topic) {
        boolean result = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to update a record
                pstmt = con.prepareStatement("update topic set idParent=?,displayName=?,fullName=? where idTopic=?");
                //Set the value
                pstmt.setInt(1, topic.getIdParent());
                pstmt.setString(2, topic.getDisplayName());
                pstmt.setString(3, topic.getFullName());
                pstmt.setInt(4, topic.getIdTopic());
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
    /** count the total number of rows in topic table.
     * 
     * @return noOfRows - the number of rows present in topic table. 
     */

    private int count() {
    int n=0;
     if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                String query = "select * from topic";
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
    /** shows whether Topic table has next row.
     * 
     * @return boolean - when topic table has next row it returns true.
     *                   when topic table does not have next row it returns false. 
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

    /** shows the details of all rows one by one from Topic table
     *
     *@return Topic - an object of Topic class
     */

     public Topic getTopic() {
         Topic topic = null;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
                 try {
                 String query = "select * from topic order by idTopic limit "+i+","+ (i+1) ;
                //create a statement
                i++;
                pstmt = con.prepareStatement(query);
                res = pstmt.executeQuery();
                if(res.next()) {
                    topic = new Topic();
                    topic.setIdTopic(res.getInt("idTopic"));
                    topic.setIdParent(res.getInt("idParent"));
                    topic.setDisplayName(res.getString("displayName"));
                    topic.setFullName(res.getString("fullName"));
                }
                return topic;
             }catch (SQLException sqle) {
                sqle.printStackTrace();
              }
        }
      return topic;
    }

    /** list the details of Topic based on Topic id
     *
     * @param idTopic  unique id of the Topic
     *
     * @return Topic - an object of Topic class.
     */
    public Topic getTopicByIdTopic(int idTopic) {
        Topic topic = null;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                String query = "select idParent,displayName,fullName from topic where idTopic=?";
                //create a statement
                pstmt = con.prepareStatement(query);
                //set input parameter
                pstmt.setInt(1, idTopic);
                res = pstmt.executeQuery();
                //extract data from the ResultSet
                if(res.next()) {
                    topic = new Topic();
                    topic.setIdTopic(idTopic);
                    topic.setIdParent(res.getInt("idParent"));
                    topic.setDisplayName(res.getString("displayName"));
                    topic.setFullName(res.getString("fullName"));
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
        return topic;
    }

    /** shows the details of topic based on the parameter passed.
     *
     * @param idTopic unique id of topic
     * @param idParent parent id of topic
     * @param displayName the display name of topic
     * @param fullName the full name of topic
     *
     * @return Topic[]- an array of Topic object
     */
    public Topic[] getTopicByParameter(Integer idTopic,Integer idParent, String displayName, String fullName) {
        Topic[] topic = null;
        boolean IsIdTopic = false;
        boolean IsIdParent = false;
        boolean IsDisplayName = false;
        boolean IsFullName = false;
        boolean flag = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
         if(!DBConnection.isClosed(con)) {
            try {
                String query = "select * from topic where";
                if(idTopic != null) {
                    IsIdTopic = true;
                    flag = true;
                    query += " idTopic='" +idTopic.toString()+"'";
                }
                if(idParent != null) {
                    IsIdParent = true;
                    if(flag==true) {
                        query +=" or idParent='" +idParent.toString()+"'";
                    }
                    else {
                        query +=" idParent='" +idParent.toString()+"'";
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
                if(fullName != null) {
                    IsFullName = true;
                    if(flag==true) {
                    query +=" or fullName='" +fullName+"'";
                    }
                    else {
                      query +=" fullName='" +fullName+"'";
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
                topic = new Topic[n];
                while(res.next()) {
                    topic[j] = new Topic();
                    topic[j].setIdTopic(res.getInt("idTopic"));
                    topic[j].setIdParent(res.getInt("idParent"));
                    topic[j].setDisplayName(res.getString("displayName"));
                    topic[j].setFullName(res.getString("fullName"));
                    j++;
                }
                return topic;
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
