package Ans_module;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.io.file.FileSystemRegistry;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Form;
import javax.obex.*;


/* called when a bluetooth connection is required to be created with the server*/
public class Bluetooth
{

    private Display display;                                /* Display */
    private Form infoArea;                                  /* Info Area form*/
    String fileURL;                                         /* Url of file */
    String rollNo;                                          /* student roll no*/
    RemoteDevice remotedevice;                              /* Remote Device*/
    ClientSession con;                                      /* Connection session of client*/
    HeaderSet hdr;                                          /* Headerset object*/
    String url;                                             /* sevre url*/
    String startUid="990";                                  /* Connection uid*/
    private Command exit;
    TeaseApp teaseapp;
    
    /* bluetooth constructor called when an object of this class
       is created in TeaseApp*/
    public Bluetooth(TeaseApp teaseapp)
    {
        this.teaseapp = teaseapp;
        this.display = teaseapp.getDisplay();  
        this.infoArea = teaseapp.getInfoArea();
        this.exit=teaseapp.exit;
    }

    /* set studnets roll no*/
    public void setRoll(String roll)
    {
        this.rollNo = roll;
    }

    /* return file url */
    public String getURL()
    {
        return fileURL;
    }

    /* clear and display info area*/
    public void setInfoArea()
    {
        infoArea.deleteAll();
        display.setCurrent(infoArea);
    }

    /* start input connection*/
    public void startInputConnection()
	{
        setInfoArea();
		try
		{
            fileURL = getRoot();
			log("Searching for server.. ");
			UUID uuid = new UUID(startUid+"11111111111111111111111111122",false);
            while(url == null)
            {
                url = getAgent().selectService(uuid, ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
                Thread.sleep(4000);
            }
            handleInputConnection();
        }
		catch (Exception e)
		{
			log(e.getMessage());
			log("Device not found");
		}
	}

    /* */
    public void displays(String s)
    {
        log(s);
    }

    /* Connect to server and request for input file */
    private void handleInputConnection()
    {
        setInfoArea();
        log("Opening a connection with the server....\n");
        try
        {
            LocalDevice localDevice = LocalDevice.getLocalDevice();
            HeaderSet hdrsess;
            FileConnection fileconnection = null;
            while(true)
            {
                try
                {
                    con = (ClientSession)Connector.open(url);
                    hdrsess = con.connect(hdr);
                    break;
                }
                catch(Exception e){}               
            }
 
            
            if (hdrsess.getResponseCode() != ResponseCodes.OBEX_HTTP_OK)
            {
                log("Failed to connect");
                return;
            } 
            
            while(true)
            {
                try
                {
                    fileconnection = (FileConnection)Connector.open(fileURL+"data.txt",Connector.READ_WRITE);
                    break;
                }
                catch(Exception e){}
            }
            teaseapp.setInFileConn(fileconnection);
            
            hdr = con.createHeaderSet();
            hdr.setHeader(HeaderSet.NAME,getFriendlyName(localDevice));
            hdr.setHeader(HeaderSet.DESCRIPTION,rollNo);
            Operation op = con.get(hdr);
            
            DataInputStream in = op.openDataInputStream();

            if(!fileconnection.exists())
            {
                fileconnection.create();
                //log("File created\n");
            }
            else
            {
                //log("File exists");
                fileconnection.delete();
                fileconnection.create();
            }
            
            DataOutputStream out = fileconnection.openDataOutputStream();         

            int data = in.read();
            
            if(data == -1) teaseapp.exitMidlet();
            if(data == 254) teaseapp.exitMidlet();

            while(data!=-1)
            {
                out.write(data);
                data = in.read();
            }
            
            in.close();
            out.flush();
            out.close();
            int responseCode = op.getResponseCode();
            op.close();
            con.close();
            if (hdrsess.getResponseCode() != ResponseCodes.OBEX_HTTP_OK)
            {
                log("Failed to disconnect\n");
                return;
            }
            //con.disconnect(null);
        }
        catch(Exception e)
        {
            log("Error: In recieving file!!\n");
        }
    }
    
    /* connect to server and submit answers*/
    public void handleOutputConnection()
    {
        setInfoArea();
        infoArea.append("Sending Answer Sheet to Server Please Hold...");
        HeaderSet hdrsess;
        FileConnection fileconnection = teaseapp.getOutFileConn();
        
        try
        {
            while(true)
            {
                try
                {
                    con = (ClientSession)Connector.open(url);
                    hdrsess = con.connect(hdr);
                    break;
                }
                catch(Exception e){}               
            }
           
            if (hdrsess.getResponseCode() != ResponseCodes.OBEX_HTTP_OK)
            {
                log("Failed to connect\n");
                return;
            }
   
            log("Connection Obtained.. \n");
            
            /*while(true)
            {
                try
                {
                    fileconnection = (FileConnection)Connector.open(getURL()+"output.txt",Connector.READ_WRITE);
                    break;
                }
                catch(Exception e){}
            }*/
            
            InputStream in = fileconnection.openInputStream();
            
            hdr = con.createHeaderSet();
            hdr.setHeader(HeaderSet.NAME,rollNo+".txt");
            Operation op = con.put(hdr);
          
            OutputStream out = op.openOutputStream();
          
            int data = in.read();
            if(data==-1)log("No Data\n");
            
            while(data!=-1)
            {
                out.write(data);
                data = in.read();
            }
                       
            in.close();
            out.flush();
            out.close();
           
            op.close();
            
            cleanInfoArea();
            log("Answer Sheet sent to Server");
            hdr.setHeader(HeaderSet.NAME, "getResult123");
            op = con.get(hdr);
           
            DataInputStream inn = op.openDataInputStream();
            int j;
           
            
            cleanInfoArea();
            log("You scored: "+inn.readFloat());
            
            inn.close();
            op.close();

            con.disconnect(null);
            con.close();
           
        }
        catch(Exception e)
        {
            log("Error: In Sending file!!\n");
        }

            
            infoArea.addCommand(exit);
            
            infoArea.setCommandListener(teaseapp);
            display.setCurrent(infoArea);
           
    }

    /* desconnect connection with the server*/
    public void disconnect()
    {
        try
        {
            con.disconnect(null);
            con.close();
        }
        catch(Exception e){}
    }

    /* get root directly of mobile*/
    private String getRoot() 
    {

        String url = System.getProperty("fileconn.dir.private");
        // Otherwise get the first root.
        if (url == null) 
        {
            Enumeration e = FileSystemRegistry.listRoots();
            if (e.hasMoreElements())
            {
                String root = (String)e.nextElement();
                url = "file:///" + root;
            }
        }
        return url;
    }

    /* Append messages in info area*/
    private void log(String msg)
    {
		infoArea.append(msg);
		infoArea.append("\n\n");
    }
    
    private void cleanInfoArea()
    {
        infoArea.deleteAll();
    }

    /* get name of a device*/
    private String getFriendlyName(LocalDevice btDevice)
	{
		try
		{
			String s = btDevice.getFriendlyName();
            if(s!=null)
                return s;
            else
                return btDevice.getBluetoothAddress();
		}
		catch (Exception e)
		{
			return "no name available";
		}
	}

    /* return discovery agent of local device*/
    private DiscoveryAgent getAgent()
    {
        try
        {
            return LocalDevice.getLocalDevice().getDiscoveryAgent();
        }
        catch (BluetoothStateException e)
        {
            throw new Error(e.getMessage());
        }
	}

    
}