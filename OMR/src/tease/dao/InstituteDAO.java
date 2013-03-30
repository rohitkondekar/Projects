/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tease.dao;

/**
 *
 * @author administrator
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import tease.bean.Institute;
import java.sql.SQLException;
import tease.helper.DBConnection;
/**
 * Gives the Details of the Institute.
 */
public class InstituteDAO {
    //Declaring class variables
    private Connection con;
    private ResultSet res;
    private PreparedStatement pstmt;
    private Statement stmt;
    int noOfRows;
    //boolean status;
    int i;
    public InstituteDAO() {
        i=0;
        noOfRows = count();
        //status = false;
        con = DBConnection.getConnection();
    }
    /**
     *  Inserts a row into a Institute table of the database.
     * @param institute An object of Institute class.
     * @return boolean - Gives true or false
     *                  When a row is successfully Inserted it returns True.
     *                  When a row is not Inserted it returns False.
     */
 
    public boolean insertInstitute(Institute institute) {
        boolean result = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to insert a record
                pstmt = con.prepareStatement("insert into institute(idInstitute,instituteName,motto,logo,levelOfEducation,email,instituteDescription) values(?,?,?,?,?,?,?)");
                // In the SQL statement being prepared, each question mark is a placeholder
                // that must be replaced with a value you provide through a "set" method invocation.
                pstmt.setInt(1, institute.getIdInstitute());
                pstmt.setString(2, institute.getInstituteName());
                pstmt.setString(3, institute.getMotto());
                pstmt.setString(4, institute.getLogo());
                pstmt.setString(5, institute.getLevelOfEducation());
                pstmt.setString(6, institute.getEmail());
                pstmt.setString(7, institute.getInstituteDescription());
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
     * Deletes a row in Institute table.
     * @param instituteName Gives the name of the institute.
     * @return  boolean - Gives true or false
     *                  When a row is successfully Deleted it returns True.
     *                  When a row is not Deleted it returns False.
     */
    public boolean deleteInstitute(int idInstitute) {
        boolean result = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to delete a record
                pstmt = con.prepareStatement("delete from institute where idInstitute=?");
                //Set the value
                pstmt.setInt(1,idInstitute);
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
     * Updates a row in Institute table.
     * @param institute  An object of Institute class.
     * @return  boolean  - Gives true or false
     *                  When a row is successfully Updated it returns True.
     *                  When a row is not Updated it returns False.
     */
    public boolean updateInstitute(Institute institute) {
        boolean result = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
            try {
                //Prepare a statement to update a record
                pstmt = con.prepareStatement("update institute set instituteName=?,motto=?,logo=?,levelOfEducation=?,Email=?,instituteDescription=? where idInstitute=?");
                //Set the value
                pstmt.setString(1, institute.getInstituteName());
                pstmt.setString(2, institute.getMotto());
                pstmt.setString(3, institute.getLogo());
                pstmt.setString(4, institute.getLevelOfEducation());
                pstmt.setString(5, institute.getEmail());
                pstmt.setString(6, institute.getInstituteDescription());
                pstmt.setInt(7, institute.getIdInstitute());

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
                DBConnection.closeResultSet(res);
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
                String query = "select * from institute";
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

    /** shows the details of all rows from a institute table
     *
     *@return institute - an object of institute class
     */

    public Institute getInstitute() {
        Institute institute = null;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
                 try {
                 String query = "select * from institute order by instituteName limit "+i+","+ (i+1) ;
                //create a statement
                i++;
                pstmt = con.prepareStatement(query);
                res = pstmt.executeQuery();
                if(res.next()) {
                    institute = new Institute();
                    institute.setInstituteName(res.getString("instituteName"));
                    institute.setMotto(res.getString("motto"));
                    institute.setLogo(res.getString("logo"));
                    institute.setLevelOfEducation(res.getString("levelOfEducation"));
                    institute.setEmail(res.getString("email"));
                    institute.setInstituteDescription(res.getString("instituteDescription"));
                }
                return institute;
             }catch (SQLException sqle) {
                sqle.printStackTrace();
              }
        }

      return institute;
    } 
    /**
     * Shows the details based on the given parameter.
     * @param instituteName  Displays the name of the institute.
     * @param motto  Displays the aim.
     * @param logo  Gives the content of image.
     * @param levelOfEducation  Displays education level.
     * @param email  Gives the valid email.
     * @param instituteDescription  Gives the institute description
     * @return Institute[] An array of Institute object.
     *         If the given parameter is not present it returns null.
     */
    public Institute[] getInstituteByParameter(Integer idInstitute,String instituteName,String motto,String logo,String levelOfEducation,String email, String instituteDescription) {
        Institute[] institute = null;
        boolean IsIdInstitute = false;
        boolean IsInstituteName = false;
        boolean IsMotto = false;
        boolean IsLogo = false;
        boolean IsLevelOfEducation = false;
        boolean IsEmail = false;
        boolean IsInstituteDescription = false;
        boolean flag = false;
        if(DBConnection.isClosed(con))
            con = DBConnection.getConnection();
            //System.out.println("Here");
        if(!DBConnection.isClosed(con)) {
            try {
                String query = "select * from institute where";
                if(idInstitute !=null) {
                    IsIdInstitute= true;
                    if(flag==true) {
                        query +=" or idInstitute='" +idInstitute+"'";
                    }
                    else {
                        query +=" idInstitute='" +idInstitute+"'";
                        flag = true;
                    }
                }
                if(instituteName !=null) {
                    IsInstituteName= true;
                    if(flag==true) {
                        query +=" or instituteName='" +instituteName+"'";
                    }
                    else {
                        query +=" instituteName='" +instituteName+"'";
                        flag = true;
                    }
                }
                if(motto !=null) {
                    IsMotto = true;
                    if(flag==true) {
                        query +=" or motto='" +motto+"'";
                    }
                    else {
                        query +=" motto='" +motto+"'";
                        flag = true;
                    }
                }
                if(logo !=null) {
                    IsLogo = true;
                    if(flag==true) {
                        query +=" or logo='" +logo+"'";
                    }
                    else {
                        query +=" logo='" +logo+"'";
                        flag = true;
                    }
                }
                if(levelOfEducation !=null) {
                    IsLevelOfEducation = true;
                    if(flag==true) {
                    query +=" or levelOfEducation='" +levelOfEducation+"'";
                    }
                    else {
                      query +=" levelOfEducation='" +levelOfEducation+"'";
                    }
                }
                if(email != null) {
                    IsEmail = true;
                    if(flag==true) {
                        query +=" or email='" +email+"'";
                    }
                    else {
                    query +=" email='" +email+"'";
                    }
                }
                  if(instituteDescription != null) {
                    IsInstituteDescription = true;
                    if(flag==true) {
                        query +=" or instituteDescription='" +instituteDescription+"'";
                    }
                    else {
                    query +=" instituteDescription='" +instituteDescription+"'";
                    }
                }
                pstmt = con.prepareStatement(query);
                res = pstmt.executeQuery();
              
                res.last();

                int n= res.getRow();
                if(n>0)
                {
                res.beforeFirst();
                query +=";";
                System.out.println(query);
                int j=0;

                institute = new Institute[n];
                while(res.next()) {
                    institute[j] = new Institute();
                    institute[j].setIdInstitute(res.getInt("idInstitute"));
                    institute[j].setInstituteName(res.getString("instituteName"));
                    institute[j].setMotto(res.getString("motto"));
                    institute[j].setLogo(res.getString("logo"));
                    institute[j].setLevelOfEducation(res.getString("levelOfEducation"));
                    institute[j].setEmail(res.getString("email"));
                    institute[j].setInstituteDescription(res.getString("instituteDescription"));
                    j++;
                }
                return institute;}
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