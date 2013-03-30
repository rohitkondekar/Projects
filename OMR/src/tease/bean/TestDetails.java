/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tease.bean;

/**
 *
 * @author anusha11
 */
/**
 * Details (description of questions) of the test to be given.
 *
 */
public class TestDetails {
   //Declaring class variables
   private  int idTest;
   private int sequence;
   private int questionType;
   private int questionOptions;
   private String correctAnswer;
   private float marks;
   private float negativeMarks;

   //setter methods to set the value for class variable
   /**
    * Represents the TestId of Test
    *
    * @param idTest -Unique id of the test.
    *
    * @return void.
    */
   public void setIdTest(int idTest) {
        this.idTest = idTest;
   }
   /**
    * Represents the Sequence of the Test.
    *
    * @param sequence -The test sequence number.
    *
    * @return void.
    */
   public void setSequence(int sequence) {
        this.sequence=sequence;
   }
   /**
    * Represents the Type of question in the Test.
    *
    * @param questionType -The type of the question.
    *
    * @return void.
    */
   public void setQuestionType(int questionType) {
        this.questionType=questionType;
   }
   /**
    * Represents the number of options for the Question in Test
    *
    * @param questionOptions The total options for the question.
    *
    * @return void.
    */
   public void setQuestionOptions(int questionOptions) {
        this.questionOptions=questionOptions;
   }
   /**
    * Represents the Correct Answer for the Test
    *
    * @param correctAnswer -The answer which is correct.
    *
    * @return void.
    */
   public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer=correctAnswer;
   }
   /**
    * Represents the Marks for the Test
    *
    * @param marks -The marks of the test.
    *
    * @return void.
    */
   public void setMarks(float marks) {
       this.marks=marks;
   }
   /**
    * Represents the Negative Marks for the Test.
    *
    * @param negativeMarks  the negative mark obtained in test.
    *
    * @return void.
    */
   public void setNegativeMarks(float negativeMarks) {
     this.negativeMarks=negativeMarks;
   }

   //getter method to get the value  for class variables
   /**
    * Gives the Test id
    *
    * @return idTest-Unique id of the Test.
    */
   public int getIdTest() {
        return idTest;
   }
   /**
    * Gives the Sequence Number for Test.
    *
    * @return sequence-The sequence number of the Test.
    */
   public int getSequence() {
        return sequence;
   }
   /**
    * Gives the Type of Question in Test.
    *
    * @return questionType-Shows the type of question.
    */
   public int getQuestionType() {
        return questionType ;
   }
   /**
    * Gives the Question Options in Test
    *
    * @return questionOptions-Shows the number of options for the question.
    */
   public int getQuestionOptions() {
        return questionOptions ;
   }
   /**
    * Gives the correct answers in Test
    *
    * @return correctAnswer- The answers which are correct.
    */
   public String getCorrectAnswer() {
        return correctAnswer;
   }
   /**
    * Gives the Marks gained in Test.
    *
    * @return marks-The marks gained in the test
    */
   public float getMarks() {
        return marks ;
   }
   /**
    * Gives the Negative marks in Test
    *
    * @return negativeMarks-The negative marks gained in the test.
    */
   public float getNegativeMarks() {
        return negativeMarks  ;
   }
}