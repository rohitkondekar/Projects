package Ans_module;         

import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.midlet.*;
import java.io.IOException;
import net.mypapit.java.StringTokenizer;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.lcdui.*;

/*Tease App class is the main midlet class which initiates
 all the other classes and canvas.
 It starts executing with start app method and is called when user starts application
 It terminates when user exits.*/

public class TeaseApp extends MIDlet implements CommandListener
{
    private Display display;                                      /* Used to set current display */
    private OMR omr;                                              /* Drawing Canvas */
    private Bluetooth bluetooth;                                  /* object of Bluetooth class*/
    private Question[] question;                                  /* Questions */
    private Color color;                                          /* object of Color class*/
    private String data;                                          /* Data read from file */
    private String StartUid="990";                                /* initial value of uuid */
    private Form submitForm;                                      /* Form appears when submit is pressed */
    private Form startForm;                                       /* Form appears when application starts */
    private Form infoArea;                                        /* From diplaying all details about connection with server */
    private Alert alert;                                          /* Alert Message */
    private Alert timeupAlert;                                    /* Alert when time is over */
    private TextBox rollno;                                       /* text box asking for student's roll no */
    private TextBox Uuid;                                         /* Text Box asking connection uuid */
    Command exit;                                                 /* Exit command for error case */
    private Command no;                                           /* to cancel submit */
    private Command submitYes;                                    /* confirm submit */
    private Command startconnection;                              /* connect to server command */
    private Command changeuuid;                                   /* command to change connection uuid */
    private Command change;                                       /* confirm change in uuid */
    private Command timeOk;                                       /* command to close timeup alert */
    private Command rollOk;                                       /* command to accept Students roll no*/
    private Thread con,con1;                                      /* connection threads */
    private Image image = null;                                   /* Students image */
    private int totalQuestions;                                   /* total number of Questions */
    private int duration;                                         /* total Duration */
    private float totalMarks;                                     /* total Marks */
    private FileConnection infileconnection;
    private FileConnection outfileconnection;
   
   /* This function is call in start app i.e in the beginning.
    It initializes forms and commands and add action listener to them  */

    private void setApp()                                         
    {
        startForm = new Form("");                                 /* initializing start form*/
        startForm.append("Tease App, press Start to proceed or UUID to change the server code");
        startconnection = new Command("START",Command.OK,1);
        changeuuid = new Command("UUID",Command.SCREEN,1);
        /* Add commands to start form */
        startForm.addCommand(startconnection);
        startForm.addCommand(changeuuid);
        /* Add command listener to start form */
        startForm.setCommandListener(this);
        
        /* initialixe text box to input Uid */
        Uuid=new TextBox("Enter 3 digit UUID","",3 ,TextField.NUMERIC);
        change=new Command("Change",Command.OK,1);
        Uuid.addCommand(change);
        Uuid.setCommandListener(this);

        /* initialize roll no input text box */
        rollno = new TextBox("Enter your roll no", "", 10, TextField.ANY);
        rollOk = new Command("Ok",Command.OK,1);
        rollno.addCommand(rollOk);
        rollno.setCommandListener(this);

        /* Initialize submit form */
        submitForm = new Form("Submit");
        submitForm.append("Do you want to submit? Press Yes to Process No to go back.");
        submitYes = new Command("Yes",Command.SCREEN,1);
        no = new Command("No",Command.SCREEN,1);
        submitForm.addCommand(no);
        submitForm.addCommand(submitYes);
        submitForm.setCommandListener(this);

        /*initialize info area*/
        infoArea = new Form("Bluetooth Client");
        exit = new Command("Exit",Command.EXIT,1);
        //infoArea.setCommandListener(this);

        /* Initialize time up alert */
        timeOk = new Command("Ok",Command.SCREEN,1);
        timeupAlert = new Alert("Attention","TIME UP", null, AlertType.WARNING);
        timeupAlert.addCommand(timeOk);
        timeupAlert.setCommandListener(this);
        timeupAlert.setTimeout(Alert.FOREVER);

        color=new Color();
    }

    
    /* Read from Input File.
       Called when a file is recieved from the server
       Data read is fed into Question class which paints
       omr question by question */

    private void readFile()
    {
        try
        {
            int chars;
            /* Open file*/
/*            while(true)
            {
                try
                {
                    infileconnection = (FileConnection)Connector.open(bluetooth.getURL()+"data.txt",Connector.READ_WRITE);
                    break;
                }
                catch(Exception e){}
            }*/
            
            DataInputStream inread = infileconnection.openDataInputStream();

            StringBuffer sb = new StringBuffer();
            chars = inread.read();
            
            while(chars!=-1)
            {
                sb.append((char) chars);
                chars = inread.read();
            }
            data = sb.toString();

            if(data == null)throw new Exception();
        }
        catch(Exception e)
        {
            exceptionAlert(e);
        }
    }
 
    /* Construct Question Array
       Called after read file */

    private void constructQuestion() throws Exception
    {       
        StringTokenizer strtok = new StringTokenizer(data,",");
        if(!strtok.hasMoreTokens()) throw new Exception();
        
        int qType;                                                 /* question type */
        int qQualifier;                                            /* number of options */
        float qMarks;                                              /* marks alloted for that Question */
        int ind;                                                   /* temprary index variable */
        String qOptionsChars = null;                               /* Option characters */
              
        while(strtok.hasMoreTokens())
        {
            totalQuestions = Integer.parseInt(strtok.nextToken());
            duration = Integer.parseInt(strtok.nextToken());
            totalMarks = Float.parseFloat(strtok.nextToken());
            if(strtok.peek().length()!=1)
                    qOptionsChars = strtok.nextToken();
            break;
        }
                                                                  
        question = new Question[totalQuestions];                  /* allocate memory to question object */
        ind=0;
        
        while(strtok.hasMoreTokens()) 
        {
            if(strtok.peek().startsWith("E"))break;
            qType = Integer.parseInt(strtok.nextToken());
            qQualifier = Integer.parseInt(strtok.nextToken());
            qMarks = Float.parseFloat(strtok.nextToken());
            question[ind] = new Question(ind+1,qType,qQualifier,qMarks,color);
            question[ind].setOptionCharacters(qOptionsChars);
            ind++;           
        }  
        
    }


    /* pick students image called while loding image  */
    
    private void getImage() throws IOException
    {
        image = Image.createImage("/Ans_module/logo.jpg");        
    }
    
    public Display getDisplay()
    {
        return display;
    }
    
    public Form getInfoArea()
    {
        return infoArea;
    }
    
    public void setInFileConn(FileConnection fconn)
    {
        this.infileconnection = fconn;
    }
           
    public FileConnection getOutFileConn()
    {
        return outfileconnection;
    }
    /* Alert appers if there is error in reading file and omr sheet can not be generated*/

    private void exceptionAlert(Exception e)        
    {
        bluetooth.disconnect();                                            /* disconnect bluetooth connection with server*/
        alert = new Alert("Error: ",e.getMessage(), null, AlertType.ERROR);   /* alert initialization */
        alert.setTimeout(Alert.FOREVER);
        exit = new Command("Exit",Command.EXIT,1);
        alert.addCommand(exit);
        alert.setCommandListener(this);
        display.setCurrent(alert);             
    }
    
    /* called when application starts executing */
    public void startApp()
    {
        display = Display.getDisplay(this);                                 /* Get the display */
        setApp();
        display.setCurrent(startForm);                                      /* Set the display to start form */
    }

    /* function is called when user clicks on start connection button
       It creats a bluttoh nobex connection with the server*/
    
    public void connectToServer()
    {
         bluetooth = new Bluetooth(this);
         bluetooth.setRoll(rollno.getString().toLowerCase());
         con=new Thread()
         {
             public void run()
             {                
                bluetooth.startUid=StartUid;
                bluetooth.startInputConnection();
                try
                {
                    readFile();
                    constructQuestion();
                    getImage();
                    /* call omr constructor */
                    omr = new OMR(display,question,image,submitForm,timeupAlert,duration);
                    /* to input roll no before displaying omr sheet*/
                    display.setCurrent(omr);
                }
                catch(Exception e)
                {
                   exceptionAlert(e);
                }
             }
         };
         con.start();
    }
    
   /* This function is called when a command is selected */
    public void commandAction(Command c, Displayable d)
    {
        if(c==exit)
        {
            exitMidlet();
        }
        else if(c == submitYes || c == timeOk)
        {
            omr.stopTimer();
            infoArea.deleteAll();
            display.setCurrent(infoArea);
            infoArea.append("Sending Answer Sheet to Server Please Hold...");
            display.vibrate(400);
            writeOutput();
            con1=new Thread()
            {
                public void run()
                {
                    bluetooth.handleOutputConnection();
                }
            };
            con1.start();
        }
        else if(c == no)
            display.setCurrent(omr);
        else if(c==startconnection)
        {    //connectToServer();
            display.setCurrent(rollno);
        }
        else if(c==changeuuid)
        {
           display.setCurrent(Uuid);
        }
        else if(c == change)
        {
           if(Uuid.getString().length()==3)
           {
               StartUid=Uuid.getString();
               display.setCurrent(startForm);
           }          
        }
        else if(c == rollOk)
        {
            if(rollno.getString().length()>0)
            {
                connectToServer();
            }
        }
                  
    }    

    /* function is automatically called When a midlet is paused */
    public void pauseApp()
    {
        writeOutput();
        bluetooth.handleOutputConnection();
        destroyApp(true);
    }
    
    /* exit form midlet */
    public void exitMidlet()
    {
        destroyApp(true);
    }   

    /* destroy midlet*/
    public void destroyApp(boolean unconditional)
    {        
        notifyDestroyed();
    }
    
    /* called when exam is over and students submit anssheet answersheet is send to server*/
    private void writeOutput()
    {
        int count = 0,optionMarked;
        
        try
        {
            while(true)
            {
                try
                {
                     outfileconnection = (FileConnection)Connector.open(bluetooth.getURL()+"output.txt",Connector.READ_WRITE);
                    break;
                }
                catch(Exception e){}
            }

            if(!outfileconnection.exists())
                outfileconnection.create();
            else
            {               
                outfileconnection.delete();
                outfileconnection.create();
            }
            
            DataOutputStream out = outfileconnection.openDataOutputStream();
            out.writeChars(rollno.getString()+";");
            
            for(int i=0;i<question.length;i++)
            {
                optionMarked = question[i].getNumMarkedOption();
                count=0;
                for(int j=0;j<question[i].getqQualifier();j++)
                {
                    if(question[i].getIfMarkedIndex(j))
                    {
                        count++;
                        out.writeChars(question[i].getOptionChar(j)+"");
                        if(count < optionMarked && j<question[i].getqQualifier()-1)
                            out.writeChars(",");
                    }
                }
                if(!question[i].getIfMarked())
                    out.writeChars(" ");
                out.writeChars(";");
            }
            out.flush();
            out.close();            
        }
        catch(Exception e)
        {
            exceptionAlert(e);
        }
    }
}
