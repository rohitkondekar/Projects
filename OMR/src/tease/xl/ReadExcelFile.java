/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tease.xl;

/**
 *
 * @author administrator
 */
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcelFile {
   private String inputFile;
    
	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}
        public void read() throws IOException, SQLException  {
		File inputWorkbook = new File("/home/administrator/NetBeansProjects/TEAsE/Test.xls");
		Workbook w;
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			// Get the first sheet
			Sheet sheet = w.getSheet(0);
			
			
                        for (int j = 0; j < sheet.getRows(); j++) {
                         
                             for (int i = 0; i < sheet.getColumns(); i++) {

                                 Cell cell = sheet.getCell(j,i);
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
        public static void main(String[] args) throws IOException, SQLException {
		ReadExcelFile test = new ReadExcelFile();
		test.setInputFile("/home/administrator/NetBeansProjects/TEAsE/Test.xls");
		test.read();
	}

}


