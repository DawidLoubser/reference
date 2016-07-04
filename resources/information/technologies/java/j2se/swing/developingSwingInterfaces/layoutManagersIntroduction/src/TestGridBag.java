import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class TestGridBag extends JFrame
{
  public TestGridBag()
  {
    setTitle("Testing GridBagLayout");

    addWindowListener(new WindowAdapter()
    {
        public void windowClosing(WindowEvent event)
        {System.exit(0);}
    });

    File file = new File(".");
    File[] files = file.listFiles();
    for (int i=0; i<files.length; ++i)
      System.out.println(files[i].getName());
    ImageIcon icon = new ImageIcon("face.gif");

    GridBagLayout gridBag = new GridBagLayout();
    getContentPane().setLayout(gridBag);
    GridBagConstraints constraints = new GridBagConstraints();


    constraints.gridx      = 1;
    constraints.gridy      = 1;
    constraints.gridwidth  = 1;
    constraints.gridheight = 1;
    constraints.weightx    = 1;
    constraints.weighty    = 1;
    constraints.fill       = GridBagConstraints.BOTH;
    JButton button = new JButton("(1,1)");
    button.setBackground(Color.green);
    gridBag.setConstraints(button, constraints);
    getContentPane().add(button);

    constraints.gridx      = 2;
    constraints.gridy      = 1;
    constraints.gridwidth  = 1;
    constraints.gridheight = 1;
    constraints.weightx    = 1;
    constraints.weighty    = 1;
    constraints.fill       = GridBagConstraints.BOTH;
    constraints.anchor     = GridBagConstraints.CENTER;
    button = new JButton("1");
    gridBag.setConstraints(button, constraints);
    getContentPane().add(button);

    constraints.gridx      = 3;
    constraints.gridy      = 1;
    constraints.gridwidth  = 1;
    constraints.gridheight = 1;
    constraints.weightx    = 1;
    constraints.weighty    = 1;
    constraints.fill       = GridBagConstraints.BOTH;
    constraints.anchor     = GridBagConstraints.CENTER;
    button = new JButton("2");
    gridBag.setConstraints(button, constraints);
    getContentPane().add(button);

    constraints.gridx      = 2;
    constraints.gridy      = 2;
    constraints.gridwidth  = 4;
    constraints.gridheight = 1;
    constraints.weightx    = 1;
    constraints.weighty    = 1;
    constraints.fill       = GridBagConstraints.NONE;
    constraints.anchor     = GridBagConstraints.CENTER;
    button = new JButton("Press me please");
    gridBag.setConstraints(button, constraints);
    getContentPane().add(button);

    constraints.gridx      = 4;
    constraints.gridy      = 1;
    constraints.gridwidth  = 3;
    constraints.gridheight = 1;
    constraints.weightx    = 1;
    constraints.weighty    = 1;
    constraints.fill       = GridBagConstraints.NONE;
    constraints.anchor     = GridBagConstraints.NORTHEAST;
    button = new JButton("Press me again please");
    gridBag.setConstraints(button, constraints);
    getContentPane().add(button);

    constraints.gridx      = 6;
    constraints.gridy      = 2;
    constraints.gridwidth  = 2;
    constraints.gridheight = 4;
    constraints.weightx    = 1;
    constraints.weighty    = 1;
    constraints.fill       = GridBagConstraints.BOTH;
    constraints.anchor     = GridBagConstraints.CENTER;
    button = new JButton("Press!!");
    gridBag.setConstraints(button, constraints);
    getContentPane().add(button);

    constraints.gridx      = 7;
    constraints.gridy      = 1;
    constraints.gridwidth  = 1;
    constraints.gridheight = 1;
    constraints.weightx    = 1;
    constraints.weighty    = 1;
    constraints.fill       = GridBagConstraints.BOTH;
    constraints.anchor     = GridBagConstraints.CENTER;
    button = new JButton("Press!!!!");
    gridBag.setConstraints(button, constraints);
    getContentPane().add(button);

    constraints.gridx      = 5;
    constraints.gridy      = 5;
    constraints.gridwidth  = 2;
    constraints.gridheight = 2;
    constraints.weightx    = 1;
    constraints.weighty    = 1;
    constraints.anchor     = GridBagConstraints.CENTER;
    constraints.fill       = GridBagConstraints.BOTH;
    button = new JButton(icon);
    gridBag.setConstraints(button, constraints);
    getContentPane().add(button);

    constraints.gridx      = 1;
    constraints.gridy      = 4;
    constraints.gridwidth  = 1;
    constraints.gridheight = 3;
    constraints.weightx    = 1;
    constraints.weighty    = 1;
    constraints.anchor     = GridBagConstraints.CENTER;
    constraints.fill       = GridBagConstraints.NONE;
    button = new JButton(icon);
    gridBag.setConstraints(button, constraints);
    getContentPane().add(button);

    pack();
    setVisible(true);
  }

  public static void main(String[] args) throws Exception
    {new TestGridBag();}
}
