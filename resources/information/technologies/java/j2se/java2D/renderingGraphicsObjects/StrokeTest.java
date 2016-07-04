
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.util.*;

public class StrokeTest extends JFrame
{
  public StrokeTest()
  {
    addWindowListener(new WindowAdapter()
                      {
                        public void windowClosing(WindowEvent event)
                          {System.exit(0);}
                      });    

    setTitle("Testing Strokes");

    getContentPane().setLayout(new BorderLayout()); 

    JPanel drawPanel = new JPanel();
    DrawCanvas drawCanvas = new DrawCanvas();     
    drawPanel.add(drawCanvas, BorderLayout.CENTER);
    drawPanel.setBorder(BorderFactory.createRaisedBevelBorder());

    JPanel capJoinWidthPanel = new JPanel();
    capJoinWidthPanel.add(createStrokePanel(drawCanvas, this));
    capJoinWidthPanel.add(createWidthSelectionPanel(drawCanvas));
    JPanel dashPatternPanel = createDashPatternPanel(drawCanvas);

    JTabbedPane tabbedPane = new JTabbedPane();
    tabbedPane.add("Cap, Join & Width", new JScrollPane(capJoinWidthPanel));
    tabbedPane.add("DashPattern",  new JScrollPane(dashPatternPanel));
    tabbedPane.setBorder(BorderFactory.createEmptyBorder(5,5,10,5));
                      
    
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    mainPanel.add(tabbedPane, BorderLayout.NORTH);
    mainPanel.add(new JScrollPane(drawPanel), BorderLayout.CENTER);
    
    getContentPane().add(mainPanel, BorderLayout.CENTER);

    pack();     
    setVisible(true);                                 
  }
  
  private JPanel createDashPatternPanel(DrawCanvas drawCanvas)
  {
    JPanel topLeftPanel = new JPanel();
    topLeftPanel.add(new JLabel("Dash width: "));  
    JDoubleField dashField = new JDoubleField(5, 10);
    DoubleFilter filter = new DoubleClosedRangeFilter(1,60);
    JDoubleFieldFilter doubleFilter = new JDoubleFieldFilter(filter);
    doubleFilter.assignTo(dashField,true);
    topLeftPanel.add(dashField);
    
    JButton removeButton = new JButton("Remove");
    JPanel bottomLeftPanel = new JPanel();
    bottomLeftPanel.add(removeButton);
    
    DefaultListModel listData = new DefaultListModel();
    JList dashList = new JList(listData);

    JPanel leftPanel  = new JPanel();
    JPanel rightPanel = new JPanel();
    
    leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));
    leftPanel.add(topLeftPanel);
    leftPanel.add(bottomLeftPanel);
    rightPanel.add(new JScrollPane(dashList));
    
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
    panel.add(leftPanel); 
    panel.add(rightPanel); 
    
    class DashFieldListener implements ActionListener
    {
      DashFieldListener (JDoubleField dashField, 
                         DefaultListModel listData, DoubleFilter doubleFilter)
      {
        this.dashField    = dashField;
        this.listData     = listData;
        this.doubleFilter = doubleFilter;
      }
        
      public void actionPerformed(ActionEvent event)
      {
        double fieldValue = 0;
        try
        {
          fieldValue = dashField.getDouble();        
          if (doubleFilter.accept(fieldValue))       
            listData.addElement(new Double(fieldValue));
        }
        catch (NumberFormatException e) {}  
      }  
      private JDoubleField dashField;
      private DefaultListModel listData;
      private DoubleFilter doubleFilter;
    }
  
    class DashListListener implements ListDataListener
    {
      public DashListListener (DrawCanvas drawCanvas)
      {
        this.drawCanvas = drawCanvas;
      }

      private void updateDrawCanvas(EventObject event)
      {
        DefaultListModel listModel = (DefaultListModel)event.getSource();
        
        float[] pattern = new float[listModel.getSize()];
        Enumeration iter = listModel.elements();
        int i = 0;
        while (iter.hasMoreElements())
          {
            pattern[i] = (float)((Double)iter.nextElement()).doubleValue();
            ++i;
          }  
        drawCanvas.setDashPattern(pattern);  
        drawCanvas.repaint();
      }
      
      public void intervalAdded(ListDataEvent event) 
      {
        updateDrawCanvas(event);
      }  
       
      public void contentsChanged(ListDataEvent event)
      {
        updateDrawCanvas(event);
      }  
      
      public void intervalRemoved(ListDataEvent event) 
      {
        updateDrawCanvas(event);
      }  
      
      private DrawCanvas drawCanvas;
    }  
      
    class RemoveListener implements ActionListener
    {
      public RemoveListener (JList list, DefaultListModel dataModel)
      {
        this.list = list;
        this.dataModel = dataModel;
      }
          
      public void actionPerformed(ActionEvent event)
      {
        try
        {
          dataModel.removeElementAt(list.getSelectedIndex()); 
        }
        catch (ArrayIndexOutOfBoundsException e) {}  
      }  
       
      private JList list;
      private DefaultListModel dataModel;
    }
    
    dashField.addActionListener(new DashFieldListener(dashField, listData,
                                                      filter));
    listData.addListDataListener(new DashListListener(drawCanvas));
    removeButton.addActionListener(new RemoveListener(dashList, listData));
    return panel;
  }
  
  private JPanel createStrokePanel(DrawCanvas drawCanvas, JFrame frame)
  {
    JPanel capJoinPanel = new JPanel();  
    capJoinPanel.add(createCapSelectionPanel(drawCanvas));                       
    capJoinPanel.add(createJoinSelectionPanel(drawCanvas));
    
    JButton colorButton       = new JButton("Color");
    colorButton.addActionListener(new ColorButtonListener(drawCanvas,frame));
    
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(colorButton);
    
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(2,1));
    panel.add(capJoinPanel);
    panel.add(buttonPanel);
    return panel;
  }
  
  private JPanel createCapSelectionPanel(DrawCanvas drawCanvas)
  {
    JPanel panel = new JPanel();     
    CapComboBox capComboBox = new CapComboBox(); 
    capComboBox.addActionListener(new CapListener(drawCanvas)); 
    panel.add(capComboBox); 
    panel.setBorder(BorderFactory.createTitledBorder("Line Cap")); 
    return panel;
  }                    

  private JPanel createJoinSelectionPanel(DrawCanvas drawCanvas)
  {
    JPanel panel = new JPanel();     
    JoinComboBox joinComboBox = new JoinComboBox(); 
    joinComboBox.addActionListener(new JoinListener(drawCanvas)); 
    panel.add(joinComboBox); 
    panel.setBorder(BorderFactory.createTitledBorder("Join Type")); 
    return panel;
  }

  private JPanel createWidthSelectionPanel(DrawCanvas drawCanvas)
  {
    JPanel panel = new JPanel(); 
    panel.setLayout(new GridLayout(2,1));
    JPanel inputPanel = new JPanel();
    
    int minWidth=1, maxWidth=60, value=40;

    DoubleFilter filter = new DoubleClosedRangeFilter(minWidth,maxWidth);
    JDoubleField widthField = new JDoubleField(40,10);
    JDoubleFieldFilter doubleFieldFilter = new JDoubleFieldFilter(filter);
    doubleFieldFilter.assignTo(widthField,true);
    
    JSlider widthBar = new JSlider(JSlider.HORIZONTAL, 0, maxWidth, 
                                   value);
    widthBar.setMajorTickSpacing(10);
    widthBar.setMinorTickSpacing(2);
    widthBar.setPaintTicks(true);
    widthBar.setPaintLabels(true);
    widthBar.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));    

    class WidthListener implements ActionListener, FocusListener,
                                   ChangeListener
    {  
      public WidthListener(DrawCanvas drawCanvas, JDoubleField widthField,
                           JSlider widthBar, DoubleFilter filter) 
      {
        this.drawCanvas = drawCanvas;
        this.widthField = widthField;
        this.widthBar   = widthBar;
        this.filter     = filter;
      }
  
      private void setWidth ()
      {
        double fieldValue = 0; 
        try
        {
          fieldValue = widthField.getDouble();
          if (filter.accept(fieldValue))
          {
            drawCanvas.setWidth(widthField.getDouble());
            drawCanvas.repaint();
          }  
        }
        catch (NumberFormatException e) {}
      }  
  
      private void processField()
      {
        widthBar.setValue((int)widthField.getDouble());
        setWidth();
      }
  
      public void actionPerformed (ActionEvent event) {processField();}
      public void focusLost       (FocusEvent event)  {processField();}
      public void focusGained     (FocusEvent event)  {}
  
      public void stateChanged (ChangeEvent event)
      {
        widthField.setDouble((double)widthBar.getValue());
        setWidth();
      }  

      private DrawCanvas   drawCanvas;
      private JDoubleField widthField;
      private JSlider      widthBar;
      private DoubleFilter filter;
    }    
    
    WidthListener widthListener = new WidthListener (drawCanvas, widthField,
                                                     widthBar, filter);
    widthField.addActionListener (widthListener);
    widthField.addFocusListener  (widthListener);
    widthBar.addChangeListener   (widthListener);
    
    inputPanel.add(new JLabel("Width: "));
    inputPanel.add(widthField);
    
    panel.add(inputPanel);
    panel.add(widthBar);
    panel.setBorder(BorderFactory.createTitledBorder("Line Width"));
    return panel;
  }  
  
  public static void main(String[] args) {new StrokeTest();}
}  

//--------------------------------------------------------------------

class DrawCanvas extends JPanel
{
  public DrawCanvas() 
  {
    setPreferredSize(new Dimension(450,450));
    setMinimumSize(new Dimension(300,300));
    setBorder(BorderFactory.createRaisedBevelBorder());
    setBackground(Color.white);
  }
    
  public void paint(Graphics gc)
  {
    super.paint(gc);
    Graphics2D gc2D = (Graphics2D)gc;
    
    Dimension componentSize = getSize();
    int width  = (int)componentSize.getWidth();
    int height = (int)componentSize.getHeight();
    
    Stroke stroke = null;
    if ((dashPattern == null) || (dashPattern.length == 0))
      stroke = new BasicStroke(lineWidth, lineCap, lineJoin);
    else                           
      stroke = new BasicStroke(lineWidth, lineCap, lineJoin,
                               miterLimit, dashPattern, dashPhase);
    gc2D.setStroke (stroke);
    gc2D.setColor  (color);
    gc2D.drawRect  (width/8,height/4,3*width/4,3*height/5);
    gc2D.drawLine  (width/6, height/10, 5*width/6, height/10);
  }  
  
  public void setCap       (int cap)       {lineCap = cap;}
  public void setJoin      (int join)      {lineJoin = join;}
  public void setWidth     (double width)  {lineWidth = (float)width;}
  public void setColor     (Color color)   {this.color = color;}
  public void setDashPhase (int dashPhase) {this.dashPhase = dashPhase;}
  
  public void setDashPattern (float[] pattern) {dashPattern = pattern;}
  
  public Color getColor() {return color;}
  
  private int     lineCap  = BasicStroke.CAP_BUTT;
  private int     lineJoin = BasicStroke.JOIN_MITER;
  private int     dashPhase = 0;
  private float   lineWidth = 40, miterLimit = 100; 
  private float[] dashPattern = null;
  private Color   color;
}  

//----------------------------------------------------------

class CapComboBox extends JComboBox
{
  public CapComboBox() {super(choices);}  
  
  int getCapType()
  {
    int index = getSelectedIndex();
    
    switch (index)
    {
      case 0: return BasicStroke.CAP_BUTT;
      case 1: return BasicStroke.CAP_ROUND;
      case 2: return BasicStroke.CAP_SQUARE;
      
      default: return BasicStroke.CAP_BUTT;
    }
  }    
  private static String[] choices = {"Butt","Round","Square"};
}        

//----------------------------------------------------------

class JoinComboBox extends JComboBox
{
  public JoinComboBox() {super(choices);}  
  
  int getJoinType()
  {
    int index = getSelectedIndex();
    
    switch (index)
    {
      case 0: return BasicStroke.JOIN_MITER;
      case 1: return BasicStroke.JOIN_BEVEL;
      case 2: return BasicStroke.JOIN_ROUND;
      
      default: return BasicStroke.JOIN_MITER;
    }
  }    
  private static String[] choices = {"Miter","Bevel","Round"};
}        

//----------------------------------------------------------

class CapListener implements ActionListener
{  
  public CapListener(DrawCanvas drawCanvas) 
  {
    this.drawCanvas = drawCanvas;
  }
  
  public void actionPerformed(ActionEvent event)
  {
    int capType = ((CapComboBox)event.getSource()).getCapType();
    drawCanvas.setCap(capType);
    drawCanvas.repaint();
  }
  private DrawCanvas drawCanvas;
}    
//----------------------------------------------------------

class JoinListener implements ActionListener
{  
  public JoinListener(DrawCanvas drawCanvas) 
  {
    this.drawCanvas = drawCanvas;
  }
  
  public void actionPerformed(ActionEvent event)
  {
    int joinType = ((JoinComboBox)event.getSource()).getJoinType();
    drawCanvas.setJoin(joinType);
    drawCanvas.repaint();
  }
  private DrawCanvas drawCanvas;
}    

//----------------------------------------------------------

class ColorButtonListener implements ActionListener
{  
  public ColorButtonListener(DrawCanvas drawCanvas, JFrame frame) 
  {
    this.drawCanvas = drawCanvas;
    this.frame      = frame;
  }
  
  public void actionPerformed(ActionEvent event)
  {
    Color color = JColorChooser.showDialog(frame, "Choose Color",
                                           drawCanvas.getColor());
    drawCanvas.setColor(color);
    drawCanvas.repaint();
  }
  private DrawCanvas drawCanvas;
  private JFrame frame;
}    

//----------------------------------------------------------

class JDoubleField extends JTextField
{
  public JDoubleField(int width) { super(width); }

  public JDoubleField(double initialValue, int width) 
    { this(width); setDouble(initialValue);}

  public synchronized void setDouble(double value) 
    { setText(new Double(value).toString()); }

  public double getDouble() 
  { 
    double result = new Double(getText().trim()).doubleValue(); 
    return result;
  }
}  
//---------------------------------------------------------------------

class JDoubleFieldFilter extends JPanel implements FocusListener,
                                                   ActionListener
{
  public JDoubleFieldFilter(DoubleFilter filter)
  {
    this.filter = filter;
  } 
  
  private void checkField(EventObject event)
  {
    JDoubleField source = (JDoubleField)event.getSource();
    
    source.removeFocusListener(this);
    source.removeActionListener(this);
    
    boolean happy = false;
    double value = 0;
    
    try
    {
      if (filter.accept(source.getDouble()))
        happy = true;
    }
    catch (NumberFormatException e) {happy = false;}
    
      
    if (!happy)  
    {
      String errorMessage = "Entered data failed filter " + filter;
      JOptionPane.showMessageDialog (source.getParent(), 
                                     errorMessage, "Double input error",
                                     JOptionPane.ERROR_MESSAGE);
      source.requestFocus();
    }                           
    source.addFocusListener(this);
    source.addActionListener(this);
  }
 
  public void focusGained     (FocusEvent event)  {}
  public void focusLost       (FocusEvent event)  {checkField(event);}
  public void actionPerformed (ActionEvent event) {checkField(event);}
  public void assignTo (JDoubleField doubleField, boolean rangeToolTip)
  {
    doubleField.setRequestFocusEnabled (true);
    doubleField.addFocusListener       (this);
    doubleField.addActionListener      (this);
    
    if (rangeToolTip)
    {
      String toolTip = "Double within " + filter.toString();
      doubleField.getAccessibleContext().setAccessibleDescription(toolTip);
      doubleField.setToolTipText(toolTip);
    }  
  }    
  private DoubleFilter filter;
}  

interface DoubleFilter
{
  public boolean accept(double x);
}

class DoubleClosedRangeFilter implements DoubleFilter
{
  public DoubleClosedRangeFilter() {}
  
  public DoubleClosedRangeFilter(double min, double max)
  {
    this.min = min;
    this.max = max;
  }
  
  public boolean accept(double x)
  {
    return  ((x >= min) && (x <= max));
  }     
  
  public double getMin() {return min;}
  public double getMax() {return max;}
  
  public String toString() 
  {
    StringBuffer result = new StringBuffer();
    result.append('[').append(min).append(',').append(max).append(']');
    return result.toString();
  }  
  
  private double min  = Double.MIN_VALUE;
  private double max  = Double.MAX_VALUE;
}  
