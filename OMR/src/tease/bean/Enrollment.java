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
 * Enrollment of students into groups.
 */

public class Enrollment {
    //declaring class variables
    private String idEnrollment;
    private int idStudent;
    private int idGroup;

    //setter methods to set the value of class variables
    /**
     * Represents the enrollment id
     *
     * @param idEnrollment  unique enrollment id of the student.
     *
     * @return void
     */
    public void setIdEnrollment(String idEnrollment) {
        this.idEnrollment = idEnrollment;
    }
    /**
     * Represents the student id
     *
     * @param idStudent  unique id of the student.
     *
     * @return void
     */
    public void setIdStudent(int  idStudent) {
        this. idStudent =  idStudent;
    }
    /**
     * Represents the Group Id
     *
     * @param idGroup  unique id of the group.
     *
     * @return void
     */
    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    //Getter methods to get the value of class variables
    /**
     * Gets the Enrollment Id
     *
     * @return idEnrollment - unique id of enrollment.
     */
    public String getIdEnrollment() {
        return idEnrollment;
    }
    /**
     * Gets the students id
     *
     * @return idStudent - unique id of the student.
     */
    public int getIdStudent() {
        return idStudent;
    }
    /**
     * Gets the Group id
     *
     * @return idGroup - unique id of the group
     */
    public int getIdGroup() {
        return idGroup;
    }
}