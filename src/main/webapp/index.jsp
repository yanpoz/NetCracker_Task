<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<html>
    <head>
        <link rel='stylesheet' href='style.css'>
    </head>
    <body>
    <div>
        <h2>Hello World! From JSP</h2>
        <br/>
        <a href="servlet?id=0">Click here to see servlet</a>
        <br/>
        <h2>
            Now is
            <%
            java.util.Date date = new java.util.Date();
            %>
            <%=date.toString()
            %>
        </h2>
    </div>
    </body>
</html>
