/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tease.assessment;
/**
 * @author Abhinav Kumar
 * @version 1.7
 */
/**
 *Contains all the data of the student of a particular group
 */
public class StudData
{
    private int idGroup;
    private int idStudent;
    private int idTest;
    private float marks;

    /**
     *Assigns marks ,correct and incorrect answers to 0
     */
    public StudData()
    {
        marks =0;
    }
    
    /**
     * Sets the id of the Student
     * @param idStudent id of the student 
     */
    public void setIdStudent( int idStudent)
    {
        this.idStudent = idStudent;
    }

    /**
     * Sets the group of the student
     * @param idGroup idGroup of the Student
     */
    public void setIdGroup( int idGroup )
    {
        this.idGroup = idGroup;
    }

    /**
     * Sets the idTest of the student
     * @param idTest idTest of the student 
     */
    public void setIdTest(int idTest)
    {
        this.idTest = idTest;
    }

    /**
     *increases the marks of the student in a particular test
     *@param n the no by which marks of the candidate is to be increased
     */
    public void incrementMarks(float n)
    {
        marks += n;
    }

    /**
     *returns the id of the student
     *@return idStudent id of the Student
     */
    public int getIdStudent()
    {
        return idStudent;
    }

    /**
     * Returns the group of the student
     * @return idGroup group of the student
     */
    public int getIdGroup()
    {
        return idGroup;
    }
    
    /**
     * Returns the test id of the student
     * @return idTest the test of the student
     */
    public int getIdTest()
    {
        return this.idTest;
    }

    /**
     *returns the marks obtained by the student
     *@return marks the score of the student
     */
    public float getMarks()
    {
        return marks;
    }
}