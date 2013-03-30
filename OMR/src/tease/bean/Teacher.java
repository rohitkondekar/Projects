/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tease.bean;

/**
 *
 * @author nishi11
 */
/**
 * Represents a Teacher enrolled in the school.
 */

public class Teacher {
    //declaring class variables
    private int idTeacher;
    private String firstName;
    private String lastName;
    private String designation;
    private String qualification;

    //setter methods to set the value of class variables
    /**
     * Represents the Teacher Id
     *
     * @param idTeacher  unique id of the teacher.
     *
     * @return void
     */
    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }
    /**
     *Creates a new Teacher with the given first Name.
     *
     * @param firstName  the first name of the teacher.
     *
     * @return void
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * Creates a new Teacher with the given last name.
     *
     * @param lastName  the last name of the teacher.
     *
     * @return void
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * Represents the teacher designation
     *
     * @param designation  the designation place of the teacher.
     *
     * @return void
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    /**
     * The Teachers Qualification is to be Displayed
     *
     * @param qualification  the qualification of the teacher.
     *
     * @return void
     */
    public void setQualification(String  qualification) {
        this. qualification= qualification;
    }

    //Getter methods to get the value of class variables
    /**
     * Gets the Teacher id of teacher
     *
     * @return idTeacher - shows the unique id of teacher
     */
    public int getIdTeacher() {
        return idTeacher;
    }
    /**
     * Gets the Teachers FirstName
     *
     * @return firstName - shows the first name of teacher
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Gets the Teacher last name
     *
     * @return lastName - shows the last name of the teacher
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Gives the designation of a Teacher
     *
     * @return designation - shows the designation place of the teacher.
     */
    public String getDesignation() {
        return  designation;
    }
    /**
     * Gives the Teacher Qualification
     *
     * @return qualification - shows the qualification of the teacher.
     */
    public String  getQualification() {
        return  qualification;
    }
}