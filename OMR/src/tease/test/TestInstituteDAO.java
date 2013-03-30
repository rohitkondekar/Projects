/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tease.test;

/**
 *
 * @author administrator
 */
import tease.bean.Institute;
import tease.dao.InstituteDAO;
import java.util.*;
public class TestInstituteDAO {
    //declaring class variables
    InstituteDAO dao = new InstituteDAO();
    Institute institute = new Institute();
    Scanner scan = new Scanner(System.in);
    public TestInstituteDAO() {
    }
    public void TestInsertInstitute() {
        System.out.println(dao.insertInstitute(institute));
    }
    public void TestDeleteInstitute() {
        System.out.println("enter the instituteid to be deleted");
        int i = scan.nextInt();
        System.out.println(dao.deleteInstitute(i));
    }
    public void TestUpdateInstitute() {
        System.out.println("enter the parameter value to update");
        System.out.println("institute id:");
        int id = scan.nextInt();
        institute.setIdInstitute(id);
        System.out.println("instituteName:");
        String name = scan.next();
        institute.setInstituteName(name);
        System.out.println("motto:");
        String motto = scan.next();
        institute.setMotto(motto);
        System.out.println("logo:");
        String logo = scan.next();
        institute.setLogo(logo);
        System.out.println("levelOfEducation:");
        String edu = scan.next();
        institute.setLevelOfEducation(edu);
        System.out.println("email:");
        String email = scan.next();
        institute.setEmail(email);
        System.out.println("instituteDescription:");
        String instituteDescription = scan.next();
        institute.setInstituteDescription(instituteDescription);

         System.out.println(dao.updateInstitute(institute));
    }
    public void TestHasNext() {
        System.out.println(dao.hasNext());
    }
    public void TestGetInstitute() {
        institute = dao.getInstitute();

        for(institute=dao.getInstitute();institute!=null;) {

            System.out.println(institute.getIdInstitute());
            System.out.println(institute.getInstituteName());
            System.out.println(institute.getMotto());
            System.out.println(institute.getLogo());
            System.out.println(institute.getLevelOfEducation());
            System.out.println(institute.getEmail());
            System.out.println(institute.getInstituteDescription());
            institute = dao.getInstitute();

        }

    }

    public void TestGetInstituteByParameter() {
        Institute[] institute = null;
        System.out.println("enter the value for parameters");
        System.out.println("idInstitute");
        Integer id =scan.nextInt();
        System.out.println("instituteName:");
        String name = scan.next();
        System.out.println("motto:");
        String motto = scan.next();
        System.out.println("logo:");
        String logo = scan.next();
        System.out.println("levelOfEducation:");
        String edu = scan.next();
        System.out.println("email:");
        String email = scan.next();
        System.out.println("instituteDescription:");
        String instituteDescription = scan.next();

        institute= dao.getInstituteByParameter(id, name, motto, logo, edu, email, instituteDescription);
        if(institute == null)
                System.out.println("not found the parameter");
        else
        {
        int size = institute.length;
        for(int i=0; i<size;i++) {
            System.out.println(institute[i].getIdInstitute());
               System.out.println(institute[i].getInstituteName());
               System.out.println(institute[i].getMotto());
               System.out.println(institute[i].getLogo());
               System.out.println(institute[i].getLevelOfEducation());
               System.out.println(institute[i].getEmail());
               System.out.println(institute[i].getInstituteDescription());
           }
        }
    }
    public static void main(String args[]) {
        TestInstituteDAO test= new TestInstituteDAO();
        System.out.println("enter your choice to test method");
        Scanner scan = new Scanner(System.in);
        int i= scan.nextInt();
        switch(i) {
            case 1:test.TestInsertInstitute();
            break;
            case 2:test.TestDeleteInstitute();
            break;
            case 3:test.TestUpdateInstitute();
            break;
            case 4:test.TestHasNext();
            break;
            case 5:test.TestGetInstitute();
            break;
            case 6:test.TestGetInstituteByParameter();
            break;
            default: System.out.println("exit");
        }
    }

}