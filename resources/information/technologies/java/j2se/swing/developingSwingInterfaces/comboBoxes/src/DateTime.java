import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;
import java.text.*;
import java.util.*;

public class DateTime extends JFrame implements ActionListener
{
  public DateTime()
  {
    setTitle("STC Clock");

    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent event)
        {System.exit(0);}
    });

    createDateFormatList();

    JPanel clockPanel = new JPanel();
    clockPanel.setBorder(new EmptyBorder(15,20,15,20));

    clockLabel.setText(dateFormat.format(dateTime));
    clockLabel.setFont(clockFont);
    clockLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    clockLabel.setBorder(raisedBorder);

    clockPanel.add(clockLabel);
    clockLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    JPanel formatPanel = new JPanel();
    formatPanel.setBorder(BorderFactory.createTitledBorder
         ("Select or enter date/time format:"));
    formatPanel.add(dateFormatList);

    getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
    getContentPane().add(clockPanel);
    getContentPane().add(formatPanel);

    dateFormatList.addActionListener(this);
    dateFormatList.setEditable(true);

    class TimerListener implements ActionListener
    {
       public void actionPerformed(ActionEvent e)
         {clockLabel.setText(dateFormat.format(new Date()));}
    }

    javax.swing.Timer timer = new javax.swing.Timer(1, new TimerListener());
    timer.start();

    pack();
    setVisible(true);
  }

  public void actionPerformed(ActionEvent event)
  {
    String datePattern = (String)dateFormatList.getSelectedItem();
    dateFormat.applyPattern(datePattern);
  }

  private void createDateFormatList()
  {
    dateFormatList.addItem("hh:mm:ss 'on' dd MM yyyy");
    dateFormatList.addItem("h:mm a");
    dateFormatList.addItem("hh 'o''clock' a, zzzz");
    dateFormatList.addItem("yyyy.MM.dd G 'at' hh:mm:ss z");
  }

  private Font clockFont = new Font("Helvetica",Font.PLAIN,20);
  private JLabel clockLabel = new JLabel();
  private JComboBox dateFormatList = new JComboBox();
  private SimpleDateFormat dateFormat 
    = new SimpleDateFormat("hh:mm:ss 'on' dd MM yyyy");
  private Date dateTime = new Date();
  private Border raisedBorder = BorderFactory.createRaisedBevelBorder();
  
  public static void main(String[] args) throws Exception 
    {new DateTime();}
}    
