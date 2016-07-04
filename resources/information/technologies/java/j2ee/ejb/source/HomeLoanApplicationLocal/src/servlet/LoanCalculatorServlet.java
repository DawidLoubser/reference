package za.co.solms.finance.calculators;

import java.util.*;
import java.text.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoanCalculatorServlet extends HttpServlet
{
   private LoanCalculatorHome loanCalculatorHome = null;

   /** Looks up the LoanCalculatorHome interface and saves it for use in
    doGet().
    */
   public void init() throws ServletException
   {
      try
      {
         InitialContext jndiContext = new InitialContext();

         Object ref  = jndiContext.lookup(jndiName);

         loanCalculatorHome
           = (LoanCalculatorHome)PortableRemoteObject.narrow
               (ref, LoanCalculatorHome.class);
      }
      catch(Exception e)
      {
         throw new ServletException
          ("Failed to lookup " + jndiName, e);
      }
   }

   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
   {
      String title = "Servlet interface to Loan Calculator EJB";
      String loanAmountStr = request.getParameter("loanAmount");
      String installmentStr = request.getParameter("installment");
      String interestRateStr = request.getParameter("interestRate");
      String loanPeriodStr = request.getParameter("loanPeriod");

      response.setContentType("text/html");

      PrintWriter out = response.getWriter();
      
      out.println("<HTML><HEAD><TITLE>");
      out.println(title);
      out.println("</TITLE></HEAD><BODY>");
      out.println("<H1>" + title + "</H1>");     

      try
      {
         LoanCalculator loanCalculatorBean = loanCalculatorHome.create();
    
         try
         {
           boolean validInput
             = calculate(loanCalculatorBean,
                         loanAmountStr, installmentStr,
                         interestRateStr, loanPeriodStr);

           if (!validInput)
             out.println("Invalid input.");
           else
           {   
             out.println("Loan amount: " 
               + amountFormatter.format(loanAmount) + "<br>");
             out.println("Monthly installment: " 
               + amountFormatter.format(installment) + "<br>");
             out.println("InterestRate: " 
               + amountFormatter.format(interestRate*100) + "<br>");
             out.println("Loan period: " 
               + amountFormatter.format(loanPeriod));
           }
           loanCalculatorBean.remove();
        }
        catch(Exception e)
        {
          out.println(e.toString());
        }
      }
      catch (javax.ejb.CreateException e)
      {
        out.println
          ("Problem creating enterprise bean.");
      }  
      catch (java.rmi.RemoteException e)
      {
        out.println
          ("Problem communicating with enterprise bean.");
      }  
      
      out.println("</BODY></HTML>");
      out.close();
   }
   
   private boolean calculate(LoanCalculator loanCalculatorBean,
                             String loanAmountStr, 
                             String installmentStr,
                             String interestRateStr, 
                             String loanPeriodStr)
              throws java.rmi.RemoteException,
                     za.co.solms.utils.InvalidArgumentsException                
   {
     loanAmount = installment = interestRate = loanPeriod = 0;

     try
     {
       loanAmount = amountFormatter.parse
         (loanAmountStr,new ParsePosition(0)).doubleValue();
     }
     catch (Exception e) {}
     
     try
     {
       installment = amountFormatter.parse
         (installmentStr,new ParsePosition(0)).doubleValue();
     }
     catch (Exception e) {}
     
     try
     {
       interestRate = amountFormatter.parse
         (interestRateStr,new ParsePosition(0)).doubleValue()/100;
     }
     catch (Exception e) {}
     
     try
     {
       loanPeriod = amountFormatter.parse
         (loanPeriodStr,new ParsePosition(0)).intValue();
     }
     catch (Exception e) {}
     
     int greaterZeros = 0;
     if (loanAmount > 0) ++ greaterZeros;
     if (installment > 0) ++ greaterZeros;
     if (interestRate > 0) ++ greaterZeros;
     if (loanPeriod > 0) ++ greaterZeros;

     if (greaterZeros < 3)
       return false;
     
       if (loanAmount <= 0)  
         loanAmount = loanCalculatorBean.calcLoanAmount
           (installment, interestRate, loanPeriod);
           
       else if (installment <= 0)    
         installment = loanCalculatorBean.calcMonthlyInstallment
           (loanAmount, interestRate, loanPeriod);
           
       else if (loanPeriod <= 0)    
         loanPeriod = loanCalculatorBean.calcLoanPeriodInMonths
           (loanAmount, installment, interestRate);
           
       return true;  
   } 
    
   private DecimalFormat amountFormatter
     = new DecimalFormat("###,###,###,##0.00");  

   private double loanAmount, installment, interestRate;
   private int loanPeriod;
   private static final String jndiName = "ejb/LoanCalculator";
//   private static final String jndiName = "java:comp/env/ejb/LoanCalculator";

}

