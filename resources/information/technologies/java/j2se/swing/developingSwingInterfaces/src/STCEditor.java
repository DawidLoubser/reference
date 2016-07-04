import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.multi.*;
import javax.swing.text.*;
import java.io.*;
import java.util.*;

public class STCEditor extends JFrame implements ActionListener
{
  public STCEditor()
  {
    app = this;
    addWindowListener(new WindowAdapter()
                      {
                        public void windowClosing(WindowEvent event)
                          {System.exit(0);}
                      });    

    setTitle("STC Editor");                      
    JPanel statusLine = new JPanel();
    statusLine.add(statusField);         statusField.setEditable(true);
    statusLine.add(cursorField);         cursorField.setEditable(false);

    getContentPane().add(createToolBar(), BorderLayout.NORTH);
    getContentPane().add(desktop, BorderLayout.CENTER);
    getContentPane().add(statusLine, BorderLayout.SOUTH);

    desktop.putClientProperty("JDesktopPane.dragMode", "outline");
    setJMenuBar(createMenuBar());
    createPopupMenu();

    screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setBounds(screenInset, screenInset, screenSize.width - 2*screenInset,
                                        screenSize.height - 2*screenInset);
    paneSize = new Dimension((int)(screenSize.getWidth() - 2*screenInset)/2,
                             (int)(screenSize.getHeight() - 2*screenInset)/2);

    setVisible(true);
  }

  public void createPopupMenu()
  {
    popupMenu.add(new JMenuItem("Find"));
    popupMenu.add(new JMenuItem("Find and Replace"));
  }

  public JMenuBar createMenuBar ()
  {
    JMenuBar menuBar = new JMenuBar();

    JMenu subMenu = new JMenu("subMenu");
    subMenu.add(new JButton("Press"));
    subMenu.add(new JCheckBoxMenuItem("Select", true));
    subMenu.add(new JCheckBox("Select", true));


    JMenu fileMenu = new JMenu("File");
    fileMenu.setMnemonic('F');
    fileMenu.add(newFile);
    fileMenu.add(openFile);
    fileMenu.add(closeFile);
    fileMenu.add(subMenu);
    fileMenu.add(saveFile);
    fileMenu.add(saveAsFile);
    JTextField hiThere = new JTextField("Hi there");
    fileMenu.add(hiThere);
    fileMenu.addSeparator();
    fileMenu.add(exit);

    
    ActionListener lookAndFeelListener = new ActionListener()
      {
        public void actionPerformed(ActionEvent event)
        {
          try
          {
            UIManager.setLookAndFeel((String)lookAndFeels.get(event.getActionCommand()));
            SwingUtilities.updateComponentTreeUI(app);
          }
          catch (Exception ex)
          {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
              "Could not set look & feel.", JOptionPane.ERROR_MESSAGE);
          }
        }
      };
    
    
    JMenu optionsMenu = new JMenu("Options");
    
    Iterator iter = lookAndFeels.keySet().iterator();
    while (iter.hasNext())
    {
      JMenuItem item = new JMenuItem((String)iter.next());
      optionsMenu.add(item);
      item.addActionListener(lookAndFeelListener);
    }

    
    JMenu helpMenu = new JMenu("Help");
    helpMenu.add(about);
    
    menuBar.add(fileMenu);    
    menuBar.add(helpMenu);    
    menuBar.add(optionsMenu);
    
    newFile.addActionListener(this);
    openFile.addActionListener(this);
    closeFile.addActionListener(this);
    saveFile.addActionListener(this);
    saveAsFile.addActionListener(this);
    exit.addActionListener(this);
    
    return menuBar;
  }
  
  private JToolBar createToolBar()
  {
    JToolBar toolBar = new JToolBar();
    
    toolBar.add(openButton);
    toolBar.add(saveButton);
    toolBar.add(new JTextField("Hi", 20));
    
    openButton.addActionListener(this);
    saveButton.addActionListener(this);
    
    return toolBar;
  }
  
  public void actionPerformed(ActionEvent event)
  {
    if ((event.getSource() == openFile) || (event.getSource() == openButton))
      loadFile();
  }  
  
  public void loadFile()
  {
    File file = null;
    try
    {
      int returnOption = fileChooser.showOpenDialog(this);

      if (returnOption == JFileChooser.APPROVE_OPTION)
      {
        file = fileChooser.getSelectedFile();

        FileInputStream fin = new FileInputStream(file);
        BufferedReader in = new BufferedReader(new InputStreamReader(fin));
    
        PlainDocument document = new PlainDocument();
        JEditorPane editorPane = new JEditorPane();
        editorPane.read(in,document);
        statusField.setText("opened " + file.getName());

        JInternalFrame internalFrame = new JInternalFrame(file.getName(),
                                                          true, // resizable
                                                          true, // closable
                                                          true, // maximizable
                                                          true);// iconifiable

        internalFrame.setSize(paneSize);
        internalFrame.setLocation(lastOffset,lastOffset);
        internalFrame.setVisible(true);
        internalFrame.getContentPane().add(new JScrollPane(editorPane));
        desktop.add(internalFrame);
        internalFrame.moveToFront();
        internalFrame.setSelected(true);

        class MouseClickListener extends MouseAdapter
        {
          public MouseClickListener(JInternalFrame internalFrame)
            {this.internalFrame = internalFrame;}
            
   
          public void mouseReleased(MouseEvent event)
          {
            considerShowingPopupMenu(event);
            internalFrame.moveToFront();
          }

          private void considerShowingPopupMenu(MouseEvent event)
          {
            //if (event.isPopupTrigger())
            if (event.getButton() == MouseEvent.BUTTON3)
              popupMenu.show(event.getComponent(), event.getX(), event.getY());
          }
            
          private JInternalFrame internalFrame;  
        }

        editorPane.addMouseListener (new MouseClickListener(internalFrame));
        
        lastOffset += paneOffset;
        if (lastOffset > Math.min(screenSize.getWidth()*0.6,
                                  screenSize.getHeight()*0.6))
          lastOffset = 0;
      }    
    }
    catch (Exception e) {e.printStackTrace();}
  }  
  
  JTextField getCursorField() {return cursorField;}
  
  private JMenuItem newFile    = new JMenuItem("New");
  private JMenuItem openFile   = new JMenuItem("Open");
  private JMenuItem closeFile  = new JMenuItem("Close");
  private JMenuItem saveFile   = new JMenuItem("Save as");
  private JMenuItem saveAsFile = new JMenuItem("Save as");
  private JMenuItem exit       = new JMenuItem("Exit");
  private JMenuItem about      = new JMenuItem("About ISSC Editor");

  private JButton openButton = new JButton("Open");
  private JButton saveButton = new JButton(new ImageIcon("saveIcon.jpg"));
    
  private JFileChooser fileChooser = new JFileChooser();
  private JDesktopPane desktop     = new JDesktopPane();
  private JTextField statusField   = new JTextField("No open Documents", 30);
  private JTextField cursorField   = new JTextField("Line 0, Col 0", 20);
  private JPopupMenu popupMenu     = new JPopupMenu();

  private Dimension screenSize, paneSize;
  private static final int screenInset = 70;
  private static final int paneOffset  = 20;
  private int lastOffset = 0;

  public static void main(String[] args) {new STCEditor();}

  private static TreeMap lookAndFeels = new TreeMap();
  static
  {
    lookAndFeels.put("Windows", "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    lookAndFeels.put("Mac", "com.sun.java.swing.plaf.mac.MacLookAndFeel");
    lookAndFeels.put("Motif", "com.sun.java.swing.plaf.motif.MotifLookAndFeel");
    lookAndFeels.put("Metal", "com.sun.java.swing.plaf.metal.MetalLookAndFeel");
    lookAndFeels.put("CrossPlatform", UIManager.getCrossPlatformLookAndFeelClassName());
  }
  private STCEditor app;
}
