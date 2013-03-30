/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tease.helper;

/**
 *
 * @author nishi11
 */
import java.sql.*;
public class DBConnection {
/** Establishes the connection.
 *
 * @return connection
 */
public static Connection getConnection() {
        Connection con = null;
        try {
            // Load the Driver class.
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/teasedb","tease","123");
        }catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }catch(SQLException sqle) {
            sqle.printStackTrace();
        }
        return con;
    }
    /** closes the connection
     *
     * @param con  an object of Connection.
     */

    public static void closeConnection(Connection con) {
        if(con != null) {
            try {
                con.close();
                con = null;
            }catch(SQLException sqle) {
                sqle.printStackTrace();
            }
        }
    }
    /** closes the result set.
     *
     * @param res  an instance of ResultSet.
     */

    public static void closeResultSet(ResultSet res) {
        if(res != null) {
            try {
                res.close();
                res = null;
            }catch(SQLException sqle) {
                sqle.printStackTrace();
            }
        }
    }
    /** closes the prepared statement.
     *
     * @param pstmt  an instance of preparedStatement.
     */
    public static void closePreparedStatementSet(PreparedStatement pstmt) {
        if(pstmt != null) {
            try {
                pstmt.close();
                pstmt = null;
            }catch(SQLException sqle) {
                sqle.printStackTrace();
            }
        }
    }
    /** closes the statement set.
     *
     * @param stmt  an instance of StatementSet.
     */
    public static void closeStatementSet(Statement stmt) {
        if(stmt != null) {
            try {
                stmt.close();
                stmt = null;
            }catch(SQLException sqle) {
                sqle.printStackTrace();
            }
        }
    }

    public static boolean isClosed(Connection con) {
        boolean isClosed = false;
        try {
            if(con == null || con.isClosed()) {
                isClosed = true;
            }
        }catch(SQLException slqe) {
            slqe.printStackTrace();
        }
        return isClosed;
    }
}