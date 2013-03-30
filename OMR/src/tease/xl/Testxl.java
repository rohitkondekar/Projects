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

public class Testxl {
    public static void main(String [] args)
    {
        try
        {// FileStrem

            WritableWorkbook workbook = Workbook.createWorkbook(new File ("/home/administrator/NetBeansProjects/TEAsE/Test.xls"));
            //WritableSheet sheet = workbook.createSheet(“First Sheet”,0);
            WritableSheet sheet = workbook.createSheet("Test table",0);
            Label label = new Label(0,0,"idTest");
            sheet.addCell(label);
            Label label2 = new Label(1,0,"idTopic");
            sheet.addCell(label2);
            Label label3 = new Label(2,0,"marks");
            sheet.addCell(label3);
            Label label4 = new Label(3,0,"duration");
            sheet.addCell(label4);
            Label label5 = new Label(4,0,"displayName");
            sheet.addCell(label5);
            Label label6 = new Label(5,0,"fullDescription");
            sheet.addCell(label6);

            TestDAO testdao = new TestDAO();
            Test myTest;
            int i=1;
            while(testdao.hasNext() == true)
            {

                myTest = testdao.getTest();
                jxl.write.Number num1 = new jxl.write.Number(0,i,myTest.getIdTest());
                sheet.addCell(num1);
                jxl.write.Number num2 = new jxl.write.Number(1,i,myTest.getIdTopic());
                sheet.addCell(num2);
                WritableCellFormat floatFormat = new WritableCellFormat (NumberFormats.FLOAT);
                jxl.write.Number num3 = new jxl.write.Number(2,i,myTest.getMarks());
                sheet.addCell(num3);
                jxl.write.Number num4 = new jxl.write.Number(3,i,myTest.getDuration());;
                sheet.addCell(num4);
                Label label1 = new Label(4,i,myTest.getDisplayName());
                sheet.addCell(label1);
                Label label8 = new Label(5,i,myTest.getFullDescription());
                sheet.addCell(label8);


                i++;
            }
            workbook.write();
            workbook.close();
            testdao = null;
            myTest = null;
        }
       catch(Exception e)
     {
        System.out.println("IO Exception caught ");
        }


    }
}