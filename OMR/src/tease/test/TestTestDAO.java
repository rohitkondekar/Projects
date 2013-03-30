package tease.test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anusha11
 */
import tease.bean.Test;
import tease.dao.TestDAO;
import java.util.*;
/**
 * Details of the assessment test to be attempted by students.
 */
public class TestTestDAO {
    //declaring class variables
    TestDAO dao = new TestDAO();
    Test test = new Test();
    Scanner scan = new Scanner(System.in);
    
    public TestTestDAO() {
    }
    /**
     * tests whether a row inserted into Test table of the database.
     *
     * @return void.
     */
    public void TestInsertTest() {
        System.out.println(dao.insertTest(test));
    }
    /**
     * tests whether a row deleted into Test table of the database.
     *
     * @return  void.
     */
    public void TestDeleteTest() {
        System.out.println("enter the testid to be deleted");
        int i = scan.nextInt();
        System.out.println(dao.deleteTest(i));
    }
    /**
     * tests whether a row deleted into Test table of the database.
     *
     * @return  void.
     */
    public void TestUpdateTest() {
        System.out.println("enter the parameter value to update");
        System.out.println("test id:");
        int testid = scan.nextInt();
        test.setIdTest(testid);
        System.out.println("topic id:");
        int studid = scan.nextInt();
        test.setIdTopic(studid);
        System.out.println("marks:");
        float mks = scan.nextFloat();
        test.setMarks(mks);
        System.out.println("\nduration:");
        int duration = scan.nextInt();
        test.setDuration(duration);
        System.out.println("display Name:");
        String displayName = scan.next();
        test.setDisplayName(displayName);
        System.out.println("fullDescription:");
        String fullDescription = scan.next();
        test.setFullDescription(fullDescription);

        System.out.println(dao.updateTest(test));
    }
    /**
     * Checks whether it has next row.
     *
     * @return  void
     */
    public void TestHasNext() {
        System.out.println(dao.hasNext());
    }
    /**
     * tests whether it shows all details of the test table.
     *
     * @return  void.
     */
    public void TestGetTest() {
        test = dao.getTest();
        for(test=dao.getTest();test!=null;) {
            System.out.println(test.getIdTest());
            System.out.println(test.getIdTopic());
            System.out.println(test.getMarks());
            System.out.println(test.getDuration());
            System.out.println(test.getDisplayName());
            System.out.println(test.getFullDescription());
            test = dao.getTest();

        }

    }
    /**
     * tests whether it gives the details of the test based on the test id.
     *
     * @return  void.
     */
    public void TestgetTestByIdTest() {
        System.out.println("enter the testid for detail of testanswers");
        int i = scan.nextInt();
        test = dao.getTestByIdTest(i);
        System.out.println(test.getIdTopic());
        System.out.println(test.getMarks());
        System.out.println(test.getDuration());
        System.out.println(test.getDisplayName());
        System.out.println(test.getFullDescription());

    }
    /**
     * Gives the details of the test based on the parameter passed.
     *
     * @return  void.
     */
    public void TestGetTestByParameter() {
        Test[] test = null;
        System.out.println("enter the value for parameters");
        System.out.println("testid:");
        Integer testid = new Integer(scan.nextInt());
        System.out.println("\ntopic id:");
        Integer studid = new Integer(scan.nextInt());
        System.out.println("\nmarks:");
        Float mks = new Float(scan.nextFloat());
        System.out.println("\nduration:");
        Integer duration = new Integer(scan.nextInt());
        System.out.println("\ndisplayName:");
        String displayName = scan.next();
        System.out.println("\nfullDescription:");
        String fullDescription = scan.next();

        test= dao.getTestByParameter(testid, studid, mks, duration, displayName, fullDescription);
        if(test == null)
                System.out.println("not found the parameter");
        else
        {
        int size = test.length;
           for(int i=0; i<size;i++) {
               System.out.println(test[i].getIdTest());
               System.out.println(test[i].getIdTopic());
               System.out.println(test[i].getMarks());
               System.out.println(test[i].getDuration());
               System.out.println(test[i].getDisplayName());
               System.out.println(test[i].getFullDescription());

           }
        }
    }
    /**
     * tests the function for Test.
     * @param args
     */
    public static void main(String args[]) {
        TestTestDAO test= new TestTestDAO();
        System.out.println("enter ur choice to test method");
        Scanner scan = new Scanner(System.in);
        int i= scan.nextInt();
        switch(i) {
            case 1:test.TestInsertTest();
            break;
            case 2:test.TestDeleteTest();
            break;
            case 3:test.TestUpdateTest();
            break;
            case 4:test.TestHasNext();
            break;
            case 5:test.TestGetTest();
            break;
            case 6:test.TestgetTestByIdTest();
            break;
            case 7:test.TestGetTestByParameter();
            break;
            default: System.out.println("exit");
        }
    }

}
