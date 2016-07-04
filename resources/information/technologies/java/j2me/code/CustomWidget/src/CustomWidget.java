<![CDATA[
import javax.microedition.lcdui.*;

public class CustomWidget extends CustomItem
{
  private int number;

  public CustomWidget(String title)
  {
    super(title);
    System.out.println("Created CustomWidget");
    setLayout(LAYOUT_CENTER);
    number = 1;
  }  

  public int getMinContentWidth()
  {
    return 126;
  }
  public int getMinContentHeight()
  {
    return 99;
  }

  public int getPrefContentWidth(int width)
  {
    return getMinContentWidth();
  }

  public int getPrefContentHeight(int height)
  {
    return getMinContentHeight();
  }
    
  public void paint(Graphics g, int w, int h)
  {
    System.out.println("Drawing Graphic : "+w+" "+h);
    int pixelCount = w*h;
    int[] pixels = new int[pixelCount];
    
    System.out.println("MAX : "+Integer.toString(Integer.MAX_VALUE,16));
    int black = 0xff000000; 
    int red = 0xffff0000; 
    int green = 0xff00ff00;	
    int blue = 0xff0000ff;	
    int white = red | green | blue; 
    
    for (int index=0;index<pixelCount;index++)
    {
      pixels[index]=black;
      if ((index+number) % 9 == 0 ) pixels[index] |= red; 
      if ((index+number) % 7 == 0 ) pixels[index] |= green; 
      if ((index+number) % 5 == 0 ) pixels[index] |= blue; 
      if (index % number == 0 ) pixels[index] = white;
      //System.out.println("Color "+index+" : "+Integer.toString(pixels[index],16));
    }
    g.drawRGB(pixels,0,w,0,0,w,h,true);
    g.setColor(0);
    g.drawRect(0,0,w-1,h-1);
  }
  protected void keyPressed(int keyCode)
  {
    System.out.println("Key Pressed : "+keyCode);
    number = keyCode > 0 ? keyCode : -keyCode;
    repaint();
  }
  
  protected void pointerPressed(int x, int y)
  {
    System.out.println("Pointer Pressed : "+x+" "+y);
  }
}
]]>
