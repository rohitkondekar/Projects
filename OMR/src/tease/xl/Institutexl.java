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

public class Institutexl {
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
            WritableSheet sheet = workbook.createSheet("Excel sheet for institute table",0);
            InstituteDAO institutedao = new InstituteDAO();
            Institute myTest;
            Label label = new Label(0,0,"idInstitute");
            sheet.addCell(label);
            Label label1 = new Label(1,0,"instituteName");
            sheet.addCell(label1);
            Label label2 = new Label(2,0,"motto");
            sheet.addCell(label2);
            Label label3 = new Label(3,0,"logo");
            sheet.addCell(label3);
            Label label4 = new Label(4,0,"email");
            sheet.addCell(label4);
            Label label5 = new Label(5,0,"levelOfEducation");
            sheet.addCell(label5);
            Label label6 = new Label(6,0,"instituteDescription");
            sheet.addCell(label6);
            int i=1;
            while(institutedao.hasNext() == true)
            {
                myTest = institutedao.getInstitute();
                sheet.setColumnView(0, 15);
                jxl.write.Number num1 = new jxl.write.Number(0,i,myTest.getIdInstitute());
                sheet.addCell(num1);
                sheet.setColumnView(1, 15);
                Label label7 = new Label(1,i,myTest.getInstituteName());
                sheet.addCell(label7);
                sheet.setColumnView(2, 15);
                Label label8 = new Label(2,i,myTest.getMotto());
                sheet.addCell(label8);
                sheet.setColumnView(3, 15);
                Label label9 = new Label(3,i,myTest.getLogo());
                sheet.addCell(label9);
                sheet.setColumnView(4, 15);
                Label label10 = new Label(4,i,myTest.getEmail());
                sheet.addCell(label10);
                sheet.setColumnView(5, 15);
                Label label11 = new Label(5,i,myTest.getLevelOfEducation());
                sheet.addCell(label11);
                sheet.setColumnView(6, 15);
                Label label12 = new Label(6,i,myTest.getInstituteDescription());
                sheet.addCell(label12);
                i++;
            }
            workbook.write();
            workbook.close();
            institutedao = null;
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
            Institutexl institute = new Institutexl();
            institute.setInputFile("/home/administrator/Desktop/Institute.xls");
            System.out.println("enter 1.write 2.read\n");
            System.out.println("enter ur choice to test method");
            try {
            Scanner scan = new Scanner(System.in);
            int i= scan.nextInt();
            switch(i) {
            case 1:institute.write();
            break;
            case 2:institute.read();
            break;
            default:
                System.out.println("exit");
            }
            }catch(InputMismatchException e) {
                System.out.println("enter a valid number!!!");
            }
        }
    }


