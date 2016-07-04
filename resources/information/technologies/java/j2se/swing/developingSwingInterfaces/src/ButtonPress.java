 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 
 public class ButtonPress extends JFrame implements ActionListener
 {
   public ButtonPress(ButtonPressHandler buttonHandler)
   {
     setTitle("ButtonPress");
 
     addWindowListener(new WindowAdapter()
                       {
                         public void windowClosing(WindowEvent event)
                           {System.exit(0);}
                       });
 
     getContentPane().setBackground(Color.white);
     getContentPane().setLayout(new FlowLayout());
     getContentPane().add(button);
 
     button.addActionListener(buttonHandler);
     button.addActionListener(this);
 
     setSize(400,150);
     setVisible(true);
   }
 
   public void actionPerformed(ActionEvent event)
   {
     red   = randomNumberGenerator.nextInt(256);
     green = randomNumberGenerator.nextInt(256);
     blue  = randomNumberGenerator.nextInt(256);
 
     button.setForeground(new Color(red,green,blue));
   }
 
 
   private JButton button = new JButton("Press me!");
   private java.util.Random randomNumberGenerator = new java.util.Random();
   private int red = 0, green = 0, blue = 0;
 
   public static void main(String[] args) throws Exception
   {
     UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
     new ButtonPress(new ButtonPressHandler());
   }

 }
 
 class ButtonPressHandler implements ActionListener
 {
   public void actionPerformed(ActionEvent event)
   {
     ++numPresses;
     ((JButton)event.getSource()).setText
       (numPresses + ": OOh, that was nice. Please press me again.");
 
     if (numPresses == 10)
       ((JButton)event.getSource()).removeActionListener(this);
   }
 
   private int numPresses = 0;
 }                

