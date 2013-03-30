package tease.test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anusha11
 */
import tease.bean.TestAnswers;
import tease.dao.TestAnswersDAO;
import java.util.*;
/**
 * Answers given by the students.
 */
public class TestTestAnswersDAO {
    //declaring class variables
    TestAnswersDAO dao = new TestAnswersDAO();
    TestAnswers testAnswers = new TestAnswers();
    Scanner scan = new Scanner(System.in);
    
    public TestTestAnswersDAO() {
    }
    /**
     * test whether a row inserted into TestAnswers table of the database.
     *
     * @return void.
     */
    public void TestInsertTestAnswers() {
        System.out.println(dao.insertTestAnswers(testAnswers));
    }
    /**
     * test whether a row deleted into TestAnswers table of the database.
     *
     * @return void.
     */
    public void TestDeleteTestAnswers() {
        System.out.println("enter the testid to be deleted");
        int i = scan.nextInt();
        System.out.println(dao.deleteTestAnswers(i));
    }
    /**
     * test whether a row updated into TestAnswers table of the database.
     *
     * @return void.
     */
    public void TestUpdateTestAnswers() {
        String s;
        System.out.println("enter the parameter value to update");
        System.out.println("test id:");
        s = scan.next();
        if(s.equals("null")== false)
            testAnswers.setIdTest(Integer.parseInt(s));
        System.out.println("student id:");
        int j = scan.nextInt();
        testAnswers.setIdStudent(j);
        System.out.println("answers:");
        String answers = scan.next();
        testAnswers.setAnswers(answers);
        System.out.println("correct:");
        int correct = scan.nextInt();
        testAnswers.setCorrect(correct);
        System.out.println("inCorrect:");
        int inCorrect = scan.nextInt();
        testAnswers.setInCorrect(inCorrect);
        System.out.println("marks:");
        float mks = scan.nextFloat();
        testAnswers.setMarks(mks);
        System.out.println(dao.updateTestAnswers(testAnswers));
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
     * tests whether it gives all details of the TestAnswers table.
     *
     * @return void.
     */
    public void TestGetTestAnswers() {
        for(testAnswers=dao.getTestAnswers();testAnswers!=null;) {
            System.out.println(testAnswers.getIdTest());
            System.out.println(testAnswers.getIdStudent());
            System.out.println(testAnswers.getAnswers());
            System.out.println(testAnswers.getCorrect());
            System.out.println(testAnswers.getInCorrect());
            System.out.println(testAnswers.getMarks());
            testAnswers = dao.getTestAnswers();

        }
        
    }
    /**
     * tests whether it gives details of TestAnswers based on the test id.
     *
     * @return  void.
     */
    public void TestgetTestAnswersByIdTest() {
        System.out.println("enter the testid for detail of testanswers");
        int i = scan.nextInt();
        testAnswers = dao.getTestAnswersByIdTest(i);
        System.out.println(testAnswers.getIdStudent());
        System.out.println(testAnswers.getAnswers());
        System.out.println(testAnswers.getCorrect());
        System.out.println(testAnswers.getInCorrect());
        System.out.println(testAnswers.getMarks());
        
    }
    /**
     * tests whether it gives details of the TestAnswers based on the parameter passed.
     *
     * @return void.
     */
    public void TestGetTestAnswersByParameter() {
        TestAnswers[] testAnswers = null;
        System.out.println("enter the value for parameters");
        System.out.println("testid:");
        Integer testid = new Integer(scan.nextInt());
        System.out.println("\nstudent id:");
        Integer studid = new Integer(scan.nextInt());
        System.out.println("\nanswers:");
        String answers = scan.next();
        System.out.println("correct:");
        Integer correct = new Integer(scan.nextInt());
        System.out.println("inCorrect:");
        Integer inCorrect = new Integer(scan.nextInt());
        System.out.println("marks:");
        float mks = scan.nextFloat();
        
        testAnswers = dao.getTestAnswersByParameter(testid, studid, answers, correct, inCorrect, mks);
        if(testAnswers == null)
                System.out.println("not found the parameter");
        else
         {
        int size = testAnswers.length;
        for(int i=0; i<size;i++) {
           System.out.println(testAnswers[i].getIdTest());
           System.out.println(testAnswers[i].getIdStudent());
           System.out.println(testAnswers[i].getAnswers());
           System.out.println(testAnswers[i].getCorrect());
           System.out.println(testAnswers[i].getInCorrect());
           System.out.println(testAnswers[i].getMarks());
        }
        }
     }
    /**
     * test the functions for TestAnswers.
     * @param args
     */
    public static void main(String args[]) {
        TestTestAnswersDAO test= new TestTestAnswersDAO();
        System.out.println("enter ur choice to test method");
        Scanner scan = new Scanner(System.in);
        int i= scan.nextInt();
        switch(i) {
            case 1:test.TestInsertTestAnswers();
            break;
            case 2:test.TestDeleteTestAnswers();
            break;
            case 3:test.TestUpdateTestAnswers();
            break;
            case 4:test.TestHasNext();
            break;
            case 5:test.TestGetTestAnswers();
            break;
            case 6:test.TestgetTestAnswersByIdTest();
            break;
            case 7:test.TestGetTestAnswersByParameter();
            break;
            default: System.out.println("exit");
        }

    }

}
