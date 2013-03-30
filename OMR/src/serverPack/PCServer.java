package serverPack;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.InputConnection;

import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import javax.obex.HeaderSet;
import javax.obex.Operation;
import javax.obex.ResponseCodes;
import javax.obex.ServerRequestHandler;
import javax.obex.SessionNotifier;


public class PCServer extends ServerRequestHandler 
{
	
    private String fileName;
    private String testId;
    private String folderName;
    private String outputFile;
    private float resultMarks;
    private serverGUI serverGUI;
    SessionNotifier notifier;
    private String UUID;
    
    
    public PCServer(serverGUI server)
    {
        this.serverGUI = server;
    }
    
    public void changeUUID(String uuid)
    {
        UUID = new UUID(uuid+"11111111111111111111111111122", false).toString();
    }
    
    public void printServerProperties()
    {
        try
        {
            LocalDevice device = LocalDevice.getLocalDevice();
            logServer("Name: "+device.getFriendlyName()+"\n");
            logServer("Address: "+device.getBluetoothAddress()+"\n");
            logServer("Device Class: "+device.getDeviceClass()+"\n");
        }
        catch (Exception e)
        {
			log(e.toString());
		}
		
    }   
    
	public void startConnection(String folderName,String testId)
    {
		try 
        {
            this.folderName = folderName;
            this.testId = testId;
            this.fileName = folderName+testId+".txt";
            
			LocalDevice device = LocalDevice.getLocalDevice();
			device.setDiscoverable(DiscoveryAgent.GIAC);
			String url = "btgoep://localhost:" + UUID + ";name=PCServer;authenticate=false;master=false;encrypt=false";
			log("Server Creation Started...");
			notifier = (SessionNotifier) Connector.open(url);
            serverLoop();           
		}
        catch (Exception e)
        {
		}
	}

    public void stopServer() 
    {
        try {
            notifier.close();
        } catch (IOException ex) {
            log("Error Stop");
        }
    }
    
	private void serverLoop() throws IOException
    {
        while(true)
        {
            log("Waiting for connection...");
            notifier.acceptAndOpen(this);
            log("A Client now connected.... ");
        }
	}
    
    @Override
    public int onConnect(HeaderSet request, HeaderSet reply)
    {
        log("A OBEX connection has received....");
        return ResponseCodes.OBEX_HTTP_OK;
    }
    
    @Override
    public int onGet(Operation op)
    {
        DataOutputStream out = null;
        FileInputStream stream = null;
        String url="";
        try
        {
            log("Received a GET request from client....");
            HeaderSet hdr = op.getReceivedHeaders();
            if((hdr.getHeader(HeaderSet.NAME).toString()).equals("getResult123"))
            {
               log("processing result");
               DataOutputStream d=op.openDataOutputStream();
               d.writeFloat(resultMarks);
               d.close();
               op.close();
            }
            else
            {
                String roll = hdr.getHeader(HeaderSet.DESCRIPTION).toString();
                File chckfile = new File(folderName+"Result/"+roll+".txt");

                File file = new File(fileName);
                stream = new FileInputStream(file);

                byte[] fileContent = new byte[stream.available()];
                stream.read(fileContent);
                stream.close();

                out = op.openDataOutputStream();
                if(!chckfile.exists())
                {
                    logDevice(hdr.getHeader(HeaderSet.NAME).toString()+"\n");
                    out.write(fileContent);
                }
                else
                {
                   out.write(-2);
                }
                log("File written back to client....\n");
                out.flush();
                out.close();
                op.close();
            }
                                    
        }
        catch(Exception e)
        { }
        return ResponseCodes.OBEX_HTTP_OK;
    }
    
    @Override
    public int onPut(Operation op)
    {
        FileWriter stream = null; 
        try
        {
            log("Received a PUT request from client....\n");
            HeaderSet hs = op.getReceivedHeaders();
            outputFile = (String) hs.getHeader(HeaderSet.NAME);
            
            InputStream in = op.openInputStream();
           
            File file = new File(folderName+"Result/"+outputFile);
            File fold = new File(folderName+"Result/");
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
            stream = new FileWriter(file);
            int data = in.read();
            if(data==-1)log("No Data -1 \n");
            
            while(data!=-1)
            {
                if(data!=0)
                {
                    stream.write(data);
                }
                data = in.read();
            }
            stream.flush();
            stream.close();           
            in.close();         
            op.close();
            
            resultMarks = startResultEvaluation(outputFile,file);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return ResponseCodes.OBEX_HTTP_OK;

    }
    
    
    public float startResultEvaluation(String name,File file)
    {
        name.replace(".txt","");
        checkResult result = new checkResult(serverGUI);
        return result.evaluateAnswers(file,testId,name);        
    }
    
    public boolean checkFile(String fileName)
    {
		try
        {
            File f = new File(fileName);
            if(!f.exists())
                return false;
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    
    void log(String s)
    {
        serverGUI.writeStatusDetails(s+"\n");
    }
    
    void logServer(String s)
    {
        serverGUI.writeServerDetails(s);
    }
    
    void logDevice(String s)
    {
        serverGUI.writeSentDetails(s);
    }
}
