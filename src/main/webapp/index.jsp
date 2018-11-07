<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<html>
    <body>
        <h2>Hello World! From JSP</h2>
        <br/>
        <a href="servlet?x=iAmParam >^.^<">Click here to see servlet</a>
        <br/>
        <h2>
            Now is
            <%
            java.util.Date date = new java.util.Date();
            %>
            <%=date.toString()
            %>
        </h2>

        <table border="1" cellpadding="5" cellspacing="1" >
               <tr>
                  <th>Name</th>
                  <th>Age</th>
                  <th>Email</th>
                  <th>Edit</th>
                  <th>Delete</th>
               </tr>
               <c:forEach items="${userList}" var="user" >
                  <tr>
                     <td>${user.name}</td>
                     <td>${user.age}</td>
                     <td>${user.email}</td>
                     <td>
                        <a href="editUser?code=${user.code}">Edit</a>
                     </td>
                     <td>
                        <a href="deleteUser?code=${user.code}">Delete</a>
                     </td>
                  </tr>
               </c:forEach>
            </table>
    </body>
</html>
