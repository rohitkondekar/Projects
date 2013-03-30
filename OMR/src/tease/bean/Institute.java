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
 *
 * Gives the details about Institute.
 */
public class Institute {
    //Declaring class variables
    int idInstitute;
    String instituteName;
    String motto;
    String logo;
    String levelOfEducation;
    String email;
    String instituteDescription;

    /**
     * Represents the institute id.
     *
     * @param idInstitute unique id of the institute.
     *
     * @return void.
     */
    public void setIdInstitute(int idInstitute) {
        this.idInstitute = idInstitute;
    }
    /**
     *  Represents the name of institution.
     *
     * @param instituteName  Unique name of the institution.
     *
     * @return  void.
     */
    public void setInstituteName(String instituteName) {
        this.instituteName= instituteName;
    }
    /**
     * Represents the motto.
     * 
     * @param motto Gives the Aim.
     * 
     * @return  void.
     */
    public void setMotto(String motto) {
        this.motto= motto;
    }
    /**
     * Represents the image.
     * 
     * @param logo Gives the image.
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }
    /**
     * Represents the education level.
     * 
     * @param levelOfEducation  Gives the education level.
     * 
     * @return  void.
     */

    public void setLevelOfEducation(String levelOfEducation) {
        this.levelOfEducation = levelOfEducation;
    }
    /**
     * Represents the email of institute.
     * 
     * @param email  Gives valid email.
     * 
     * @return   void.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Represents the description of the Institute.
     * 
     * @param instituteDescription Gives full description
     * 
     * @return  void.
     */
    public void setInstituteDescription(String instituteDescription) {
        this.instituteDescription = instituteDescription;
    }
    /**
     * Gives the institute id.
     * 
     * @return idInstitute unique id of the institute.
     */
    public int getIdInstitute() {
        return idInstitute;
    }
    /**
     * Gives the name of the institute.
     * 
     * @return instituteName unique name of the institute.
     */
    public String getInstituteName() {
        return instituteName;
    }
     /**
     * Gives the Motto.
     * 
     * @return motto displays the motto.
     */
    public String getMotto() {
        return motto;
    }
    /**
     * Gives the Image of user.
     * 
     * @return logo Displays the image.
     */
    public String getLogo() {
        return logo;
    }
    /**
     * Gives the valid Email of the Institute.
     * 
     * @return email  email of the institute.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Gives the description of the institute.
     * 
     * @return instituteDescription  full description of institute.
     */
    public String getInstituteDescription() {
        return instituteDescription;
    }
    /**
     *  Gives the education level.
     * 
     * @return levelOfEducation details of education level.
     */
    public String getLevelOfEducation() {
        return levelOfEducation;
    }

}