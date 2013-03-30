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
 * Displays the assignment of teachers to student group.
 */

public class Assignment {
    //declaring class variables
    int idTeacher;
    int idGroup;
    int idTopic;
    String assignmentcol;

    //setter methods to set the value of class variables
    /**
     * Represents the Teacher Id
     *
     * @param idteacher  unique id of the teacher.
     *
     * @return void
     */
    public void setIdTeacher(int idteacher) {
        this.idTeacher = idteacher;
    }
    /**
     * Represents the Group Id
     *
     * @param idGroup unique id of the group.
     *
     * @return void
     */
    public void setIdGroup(int idGroup) {
        this.idGroup= idGroup;
    }
    /**
     * Represents the Topic Id
     *
     * @param idTopic  unique id of the topic.
     *
     * @return void
     */
    public void setIdTopic(int idTopic) {
        this.idTopic= idTopic;
    }
    /**
     * Represents the AssignmentCol
     *
     * @param assignmentcol  assignment to students by teachers.
     *
     * @return void
     */
    public void setAssignmentcol(String assignmentcol) {
        this.assignmentcol= assignmentcol;
    }

    //Getter methods to get the value of class variables
    /**
     * Gets the Teacher Id
     *
     * @return idTeacher - shows the unique id of teacher.
     */
    public int getIdTeacher() {
        return idTeacher;
    }
    /**
     * Gets the Group Id for assignment.
     *
     * @return idGroup - shows the unique id of the group to which student belongs.
     */
    public int getIdGroup() {
        return idGroup;
    }
    /**
     * Gets the Topic Id for assignment.
     *
     * @return idTopic - shows the unique id of topic.
     */
    public int getIdTopic() {
        return idTopic;
    }
    /**
     * Gets the assignmentCol for assignment
     *
     * @return assignmentCol - shows the assignment to students by teachers.
     */
    public String getAssignmentcol() {
        return assignmentcol;
    }
}