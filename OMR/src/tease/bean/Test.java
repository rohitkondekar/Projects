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
 *Details of the assessment test to be attempted by students.
 */
public class Test {
    //Declaring class variables
    private  int idTest;
    private int idTopic;
    private float marks;
    private int duration;
    private String displayName;
    private String fullDescription;

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
     * Represents the Topic id of the Test
     *
     * @param idTopic -Unique id of the topic
     *
     * @return void.
     */
    public void setIdTopic(int idTopic) {
        this.idTopic=idTopic;
    }
    /**
     * Represents the Marks in the Test.
     *
     * @param marks  -The total marks of the test.
     *
     * @return void.
     */
    public void setMarks(float marks) {
       this.marks=marks;
    }
    /**
     * Represents the Time duration of Test.
     *
     * @param duration  -The total duration of the test.
     *
     * @return void.
     */
    public void setDuration(int duration) {
       this.duration=duration;
    }
    /**
     * Represents the Display name of Test.
     *
     * @param displayName -The display name of the test.
     *
     * @return void.
     */
    public void setDisplayName(String displayName) {
       this.displayName=displayName;
    }
    /**
     * Represents the Full description of the Test.
     *
     * @param fullDescription -The full description of the test.
     *
     * @return void.
     */
    public void setFullDescription(String fullDescription) {
       this.fullDescription=fullDescription;
    }

    //getter method to get the values for class variables
    /**
     * Gives the id of the Test.
     *
     * @return idTest -Unique id of the test.
     */
    public int getIdTest() {
        return idTest;
    }
    /**
     * Gives the Topic id of the Test.
     *
     * @return idTopic -Unique id of the topic.
     */
    public int getIdTopic() {
        return idTopic;
    }
    /**
     * Gives the marks gained in the Test.
     *
     * @return marks -The marks gained in the test.
     */
    public float getMarks() {
        return marks ;
    }
    /**
     * Gives the Time duration of Test.
     *
     * @return duration -The time duration of the test.
     */
    public int getDuration() {
        return duration ;
    }
    /**
     * Gives the display name of Test.
     *
     * @return displayName -The display name of the test.
     */
    public String getDisplayName() {
        return displayName ;
    }
    /**
     * GIves the FullDescription of the Test.
     * 
     * @return fullDescription -The full description of the test.
     */
    public String getFullDescription() {
        return fullDescription ;
    }
}