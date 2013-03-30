package tease.xl;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nishi11
 */
import jxl.read.biff.BiffException;
import tease.bean.*;
import tease.dao.*;
import java.util.*;
import java.io.*;
import java.sql.SQLException;
import jxl.write.*;
import jxl.*;

public class TestElegibilityxl {
    //Declaring class variables.
    private String inputFile;
    /**
     * Sets the input file
     * 
     * @param inputFile the file input.
     * 
     * @return void.
     * 
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
            WritableSheet sheet = workbook.createSheet("testelegibility table",0);
            TestElegibilityDAO testElegibilitydao = new TestElegibilityDAO();
            TestElegibility myTest;
            Label label = new Label(0,0,"idTest");
            sheet.addCell(label);
            Label label2 = new Label(1,0,"idGroup");
            sheet.addCell(label2);
            Label label3 = new Label(2,0,"status");
            sheet.addCell(label3);
            int i=1;
            while(testElegibilitydao.hasNext() == true)
            {
                myTest = testElegibilitydao.getTestElegibility();
                jxl.write.Number num1 = new jxl.write.Number(0,i,myTest.getIdTest());
                sheet.addCell(num1);
                jxl.write.Number num2 = new jxl.write.Number(1,i,myTest.getIdGroup());
                sheet.addCell(num2);
                jxl.write.Number num3 = new jxl.write.Number(2,i,myTest.getStatus());
                sheet.addCell(num3);
                i++;
            }
            workbook.write();
            workbook.close();
            testElegibilitydao = null;
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
    
    public void read() throws IOException, SQLException, BiffException  {
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
    
        public static void main(String[] args) throws IOException, SQLException, BiffException {
            TestElegibilityxl testElegibility = new TestElegibilityxl();
            testElegibility.setInputFile("/home/administrator/Desktop/TestElegibility.xls");
            System.out.println("enter 1.write 2.read\n");
            System.out.println("enter ur choice to test method");
            try {
            Scanner scan = new Scanner(System.in);
            int i= scan.nextInt();
            switch(i) {
            case 1:testElegibility.write();
            break;
            case 2:testElegibility.read();
            break;
            default:
                System.out.println("exit");
            }
            }catch(InputMismatchException e) {
                System.out.println("enter a valid number!!!");
            }
        }
    }



