/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tease.bean;

/**
 *
 * @author nishi11
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 *Answers given by the students.
 */
public class TestAnswers {
    //Declaring class variables
    private int idTest;
    private int idStudent;
    private String answers;
    private int correct;
    private int incorrect;
    private float marks;

    //setter methods to set the value for class variable
    /**
     *  Represents the Test id of the Test.
     *
     * @param idTest Unique id of the test.
     *
     * @return void.
     */
    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }
    /**
     *  Represents the Student id of the Student.
     *
     * @param idStudent  Unique id of the student.
     *
     * @return void.
     */
    public void setIdStudent(int idStudent) {
        this.idStudent=idStudent;
    }
    /**
     * Represents the Answers for the Test.
     *
     * @param answers -The answers given in the test.
     */
    public void setAnswers(String answers) {
        this.answers=answers;
    }
    /**
     * Represents the correct answers in Test.
     *
     * @param correct -The answers which are correct.
     *
     * @return void.
     */
    public void setCorrect(int correct) {
        this.correct=correct;
    }
    /**
     * Represents the incorrect answers in Test.
     * @param incorrect  Answers which are incorrect.
     *
     * @return void.
     */
    public void setInCorrect(int incorrect) {
        this.incorrect=incorrect;
    }
    /**
     * Represents the marks gained in Test.
     *
     * @param marks -The marks obtained in test.
     *
     * @return void.
     */
    public void setMarks(float marks) {
        this.marks=marks;
    }
    //getter method to set get the value for class variables
    /**
     * Gives the Test id in the Test.
     *
     * @return idTest-Unique id of the Test.
     */
    public int getIdTest() {
        return idTest;
    }
    /**
     * Gives the student id in Test.
     *
     * @return idStudent-Unique id of the Student.
     */
    public int getIdStudent() {
        return idStudent;
    }
    /**
     * Gives the answers in the Test.
     *
     * @return answers-Gives the TestAnswers.
     */
    public String getAnswers() {
        return answers;
    }
    /**
     * Gives the marks in the Test.
     *
     * @return marks-Marks gained by the Students.
     */
    public float getMarks() {
        return marks;
    }
    /**
     * Gives the correct answers in the Test.
     *
     * @return correct-Answers which are correct.
     */
    public int getCorrect() {
        return correct;
    }
    /**
     * Gives the incorrect answers in the Test.
     * 
     * @return inCorrect-Answers Which are incorrect.
     */
    public int getInCorrect() {
        return incorrect;
    }


}