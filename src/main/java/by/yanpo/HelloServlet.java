package by.yanpo;//package main;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class HelloServlet extends HttpServlet {

    private static final String URL = "jdbc:mysql://localhost:3306/schem_devcolibri"+
            "?verifyServerCertificate=false"+
            "&useSSL=false"+
            "&requireSSL=false"+
            "&useLegacyDatetimeCode=false"+
            "&amp"+
            "&serverTimezone=UTC";

    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException {

        ServletOutputStream out = response.getOutputStream();

        try {

            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            if (!con.isClosed())
                System.out.println("\n***Connection is OK***");

            Statement stat = con.createStatement();

            //String Insert = "insert into users (name, age, email) values('Cain', 19, 'cain@mail.com');";
            //int res = stat.executeUpdate(Insert);

            int id, age;
            String name;
            String email;
            if (!request.getParameter("id").equals("0")) {

                id = Integer.parseInt(request.getParameter("id"));
                name = request.getParameter("name");
                age = Integer.parseInt(request.getParameter("age"));
                email = request.getParameter("email");

                String Update = "update users set " +
                        "name = '"+ name +
                        "', age = "+ age +
                        ", email = '"+ email +
                        "' where id = "+ id +";";
                stat.executeUpdate(Update);
            }


                String Select = "select * from users;";
            ResultSet ResSet = stat.executeQuery(Select);

            out.println("<html>"+
                    "<head><title>Hello Servlet</title>" +
                    "<link rel='stylesheet' href='style.css'>"+
                    "</head>"+
                    "<body>"+
                    "<h1>list of Users</h1>"+"\n");
            out.println(
               "<table>"+
               "<tr>"+
               "   <th>Name</th>"+
               "   <th>Age</th>"+
               "   <th>Email</th>"+
               "   <th>Edit</th>"+
               "   <th>Delete</th>"+
               "</tr>");

            //String name, age, email;
            while (ResSet.next()){
                id = ResSet.getInt(1);
                name = ResSet.getString(2);
                age = ResSet.getInt(3);
                email = ResSet.getString(4);

                out.println(
                    "<tr>"+
                        "<td>"+name+"</td>"+
                        "<td>"+age+"</td>"+
                        "<td>"+email+"</td>"+
                        "<td>"+
                            "<a href='edit.jsp?id="+
                            id+"'>Edit</a>"+"</td>"+
                        "<td>"+
                            "<a href='servlet?id="+
                            0+"'>Delete</a>"+"</td>"+
                    "</tr>");
            }

            out.println(
                    "<tr><td  colspan='5'>"+
                        "<a href='edit.jsp?id="+
                        0+"'>Add New</a></td></tr>"+
                    "</table>"+
                    "</body>"+
                    "<html>");

            con.close();
        }
        catch (SQLException e) {
            System.err.println();
            System.err.println("Error code: "+e.getErrorCode());
            System.err.println("SQLState: "+e.getSQLState());
            e.printStackTrace();
        }
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException {
        doGet(request, response);
    }
}