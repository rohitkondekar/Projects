package tease.xl;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nishi11
 */
import tease.bean.*;
import tease.dao.*;
import java.util.*;
import java.io.*;
import java.sql.SQLException;
import jxl.write.*;
import jxl.*;
import jxl.read.biff.BiffException;

public class TestSchedulexl {
    //Declaring class variables
    private String inputFile;
    /**
     * Sets the input file
     * 
     * @param inputFile the file input
     * 
     * @return void.
     */
    
    public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
    }
    /**
     * Write data to the excel sheet.
     * @throws IOException
     * @throws SQLException
     */
        
    public void write() throws IOException, SQLException  {
        try
        {
            WritableWorkbook workbook = Workbook.createWorkbook(new File(inputFile)); 
            WritableSheet sheet = workbook.createSheet("testschedule table",0);
            TestScheduleDAO testScheduledao = new TestScheduleDAO();
            TestSchedule myTest;
            Label label1 = new Label(0,0,"idTest");
            sheet.addCell(label1);
            Label label2 = new Label(1,0,"idGroup");
            sheet.addCell(label2);
            Label label3 = new Label(2,0,"location");
            sheet.addCell(label3);
            Label label4 = new Label(3,0,"startTime");
            sheet.addCell(label4);
            Label label5 = new Label(4,0,"endTime");
            sheet.addCell(label5);
            int i=1;
            while(testScheduledao.hasNext() == true)
            {
                myTest = testScheduledao.getTestSchedule();
                jxl.write.Number num1 = new jxl.write.Number(0,i,myTest.getIdTest());
                sheet.addCell(num1);
                jxl.write.Number num2 = new jxl.write.Number(1,i,myTest.getIdGroup());
                sheet.addCell(num2);
                Label label6 = new Label(2,i,myTest.getLocation());
                sheet.addCell(label6);
                jxl.write.DateTime dateCell = new jxl.write.DateTime(3,i,myTest.getStartTime());
                sheet.addCell(dateCell);
                jxl.write.DateTime datetime = new jxl.write.DateTime(4,i,myTest.getEndTime());
                sheet.addCell(datetime);
                i++;
            }
            workbook.write();
            workbook.close();
            testScheduledao = null;
            myTest = null;
        }
        catch(Exception e)
        {
        System.out.println("IO Exception caught ");
        }
    }
    /**
     * Read the data from the excel sheet.
     * @throws IOException
     * @throws SQLException
     */
    
    public void read() throws IOException, SQLException  {
       try {
		Workbook w = Workbook.getWorkbook(new File(inputFile));
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
    /**
     * test the read and write to excel sheet.
     * @param args
     * @throws IOException
     * @throws SQLException
     */
    
    public static void main(String[] args) throws IOException, SQLException {
            TestSchedulexl testSchedule = new TestSchedulexl();
            testSchedule.setInputFile("/home/administrator/Desktop/TestSchedule.xls");
            System.out.println("enter 1.write 2.read\n");
            System.out.println("enter ur choice to test method");
            try {
            Scanner scan = new Scanner(System.in);
            int i= scan.nextInt();
            switch(i) {
            case 1:testSchedule.write();
            break;
            case 2:testSchedule.read();
            break;
            default:
                System.out.println("exit");
            }
            }catch(InputMismatchException e) {
                System.out.println("enter a valid number");
            }
        }
    }



