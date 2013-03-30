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

public class TestDetailsxl {
    //Declaring class variables.
    private String inputFile;
    /**
     * Sets the input file
     * 
     * @param inputFile the file input.
     * 
     * @return void.
     */
    
    public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
    }
    /**
     * write the data to the excel sheet.
     * @throws IOException
     * @throws SQLException
     */
        
    public void write() throws IOException, SQLException  {
        try
        {
            WritableWorkbook workbook = Workbook.createWorkbook(new File(inputFile)); 
            WritableSheet sheet = workbook.createSheet("testdetails table",0);
            TestDetailsDAO testDetailsdao = new TestDetailsDAO();
            TestDetails myTest;
            Label label1 = new Label(0,0,"idTest");
            sheet.addCell(label1);
            Label label2 = new Label(1,0,"sequence");
            sheet.addCell(label2);
            Label label3 = new Label(2,0,"questionType");
            sheet.addCell(label3);
            Label label4 = new Label(3,0,"questionOptions");
            sheet.addCell(label4);
            Label label5 = new Label(4,0,"correctAnswer");
            sheet.addCell(label5);
            Label label6 = new Label(5,0,"marks");
            sheet.addCell(label6);
            Label label7 = new Label(6,0,"negativeMarks");
            sheet.addCell(label7);
            int i=1;
            while(testDetailsdao.hasNext() == true)
            {
                myTest = testDetailsdao.getTestDetails();
                jxl.write.Number num1 = new jxl.write.Number(0,i,myTest.getIdTest());
                sheet.addCell(num1);
                jxl.write.Number num2 = new jxl.write.Number(1,i,myTest.getSequence());
                sheet.addCell(num2);
                jxl.write.Number num3 = new jxl.write.Number(2,i,myTest.getQuestionType());
                sheet.addCell(num3);
                jxl.write.Number num4 = new jxl.write.Number(3,i,myTest.getQuestionOptions());
                sheet.addCell(num4);
                Label label8 = new Label(4,i,myTest.getCorrectAnswer());
                sheet.addCell(label8);
                jxl.write.Number num5 = new jxl.write.Number(5, i, myTest.getMarks());
                sheet.addCell(num5);
                jxl.write.Number num6 = new jxl.write.Number(6, i, myTest.getNegativeMarks());
                sheet.addCell(num6);
                i++;
            }
            workbook.write();
            workbook.close();
            testDetailsdao = null;
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
     * test the read and write to the excel sheet.
     * @param args
     * @throws IOException
     * @throws SQLException
     */
        
    public static void main(String[] args) throws IOException, SQLException {
            TestDetailsxl testDetails = new TestDetailsxl();
            testDetails.setInputFile("/home/administrator/Desktop/TestDetails.xls");
            System.out.println("enter 1.write 2.read\n");
            System.out.println("enter ur choice to test method");
            try {
            Scanner scan = new Scanner(System.in);
            int i= scan.nextInt();
            switch(i) {
            case 1:testDetails.write();
            break;
            case 2:testDetails.read();
            break;
            default:
                System.out.println("exit");
            }
            }catch(InputMismatchException e) {
                System.out.println("enter a valid Number !!!");
                
            }
        }
    }



