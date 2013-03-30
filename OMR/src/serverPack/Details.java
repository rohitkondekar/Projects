package serverPack;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import tease.bean.*;
import tease.dao.*;
import tease.helper.*;
import java.util.*;

public class Details
{
    
    String folder,filename;
    int testId; 
    serverGUI server;
    
    public Details(String folder,String filename,String testid,serverGUI server)
    {
        this.folder = folder;
        this.filename = filename;
        this.testId = Integer.parseInt(testid);
        this.server = server;
    }  

    public void writeDetails()
    {
        server.writeStatusDetails("Initiating Fetch..\n");
        TestDetailsDAO tdDAO = new TestDetailsDAO();
        TestDetails [] testDetails;
        server.writeStatusDetails("Accessing Database..\n");
        testDetails = tdDAO.getTestDetailsByParameter(testId,null,null,null,null,null,null);

        try
        {
            if(testDetails == null)
            {
                server.writeStatusDetails("Not found the test\n");
                throw new Exception();
            }
            else
            {
                TestDAO testDAO = new TestDAO();
                Test[] test;
                test = testDAO.getTestByParameter(testId, null, null, null, null, null);

                if(test == null)
                {
                    server.writeStatusDetails("Test id non existent\n");
                    throw new Exception("");
                }
                File file = new File(filename);
                File fold = new File(folder);
                fold.mkdirs();
                if(file.exists())
                {
                    file.delete();
                    file.createNewFile();
                }
                else
                {
                    file.createNewFile();
                }

                FileWriter out = new FileWriter(file);
                out.write(testDetails.length + ",");
                out.write(test[0].getDuration() + ",");
                out.write(test[0].getMarks()+ ",");

                for(int i=0;i< testDetails.length;++i)
                {
                    out.write(testDetails[i].getQuestionType() + ",");
                    out.write(testDetails[i].getQuestionOptions() + ",");
                    out.write(testDetails[i].getMarks()+ ",");
                }
                out.write("END");
                out.close();
                server.writeStatusDetails("Fetch Completed Successfully..\n");
            }        
        }
        catch(Exception e)
        {
            server.writeStatusDetails("Error Fetching file!!\n");
        }
    }
}
