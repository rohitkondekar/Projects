/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tease.test;

/**
 *
 * @author administrator
 */
import tease.bean.Teacher;
import tease.dao.TeacherDAO;
import java.text.SimpleDateFormat;
import java.text.ParseException;
//import tease.helper.DBConnection;
import java.util.*;

public class TestTeacherDAO {

    TeacherDAO dao = new TeacherDAO();
    Teacher teacher = new Teacher();
    Scanner scan = new Scanner(System.in);
    public TestTeacherDAO() {
    }
    public void TestInsertTeacher() {
        System.out.println("enter the parameter value to insert");
        System.out.println("first name:");
        String first = scan.next();
        teacher.setFirstName(first);
        System.out.println("last name:");
        String last = scan.next();
        teacher.setLastName(last);
        System.out.println("designation:");
        String desg = scan.next();
        teacher.setDesignation(desg);
        System.out.println("qualification:");
        String qual = scan.next();
        teacher.setQualification(qual);
        System.out.println(dao.insertTeacher(teacher));
    }
    public void TestDeleteTeacher() {
        System.out.println("enter the teacherid to be deleted");
        int i = scan.nextInt();
        System.out.println(dao.deleteTeacher(i));
    }
    public void TestUpdateTeacher() {
        System.out.println("enter the parameter value to update");
        System.out.println("teacher id:");
        int i = scan.nextInt();
        teacher.setIdTeacher(i);
        System.out.println("first name:");
        String first = scan.next();
        teacher.setFirstName(first);
        System.out.println("last name:");
        String last = scan.next();
        teacher.setLastName(last);
        System.out.println("designation:");
        String desg = scan.next();
        teacher.setDesignation(desg);
        System.out.println("qualification:");
        String qual = scan.next();
        teacher.setQualification(qual);
        System.out.println(dao.updateTeacher(teacher));
    }
    public void TestHasNext() {
        System.out.println(dao.hasNext());
    }
    public void TestGetTeacher() {
        for(teacher =dao.getTeacher();teacher!=null;) {
        System.out.println(teacher.getIdTeacher());
        System.out.println(teacher.getFirstName());
        System.out.println(teacher.getLastName());
        System.out.println(teacher.getDesignation());
        System.out.println(teacher.getQualification());
        teacher = dao.getTeacher();
        }
    }
    public void TestGetTeacherByIdTeacher() {
        System.out.println("enter the teacherid for detail of teacher");
        int i = scan.nextInt();
        teacher = dao.getTeacherByIdTeacher(i);
        System.out.println(teacher.getFirstName());
        System.out.println(teacher.getLastName());
        System.out.println(teacher.getDesignation());
        System.out.println(teacher.getQualification());
    }
    public void TestGetTeacherByParameter() {
        Teacher[] teacher = null;
        System.out.println("enter the value for parameters");
        System.out.println("teacherid:");
        int id = scan.nextInt();
        System.out.println("\nfirst name:");
        String first = scan.next();
        System.out.println("\nlast name:");
        String last = scan.next();
        System.out.println("\ndesignation:");
        String desg = scan.next();
        System.out.println("\n qualification:");
        String qual = scan.next();

        teacher = dao.getTeacherByParameter(id,first,last,desg,qual);
        if(teacher != null) {
        int size = teacher.length;
           for(int i=0; i<size;i++) {
           System.out.println(teacher[i].getIdTeacher());
           System.out.println(teacher[i].getFirstName());
           System.out.println(teacher[i].getLastName());
           System.out.println(teacher[i].getDesignation());
           System.out.println(teacher[i].getQualification());
           }
        }else
            System.out.println("no record found");
    }

    public static void main(String[] args) {
        TestTeacherDAO test = new TestTeacherDAO();
        System.out.println("enter 1.insert 2.delete 3.update 4.hasnext 5.get teacher 6.getteacher by id teacher 7.get teacher by parameter\n");
        System.out.println("enter ur choice to test method");
        Scanner scan = new Scanner(System.in);
        int i= scan.nextInt();
        switch(i) {
            case 1:test.TestInsertTeacher();
            break;
            case 2:test.TestDeleteTeacher();
            break;
            case 3:test.TestUpdateTeacher();
            break;
            case 4:test.TestHasNext();
            break;
            case 5:test.TestGetTeacher();
                break;
            case 6:test.TestGetTeacherByIdTeacher();
                break;
            case 7:test.TestGetTeacherByParameter();
                break;
            default: System.out.println("exit");
        }
    }
}