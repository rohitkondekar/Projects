package Ans_module;

import javax.microedition.lcdui.Graphics;

public class Color
{
    private static final int ant_white = 0xfdf5e6;
    private static final int white = 0xffffff;
    private static final int snow_white = 0xfffaf0;
    private static final int black = 0x000000;
    private static final int gray = 0xdcdcdc;
    private static final int peru = 0xcd853f;
    private static final int Org_red = 0xff4500;
    private static final int mud = 0xCCCC99;
    private static final int pink = 0x990033;
    private static final int blue = 0x81A594;
    private static final int yellow = 0xFFCC00;
    private static final int purple = 0x666699;
    private static final int deep_pink = 0xFF1493;
    
    public void setBackgroundColor(Graphics g,int cmode)
    {
              if(cmode == 0)
                  g.setColor(white);
              else if(cmode == 1)
                  g.setColor(mud);
                else if(cmode == 2)
            g.setColor(white);              
    }
    
    public void setFontColor(Graphics g,int cmode)
    {
        if(cmode == 0)
            g.setColor(black);
        else if (cmode == 1)
            g.setColor(pink);
        else if (cmode == 2)
            g.setColor(0,0,255); 

    }
    
    public void setLineHighlightColor(Graphics g,int cmode)
    {
        if(cmode == 0)
            g.setColor(gray); 
        else if (cmode == 1)
            g.setColor(blue);
        else if (cmode == 2)
            g.setColor(255,128,64);
    }
    
    public void setCircleHighlightColor(Graphics g,int cmode)
    {
        if(cmode == 0)
            g.setColor(deep_pink);
        else if(cmode == 1)
            g.setColor(yellow);
        else if (cmode == 2)
            g.setColor(0x800000);
    }
    
    public void setMarkColor(Graphics g,int cmode)
    {
        if(cmode == 0)
            g.setColor(peru);
        else if(cmode == 1)
            g.setColor(purple);
        else if(cmode == 2)
            g.setColor(black);        

    }
    
    public void setTopBarColor(Graphics g,int cmode)
    {
        if(cmode == 0)
            g.setColor(purple);
        else if(cmode == 1)
            g.setColor(black);
        else if(cmode == 2)
            g.setColor(255,0,255);        
    }
}
