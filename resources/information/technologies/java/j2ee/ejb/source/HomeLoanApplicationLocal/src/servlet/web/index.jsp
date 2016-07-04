<![CDATA[
<%@ page import="stc.finance.calculators.LoanCalculator.*,stc.util.*" %>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<html>

  <title>Loan Calculator</title>

  <script language="JAVASCRIPT" type="TEXT/JAVASCRIPT">
   function validLoanForm(form)
   {
     var nEntries = 0;

     if (parseFloat(form.loanAmount.value) <= 0)
     {
       alert("ERROR: loan amount must be positive.");
       return false;
     }
     if (parseInt(form.loanPeriod.value) <= 0)
     {
       alert("ERROR: loan period in months must be a positive integer.");
       return false;
     }
     if (parseFloat(form.interestRate.value) < 0)
     {
       alert("ERROR: interest rate must be non-negative.");
       return false;
     }
     if (parseInt(form.monthlyInstallment.value) <= 0)
     {
       alert("ERROR: monthly installment must be positive.");
       return false;
     }

     if (form.loanAmount.value!="") ++nEntries;
     if (form.loanPeriod.value!="") ++nEntries;
     if (form.interestRate.value!="") ++nEntries;
     if (form.monthlyInstallment.value!="") ++nEntries;

     if (nEntries != 3)
     {
       alert("ERROR: Have to supply 3 of the 4 entry fields.");
       return false;
     }

     return true;
   }
  </script>

<body>
<h1><center>Loan Calculator</center></h1>

<center>
  <form name="loanParametersForm" onSubmit="return validLoanForm(this)"
        action="LoanCalculatorServlet" method="POST" >
    <table>
      <tr>
        <td>loan amount:</td>
        <td><input TYPE="TEXT" NAME="loanAmount"/></td>
      <tr/>
      <tr>
        <td>loan period (months):</td>
        <td><input TYPE="TEXT" NAME="loanPeriod"/></td>
      <tr/>
      <tr>
        <td>annual interest rate (%):</td>
        <td><input TYPE="TEXT" NAME="interestRate"/></td>
      <tr/>
      <tr>
        <td>monthly installment:</td>
        <td><input TYPE="TEXT" NAME="monthlyInstallment"/></td>
      <tr/>
    </table>
    <input type="submit" value="Submit"/>
    <input type="reset" value="Reset"/>
  </form>
</center>

<%
  String loanAmountStr = request.getParameter("loanAmount");
%>

</body>
</html>
]]>
