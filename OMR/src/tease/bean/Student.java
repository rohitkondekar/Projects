/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tease.bean;

/**
 *
 * @author nishi11
 */
import java.sql.Date;

/**
 * Student table containing bare bones student data
 */
public class Student {
    //declaring class variables
    private  int idStudent;
    private String firstName;
    private String lastName;
    private String rollNumber;
    private Date birthDate;
    private char gender;
    private String email;
    private long mobile;
    
    //setter methods to set the value of class variables
    /**
     * Represents a student with id
     *
     * @param idStudent  unique id of the student.
     *
     * @return void
     */
    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }
    /**
     * Represents a Student with the first name.
     *
     * @param firstName  the first name of the student.
     *
     * @return void
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * Represents a Student with the last name.
     *
     * @param lastName  the last name of the student.
     *
     * @return void
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
     /**
     * Represents a Student with roll number.
     *
     * @param rollNumber the roll number of the student.
     *
     * @return void
     */
    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }
    /**
     * Represents the student date of birth
     *
     * @param birthDate  the birth date of the student.
     *
     * @return void
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    /**
     * Represents the gender of the student
     *
     * @param gender  gender includes male or female.
     *
     * @return void
     */
    public void setGender(char gender) {
        this.gender = gender;
    }
    /**
     * Represents the E-mail of a student
     *
     * @param email  the valid e-mail
     *
     * @return void
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Represents the mobile number of a Student
     *
     * @param mobile  mobile number of student.
     *
     * @return void
     */
    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    //Getter methods to get the value of class variables
    /**
     * Gets the students id.
     *
     * @return idStudent - shows the unique id of the student.
     */
    public int getIdStudent() {
        return idStudent;
    }
    /**
     * Gets the students First name.
     *
     * @return firstName - shows the first name of the student.
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Gets the students last name.
     *
     * @return lastName - shows the last name of the student.
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Gets the students roll number.
     *
     * @return rollNUmber  - shows the roll number of the student.
     */
    public String getRollNumber() {
        return rollNumber;
    }
    /**
     * Gets the students birth date.
     *
     * @return birthDate - shows the date of birth of the student.
     */
    public Date getBirthDate() {  
        return birthDate;
    }
    /**
     * Gets the Gender of a student.
     *
     * @return gender - shows whether the student is a male or female.
     */
    public char getGender() {
        return gender;
    }
    /**
     * Gets the E-mail of a student.
     *
     * @return email - shows the valid e-mail of student.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Gets the mobile number of a student.
     *
     * @return mobile - shows the mobile number of student.
     */
    public long getMobile() {
        return mobile;
    }
}