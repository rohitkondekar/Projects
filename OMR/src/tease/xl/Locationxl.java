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
public class Locationxl {
    //declaring class variables
    private String inputFile;
    /**
     * sets the input file
     * 
     * @param inputFile the file input
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
            WritableSheet sheet = workbook.createSheet("Excel sheet for location table",0);
            LocationDAO locationdao = new LocationDAO();
            Location myTest;
            Label label1 = new Label(0,0,"idLocation");
            sheet.addCell(label1);
            Label label2 = new Label(1,0,"locationName");
            sheet.addCell(label2);
            Label label3 = new Label(2,0,"locationStrength");
            sheet.addCell(label3);
            int i=1;
            while(locationdao.hasNext() == true)
            {
                myTest = locationdao.getLocation();
                sheet.setColumnView(0, 15);
                jxl.write.Number num1 = new jxl.write.Number(0,i,myTest.getIdLocation());
                sheet.addCell(num1);
                sheet.setColumnView(1, 15);
                Label label5 = new Label(1,i,myTest.getLocationName());
                sheet.addCell(label5);
                sheet.setColumnView(2, 15);
                jxl.write.Number num2 = new jxl.write.Number(2,i,myTest.getLocationStrength());
                sheet.addCell(num2);
                i++;
            }
            workbook.write();
            workbook.close();
            locationdao = null;
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
            Locationxl location = new Locationxl();
            location.setInputFile("/home/administrator/Desktop/Location.xls");
            System.out.println("enter 1.write 2.read\n");
            System.out.println("enter ur choice to test method");
            try {
            Scanner scan = new Scanner(System.in);
            int i= scan.nextInt();
            switch(i) {
            case 1:location.write();
            break;
            case 2:location.read();
            break;
            default:
                System.out.println("exit");
            }
            }catch(InputMismatchException e) {
                System.out.println("enter a valid number!!!");
            }
        }
    }




