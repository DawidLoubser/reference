<![CDATA[
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<html>

  <title>Loan Calculation Results</title>

<body>
<h1><center>Loan Calculation Results</center></h1>

<center>

  <table>
    <tr><th align="left">Loan amount:</th> <td>R<%= request.getAttribute("loanAmount") %></td></tr>
    <tr><th align="left">Loan period:</th> <td><%= request.getAttribute("loanPeriod") %> months</td></tr>
    <tr><th align="left">Interest rate:</th> <td><%= request.getAttribute("interestRate") %>%</td></tr>
    <tr><th align="left">Monthly installment:</th> <td>R<%= request.getAttribute("monthlyInstallment")%></td></tr>
  </table>

</center>

<p>
Press <a href="/LoanCalculator">here</a> to return to calculator.
</p>

</body>
</html>

]]>
