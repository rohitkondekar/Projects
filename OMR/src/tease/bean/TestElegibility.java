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
 * The groups/subgroups to whom the test needs to be delivered.
 *
 */
public class TestElegibility {
   //Declaring class variables.
   private  int idTest;
   private int idGroup;
   private int status;
   //setter method to set the value for class variable
   /**
    * Represents the Test id of the Test.
    *
    * @param idTest Unique id of the test.
    *
    * @return void.
    */
   public void setIdTest(int idTest) {
        this.idTest = idTest;
   }
   /**
    * Represents the Group ID.
    *
    * @param idGroup Unique group id of the student.
    *
    * @return void.
    */
   public void setIdGroup(int idGroup) {
        this.idGroup=idGroup;
   }
   /**
    * Represents the Status.
    *
    * @param status -The status of the test.
    *
    * @return void.
    */
   public void setStatus(int status) {
        this.status=status;
   }

   //getter method to get the value for class variables
   /**
    * Gives the id of the Test.
    *
    * @return idTest Unique id of the test.
    */
   public int getIdTest() {
        return idTest;
   }
   /**
    * TGives the group id.
    *
    * @return idGroup-Unique id of the group.
    */
   public int getIdGroup() {
        return idGroup;
   }
   /**
    * Gives the Status of TestEligibility.
    * 
    * @return status-shows the status of the testelegibility.
    */
   public int getStatus() {
          return status;
   }
}