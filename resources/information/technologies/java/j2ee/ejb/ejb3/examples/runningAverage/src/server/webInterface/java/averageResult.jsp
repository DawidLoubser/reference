<%@ page contentType="text/html; charset=ISO-8859-1" %>
<html>

  <title>Averaging Result</title>

<body>
<h1><center>Averaging Result</center></h1>

<p>
  The averag of <%= request.getParameter("data") %> 
  is <%= request.getAttribute("average") %>.
</p>  

<p>
Press <a href="/average">here</a> to return to average calculator.
</p>

</body>
</html>
