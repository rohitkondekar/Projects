package Ans_module;

import javax.microedition.lcdui.*;
import java.util.*;

/*called by Tease App midlet. This class draws omr sheet*/

public class OMR extends Canvas implements CommandListener
{
                                                          
    private Display display;                              /* to access OMR display */
    private Question question[];                          /* to access OMR Questions */
    private Image image;                                  /* Student Image */
    private int canvasHeight;                             /* Canvas Height */
    private int canvasWidth;                              /* Canvas Width */
    private int rowsPerScreen;                            /* total number of printable rows per screen */
    private int circlesPerRow;                            /* Circles per row */
    private final int rowHeight = 30;                     /* row height */
    private final int circleWidth = 20;                   /* Width of one circle */
    private final int spacing = 40;                       /* Spacing between two Circles */
    private int xyDisp[];                                 /* X and Y Displacement */
    private int startIndex;                               /* question no. of first question on the canvas */
    private int presentIndex;                             /* question no. of present highlighted question */
    private int lastIndex;                                /* question no. of the last printer Q on the canvas */
    private int filterStartIndex;                         /* index of first question in filter mode */
    private int filterEndIndex;                           /* last index of the filtered question */
    private int remaingQuestions;                         /* no. of remaing Q */
    private int totalFilterQ;                             /* Total Question in presnt filter*/
    private boolean starPressed;                          /* star key pressed */
    private boolean printPhoto;                           /* checks if photo is to be printed*/
    private int jumpQuestion;                             /* jump question number */
    private int scrollY;                                  /* y index of scrolling arc of scroll bar*/
    private ChoiceGroup filterType;                       /* 1-UnAns 2-Ans */
    private ChoiceGroup themeType;                        /* select theme*/
    private int cmode;                                    /* theme mode*/
    private int filterChoosen;                            /* filter choosen*/
    private Alert timeupAlert;                            /* time up Alert*/
    private Alert timeWarning;                            /* 2 min remaining warning*/
    private int duration;                                 /* Test duration */
    private Timer timer;                                  /* timer object*/
    private RunTimerTask tt;                              /* object of RuntimerTaskclass*/
    private int time=-1;                                  /* presnt time */
    private Command filterOk;                             /* command to select filter*/
    private Command submit;                               /* submit command */
    private Command filter;                               /* filter command */
    private Command jump;                                 /* jump command */
    private Command mark;                                 /* mark command */
    private Command photo;                                /* vew image command*/
    private Command jumpok;                               /* ok button on jump screen */
    private Command photoBack;                            /* back command*/
    private Command theme;                                /* theme command*/
    private Command themeOk;                              /* select theme command*/
    private TextBox jumpInput;                            /* input jump question no. */
    private Form photoForm;                               /* phot form*/
    private Form filterForm;                              /* Select filter form */
    private Form themeForm;                               /* Select theme form */
    private boolean filterQuestion[];                     /* stores which question are marked */
    private Color color;                                  /* object of colour class */
    private Form submitForm;                              /* submit form*/
    private int totalMarksAttempted=0;                    /* total marks attempted by student till now*/

    /* OMR constuctor */
    public OMR(Display display,Question[] question,Image image,Form submitForm,Alert timeupAlert,int duration)
    {
        this.question = new Question[question.length];
        this.question = question;
        this.display = display;
        this.timeupAlert = timeupAlert;
        this.duration = duration;
        
        setSizeVariables();
        filterChoosen = 0;
        filterQuestion = new boolean[question.length];
        this.image = image;
        color = new Color();
        remaingQuestions = question.length;
        this.submitForm= submitForm;
        mark = new Command("Mark",Command.OK,1);
        jump = new Command("Jump",Command.SCREEN,2);
        filter = new Command("Filter",Command.SCREEN,3);
        submit = new Command("Submit",Command.SCREEN,4);
        photo = new Command("Photo",Command.SCREEN,5);
        photoBack = new Command("Back",Command.BACK,1);
        jumpok = new Command("Ok",Command.OK,1);
        filterOk = new Command("Ok",Command.OK,1);
        themeOk = new Command("Ok",Command.OK,1);
        theme = new Command("Theme",Command.SCREEN,6);
        jumpInput = new TextBox("Enter Question number :","",3,TextField.NUMERIC);
        jumpInput.addCommand(jumpok);
        jumpInput.setCommandListener(this);
        photoForm = new Form("Student Photo");
        photoForm.addCommand(photoBack);
        photoForm.setCommandListener(this);       
        addCommand(mark);
        addCommand(jump);
        addCommand(filter);
        addCommand(submit);
        addCommand(photo);
        addCommand(theme);
        themeType = new ChoiceGroup("Choose Theme",Choice.EXCLUSIVE);
        themeType.append("Theme 1",null);
        themeType.append("Theme 2",null);
        themeType.append("Theme 3",null);
        themeForm = new Form("Theme");
        themeForm.append(themeType);
        themeForm.addCommand(themeOk);
        themeForm.setCommandListener(this);
        filterType = new ChoiceGroup("Choose Filter Type",Choice.EXCLUSIVE);
        filterType.append("Normal", null);
        filterType.append("Unsolved Questions", null);
        filterType.append("Solved Questions",null);
        filterForm = new Form("Filter Choice");
        filterForm.append(filterType);
        filterForm.addCommand(filterOk);
        filterForm.setCommandListener(this);
        setCommandListener(this);
        
        timeWarning = new Alert("Attention","2 mins left", null, AlertType.WARNING);
        timeWarning.setTimeout(2000);
        
        cmode =0;
        startIndex = 0;
        presentIndex = 0;
        jumpQuestion=0;
        image = rescaleImage(image,canvasWidth/2,canvasHeight/2);
        photoForm.append(image);
        
        checkQuestionFilter();
        timer = new Timer();
        tt = new RunTimerTask();
        timer.schedule(tt,0,1000*60);
    }

    /* claculate variables dependent upon screen width and height*/
    private void setSizeVariables()
    {
        xyDisp = new int[2];
        xyDisp[0] = spacing*3/5;            /* X Displacement */
        xyDisp[1] = spacing/2;            /* Y Displacement */
        scrollY = spacing/2;
        canvasHeight = getHeight();
        canvasWidth = getWidth();
        rowsPerScreen = (canvasHeight-spacing)/rowHeight;
        circlesPerRow = (canvasWidth-spacing)/spacing;
    }

    /* set initial graphic properties */
    private void graphicProperties(Graphics g)
    {
        color.setBackgroundColor(g, cmode);
        g.fillRect(0, 0, canvasWidth,canvasHeight);
        g.setFont(Font.getFont(Font.FACE_PROPORTIONAL,Font.STYLE_ITALIC, Font.SIZE_SMALL));
        color.setFontColor(g, cmode);
    }

    /* determines questions that are present in the filter*/
    private void checkQuestionFilter()
    {
        boolean flag = true;
        for(int i = 0;i < question.length;i++)
        {
            if(flag && (filterQuestion[i]=question[i].checkFilter(filterChoosen)))
            {
                presentIndex=startIndex = i;
                flag = false;
            }
            filterQuestion[i] = question[i].checkFilter(filterChoosen);
            if(filterQuestion[i])
                filterEndIndex = i;
        }
        
        filterStartIndex = startIndex;
        totalFilterQ = totalFilterQuestions();
        scrollY = spacing/2;
    }

    /* calculate number of question present in the filter choosen*/
    private int totalFilterQuestions()
    {
        int count = 0;
        for(int i=0;i < question.length; i++)
            if(filterQuestion[i])
                count++;
        return count;
    }

    /* rescale image according to mobile screen*/
    private Image rescaleImage(Image image, int width, int height)
    {
		int sourceWidth = image.getWidth();
		int sourceHeight = image.getHeight();
	
		Image newImage = Image.createImage(width, height);
		Graphics g = newImage.getGraphics();
		
		for(int y=0; y<height; y++)
		{
			for(int x=0; x<width; x++)
			{
				g.setClip(x, y, 1, 1);
				int dx = x * sourceWidth / width;
				int dy = y * sourceHeight / height;
				g.drawImage(image, x-dx, y-dy, Graphics.LEFT | Graphics.TOP);
			}
		}	
		return Image.createImage(newImage);
	}


    /* called to paint canvas. This function called paint of question class.
       Each Question paints itself .*/
    protected void paint(Graphics g)
    {    
        graphicProperties(g);
        
        if(printPhoto == true)
        {           
            g.drawImage(image,1,1, Graphics.LEFT|Graphics.TOP);
            printPhoto = false;
            return;           
        }
        color.setTopBarColor(g, cmode);
        g.drawString("Q: "+remaingQuestions+"", 0,0, 0);
        g.drawString("Left: "+(duration-time)+"min",canvasWidth/4,0,0);
        g.drawString("A: "+totalMarksAttempted+"marks",3*canvasWidth/4,0,0);
        color.setTopBarColor(g, cmode);
        
        int rowsLeft = rowsPerScreen;
        int i;
        
        xyDisp[0] = spacing/2;            /* X Displacement */
        xyDisp[1] = spacing/2;            /* Y Displacement */        

        for(i = startIndex;i<question.length;i++)
        {
            if(filterQuestion[i])
            {
                rowsLeft -= question[i].getRowSpan(circlesPerRow);
                if(rowsLeft >= 0)
                    question[i].qPaint(g,xyDisp,canvasWidth,circlesPerRow,rowHeight,circleWidth,spacing,presentIndex,filterChoosen,cmode);
                else
                    break;
                lastIndex = i;
            }
        }
        if(totalFilterQ !=0 && totalFilterQ !=1)
            drawScroll(g);        
    }

    /* draw scroll bar*/
    private void drawScroll(Graphics g)
    {
        int val;
        val = (canvasHeight-spacing*3/2)/(totalFilterQ-1);        
        g.fillRect(canvasWidth-spacing/4,spacing/2,2,val*(totalFilterQ-1)+circleWidth/2);
        g.fillArc(canvasWidth-spacing*3/8,scrollY,circleWidth/2,circleWidth/2,0,360);        
    }

    /* Set height of scrool bar whneevr screen changes*/
    private void setHeightScrollBall(int choice,int index)
    {
        switch(choice)
        {
            case 1:
                int val = (canvasHeight-spacing*3/2)/(totalFilterQ-1);
                scrollY = spacing/2 + val*index;
                break;
            case 2:
                scrollY -= (canvasHeight-spacing*3/2)/(totalFilterQ-1);
                break;
            case 3:
                scrollY += (canvasHeight-spacing*3/2)/(totalFilterQ-1);
                break;
            default:
                /* do Nothing */
        }
    }

     /* called when up key is pressed*/
    private void scrollUp()
    {
        if(presentIndex > startIndex && totalFilterQ!=0 && totalFilterQ!=1)
        {
            while(!filterQuestion[--presentIndex]);
            setHeightScrollBall(2,0);
            
        }
        else if (presentIndex == startIndex && presentIndex > filterStartIndex && totalFilterQ!=0 && totalFilterQ!=1)
        {
            while(presentIndex > filterStartIndex && !filterQuestion[--presentIndex]);
            startIndex = presentIndex;
            setHeightScrollBall(2,0);
        }
        else if(totalFilterQ!=0 && totalFilterQ!=1)
        {
            startIndex = presentIndex = filterEndIndex;
            setHeightScrollBall(1,totalFilterQ-1);
        }
    }

     /* called when down key is pressed*/
    private void scrollDown()
    {
        if(presentIndex < lastIndex && totalFilterQ!=0 && totalFilterQ!=1)
        {
            while(!filterQuestion[++presentIndex]);
            setHeightScrollBall(3,0);            
        }
        else if (presentIndex == lastIndex && presentIndex < filterEndIndex && totalFilterQ!=0 && totalFilterQ!=1)
        {
            while(!filterQuestion[++presentIndex]);
            while(!filterQuestion[++startIndex]);
            setHeightScrollBall(3,0);
        }
        else if(totalFilterQ!=0 && totalFilterQ!=1)
        {
            startIndex = presentIndex = filterStartIndex;
            setHeightScrollBall(1,0);
        }           
    }

     /* called when left key is pressed*/
    private void scrollLeft()
    {
        if(totalFilterQ!=0)
        {
            int currentOption = question[presentIndex].getCurrentOption();
            if(currentOption > 0)
                currentOption--;
            else
                currentOption = question[presentIndex].getqQualifier()-1;
            question[presentIndex].setCurrentOption(currentOption);
        }
    }

    /* called when right key is pressed*/
    private void scrollRight()
    {
        if(totalFilterQ!=0)
        {
            int currentOption = question[presentIndex].getCurrentOption();
            if(currentOption < question[presentIndex].getqQualifier()-1)
                currentOption++;
            else
                currentOption = 0;
            question[presentIndex].setCurrentOption(currentOption);
        }
    }

    /* called when user wants to jump to a question*/
    private void jumpScroll()
    {
        if(jumpQuestion > 0 && jumpQuestion < question.length && filterQuestion[jumpQuestion] && totalFilterQ!=0 && totalFilterQ!=1)
        {
            startIndex = jumpQuestion;
            presentIndex = jumpQuestion;
            setHeightScrollBall(1,jumpQuestion);
        }
        jumpQuestion = 0;
    }

    /* repaint question part*/
    private void repaintPart()
    {
        repaint(question[presentIndex].getxy()[0],question[presentIndex].getxy()[1],canvasWidth,rowHeight*question[presentIndex].getRowSpan(circlesPerRow));
    }

    /* calculates question remaining called when ever user marks or unmaks an option*/
    private void questionsRemaining(boolean oldMarked,boolean newMarked)
    {
        if(oldMarked == false && newMarked == true)
        {
            remaingQuestions--;
            totalMarksAttempted+=question[presentIndex].getQmarks();
        }
        else if(oldMarked == true && newMarked == false)
        {
            remaingQuestions++;
            totalMarksAttempted-=question[presentIndex].getQmarks();
        }
        repaint(0,0,canvasWidth,spacing/2);
    }

    /* called when a key is pressed */
    protected void keyPressed(int key)
    {
        boolean oldMarked,newMarked;
        if( key >= KEY_NUM1 && key <= KEY_NUM9 && totalFilterQ!=0)
        {
            if(!starPressed && key-KEY_NUM1 < question[presentIndex].getqQualifier())
            {
                oldMarked = question[presentIndex].getIfMarked();
                question[presentIndex].setCurrentOption(key - KEY_NUM1);
                question[presentIndex].setmarkOption();
                newMarked = question[presentIndex].getIfMarked();
                questionsRemaining(oldMarked, newMarked);
                display.vibrate(20);
                //repaintPart();
                repaint();
            }
            else if(starPressed)
            {
                jumpQuestion = jumpQuestion*10 + key-KEY_NUM0;
                repaint();
            }
            else
                display.vibrate(100);
        }
                
        else if(key == KEY_STAR)
            starPressed=!starPressed;
                
        else if(key == KEY_POUND)
        {
            if(starPressed)
            {
                jumpQuestion--;
                jumpScroll();
                repaint();
                starPressed=!starPressed;
            }
        }
        else 
            switch(getGameAction(key))
            {
                case UP:
                    scrollUp();
                    repaint();
                    break;
                case DOWN:
                    scrollDown();
                    repaint();
                    break;
                case LEFT:
                    scrollLeft();
                    repaintPart();
                    break;
                case RIGHT:
                    scrollRight();
                    repaintPart();
                    break;
                case FIRE:
                    if(totalFilterQ!=0)
                    {
                        oldMarked = question[presentIndex].getIfMarked();
                        question[presentIndex].setmarkOption();
                        newMarked = question[presentIndex].getIfMarked();
                        questionsRemaining(oldMarked, newMarked);
                        display.vibrate(20);
                        repaintPart();
                    }
                    break;
                default:
                    /* do nothing */           
            }

    }

    /* called when a key is repeatedly pressed */
    
    protected void keyRepeated(int key) 
    {
      keyPressed(key);       
    }

    /* automatically called when a command is selected*/
    public void commandAction(Command cmd, Displayable d)
    {
        try
        {            
            if(cmd == mark && totalFilterQ!=0)
            {
                boolean oldMarked,newMarked;
                oldMarked = question[presentIndex].getIfMarked();
                question[presentIndex].setmarkOption();
                newMarked = question[presentIndex].getIfMarked();
                questionsRemaining(oldMarked, newMarked);
            }
            else if (cmd == jump)
            {
                jumpInput.setString(null);
                display.setCurrent(jumpInput);
            }
            else if (cmd == jumpok)
            {
                if(jumpInput.getString() != null || !jumpInput.equals(""))
                {
                    jumpQuestion = Integer.parseInt(jumpInput.getString())-1;
                    jumpScroll();
                }
                display.setCurrent(this);
            }
            else if (cmd == filter)
               display.setCurrent(filterForm);
            else if(cmd == filterOk)
            {
                filterChoosen = filterType.getSelectedIndex();
                checkQuestionFilter();
                display.setCurrent(this);
            }
            else if(cmd == photo)
            { 
                display.setCurrent(photoForm);

            }
            else if(cmd == photoBack)
                display.setCurrent(this);
            else if(cmd == theme)
                display.setCurrent(themeForm);
            else if(cmd == themeOk)
            {
                cmode = themeType.getSelectedIndex();
                display.setCurrent(this);
            }
            else if(cmd == submit)
            {
                display.setCurrent(submitForm);
            }
        }
        catch(Exception e)
        {
            display.setCurrent(this);
        }
        repaint();
    }

    /* automatically called when size of canvas is changed*/
    protected void sizeChanged(int w,int h)
    {
        setSizeVariables();       
    }

    /* Stop timer */
    public void stopTimer()
    {
        timer.cancel();
    }

    /* Timer class which keeps count of time
     called in veery one minute and time remaining shown is updated*/
    private class RunTimerTask extends TimerTask
    {
        public final void run()
        {
            time++;
            if(time == duration-2)
            {
                display.setCurrent(timeWarning);
            }
            else if(time == duration)
            {
                display.setCurrent(timeupAlert);
            }

            repaint(0, 0, getWidth(), spacing/2);
        }
    }
    
}
