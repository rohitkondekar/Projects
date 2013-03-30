/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tease.bean;

/**
 *
 * @author anusha11
*/
import java.sql.Timestamp;
/**
 *
 * Schedule of test to be given to student groups.
 */

public class TestSchedule {
   //Declaring class variables.
   private int idTest;
   private int idGroup;
   private String location;
   private Timestamp startTime;
   private Timestamp endTime;

   //setter methods for class variable
   /**
    * Represents the Test id of the Test.
    *
    * @param idTest -Unique id of the test.
    *
    * @return void.
    */
   public void setIdTest(int idTest) {
        this.idTest = idTest;
   }
   /**
    * Represents the Group ID.
    *
    * @param idGroup Unique id of the group.
    *
    * @return void.
    */
   public void setIdGroup(int idGroup) {
        this.idGroup=idGroup;
   }
   /**
    * Represents the Location of TestSchedule.
    * 
    * @param location  -The location of the test.
    *
    * @return void.
    */
   public void setLocation(String location) {
        this.location=location;
   }
   /**
    * Represents the StartTime of TestSchedule.
    *
    * @param startTime  -The test starting time.
    *
    * @return void.
    */
   public void setStartTime(Timestamp startTime) {
        this.startTime=startTime;
   }
   /**
    * Represents the EndTime of TestSchedule.
    *
    * @param endTime  -The test ending time.
    *
    * @return void.
    */
   public void setEndTime(Timestamp endTime) {
        this.endTime=endTime;
   }

   //getter methods for class variables
   /**
    * Gives the id of the Test.
    *
    * @return idTest -Unique id of the test.
    */
   public int getIdTest() {
        return idTest;
   }
   /**
    * Gives the id of the Group.
    *
    * @return idGroup-Unique id of the group.
    */
   public int getIdGroup() {
        return idGroup;
   }
   /**
    * Gives the location of TestSchedule.
    *
    * @return location -The location of the test schedule.
    */
   public String getLocation() {
        return location;
   }
   /**
    * Gives the StartTime of TestSchedule.
    *
    * @return startTime -The stating time of the test.
    */

   public Timestamp getStartTime() {
        return startTime;
   }
   /**
    * Gives the EndTime of TestSchedule.
    *
    * @return endTime -The ending time of the test.
    */

   public Timestamp getEndTime() {
        return endTime;
   }
}