<![CDATA[
/*
 * LowLevelCanvas.java
 *
 * Created on April 14, 2003, 9:44 PM
 */
package za.co.solmstraining.j2me.lowlevelgui;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author  ernst
 */
public class LowLevelCanvas extends Canvas
{
    protected void paint(Graphics graphics)
    {
        graphics.setColor(255,255,255);
        graphics.fillRect(0,0, getWidth(),getHeight());
        graphics.setColor(0, 0, 0);
        //vertical
        int x1 = getWidth() / 3;
        int x2 = x1 * 2;
        int y1 = getHeight() / 3;
        int y2 = y1 * 2;
        graphics.drawLine(x1, 0, x1, (getHeight()));
        graphics.drawLine(x2, 0, x2, (getHeight()));
        //horizontal
        graphics.drawLine(0, y1, (getWidth()), y1);
        graphics.drawLine(0, y2, (getWidth()), y2);
        
        if (q == 1)
        {
            graphics.drawLine(0, 0, x1, y1);
            graphics.drawLine(x1, 0, 0, y1);
            javax.microedition.lcdui.AlertType.CONFIRMATION.playSound(display);
        }
        if (q == 2)
        {
            graphics.drawRoundRect(0, 0, x1, y1, 50, 50);
            javax.microedition.lcdui.AlertType.INFO.playSound(display);
        }
        
        if (w == 1)
        {
            graphics.drawLine(x1, 0, x2, y1);
            graphics.drawLine(x1, y1, x2, 0);
            javax.microedition.lcdui.AlertType.CONFIRMATION.playSound(display);
        }
        if (w == 2)
        {
            graphics.drawRoundRect(x1, 0, x1, y1, 50, 50);
            javax.microedition.lcdui.AlertType.INFO.playSound(display);
        }
        
        if (e == 1)
        {
            graphics.drawLine(x2, 0, getWidth(), y1);
            graphics.drawLine(x2, y1, getWidth(), 0);
            javax.microedition.lcdui.AlertType.CONFIRMATION.playSound(display);
        }
        if (e == 2)
        {
            graphics.drawRoundRect(x2, 0, x1, y1, 50, 50);
            javax.microedition.lcdui.AlertType.INFO.playSound(display);
        }
        
        if (r == 1)
        {
            graphics.drawLine(0, y1, x1, y2);
            graphics.drawLine(0, y2, x1, y1);
            javax.microedition.lcdui.AlertType.CONFIRMATION.playSound(display);
        }
        if (r == 2)
        {
            graphics.drawRoundRect(0, y1, x1, y1, 50, 50);
            javax.microedition.lcdui.AlertType.INFO.playSound(display);
        }
        
        if (t == 1)
        {
            graphics.drawLine(x1, y1, x2, y2);
            graphics.drawLine(x1, y2, x2, y1);
            javax.microedition.lcdui.AlertType.CONFIRMATION.playSound(display);
        }
        if (t == 2)
        {
            graphics.drawRoundRect(x1, y1, x1, y1, 50, 50);
            javax.microedition.lcdui.AlertType.INFO.playSound(display);
        }
        
        if (y == 1)
        {
            graphics.drawLine(x2, y1, getWidth(), y2);
            graphics.drawLine(x2, y2, getWidth(), y1);
            javax.microedition.lcdui.AlertType.CONFIRMATION.playSound(display);
        }
        if (y == 2)
        {
            graphics.drawRoundRect(x2, y1, x1, y1, 50, 50);
            javax.microedition.lcdui.AlertType.INFO.playSound(display);
        }
        
        if (u == 1)
        {
            graphics.drawLine(0, y2, x1, getHeight());
            graphics.drawLine(0, getHeight(), x1, y2);
            javax.microedition.lcdui.AlertType.CONFIRMATION.playSound(display);
        }
        if (u == 2)
        {
            graphics.drawRoundRect(0, y2, x1, y1, 50, 50);
            javax.microedition.lcdui.AlertType.INFO.playSound(display);
        }
        
        if (i == 1)
        {
            graphics.drawLine(x1, y2, x2, getHeight());
            graphics.drawLine(x1, getHeight(), x2, y2);
            javax.microedition.lcdui.AlertType.CONFIRMATION.playSound(display);
        }
        if (i == 2)
        {
            graphics.drawRoundRect(x1, y2, x1, y1, 50, 50);
            javax.microedition.lcdui.AlertType.INFO.playSound(display);
        }
        
        if (o == 1)
        {
            graphics.drawLine(x2, y2, getWidth(), getHeight());
            graphics.drawLine(x2, getHeight(), getWidth(), y2);
            javax.microedition.lcdui.AlertType.CONFIRMATION.playSound(display);
        }
        if (o == 2)
        {
            graphics.drawRoundRect(x2, y2,  x1, y1, 50, 50);
            javax.microedition.lcdui.AlertType.INFO.playSound(display);
        }
    }
    protected void keyReleased(int param)
    {
        drawIt(param);
    }
    
    private void doTurn()
    {
        int moveNum = q+w+e+r+t+y+u+i+o+1;
        int parm1 = (random.nextInt() % 10);
        
        if (moveNum<13)
        {
            while (parm1 <= 0 || parm1 == 10)
                parm1 = (random.nextInt() % 10);
            
            switch(parm1)
            {
                case 1:
                    drawIt(49); break;
                case 2:
                    drawIt(50); break;
                case 3:
                    drawIt(51); break;
                case 4:
                    drawIt(52); break;
                case 5:
                    drawIt(53); break;
                case 6:
                    drawIt(54); break;
                case 7:
                    drawIt(55); break;
                case 8:
                    drawIt(56); break;
                case 9:
                    drawIt(57); break;
                default:
                    break;
            }
        }
    }
    
    private void drawIt(int param)
    {
        if (turn)
            cross = 1;
        if (!turn)
            cross = 2;
        
        switch(param)
        {
            case 49:
            {
                if (q == 0)
                    q = cross;
                else
                    turn = !turn;
                break;
            }
            case 50:
            {
                if (w == 0)
                    w = cross;
                else
                    turn = !turn;
                break;
            }
            case 51:
            {
                if (e == 0)
                    e = cross;
                else
                    turn = !turn;
                break;
            }
            case 52:
            {
                if(r == 0)
                    r = cross;
                else
                    turn = !turn;
                break;
            }
            case 53:
            {
                if(t == 0)
                    t = cross;
                else
                    turn = !turn;
                break;
            }
            case 54:
            {
                if (y == 0)
                    y = cross;
                else
                    turn = !turn;
                break;
            }
            case 55:
            {
                if (u == 0)
                    u = cross;
                else
                    turn = !turn;
                break;
            }
            case 56:
            {
                if (i == 0)
                    i = cross;
                else
                    turn = !turn;
                break;
            }
            case 57:
            {
                if(o == 0)
                    o = cross;
                else
                    turn = !turn;
                break;
            }
            default: turn = !turn;
        }
        
        if (turn)
            turn = false;
        else
            turn = true;
        
        repaint();
        
        if (!turn)
            doTurn();
    }
    
    boolean turn = true;
    private int cross = 0;
    private int q=0,w=0,e=0,r=0,t=0,y=0,u=0,i=0,o=0;
    private java.util.Random random = new java.util.Random();
    private javax.microedition.lcdui.Display display;
    
    public LowLevelCanvas(javax.microedition.midlet.MIDlet midlet)
    {
        display = javax.microedition.lcdui.Display.getDisplay(midlet);
    }
    
}
]]>
