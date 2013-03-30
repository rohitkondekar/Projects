package tease.test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anusha11
 */
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import tease.bean.TestSchedule;
import tease.dao.TestScheduleDAO;
import java.util.*;
import jxl.write.DateTime;
/**
 * Schedule of test to be given to student groups.
 */
public class TestTestScheduleDAO {
    //declaring class variables
    TestScheduleDAO dao = new TestScheduleDAO();
    TestSchedule testSchedule = new TestSchedule();
    Scanner scan = new Scanner(System.in);
    
    public TestTestScheduleDAO() {
    }
    /**
     * tests whether a row inserted into TestSchedule table of the database.
     *
     * @return void.
     */
    public void TestInsertTestSchedule() {
        System.out.println(dao.insertTestSchedule(testSchedule));
    }
    /**
     * tests whether a row deleted into TestSchedule table of the database.
     *
     * @return void.
     */
    public void TestDeleteTestSchedule() {
        System.out.println("enter the testid to be deleted");
        int i = scan.nextInt();
        System.out.println(dao.deleteTestSchedule(i));
    }
    /**
     * tests whether a row updated into TestSchedule table of the database.
     *
     * @return void.
     */
    public void TestUpdateTestSchedule() {
        System.out.println("enter the parameter value to update");
        System.out.println("test id:");
        int i = scan.nextInt();
        testSchedule.setIdTest(i);
        System.out.println("group id:");
        int j = scan.nextInt();
        testSchedule.setIdGroup(j);
        System.out.println("\nlocation:");
        String location = scan.next();
        testSchedule.setLocation(location);
        System.out.println("\nstartTime:");
        //Calendar myCalendar = new Calendar();
        //Date mydate = Calendar.set(2000,2,2,12,23,34);
        //java.sql.Date sqldate = new java.sql.Date(new java.util.Date().getTime());
        //Timestamp startTime = scan.next();
        //String dateFormat = "yyyy-MM-dd HH:mm:ss:SSS";
        //DateFormat df = new SimpleDateFormat(dateFormat);

        java.util.Date date = new java.util.Date();
        long t = date.getTime();
        java.sql.Timestamp startTime = new java.sql.Timestamp(t);
        testSchedule.setStartTime(startTime);
        System.out.println("\nendTime:");
        java.sql.Timestamp endTime = new java.sql.Timestamp(t);
        testSchedule.setStartTime(startTime);
        //Timestamp endTime = scan.next();
        testSchedule.setEndTime(endTime);

        System.out.println(dao.updateTestSchedule(testSchedule));
    }
    /**
     * test whether it has next row.
     *
     * @return void.
     */
    public void TestHasNext() {
        System.out.println(dao.hasNext());
    }
    /**
     * test whether it shows all details of the TestSchedule table.
     *
     * @return void.
     */
    public void TestGetTestSchedule() {
        for(testSchedule=dao.getTestSchedule();testSchedule!=null;) {
            System.out.println(testSchedule.getIdTest());
            System.out.println(testSchedule.getIdGroup());
            System.out.println(testSchedule.getLocation());
            System.out.println(testSchedule.getStartTime());
            System.out.println(testSchedule.getEndTime());
            testSchedule = dao.getTestSchedule();

        }

    }
    /**
     * test whether it shows the details of the TestSchedule based on the test id.
     *
     * @return void.
     */
    public void TestgetTestScheduleByIdTest() {
        System.out.println("enter the testid for detail of testSchedule");
        int i = scan.nextInt();
        testSchedule = dao.getTestScheduleByIdTest(i);
        System.out.println(testSchedule.getIdGroup());
        System.out.println(testSchedule.getLocation());
        System.out.println(testSchedule.getStartTime());
        System.out.println(testSchedule.getEndTime());

    }
    /**
     * test whether it shows the details of the TestSchedule based on the parameter passed.
     *
     * @return  void.
     */
    public void TestGetTestScheduleByParameter() {
        TestSchedule[] testSchedule = null;
        System.out.println("enter the value for parameters");
        System.out.println("testid:");
        Integer id = new Integer(scan.nextInt());
        System.out.println("\ngroup id:");
        Integer id2 = new Integer(scan.nextInt());
        System.out.println("\nlocation:");
        String location = scan.next();
        System.out.println("\nstartTime:");
        String startTime = scan.next();
        System.out.println("\nendTime:");
        String endTime = scan.next();


        testSchedule = dao.getTestScheduleByParameter(id, id2, location, startTime, endTime);
        if(testSchedule == null)
                System.out.println("not found the parameter");
        else
        {
           int size = testSchedule.length;
           for(int i=0; i<size;i++) {
           System.out.println(testSchedule[i].getIdTest());
           System.out.println(testSchedule[i].getIdGroup());
           System.out.println(testSchedule[i].getLocation());
           System.out.println(testSchedule[i].getStartTime());
           System.out.println(testSchedule[i].getEndTime());

           }
        }
    }
    /**
     * test  the functions for TestSchedule.
     * @param args
     */
    public static void main(String args[]) {
        TestTestScheduleDAO test= new TestTestScheduleDAO();
        System.out.println("enter ur choice to test method");
        Scanner scan = new Scanner(System.in);
        int i= scan.nextInt();
        switch(i) {
            case 1:test.TestInsertTestSchedule();
            break;
            case 2:test.TestDeleteTestSchedule();
            break;
            case 3:test.TestUpdateTestSchedule();
            break;
            case 4:test.TestHasNext();
            break;
            case 5:test.TestGetTestSchedule();
            break;
            case 6:test.TestgetTestScheduleByIdTest();
            break;
            case 7:test.TestGetTestScheduleByParameter();
            break;
            default: System.out.println("exit");
        }

    }

}


