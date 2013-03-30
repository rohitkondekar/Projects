/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tease.test;

/**
 *
 * @author nishi11
 */
import tease.bean.Assignment;
import tease.dao.AssignmentDAO;
import java.text.SimpleDateFormat;
import java.text.ParseException;
//import tease.helper.DBConnection;
import java.util.*;
/**
 * Displays the assignment of teachers to student group.
 */
public class TestAssignmentDAO {
    //declaring class variables   
    AssignmentDAO dao = new AssignmentDAO();
    Assignment assignment = new Assignment();
    Scanner scan = new Scanner(System.in);
    public TestAssignmentDAO() {
    }
    /** Test whether inserts a row into assignment table of the database.
     * 
     * @return void
     */
    public void TestInsertAssignment() {
        System.out.println("enter the parameter value to insert");
        System.out.println("teacher id:");
        int i = scan.nextInt();
        assignment.setIdTeacher(i);
        System.out.println("group id:");
        int groupid = scan.nextInt();
        assignment.setIdGroup(groupid);
        System.out.println("topic id:");
        int topicid = scan.nextInt();
        assignment.setIdTopic(topicid);
        System.out.println("assignmentcol:");
        String assign = scan.next();
        assignment.setAssignmentcol(assign);
        System.out.println(dao.insertAssignment(assignment));
    }
    /** Test whether deletes a row from assignment table of the database.
     * 
     * @return void
     */
    public void TestDeleteAssignment() {
        System.out.println("enter the teacherid to be deleted");
        int i = scan.nextInt();
        System.out.println(dao.deleteAssignment(i));
    }
    /** Test whether updates a row from assignment table of the database.
     * 
     * @return void
     */
    public void TestUpdateAssignment() {
        System.out.println("enter the parameter value to update");
        System.out.println("teacher id:");
        int i = scan.nextInt();
        assignment.setIdTeacher(i);
        System.out.println("group id:");
        int groupid = scan.nextInt();
        assignment.setIdGroup(groupid);
        System.out.println("topic id:");
        int topicid = scan.nextInt();
        assignment.setIdTopic(topicid);
        System.out.println("assignment:");
        String assign = scan.next();
        assignment.setAssignmentcol(assign);
        System.out.println(dao.updateAssignment(assignment));
    }
    /** Test whether assignment table has next row or not.
     * 
     * @return void
     */
    public void TestHasNext() {
        System.out.println(dao.hasNext());
    }
    /** Test whether shows all the rows one by one from assignment table.
     * 
     * @return void
     */
    public void TestGetAssignment() {
        for(assignment =dao.getAssignment();assignment!=null;) {
        System.out.println(assignment.getIdTeacher());
        System.out.println(assignment.getIdGroup());
        System.out.println(assignment.getIdTopic());
        System.out.println(assignment.getAssignmentcol());
        assignment = dao.getAssignment();
        }
    }
    /** Test whether shows the details of assignment based on teacher id.
     * 
     * @return void
     */
    public void TestGetAssignmentByIdTeacher() {
        System.out.println("enter the teacherid for detail of assignment");
        int i = scan.nextInt();
        assignment = dao.getAssignmentByIdTeacher(i);
        System.out.println(assignment.getIdGroup());
        System.out.println(assignment.getIdTopic());
        System.out.println(assignment.getAssignmentcol());
    }
    /** Test whether shows the assignment details based on the parameter passed.
     * 
     * @return void
     */
    public void TestGetAssignmentByParameter() {
        Assignment[] assignment = null;
        System.out.println("enter the value for parameters");
        System.out.println("teacherid:");
        Integer teacherid = new Integer(scan.nextInt());
        System.out.println("\ngroup id:");
        Integer groupid = new Integer(scan.nextInt());
        System.out.println("\ntopic id:");
        Integer topicid = new Integer(scan.nextInt());
        System.out.println("\nassignmentcol:");
        String assign = scan.next();
        assignment = dao.getAssignmentByParameter(teacherid,groupid,topicid,assign);
        if(assignment !=null){
        int size = assignment.length;
           for(int i=0; i<size;i++) {
           System.out.println(assignment[i].getIdTeacher());
           System.out.println(assignment[i].getIdGroup());
           System.out.println(assignment[i].getIdTopic());
           System.out.println(assignment[i].getAssignmentcol());
           }
        }else
            System.out.println("no record found");
    }
    /** test the functions for assignment
     * 
     * @param args 
     */

    public static void main(String[] args) {
        TestAssignmentDAO test = new TestAssignmentDAO();
        System.out.println("enter 1.insert 2.delete 3.update 4.hasnext 5.getassignment 6.getassignment by id teacher 7.get assignment by parameter\n");
        System.out.println("enter ur choice to test method");
        Scanner scan = new Scanner(System.in);
        int i= scan.nextInt();
        switch(i) {
            case 1:test.TestInsertAssignment();
            break;
            case 2:test.TestDeleteAssignment();
            break;
            case 3:test.TestUpdateAssignment();
            break;
            case 4:test.TestHasNext();
            break;
            case 5:test.TestGetAssignment();
                break;
            case 6:test.TestGetAssignmentByIdTeacher();
                break;
            case 7:test.TestGetAssignmentByParameter();
                break;
            default: System.out.println("exit");
        }
    }
}