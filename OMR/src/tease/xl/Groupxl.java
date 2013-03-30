/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tease.xl;

/**
 *
 * @author administrator
 */
import tease.bean.*;
import tease.dao.*;
import java.util.*;
import jxl.write.*;
import jxl.*;
import java.io.File;
import jxl.read.biff.BiffException;
import java.sql.SQLException;
import java.io.IOException;
public class Groupxl {
    private String inputFile;
/**
 * sets the input file
 * 
 * @param inputFile the file input
 * 
 * @return void.
 */
public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
}
/** write into the excel sheet from group table
 * 
 * @throws IOException Signals that an I/O exception has occurred.
 * @throws SQLException the SQL exception
 */
public void write() throws IOException, SQLException  {
    try {            
        WritableWorkbook workbook = Workbook.createWorkbook(new File(inputFile)); //WritableSheet sheet = workbook.createSheet(“First Sheet”,0);
        WritableSheet sheet = workbook.createSheet("group table",0);
        GroupTblDAO dao = new GroupTblDAO();
        GroupTbl groupTbl;
        Label label1 = new Label(0,0,"idGroup");
        sheet.addCell(label1);
        Label label2 = new Label(1,0,"idparentGroup");
        sheet.addCell(label2);
        Label label3 = new Label(2,0,"displayName");
        sheet.addCell(label3);
        Label label4 = new Label(3,0,"fullDescription");
        sheet.addCell(label4);
        int i=1;
        while(dao.hasNext() == true)
        {
            groupTbl = dao.getGroupTbl();
            sheet.setColumnView(0, 15);
            jxl.write.Number num1 = new jxl.write.Number(0,i,groupTbl.getIdGroup());
            sheet.addCell(num1);
            sheet.setColumnView(1, 15);
            jxl.write.Number num2 = new jxl.write.Number(1,i,groupTbl.getIdparentGroup());
            sheet.addCell(num2);
            sheet.setColumnView(2, 15);
            Label label6 = new Label(2,i,groupTbl.getDisplayName());
            sheet.addCell(label6);
            sheet.setColumnView(3, 15);
            Label label7 = new Label(3,i,groupTbl.getFullDescription());
            sheet.addCell(label7);
            i++;
        }
        workbook.write();
        workbook.close();
        dao = null;
        groupTbl = null;
    }
    catch(Exception e)
    {
    System.out.println("IO Exception caught ");
    }
}
/** read from excel sheet of group table
 * 
 * @throws IOException Signals that an I/O exception has occurred.
 * @throws SQLException the SQL exception
 */
public void read() throws IOException, SQLException  {
    try {
    Workbook w = Workbook.getWorkbook(new File(inputFile));	
    // Get the first sheet
    Sheet sheet = w.getSheet(0);
    for (int j = 0; j < sheet.getRows(); j++) {
         for (int i = 0; i < sheet.getColumns(); i++) {
             Cell cell = sheet.getCell(i, j);
             CellType type = cell.getType();
             if (cell.getType() == CellType.LABEL) {                                     
                 System.out.print(cell.getContents() + " ");
      }
      else if (cell.getType() == CellType.NUMBER) {
          System.out.print(cell.getContents() + " ");    
      }
      else {
          System.out.print(cell.getContents() + " ");
      }
      }
      System.out.println("\n");
    }
    w.close();
    } catch (BiffException e) {
        e.printStackTrace();
    }
}
/** test the read and write to excel sheet.
 * 
 * @param args string argument
 * @throws IOException Signals that an I/O exception has occurred.
 * @throws SQLException the SQL exception
 */
 public static void main(String[] args) throws IOException, SQLException {
    Groupxl group = new Groupxl();
    group.setInputFile("/home/administrator/Desktop/Group.xls");
    System.out.println("enter 1.write 2.read\n");
    System.out.println("enter ur choice to test method");
    try {
    Scanner scan = new Scanner(System.in);
    int i= scan.nextInt();
    switch(i) {
        case 1:group.write();
        break;
        case 2:group.read();
        break;
        default:
            System.out.println("exit");
    }
    }catch(InputMismatchException e) {
    System.out.println("enter a valid number!!!");
    }
 }
}
