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
 * Hierarchical topic descriptions. A topic is included in pare.
 */

public class Topic {
    //declaring class variables.
    private  int idTopic;
    private int idParent;
    private String displayName;
    private String fullName;

    //setter methods to set the value of class variables
    /**
     *  Represents the Topic ID.
     *
     * @param idTopic  unique id of the topic.
     *
     * @return void
     */
    public void setIdTopic(int idTopic) {
        this.idTopic = idTopic;
    }
    /**
     * Represents the parent Id of topic.
     *
     * @param idParent  the topic parent id.
     *
     * @return void
     */
    public void setIdParent(int idParent) {
        this.idParent=idParent;
    }
    /**
     * Represents the  Display name of the Topic
     *
     * @param displayName  the topic display name
     *
     * @return void
     */
    public void setDisplayName(String displayName) {
       this.displayName=displayName;
    }
    /**
     * Represents the  Full name of the Topic.
     *
     * @param fullName  the full name of the topic.
     *
     * @return void
     */
    public void setFullName(String fullName) {
       this.fullName=fullName;
    }

    //getter methods to get the value of class variables
    /**
     * Gets the topic Id of the topic
     *
     * @return idTopic - shows the unique id of topic
     */
    public int getIdTopic() {
        return idTopic;
    }
    /**
     * Gets the parent Id of Topic
     *
     * @return idParent - shows the parent id of the topic
     */
    public int getIdParent() {
        return idParent;
    }
    /**
     * Gets the Display Name of Topic
     *
     * @return displayName - shows the display name of the topic
     */
    public String getDisplayName() {
        return displayName ;
    }
    /**
     * Gets the FullName of Topic
     *
     * @return fullName - shows the full name of topic
     */
    public String getFullName() {
        return fullName ;
    }
}