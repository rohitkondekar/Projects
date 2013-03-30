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
 * Gives the details of the location.
 */
public class Location {
     //Declaring class variables
    int idLocation;
    String locationName;
    int locationStrength;
    /**
     * Represents the location id.
     *
     * @param idLocation  unique id of the location.
     *
     * @return  void.
     */
    public void setIdLocation(int idLocation) {
        this.idLocation= idLocation;
    }
    /**
     * Represents the name of location.
     * 
     * @param locationName  name of the location
     *
     * @return  void
     */

    public void setLocationName(String locationName) {
        this.locationName= locationName;
    }
    /**
     * Represents the strength of the location.
     *
     * @param locationStrength strength of the location.
     *
     * @return  void.
     */
    public void setLocationStrength(int locationStrength) {
       this.locationStrength= locationStrength;
    }
    /**
     * Gives the location id.
     *
     * @return idLocation  unique id of the location.
     */
    public int getIdLocation() {
         return idLocation;
    }
    /**
     * Gives the location name
     *
     * @return locationName  name of the location.
     */

    public String getLocationName() {
         return locationName;
    }
    /**
     * Gives the strength of location.
     *
     * @return locatiobStrength  strength of location.
     */
    public int getLocationStrength() {
         return locationStrength;
    }
}