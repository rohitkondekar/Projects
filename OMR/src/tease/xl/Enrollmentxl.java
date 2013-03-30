/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tease.xl;

/**
 *
 * @author nishi11
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
public class Enrollmentxl {
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
/** write into the excel sheet from enrollment table
 * 
 * @throws IOException Signals that an I/O exception has occurred.
 * @throws SQLException the SQL exception
 */
public void write() throws IOException, SQLException  {
    try {
        WritableWorkbook workbook = Workbook.createWorkbook(new File(inputFile)); 
        WritableSheet sheet = workbook.createSheet("enrollment table",0);
        EnrollmentDAO dao = new EnrollmentDAO();
        Enrollment enrollment;
        Label label1 = new Label(0,0,"idEnrollment");
        sheet.addCell(label1);
        Label label2 = new Label(1,0,"idStudent");
        sheet.addCell(label2);
        Label label3 = new Label(2,0,"idGroup");
        sheet.addCell(label3);
        int i=1;
        while(dao.hasNext() == true)
        {
            enrollment = dao.getEnrollment();
            sheet.setColumnView(0, 15);
            Label label6 = new Label(0,i,enrollment.getIdEnrollment());
            sheet.addCell(label6);
            sheet.setColumnView(1, 15);
            jxl.write.Number num1 = new jxl.write.Number(1,i,enrollment.getIdStudent());
            sheet.addCell(num1);
            sheet.setColumnView(2, 15);
            jxl.write.Number num2 = new jxl.write.Number(2,i,enrollment.getIdGroup());
            sheet.addCell(num2);
            i++;
        }
        workbook.write();
        workbook.close();
        dao = null;
        enrollment = null;
    }
   catch(Exception e)
   {
    System.out.println("IO Exception caught ");
    }
}
/** read from excel sheet of enrollment table
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
    }catch (BiffException e) {
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
    Enrollmentxl enrollment = new Enrollmentxl();
    enrollment.setInputFile("/home/administrator/Desktop/Enrollment.xls");
    System.out.println("enter 1.write 2.read\n");
    System.out.println("enter ur choice to test method");
    try {
    Scanner scan = new Scanner(System.in);
    int i= scan.nextInt();
    switch(i) {
        case 1:enrollment.write();
        break;
        case 2:enrollment.read();
        break;
        default:
            System.out.println("exit");
    }
    }catch(InputMismatchException e) {
    System.out.println("enter a valid number!!!");
    }
 }
}
