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
import tease.helper.*;
import java.util.*;
import java.io.*;
import java.text.*;
import jxl.write.*;
import jxl.*;
import java.io.File;
import jxl.read.biff.BiffException;
import java.sql.SQLException;
import java.io.IOException;
public class Teacherxl {
    //public static void main(String args[])
    public void write() throws IOException, SQLException  {
        try
        {// FileStrem
            WritableWorkbook workbook = Workbook.createWorkbook(new File ("/home/administrator/NetBeansProjects/TEAsE/Teacher.xls"));
            //WritableSheet sheet = workbook.createSheet(“First Sheet”,0);
            WritableSheet sheet = workbook.createSheet("teacher table",0);
            TeacherDAO dao = new TeacherDAO();
            Teacher teacher;
            Label label1 = new Label(0,0,"idTeacher");
            sheet.addCell(label1);
            Label label2 = new Label(1,0,"firstName");
            sheet.addCell(label2);
            Label label3 = new Label(2,0,"lastName");
            sheet.addCell(label3);
            Label label4 = new Label(3,0,"designation");
            sheet.addCell(label4);
            Label label5 = new Label(4,0,"qualification");
            sheet.addCell(label5);
            int i=1;
            while(dao.hasNext() == true)
            {

                teacher = dao.getTeacher();
                jxl.write.Number num1 = new jxl.write.Number(0,i,teacher.getIdTeacher());
                sheet.addCell(num1);
                Label label6 = new Label(1,i,teacher.getFirstName());
                sheet.addCell(label6);
                Label label7 = new Label(2,i,teacher.getLastName());
                sheet.addCell(label7);
                Label label8 = new Label(3,i,teacher.getDesignation());
                sheet.addCell(label8);
                Label label9 = new Label(4,i,teacher.getQualification());
                sheet.addCell(label9);
                //jxl.write.Number num5 = new jxl.write.Number(5, i, myTest.getMarks());
                //sheet.addCell(num5);
                //jxl.write.Number num6 = new jxl.write.Number(6, i, myTest.getNegativeMarks());
                //sheet.addCell(num6);


                i++;
            }
            workbook.write();
            workbook.close();
            dao = null;
            teacher = null;
        }
       catch(Exception e)
     {
        System.out.println("IO Exception caught ");
        }
    }
    
    public Teacher[] read() throws IOException, SQLException  {
        Teacher[] teacher = null;
		//File inputWorkbook = new File("/users/extusr/nishi11/Desktop/Teacher.xls");
		//Workbook w;
		try {
			//w = Workbook.getWorkbook(inputWorkbook);
                    Workbook w = Workbook.getWorkbook(new File("/home/administrator/NetBeansProjects/TEAsE/Teacher.xls"));
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
                              
                              //Number n = (Number) cell;
                              
                              //NumberCell nc=(NumberCell)sheet.getCell(0,1);
                              //NumberCell nc=(NumberCell)sheet.getCell(0,j);
                              System.out.print(cell.getContents() + " ");
                              
                            /*  if (cell.getType() == CellType.DATE)
  219       {
  220         DateTime dt = (DateTime) cell;
  221         Calendar cal = Calendar.getInstance();
  222         cal.set(1998, 1, 18, 11, 23, 28);
  223         Date d = cal.getTime();
  224         dt.setDate(d);
  225       }*/

                              
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
                return teacher;
	}
    /** test the read and write to excel sheet.
     * 
     * @param args
     * @throws IOException
     * @throws SQLException 
     */
     public static void main(String[] args) throws IOException, SQLException {
		Teacherxl teacher = new Teacherxl();
                //teacher.write();
		//teacher.setInputFile("/users/extusr/anusha11/Desktop/Teacher.xls");
		//teacher.read();
                System.out.println("enter 1.write 2.read\n");
        System.out.println("enter ur choice to test method");
        Scanner scan = new Scanner(System.in);
        int i= scan.nextInt();
        switch(i) {
            case 1:teacher.write();
            break;
            case 2:teacher.read();
            break;
            default:
                System.out.println("exit");
        }
    }
}