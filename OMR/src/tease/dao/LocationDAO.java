/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tease.dao;

/**
 *
 * @author administrator
 */
/**
 * Gives the details of the Location table.
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import tease.bean.Location;
import java.sql.SQLException;
import tease.helper.DBConnection;
/**
 * Gives the details of the Location table.
 */
public class LocationDAO {
     //Declaring class variables
    private Connection con;
    private ResultSet res;
    private PreparedStatement pstmt;
    private Statement stmt;
    int noOfRows;
    //boolean status;
    int i;
    public LocationDAO() {
        i=0;
        noOfRows = count();
        //status = false;
        con = DBConnection.getConnection();
    }
   
    /**
     * Inserts a row into a Location table of the database.
     *
     * @param location  An object of location class.
     *
     * @return boolean - Gives true or false
     *                  When a row is successfully Inserted it returns True.
     *                  When a row is not Inserted it returns False.
     */
    public boolean insertLocation(Location location) {
        boolean result = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to insert a record
                pstmt = con.prepareStatement("insert into location(idLocation,locationName,locationStrength) values(?,?,?)");
                // In the SQL statement being prepared, each question mark is a placeholder
                // that must be replaced with a value you provide through a "set" method invocation.
                pstmt.setInt(1, location.getIdLocation());

                pstmt.setString(2, location.getLocationName());
                pstmt.setInt(3, location.getLocationStrength());
                //insert the row
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
    /**
     * Deletes a row into a Location table of the database.
     *
     * @param idLocation  unique id of the location.
     *
     * @return boolean - Gives true or false
     *                  When a row is successfully Deleted it returns True.
     *                  When a row is not Deleted it returns False.
     */
    public boolean deleteLocation(String idLocation) {
        boolean result = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to delete a record
                pstmt = con.prepareStatement("delete from location where idLocation=?");
                //Set the value
                pstmt.setString(1,idLocation);
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
    /**
     * Updates a row into a Location table of the database.
     * @param location  An object of Location class.
     * @return boolean - Gives true or false
     *                  When a row is successfully updated it returns True.
     *                  When a row is not updated it returns False.
     */
    public boolean updateLocation(Location location) {
        boolean result = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to update a record
                pstmt = con.prepareStatement("update location set locationName=?,locationStrength=? where idLocation=?");
                //Set the value
                pstmt.setString(1, location.getLocationName());
                pstmt.setInt(2, location.getLocationStrength());
                pstmt.setInt(3, location.getIdLocation());
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
                String query = "select * from location";
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

    /** shows the details of all rows from a location table
     *
     *@return location - an object of Location class
     */

    public Location getLocation() {
         Location location = null;
         if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
         if(!DBConnection.isClosed(con)) {
                 try {
                 String query = "select * from location order by idLocation limit "+i+","+ (i+1) ;
                //create a statement
                i++;
                pstmt = con.prepareStatement(query);
                res = pstmt.executeQuery();
                if(res.next()) {
                    location = new Location();
                    location.setIdLocation(res.getInt("idLocation"));
                    location.setLocationName(res.getString("locationName"));
                    location.setLocationStrength(res.getInt("locationStrength"));
                }
                return location;
             }catch (SQLException sqle) {
                sqle.printStackTrace();
              }
        }

      return location;
    }
     
    /**
     * Show the details of location table based on location id.
     * @param idlocation  unique id of the location
     * @return location- A single row based on location id.
     */
    public Location getlocationByIdlocation(int idlocation) {
           Location location = null;
           if(DBConnection.isClosed(con))
           con = DBConnection.getConnection();
           //System.out.println("Here");
           if(!DBConnection.isClosed(con)) {
               try {
                   String query = "select locationName,locationStrength from location where idlocation=?";
                   //create a statement
                   pstmt = con.prepareStatement(query);
                   //set input parameter
                   pstmt.setInt(1, idlocation);
                   res = pstmt.executeQuery();
                   //extract data from the ResultSet
                   while (res.next()) {
                       location = new Location();
                       location.setIdLocation(idlocation);
                       location.setLocationName(res.getString("locationName"));
                       location.setLocationStrength(res.getInt("locationStrength"));
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
        return location;
    }

     /**
      * Shows all Details based on the parameter.
      * @param idLocation  Gives the location id.
      * @param locationName Gives the name of the location.
      * @param locationStrength  Gives the strength of the location.
      * @return location[] An Array of Location object.
      *         If the given parameter is not present it returns null.
      */
     public Location[] getLocationByParameter( Integer idLocation, String locationName, Integer locationStrength) {
        Location[] location = null;
        boolean IsIdLocation = false;
        boolean IsLocationName = false;
        boolean IsLocationStrength = false;
        boolean flag = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
            try {
                String query = "select * from location where";
                if(idLocation !=null) {
                    IsIdLocation = true;
                    if(flag==true) {
                        query +=" or idLocation='" +idLocation+"'";
                    }
                    else {
                        query +=" idLocation='" +idLocation+"'";
                        flag = true;
                    }
                }
                if(locationName !=null) {
                    IsLocationName = true;
                    if(flag==true) {
                        query +=" or locationName='" +locationName+"'";
                    }
                    else {
                        query +=" locationName='" +locationName+"'";
                        flag = true;
                    }
                }
                if(locationStrength !=null) {
                    IsLocationStrength= true;
                    if(flag==true) {
                        query +=" or locationStrength='" +locationStrength+"'";
                    }
                    else {
                        query +=" locationStrength='" +locationStrength+"'";
                        flag = true;
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
                location = new Location[n];
                while(res.next()) {
                    location[j] = new Location();
                    location[j].setIdLocation(res.getInt("idlocation"));
                    location[j].setLocationName(res.getString("locationName"));
                    location[j].setLocationStrength(res.getInt("locationStrength"));
                    j++;
                }
                return location;}
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