/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tease.test;

/**
 *
 * @author nishi11
 */
import tease.bean.Enrollment;
import tease.dao.EnrollmentDAO;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.util.*;

/**
 * Enrollment of students into groups.
 */
public class TestEnrollmentDAO {

    EnrollmentDAO dao = new EnrollmentDAO();
    Enrollment enrollment = new Enrollment();
    Scanner scan = new Scanner(System.in);
    public TestEnrollmentDAO() {
    }
    /** Test whether inserts a row into enrollment table of the database.
     * 
     * @return void
     */
    public void TestInsertEnrollment() {
        System.out.println("enter the parameter value to insert");
        System.out.println("enrollment id:");
        String idenroll = scan.next();
        enrollment.setIdEnrollment(idenroll);
        System.out.println("student id:");
        int idstudent = scan.nextInt();
        enrollment.setIdStudent(idstudent);
        System.out.println("group id:");
        int idgroup = scan.nextInt();
        enrollment.setIdGroup(idgroup);
        System.out.println(dao.insertEnrollment(enrollment));
    }
    /** Test whether deletes a row from enrollment table of the database.
     * 
     * @return void
     */
    public void TestDeleteEnrollment() {
        System.out.println("enter the enrollmentid to be deleted");
        String idenroll = scan.next();
        System.out.println(dao.deleteEnrollment(idenroll));
    }
    /** Test whether updates a row from enrollment table of the database.
     * 
     * @return void
     */
    public void TestUpdateEnrollment() {
        System.out.println("enter the parameter value to update");
        System.out.println("enrollment id:");
        String idenroll = scan.next();
        enrollment.setIdEnrollment(idenroll);
        System.out.println("student id:");
        int idstudent = scan.nextInt();
        enrollment.setIdStudent(idstudent);
        System.out.println("group id:");
        int idgroup = scan.nextInt();
        enrollment.setIdGroup(idgroup);
        System.out.println(dao.updateEnrollment(enrollment));
    }
    /** Test whether enrollment table has next row or not.
     * 
     * @return void
     */
    public void TestHasNext() {
        System.out.println(dao.hasNext());
    }
    /** Test whether shows all the rows one by one from enrollment table.
     * 
     * @return void
     */
    public void TestGetEnrollment() {
        for(enrollment =dao.getEnrollment();enrollment!=null;) {
        System.out.println(enrollment.getIdEnrollment());
        System.out.println(enrollment.getIdStudent());
        System.out.println(enrollment.getIdGroup());
        enrollment = dao.getEnrollment();
        }
    }
    /** Test whether shows total number of students in a particular group.
     * 
     * @return void
     */
    public void TestnoOfStudents() {
        System.out.println("enter the group id");
        int groupid = scan.nextInt();
        int no = dao.noOfStudents(groupid);
        System.out.println(no);
    }
    /** Test whether shows the details of enrollment based on enrollment id.
     * 
     * @return void
     */
    public void TestGetEnrollmentByIdEnrollment() {
        System.out.println("enter the enrollment id for detail of enrollment");
        String  idenroll = scan.next();
        enrollment = dao.getEnrollmentByIdEnrollment(idenroll);
        System.out.println(enrollment.getIdStudent());
        System.out.println(enrollment.getIdGroup());
    }
    /** Test whether shows the enrollment details based on the parameter passed.
     * 
     * @return void
     */
    public void TestGetEnrollmentByParameter() {
        Enrollment[] enrollment = null;
        System.out.println("enter the value for parameters");
        System.out.println("\nenrollmentid:");
        String idenroll = scan.next();
        System.out.println("\nstudentid:");
        Integer idstudent = new Integer(scan.nextInt());
        System.out.println("\ngroupid:");
        Integer idgroup = new Integer(scan.nextInt());
        enrollment = dao.getEnrollmentByParameter(idenroll,idstudent,idgroup);
        if(enrollment != null) {
        int size = enrollment.length;
           for(int i=0; i<size;i++) {
           System.out.println(enrollment[i].getIdEnrollment());
           System.out.println(enrollment[i].getIdStudent());
           System.out.println(enrollment[i].getIdGroup());
           }
        }else
            System.out.println("no record found");
    }
    /** test the functions for enrollment
     * 
     * @param args 
     */   
    public static void main(String[] args) {
        TestEnrollmentDAO test = new TestEnrollmentDAO();
        System.out.println("enter 1.insert 2.delete 3.update 4.hasnext 5.getenrollment 6.getenrollment by id enrollment 7.get enrollment by parameter\n");
        System.out.println("enter ur choice to test method");
        Scanner scan = new Scanner(System.in);
        int i= scan.nextInt();
        switch(i) {
            case 1:test.TestInsertEnrollment();
            break;
            case 2:test.TestDeleteEnrollment();
            break;
            case 3:test.TestUpdateEnrollment();
            break;
            case 4:test.TestHasNext();
            break; 
            case 5:test.TestGetEnrollment();
                break;
            case 6:test.TestGetEnrollmentByIdEnrollment();
                break;
            case 7:test.TestGetEnrollmentByParameter();
                break;
            case 8:test.TestnoOfStudents();
                break;
            default: System.out.println("exit");
        }
    }

}