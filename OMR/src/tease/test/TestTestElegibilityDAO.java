package tease.test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anusha11
 */
import tease.bean.TestElegibility;
import tease.dao.TestElegibilityDAO;
import java.util.*;
/**
 *  The groups/subgroups to whom the test needs to be delivered.
 */
public class TestTestElegibilityDAO {
    //declaring class variables
    TestElegibilityDAO dao = new TestElegibilityDAO();
    TestElegibility testElegibility = new TestElegibility();
    Scanner scan = new Scanner(System.in);
    
    public TestTestElegibilityDAO() {
    }
    /**
     * tests whether a row inserted into TestElegibility table of the database.
     *
     * @return  void.
     */
    public void TestInsertTestElegibility() {
        System.out.println(dao.insertTestElegibility(testElegibility));
    }
    /**
     * tests whether a row deleted in TestElegibility table of the database.
     *
     * @return  void.
     */
    public void TestDeleteTestElegibility() {
        System.out.println("enter the testid to be deleted");
        int i = scan.nextInt();
        System.out.println(dao.deleteTestElegibility(i));
    }
    /**
     * tests whether a row updated in TestElegibility table of the database.
     *
     * @return void.
     */
    public void TestUpdateTestElegibility() {
        System.out.println("enter the parameter value to update");
        System.out.println("test id:");
        int testid = scan.nextInt();
        testElegibility.setIdTest(testid);
        System.out.println("group id:");
        int groupid = scan.nextInt();
        testElegibility.setIdGroup(groupid);
        System.out.println("status:");
        int status = scan.nextInt();
        testElegibility.setStatus(status);

        System.out.println(dao.updateTestElegibility(testElegibility));
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
     * test whether it shows all details of the TestElegibility table.
     *
     * @return void.
     */
    public void TestGetTestElegibility() {
        for(testElegibility=dao.getTestElegibility();testElegibility!=null;) {
               System.out.println(testElegibility.getIdTest());
               System.out.println(testElegibility.getIdGroup());
               System.out.println(testElegibility.getStatus());
               testElegibility = dao.getTestElegibility();

        }

    }
    /**
     * test whether it gives the details of TestElegibility  table based on the test id.
     *
     * @return void.
     */
    public void TestgetTestElegibilityByIdTest() {
        System.out.println("enter the testid for detail of testElegibility");
        int i = scan.nextInt();
        testElegibility = dao.getTestElegibilityByIdTest(i);
        System.out.println(testElegibility.getIdGroup());
        System.out.println(testElegibility.getStatus());

    }
    /**
     * tests whether it gives the details of TestElegibility based on the parameter passed.
     *
     * @return void.
     */
    public void TestGetTestElegibilityByParameter() {
        TestElegibility[] testElegibility = null;
        System.out.println("enter the value for parameters");
        System.out.println("testid:");
        Integer testid = new Integer(scan.nextInt());
        System.out.println("\ngroup id:");
        Integer groupid = new Integer(scan.nextInt());
        System.out.println("\nstatus:");
        Integer status = new Integer(scan.nextInt());


        testElegibility = dao.getTestElegibilityByParameter(testid, groupid, status);
        if(testElegibility == null)
                System.out.println("not found the parameter");
        else
        {

           int size = testElegibility.length;
           for(int i=0; i<size;i++) {
           System.out.println(testElegibility[i].getIdTest());
           System.out.println(testElegibility[i].getIdGroup());
           System.out.println(testElegibility[i].getStatus());
           
           }
        }
    }
    /**
     * test the functions for TestElegibility.
     * @param args
     */
    public static void main(String args[]) {
        TestTestElegibilityDAO test= new TestTestElegibilityDAO();
        System.out.println("enter ur choice to test method");
        Scanner scan = new Scanner(System.in);
        int i= scan.nextInt();
        switch(i) {
            case 1:test.TestInsertTestElegibility();
            break;
            case 2:test.TestDeleteTestElegibility();
            break;
            case 3:test.TestUpdateTestElegibility();
            break;
            case 4:test.TestHasNext();
            break;
            case 5:test.TestGetTestElegibility();
            break;
            case 6:test.TestgetTestElegibilityByIdTest();
            break;
            case 7:test.TestGetTestElegibilityByParameter();
            break;
            default: System.out.println("exit");
        }

    }

}

