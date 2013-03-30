package tease.test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anusha11
 */
import tease.bean.TestDetails;
import tease.dao.TestDetailsDAO;
import java.util.*;
/**
 *  Details (description of questions) of the test to be given.
 */
public class TestTestDetailsDAO {
    //declaring class variables
    TestDetailsDAO dao = new TestDetailsDAO();
    TestDetails testDetails = new TestDetails();
    Scanner scan = new Scanner(System.in);
    
    public TestTestDetailsDAO() {
    }
    /**
     * tests whether a row inserted into TestDetails table of the database.
     *
     * @return  void.
     */
    public void TestInsertTestDetails() {
        System.out.println(dao.insertTestDetails(testDetails));
    }
    /**
     * tests whether a row deleted into TestDetails table of the database.
     *
     * @return  void.
     */
    public void TestDeleteTestDetails() {
        System.out.println("enter the testid to be deleted");
        int i = scan.nextInt();
        System.out.println(dao.deleteTestDetails(i));
    }
    /**
     * tests whether a row updated into TestDetails table of the database.
     *
     * @return void.
     */
    public void TestUpdateTestDetails() {
        System.out.println("enter the parameter value to update");
        System.out.println("test id:");
        int testid = scan.nextInt();
        testDetails.setIdTest(testid);
        System.out.println("sequence:");
        int seq = scan.nextInt();
        testDetails.setSequence(seq);
        System.out.println("questionType:");
        int type = scan.nextInt();
        testDetails.setQuestionType(type);
        System.out.println("questionOptions:");
        int options = scan.nextInt();
        testDetails.setQuestionOptions(options);
        System.out.println("\ncorrectAnswer:");
        String correct = scan.next();
        testDetails.setCorrectAnswer(correct);
        System.out.println("\nmarks:");
        float mks = scan.nextFloat();
        testDetails.setMarks(mks);
        System.out.println("\nnegativeMarks:");
        float ngt = scan.nextFloat();
        testDetails.setNegativeMarks(ngt);


        System.out.println(dao.updateTestDetails(testDetails));
    }
    /**
     * tests whether it has next row.
     *
     * @return void.
     */
    public void TestHasNext() {
        System.out.println(dao.hasNext());
    }
    /**
     * tests whether it shows all the detils of test details table.
     *
     * @return  void.
     */
    public void TestGetTestDetails() {
        for(testDetails=dao.getTestDetails();testDetails!=null;) {
               System.out.println(testDetails.getIdTest());
               System.out.println(testDetails.getSequence());
               System.out.println(testDetails.getQuestionType());
               System.out.println(testDetails.getCorrectAnswer());
               System.out.println(testDetails.getQuestionOptions());
               System.out.println(testDetails.getMarks());
               System.out.println(testDetails.getNegativeMarks());
               testDetails = dao.getTestDetails();

        }

    }
    /**
     * test whether it gives the test details based on the test id.
     */
    public void TestgetTestDetailsByIdTest() {
        System.out.println("enter the testid for  testDetails");
        int i = scan.nextInt();
        testDetails = dao.getTestDetailsByidTest(i);
        System.out.println(testDetails.getSequence());
        System.out.println(testDetails.getQuestionType());
        System.out.println(testDetails.getCorrectAnswer());
        System.out.println(testDetails.getQuestionOptions());
        System.out.println(testDetails.getMarks());
        System.out.println(testDetails.getNegativeMarks());

    }
    /**
     * test whether it gives the test details based on the parameter passed.
     */
    public void TestGetTestDetailsByParameter() {
        TestDetails[] testDetails = null;
        System.out.println("enter the value for parameters");
        System.out.println("test id:");
        Integer testid = new Integer(scan.nextInt());
        System.out.println("sequence:");
        Integer seq = new Integer(scan.nextInt());
        System.out.println("questionType:");
        Integer type = new Integer(scan.nextInt());
        System.out.println("questionOptions:");
        Integer options = new Integer(scan.nextInt());
        System.out.println("\ncorrectAnswer:");
        String correctAnswer = scan.next();
        System.out.println("\nmarks:");
        Float mks = new Float(scan.nextFloat());
        System.out.println("\nnegativeMarks:");
        Float ngt = new Float(scan.nextFloat());

        testDetails = dao.getTestDetailsByParameter(testid, seq, type, options,correctAnswer,mks,ngt);
        if(testDetails == null)
               System.out.println("not found the parameter");
        else
         {
           int size = testDetails.length;
           for(int i=0; i<size;i++) {
                System.out.println(testDetails[i].getIdTest());
                System.out.println(testDetails[i].getSequence());
                System.out.println(testDetails[i].getQuestionType());
                System.out.println(testDetails[i].getQuestionOptions());
                System.out.println(testDetails[i].getCorrectAnswer());
                System.out.println(testDetails[i].getMarks());
                System.out.println(testDetails[i].getNegativeMarks());
           }
         }
    }
    /**
     * test the functions for TestDetails.
     * 
     * @param args
     */
    public static void main(String args[]) {
        TestTestDetailsDAO test= new TestTestDetailsDAO();
         System.out.println("enter ur choice to test method");
        Scanner scan = new Scanner(System.in);
        int i= scan.nextInt();
        switch(i) {
            case 1:test.TestInsertTestDetails();
            break;
            case 2:test.TestDeleteTestDetails();
            break;
            case 3:test.TestUpdateTestDetails();
            break;
            case 4:test.TestHasNext();
            break;
            case 5:test.TestGetTestDetails();
            break;
            case 6:test.TestgetTestDetailsByIdTest();
            break;
            case 7:test.TestGetTestDetailsByParameter();
            break;
            default: System.out.println("exit");
        }

    }

}
