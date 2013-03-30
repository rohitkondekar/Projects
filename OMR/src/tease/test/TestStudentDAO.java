/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tease.test;

/**
 *
 * @author administrator
 */
import java.text.DateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import tease.bean.Student;
import tease.dao.StudentDAO;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
//import tease.helper.DBConnection;
import java.util.*;

public class TestStudentDAO {
    //declaring class variables
    StudentDAO dao = new StudentDAO();
    Student student = new Student();
    Scanner scan = new Scanner(System.in).useDelimiter("\n");
    public TestStudentDAO() {
    }
    public void TestInsertStudent() {
        System.out.println("enter the parameter value to insert");
        System.out.println("first name:");
        String first = scan.next();
        student.setFirstName(first);
        System.out.println("last name:");
        String last = scan.next();
        student.setLastName(last);
        System.out.println("roll number:");
        String roll = scan.next();
        student.setRollNumber(roll);
        System.out.println("birth date");
        String str;
        str = scan.next();
        DateFormat formatter;
        Date date=null;
        formatter = new SimpleDateFormat("yyyy-mm-dd");
        try {
            date = (Date)formatter.parse(str);
        } catch (ParseException ex) {
        }
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        student.setBirthDate(sqlDate);
        System.out.println("gender:");
        char gen = (scan.next()).charAt(0);
        student.setGender(gen);
        System.out.println("email:");
        String email = scan.next();
        student.setEmail(email);
        System.out.println("mobile:");
        Long mob = scan.nextLong();
        student.setMobile(mob);
        System.out.println(dao.insertStudent(student));
    }
    public void TestDeleteStudent() {
        System.out.println("enter the studentid to be deleted");
        int i = scan.nextInt();
        System.out.println(dao.deleteStudent(i));
    }
    public void TestUpdateStudent() {
        System.out.println("enter the parameter value to update");
        System.out.println("student id:");
        int i = scan.nextInt();
        student.setIdStudent(i);
        System.out.println("first name:");
        String first = scan.next();
        student.setFirstName(first);
        System.out.println("last name:");
        String last = scan.next();
        student.setLastName(last);
        System.out.println("roll number:");
        String roll = scan.next();
        student.setRollNumber(roll);
        System.out.println("birth date");
        String str;
        str = scan.next();
        DateFormat formatter;
        Date date=null;
        formatter = new SimpleDateFormat("yyyy-mm-dd");
        try {
            date = (Date)formatter.parse(str);
        } catch (ParseException ex) {
        }
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        student.setBirthDate(sqlDate);
        System.out.println("gender:");
        char gen = (scan.next()).charAt(0);
        student.setGender(gen);
        System.out.println("email:");
        String email = scan.next();
        student.setEmail(email);
        System.out.println("mobile:");
        Long mob = scan.nextLong();
        student.setMobile(mob);
        System.out.println(dao.updateStudent(student));
    }
    public void TestHasNext() {
        System.out.println(dao.hasNext());
    }
    public void TestGetStudent() {
        for(student=dao.getStudent();student!=null;) {
        System.out.println(student.getIdStudent());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        System.out.println(student.getRollNumber());
        System.out.println(student.getBirthDate());
        System.out.println(student.getGender());
        System.out.println(student.getEmail());
        System.out.println(student.getMobile());
        student = dao.getStudent();
        }
    }
    public void TestGetStudentByIdStudent() {
        System.out.println("enter the studentid for detail of student");
        int i = scan.nextInt();
        student = dao.getStudentByIdStudent(i);
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        System.out.println(student.getRollNumber());
        System.out.println(student.getBirthDate());
        System.out.println(student.getGender());
        System.out.println(student.getEmail());
        System.out.println(student.getMobile());
    }
    public void TestGetStudentByParameter() {
        Student[] student = null;
        System.out.println("enter the value for parameters");
        System.out.println("studentid:");
        int id = scan.nextInt();
        System.out.println("\nfirst name:");
        String first = scan.next();
        System.out.println("\nlast name:");
        String last = scan.next();
        System.out.println("\nroll number:");
        String roll = scan.next();
        //String date = scan.nextLine();
        //SimpleDateFormat sdf = new SimpleDateFormat( "MM dd yyyy" );
        //Date bDay = sdf.parse(date);
        //Date d = dateFormat.parse(scan.nextLine());
        System.out.println("birth date");
        String str;
        str = scan.next();
        DateFormat formatter;
        java.util.Date date=null;
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = (Date)formatter.parse(str);
        } catch (ParseException ex) {
        }
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        
        System.out.println("gender:");
        char gen = (scan.next()).charAt(0);
        System.out.println("\n email:");
        String email = scan.next();
        System.out.println("\n mobile number:");
        Long mob = scan.nextLong();
        student = dao.getStudentByParameter(id,first,last,roll,sqlDate,gen,email,mob);
        int size = student.length;
           for(int i=0; i<size;i++) {
           System.out.println(student[i].getIdStudent());
           System.out.println(student[i].getFirstName());
           System.out.println(student[i].getLastName());
           System.out.println(student[i].getRollNumber());
           System.out.println(student[i].getBirthDate());
           System.out.println(student[i].getGender());
           System.out.println(student[i].getEmail());
           System.out.println(student[i].getMobile());
           }
    }

    public static void main(String[] args) {
        TestStudentDAO test = new TestStudentDAO();
        System.out.println("enter 1.insert 2.delete 3.update 4.hasnext 5.getstudent 6.getstudent by id student 7.get student by parameter\n");
        System.out.println("enter ur choice to test method");
        Scanner scan = new Scanner(System.in);
        int i= scan.nextInt();
        switch(i) {
            case 1:test.TestInsertStudent();
            break;
            case 2:test.TestDeleteStudent();
            break;
            case 3:test.TestUpdateStudent();
            break;
            case 4:test.TestHasNext();
            break;
            case 5:test.TestGetStudent();
                break;
            case 6:test.TestGetStudentByIdStudent();
                break;
            case 7:test.TestGetStudentByParameter();
                break;
            default: System.out.println("exit");
        }
    }
}