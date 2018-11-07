package by.yanpo;//package main;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class HelloServlet extends HttpServlet {

    Connection con;

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
    throws IOException, ServletException {

        //PrintWriter writer = response.getWriter();
        //writer.print("Hello from Servlet AGAIN");
        ServletOutputStream out = response.getOutputStream();

        String x = request.getParameter("x");
        System.out.println(x);

        try {

            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            if (!con.isClosed()) {
                System.out.println();
                System.out.println("***Connection is OK***");
            }

            Statement stat = con.createStatement();
            //String Insert = "insert into users (name, age, email) values('Cain', 19, 'cain@mail.com');";
            //int res = stat.executeUpdate(Insert);
            //System.out.println(res);

            String Select = "select * from users;";
            ResultSet ResSet = stat.executeQuery(Select);

            out.println("<html>");
            out.println("<head><title>Hello Servlet</title></head>");
            out.println("<body>");
            out.println("<h1>list of Users</h1>"+"\n");

            out.println(
               "<table border='1' cellpadding='5' cellspacing='1' >"+
               "<tr>"+
               "   <th>Name</th>"+
               "   <th>Age</th>"+
               "   <th>Email</th>"+
               "   <th>Edit</th>"+
               "   <th>Delete</th>"+
               "</tr>");

            int id;
            String name, age, email;
            while (ResSet.next()){
                id = ResSet.getInt(1);
                name = ResSet.getString(2);
                age = ResSet.getString(3);
                email = ResSet.getString(4);

                out.println(
                    "<tr>"+
                        "<td>"+id+"</td>"+
                        "<td>"+name+"</td>"+
                        "<td>"+age+"</td>"+
                        "<td>"+email+"</td>"+
                    "</tr>");
            }

            out.println("</table>");

            out.println("</body>");
            out.println("<html>");

            con.close();
        }
        catch (SQLException e) {
            System.err.println();
            System.err.println("Error code: "+e.getErrorCode());
            System.err.println("SQLState: "+e.getSQLState());
            e.printStackTrace();
        }
    }
}