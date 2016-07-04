<![CDATA[
package za.co.solmstraining.j2me.persistence;

import java.util.*;

import javax.microedition.lcdui.*;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

public class BirthdayList extends MIDlet
{
  public void startApp() throws MIDletStateChangeException
  {
    if (display == null)
      initMIDlet();
  }

  public void initMIDlet()
  {
    display = Display.getDisplay(this);

    try
    {
      logo = Image.createImage("/Logo.png");
    }
    catch (java.io.IOException e) {}

    try
    {
      RecordStore rmsStore
        = RecordStore.openRecordStore("birthdays", true);
      System.out.println("rmsStore = " + rmsStore);
      store = new BirthdayRepository(rmsStore);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      display.setCurrent
        (new Alert("BirthdayList","ERROR creating/opening store",
                   null, AlertType.ERROR),
           new BirthdaysPresentation());
    }

    new Thread()
      {
        public void run()
        {
          try
          {
            birthdays = store.getBirthdays(new java.util.Date());
          }
          catch (RecordStoreException e)
          {
            e.printStackTrace();
            display.setCurrent
              (new Alert("BirthdayList","ERROR reading from store",
                        null, AlertType.ERROR),
                new BirthdaysPresentation());
          }
        }
      }.run();

    Alert splashScreen = new Alert("BirthdayList",
      "Solms Training & Consulting",
         logo, AlertType.INFO);

    display.setCurrent(splashScreen, new BirthdaysPresentation());
  }

  public void pauseApp(){}

  public void destroyApp(boolean unconditional)
                  throws MIDletStateChangeException
  {
    exitMIDlet();
  }

  class BirthdaysPresentation extends List
  {
    public BirthdaysPresentation()
    {
      super("DisplayRecords", List.EXCLUSIVE,
            getStringArray(birthdays), null);
      addCommand(exitCommand);
      addCommand(addCommand);
      addCommand(searchCommand);
      addCommand(deleteCommand);
      addCommand(editCommand);
      addCommand(listAllCommand);

      setCommandListener(new CommandListener()
        {
          public void commandAction(Command cmd, Displayable d)
          {
            if (cmd == exitCommand)
              BirthdayList.this.exitMIDlet();

            if (cmd == addCommand)
              display.setCurrent(new BirthdayEditor());

            if (cmd == editCommand)
              display.setCurrent(new BirthdayEditor
                ((Birthday)birthdays.elementAt(getSelectedIndex())));

            if (cmd == deleteCommand)
            {
               try
               {
                 Birthday birthday
                   = (Birthday)birthdays.elementAt(getSelectedIndex());
                 store.deleteBirthday(birthday);
                 birthdays.removeElementAt(getSelectedIndex());
                 update(birthdays);
               }
               catch (RecordStoreException e)
               {
                 e.printStackTrace();
                 display.setCurrent
                   (new Alert("Birthday List",
                            "Error reading from birthday store",
                            null, AlertType.ERROR),
                            BirthdaysPresentation.this);
               }
             }

             if (cmd == listAllCommand)
             {
               try
               {
                 update(store.getBirthdays());
               }
               catch (RecordStoreException e)
               {
                 e.printStackTrace();
                 display.setCurrent
                   (new Alert("Birthday List",
                            "Error reading from birthday store",
                            null, AlertType.ERROR),
                            BirthdaysPresentation.this);
               }
             }

             if (cmd == searchCommand)
             {
              class SearchForm extends Form
              {
               public SearchForm()
                {
                 super("Search on Name");

                  append(nameField);

                  addCommand(searchCommand);
                  addCommand(cancelCommand);

                  setCommandListener(new CommandListener()
                    {
                     public void commandAction
                        (Command cmd, Displayable d)
                      {
                        try
                        {
                        if (cmd == searchCommand)
                          update(store.getBirthdays
                            (nameField.getString().trim()));

                         display.setCurrent(BirthdaysPresentation.this);
                        }
                       catch (RecordStoreException e)
                        {
                          e.printStackTrace();
                          display.setCurrent
                            (new Alert("Birthday List",
                                      "Error reading from birthday store",
                                      null, AlertType.ERROR),
                                      BirthdaysPresentation.this);
                        }
                      }
                    });
                }
                private Command searchCommand
                  = new Command("search", Command.SCREEN, 1);
                private Command cancelCommand
                  = new Command("cancel", Command.CANCEL, 1);
                private TextField nameField
                  = new TextField("name", "", 20, TextField.ANY);
              }
              display.setCurrent(new SearchForm());
             }
           }
        });
    }

    private void update(Vector theBirthdays)
    {
      removeAll();
      birthdays = theBirthdays;
      for (int i=0; i<birthdays.size(); ++i)
        append(((Birthday)birthdays.elementAt(i)).toString(), null);
    }

    private void removeAll()
    {
      try
      {
        while (true)
          delete(0);
      }
      catch (IndexOutOfBoundsException e) {}
    }

    private final Command addCommand
      = new Command("add", Command.ITEM, 2);
    private final Command editCommand
      = new Command("edit", Command.ITEM, 3);
    private final Command searchCommand
      = new Command("search", Command.ITEM, 2);
    private final Command listAllCommand
      = new Command("listAll", Command.ITEM, 3);
    private final Command deleteCommand
      = new Command("delete", Command.ITEM, 3);
  }

  private String[]
    getStringArray(Vector birthdays)
  {
    String[] result = new String[birthdays.size()];
    for (int i=0; i<birthdays.size(); ++i)
      result[i]
        = ((Birthday)birthdays.elementAt(i)).toString();
    return result;
  }

  public class BirthdayEditor extends Form
  {
    public BirthdayEditor()
    {
      this(new Birthday("", new Date()));
    }

    public BirthdayEditor(Birthday selectedBirthday)
    {
      super("Add/Edit Birthday");
      this.birthday = selectedBirthday;

      nameField
        = new TextField("name", birthday.getName(),
                        20, TextField.ANY);
      birthdayField
        = new DateField("birthday", DateField.DATE);
      birthdayField.setDate(birthday.getDate());

      append(nameField);
      append(birthdayField);

      addCommand(addCommand);
      addCommand(cancelCommand);

      setCommandListener(new CommandListener()
        {
          public void commandAction(Command cmd, Displayable d)
          {
            if (cmd == addCommand)
            {
              birthday.setName(nameField.getString());
              birthday.setDate(birthdayField.getDate());
              nameField.setString("");

              try
              {
                if (birthday.getRecordId() > 0)
                  store.updateBirthday(birthday);
                else
                  store.addBirthday(birthday);
              }
              catch (RecordStoreException e)
              {
                e.printStackTrace();
                display.setCurrent(
                new Alert("Birthday List",
                            "Error updating birthday store",
                            null, AlertType.ERROR),
                            BirthdayEditor.this);
              }

              display.setCurrent
                (new Alert("BirthdayList", "Added birthday to list.",
                           null, AlertType.CONFIRMATION),
                   new BirthdaysPresentation());
            }
            if (cmd == cancelCommand)
              display.setCurrent(new BirthdaysPresentation());
          }
        });
    }

    private TextField nameField;
    private DateField birthdayField;

    private final Command cancelCommand
      = new Command("cancel", Command.CANCEL, 1);
    private final Command addCommand
      = new Command("save", Command.OK, 1);
    private Birthday birthday;
  }

  public void exitMIDlet() {notifyDestroyed();}

  private Vector birthdays = new Vector();
  private BirthdayRepository store;

  private Display display;
  private Image logo;

  private static final Command exitCommand
    = new Command("Exit", Command.EXIT, 1);
}
]]>
