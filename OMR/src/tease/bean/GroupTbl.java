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
 * Grouping of students. Groups have hierarchy. Students added to each group.
 */

public class GroupTbl {
    //declaring class variables
    private int idGroup;
    private int idparentGroup;
    private String displayName;
    private String fullDescription;

    //setter methods to set the value of class variables
    /**
     * Represents the Group ID.
     *
     * @param idGroup  unique id of the group.
     *
     * @return void
     */
    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }
    /**
     * Represents the Parent group of the Group
     *
     * @param idparentGroup   parentGroup id of the group.
     *
     * @return void
     */
    public void setIdparentGroup(int idparentGroup) {
        this.idparentGroup = idparentGroup;
    }
    /**
     * Represents the Name of the Group
     *
     * @param displayName  the group display name.
     *
     * @return void
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    /**
     * Represents the full description of the Group
     *
     * @param fullDescription  fullDescription of the group.
     *
     * @return void
     */
    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    //Getter methods to get the value of class variables
    /**
     * Gets the group id of student.
     *
     * @return idGroup - shows the unique id of the group.
     */
    public int getIdGroup() {
        return idGroup;
    }
    /**
     * Gets the parent Group Id.
     *
     * @return idparentGroup - shows the parent id of the group.
     */
    public int getIdparentGroup() {
        return idparentGroup;
    }
    /**
     * Gets the display Name of the Group.
     *
     * @return displayName - shows the display name of the group.
     */
    public String getDisplayName() {
        return displayName;
    }
    /**
     * Gets the full description of the Group table.
     *
     * @return fulDescription - shows the description of the group.
     */
    public String getFullDescription() {
        return fullDescription;
    }
}