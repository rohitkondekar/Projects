/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tease.test;

/**
 *
 * @author administrator
 */
import tease.bean.Location;
import tease.dao.LocationDAO;
import java.util.*;
public class TestLocationDAO {
    //declaring class variables
    LocationDAO dao = new LocationDAO();
    Location location = new Location();
    Scanner scan = new Scanner(System.in);
    public TestLocationDAO() {
    }
    public void TestInsertLocation() {
        System.out.println(dao.insertLocation(location));
    }
    public void TestDeleteLocation() {
        System.out.println("enter the locationid to be deleted");
        String i = scan.next();
        System.out.println(dao.deleteLocation(i));
    }
    public void TestUpdateLocation() {
        System.out.println("enter the parameter value to update");
        System.out.println("\nidLocation:");
        int idLocation = scan.nextInt();
        location.setIdLocation(idLocation);
        System.out.println("\nlocationName:");
        String locationName = scan.next();
        location.setLocationName(locationName);
        System.out.println("locationStrength:");
        int k = scan.nextInt();
        location.setLocationStrength(k);

        System.out.println(dao.updateLocation(location));
    }
    public void TestHasNext() {
        System.out.println(dao.hasNext());
    }
    public void TestGetLocation() {

        for(location=dao.getLocation();location!=null;) {
               System.out.println(location.getIdLocation());
               System.out.println(location.getLocationName());
               System.out.println(location.getLocationStrength());
               location = dao.getLocation();

        }

    }
    public void TestgetlocationByIdlocation() {
        System.out.println("enter the locationid for detail of location");
        int i = scan.nextInt();
        location = dao.getlocationByIdlocation(i);
        System.out.println(location.getLocationName());
        System.out.println(location.getLocationStrength());

    }
    public void TestGetLocationByParameter() {
        Location[] location = null;
        System.out.println("enter the value for parameters");
        System.out.println("\nidLocation:");
        Integer idLocation = new Integer(scan.nextInt());
        System.out.println("\nlocationName:");
        String locationName = scan.next();
        System.out.println("\nlocationStrength:");
        Integer j = new Integer(scan.nextInt());


        location = dao.getLocationByParameter(idLocation, locationName, j);
        if(location == null)
                System.out.println("not found the parameter");
        else
        {


           int size = location.length;
           for(int i=0; i<size;i++) {
               System.out.println(location[i].getIdLocation());
               System.out.println(location[i].getLocationName());
               System.out.println(location[i].getLocationStrength());

           }
        }
    }
    public static void main(String args[]) {
        TestLocationDAO test= new TestLocationDAO();
        System.out.println("enter your choice to test method");
        Scanner scan = new Scanner(System.in);
        int i= scan.nextInt();
        switch(i) {
            case 1:test.TestInsertLocation();
            break;
            case 2:test.TestDeleteLocation();
            break;
            case 3:test.TestUpdateLocation();
            break;
            case 4:test.TestHasNext();
            break;
            case 5:test.TestGetLocation();
            break;
            case 6:test.TestgetlocationByIdlocation();
            break;
            case 7:test.TestGetLocationByParameter();
            break;
            default: System.out.println("exit");
        }

    }

}
