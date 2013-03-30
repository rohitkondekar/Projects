package Ans_module;

import javax.microedition.lcdui.*;
import java.util.*;

/* Question class consists of properties of a question. It is called When Question file from server is read .
 It is an intelligent class which decides when to paint and what to paint*/

public class Question
{

    int qNumber;                                          /* Question number */
    private int qType;                                    /* Question type */
    private int qQualifier;                               /* No of options */
    private int qOptionsMarked;                           /* number of option marked by user */
    private float qMarks;                                 /* marks alloted to the question */
    private int rowSpan;                                  /* rows occupied by the question */
    private int xy[];                                     /* x y coordinates used for drawing a question */
    private boolean markedOptions[];                      /* Bool array consisting of all the oprtins*/
    private int currentOption;                            /* current highlighted option */
    private String qOptionCharacters;                     /* charachters of options */
    private Color color;                                  /*Object of colour class */
    
    /* Constructor called to initialize variables of question class*/

    public Question(int qNumber, int qType, int qQualifier,float qMarks,Color color)
    {
        this.qNumber = qNumber;
        this.qType = qType;
        this.qQualifier = qQualifier;
        this.qMarks = qMarks;
        xy = new int[2];
        markedOptions = new boolean[qQualifier];
        currentOption = 0;
        qOptionsMarked = 0;
        this.color = color;
    }
    
   

    public void setOptionCharacters(String optionCharacters)
    {
        if(optionCharacters != null)
            this.qOptionCharacters = optionCharacters;
        else
            this.qOptionCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    }

    /* return row span of question to omr*/
    public int getRowSpan(int circlesPerRow)
    {
       return (int) Math.ceil((float)qQualifier/circlesPerRow);
    }

   /* return number of options of question to omr*/
    public int getqQualifier()
    {
        return qQualifier;
    }
    
    /* return marks of question to omr*/
    public float getQmarks()
    {
        return qMarks;
    }

     /* return highlighted option of question to omr*/
    public int getCurrentOption()
    {
        return currentOption;
    }

     /* set current option */
    public void setCurrentOption(int currentOption)
    {
        this.currentOption = currentOption;
    }

     /* returns whether provided option is markes or not*/
    public boolean getMarkedOption(int optionNumber)
    {
        return markedOptions[optionNumber];
    }

     /* return number of marked options of question to omr*/
    public int getNumMarkedOption()
    {
        return qOptionsMarked;
    }

     /* return option charachter at index i to omr*/
    public char getOptionChar(int i)
    {
        return qOptionCharacters.charAt(i);
    }

    /* return true if question is attempted*/
    public boolean getIfMarked()
    {
        if(qOptionsMarked == 0)
            return false;
        else
            return true;
    }
    
    public boolean getIfMarkedIndex(int i)
    {
        return markedOptions[i];
    }

    /* get x y coordiantes */
    public int[] getxy()
    {
        return xy;
    }


    /* called whne user marks an option*/
    public void setmarkOption()
    {
        if(markedOptions[currentOption])
        {
            markedOptions[currentOption] = false;
            qOptionsMarked--;
        }
        else
        {
            if(qType == 1)
            {
                for(int i = 0;i < qQualifier;i++)
                    markedOptions[i] = false;
                markedOptions[currentOption] = true;
                qOptionsMarked = 1;
            }
            else if (qType == 2)
            {
                markedOptions[currentOption] = true;
                qOptionsMarked++;
            }
        }
    }

    /*called when user selects a filter*/
    public boolean checkFilter(int filterChoosen)
    {
        if(filterChoosen==0)
            return true;
        else if (filterChoosen == 1)
        {
            if(qOptionsMarked == 0)
                return true;
        }
        else if (filterChoosen == 2)
        {
            if(qOptionsMarked !=0 )
                return true;
        }
        return false;
    }
    
    /* paint question */
    public void qPaint(Graphics g, int xyDisp[],int canvasWidth, int circlesPerRow, int rowHeight,int circleWidth,int spacing,int presentIndex,int filterChoosen,int cmode)
    {
        int ind=0;
        rowSpan = getRowSpan(circlesPerRow);
        xy[0] = xyDisp[0];
        xy[1] = xyDisp[1];

        if((presentIndex+1) == qNumber)
        {
            color.setLineHighlightColor(g,cmode);
            g.fillRect(0, xyDisp[1],canvasWidth,rowHeight*rowSpan);
            color.setFontColor(g, cmode);
        }
        g.drawString(qNumber+".", 0, xyDisp[1]+rowHeight*3/8, 0);
        
        for(int i = 0;i < rowSpan;i++)
        {
            for(int j=0; j < circlesPerRow && ind < qQualifier;j++)
            {
                color.setFontColor(g, cmode);
                if(markedOptions[ind] == true)
                {
                    color.setMarkColor(g, cmode);
                    g.fillArc(xyDisp[0],xyDisp[1]+rowHeight/4,circleWidth,circleWidth,0,360);
                    color.setFontColor(g, cmode);                                    
                }
                if(presentIndex+1 == qNumber && currentOption == ind)
                    color.setCircleHighlightColor(g, cmode);                               
                g.drawArc(xyDisp[0],xyDisp[1]+rowHeight/4,circleWidth,circleWidth,0,360);
                g.drawString(qOptionCharacters.charAt(ind)+"", xyDisp[0]+circleWidth/4, xyDisp[1]+rowHeight*2/7, 0);
                color.setFontColor(g, cmode);
                xyDisp[0] = xyDisp[0]+spacing;
                ind++;
            }
            xyDisp[0] = spacing/2;
            xyDisp[1] = xyDisp[1] + rowHeight;
        }        
    }   
}
