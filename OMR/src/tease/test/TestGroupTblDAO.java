/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tease.test;
/**
 *
 * @author nishi11
 */
import tease.bean.GroupTbl;
import tease.dao.GroupTblDAO;
import java.text.SimpleDateFormat;
import java.text.ParseException;
//import tease.helper.DBConnection;
import java.util.*;

/**
 * Grouping of students. Groups have hierarchy. Students added to each group.
 */
public class TestGroupTblDAO {

    GroupTblDAO dao = new GroupTblDAO();
    GroupTbl  groupTbl = new GroupTbl();
    Scanner scan = new Scanner(System.in);
    public TestGroupTblDAO() {
    }
    /** Test whether inserts a row into group table of the database.
     * 
     * @return void
     */
    public void TestInsertGroupTbl() {
        System.out.println("enter the parameter value to insert");
        System.out.println("parentgroup id:");
        int idparent = scan.nextInt();
        groupTbl.setIdparentGroup(idparent);
        System.out.println("display name:");
        String display = scan.next();
        groupTbl.setDisplayName(display);
        System.out.println("full description:");
        String fulldesc = scan.next();
        groupTbl.setFullDescription(fulldesc);
        System.out.println(dao.insertGroupTbl(groupTbl));
    }
    /** Test whether deletes a row from group table of the database.
     * 
     * @return void
     */
    public void TestDeleteGroupTbl() {
        System.out.println("enter the groupid to be deleted");
        int i = scan.nextInt();
        System.out.println(dao.deleteGroupTbl(i));
    }
    /** Test whether updates a row from group table of the database.
     * 
     * @return void
     */
    public void TestUpdateGroupTbl() {
        System.out.println("enter the parameter value to update");
        System.out.println("group id:");
        int idgroup = scan.nextInt();
        groupTbl.setIdGroup(idgroup);
        System.out.println("parentgroup id:");
        int idparent = scan.nextInt();
        groupTbl.setIdparentGroup(idparent);
        System.out.println("display name:");
        String display = scan.next();
        groupTbl.setDisplayName(display);
        System.out.println("full description:");
        String fulldesc = scan.next();
        groupTbl.setFullDescription(fulldesc);
        System.out.println(dao.updateGroupTbl(groupTbl));
    }
    /** Test whether group table has next row or not.
     * 
     * @return void
     */
    public void TestHasNext() {
        System.out.println(dao.hasNext());
    }
    /** Test whether shows all the rows one by one from group table.
     * 
     * @return void
     */
    public void TestGetGroupTbl() {
        for(groupTbl =dao.getGroupTbl();groupTbl!=null;) {
        System.out.println(groupTbl.getIdGroup());
        System.out.println(groupTbl.getIdparentGroup());
        System.out.println(groupTbl.getDisplayName());
        System.out.println(groupTbl.getFullDescription());
        groupTbl = dao.getGroupTbl();
        }
    }
    
    public void TestGetGroupByIdGroup() {
        System.out.println("enter the groupid for detail of group");
        int i = scan.nextInt();
        groupTbl = dao.getGroupByIdGroup(i);
        System.out.println(groupTbl.getIdparentGroup());
        System.out.println(groupTbl.getDisplayName());
        System.out.println(groupTbl.getFullDescription());
    }
    public void TestGetGroupByParameter() {
        GroupTbl[] groupTbl =null;
        System.out.println("enter the value for parameters");
        System.out.println("groupid:");
        Integer idgroup = new Integer(scan.nextInt());
        System.out.println("parentgroupid:");
        Integer idparentgroup = new Integer(scan.nextInt());
        System.out.println("\ndisplay name:");
        String display = scan.next();
        System.out.println("\nfull description:");
        String fuldesc = scan.next();
        //groupTbl = dao.getGroupByParameter(new Integer(57),null,null,null);//lidgroup,idparentgroup,display,fuldesc);
        groupTbl = dao.getGroupByParameter(idgroup,idparentgroup,display,fuldesc);
        if(groupTbl != null)
        {
            int size = groupTbl.length;

           for(int i=0; i<size;i++) {
           System.out.println(groupTbl[i].getIdGroup());
           System.out.println(groupTbl[i].getIdparentGroup());
           System.out.println(groupTbl[i].getDisplayName());
           System.out.println(groupTbl[i].getFullDescription());
           }
        }
            else
            {
                System.out.println("no record found");
            }
    }

    public static void main(String[] args) {
        TestGroupTblDAO test = new TestGroupTblDAO();
        System.out.println("enter 1.insert 2.delete 3.update 4.hasnext 5.getgroup 6.getgroup by id group 7.get group by parameter\n");
        System.out.println("enter ur choice to test method");
        Scanner scan = new Scanner(System.in);
        int i= scan.nextInt();
        switch(i) {
            case 1:test.TestInsertGroupTbl();
            break;
            case 2:test.TestDeleteGroupTbl();
            break;
            case 3:test.TestUpdateGroupTbl();
            break;
            case 4:test.TestHasNext();
            break;
            case 5:test.TestGetGroupTbl();
                break;
            case 6:test.TestGetGroupByIdGroup();
                break;
            case 7:test.TestGetGroupByParameter();
                break;
            default: System.out.println("exit");
        }
    }
}