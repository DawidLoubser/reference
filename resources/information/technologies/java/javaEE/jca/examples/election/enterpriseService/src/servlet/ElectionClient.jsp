<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%
  Object numVotes = request.getAttribute("numVotes");
  Object party = request.getAttribute("party");
  %>
<html>

  <title>Election Client</title>

<body>
  <h1><center>Election Client</center></h1>
    <center>
      <form action="ElectionServlet" method="post">
	<p>
  	  <table>
  	    <tr><td>Party:</td><td><input type="text" name="party"    value="<%= party != null ? party : "" %>" size="20"></td></tr>
	    <tr><td>Number of Votes</td><td><input type="text" name="numVotes"
	      value="<%= numVotes != null ? numVotes : "" %>" size="20"></td></tr>
	  </table>  
	</p>
	<p>
	  <input type="submit" name="action" value="getVotes">
	  <input type="submit" name="action" value="addVotes">
	</p>  
      </form>
    </center>
  </p>
</body>
</html>



