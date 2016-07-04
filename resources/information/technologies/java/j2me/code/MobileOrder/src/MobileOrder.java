<![CDATA[
package za.co.solmstraining.j2me.highlevelgui;

import za.co.solmstraining.j2me.commerce.*;

import javax.microedition.lcdui.*;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class MobileOrder extends MIDlet
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

    Alert splashScreen = new Alert("MobileOrder",
      "Solms Training & Consulting",
         logo, AlertType.INFO);
    splashScreen.setTimeout(3000);
         /* Otherwise default timeout for device is used.*/

    display.setCurrent(splashScreen, new LoginForm());
  }

  public void pauseApp(){}

  public void destroyApp(boolean unconditional)
                  throws MIDletStateChangeException
  {
    exitMIDlet();
  }

  public void exitMIDlet() {notifyDestroyed();}

  class LoginForm extends Form
  {
    public LoginForm()
    {
      super("Login Form");
      addCommand(exitCommand);
      addCommand(okCommand);

      append(usernameField);
      append(passwordField);

      setCommandListener(new CommandListener()
        {
          public void commandAction(Command cmd, Displayable d)
          {
            if (cmd == exitCommand)
              MobileOrder.this.exitMIDlet();

            if (cmd == okCommand)
            {
              if (usernameField.getString().equals("James Bond")
                && passwordField.getString().equals("007"))
                  display.setCurrent(shoppingBasketPresentation);
              else
                display.setCurrent
                  (new Alert("MobileOrder", "Invalid Login", null,
                   AlertType.ERROR), LoginForm.this);
            }
          }
        });
    }
    private final Command okCommand
      = new Command("Login", Command.OK, 1);

    private TextField usernameField = new TextField
      ("User name:", null, 15, TextField.ANY);
    private TextField passwordField = new TextField
      ("Password:", null, 15, TextField.PASSWORD);
  }


  class ShoppingBasketPresentation extends List
  {
    public ShoppingBasketPresentation()
    {
      super("Shopping basket", List.IMPLICIT);

      addCommand(addCmd);
      addCommand(editCmd);
      addCommand(buyCmd);
      addCommand(removeCmd);
      addCommand(exitCmd);

      setCommandListener(new CommandListener()
        {
          public void commandAction(Command cmd, Displayable d)
          {
            if (cmd == exitCommand)
              MobileOrder.this.exitMIDlet();

            if (cmd == addCmd)
              display.setCurrent(new EditLineItemPresentation());

            if (cmd == editCmd)
            {
              int selectedIndex = getSelectedIndex();
              if (selectedIndex < 0)
                display.setCurrent
                  (new Alert("MobileOrder",
                    "Must select line item to edit.", null,
                       AlertType.ERROR), ShoppingBasketPresentation.this);
              else
                display.setCurrent(new EditLineItemPresentation
                  (basket.getLineItem(selectedIndex)));
            }

            if (cmd == removeCmd)
            {
              int selectedIndex = getSelectedIndex();
              basket.remove(basket.getLineItem(selectedIndex));
              delete(selectedIndex);
            }

            if (cmd == buyCmd)
            {
              System.out.println("buying");
              display.setCurrent(new CheckoutPresentation(basket));
            }

          }
        });
    }

    private void updateView(LineItem lineItem)
    {
      int index = basket.getLineItems().indexOf(lineItem);
      if (index < size())
        set(index, lineItem.toString(), null);
      else
        append(lineItem.toString(), null);
    }

    private final Command addCmd
      = new Command("Add to basket", Command.SCREEN, 1);
    private final Command editCmd
      = new Command("Edit line item", Command.ITEM, 2);
    private final Command removeCmd
      = new Command("Purge line item", Command.ITEM, 3);
    private final Command buyCmd
      = new Command("Buy basket", Command.SCREEN, 1);
    private final Command exitCmd
      = new Command("Exit application", Command.SCREEN, 4);

    class EditLineItemPresentation extends Form
    {
      public EditLineItemPresentation()
      {
        this(new LineItem(products.getProductId(0),1));
      }

      public EditLineItemPresentation(LineItem lineItem)
      {
        super("Editing line item");
        String productId = lineItem.getProductId();

        addCommand(updateBasketCommand);
        addCommand(cancelCommand);

        append(productSelector);
        append(priceField);
        append(quantityField);

        quantityField.setString(Integer.toString(lineItem.getQuantity()));
        productSelector.setSelectedIndex
          (products.getIndex(productId), true);

        priceField.setText
          (getDecimalString(products.getPrice(productId),2));

       setCommandListener(new CommandListener()
          {
            public void commandAction(Command cmd, Displayable d)
            {
              if (cmd == updateBasketCommand)
              {
                String productId
                  = products.getProductId
                      (productSelector.getSelectedIndex());

                LineItem lineItem
                  = basket.addLineItem(productId,
                      Integer.parseInt(quantityField.getString()));
                updateView(lineItem);
              }
              display.setCurrent(ShoppingBasketPresentation.this);
            }
          });
      }

      private final Command updateBasketCommand
        = new Command("update basket", Command.OK, 1);
      private final Command cancelCommand
        = new Command("Cancel", Command.CANCEL, 2);

      private final StringItem priceField
        = new StringItem("price: ", "0.00");
      private final ChoiceGroup productSelector
        = new ChoiceGroup("product:", ChoiceGroup.EXCLUSIVE,
                          products.getProductIds(), null);
      private final TextField quantityField
        = new TextField("quantity", "1", 6, TextField.NUMERIC);
    }

    private ProductList products = new ProductList();
    private ShoppingBasket basket = new ShoppingBasket(products);
  }

  class CheckoutPresentation extends Form
  {
    public CheckoutPresentation(ShoppingBasket basket)
    {
      super("Checking out.");
      System.out.println("checking out");
      this.basket = basket;
      addCommand(checkoutCmd);
      addCommand(exitCmd);

      append(deliveryDateField);
      append(nameField);
      append(creditCardNumberField);
      append(creditCardTypeSelector);

      setCommandListener(new CommandListener()
        {
          public void commandAction(Command cmd, Displayable d)
          {
            if (cmd == exitCommand)
              MobileOrder.this.exitMIDlet();

            if (cmd == checkoutCmd)
            {
//              Order order = new Order(basket, deliveryDate, name,
//                                       creditCardNo, creditCardField);
              // now code for sending and perhaps persisting order

              display.setCurrent
                (new Alert("MobileOrder",
                  "Thank you for the order.", logo,
                    AlertType.INFO), new ShoppingBasketPresentation());
            }
          }
        });
    }
    Command exitCmd = new Command("Exit", Command.SCREEN, 2);
    Command checkoutCmd = new Command("Buy", Command.SCREEN, 1);

    private DateField deliveryDateField
      = new DateField("Delivery date", DateField.DATE);
    private TextField nameField
      = new TextField("Name", "", 20, TextField.ANY);
    private TextField creditCardNumberField
      = new TextField("Credit card no", "", 15, TextField.NUMERIC);
    private ChoiceGroup creditCardTypeSelector
      = new ChoiceGroup("Credit card type", ChoiceGroup.EXCLUSIVE,
                        new String[]{"Visa", "Master"}, null);
    private final ShoppingBasket basket;
  }

  private static String getDecimalString
                          (long amount, int numDigitsAfterDecimalPt)
  {
    String amountStr = Long.toString(amount);
    int splitPos = amountStr.length()-numDigitsAfterDecimalPt;
    return amountStr.substring(0,splitPos) + '.' +
        amountStr.substring(splitPos, amountStr.length());
  }

  private ShoppingBasketPresentation shoppingBasketPresentation
    = new ShoppingBasketPresentation();

  private Display display;
  private Image logo;

  private static final Command exitCommand
    = new Command("Exit", Command.EXIT, 1);
}
]]>
